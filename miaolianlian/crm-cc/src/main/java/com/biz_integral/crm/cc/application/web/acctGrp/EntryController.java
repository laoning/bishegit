/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.acctGrp;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.biz_integral.crm.cc.domain.dto.AcctGrpDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAcctGrp;
import com.biz_integral.crm.cc.domain.entity.CrmCcAcctGrpChargeDept;
import com.biz_integral.crm.cc.domain.entity.CrmCcAcctGrpChargeUser;
import com.biz_integral.crm.cc.domain.logic.CrmAcctGrpLogic;
import com.biz_integral.crm.cc.domain.logic.ParameterLogic;
import com.biz_integral.extension.mvc.maskat.annotaion.Execute;
import com.biz_integral.extension.mvc.maskat.annotaion.Validation;
import com.biz_integral.extension.mvc.maskat.beans.KeyValueBean;
import com.biz_integral.extension.mvc.maskat.beans.PagingResponse;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.util.CollectionsUtil;
import com.biz_integral.service.domain.master.locale.BizLocaleInfo;
import com.biz_integral.service.domain.master.locale.BizLocaleManager;

/**
 * アカウントグループ登録／更新画面のコントローラークラスです。<br/>
 */
public class EntryController {

    /**
     * パラメータロジック
     */
    @Resource
    protected ParameterLogic parameterLogic;

    /**
     * パラメータロジック
     */
    @Resource
    protected CrmAcctGrpLogic crmAcctGrpLogic;

    /**
     * {@link ContextContainer コンテキストコンテナー}
     */
    @Resource
    protected ContextContainer contextContainer;

    /**
     * {@link BizLocaleManager ロケールマネージャー}
     */
    @Resource
    protected BizLocaleManager bizLocaleManager;

    /**
     * {@link EntryMapper マッパー}
     */
    @Resource
    protected EntryMapper entryMapper;

    /**
     * 画面の初期化時に利用する情報を取得し、返します。<br/>
     * <p>
     * <ul>
     * <li>アカウントグループを取得します。</li>
     * </ul>
     * </p>
     * 
     * @param requestModel
     *            画面の初期化時に利用するリクエストモデル
     * @return アカウントグループ情報をセットしたレスポンスモデル
     */
    @Execute
    @Validation
    public EntryResponseModel initialize(EntryInitializeRequestModel reqMdl) {

        EntryResponseModel respMdl = new EntryResponseModel();

        // ロケールIDの一覧を取得します。
        List<KeyValueBean> localeIdList = new ArrayList<KeyValueBean>();
        localeIdList.addAll(getLocaleForComboList());
        respMdl.localeIdList = localeIdList;

        // 登録モード
        if (StringUtils.isEmpty(reqMdl.processModeType)
            || "entry".equalsIgnoreCase(reqMdl.processModeType)) {
            // 登録モード
            respMdl.localeId = contextContainer.getUserContext().getLocaleID();
            return respMdl;
        }

        // 更新モード

        String crmAccountGroupCd = reqMdl.crmAccountGroupCd;
        // アカウントグループ情報を取得
        CrmCcAcctGrp crmCcAcctGrp =
            crmAcctGrpLogic.getAcctGrpInfo(crmAccountGroupCd);

        respMdl.crmAccountGroupCd = crmAccountGroupCd;
        respMdl.crmAccountGroupName = crmCcAcctGrp.getCrmAccountGroupName();
        respMdl.crmAccountGroupSearchName =
            crmCcAcctGrp.getCrmAccountGroupSearchName();
        respMdl.crmAccountGroupShortName =
            crmCcAcctGrp.getCrmAccountGroupShortName();
        respMdl.notes = crmCcAcctGrp.getNotes();
        respMdl.localeId = crmCcAcctGrp.getLocaleId();
        respMdl.versionNo = Long.toString(crmCcAcctGrp.getVersionNo());

        // アカウントグループ分類所属CDを取得
        respMdl.crmAccountClassCd =
            crmAcctGrpLogic.getAcctGrpClassInfo(crmAccountGroupCd);

        // アカウントグループ担当組織情報を取得
        respMdl.accountChargeDeptResponseList =
            crmAcctGrpLogic.getAcctGrpChargeDeptInfo(crmAccountGroupCd);

        // アカウントグループ担当ユーザ情報を取得
        respMdl.accountChargeUserResponseList =
            crmAcctGrpLogic.getAcctGrpChargeUserInfo(crmAccountGroupCd);

        return respMdl;
    }

    /**
     * アカウント所属情報を返します。<br/>
     * <p>
     * <ul>
     * <li>アカウント所属を取得します。</li>
     * </ul>
     * </p>
     * 
     * @param AcctGrpAthRequestModel
     *            アカウントグループリクエストモデル
     * @return AcctGrpAthResponseModel アカウントグループ情報をセットしたレスポンスモデル
     */
    @Execute
    @Validation
    public PagingResponse<AcctGrpAthModel> searchAcctGrpAthInfo(
            AcctGrpAthRequestModel request) {

        AcctGrpDto dto = entryMapper.convertToSearchAccount(request);

        // アカウント所属情報を取得
        PagingResponse<AcctGrpAthModel> response =
            crmAcctGrpLogic.searchAcctGrpAthInfo(dto);

        return response;
    }

