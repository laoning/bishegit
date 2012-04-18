/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.domain.dto.CrmPmProposalDto;
import com.biz_integral.crm.cc.domain.dto.ProposalCheckChargeDto;
import com.biz_integral.crm.cc.domain.dto.ProposalCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.ProposalResultDto;
import com.biz_integral.crm.cc.domain.logic.CrmCcDepartmentAthCmnLogic;
import com.biz_integral.crm.cc.domain.logic.ParameterLogic;
import com.biz_integral.crm.cc.domain.logic.ProposalLogic;
import com.biz_integral.crm.pm.domain.dao.CrmPmProposalDao;
import com.biz_integral.crm.pm.domain.entity.CrmPmProposal;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.exception.IllegalAuthorizationException;
import com.biz_integral.foundation.core.message.MessageBuilder;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.foundation.core.validation.ValidationException;
import com.biz_integral.foundation.core.validation.ValidationResult;
import com.biz_integral.foundation.core.validation.ValidationResults;
import com.biz_integral.service.persistence.pager.PagingResult;
import com.biz_integral.service.security.authorization.department.DepartmentAuthorizationEvaluator;
import com.biz_integral.service.security.authorization.functional.FunctionalAuthorization;

/**
 * ProposalLogicロジックの実装です。
 */
public class ProposalLogicImpl implements ProposalLogic {

    /**
     * {@link CrmPmProposalDao}
     */
    @Resource
    protected CrmPmProposalDao crmPmProposalDao;

    /**
     * パラメータロジック
     */
    @Resource
    protected ParameterLogic parameterLogic;

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
     * {@link CrmCcDepartmentAthCmnLogic 組織所属_共通Logic}
     */
    @Resource
    protected CrmCcDepartmentAthCmnLogic crmCcDepartmentAthCmnLogic;

    /**
     * コンテキストコンテナ。
     */
    @Resource
    protected ContextContainer contextContainer;

    /**
     * {@inheritDoc}
     */
    @Override
    public PagingResult<ProposalResultDto> getEntityList(
            ProposalCriteriaDto criteriadto) {
        return crmPmProposalDao.getEntityList(criteriadto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> checkCharge(
            ProposalCheckChargeDto proposalCheckChargeDto) {
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
                        "CRM.CC.DATA_ACCESS_PROPOSAL");

                List<String> userCdList = new ArrayList<String>();
                if (null != deptCdList && 0 < deptCdList.size()) {
                    userCdList =
                        crmCcDepartmentAthCmnLogic.getUserCdList(deptCdList);
                }
                if (null != userCdList && 0 < userCdList.size()) {
                    proposalCheckChargeDto.userCdList =
                        userCdList.toArray(new String[0]);
                }

            } else {
                proposalCheckChargeDto.userCdList =
                    new String[] { contextContainer
                        .getUserContext()
                        .getUserID() };
            }
            // 入力値の案件コードをOR条件
            if (null != proposalCheckChargeDto.crmProposalCdList
                && 0 < proposalCheckChargeDto.crmProposalCdList.size()) {
                proposalCheckChargeDto.proposalCdList =
                    proposalCheckChargeDto.crmProposalCdList
                        .toArray(new String[0]);
            }
            // 会社コード
            proposalCheckChargeDto.companyCd =
                contextContainer
                    .getApplicationContext()
                    .getFeatureContext()
                    .getCompanyCode();
            // 基準日
            proposalCheckChargeDto.baseDate = DateUtil.today();

            List<CrmPmProposal> entityList =
                crmPmProposalDao.findByChangerCheck(proposalCheckChargeDto);

            if (entityList == null || entityList.isEmpty()) {
                resultList = proposalCheckChargeDto.crmProposalCdList;
            } else {
                Set<String> pmProposalSet = new HashSet<String>();
                for (CrmPmProposal pmProposal : entityList) {
                    pmProposalSet.add(pmProposal.getProposalCd());
                }

                for (String pmProposal : proposalCheckChargeDto.crmProposalCdList) {
                    if (!pmProposalSet.contains(pmProposal)) {
                        resultList.add(pmProposal);
                    }
                }
            }
        }
        return resultList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isCharge(ProposalCheckChargeDto proposalCheckChargeDto) {
        ValidationResults vr = new ValidationResults();
        List<String> resultList = checkCharge(proposalCheckChargeDto);
        if (resultList.size() != 0) {
            for (String proposalCd : proposalCheckChargeDto.crmProposalCdList) {
                vr.add(new ValidationResult(MessageBuilder.create(
                    "E.CRM.CC.00015").addParameter(
                    MessageBuilder.$("CRM.CC.proposal")).addParameter(
                    MessageBuilder.$(proposalCd)).toMessage()));
            }
        }
        if (0 < vr.size()) {
            throw new ValidationException(vr);
        }
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
     *@return isAcessCustom
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
     * 
     * {@inheritDoc}
     */
    @Override
    public CrmPmProposal getEntity(CrmPmProposalDto dto) {
        if (dto.proposalCd == null) {
            throw new IllegalArgumentException();
        }

        Date date =
            DateUtil.parse(
                DateUtil.nowDateString(DateUtil.DATE_FORMAT),
                DateUtil.DATE_FORMAT);

        return crmPmProposalDao.getValidEntityNoException(
            dto.companyCd,
            dto.proposalCd,
            date);
    }
}
