/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.biz_integral.crm.cc.application.web.acctGrpHierarchy.EntryInitializeResponseModel;
import com.biz_integral.crm.cc.application.web.acctGrpHierarchy.EntryMapper;
import com.biz_integral.crm.cc.domain.dao.CrmCcAcctGrpDao;
import com.biz_integral.crm.cc.domain.dao.CrmCcAcctGrpIncAthDao;
import com.biz_integral.crm.cc.domain.dto.AcctGrpHierarchyDto;
import com.biz_integral.crm.cc.domain.dto.NodeDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAcctGrp;
import com.biz_integral.crm.cc.domain.entity.CrmCcAcctGrpIncAth;
import com.biz_integral.crm.cc.domain.logic.AcctGrpHierarchyLogic;
import com.biz_integral.crm.cc.domain.logic.ParameterLogic;
import com.biz_integral.crm.cc.domain.types.DeleteFlg;
import com.biz_integral.crm.cc.domain.types.UseType;
import com.biz_integral.crm.cc.domain.util.FileUtil;
import com.biz_integral.foundation.core.configuration.ApplicationConfigurationRegistry;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.io.ColumnType;
import com.biz_integral.foundation.core.io.csv.CSVReader;
import com.biz_integral.foundation.core.io.csv.CSVReaderPreferences;
import com.biz_integral.foundation.core.io.csv.CSVWriter;
import com.biz_integral.foundation.core.io.csv.CSVWriterPreferences;
import com.biz_integral.foundation.core.message.MessageBuilder;
import com.biz_integral.foundation.core.types.EnumNamesRegistry;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.foundation.core.util.LocaleUtil;
import com.biz_integral.foundation.core.validation.ValidationException;
import com.biz_integral.foundation.core.validation.ValidationResult;
import com.biz_integral.foundation.core.validation.ValidationResults;
import com.biz_integral.service.async.AsyncTaskClient;
import com.biz_integral.service.async.Request;
import com.biz_integral.service.async.domain.types.ExecutePriorityType;
import com.biz_integral.service.domain.master.BizConfigurationProvider;
import com.biz_integral.service.maskat.service.model.FileDownloadResponseModel;
import com.biz_integral.service.storage.SharedFile;
import com.biz_integral.service.storage.SharedStorageManager;

/**
 * {@link AcctGrpHierarchyLogic}の実装クラス。
 */
public class AcctGrpHierarchyLogicImpl implements AcctGrpHierarchyLogic {

    /**
     * {@link AsyncTaskClient}
     */
    @Resource
    protected AsyncTaskClient asyncTaskClient;

    /**
     * {@link ParameterLogic}です
     */
    @Resource
    private ParameterLogic parameterLogic;

    /**
     * {@link ApplicationConfigurationRegistry}
     */
    @Resource
    protected ApplicationConfigurationRegistry applicationConfigurationRegistry;

    /**
     * {@link SharedStorageManager}です
     */
    @Resource
    private SharedStorageManager sharedStorageManager;

    /**
     * {@link EntryMapper 階層マッパー}
     */
    @Resource
    protected EntryMapper entryMapper;

    /**
     * {@link ContextContainer コンテキストコンテナー}
     */
    @Resource
    protected ContextContainer contextContainer;

    /**
     * {@link BizConfigurationProvider}のDaoクラスです。
     */
    @Resource
    protected BizConfigurationProvider bizConfigurationProvider;

    /** CRMアカウントグループ内包DAO */
    @Resource
    protected CrmCcAcctGrpIncAthDao crmCcAcctGrpIncAthDao;

    /** CRMアカウントグループDAO */
    @Resource
    protected CrmCcAcctGrpDao crmCcAcctGrpDao;

    /**
     * {@link EnumNamesRegistry 区分に関する名称管理}
     */
    @Resource
    protected EnumNamesRegistry enumNamesRegistry;

    @Override
    public EntryInitializeResponseModel searchAll(AcctGrpHierarchyDto searchdto) {

        // 検索
        List<AcctGrpHierarchyDto> resultList =
            crmCcAcctGrpIncAthDao.findAllByCriteria(searchdto);

        EntryInitializeResponseModel responseModel =
            new EntryInitializeResponseModel();

        List<NodeDto> treeNodeList = new ArrayList<NodeDto>();

        StringBuffer grpName = new StringBuffer();

        String tmp = StringUtils.EMPTY;
        // ツリーモデルにセット
        for (AcctGrpHierarchyDto rltDto : resultList) {
            grpName = new StringBuffer();
            // 削除されたアカウントグループ名を取得
            if (rltDto.crmAccountGroupName == null
                || rltDto.crmAccountGroupName.length() <= 0) {

                // アカウントグループ名を取得
                if (rltDto.rootFlag || rltDto.depth == 1) {
                    rltDto.crmAccountGroupName =
                        getAccountGroupName(
                            searchdto.companyCd,
                            rltDto.crmAccountGroupCd,
                            searchdto.localeId);
                }
            }

            grpName
                .append("[")
                .append(rltDto.crmAccountGroupCd)
                .append("] ")
                .append(rltDto.crmAccountGroupName);
            if (rltDto.rootFlag) {
                // ルートグループ
                NodeDto nodeDto = new NodeDto();
                nodeDto.setLabel(grpName.toString());
                nodeDto.setName(rltDto.path);
                nodeDto.setType("category");
                nodeDto.setParent(null);
                nodeDto.setGrpCd(rltDto.crmAccountGroupCd);
                nodeDto.setParentCd(rltDto.parentCrmAccountGroupCd);
                treeNodeList.add(nodeDto);
            } else if (rltDto.depth == 1) {
                // 子グループ
                NodeDto nodeDto = new NodeDto();
                nodeDto.setLabel(grpName.toString());
                nodeDto.setName(rltDto.path);
                nodeDto.setGrpCd(rltDto.crmAccountGroupCd);
                nodeDto.setType("category");
                tmp = trimLastNode(rltDto.path, rltDto.crmAccountGroupCd);
                nodeDto.setParent(tmp);
                nodeDto.setParentCd(rltDto.parentCrmAccountGroupCd);
                treeNodeList.add(nodeDto);
            }
        }

        responseModel.treeNodeList = treeNodeList;

        return responseModel;
    }

