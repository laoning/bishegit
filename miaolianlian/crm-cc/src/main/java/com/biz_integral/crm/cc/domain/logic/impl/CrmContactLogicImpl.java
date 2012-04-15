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

import com.biz_integral.crm.cc.domain.dao.CrmCcContactDao;
import com.biz_integral.crm.cc.domain.dao.CrmCcCountryCmnDao;
import com.biz_integral.crm.cc.domain.dto.ContactSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.ContactSearchResultDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactDto;
import com.biz_integral.crm.cc.domain.dto.CrmContactCheckChargeDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcContact;
import com.biz_integral.crm.cc.domain.entity.CrmCcCountryCmn;
import com.biz_integral.crm.cc.domain.logic.CrmContactCustomLogic;
import com.biz_integral.crm.cc.domain.logic.CrmContactLogic;
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
 * CRMコンタクトのロジック実装です 。
 */
public class CrmContactLogicImpl implements CrmContactLogic {

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
     * CRMコンタクトに関するDAO
     */
    @Resource
    protected CrmCcContactDao crmCcContactDao;

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
     * コンテキストコンテナ。
     */
    @Resource
    protected ContextContainer contextContainer;

    /**
     * {@link BeanMapper}です
     */
    @Resource
    protected BeanMapper beanMapper;

    /**
     * 登録モード
     */
    private static final String CREATE = "create";

    /**
     * 更新モード
     */
    private static final String UPDATE = "update";

    /**
     * {@link CrmContactCustomLogic 差し込みモジュールクラス}
     */
    protected CrmContactCustomLogic crmContactCustomLogic =
        new DefaultCrmContactCustomLogic();

    /**
     * 差し込みモジュールをセットします。
     * 
     * @param crmContactCustomLogic
     */
    @Binding(bindingType = BindingType.MAY)
    public void setCrmContactCustom(CrmContactCustomLogic crmContactCustomLogic) {
        this.crmContactCustomLogic = crmContactCustomLogic;
    }

    /**
     * 処理の差し込み口のデフォルトのクラスです。
     */
    private class DefaultCrmContactCustomLogic implements CrmContactCustomLogic {

        /**
         * {@inheritDoc}
         */
        @SuppressWarnings("synthetic-access")
        @Override
        public PagingResult<ContactSearchResultDto> getEntityList(
                ContactSearchCriteriaDto criteriaDto) {

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

            return crmCcContactDao.findByContactSearchCriteria(criteriaDto);
        }