    /**
     * アカウントグループ登録を行いします。
     * 
     * @param EntryRequestModel
     *            レスポンスモデル
     * @return なし
     */
    @Execute
    @Validation
    public EntryResponseModel regist(EntryRequestModel request) {

        String crmAccountGroupCd = request.crmAccountGroupCd;

        // CRMアカウントグループを登録します。
        CrmCcAcctGrp crmCcAcctGrp = entryMapper.convertToCrmCcAcctGrp(request);
        crmAcctGrpLogic.create(crmCcAcctGrp);

        List<CrmCcAcctGrpChargeDept> deptList =
            new ArrayList<CrmCcAcctGrpChargeDept>();

        // CRMアカウントグループ担当組織を登録します。
        for (CrmCcAcctGrpChargeDept dept : request.grpCharDeptList) {
            dept = entryMapper.convertToCrmCcAcctGrpCharDeptEntity(dept);
            dept.setCrmAccountGroupCd(crmAccountGroupCd);
            deptList.add(dept);
        }
        crmAcctGrpLogic.createDept(deptList, crmAccountGroupCd);

        List<CrmCcAcctGrpChargeUser> userList =
            new ArrayList<CrmCcAcctGrpChargeUser>();

        // CRMアカウントグループ担当者を登録します。
        for (CrmCcAcctGrpChargeUser user : request.grpCharUserList) {
            user = entryMapper.convertToCrmCcAcctGrpCharUserEntity(user);
            user.setCrmAccountGroupCd(crmAccountGroupCd);
            userList.add(user);
        }
        crmAcctGrpLogic.createUser(userList, crmAccountGroupCd);

        // CRMアカウントグループ分類を登録します。
        crmAcctGrpLogic.createClassAth(
            request.crmAccountClassCd,
            crmAccountGroupCd);

        EntryResponseModel response = new EntryResponseModel();
        response.versionNo = crmAcctGrpLogic.getVersionNo(crmAccountGroupCd);

        return response;
    }

    /**
     * CRMアカウントグループ更新を行いします。
     * 
     * @param EntryRequestModel
     *            レスポンスモデル
     * @return なし
     */
    @Execute
    @Validation
    public void update(EntryRequestModel request) {

        String crmAccountGroupCd = request.crmAccountGroupCd;

        // CRMアカウントグループを更新します。
        CrmCcAcctGrp crmCcAcctGrp = entryMapper.convertToCrmCcAcctGrp(request);
        crmAcctGrpLogic.update(crmCcAcctGrp);

        List<CrmCcAcctGrpChargeDept> deptList =
            new ArrayList<CrmCcAcctGrpChargeDept>();

        // CRMアカウントグループ担当組織を更新します。
        for (CrmCcAcctGrpChargeDept dept : request.grpCharDeptList) {
            dept = entryMapper.convertToCrmCcAcctGrpCharDeptEntity(dept);
            dept.setCrmAccountGroupCd(crmAccountGroupCd);
            deptList.add(dept);
        }
        crmAcctGrpLogic.createDept(deptList, crmAccountGroupCd);

        List<CrmCcAcctGrpChargeUser> userList =
            new ArrayList<CrmCcAcctGrpChargeUser>();

        // CRMアカウントグループ担当者を更新します。
        for (CrmCcAcctGrpChargeUser user : request.grpCharUserList) {
            user = entryMapper.convertToCrmCcAcctGrpCharUserEntity(user);
            user.setCrmAccountGroupCd(crmAccountGroupCd);
            userList.add(user);
        }
        crmAcctGrpLogic.createUser(userList, crmAccountGroupCd);

        // CRMアカウントグループ分類を更新します。
        crmAcctGrpLogic.createClassAth(
            request.crmAccountClassCd,
            crmAccountGroupCd);
    }

    /**
     * アカウントグループの削除を行いします。
     * 
     * @param EntryRequestModel
     *            レスポンスモデル
     * @return なし
     */
    @Execute
    @Validation
    public void delete(EntryRequestModel request) {

        String crmAccountGroupCd = request.crmAccountGroupCd;
        String localeId = request.localeId;
        CrmCcAcctGrp crmCcAcctGrp = new CrmCcAcctGrp();
        crmCcAcctGrp.setCrmAccountGroupCd(crmAccountGroupCd);
        crmCcAcctGrp.setLocaleId(localeId);

        // CRMアカウントグループを削除します。
        crmAcctGrpLogic.delete(crmCcAcctGrp);
    }

    /**
     * アカウント所属追加を行いします。
     * 
     * @param EntryRequestModel
     *            レスポンスモデル
     * @return なし
     */
    @Execute
    @Validation
    public void addAccount(EntryRequestAccountAddModel request) {

        String crmAccountGroupCd = request.crmAccountGroupCd;

        List<EntryAccountModel> accountList = request.entryAccountList;

        // アカウントを追加登録
        crmAcctGrpLogic.addAccount(accountList, crmAccountGroupCd);
    }

    /**
     * アカウントグループの削除を行いします。
     * 
     * @param EntryRequestModel
     *            レスポンスモデル
     * @return なし
     */
    @Execute
    @Validation
    public void deleteAccount(DeleteAccountRequestModel request) {

        String crmAccountGroupCd = request.crmAccountGroupCd;
        String accountCd = request.crmAccountCd;
        String termCd = request.termCd;

        // CRMアカウントグループを削除します。
        crmAcctGrpLogic.deleteAccount(crmAccountGroupCd, accountCd, termCd);
    }

    private List<KeyValueBean> getLocaleForComboList() {

        List<BizLocaleInfo> bizLocaleInfoList =
            bizLocaleManager.getApplicationLocales(contextContainer
                .getCurrentFeatureContext()
                .getApplicationID());

        List<KeyValueBean> localeIdList = CollectionsUtil.newArrayList();
        for (BizLocaleInfo entity : bizLocaleInfoList) {
            KeyValueBean item = new KeyValueBean();
            item.key = entity.getDisplayName();
            item.value = entity.getLocaleId();
            localeIdList.add(item);
        }
        return localeIdList;
    }

}