    @Override
    public EntryInitializeResponseModel displayReverse(AcctGrpHierarchyDto dto) {

        EntryInitializeResponseModel responseModel =
            new EntryInitializeResponseModel();
        List<NodeDto> treeNodeList = new ArrayList<NodeDto>();

        // DB検索
        List<CrmCcAcctGrpIncAth> resultList =
            crmCcAcctGrpIncAthDao.findMoveGroup(dto.companyCd, null);

        String path = StringUtils.EMPTY;
        List<String> uniqPathList = new ArrayList<String>();

        // 処理するデータのみ抽出
        for (CrmCcAcctGrpIncAth rltDto : resultList) {
            path = rltDto.getPath();
            if (uniqPathList.contains(path)) {
                continue;
            }
            uniqPathList.add(path);
        }

        List<String> nodeList;
        List<String> rootList = new ArrayList<String>();

        String companyCd = this.getCompanyCd();
        String localeId = this.getLocaleId();
        String parent = StringUtils.EMPTY;
        String self = StringUtils.EMPTY;
        StringBuffer grpNameBuf = new StringBuffer();
        String grpName;

        // ツリーモデル作成
        for (String pa : uniqPathList) {
            nodeList = Arrays.asList(pa.split(PATH_SPLIT_CHAR));
            // コレクションを逆順にする
            Collections.reverse(nodeList);
            for (int i = 0; i < nodeList.size(); i++) {

                grpNameBuf = new StringBuffer();

                NodeDto nodeDto = new NodeDto();

                if (i <= 0) {
                    if (rootList.contains(nodeList.get(0))) {
                        continue;
                    }
                    rootList.add(nodeList.get(0));
                    nodeDto.setName(nodeList.get(i));
                    // 名称設定
                    nodeDto.setLabel(nodeList.get(i));
                    nodeDto.setType("category");
                } else {
                    // 名称設定
                    nodeDto.setLabel(nodeList.get(i));
                    nodeDto.setType("category");
                    self = createParent(nodeList, i);
                    nodeDto.setName(self);
                    parent = createParent(nodeList, i - 1);
                    nodeDto.setParent(parent);
                }
                // 名称設定
                grpName =
                    getAccountGroupName(companyCd, nodeList.get(i), localeId);
                grpNameBuf
                    .append("[")
                    .append(nodeList.get(i))
                    .append("] ")
                    .append(grpName);

                nodeDto.setLabel(grpNameBuf.toString());
                treeNodeList.add(nodeDto);
            }

        }
        responseModel.treeNodeList = treeNodeList;
        return responseModel;
    }

    /**
     * アカウントグループ名を返します。<br/>
     * 
     * @param 会社コード
     * @param ロケールID
     * @param アカウントグループコード
     * 
     * @return アカウントグループ名
     */
    private String getAccountGroupName(String companyCd,
            String crmAccountGroupCd, String localeId) {

        String accountGroupName = StringUtils.EMPTY;
        boolean deleteFlg = true;

        // アカウントグループ名を取得
        List<CrmCcAcctGrp> list =
            crmCcAcctGrpDao.findByPkIgnoreTerm(
                companyCd,
                crmAccountGroupCd,
                localeId);

        if (list != null && list.size() > 0) {
            CrmCcAcctGrp entity = list.get(0);
            accountGroupName = entity.getCrmAccountGroupName();
            deleteFlg = entity.isDeleteFlag();

        }

        if (accountGroupName == null || accountGroupName.length() <= 0) {
            accountGroupName = crmAccountGroupCd;
        }

        // 名称をセット
        if (deleteFlg) {
            // 非活動名称を追加
            accountGroupName = accountGroupName.concat(" (");
            accountGroupName =
                accountGroupName.concat(enumNamesRegistry.getName(DeleteFlg
                    .getEnum("1"), LocaleUtil.toLocale(localeId)));
            accountGroupName = accountGroupName.concat(")");
        }

        return accountGroupName;
    }

    @Override
    public void validateAppend(AcctGrpHierarchyDto dto) {

        String path = dto.path;
        String addCd = dto.grpCd;

        // 追加グループ設定有無チェック
        if (addCd == null || addCd.length() <= 0) {
            throwErrorMessage("E.CRM.CC.00109");
        }

        // 追加先階層設定有無チェック
        if (path == null || path.length() <= 0) {
            throwErrorMessage("E.CRM.CC.00110");
        }

        // 追加先階層上位に自分があるかチェック
        List<String> parentList = Arrays.asList(path.split(PATH_SPLIT_CHAR));
        if (parentList.contains(addCd)) {
            throwErrorMessage("E.CRM.CC.00111");
        }

        /** 追加先階層下位に自分があるかチェック **/

        // 階層一覧取得
        List<CrmCcAcctGrpIncAth> allList =
            crmCcAcctGrpIncAthDao.findMoveGroup(dto.companyCd, null);

        // 追加先階層配下の子たちのCD、パスをチェック
        for (CrmCcAcctGrpIncAth entity : allList) {
            // 階層パスと「追加先階層+#」が前方一致 且追加CD＝子CD
            // パス一致チェックは不要、存在しても「追加先階層+#」が前方一致
            if (entity.getPath().startsWith(path + PATH_SPLIT_CHAR)
                && addCd.equals(entity.getCrmAccountGroupCd())) {
                // 追加先階層配下に自分が存在
                throwErrorMessage("E.CRM.CC.00111");
            }
        }

    }

    @Override
    public void validateAppendTop(AcctGrpHierarchyDto dto) {

        String addCd = dto.grpCd;

        // 追加グループ設定有無チェック
        if (addCd == null || addCd.length() <= 0) {
            throwErrorMessage("E.CRM.CC.00109");
        }

        // 階層一覧取得
        List<CrmCcAcctGrpIncAth> allList =
            crmCcAcctGrpIncAthDao.findMoveGroup(dto.companyCd, addCd);

        // 追加される階層に自分がある
        if (allList != null && allList.size() > 0) {
            throwErrorMessage("E.CRM.CC.00111");
        }
    }

