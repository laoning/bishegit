/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.seasar.extension.jdbc.name.PropertyName;
import org.seasar.framework.container.annotation.tiger.Binding;
import org.seasar.framework.container.annotation.tiger.BindingType;

import com.biz_integral.crm.cc.domain.dao.CrmCcAccountContactDao;
import com.biz_integral.crm.cc.domain.dao.CrmCcAccountDao;
import com.biz_integral.crm.cc.domain.dao.CrmCcContactDao;
import com.biz_integral.crm.cc.domain.dto.AccountContactCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmAccountCheckChargeDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountContactCheckItemDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountContactCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountContactDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountContactResultDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactDto;
import com.biz_integral.crm.cc.domain.dto.CrmContactCheckChargeDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccount;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountContact;
import com.biz_integral.crm.cc.domain.entity.CrmCcContact;
import com.biz_integral.crm.cc.domain.logic.AccountContactCustomLogic;
import com.biz_integral.crm.cc.domain.logic.AccountContactLogic;
import com.biz_integral.crm.cc.domain.logic.CrmAccountLogic;
import com.biz_integral.crm.cc.domain.logic.CrmContactLogic;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.mapper.BeanMapper;
import com.biz_integral.foundation.core.message.Message;
import com.biz_integral.foundation.core.message.MessageBuilder;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.foundation.core.util.UniqueIdGenerator;
import com.biz_integral.foundation.core.validation.IllegalValidatorException;
import com.biz_integral.foundation.core.validation.ValidationException;
import com.biz_integral.foundation.core.validation.ValidationResult;
import com.biz_integral.foundation.core.validation.ValidationResults;
import com.biz_integral.service.domain.master.BizConfigurationProvider;
import com.biz_integral.service.persistence.exception.OptimisticLockRuntimeException;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * AccountContactLogicロジックの実装です。
 */
public class AccountContactLogicImpl implements AccountContactLogic {

    /**
     * {@link CrmCcAccountContactDao}
     */
    @Resource
    protected CrmCcAccountContactDao crmCcAccountContactDao;

    /**
     * {@link BizConfigurationProvider}
     */
    @Resource
    protected BizConfigurationProvider bizConfigurationProvider;

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
     * CrmAccountLogicロジック
     */
    @Resource
    protected CrmAccountLogic crmAccountLogic;

    /**
     * CRMコンタクトロジックです 。
     */
    @Resource
    protected CrmContactLogic crmContactLogic;

    /**
     * CRMアカウントに関するDAO
     */
    @Resource
    protected CrmCcAccountDao crmCcAccountDao;

    /**
     * CRMコンタクトに関するDAO
     */
    @Resource
    protected CrmCcContactDao crmCcContactDao;

    /**
     * {@link AccountContactCustomLogic 差し込みモジュールクラス}
     */
    protected AccountContactCustomLogic accountContactCustomLogic =
        new DefaultAccountContactCustomLogic();

    /**
     * 差し込みモジュールをセットします。
     * 
     * @param accountContactCustomLogic
     */
    @Binding(bindingType = BindingType.MAY)
    public void setAccountContactCustom(
            AccountContactCustomLogic accountContactCustomLogic) {
        this.accountContactCustomLogic = accountContactCustomLogic;
    }

