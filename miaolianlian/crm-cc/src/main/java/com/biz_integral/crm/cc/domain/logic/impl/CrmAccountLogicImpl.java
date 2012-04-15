/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.seasar.extension.jdbc.name.PropertyName;
import org.seasar.framework.container.annotation.tiger.Binding;
import org.seasar.framework.container.annotation.tiger.BindingType;

import com.biz_integral.crm.cc.domain.dao.CrmCcAccountDao;
import com.biz_integral.crm.cc.domain.dao.CrmCcCountryCmnDao;
import com.biz_integral.crm.cc.domain.dao.CrmCcCustomerCmnDao;
import com.biz_integral.crm.cc.domain.dto.AccountSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.AccountSearchResultDto;
import com.biz_integral.crm.cc.domain.dto.CrmAccountCheckChargeDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccount;
import com.biz_integral.crm.cc.domain.entity.CrmCcCountryCmn;
import com.biz_integral.crm.cc.domain.entity.CrmCcCustomerCmn;
import com.biz_integral.crm.cc.domain.logic.CrmAccountCustomLogic;
import com.biz_integral.crm.cc.domain.logic.CrmAccountLogic;
import com.biz_integral.crm.cc.domain.logic.ParameterLogic;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.exception.IllegalAuthorizationException;
import com.biz_integral.foundation.core.mapper.BeanMapper;
import com.biz_integral.foundation.core.message.MessageBuilder;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.foundation.core.validation.ValidationException;
import com.biz_integral.foundation.core.validation.ValidationResult;
import com.biz_integral.foundation.core.validation.ValidationResults;
import com.biz_integral.service.persistence.LogicalDelete;
import com.biz_integral.service.persistence.pager.PagingResult;
import com.biz_integral.service.security.authorization.department.DepartmentAuthorizationEvaluator;
import com.biz_integral.service.security.authorization.functional.FunctionalAuthorization;

/**
 * CrmAccountLogicロジックの実装です。
 */
public class CrmAccountLogicImpl implements CrmAccountLogic {

    /**
     * {@link DepartmentAuthorizationEvaluator 機能アクセス権限}
     */
    @Resource
    protected DepartmentAuthorizationEvaluator departmentAuthorizationEvaluator;

    /**
     * {@link FunctionalAuthorization 機能アクセス権限}
     */
    @Resource
    protected FunctionalAuthorization functionalAuthorization;

    /**
     * CRMアカウントに関するDAO
     */
    @Resource
    protected CrmCcAccountDao crmCcAccountDao;

    /**
     * パラメータロジック
     */
    @Resource
    protected ParameterLogic parameterLogic;

    /**
     * 国に関するDAO
     */
    @Resource
    protected CrmCcCountryCmnDao crmCcCountryCmnDao;

    /**
     * 取引先に関するDAO
     */
    @Resource
    protected CrmCcCustomerCmnDao crmCcCustomerCmnDao;

    /**
     * {@link BeanMapper}です
     */
    @Resource
    protected BeanMapper beanMapper;

    /**
     * コンテキストコンテナ。
     */
    @Resource
    protected ContextContainer contextContainer;

    /**
     * 登録モード
     */
    private static final String CREATE = "create";

    /**
     * 更新モード
     */
    private static final String UPDATE = "update";

    /**
     * {@link CrmAccountCustomLogic 差し込みモジュールクラス}
     */
    protected CrmAccountCustomLogic crmAccountCustomLogic =
        new DefaultCrmAccountCustomLogic();