    @Override
    public void validateDelete(AcctGrpHierarchyDto dto) {

        String path = dto.path;

        // 削除する階層パス設定有無チェック
        if (path == null || path.length() <= 0) {
            throwErrorMessage("E.CRM.CC.00112");
        }
    }

    @Override
    public void validateMove(AcctGrpHierarchyDto dto) {
        String path = dto.path;
        String targetPath = dto.targetPath;

        // 移動するグループ
        if (path == null || path.length() <= 0) {
            throwErrorMessage("E.CRM.CC.00106");
        }
        // 移動先グループ
        if (targetPath == null || targetPath.length() <= 0) {
            throwErrorMessage("E.CRM.CC.00107");
        }

        // 検索ALL
        List<AcctGrpHierarchyDto> resultList =
            crmCcAcctGrpIncAthDao.findAllByCriteria(dto);

        // 親CD、自CD
        String[] grpCds = path.split(PATH_SPLIT_CHAR);
        // 自CD
        String selfCd = grpCds[grpCds.length - 1];

        // 移動先親CDS
        String[] tarGrpCds = targetPath.split(PATH_SPLIT_CHAR);

        String childCd = StringUtils.EMPTY;
        String path2 = path + PATH_SPLIT_CHAR;

        // 親子階層チェック
        for (AcctGrpHierarchyDto rltDto : resultList) {
            // 移動階層最上位グループが親になっている場合
            if (rltDto.parentCrmAccountGroupCd.equals(selfCd)
                && (rltDto.path.startsWith(path) || rltDto.path
                    .startsWith(path2))) {

                childCd = rltDto.crmAccountGroupCd;

                // 移動階層最上位グループが親になっている場合
                // 移動グループ、且つ配下の子グループが
                // 移動先で親グループになっていないかチェック
                for (int i = 0; i < tarGrpCds.length; i++) {
                    if (childCd.equals(tarGrpCds[i])) {
                        throwErrorMessage("E.CRM.CC.00108");
                    }
                }
            }
        }
    }

    @Override
    public void move(AcctGrpHierarchyDto dto) {
        String path = dto.path;
        String targetPath = dto.targetPath;

        String[] cds = path.split(PATH_SPLIT_CHAR);
        String selfCd = cds[cds.length - 1]; // 自CD

        // 親パス(自分を削除)
        String oldParentPath = trimLastNode(path, selfCd);
        oldParentPath = StringUtils.stripEnd(oldParentPath, PATH_SPLIT_CHAR);

        // 親CDS
        List<String> parentCdLst = new ArrayList<String>();
        for (int i = 0; i < cds.length - 1; i++) {
            parentCdLst.add(cds[i]);
        }

        // 移動先親CDS
        List<String> targetCdLst =
            Arrays.asList(targetPath.split(PATH_SPLIT_CHAR));

        String childCd = StringUtils.EMPTY;
        String childPath = StringUtils.EMPTY;

        // 階層一覧検索
        List<CrmCcAcctGrpIncAth> allList =
            crmCcAcctGrpIncAthDao.findMoveGroup(dto.companyCd, null);

        // 子リスト
        List<String> childList = new ArrayList<String>();
        // マップ｛CD：パス｝
        Map<String, String> mapCdPath = new HashMap<String, String>();
        String tmpPath = StringUtils.EMPTY;
        String path2 = path + PATH_SPLIT_CHAR;

        // 移動される階層TOP（自分）、及び配下の子たちのCD、パスを取得
        for (CrmCcAcctGrpIncAth entity : allList) {
            // 親が自分
            if (entity.getParentCrmAccountGroupCd().equals(selfCd)) {
                tmpPath = entity.getPath();
                // 自分パスが一致OR「自分パス+#」が前方一致
                if (tmpPath.equals(path) || tmpPath.startsWith(path2)) {
                    childCd = entity.getCrmAccountGroupCd();
                    childPath = entity.getPath();
                    mapCdPath.put(childCd, childPath);
                    childList.add(childCd);
                }
            }
        }

        /**
         * 移動するグループ元階層削除
         * */
        for (String key : childList) {
            for (CrmCcAcctGrpIncAth entity : allList) {
                // 子CD、パスが一致すると削除
                if (entity.getCrmAccountGroupCd().equals(key)
                    && entity.getPath().equals(mapCdPath.get(key))) {
                    // 物理削除
                    // crmCcAcctGrpIncAthDao.delete(entity);
                    deleteCcAcctGrpIncAth(entity);
                }
            }
        }

        // 移動グループ階層情報
        List<CrmCcAcctGrpIncAth> moveDtoListOld =
            new ArrayList<CrmCcAcctGrpIncAth>();
        String chldPath = StringUtils.EMPTY;

        // 移動グループCD、末端CDまで取得する。
        for (String key : childList) {
            chldPath = mapCdPath.get(key);
            // 子の子の子・・・末端CDまで取得
            for (CrmCcAcctGrpIncAth entity : allList) {
                // 子が子を持っている場合
                // 移動グループCDが親になっている場合且パスが前方一致
                if (entity.getParentCrmAccountGroupCd().equals(key)
                    && entity.getPath().startsWith(chldPath)) {
                    moveDtoListOld.add(entity);
                }
            }
        }

        // 移動グループ階層情報を編集（パス情報を編集）
        List<CrmCcAcctGrpIncAth> moveEntityListUp =
            new ArrayList<CrmCcAcctGrpIncAth>();
        String newPath = StringUtils.EMPTY;
        for (CrmCcAcctGrpIncAth entity : moveDtoListOld) {
            // パス情報を編集
            newPath = entity.getPath();
            if (StringUtils.isEmpty(oldParentPath)) {
                newPath = targetPath + PATH_SPLIT_CHAR + newPath;
            } else {
                newPath = newPath.replaceFirst(oldParentPath, targetPath);
            }
            entity.setPath(newPath);
            entity.setRootFlag(false);
            moveEntityListUp.add(entity);

        }

        // 移動グループ階層情報を編集（新規親を追加）
        List<CrmCcAcctGrpIncAth> entityList =
            new ArrayList<CrmCcAcctGrpIncAth>();
        int parentIndex = 0;
        int selfIndex = 0;

        // 新エンティティを作成
        for (String targetCd : targetCdLst) {
            Set<String> keySet = mapCdPath.keySet();
            Iterator<String> keyIte = keySet.iterator();
            while (keyIte.hasNext()) {
                String key = keyIte.next();
                String value = mapCdPath.get(key);

                // パスを作成
                if (StringUtils.isEmpty(oldParentPath)) {
                    value = targetPath + PATH_SPLIT_CHAR + value;
                } else {
                    value = value.replaceFirst(oldParentPath, targetPath);
                }

                parentIndex = 0;
                selfIndex = 0;

                // 階層深さを作成
                String[] temCds = value.split(PATH_SPLIT_CHAR);
                for (int i = 0; i < temCds.length; i++) {
                    if (targetCd.equals(temCds[i])) {
                        parentIndex = i;
                    }
                    if (key.equals(temCds[i])) {
                        selfIndex = i;
                    }
                }

                // 新エンティティを作成
                CrmCcAcctGrpIncAth entity = new CrmCcAcctGrpIncAth();
                entity.setCompanyCd(dto.companyCd);
                entity.setParentCrmAccountGroupCd(targetCd);
                entity.setCrmAccountGroupCd(key);
                entity.setPath(value);
                entity.setDepth(selfIndex - parentIndex);
                entity.setCreateDate(DateUtil.nowDate());
                entity.setStartDate(bizConfigurationProvider.getStartDate());
                entity.setEndDate(bizConfigurationProvider.getEndDate());
                entity.setDeleteFlag(false);
                entity.setCreateUserCd(contextContainer
                    .getUserContext()
                    .getUserID());
                entity.setRootFlag(false);
                entity.setSortKey(1L);
                entityList.add(entity);
            }
        }

        /**
         * グループ移動する
         * */
        for (CrmCcAcctGrpIncAth entity : moveEntityListUp) {
            CrmCcAcctGrpIncAth e =
                crmCcAcctGrpIncAthDao.getEntity(entity.getCompanyCd(), entity
                    .getParentCrmAccountGroupCd(), entity
                    .getCrmAccountGroupCd(), entity.getPath(), null);
            if (e == null) {
                crmCcAcctGrpIncAthDao.insert(entity);
            } else {
                e.setDeleteFlag(false);
                crmCcAcctGrpIncAthDao.update(e);
            }
        }

        for (CrmCcAcctGrpIncAth entity : entityList) {
            CrmCcAcctGrpIncAth e =
                crmCcAcctGrpIncAthDao.getEntity(entity.getCompanyCd(), entity
                    .getParentCrmAccountGroupCd(), entity
                    .getCrmAccountGroupCd(), entity.getPath(), null);
            if (e == null) {
                crmCcAcctGrpIncAthDao.insert(entity);
            } else {
                e.setDeleteFlag(false);
                crmCcAcctGrpIncAthDao.update(e);
            }
        }
    }