        /**
         * {@inheritDoc}
         */
        @SuppressWarnings("unchecked")
        @Override
        public void update(CrmCcContactDto crmCcContactDto) {

            // 入力値をチェックします。
            validateCrmCcContactDto(crmCcContactDto, UPDATE);

            // CRMコンタクトを更新します（楽観的排他）
            crmCcContactDao.updateExcludes(
                beanMapper.map(crmCcContactDto, CrmCcContact.class),
                new PropertyName<String>("startDate"),
                new PropertyName<String>("endDate"),
                new PropertyName<String>("dummyFlag"),
                new PropertyName<String>("deleteFlag"),
                new PropertyName<String>("sortKey"),
                new PropertyName<String>("createUserCd"),
                new PropertyName<String>("createDate"));

            // DIによる名寄せ辞書への登録処理呼び出しを実行します。
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public List<String> checkCharge(
                CrmContactCheckChargeDto crmContactCheckChargeDto) {
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
                    crmContactCheckChargeDto.dataAccessCustomerFlag = "1";

                    if (null != deptCdList && 0 < deptCdList.size()) {
                        crmContactCheckChargeDto.deptCdList =
                            deptCdList.toArray(new String[0]);
                    }
                }
                crmContactCheckChargeDto.companyCd =
                    contextContainer
                        .getApplicationContext()
                        .getFeatureContext()
                        .getCompanyCode();
                crmContactCheckChargeDto.localeId =
                    contextContainer.getUserContext().getLocaleID();
                if (null != crmContactCheckChargeDto.crmContactCdList
                    && 0 < crmContactCheckChargeDto.crmContactCdList.size()) {
                    crmContactCheckChargeDto.crmContactCdArray =
                        crmContactCheckChargeDto.crmContactCdList
                            .toArray(new String[0]);
                }

                List<CrmCcContact> crmCcContactList =
                    crmCcContactDao
                        .findByCrmContactCheckChargeDto(crmContactCheckChargeDto);

                if (crmCcContactList == null || crmCcContactList.isEmpty()) {
                    return crmContactCheckChargeDto.crmContactCdList;
                } else {
                    Set<String> crmCcContactSet = new HashSet<String>();
                    for (CrmCcContact crmCcContact : crmCcContactList) {
                        crmCcContactSet.add(crmCcContact.getCrmContactCd());
                    }

                    for (String crmContactCd : crmContactCheckChargeDto.crmContactCdList) {
                        if (!crmCcContactSet.contains(crmContactCd)) {
                            resultList.add(crmContactCd);
                        }
                    }
                }
            }
            return resultList;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PagingResult<ContactSearchResultDto> getEntityList(
            ContactSearchCriteriaDto criteriaDto) {
        return crmContactCustomLogic.getEntityList(criteriaDto);
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
    public void isCharge(CrmContactCheckChargeDto crmContactCheckChargeDto) {
        ValidationResults vr = new ValidationResults();
        List<String> resultList =
            crmContactCustomLogic.checkCharge(crmContactCheckChargeDto);
        if (resultList.size() != 0) {
            for (String crmCcContactCd : crmContactCheckChargeDto.crmContactCdList) {
                vr.add(new ValidationResult(MessageBuilder.create(
                    "E.CRM.CC.00015").addParameter(
                    MessageBuilder.$("CRM.CC.crmContactCd")).addParameter(
                    MessageBuilder.$(crmCcContactCd)).toMessage()));
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
    public List<String> checkCharge(
            CrmContactCheckChargeDto crmContactCheckChargeDto) {
        return crmContactCustomLogic.checkCharge(crmContactCheckChargeDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrmCcContact getEntity(CrmCcContactDto crmCcContactDto) {
        crmCcContactDto.startDate = DateUtil.today();
        crmCcContactDto.endDate = DateUtil.today();
        crmCcContactDto.companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();
        crmCcContactDto.localeId =
            contextContainer.getUserContext().getLocaleID();
        crmCcContactDto.deleteFlag = false;
        return crmCcContactDao.getEntityByDto(crmCcContactDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrmCcContact create(CrmCcContactDto crmCcContactDto) {

        // 入力値をチェックします。
        validateCrmCcContactDto(crmCcContactDto, CREATE);

        // CRMコンタクトを登録します。
        crmCcContactDao.insert(beanMapper.map(
            crmCcContactDto,
            CrmCcContact.class));

        CrmCcContact crmCcContact = validateCrmCcContact(crmCcContactDto);

        // DIによる名寄せ辞書への登録処理呼び出しを実行します。
        return crmCcContact;
    }

    /**
     * 登録前入力値をチェックします。
     * 
     * @param crmCcContactDto
     *            CRMコンタクトDTO
     * @param mode
     *            処理モード
     */
    private void validateCrmCcContactDto(CrmCcContactDto crmCcContactDto,
            String mode) {

        ValidationResults vr = new ValidationResults();
        // 新コンタクトコード不一致チェックします。
        if (crmCcContactDto.crmContactCd
            .equals(crmCcContactDto.newCrmContactCd)) {
            vr.add(new ValidationResult(MessageBuilder
                .create("E.CRM.CC.00076")
                .toMessage()));
            throw new ValidationException(vr);
        }

        // 登録前CRMコンタクトコードの重複をチェックします。
        if (CREATE.equals(mode)) {
            List<CrmCcContact> crmCcContactBefore =
                crmCcContactDao.getEntityForCheck(crmCcContactDto);
            if (0 < crmCcContactBefore.size()) {
                vr.add(new ValidationResult(MessageBuilder.create(
                    "E.CRM.CC.00060").addParameter(
                    MessageBuilder.$("CRM.CC.crmContactCd")).toMessage()));
                throw new ValidationException(vr);
            }
        }

        // 国コードの存在をチェックします。
        if (crmCcContactDto.countryCd != null) {
            CrmCcCountryCmn crmCcCountryCmn =
                crmCcCountryCmnDao.getByPkNoException(
                    crmCcContactDto.companyCd,
                    crmCcContactDto.countryCd,
                    crmCcContactDto.localeId,
                    LogicalDelete.AVAILABLE);
            if (crmCcCountryCmn == null) {
                vr.add(new ValidationResult(MessageBuilder.create(
                    "E.CRM.CC.00024").addParameter(
                    MessageBuilder.$("CRM.CC.countryCd")).addParameter(
                    MessageBuilder.$(crmCcContactDto.countryCd)).toMessage()));
                throw new ValidationException(vr);
            }
        }

        // 新CRMコンタクトコードの存在をチェックします。
        if (crmCcContactDto.newCrmContactCd != null) {
            CrmCcContact crmCcContact =
                crmCcContactDao.getValidLocaleEntityNoException(
                    crmCcContactDto.companyCd,
                    crmCcContactDto.newCrmContactCd,
                    crmCcContactDto.localeId,
                    DateUtil.today(),
                    LogicalDelete.AVAILABLE);

            if (crmCcContact == null) {
                vr.add(new ValidationResult(MessageBuilder
                    .create("E.CRM.CC.00024")
                    .addParameter(MessageBuilder.$("CRM.CC.crmNewContactCd"))
                    .addParameter(
                        MessageBuilder.$(crmCcContactDto.newCrmContactCd))
                    .toMessage()));
                throw new ValidationException(vr);
            }
        }
    }

    /**
     * 登録後CRMコンタクトコードの重複をチェックします。
     * 
     * @param crmCcContactDto
     *            CRMコンタクトDTO
     * @return CrmCcContactエンティティ
     */
    private CrmCcContact validateCrmCcContact(CrmCcContactDto crmCcContactDto) {
        ValidationResults vr = new ValidationResults();
        CrmCcContact result = new CrmCcContact();
        List<CrmCcContact> crmCcContactAfter =
            crmCcContactDao.getEntityForCheck(crmCcContactDto);
        if (crmCcContactAfter.size() > 1) {
            vr.add(new ValidationResult(MessageBuilder
                .create("E.CRM.CC.00060")
                .addParameter(MessageBuilder.$("CRM.CC.crmContactCd"))
                .toMessage()));
            throw new ValidationException(vr);
        }
        if (crmCcContactAfter.size() != 0) {
            result = crmCcContactAfter.get(0);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(CrmCcContactDto crmCcContactDto) {
        crmContactCustomLogic.update(crmCcContactDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(CrmCcContactDto crmCcContactDto) {

        // CRMコンタクトを更新します（楽観的排他）
        crmCcContactDao.updateIncludes(
            beanMapper.map(crmCcContactDto, CrmCcContact.class),
            new PropertyName<String>("deleteFlag"),
            new PropertyName<String>("versionNo"),
            new PropertyName<String>("recordUserCd"),
            new PropertyName<String>("recordDate"));

        // DIによる名寄せ辞書への登録処理呼び出しを実行します。
    }
}