    /**
     * 差し込みモジュールをセットします。
     * 
     * @param crmAccountCustomLogic
     */
    @Binding(bindingType = BindingType.MAY)
    public void setCrmAccountCustom(CrmAccountCustomLogic crmAccountCustomLogic) {
        this.crmAccountCustomLogic = crmAccountCustomLogic;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> checkCharge(
            CrmAccountCheckChargeDto crmAccountCheckChargeDto) {
        return crmAccountCustomLogic.checkCharge(crmAccountCheckChargeDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(CrmCcAccountDto crmCcAccountDto) {
        crmAccountCustomLogic.update(crmCcAccountDto);
    }

    /**
     * 処理の差し込み口のデフォルトのクラスです。
     */
    private class DefaultCrmAccountCustomLogic implements CrmAccountCustomLogic {

        /**
         * {@inheritDoc}
         */
        @SuppressWarnings("synthetic-access")
        @Override
        public List<String> checkCharge(
                CrmAccountCheckChargeDto crmAccountCheckChargeDto) {
            List<String> resultList = new ArrayList<String>();
            String departmentSetCd =
                parameterLogic.getEntity("CRMCC0004").toString();
            String[] departmentSetCdArray = { departmentSetCd };
            if (isPrivilege()) {
                // 何もしない
            } else {
                if (isAcessCustom(departmentSetCdArray)) {
                    List<String> deptCdList =
                        departmentAuthorizationEvaluator.findAllDepartments(
                            contextContainer
                                .getApplicationContext()
                                .getFeatureContext()
                                .getCompanyCode(),
                            departmentSetCd,
                            "CRM.CC.DATA_ACCESS_CUSTOMER");
                    crmAccountCheckChargeDto.dataAccessCustomerFlag = "1";

                    if (null != deptCdList && 0 < deptCdList.size()) {
                        crmAccountCheckChargeDto.deptCdList =
                            deptCdList.toArray(new String[0]);
                    }
                }

                if (null != crmAccountCheckChargeDto.crmAccountCdList
                    && 0 < crmAccountCheckChargeDto.crmAccountCdList.size()) {
                    crmAccountCheckChargeDto.crmAccountCdArray =
                        crmAccountCheckChargeDto.crmAccountCdList
                            .toArray(new String[0]);
                }
                crmAccountCheckChargeDto.companyCd =
                    contextContainer
                        .getApplicationContext()
                        .getFeatureContext()
                        .getCompanyCode();
                crmAccountCheckChargeDto.localeId =
                    contextContainer.getUserContext().getLocaleID();
                List<CrmCcAccount> entityList =
                    crmCcAccountDao
                        .findByCrmAccountCheckChargeDto(crmAccountCheckChargeDto);
                if (entityList == null || entityList.isEmpty()) {
                    return crmAccountCheckChargeDto.crmAccountCdList;
                } else {

                    Set<String> crmCcAccountSet = new HashSet<String>();
                    for (CrmCcAccount crmCcAccount : entityList) {
                        crmCcAccountSet.add(crmCcAccount.getCrmAccountCd());
                    }

                    for (String crmAccountCd : crmAccountCheckChargeDto.crmAccountCdList) {
                        if (!crmCcAccountSet.contains(crmAccountCd)) {
                            resultList.add(crmAccountCd);
                        }
                    }
                }
            }
            return resultList;
        }

        /**
         * {@inheritDoc}
         */
        @SuppressWarnings( { "unchecked", "synthetic-access" })
        @Override
        public void update(CrmCcAccountDto crmCcAccountDto) {

            // 入力値をチェックします。
            validateCrmCcAccountDto(crmCcAccountDto, UPDATE);

            // CRMアカウントを更新します（楽観的排他）
            crmCcAccountDao.updateExcludes(
                beanMapper.map(crmCcAccountDto, CrmCcAccount.class),
                new PropertyName<String>("startDate"),
                new PropertyName<String>("endDate"),
                new PropertyName<String>("crmAccountAttrCd"),
                new PropertyName<String>("paymentCd"),
                new PropertyName<String>("deleteFlag"),
                new PropertyName<String>("sortKey"),
                new PropertyName<String>("createUserCd"),
                new PropertyName<String>("createDate"));

            // DIによる名寄せ辞書への登録処理呼び出しを実行します。
        }

        /**
         * {@inheritDoc}
         */
        @SuppressWarnings("synthetic-access")
        @Override
        public PagingResult<AccountSearchResultDto> getEntityList(
                AccountSearchCriteriaDto criteriaDto) {

            String[] departmentSetCdArray =
                new String[] { criteriaDto.departmentSetCd };
            if (isPrivilege()) {
                criteriaDto.hasPrivilegeFlag = "1";
            } else {

                // 処理ユーザが「データアクセス制御：特権」を持っていない場合
                criteriaDto.hasPrivilegeFlag = "0";
                if (isAcessCustom(departmentSetCdArray)) {

                    // 「データアクセス制御：顧客データ参照」を持っている場合
                    criteriaDto.hasAceessCustomerFlag = "1";

                    // List<組織コード>を取得します。
                    List<String> updepartmentCdList =
                        departmentAuthorizationEvaluator.findAllDepartments(
                            criteriaDto.companyCd,
                            criteriaDto.departmentSetCd,
                            "CRM.CC.DATA_ACCESS_CUSTOMER");
                    if (null != updepartmentCdList
                        && 0 < updepartmentCdList.size()) {
                        criteriaDto.updepartmentCdListToArray =
                            updepartmentCdList.toArray(new String[0]);
                    }
                } else {
                    criteriaDto.hasAceessCustomerFlag = "0";
                }
            }
            criteriaDto.crmAccountCatCdListToArray();
            return crmCcAccountDao.findByAccountSearchCriteria(criteriaDto);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PagingResult<AccountSearchResultDto> getEntityList(
            AccountSearchCriteriaDto criteriaDto) {

        return crmAccountCustomLogic.getEntityList(criteriaDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrmCcAccount getEntity(CrmCcAccountDto crmCcAccountDto) {
        crmCcAccountDto.startDate = DateUtil.today();
        crmCcAccountDto.endDate = DateUtil.today();
        crmCcAccountDto.companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();
        crmCcAccountDto.localeId =
            contextContainer.getUserContext().getLocaleID();
        crmCcAccountDto.deleteFlag = false;
        return crmCcAccountDao.getAccountEntity(crmCcAccountDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isCharge(CrmAccountCheckChargeDto crmAccountCheckChargeDto) {
        ValidationResults vr = new ValidationResults();
        List<String> resultList =
            crmAccountCustomLogic.checkCharge(crmAccountCheckChargeDto);
        if (resultList.size() != 0) {
            for (String crmCcAccountCd : crmAccountCheckChargeDto.crmAccountCdList) {
                vr.add(new ValidationResult(MessageBuilder.create(
                    "E.CRM.CC.00015").addParameter(
                    MessageBuilder.$("CRM.CC.crmAccountCd")).addParameter(
                    MessageBuilder.$(crmCcAccountCd)).toMessage()));
            }
        }
        if (0 < vr.size()) {
            throw new ValidationException(vr);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrmCcAccount create(CrmCcAccountDto crmCcAccountDto) {

        // 入力値をチェックします。
        validateCrmCcAccountDto(crmCcAccountDto, CREATE);

        // CRMアカウントを登録します。
        crmCcAccountDao.insert(beanMapper.map(
            crmCcAccountDto,
            CrmCcAccount.class));

        CrmCcAccount crmCcAccount = validateCrmCcAccount(crmCcAccountDto);

        // DIによる名寄せ辞書への登録処理呼び出しを実行します。

        return crmCcAccount;
    }

    /**
     * 登録前入力値をチェックします。
     * 
     * @param crmCcAccountDto
     *            CRMアカウントDTO
     * @param mode
     *            処理モード
     */
    private void validateCrmCcAccountDto(CrmCcAccountDto crmCcAccountDto,
            String mode) {

        ValidationResults vr = new ValidationResults();
        // 新アカウントコード不一致チェックします。
        if (crmCcAccountDto.crmAccountCd
            .equals(crmCcAccountDto.newCrmAccountCd)) {
            vr.add(new ValidationResult(MessageBuilder
                .create("E.CRM.CC.00075")
                .toMessage()));
            throw new ValidationException(vr);
        }

        // 登録前CRMアカウントコードの重複をチェックします。
        if (CREATE.equals(mode)) {
            List<CrmCcAccount> crmCcAccountBefore =
                crmCcAccountDao.getEntityForCheck(crmCcAccountDto);
            if (crmCcAccountBefore.size() > 0) {
                vr.add(new ValidationResult(MessageBuilder.create(
                    "E.CRM.CC.00060").addParameter(
                    MessageBuilder.$("CRM.CC.crmAccountCd")).toMessage()));
                throw new ValidationException(vr);
            }
        }

        // 国コードの存在をチェックします。
        if (crmCcAccountDto.countryCd != null) {
            CrmCcCountryCmn crmCcCountryCmn =
                crmCcCountryCmnDao.getByPkNoException(
                    crmCcAccountDto.companyCd,
                    crmCcAccountDto.countryCd,
                    crmCcAccountDto.localeId,
                    LogicalDelete.AVAILABLE);
            if (crmCcCountryCmn == null) {
                vr.add(new ValidationResult(MessageBuilder.create(
                    "E.CRM.CC.00024").addParameter(
                    MessageBuilder.$("CRM.CC.countryCd")).addParameter(
                    MessageBuilder.$(crmCcAccountDto.countryCd)).toMessage()));
                throw new ValidationException(vr);
            }
        }

        // 取引先コードの存在をチェックします。
        if (crmCcAccountDto.customerCd != null) {

            CrmCcCustomerCmn crmCcCustomerCmn =
                crmCcCustomerCmnDao.getValidLocaleEntityNoException(
                    crmCcAccountDto.companyCd,
                    crmCcAccountDto.customerCd,
                    crmCcAccountDto.localeId,
                    DateUtil.today(),
                    LogicalDelete.AVAILABLE);

            if (crmCcCustomerCmn == null) {
                vr.add(new ValidationResult(MessageBuilder.create(
                    "E.CRM.CC.00024").addParameter(
                    MessageBuilder.$("CRM.CC.customerCd")).addParameter(
                    MessageBuilder.$(crmCcAccountDto.customerCd)).toMessage()));
                throw new ValidationException(vr);
            }
        }

        // 新CRMアカウントコードの存在をチェックします。
        if (crmCcAccountDto.newCrmAccountCd != null) {

            CrmCcAccount crmCcAccount =
                crmCcAccountDao.getValidLocaleEntityNoException(
                    crmCcAccountDto.companyCd,
                    crmCcAccountDto.newCrmAccountCd,
                    crmCcAccountDto.localeId,
                    DateUtil.today(),
                    LogicalDelete.AVAILABLE);

            if (crmCcAccount == null) {
                vr.add(new ValidationResult(MessageBuilder
                    .create("E.CRM.CC.00024")
                    .addParameter(MessageBuilder.$("CRM.CC.crmNewAccountCd"))
                    .addParameter(
                        MessageBuilder.$(crmCcAccountDto.newCrmAccountCd))
                    .toMessage()));
                throw new ValidationException(vr);
            }
        }
    }

    /**
	 * 登録後CRMアカウントコードの重複をチェックします。
	 * 
	 * @param crmCcAccountDto
	 *            CRMアカウントDTO
	 * @return CrmCcAccountエンティティクラス
	 */
    private CrmCcAccount validateCrmCcAccount(CrmCcAccountDto crmCcAccountDto) {
        ValidationResults vr = new ValidationResults();
        CrmCcAccount result = new CrmCcAccount();
        List<CrmCcAccount> crmCcAccountAfter =
            crmCcAccountDao.getEntityForCheck(crmCcAccountDto);
        if (crmCcAccountAfter.size() > 1) {
            vr.add(new ValidationResult(MessageBuilder
                .create("E.CRM.CC.00060")
                .addParameter(MessageBuilder.$("CRM.CC.crmAccountCd"))
                .toMessage()));
            throw new ValidationException(vr);
        }
        if (crmCcAccountAfter.size() != 0) {
            result = crmCcAccountAfter.get(0);
        }
        return result;
    }

    /**
     * 特権の判断
     * 
     * @return 特権の有無
     */
    private boolean isPrivilege() {
        return functionalAuthorization.hasAuthority("CRM.CC.PRIVILEGE");
    }

    /**
     * 顧客データ参照権限の判断
     * 
     * @param departmentSetCdArray
     *            組織セットコード
     *@return 顧客データ参照権限の有無
     */
    private boolean isAcessCustom(String[] departmentSetCdArray) {
        boolean isAces = false;
        try {
            departmentAuthorizationEvaluator.decide(
                "CRM.CC.DATA_ACCESS_CUSTOMER",
                departmentSetCdArray);
            isAces = true;

        } catch (IllegalAuthorizationException e) {
            // 何もしない
        }
        return isAces;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(CrmCcAccountDto crmCcAccountDto) {

        // CRMアカウントを更新します（楽観的排他）
        crmCcAccountDao.updateIncludes(
            beanMapper.map(crmCcAccountDto, CrmCcAccount.class),
            new PropertyName<String>("deleteFlag"),
            new PropertyName<String>("versionNo"),
            new PropertyName<String>("recordUserCd"),
            new PropertyName<String>("recordDate"));

        // DIによる名寄せ辞書への登録処理呼び出しを実行します。
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createCustomer(CrmCcAccountDto crmCcAccountDto) {

        // CRMアカウントを更新します（楽観的排他）
        update(crmCcAccountDto);

        // DIによる取引先マスタへの登録処理呼び出しを実行します。
    }
}