    @Override
    public void delete(AcctGrpHierarchyDto dto) {

        String path = dto.path;

        // 階層一覧検索
        List<CrmCcAcctGrpIncAth> grpLst =
            crmCcAcctGrpIncAthDao.findMoveGroup(dto.companyCd, null);

        // 階層削除
        String tmpPath = StringUtils.EMPTY;
        for (CrmCcAcctGrpIncAth entity : grpLst) {
            tmpPath = entity.getPath();
            // 階層パスが一致するのを削除
            if (tmpPath.equals(path)
                || tmpPath.startsWith(path + PATH_SPLIT_CHAR)) {
                // 削除フラグ立てる
                entity.setDeleteFlag(true);
                crmCcAcctGrpIncAthDao.update(entity);
            }
        }
    }

    @Override
    public void appendAcctGrp(AcctGrpHierarchyDto dto) {
        String path = dto.path;
        String addCd = dto.grpCd;

        String[] parentCds = path.split(PATH_SPLIT_CHAR);

        // 最下位親CDの配下の子たち一覧取得
        // 別階層の子たちも取得される。

        // 追加先階層配下の子たちのENTITY取得(自分が含まれない)
        // 別階層の子たちを除外

        // 追加先階層配下の子たちのパス編集：
        String oldParentPath = path + PATH_SPLIT_CHAR;
        String newPath = StringUtils.EMPTY;

        // 親たちに自分を登録する
        CrmCcAcctGrpIncAth entity = new CrmCcAcctGrpIncAth();
        newPath = oldParentPath + addCd;

        // 親たちに自分を追加する
        for (int i = 0; i < parentCds.length; i++) {
            entity = entryMapper.entityMapper(entity);
            entity.setParentCrmAccountGroupCd(parentCds[i]);
            entity.setCrmAccountGroupCd(addCd);
            entity.setPath(newPath);
            entity.setDepth(i + 1);

            // 検索
            CrmCcAcctGrpIncAth e =
                crmCcAcctGrpIncAthDao.getEntity(
                    getCompanyCd(),
                    parentCds[i],
                    addCd,
                    newPath,
                    null);
            // 登録実行
            if (e == null) {
                crmCcAcctGrpIncAthDao.insert(entity);
            } else {
                e.setDeleteFlag(false);
                e.setDepth(i + 1);
                crmCcAcctGrpIncAthDao.update(e);
            }
        }

        // 自分を追加する
        entity = entryMapper.entityMapper(entity);
        entity.setParentCrmAccountGroupCd(addCd);
        entity.setCrmAccountGroupCd(addCd);
        entity.setPath(newPath);
        entity.setDepth(0);

        // 検索
        CrmCcAcctGrpIncAth e =
            crmCcAcctGrpIncAthDao.getEntity(
                getCompanyCd(),
                addCd,
                addCd,
                newPath,
                null);
        // 登録実行
        if (e == null) {
            crmCcAcctGrpIncAthDao.insert(entity);
        } else {
            e.setDeleteFlag(false);
            crmCcAcctGrpIncAthDao.update(e);
        }
    }

