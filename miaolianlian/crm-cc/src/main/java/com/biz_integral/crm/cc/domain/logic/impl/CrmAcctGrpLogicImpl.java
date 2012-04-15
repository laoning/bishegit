/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.biz_integral.crm.cc.application.web.accountManage.EntryAccountChargeDeptResponseModel;
import com.biz_integral.crm.cc.application.web.accountManage.EntryAccountChargeUserResponseModel;
import com.biz_integral.crm.cc.application.web.acctGrp.AcctGrpAthModel;
import com.biz_integral.crm.cc.application.web.acctGrp.EntryAccountModel;
import com.biz_integral.crm.cc.domain.dao.CrmCcAccountClassDao;
import com.biz_integral.crm.cc.domain.dao.CrmCcAcctGrpAthDao;
import com.biz_integral.crm.cc.domain.dao.CrmCcAcctGrpChargeDeptDao;
import com.biz_integral.crm.cc.domain.dao.CrmCcAcctGrpChargeUserDao;
import com.biz_integral.crm.cc.domain.dao.CrmCcAcctGrpClassAthDao;
import com.biz_integral.crm.cc.domain.dao.CrmCcAcctGrpDao;
import com.biz_integral.crm.cc.domain.dto.AcctGrpDto;
import com.biz_integral.crm.cc.domain.dto.AcctGrpSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.AcctGrpSearchResultDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountChargeUserNames;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountClass;
import com.biz_integral.crm.cc.domain.entity.CrmCcAcctGrp;
import com.biz_integral.crm.cc.domain.entity.CrmCcAcctGrpAth;
import com.biz_integral.crm.cc.domain.entity.CrmCcAcctGrpChargeDept;
import com.biz_integral.crm.cc.domain.entity.CrmCcAcctGrpChargeDeptNames;
import com.biz_integral.crm.cc.domain.entity.CrmCcAcctGrpChargeUser;
import com.biz_integral.crm.cc.domain.entity.CrmCcAcctGrpClassAth;
import com.biz_integral.crm.cc.domain.logic.CrmAcctGrpLogic;
import com.biz_integral.crm.cc.domain.logic.ParameterLogic;
import com.biz_integral.crm.cc.domain.types.MainChargeFlg;
import com.biz_integral.extension.beans.BizBeans;
import com.biz_integral.extension.mvc.maskat.beans.PagingResponse;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.mapper.BeanMapper;
import com.biz_integral.foundation.core.message.MessageBuilder;
import com.biz_integral.foundation.core.types.EnumNamesRegistry;
import com.biz_integral.foundation.core.util.CollectionsUtil;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.foundation.core.util.LocaleUtil;
import com.biz_integral.foundation.core.util.UniqueIdGenerator;
import com.biz_integral.foundation.core.validation.ValidationException;
import com.biz_integral.foundation.core.validation.ValidationResult;
import com.biz_integral.foundation.core.validation.ValidationResults;
import com.biz_integral.service.domain.master.BizConfigurationProvider;
import com.biz_integral.service.persistence.LogicalDelete;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * CrmAccountLogicロジックの実装です。
 */
public class CrmAcctGrpLogicImpl implements CrmAcctGrpLogic {

    /**
     * コンテキストコンテナ。
     */
    @Resource
    protected ContextContainer contextContainer;

    /**
     * アプリケーション共通マスタ
     */
    @Resource
    protected BizConfigurationProvider bizConfigurationProvider;

    /**
     * パラメータロジック
     */
    @Resource
    protected ParameterLogic parameterLogic;

    /**
     * アカウントグループDao。
     */
    @Resource
    protected CrmCcAcctGrpDao crmCcAcctGrpDao;

    /**
     * アカウントグループ担当組織Dao。
     */
    @Resource
    protected CrmCcAcctGrpChargeDeptDao crmCcAcctGrpChargeDeptDao;

