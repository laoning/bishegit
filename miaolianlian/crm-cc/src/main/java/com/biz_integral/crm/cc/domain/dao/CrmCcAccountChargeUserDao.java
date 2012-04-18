/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.dao;

import java.util.List;

import com.biz_integral.crm.cc.domain.dto.CrmCcAccountChargeUserCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountChargeUserResultDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountChargeUser;
import com.biz_integral.crm.sa.domain.dto.SalesActCheckDto;
import com.biz_integral.foundation.core.util.DateUtil;

/**
 * {@link CrmCcAccountChargeUser}のDaoクラスです。
 */
public class CrmCcAccountChargeUserDao extends
        AbstractCrmCcAccountChargeUserDao<CrmCcAccountChargeUser> {

    /**
     * CRMアカウント担当者有効チェック（営業活動日）用SQL
     */
    private static final String ACCOUNT_CHARGE_USER_ENABLE_CHECK =
        "CrmCcAccountChargeUserDao_crmAccountChargeUserEnableCheck.sql";

    /**
     * CRMアカウント担当者有効チェック（営業活動日）を行います。
     * 
     * @param checkDto
     *            営業活動（チェック項目あり）モデル
     * @return true（OKの場合）／false（NGの場合）
     */
    public boolean crmAccountChargeUserEnableCheck(SalesActCheckDto checkDto) {

        boolean result = true;
        long count =
            super.getCountBySqlFile(ACCOUNT_CHARGE_USER_ENABLE_CHECK, checkDto);
        if (0 == count) {
            result = false;
        }

        return result;
    }

    /**
     * CRMアカウント担当者を一覧検索します。
     * 
     * @param criteriaDto
     *            CRMアカウント担当者検索のDto
     * @return CRM担当者一覧検索結果
     */
    public List<CrmCcAccountChargeUserResultDto> getEntityList(
            CrmCcAccountChargeUserCriteriaDto criteriaDto) {

        List<CrmCcAccountChargeUserResultDto> resultList =
            super.findBySqlFile(
                CrmCcAccountChargeUserResultDto.class,
                "CrmCcAccountChargeUserDao_getEntityList.sql",
                criteriaDto);
        for (CrmCcAccountChargeUserResultDto dto : resultList) {
            dto.endDate =
                DateUtil.getCalculator(dto.endDate).addDay(-1).asDate();
            dto.userCmnEndDate =
                DateUtil.getCalculator(dto.userCmnEndDate).addDay(-1).asDate();
        }
        return resultList;
    }

    /**
     * 登録前CRMアカウント担当者を一覧検索します。
     * 
     * @param chargeUserCriteriaDto
     *            CRMアカウント担当者者検索条件のDto
     * @return CRMアカウント担当者者検索結果のDto
     */
    public List<CrmCcAccountChargeUser> getCreateEntityList(
            CrmCcAccountChargeUserCriteriaDto chargeUserCriteriaDto) {
        List<CrmCcAccountChargeUser> resultList =
            super.findBySqlFile(
                CrmCcAccountChargeUser.class,
                "CrmCcAccountChargeUserDao_getCreateEntityList.sql",
                chargeUserCriteriaDto);
        return resultList;
    }
}