    @Override
    public void appendAcctGrpRoot(AcctGrpHierarchyDto dto) {

        String addCd = dto.grpCd;
        if (StringUtils.isEmpty(addCd)) {
            throwErrorMessage("E.CRM.CC.00109");
        }

        // 検索
        CrmCcAcctGrpIncAth e =
            crmCcAcctGrpIncAthDao.getEntity(
                getCompanyCd(),
                addCd,
                addCd,
                addCd,
                null);
        // 登録実行
        if (e == null) {
            e = new CrmCcAcctGrpIncAth();
            e.setCompanyCd(getCompanyCd());
            e.setParentCrmAccountGroupCd(addCd);
            e.setCrmAccountGroupCd(addCd);
            e.setPath(addCd);
            e.setDepth(0);
            e.setRootFlag(true);
            e.setStartDate(bizConfigurationProvider.getStartDate());
            e.setEndDate(bizConfigurationProvider.getEndDate());

            crmCcAcctGrpIncAthDao.insert(e);
        } else {
            if (!e.isDeleteFlag()) {
                this.throwErrorMessage("E.CRM.CC.00111");
            }
            e.setDeleteFlag(false);
            e.setRootFlag(true);
            e.setDepth(0);
            crmCcAcctGrpIncAthDao.update(e);
        }
    }

    @Override
    public void appendTop(AcctGrpHierarchyDto dto) {

        String topCd = dto.grpCd;

        // 階層一覧検索
        List<CrmCcAcctGrpIncAth> allList =
            crmCcAcctGrpIncAthDao.findMoveGroup(dto.companyCd, null);

        // 配下の子たちのパス編集：
        String topPath = topCd + PATH_SPLIT_CHAR;

        // 追加先階層配下の子グループパスを変更
        for (CrmCcAcctGrpIncAth entity : allList) {

            entity.setDeleteFlag(true);
            crmCcAcctGrpIncAthDao.delete(entity);

            entity.setPath(topPath + entity.getPath()); // パス
            entity.setRootFlag(false);
            entity.setDeleteFlag(false);

            // 配下の子グループ階層を更新する
            crmCcAcctGrpIncAthDao.insertOrUpdate(entity);
        }

        CrmCcAcctGrpIncAth entity = new CrmCcAcctGrpIncAth();

        // 自分を追加する
        CrmCcAcctGrpIncAth e =
            crmCcAcctGrpIncAthDao.getEntity(
                this.getCompanyCd(),
                topCd,
                topCd,
                topCd,
                null);
        if (e == null) {
            entity = entryMapper.entityMapper(entity);
            entity.setParentCrmAccountGroupCd(topCd);
            entity.setCrmAccountGroupCd(topCd);
            entity.setPath(topCd);
            entity.setRootFlag(true);
            entity.setDepth(0);
            // 登録実行
            crmCcAcctGrpIncAthDao.insert(entity);
        } else {
            e.setDepth(0);
            e.setRootFlag(true);
            e.setDeleteFlag(false);
            // 登録実行
            crmCcAcctGrpIncAthDao.update(e);
        }

        // 自分に子グループを登録準備
        String[] temCds;
        String childPath = StringUtils.EMPTY;
        int childIndex = 0;
        List<String> insertLst = new ArrayList<String>();

        // 自分に子グループを登録する
        for (CrmCcAcctGrpIncAth entt : allList) {

            // 重複を除く
            childPath = entt.getPath();
            if (insertLst.contains(childPath)) {
                continue;
            }

            // 同じパス文字列を新作成して格納
            insertLst.add(new String(childPath));

            // 登録するEntity
            entt = entryMapper.entityMapper(entt);
            entt.setParentCrmAccountGroupCd(topCd);

            // パスを作成不要

            // 階層深さを作成
            temCds = entt.getPath().split(PATH_SPLIT_CHAR);
            for (int i = 0; i < temCds.length; i++) {
                if (entt.getCrmAccountGroupCd().equals(temCds[i])) {
                    childIndex = i;
                    break;
                }
            }
            entt.setDepth(childIndex);

            e =
                crmCcAcctGrpIncAthDao.getEntity(
                    entt.getCompanyCd(),
                    topCd,
                    entt.getCrmAccountGroupCd(),
                    entt.getCrmAccountGroupCd(),
                    null);
            if (e == null) {
                // 登録実行
                crmCcAcctGrpIncAthDao.insert(entt);
            } else {
                e.setDeleteFlag(false);
                crmCcAcctGrpIncAthDao.update(e);
            }
        }
    }