    /**
     * アカウントグループ担当者Dao。
     */
    @Resource
    protected CrmCcAcctGrpChargeUserDao crmCcAcctGrpChargeUserDao;

    /**
     * アカウントグループ分類所属Dao。
     */
    @Resource
    protected CrmCcAcctGrpClassAthDao crmCcAcctGrpClassAthDao;

    /**
     * アカウントグループ分類所属Dao。
     */
    @Resource
    protected CrmCcAccountClassDao crmCcAccountClassDao;

    /**
     * パラーメタCRM用組織セットコードの名称
     */
    private static final String DEPARTMENTSETCD = "CRMCC0004";

    /**
     * 区分に関する名称管理API
     */
    @Resource
    protected EnumNamesRegistry enumNamesRegistry;

    /**
     * アカウントグループ所属Dao。
     */
    @Resource
    protected CrmCcAcctGrpAthDao crmCcAcctGrpAthDao;

    /**
     * {@link BeanMapper}
     */
    @Resource(name = "crmCcBeanMapper")
    protected BeanMapper beanMapper;

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public PagingResult<AcctGrpSearchResultDto> getAcctGrpList(
            AcctGrpSearchCriteriaDto dto) {
        return crmCcAcctGrpDao.findByAcctGrpSearchCriteriaDto(dto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(CrmCcAcctGrp entity) {

        // 存在チェック
        List<CrmCcAcctGrp> grpList = crmCcAcctGrpDao.getEntity(entity);

        if (grpList != null && grpList.size() > 0) {
            throwErrorMessage("E.CRM.CC.00118");
        }

        entity.setCompanyCd(getCompanyCd());
        entity.setLocaleId(getLocaleId());
        entity.setStartDate(bizConfigurationProvider.getStartDate());
        entity.setEndDate(bizConfigurationProvider.getEndDate());
        entity.setDeleteFlag(false);
        entity.setTermCd(UniqueIdGenerator.getInstance().create());
        entity.setSortKey(1L);

        // 登録
        crmCcAcctGrpDao.insertOrUpdate(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getVersionNo(String crmAccountGroupCd) {

        if (StringUtils.isEmpty(crmAccountGroupCd)) {
            this.throwErrorMessage("E.CRM.CC.00117");
        }

        String companyCd = this.getCompanyCd();
        String localeId = this.getLocaleId();
        // 取得
        CrmCcAcctGrp e =
            crmCcAcctGrpDao.getEntity(
                companyCd,
                crmAccountGroupCd,
                localeId,
                "0",
                null);

        if (e == null) {
            this.throwErrorMessage("E.CRM.CC.00117");
        }

        return Long.toString(e.getVersionNo());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createDept(List<CrmCcAcctGrpChargeDept> deptList,
            String crmAccountGroupCd) {

        String companyCd = getCompanyCd();

        // 領域コード
        Object ob = parameterLogic.getEntity("CRMCC0002");
        String domainCd = StringUtils.EMPTY;
        if (ob != null) {
            domainCd = ob.toString();
        }

        // 入力値をチェックします。
        validateChargeDeptCriteriaDto(deptList);

        CrmCcAcctGrpChargeDept entity = new CrmCcAcctGrpChargeDept();
        entity.setCompanyCd(companyCd);
        entity.setCrmAccountGroupCd(crmAccountGroupCd);
        entity.setCrmDomainCd(domainCd);

        // DBに存在のデータ
        List<CrmCcAcctGrpChargeDept> existEntityList =
            crmCcAcctGrpChargeDeptDao.getEntityList(entity);

        String deptCd = StringUtils.EMPTY;
        boolean isExist = false;

        // DBに存在するが画面からのデータにない、削除
        // DBに存在するが画面からのデータにある、更新
        for (CrmCcAcctGrpChargeDept existDept : existEntityList) {
            deptCd = existDept.getDepartmentCd();
            isExist = false;
            for (CrmCcAcctGrpChargeDept dept : deptList) {
                if (deptCd.equals(dept.getDepartmentCd())) {
                    isExist = true;
                    entity = dept;
                    break;
                }
            }
            if (isExist) {
                // 更新
                existDept.setMainChargeFlag(entity.isMainChargeFlag());
                existDept.setStartDate(entity.getStartDate());
                existDept.setEndDate(entity.getEndDate());
                existDept.setCrmDomainCd(domainCd);
                crmCcAcctGrpChargeDeptDao.updateWithLock(existDept);
            } else {
                // 削除
                updateDeleteFlagDept(existDept);
            }
        }

        // 画面に存在するがDBにない、新規登録
        for (CrmCcAcctGrpChargeDept dept : deptList) {
            deptCd = dept.getDepartmentCd();
            isExist = false;
            for (CrmCcAcctGrpChargeDept existDept : existEntityList) {
                if (deptCd.equals(existDept.getDepartmentCd())) {
                    isExist = true;
                    entity = dept;
                    break;
                }
            }
            if (!isExist) {
                // 新規登録
                dept.setCrmDomainCd(domainCd);
                crmCcAcctGrpChargeDeptDao.insert(dept);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createUser(List<CrmCcAcctGrpChargeUser> newUserList,
            String crmAccountGroupCd) {

        String companyCd = getCompanyCd();
        String domainCd = getCrmDomainCd();

        // 入力値をチェックします。
        validateChargeUserCriteriaDto(newUserList);

        CrmCcAcctGrpChargeUser entity = new CrmCcAcctGrpChargeUser();
        entity.setCompanyCd(companyCd);
        entity.setCrmAccountGroupCd(crmAccountGroupCd);
        entity.setCrmDomainCd(domainCd);

        // DBに存在のデータ
        List<CrmCcAcctGrpChargeUser> existEntityList =
            crmCcAcctGrpChargeUserDao.getEntityList(entity);

        String userCd = StringUtils.EMPTY;
        boolean isExist = false;

        // DBに存在するが画面からのデータにない、削除
        // DBに存在するが画面からのデータにある、更新
        for (CrmCcAcctGrpChargeUser existUser : existEntityList) {
            userCd = existUser.getUserCd();
            isExist = false;
            for (CrmCcAcctGrpChargeUser user : newUserList) {
                if (userCd.equals(user.getUserCd())) {
                    isExist = true;
                    entity = user;
                    break;
                }
            }
            if (isExist) {
                // 更新
                existUser.setMainChargeFlag(entity.isMainChargeFlag());
                existUser.setStartDate(entity.getStartDate());
                existUser.setEndDate(entity.getEndDate());
                existUser.setCrmDomainCd(domainCd);
                crmCcAcctGrpChargeUserDao.updateWithLock(existUser);
            } else {
                // 削除
                updateDeleteFlagUser(existUser);
            }
        }

        // 画面に存在するがDBにない、新規登録
        for (CrmCcAcctGrpChargeUser user : newUserList) {
            userCd = user.getUserCd();
            isExist = false;
            for (CrmCcAcctGrpChargeUser existDept : existEntityList) {
                if (userCd.equals(existDept.getUserCd())) {
                    isExist = true;
                    entity = user;
                    break;
                }
            }
            if (!isExist) {
                // 新規登録
                user.setCrmDomainCd(domainCd);
                crmCcAcctGrpChargeUserDao.insert(user);
            }
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createClassAth(String crmAccountClassCd,
            String crmAccountGroupCd) {

        // 設定されてない場合終了
        if (StringUtils.isEmpty(crmAccountClassCd)) {
            return;
        }

        // 分類存在チェック
        CrmCcAccountClass clasEntity =
            crmCcAccountClassDao.getByPkNoException(
                getCompanyCd(),
                crmAccountClassCd,
                getLocaleId(),
                LogicalDelete.AVAILABLE);
        if (clasEntity == null) {
            throwErrorMessage("E.CRM.CC.00116");
        }

        // 別の分類コードが設定されている場合処理
        List<CrmCcAcctGrpClassAth> rltList =
            crmCcAcctGrpClassAthDao.getEntityList(
                getCompanyCd(),
                crmAccountGroupCd,
                null);
        CrmCcAcctGrpClassAth entity;
        if (rltList != null && rltList.size() > 0) {
            entity = rltList.get(0);
            // 変更なし
            if (crmAccountClassCd.equals(entity.getCrmAccountClassCd())) {
                return;
            }
            // 削除
            entity.setDeleteFlag(true);
            crmCcAcctGrpClassAthDao.updateWithLock(entity);
        }

        entity = new CrmCcAcctGrpClassAth();
        entity.setCompanyCd(getCompanyCd());
        entity.setCrmAccountClassCd(crmAccountClassCd);
        entity.setCrmAccountGroupCd(crmAccountGroupCd);

        // 登録
        crmCcAcctGrpClassAthDao.insertOrUpdateWithLock(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(CrmCcAcctGrp entity) {

        String companyCd = this.getCompanyCd();
        String localeId = this.getLocaleId();
        String versionNo = StringUtils.EMPTY;
        if (entity.getVersionNo() != null) {
            versionNo = Long.toString(entity.getVersionNo());
        }
        String crmAccountGroupCd = entity.getCrmAccountGroupCd();
        // 検索
        CrmCcAcctGrp upEntity =
            crmCcAcctGrpDao.getEntity(
                companyCd,
                crmAccountGroupCd,
                localeId,
                "0",
                versionNo);

        if (upEntity == null) {
            this.throwErrorMessage("E.CRM.CC.00039");
        }

        upEntity.setCrmAccountGroupName(entity.getCrmAccountGroupName());
        upEntity.setCrmAccountGroupShortName(entity
            .getCrmAccountGroupShortName());
        upEntity.setCrmAccountGroupSearchName(entity
            .getCrmAccountGroupSearchName());
        upEntity.setNotes(entity.getNotes());

        // アカウントグループ情報を更新
        crmCcAcctGrpDao.updateWithLock(upEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(CrmCcAcctGrp crmCcAcctGrp) {

        String companyCd = this.getCompanyCd();
        String crmAccountGroupCd = crmCcAcctGrp.getCrmAccountGroupCd();
        String localeId = crmCcAcctGrp.getLocaleId();
        String versionNo = StringUtils.EMPTY;
        if (crmCcAcctGrp.getVersionNo() != null) {
            versionNo = Long.toString(crmCcAcctGrp.getVersionNo());
        }

        // 削除するEntityを取得
        if (StringUtils.isEmpty(crmAccountGroupCd)) {
            this.throwErrorMessage("E.CRM.CC.00117");
        }

        CrmCcAcctGrp entity = new CrmCcAcctGrp();
        entity.setCompanyCd(getCompanyCd());
        entity.setCrmAccountGroupCd(crmAccountGroupCd);
        entity.setLocaleId(localeId);

        // 検索
        CrmCcAcctGrp upEntity =
            crmCcAcctGrpDao.getEntity(
                companyCd,
                crmAccountGroupCd,
                localeId,
                "0",
                versionNo);

        if (upEntity == null) {
            this.throwErrorMessage("E.CRM.CC.00039");
        }

        // アカウントグループを論理削除
        upEntity.setDeleteFlag(true);
        crmCcAcctGrpDao.updateWithLock(upEntity);

        // CRMアカウントグループ所属削除
        CrmCcAcctGrpAth entityGrpAth = new CrmCcAcctGrpAth();
        entityGrpAth.setCompanyCd(getCompanyCd());
        entityGrpAth.setCrmAccountGroupCd(crmAccountGroupCd);
        List<CrmCcAcctGrpAth> entityGrpAthLst =
            crmCcAcctGrpAthDao.getEntityList(entityGrpAth);
        for (CrmCcAcctGrpAth e : entityGrpAthLst) {
            crmCcAcctGrpAthDao.delete(e);
        }

        // CRMアカウントグループ担当組織削除
        CrmCcAcctGrpChargeDept entityGrpChagDept = new CrmCcAcctGrpChargeDept();
        entityGrpChagDept.setCompanyCd(getCompanyCd());
        entityGrpChagDept.setCrmAccountGroupCd(crmAccountGroupCd);
        List<CrmCcAcctGrpChargeDept> entityGrpChagDeptLst =
            crmCcAcctGrpChargeDeptDao.getEntityList(entityGrpChagDept);
        for (CrmCcAcctGrpChargeDept e : entityGrpChagDeptLst) {
            crmCcAcctGrpChargeDeptDao.delete(e);
        }

        // CRMアカウントグループ担当者削除
        CrmCcAcctGrpChargeUser entityGrpChagUser = new CrmCcAcctGrpChargeUser();
        entityGrpChagUser.setCompanyCd(getCompanyCd());
        entityGrpChagUser.setCrmAccountGroupCd(crmAccountGroupCd);
        entityGrpChagUser.setCrmDomainCd(getCrmDomainCd());
        List<CrmCcAcctGrpChargeUser> entityGrpChagUserLst =
            crmCcAcctGrpChargeUserDao.getEntityList(entityGrpChagUser);
        for (CrmCcAcctGrpChargeUser e : entityGrpChagUserLst) {
            crmCcAcctGrpChargeUserDao.delete(e);
        }

        // CRMアカウントグループ分類所属削除
        CrmCcAcctGrpClassAth entityGrpClassAth = new CrmCcAcctGrpClassAth();
        entityGrpClassAth.setCompanyCd(getCompanyCd());
        entityGrpClassAth.setCrmAccountGroupCd(crmAccountGroupCd);
        List<CrmCcAcctGrpClassAth> entityGrpClassAthLst =
            crmCcAcctGrpClassAthDao.getEntityList(
                getCompanyCd(),
                crmAccountGroupCd,
                null);
        for (CrmCcAcctGrpClassAth e : entityGrpClassAthLst) {
            crmCcAcctGrpClassAthDao.delete(e);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addAccount(List<EntryAccountModel> accountList,
            String crmAccountGroupCd) {

        AcctGrpDto dto = new AcctGrpDto();
        dto.companyCd = getCompanyCd();
        dto.crmAccountGroupCd = crmAccountGroupCd;
        dto.localeId = getLocaleId();
        dto.baseDate = getBaseDate();

        // 既存アカウントグループ所属検索
        PagingResult<AcctGrpDto> existLst =
            crmCcAcctGrpDao.findAcctGrpAthList(dto);

        CrmCcAcctGrpAth entity = new CrmCcAcctGrpAth();
        entity.setCompanyCd(getCompanyCd());
        entity.setCrmAccountGroupCd(crmAccountGroupCd);

        boolean isExist = false;
        String acct;
        for (EntryAccountModel mdl : accountList) {
            acct = mdl.crmAccountCd;
            if (StringUtils.isEmpty(acct)) {
                continue;
            }
            for (AcctGrpDto exist : existLst) {
                if (exist.crmAccountCd.equals(acct)) {
                    isExist = true;
                    break;
                }
            }

            // 存在しないので新規登録
            if (!isExist) {
                entity.setCrmAccountCd(acct);
                entity.setStartDate(bizConfigurationProvider.getStartDate());
                entity.setEndDate(bizConfigurationProvider.getEndDate());
                crmCcAcctGrpAthDao.insert(entity);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAccount(String crmAccountGroupCd, String crmAccountCd,
            String termCd) {
        // アカウント検索
        CrmCcAcctGrpAth entity =
            crmCcAcctGrpAthDao.getByPkNoException(
                getCompanyCd(),
                crmAccountGroupCd,
                crmAccountCd,
                termCd);

        if (entity == null) {
            this.throwErrorMessage("I.CRM.CC.00005");
        }

        entity.setDeleteFlag(true);

        // アカウント論理削除
        crmCcAcctGrpAthDao.updateWithLock(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<EntryAccountChargeDeptResponseModel> getAcctGrpChargeDeptInfo(
            String crmAccountGroupCd) {

        CrmCcAcctGrpChargeDept entity = new CrmCcAcctGrpChargeDept();
        entity.setCompanyCd(getCompanyCd());
        entity.setCrmAccountGroupCd(crmAccountGroupCd);
        entity.setCrmDomainCd(getCrmDomainCd());

        // アカウントグループ担当組織検索
        List<CrmCcAcctGrpChargeDept> rltList =
            crmCcAcctGrpChargeDeptDao.getEntityList(entity);

        List<EntryAccountChargeDeptResponseModel> tempList =
            CollectionsUtil.newArrayList();

        String companyCd = getCompanyCd();
        String departmentSetCd = this.getDepartmentSetCd();
        String localeId = this.getLocaleId();
        Date baseDate = this.getBaseDate();

        String mainChargeChar =
            this.enumNamesRegistry.getName(
                MainChargeFlg.getEnum("1"),
                LocaleUtil.toLocale(contextContainer
                    .getUserContext()
                    .getLocaleID()));

        EntryAccountChargeDeptResponseModel tempModel;

        // レスポンスにセット
        for (CrmCcAcctGrpChargeDept e : rltList) {

            // 日付-１
            Date endDate = e.getEndDate();
            endDate = DateUtil.getCalculator(endDate).addDay(-1).asDate();
            e.setEndDate(endDate);

            tempModel =
                BizBeans.createAndCopy(
                    EntryAccountChargeDeptResponseModel.class,
                    e).execute();

            // 組織名を取得
            tempModel.departmentName =
                crmCcAcctGrpChargeDeptDao.getDeptName(
                    companyCd,
                    departmentSetCd,
                    localeId,
                    e.getDepartmentCd(),
                    baseDate);

            // 主担当の場合
            if (e.isMainChargeFlag()) {
                tempModel.mainCharge = mainChargeChar;
                tempModel.mainChargeFlag = "1";
            } else {
                tempModel.mainChargeFlag = "0";
            }

            tempList.add(tempModel);
        }

        return tempList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<EntryAccountChargeUserResponseModel> getAcctGrpChargeUserInfo(
            String crmAccountGroupCd) {

        CrmCcAcctGrpChargeUser entity = new CrmCcAcctGrpChargeUser();
        entity.setCompanyCd(getCompanyCd());
        entity.setCrmAccountGroupCd(crmAccountGroupCd);
        entity.setCrmDomainCd(getCrmDomainCd());

        // アカウントグループ担当組織検索
        List<CrmCcAcctGrpChargeUser> rltList =
            crmCcAcctGrpChargeUserDao.getEntityList(entity);

        List<EntryAccountChargeUserResponseModel> tempList =
            CollectionsUtil.newArrayList();

        String localeId = this.getLocaleId();
        Date baseDate = this.getBaseDate();

        String mainChargeChar =
            this.enumNamesRegistry.getName(
                MainChargeFlg.getEnum("1"),
                LocaleUtil.toLocale(contextContainer
                    .getUserContext()
                    .getLocaleID()));

        EntryAccountChargeUserResponseModel tempModel;

        // 変換
        for (CrmCcAcctGrpChargeUser e : rltList) {
            // 日付-１
            Date endDate = e.getEndDate();
            endDate = DateUtil.getCalculator(endDate).addDay(-1).asDate();
            e.setEndDate(endDate);

            tempModel =
                BizBeans.createAndCopy(
                    EntryAccountChargeUserResponseModel.class,
                    e).execute();

            // ユーザ名を取得
            tempModel.userName =
                crmCcAcctGrpChargeUserDao.getUserName(
                    e.getUserCd(),
                    localeId,
                    baseDate);

            // 主担当の場合
            if (e.isMainChargeFlag()) {
                tempModel.mainCharge = mainChargeChar;
                tempModel.mainChargeFlag = "1";
            } else {
                tempModel.mainChargeFlag = "0";
            }

            tempList.add(tempModel);
        }

        return tempList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getAcctGrpClassInfo(String crmAccountGroupCd) {
        // 検索
        List<CrmCcAcctGrpClassAth> rltList =
            crmCcAcctGrpClassAthDao.getEntityList(
                getCompanyCd(),
                crmAccountGroupCd,
                null);
        if (rltList == null || rltList.size() <= 0) {
            return null;
        }
        return rltList.get(0).getCrmAccountClassCd();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrmCcAcctGrp getAcctGrpInfo(String crmAccountGroupCd) {

        if (StringUtils.isEmpty(crmAccountGroupCd)) {
            this.throwErrorMessage("E.CRM.CC.00117");
        }

        String companyCd = this.getCompanyCd();
        String localeId = this.getLocaleId();
        // 取得
        CrmCcAcctGrp e =
            crmCcAcctGrpDao.getEntity(
                companyCd,
                crmAccountGroupCd,
                localeId,
                "0",
                null);

        if (e == null) {
            this.throwErrorMessage("E.CRM.CC.00117");
        }

        return e;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PagingResponse<AcctGrpAthModel> searchAcctGrpAthInfo(AcctGrpDto dto) {
        dto.localeId = getLocaleId();
        dto.baseDate = getBaseDate();

        PagingResponse<AcctGrpAthModel> response =
            new PagingResponse<AcctGrpAthModel>();

        // アカウントグループ所属検索
        PagingResult<AcctGrpDto> searchResult =
            crmCcAcctGrpDao.findAcctGrpAthList(dto);

        PagingResult<AcctGrpAthModel> result =
            new PagingResult<AcctGrpAthModel>();

        AcctGrpAthModel model;

        for (AcctGrpDto dist : searchResult) {
            model = beanMapper.map(dist, AcctGrpAthModel.class);
            result.add(model);
        }
        result.setAllRecordCount(searchResult.getAllRecordCount());
        result.setLimit(searchResult.getLimit());
        result.setOffset(searchResult.getOffset());

        response.setRows(result.getResultList());
        response.setTotal(result.getAllRecordCount());

        return response;
    }

    /**
     * CRMアカウント担当組織の登録場合、入力値をチェックします。
     * 
     * @param chargeDeptCriteriaDtoList
     *            CRMアカウント担当組織モデルリスト
     */
    private void validateChargeDeptCriteriaDto(
            List<CrmCcAcctGrpChargeDept> entityList) {
        ValidationResults vr = new ValidationResults();
        int count = 0;

        // 担当組織有無チェックします。
        if (entityList.size() == 0) {
            vr.add(new ValidationResult(MessageBuilder
                .create("E.CRM.CC.00041")
                .addParameter(MessageBuilder.$("CRM.CC.chargeDepartment"))
                .toMessage()));
            throw new ValidationException(vr);
        }
        for (CrmCcAcctGrpChargeDept entity : entityList) {
            if (entity.isMainChargeFlag()) {
                count = count + 1;
            }

            // 期間大小チェックします。
            if (entity.getStartDate().after(entity.getEndDate())) {
                vr.add(new ValidationResult(MessageBuilder.create(
                    "E.CRM.CC.00005").addParameter(
                    MessageBuilder.$("CRM.CC.periodTo")).addParameter(
                    MessageBuilder.$("CRM.CC.periodFrom")).toMessage()));
                throw new ValidationException(vr);
            }
        }

        // 主担当組織１件チェックします。
        if (count != 1) {
            vr.add(new ValidationResult(MessageBuilder
                .create("E.CRM.CC.00042")
                .addParameter(MessageBuilder.$("CRM.CC.chargeDepartment"))
                .toMessage()));
            throw new ValidationException(vr);
        }
    }

    /**
     * CRMアカウント担当者の登録場合、入力値をチェックします。
     * 
     * @param chargeUserCriteriaDtoList
     *            CRMアカウント担当者モデル
     */
    private void validateChargeUserCriteriaDto(
            List<CrmCcAcctGrpChargeUser> newUserList) {
        ValidationResults vr = new ValidationResults();
        int count = 0;

        // 担当者有無チェックします。
        if (newUserList.size() == 0) {
            vr.add(new ValidationResult(MessageBuilder
                .create("E.CRM.CC.00041")
                .addParameter(MessageBuilder.$("CRM.CC.chargeUser"))
                .toMessage()));
            throw new ValidationException(vr);
        }
        for (CrmCcAcctGrpChargeUser userEntity : newUserList) {
            if (userEntity.isMainChargeFlag()) {
                count = count + 1;
            }

            // 期間大小チェックします。
            if (userEntity.getStartDate().after(userEntity.getEndDate())) {
                vr.add(new ValidationResult(MessageBuilder.create(
                    "E.CRM.CC.00005").addParameter(
                    MessageBuilder.$("CRM.CC.periodTo")).addParameter(
                    MessageBuilder.$("CRM.CC.periodFrom")).toMessage()));
                throw new ValidationException(vr);
            }
        }

        // 主担当者１件チェックします。
        if (count != 1) {
            vr.add(new ValidationResult(MessageBuilder
                .create("E.CRM.CC.00042")
                .addParameter(MessageBuilder.$("CRM.CC.chargeUser"))
                .toMessage()));
            throw new ValidationException(vr);
        }
    }

    /**
     * CRMアカウント担当組織の論理削除を行いします。
     * 
     * @param resultDto
     *            CRMアカウント担当組織モデル
     */
    private void updateDeleteFlagDept(CrmCcAcctGrpChargeDept resultDto) {
        resultDto.setDeleteFlag(true);
        crmCcAcctGrpChargeDeptDao.updateIncludes(
            resultDto,
            CrmCcAcctGrpChargeDeptNames.deleteFlag(),
            CrmCcAcctGrpChargeDeptNames.recordUserCd(),
            CrmCcAcctGrpChargeDeptNames.recordDate());
    }

    /**
     * CRMアカウント担当者の論理削除を行う
     * 
     * @param resultDto
     *            CRMアカウント担当者モデル
     */
    private void updateDeleteFlagUser(CrmCcAcctGrpChargeUser resultDto) {

        resultDto.setDeleteFlag(true);
        crmCcAcctGrpChargeUserDao.updateIncludes(
            resultDto,
            CrmCcAccountChargeUserNames.deleteFlag(),
            CrmCcAccountChargeUserNames.recordUserCd(),
            CrmCcAccountChargeUserNames.recordDate());

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
     * システム日付を取得します。
     * 
     * @param String
     * 
     */
    private Date getBaseDate() {
        // システム日付
        return DateUtil.parse(
            DateUtil.nowDateString(DateUtil.DATE_FORMAT),
            DateUtil.DATE_FORMAT);
    }

    /**
     * 領域コードを取得します。
     * 
     * @param String
     * 
     */
    private String getCrmDomainCd() {
        // 領域コード
        Object ob = parameterLogic.getEntity("CRMCC0002");
        if (ob == null) {
            return null;
        }
        return ob.toString();
    }

    /**
     * 会社組織セットコードを取得します。
     * 
     * @param String
     * 
     */
    private String getDepartmentSetCd() {

        Object ob = (String) parameterLogic.getEntity(DEPARTMENTSETCD);

        if (ob == null) {
            return null;
        }
        return ob.toString();
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

}