    /**
     * 処理の差し込み口のデフォルトのクラスです。
     */
    private class DefaultAccountContactCustomLogic implements
            AccountContactCustomLogic {

        /**
         * {@inheritDoc}
         */
        @Override
        public PagingResult<CrmCcAccountContactResultDto> getContactEntityList(
                CrmCcAccountContactCriteriaDto criteriadto) {
            if (criteriadto.crmAccountCd == null) {
                throw new IllegalArgumentException();
            }
            return crmCcAccountContactDao.getContactEntityList(criteriadto);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public PagingResult<CrmCcAccountContactResultDto> getAccountEntityList(
                CrmCcAccountContactCriteriaDto criteriadto) {
            if (criteriadto.crmContactCd == null) {
                throw new IllegalArgumentException();
            }
            return crmCcAccountContactDao.getAccountEntityList(criteriadto);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PagingResult<CrmCcAccountContactResultDto> getContactEntityList(
            CrmCcAccountContactCriteriaDto criteriadto) {
        return accountContactCustomLogic.getContactEntityList(criteriadto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PagingResult<CrmCcAccountContactResultDto> getAccountEntityList(
            CrmCcAccountContactCriteriaDto criteriadto) {
        return accountContactCustomLogic.getAccountEntityList(criteriadto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createContact(
            List<AccountContactCriteriaDto> accountContactCriteriaDtoList,
            String crmDomainCd) {
        ValidationResults vr = new ValidationResults();
        if (accountContactCriteriaDtoList.size() == 0) {
            vr.add(new ValidationResult(MessageBuilder
                .create("E.CRM.CC.00001")
                .addParameter(MessageBuilder.$("CRM.CC.crmContact"))
                .toMessage()));
            throw new ValidationException(vr);

        }
        create(accountContactCriteriaDtoList, crmDomainCd);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createAccount(
            List<AccountContactCriteriaDto> accountContactCriteriaDtoList,
            String crmDomainCd) {
        ValidationResults vr = new ValidationResults();
        if (accountContactCriteriaDtoList.size() == 0) {
            vr.add(new ValidationResult(MessageBuilder
                .create("E.CRM.CC.00001")
                .addParameter(MessageBuilder.$("CRM.CC.crmAccount"))
                .toMessage()));
            throw new ValidationException(vr);
        }
        create(accountContactCriteriaDtoList, crmDomainCd);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void create(
            List<AccountContactCriteriaDto> accountContactCriteriaDtoList,
            String crmDomainCd) {
        CrmAccountCheckChargeDto crmAccountCheckChargeDto =
            new CrmAccountCheckChargeDto();
        CrmContactCheckChargeDto crmContactCheckChargeDto =
            new CrmContactCheckChargeDto();
        ValidationResults vr = new ValidationResults();
        SimpleDateFormat dateFormat =
            new SimpleDateFormat(DateUtil.DATE_FORMAT_HYPHEN);
        String sysDate = dateFormat.format(DateUtil.nowDate()).toString();
        for (AccountContactCriteriaDto accountContactCriteriaDto : accountContactCriteriaDtoList) {
            List<CrmCcAccountContact> resultList =
                crmCcAccountContactDao
                    .getCreateEntityList(accountContactCriteriaDto);
            if (resultList.size() == 0) {

                // アカウントコンタクトに登録します。
                create(accountContactCriteriaDto);
            } else if ((resultList.size() == 1)
                && (!resultList.get(0).isDeleteFlag())) {
                vr.add(new ValidationResult(MessageBuilder
                    .create("E.CRM.CC.00016")
                    .addParameter(MessageBuilder.$("CRM.CC.crmAccountCd"))
                    .addParameter(
                        MessageBuilder
                            .$(accountContactCriteriaDto.crmAccountCd))
                    .addParameter(MessageBuilder.$("CRM.CC.crmContactCd"))
                    .addParameter(
                        MessageBuilder
                            .$(accountContactCriteriaDto.crmContactCd))
                    .toMessage()));
                throw new ValidationException(vr);
            } else if ((resultList.size() == 1)
                && (resultList.get(0).isDeleteFlag())
                && (sysDate.equals(dateFormat.format(
                    resultList.get(0).getStartDate()).toString()))) {

                // アカウントコンタクトの論理削除取消を行いします。
                delete(resultList.get(0));
            } else if ((resultList.size() == 1)
                && (resultList.get(0).isDeleteFlag())
                && (!sysDate.equals(dateFormat.format(
                    resultList.get(0).getStartDate()).toString()))) {

                // アカウントコンタクトの登録と更新を行いします。
                createUpdate(resultList.get(0));
            } else if (2 <= resultList.size()) {
                Message message = new Message();
                message.setMessageCode("F.CRM.CC.00002");
                throw new IllegalValidatorException(message);
            }
            crmAccountCheckChargeDto.crmAccountCdList
                .add(accountContactCriteriaDto.crmAccountCd);
            crmContactCheckChargeDto.crmContactCdList
                .add(accountContactCriteriaDto.crmContactCd);
        }
        crmAccountCheckChargeDto.userCd =
            contextContainer.getUserContext().getUserID();
        crmAccountCheckChargeDto.crmDomainCd = crmDomainCd;
        crmAccountCheckChargeDto.baseDate = DateUtil.nowDate();
        List<String> accoundCdResult =
            crmAccountLogic.checkCharge(crmAccountCheckChargeDto);
        crmContactCheckChargeDto.userCd =
            contextContainer.getUserContext().getUserID();
        crmContactCheckChargeDto.baseDate = DateUtil.nowDate();
        List<String> contactCdResultList =
            crmContactLogic.checkCharge(crmContactCheckChargeDto);
        if (accoundCdResult.size() != 0) {
            vr.add(new ValidationResult(MessageBuilder
                .create("E.CRM.CC.00015")
                .addParameter(MessageBuilder.$("CRM.CC.crmAccountCd"))
                .addParameter(MessageBuilder.$(accoundCdResult.get(0)))
                .toMessage()));
        }
        if (contactCdResultList.size() != 0) {
            for (String crmCcContactCd : contactCdResultList) {
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
     * アカウントコンタクトに登録します。
     * 
     * @param accountContactCriteriaDto
     *            アカウントコンタクト条件Dto
     */
    private void create(AccountContactCriteriaDto accountContactCriteriaDto) {
        CrmCcAccountContact crmCcAccountContact =
            beanMapper
                .map(accountContactCriteriaDto, CrmCcAccountContact.class);
        setCrmCcAccountContact(crmCcAccountContact);

        crmCcAccountContact.setTermCd(UniqueIdGenerator.getInstance().create());
        crmCcAccountContact.setStartDate(bizConfigurationProvider
            .getStartDate());
        crmCcAccountContact.setEndDate(DateUtil.nowDate());
        crmCcAccountContact.setDeleteFlag(true);
        crmCcAccountContactDao.insert(crmCcAccountContact);

        crmCcAccountContact.setTermCd(UniqueIdGenerator.getInstance().create());
        crmCcAccountContact.setStartDate(DateUtil.nowDate());
        crmCcAccountContact.setEndDate(bizConfigurationProvider.getEndDate());
        crmCcAccountContact.setDeleteFlag(false);
        crmCcAccountContactDao.insert(crmCcAccountContact);
    }

    /**
     * アカウントコンタクトの論理削除取消を行いします。（楽観的排他）。
     * 
     * @param crmCcAccountContact
     *            アカウントコンタクト条件Dto
     */
    private void delete(CrmCcAccountContact crmCcAccountContact) {
        crmCcAccountContact.setDeleteFlag(false);
        crmCcAccountContact.setRecordUserCd(contextContainer
            .getUserContext()
            .getUserID());
        crmCcAccountContact.setRecordDate(DateUtil.nowDate());
        crmCcAccountContactDao.updateIncludes(
            crmCcAccountContact,
            new PropertyName<String>("deleteFlag"),
            new PropertyName<String>("versionNo"),
            new PropertyName<String>("recordUserCd"),
            new PropertyName<String>("recordDate"));
    }

    /**
     * アカウントコンタクトの登録と更新を行いします。（楽観的排他）
     * 
     * @param crmCcAccountContact
     *            アカウントコンタクト条件Dto
     */
    private void createUpdate(CrmCcAccountContact crmCcAccountContact) {
        Date accountContactEndDate = crmCcAccountContact.getEndDate();
        crmCcAccountContact.setEndDate(DateUtil.nowDate());
        crmCcAccountContact.setDeleteFlag(true);
        crmCcAccountContact.setRecordUserCd(contextContainer
            .getUserContext()
            .getUserID());
        crmCcAccountContact.setRecordDate(DateUtil.nowDate());
        crmCcAccountContactDao.updateIncludes(
            crmCcAccountContact,
            new PropertyName<String>("startDate"),
            new PropertyName<String>("endDate"),
            new PropertyName<String>("deleteFlag"),
            new PropertyName<String>("versionNo"),
            new PropertyName<String>("recordUserCd"),
            new PropertyName<String>("recordDate"));
        crmCcAccountContact.setTermCd(UniqueIdGenerator.getInstance().create());
        crmCcAccountContact.setStartDate(DateUtil.nowDate());
        crmCcAccountContact.setEndDate(accountContactEndDate);
        setCrmCcAccountContact(crmCcAccountContact);
        crmCcAccountContact.setVersionNo(1L);
        crmCcAccountContact.setDeleteFlag(false);
        crmCcAccountContactDao.insert(crmCcAccountContact);
    }

    /**
     * アカウントコンタクトを設定します。
     * 
     * @param crmCcAccountContact
     *            アカウントコンタクト
     */
    private void setCrmCcAccountContact(CrmCcAccountContact crmCcAccountContact) {
        crmCcAccountContact.setDeleteFlag(false);
        crmCcAccountContact.setSortKey(0L);
        crmCcAccountContact.setCreateUserCd(contextContainer
            .getUserContext()
            .getUserID());
        crmCcAccountContact.setCreateDate(DateUtil.nowDate());
        crmCcAccountContact.setRecordUserCd(contextContainer
            .getUserContext()
            .getUserID());
        crmCcAccountContact.setRecordDate(DateUtil.nowDate());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(AccountContactCriteriaDto criteriaDto, String crmDomainCd) {
        ValidationResults vr = new ValidationResults();
        crmCcAccountContactDao.delete(criteriaDto);
        CrmAccountCheckChargeDto crmAccountCheckChargeDto =
            new CrmAccountCheckChargeDto();

        crmAccountCheckChargeDto.crmAccountCdList.add(criteriaDto.crmAccountCd);
        crmAccountCheckChargeDto.userCd =
            contextContainer.getUserContext().getUserID();
        crmAccountCheckChargeDto.crmDomainCd = crmDomainCd;
        crmAccountCheckChargeDto.baseDate = DateUtil.nowDate();

        CrmContactCheckChargeDto crmContactCheckChargeDto =
            new CrmContactCheckChargeDto();
        crmContactCheckChargeDto.userCd = crmAccountCheckChargeDto.userCd;
        crmContactCheckChargeDto.crmContactCdList.add(criteriaDto.crmContactCd);
        crmContactCheckChargeDto.baseDate = crmAccountCheckChargeDto.baseDate;
        List<String> accountCdResultList =
            crmAccountLogic.checkCharge(crmAccountCheckChargeDto);
        List<String> contactCdResultList =
            crmContactLogic.checkCharge(crmContactCheckChargeDto);
        if (accountCdResultList.size() != 0) {
            for (String crmAccountCd : accountCdResultList) {
                vr.add(new ValidationResult(MessageBuilder.create(
                    "E.CRM.CC.00015").addParameter(
                    MessageBuilder.$("CRM.CC.crmAccountCd")).addParameter(
                    MessageBuilder.$(crmAccountCd)).toMessage()));
            }
        }
        if (contactCdResultList.size() != 0) {
            for (String crmCcContactCd : contactCdResultList) {
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
    public CrmCcAccountContact getEntity(CrmCcAccountContactDto dto) {

        // 入力値．CRMアカウントコードの必須チェック
        if (dto.crmAccountCd == null) {
            throw new IllegalArgumentException();
        }

        // 入力値．CRMコンタクトコードの必須チェック
        if (dto.crmContactCd == null) {
            throw new IllegalArgumentException();
        }

        // 入力値．期間（自）の必須チェック
        if (dto.startDate == null) {
            throw new IllegalArgumentException();
        }

        // 入力値．期間（至）の必須チェック
        if (dto.endDate == null) {
            throw new IllegalArgumentException();
        }

        // 期間（至）は 期間（至）+ 1日 に編集します。
        dto.endDate = DateUtil.getCalculator(dto.endDate).addDay(1).asDate();

        // CRMアカウントコンタクト一件を検索します。
        CrmCcAccountContact crmCcAccountContact =
            crmCcAccountContactDao.getEntity(dto);

        // 例外時のメッセージについて
        if (crmCcAccountContact == null) {
            Message e = new Message();
            e.setMessageCode("E.CRM.CC.00065");
            throw new IllegalValidatorException(e);
        }

        // 戻り値.終了日の出力編集は、終了日-1日 に編集します。
        crmCcAccountContact.setEndDate(DateUtil.getCalculator(
            crmCcAccountContact.getEndDate()).addDay(-1).asDate());

        return crmCcAccountContact;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void termUpdateCheck(CrmCcAccountContactCheckItemDto dto) {
        // 入力値.終了日 + 1日
        Date endDate =
            DateUtil.getCalculator(dto.getEndDate()).addDay(1).asDate();

        ValidationResults vr;
        // 開始日、終了日の大小比較チェックを行います。
        if (0 < dto.getStartDate().compareTo(endDate)) {
            vr = new ValidationResults();
            vr.add(new ValidationResult(MessageBuilder
                .create("E.CRM.CC.00005")
                .addParameter(MessageBuilder.$("CRM.CC.effectiveTermTo"))
                .addParameter(MessageBuilder.$("CRM.CC.effectiveTermFrom"))
                .toMessage()));
            throw new ValidationException(vr);
        }

        // 開始日有効チェックを行います。
        Date sysStartDate = bizConfigurationProvider.getStartDate();
        if (0 < sysStartDate.compareTo(dto.getStartDate())) {
            vr = new ValidationResults();
            vr.add(new ValidationResult(MessageBuilder
                .create("E.CRM.CC.00050")
                .toMessage()));
            throw new ValidationException(vr);
        }
        // 終了日有効チェックを行います。
        Date sysEndDate = bizConfigurationProvider.getEndDate();
        if (0 < endDate.compareTo(sysEndDate)) {
            vr = new ValidationResults();
            vr.add(new ValidationResult(MessageBuilder
                .create("E.CRM.CC.00051")
                .toMessage()));
            throw new ValidationException(vr);
        }

        // アカウントコンタクト（期間前情報）を取得します。
        CrmCcAccountContactDto crmCcAccountContactPreDto =
            new CrmCcAccountContactDto();
        crmCcAccountContactPreDto.companyCd = dto.getCompanyCd();
        crmCcAccountContactPreDto.crmAccountCd = dto.getCrmAccountCd();
        crmCcAccountContactPreDto.crmContactCd = dto.getCrmContactCd();
        crmCcAccountContactPreDto.endDate = dto.checkStartDate;

        // CRMアカウントコンタクト一件を検索します。
        CrmCcAccountContact crmCcPreAccountContact =
            crmCcAccountContactDao.getEntity(crmCcAccountContactPreDto);
        if (dto.checkStartDate.compareTo(sysStartDate) != 0) {
            // 取得チェックを行います。
            if (crmCcPreAccountContact == null) {
                if (dto.checkStartDate.compareTo(sysStartDate) != 0) {
                    throw new OptimisticLockRuntimeException(null);
                }
            } else {
                // 開始日期間跨りチェックを行います。
                if (0 <= crmCcPreAccountContact.getStartDate().compareTo(
                    dto.getStartDate())) {
                    vr = new ValidationResults();
                    vr.add(new ValidationResult(MessageBuilder.create(
                        "E.CRM.CC.00052").toMessage()));
                    throw new ValidationException(vr);
                }
            }
        }

        // アカウントコンタクト（期間後情報）を取得する。
        CrmCcAccountContactDto crmCcAccountContactPostDto =
            new CrmCcAccountContactDto();
        crmCcAccountContactPostDto.companyCd = dto.getCompanyCd();
        crmCcAccountContactPostDto.crmAccountCd = dto.getCrmAccountCd();
        crmCcAccountContactPostDto.crmContactCd = dto.getCrmContactCd();

        // ①.チェック期間（至）+1日
        Date checkEndDate =
            DateUtil.getCalculator(dto.checkEndDate).addDay(1).asDate();

        crmCcAccountContactPostDto.startDate = checkEndDate;

        // CRMアカウントコンタクト一件を検索します。
        CrmCcAccountContact crmCcPostAccountContact =
            crmCcAccountContactDao.getEntity(crmCcAccountContactPostDto);
        if (crmCcPostAccountContact == null) {
            // 情報が取得できなかった場合
            if (checkEndDate.compareTo(sysEndDate) != 0) {
                throw new OptimisticLockRuntimeException(null);
            }
        } else {
            // 情報が取得できた場合
            if (0 <= endDate.compareTo(crmCcPostAccountContact.getEndDate())) {
                vr = new ValidationResults();
                vr.add(new ValidationResult(MessageBuilder.create(
                    "E.CRM.CC.00053").toMessage()));
                throw new ValidationException(vr);
            }
        }

        // 期間情報の更新と登録を行います。
        updateAndInsertTermInfo(
            crmCcPostAccountContact,
            crmCcPreAccountContact,
            endDate,
            dto,
            checkEndDate);

        // CRMアカウントの更新とCRMコンタクトの更新をチェックします。
        checkCrmCcACUpdate(dto);

    }

    /**
     * 期間情報の更新と登録を行います。
     * 
     * @param crmCcPostAccountContact
     *            期間後情報
     * @param crmCcPreAccountContact
     *            期間前情報
     * @param endDate
     *            入力値.終了日 + 1日
     * @param dto
     *            アカウントコンタクト（チェック項目あり）モデル
     * @param checkEndDate
     *            ①.チェック期間（至）+1日
     */
    private void updateAndInsertTermInfo(
            CrmCcAccountContact crmCcPostAccountContact,
            CrmCcAccountContact crmCcPreAccountContact, Date endDate,
            CrmCcAccountContactCheckItemDto dto, Date checkEndDate) {

        // ②、③が共に取得できた場合の分岐
        if (crmCcPostAccountContact != null && crmCcPreAccountContact != null) {
            // ①.開始日＝①.チェック期間（自）
            if (0 == dto.getStartDate().compareTo(dto.checkStartDate)) {

                // ①.終了日+1日＝①.チェック期間（至）+1日
                if (0 == endDate.compareTo(checkEndDate)) {

                    // 期間情報更新を行います。
                    updateTermInfo(dto, endDate);
                } else {
                    // ①.終了日+1日＜①.チェック期間（至）+1日 と ①.終了日+1日＞①.チェック期間（至）+1日

                    // 期間情報更新を行います。
                    updateTermInfo(dto, endDate);

                    // 期間後情報更新を行います。
                    updateTermPostInfo(dto, crmCcPostAccountContact, endDate);
                }
            } else {
                // ①.開始日＜①.チェック期間（自）と ①.開始日＞①.チェック期間（自）

                // ①.終了日+1日＝①.チェック期間（至）+1日
                if (0 == endDate.compareTo(checkEndDate)) {

                    // 期間前情報更新を行います。
                    updateTermPreInfo(dto, crmCcPreAccountContact);

                    // 期間情報更新を行います。
                    updateTermInfo(dto, endDate);
                } else {
                    // ①.終了日+1日＜①.チェック期間（至）+1日 と ①.終了日+1日＞①.チェック期間（至）+1日

                    // 期間前情報更新を行います。
                    updateTermPreInfo(dto, crmCcPreAccountContact);

                    // 期間情報更新を行います。
                    updateTermInfo(dto, endDate);

                    // 期間後情報更新を行います。
                    updateTermPostInfo(dto, crmCcPostAccountContact, endDate);
                }
            }
            // ②のみ取得できた場合の分岐 （①.チェック期間（至） + 1日＝システム終了日のケース）
        } else if (crmCcPreAccountContact != null
            && crmCcPostAccountContact == null) {

            // ①.開始日＝①.チェック期間（自）
            if (0 == dto.getStartDate().compareTo(dto.checkStartDate)) {

                // ①.終了日+1日＜①.チェック期間（至）+1日
                if (0 < checkEndDate.compareTo(endDate)) {

                    // 期間情報更新を行います。
                    updateTermInfo(dto, endDate);

                    // 期間後情報登録を行います。
                    insertTermPostInfo(dto, endDate);
                } else if (0 == endDate.compareTo(checkEndDate)) {
                    // ①.終了日+1日＝①.チェック期間（至）+1日

                    // 期間情報更新を行います。
                    updateTermInfo(dto, endDate);
                }
            } else {
                // ①.開始日＜①.チェック期間（自） と // ①.開始日＞①.チェック期間（自）

                // ①.終了日+1日＜①.チェック期間（至）+1日
                if (0 < checkEndDate.compareTo(endDate)) {

                    // 期間前情報更新を行います。
                    updateTermPreInfo(dto, crmCcPreAccountContact);

                    // 期間情報更新を行います。
                    updateTermInfo(dto, endDate);

                    // 期間後情報登録を行います。
                    insertTermPostInfo(dto, endDate);
                } else if (0 == endDate.compareTo(checkEndDate)) {
                    // ①.終了日+1日＝①.チェック期間（至）+1日

                    // 期間前情報更新を行います。
                    updateTermPreInfo(dto, crmCcPreAccountContact);

                    // 期間情報更新を行います。
                    updateTermInfo(dto, endDate);
                }
            }
            // ③のみ取得できた場合の分岐（①.チェック期間（自）＝システム開始日のケース）
        } else if (crmCcPreAccountContact == null
            && crmCcPostAccountContact != null) {
            // ①.開始日＝①.チェック期間（自）
            if (0 == dto.getStartDate().compareTo(dto.checkStartDate)) {
                // 期間情報更新を行います。
                updateTermInfo(dto, endDate);

                // 期間後情報更新を行います。
                if (0 != endDate.compareTo(checkEndDate)) {
                    updateTermPostInfo(dto, crmCcPostAccountContact, endDate);
                }
            } else if (0 < dto.getStartDate().compareTo(dto.checkStartDate)) {
                // ①.開始日＞①.チェック期間（自）

                // 期間前情報を登録する。
                insertTermPreInfo(dto);

                // 期間情報更新を行います。
                updateTermInfo(dto, endDate);

                // 期間後情報更新を行います。
                if (0 != endDate.compareTo(checkEndDate)) {
                    updateTermPostInfo(dto, crmCcPostAccountContact, endDate);
                }
            }
        }
    }

    /**
     * CRMコンタクトの更新をチェックします。
     * 
     * @param dto
     *            アカウントコンタクト（チェック項目あり）モデル
     */
    private void checkCrmCcACUpdate(CrmCcAccountContactCheckItemDto dto) {

        // CRMアカウントエンティティを取得します。
        CrmCcAccountDto crmCcAccountDto = new CrmCcAccountDto();

        crmCcAccountDto.companyCd = dto.getCompanyCd();
        crmCcAccountDto.crmAccountCd = dto.getCrmAccountCd();
        crmCcAccountDto.localeId =
            contextContainer.getUserContext().getLocaleID();
        crmCcAccountDto.startDate = DateUtil.today();
        crmCcAccountDto.endDate = DateUtil.today();

        // アカウントの一件取得です。
        CrmCcAccount crmCcAccount =
            crmCcAccountDao.getAccountEntityWithoutDeleteFlag(crmCcAccountDto);

        // 更新チェックを判断します。
        if (crmCcAccount != null
            && !dto.crmAccountVersionNo.equals(crmCcAccount
                .getVersionNo()
                .toString())) {
            throw new OptimisticLockRuntimeException(null);
        }

        // CRMコンタクトエンティティを取得します。
        CrmCcContactDto crmCcContactDto = new CrmCcContactDto();

        crmCcContactDto.companyCd = dto.getCompanyCd();
        crmCcContactDto.crmContactCd = dto.getCrmContactCd();
        crmCcContactDto.localeId =
            contextContainer.getUserContext().getLocaleID();
        crmCcContactDto.startDate = DateUtil.today();
        crmCcContactDto.endDate = DateUtil.today();

        // コンタクトの一件取得です。
        CrmCcContact crmCcContact =
            crmCcContactDao.getEntityByDtoWithoutDeleteFlag(crmCcContactDto);

        // 更新チェックを判断します。
        if (crmCcContact != null
            && !dto.crmContactVersionNo.equals(crmCcContact
                .getVersionNo()
                .toString())) {
            throw new OptimisticLockRuntimeException(null);
        }
    }

    /**
     * 
     * 期間前情報更新を行います。
     * 
     * @param dto
     *            アカウントコンタクト（チェック項目あり）モデル
     * @param preDto
     *            CrmCcAccountContactエンティティクラス
     */
    private void updateTermPreInfo(CrmCcAccountContactCheckItemDto dto,
            CrmCcAccountContact preDto) {

        CrmCcAccountContact updateProInfo =
            beanMapper.map(preDto, CrmCcAccountContact.class);

        updateProInfo.setEndDate(dto.getStartDate());
        updateProInfo.setRecordUserCd(dto.getRecordUserCd());

        // 期間化更新を行います。
        termUpdate(updateProInfo);
    }

    /**
     * 
     * 期間前情報登録を行います。
     * 
     * @param dto
     *            アカウントコンタクト（チェック項目あり）モデル
     */
    private void insertTermPreInfo(CrmCcAccountContactCheckItemDto dto) {

        CrmCcAccountContact insertProInfo = new CrmCcAccountContact();

        insertProInfo.setCompanyCd(dto.getCompanyCd());
        insertProInfo.setCrmAccountCd(dto.getCrmAccountCd());
        insertProInfo.setCrmContactCd(dto.getCrmContactCd());
        insertProInfo.setStartDate(bizConfigurationProvider.getStartDate());
        insertProInfo.setEndDate(dto.getStartDate());
        insertProInfo.setDeleteFlag(true);
        insertProInfo.setCreateUserCd(dto.getCreateUserCd());
        insertProInfo.setRecordUserCd(dto.getRecordUserCd());

        // 期間化登録を行います。
        termCreate(insertProInfo);
    }

    /**
     * 
     * 期間情報更新を行います。
     * 
     * @param dto
     *            アカウントコンタクト（チェック項目あり）モデル
     * @param endDate
     *            終了日+1日
     */
    private void updateTermInfo(CrmCcAccountContactCheckItemDto dto,
            Date endDate) {

        CrmCcAccountContact updateProInfo =
            beanMapper.map(dto, CrmCcAccountContact.class);

        updateProInfo.setEndDate(endDate);

        // 期間化更新を行います。
        termUpdate(updateProInfo);
    }

    /**
     * 
     * 期間後情報更新を行います。
     * 
     * @param dto
     *            アカウントコンタクト（チェック項目あり）モデル
     * @param postDto
     *            CrmCcAccountContactエンティティクラス
     * @param endDate
     *            終了日+1日
     */
    private void updateTermPostInfo(CrmCcAccountContactCheckItemDto dto,
            CrmCcAccountContact postDto, Date endDate) {

        CrmCcAccountContact updateProInfo =
            beanMapper.map(postDto, CrmCcAccountContact.class);

        updateProInfo.setStartDate(endDate);
        updateProInfo.setRecordUserCd(dto.getRecordUserCd());

        // 期間化更新を行います。
        termUpdate(updateProInfo);
    }

    /**
     * 
     * 期間後情報登録を行います。
     * 
     * @param dto
     *            アカウントコンタクト（チェック項目あり）モデル
     * @param endDate
     *            終了日+1日
     */
    private void insertTermPostInfo(CrmCcAccountContactCheckItemDto dto,
            Date endDate) {

        CrmCcAccountContact insertProInfo = new CrmCcAccountContact();

        insertProInfo.setCompanyCd(dto.getCompanyCd());
        insertProInfo.setCrmAccountCd(dto.getCrmAccountCd());
        insertProInfo.setCrmContactCd(dto.getCrmContactCd());
        insertProInfo.setStartDate(endDate);
        insertProInfo.setEndDate(bizConfigurationProvider.getEndDate());
        insertProInfo.setDeleteFlag(true);
        insertProInfo.setCreateUserCd(dto.getCreateUserCd());
        insertProInfo.setRecordUserCd(dto.getRecordUserCd());

        // 期間化登録を行います。
        termCreate(insertProInfo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void termUpdate(CrmCcAccountContact dto) {

        CrmCcAccountContact updateDto =
            beanMapper.map(dto, CrmCcAccountContact.class);

        updateDto.setRecordDate(DateUtil.nowDate());

        crmCcAccountContactDao.update(updateDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void termCreate(CrmCcAccountContact dto) {

        CrmCcAccountContact insertDto =
            beanMapper.map(dto, CrmCcAccountContact.class);

        insertDto.setSortKey(0L);
        insertDto.setRecordDate(DateUtil.nowDate());

        crmCcAccountContactDao.insert(insertDto);
    }
}