    @Override
    public void moveToRoot(AcctGrpHierarchyDto dto) {
        String path = dto.path;

        String[] cds = path.split(PATH_SPLIT_CHAR);
        String selfCd = cds[cds.length - 1]; // 自CD

        // 親パス(自分を削除)
        String oldParentPath = trimLastNode(path, selfCd);

        String childCd = StringUtils.EMPTY;
        String childPath = StringUtils.EMPTY;

        // 階層一覧検索
        List<CrmCcAcctGrpIncAth> allList =
            crmCcAcctGrpIncAthDao.findMoveGroup(dto.companyCd, null);

        // 子リスト
        List<String> childList = new ArrayList<String>();
        // マップ｛CD：パス｝
        Map<String, String> mapCdPath = new HashMap<String, String>();
        String tmpPath = StringUtils.EMPTY;
        String path2 = path + PATH_SPLIT_CHAR;

        // 移動される階層TOP（自分）、及び配下の子たちのCD、パスを取得
        for (CrmCcAcctGrpIncAth entity : allList) {
            // 親が自分
            if (entity.getParentCrmAccountGroupCd().equals(selfCd)) {
                tmpPath = entity.getPath();
                // 自分パスが一致OR「自分パス+#」が前方一致
                if (tmpPath.equals(path) || tmpPath.startsWith(path2)) {
                    childCd = entity.getCrmAccountGroupCd();
                    childPath = entity.getPath();
                    mapCdPath.put(childCd, childPath);
                    childList.add(childCd);
                }
            }
        }

        /**
         * 移動するグループ元階層削除
         * */
        for (String key : childList) {
            for (CrmCcAcctGrpIncAth entity : allList) {
                // 子CD、パスが一致すると削除
                if (entity.getCrmAccountGroupCd().equals(key)
                    && entity.getPath().equals(mapCdPath.get(key))) {
                    // 物理削除
                    crmCcAcctGrpIncAthDao.delete(entity);
                }
            }
        }

        // 移動グループ階層情報
        List<CrmCcAcctGrpIncAth> moveDtoListOld =
            new ArrayList<CrmCcAcctGrpIncAth>();
        String chldPath = StringUtils.EMPTY;

        // 移動グループCD、末端CDまで取得する。
        for (String key : childList) {
            chldPath = mapCdPath.get(key);
            // 子の子の子・・・末端CDまで取得
            for (CrmCcAcctGrpIncAth entity : allList) {
                // 子が子を持っている場合
                // 移動グループCDが親になっている場合且パスが前方一致
                if (entity.getParentCrmAccountGroupCd().equals(key)
                    && entity.getPath().startsWith(chldPath)) {

                    moveDtoListOld.add(entity);
                }
            }
        }

        // 移動グループ階層情報を編集（パス情報を編集）
        String newPath = StringUtils.EMPTY;
        for (CrmCcAcctGrpIncAth entity : moveDtoListOld) {
            // パス情報を編集
            newPath = entity.getPath();
            newPath = newPath.replaceFirst(oldParentPath, StringUtils.EMPTY);
            newPath = newPath.replaceFirst(PATH_SPLIT_CHAR, StringUtils.EMPTY);
            entity.setPath(newPath);

            String parCd = entity.getParentCrmAccountGroupCd();
            String chidCd = entity.getCrmAccountGroupCd();
            int parentIndex = 0;
            int selfIndex = 0;

            // 階層深さを作成
            String[] temCds = newPath.split(PATH_SPLIT_CHAR);
            for (int i = 0; i < temCds.length; i++) {
                if (parCd.equals(temCds[i])) {
                    parentIndex = i;
                }
                if (chidCd.equals(temCds[i])) {
                    selfIndex = i;
                }
            }
            entity.setDepth(selfIndex - parentIndex);

            // 自分にルートフラグを立てる
            if (entity.getCrmAccountGroupCd().equals(selfCd)) {
                entity.setRootFlag(true);
            }
            // 実行
            crmCcAcctGrpIncAthDao.insertOrUpdate(entity);
        }
    }

    @Override
    public void validateMoveToRoot(AcctGrpHierarchyDto dto) {
        String path = dto.path;
        String[] cds = path.split(PATH_SPLIT_CHAR);
        String selfCd = cds[cds.length - 1]; // 自CD

        // 移動するグループ
        if (path == null || path.length() <= 0) {
            throwErrorMessage("E.CRM.CC.00106");
        }

        // 階層一覧検索
        List<CrmCcAcctGrpIncAth> allList =
            crmCcAcctGrpIncAthDao.findMoveGroup(dto.companyCd, selfCd);

        // 自分がすでにルート直下にいるかチェック
        for (CrmCcAcctGrpIncAth entity : allList) {
            // 移動階層最上位グループが親になっている場合
            if (entity.getParentCrmAccountGroupCd().equals(selfCd)
                && entity.isRootFlag()) {
                throwErrorMessage("E.CRM.CC.00108");
            }
        }
    }

    @Override
    public FileDownloadResponseModel exportFile(AcctGrpHierarchyDto createDto,
            String registerNo) {

        // アカウントグループ階層一覧検索
        List<AcctGrpHierarchyDto> allList =
            crmCcAcctGrpIncAthDao.findAllByCriteria(createDto);

        List<AcctGrpHierarchyDto> exportDtoList =
            new ArrayList<AcctGrpHierarchyDto>();
        List<String> pathList = new ArrayList<String>();
        String path = StringUtils.EMPTY;

        // 重複を除く
        for (AcctGrpHierarchyDto dto : allList) {
            path = dto.path;
            if (pathList.contains(path)) {
                continue;
            }
            pathList.add(path);

            // 名称取得
            if (dto.crmAccountGroupName == null
                || dto.crmAccountGroupName.length() <= 0) {
                dto.crmAccountGroupName =
                    getAccountGroupName(
                        createDto.companyCd,
                        dto.crmAccountGroupCd,
                        createDto.localeId);
            }
            exportDtoList.add(dto);
        }

        FileDownloadResponseModel responseModel =
            new FileDownloadResponseModel();

        if (exportDtoList != null && !exportDtoList.isEmpty()) {

            // 取得した一覧をファイルに出力します。
            SharedFile sharedFile =
                FileUtil.createSharedFile(
                    createDto.fileName,
                    registerNo,
                    contextContainer);

            CSVWriter<AcctGrpHierarchyDto> writer =
                new CSVWriter<AcctGrpHierarchyDto>(
                    makeCSVWriterPreferences(sharedFile));
            writer.write(exportDtoList);

            // 作成したファイルのダウンロードに必要な情報を
            // 共有ストレージサービスを使用して作成します。
            SharedFile downLoadSharedFile =
                sharedStorageManager.register(sharedFile, writer
                    .getOutputAsFile());

            writer.close();

            responseModel
                .setStoragePath(FileUtil.createKey(downLoadSharedFile));
            responseModel.setFileNameForDownload(createDto.fileName);
        }

        return responseModel;
    }

    /**
     * 設定された値がCSVファイルに出力します。
     * 
     * @param sharedFile
     *            ファイル
     * @return CSVファイル
     */
    private CSVWriterPreferences makeCSVWriterPreferences(SharedFile sharedFile) {
        // ヘッダを追加します。
        UseType isOutHeader = (UseType) parameterLogic.getEntity("CRMCC0001");

        CSVWriterPreferences preferences =
            FileUtil.newCSVWriterPreferences(
                applicationConfigurationRegistry,
                sharedFile,
                (UseType.USE == isOutHeader));
        if (UseType.USE == isOutHeader) {
            String[] headerNames =
                { "CRM.CC.crmAccountGroupCd", "CRM.CC.hierarchy" };
            FileUtil.putHeaderNamesMap(headerNames, preferences);
        }
        int dataIndex = 0;
        preferences.addMapping(dataIndex++, "crmAccountGroupCd");
        preferences.addMapping(dataIndex++, "path");
        return preferences;
    }

    @Override
    public String asyncRegister(AcctGrpHierarchyDto dto) {
        Request request = new Request();
        request.setAsyncTaskConfigCd("crm.cc.acctGrpHierarchy.entry.export");

        request.setCompanyCd(contextContainer
            .getApplicationContext()
            .getFeatureContext()
            .getCompanyCode());
        request.setParameterBean(dto);
        request.setComment("");
        request.setExecutePriorityType(ExecutePriorityType.MIDDLE);
        return asyncTaskClient.register(request);
    }

    @Override
    public void importFile(InputStream inputStream) {

        // 件数チェックします。
        try {
            if (inputStream == null || inputStream.read() <= 0) {
                throwErrorMessage("E.CRM.CC.00026");
            }
        } catch (IOException e) {
            throwErrorMessage("E.CRM.CC.00026");
        }

        // 入力ストリームからentityリスト作成
        List<CrmCcAcctGrpIncAth> entityList =
            getDataListFromImpFile(inputStream);

        // 会社コード
        String companyCd =
            contextContainer.getCurrentFeatureContext().getCompanyCode();

        // アカウントグループ階層一覧検索
        List<CrmCcAcctGrpIncAth> allList =
            crmCcAcctGrpIncAthDao.findMoveGroup(companyCd, null);

        String path = StringUtils.EMPTY;
        boolean isExistPath = false;

        // チェック, 登録実行
        for (CrmCcAcctGrpIncAth entity : entityList) {

            // アカウントグループCDチェック
            checkCrmAccountGroupCd(entity.getCrmAccountGroupCd());

            // パスチェック
            checkPath(entity.getPath());

            // ルートである
            if (entity.getPath().equals(entity.getCrmAccountGroupCd())) {
                // 登録
                addCrmCcAcctGrpIncAth(entity);
                continue;
            }

            // パスから自分を削除
            path =
                trimLastNode(entity.getPath(), entity.getCrmAccountGroupCd());

            // DBに親パスが存在するかチェック
            isExistPath = false;
            for (CrmCcAcctGrpIncAth dbEntity : allList) {
                if (path.equals(dbEntity.getPath())) {
                    isExistPath = true;
                    break;
                }
            }

            // DBに親パスが存在する。OK
            if (isExistPath) {
                // 登録
                addCrmCcAcctGrpIncAth(entity);
                continue;
            }

            // DBに親パスが無いのでImportデータにあるかチェック
            for (CrmCcAcctGrpIncAth entity2 : entityList) {
                if (path.equals(entity2.getPath())) {
                    isExistPath = true;
                    break;
                }
            }

            // 存在しないパス。NG
            if (!isExistPath) {
                throwErrorMessage("E.CRM.CC.00114");
            }

            // 登録
            addCrmCcAcctGrpIncAth(entity);
        }
    }

    /**
     * 登録処理
     * 
     * @param CrmCcAcctGrpIncAth
     *            entity
     * 
     * @return なし
     */
    private void addCrmCcAcctGrpIncAth(CrmCcAcctGrpIncAth entity) {

        String path = entity.getPath();
        String selfCd = entity.getCrmAccountGroupCd();

        List<String> nodeList = Arrays.asList(path.split(PATH_SPLIT_CHAR));
        int selfIndex = nodeList.size();

        // 会社コード
        String companyCd = getCompanyCd();
        String deletedFlg = null;

        if (selfIndex == 1) {
            // ルート階層

            // DB検索
            CrmCcAcctGrpIncAth e =
                crmCcAcctGrpIncAthDao.getEntity(
                    companyCd,
                    selfCd,
                    selfCd,
                    path,
                    deletedFlg);

            if (e == null) {
                // DBに存在しない：insert
                entity = entryMapper.entityMapper(entity);
                entity.setParentCrmAccountGroupCd(selfCd);
                entity.setRootFlag(true);
                entity.setDepth(0);
                // 登録
                crmCcAcctGrpIncAthDao.insertOrUpdateWithLock(entity);
            } else {
                // DBに存在する：update
                if (e.isDeleteFlag()) {
                    e.setDeleteFlag(false);
                    e.setRootFlag(true);
                    e.setDepth(0);
                    // 登録
                    crmCcAcctGrpIncAthDao.update(e);
                }
            }

        } else {
            // 子階層：親に自分を登録
            for (int i = 0; i < nodeList.size() - 1; i++) {
                // DB検索
                CrmCcAcctGrpIncAth e =
                    crmCcAcctGrpIncAthDao.getEntity(
                        companyCd,
                        nodeList.get(i),
                        selfCd,
                        path,
                        deletedFlg);

                if (e == null) {
                    // DBに存在しない：insert
                    entity = entryMapper.entityMapper(entity);
                    entity.setParentCrmAccountGroupCd(nodeList.get(i));
                    entity.setDepth(selfIndex - i - 1);
                    // 登録
                    crmCcAcctGrpIncAthDao.insertOrUpdateWithLock(entity);
                } else {
                    // DBに存在する：update
                    if (e.isDeleteFlag()) {
                        e.setParentCrmAccountGroupCd(nodeList.get(i));
                        e.setDepth(selfIndex - i - 1);
                        e.setDeleteFlag(false);
                        // 登録
                        crmCcAcctGrpIncAthDao.update(e);
                    }
                }
            }

            // 自分を登録
            // DB検索
            CrmCcAcctGrpIncAth e =
                crmCcAcctGrpIncAthDao.getEntity(
                    companyCd,
                    selfCd,
                    selfCd,
                    path,
                    deletedFlg);

            if (e == null) {
                // DBに存在しない：insert
                entity = entryMapper.entityMapper(entity);
                entity.setParentCrmAccountGroupCd(selfCd);
                entity.setDepth(0);
                // 登録
                crmCcAcctGrpIncAthDao.insertOrUpdateWithLock(entity);
            } else {
                // DBに存在する：update
                if (e.isDeleteFlag()) {
                    e.setParentCrmAccountGroupCd(selfCd);
                    e.setDepth(0);
                    e.setDeleteFlag(false);
                    // 登録
                    crmCcAcctGrpIncAthDao.update(e);
                }
            }
        }
    }

    /**
     * 入力ファイルからデータリスト作成
     * 
     * @param InputStream
     *            inputStream：インポートファイルのストリーム
     * 
     * @return List<CrmCcAcctGrpIncAth>
     */
    private List<CrmCcAcctGrpIncAth> getDataListFromImpFile(
            InputStream inputStream) {
        // 入力ストリームのドメインチェックを行う。
        CSVReaderPreferences preferences = FileUtil.newCSVReaderPreferences();
        int readerIndex = 0;

        // アカウントグループコード取得
        preferences.addMapping(
            readerIndex++,
            "crmAccountGroupCd",
            ColumnType.STRING);
        // 階層情報取得
        preferences.addMapping(readerIndex++, "path", ColumnType.STRING);

        CSVReader<CrmCcAcctGrpIncAth> reader =
            new CSVReader<CrmCcAcctGrpIncAth>(
                CrmCcAcctGrpIncAth.class,
                preferences,
                inputStream);

        List<CrmCcAcctGrpIncAth> entityList = null;
        try {
            entityList = reader.read();
            if (entityList == null || entityList.isEmpty()) {
                throwErrorMessage("E.CRM.CC.00026");
            }
        } catch (Exception e) {
            throwErrorMessage("E.CRM.CC.00026");
        }

        return entityList;
    }

    /**
     * 指定アカウントグループ階層パスの最後から指定アカウントグループコード削除
     * 
     * @param String
     *            path：アカウントグループ階層パス
     * @param String
     *            cd：アカウントグループコード
     * 
     * @return パス
     */
    private String trimLastNode(String path, String cd) {

        if (StringUtils.isEmpty(path)
            || StringUtils.isEmpty(cd)
            || path.equals(cd)) {
            return StringUtils.EMPTY;
        }

        int cdLen = cd.length();
        int pathLen = path.length();

        // 長さチェック
        if (pathLen < cdLen + 1) {
            throwErrorMessage("E.CRM.CC.00114");
        }

        // 自分を削除、自分が無ければエラー
        String lastPath = StringUtils.right(path, cdLen + 1);
        if ((PATH_SPLIT_CHAR + cd).equals(lastPath)) {
            path = StringUtils.left(path, pathLen - cdLen - 1);
        } else {
            throwErrorMessage("E.CRM.CC.00114");
        }

        return path;
    }

    /**
     * アカウントグループコードが有効かチェック
     * 
     * @param String
     *            cd：アカウントグループコード
     * 
     * @return なし
     */
    private void checkCrmAccountGroupCd(String cd) {

        if (StringUtils.isEmpty(cd)) {
            throwErrorMessage("E.CRM.CC.00113");
        }

        // 会社コード
        String companyCd =
            contextContainer.getCurrentFeatureContext().getCompanyCode();
        // ロケールID
        String localeId = contextContainer.getUserContext().getLocaleID();

        // アカウントグループ存在チェック
        if (crmCcAcctGrpDao.getCountByGroupCd(companyCd, localeId, cd) <= 0) {
            throwErrorMessage("E.CRM.CC.00113");
        }
    }

    /**
     * アカウントグループパスチェック
     * 
     * @param String
     *            path：アカウントグループパス
     * 
     * @return なし
     */
    private void checkPath(String path) {
        // 空チェック
        if (StringUtils.isEmpty(path) || path.length() > 1000) {
            throwErrorMessage("E.CRM.CC.00114");
        }

        // アカウントグループ階層パスにコードが重複しているかチェック
        List<String> cdLst = Arrays.asList(path.split(PATH_SPLIT_CHAR));
        List<String> tmpLst = new ArrayList<String>();
        for (String cd : cdLst) {
            if (tmpLst.contains(cd)) {
                // 重複している
                throwErrorMessage("E.CRM.CC.00114");
            }
            tmpLst.add(cd);
        }
    }

    private static String createParent(List<String> nodeList, int index) {
        StringBuffer parent = new StringBuffer();

        for (int i = 0; i <= index; i++) {
            parent.append(nodeList.get(i));
            if (i != index) {
                parent.append(PATH_SPLIT_CHAR);
            }
        }
        return parent.toString();
    }

    /**
     * 会社コードを取得します。
     * 
     * @param String
     * 
     */
    private String getCompanyCd() {
        return contextContainer
            .getApplicationContext()
            .getFeatureContext()
            .getCompanyCode();
    }

    /**
     * ロケールIDを取得します。
     * 
     * @param String
     * 
     */
    private String getLocaleId() {
        return contextContainer.getUserContext().getLocaleID();
    }

    /**
     * エラーメッセージをスローする。
     * 
     * @param String
     *            messageId：メッセージID
     * 
     * @return なし
     */
    private void throwErrorMessage(String messageId) {
        ValidationResults validationResults = new ValidationResults();
        validationResults.add(new ValidationResult(MessageBuilder.create(
            messageId).toMessage()));
        throw new ValidationException(validationResults);
    }

    private void deleteCcAcctGrpIncAth(CrmCcAcctGrpIncAth e) {
        String companyCd = e.getCompanyCd();
        String parent_crm_account_group_cd = e.getParentCrmAccountGroupCd();
        String crm_account_group_cd = e.getCrmAccountGroupCd();
        String path = e.getPath();
        String deletedFlg = null;
        e =
            crmCcAcctGrpIncAthDao.getEntity(
                companyCd,
                parent_crm_account_group_cd,
                crm_account_group_cd,
                path,
                deletedFlg);
        if (e != null) {
            crmCcAcctGrpIncAthDao.delete(e);
        }
    }
}
