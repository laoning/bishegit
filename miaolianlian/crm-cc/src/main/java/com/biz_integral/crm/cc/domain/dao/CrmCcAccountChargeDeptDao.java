/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.dao;

import java.util.List;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.domain.dto.CrmCcAccountChargeDeptCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountChargeDeptResultDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountChargeDept;
import com.biz_integral.crm.cc.domain.logic.ParameterLogic;
import com.biz_integral.foundation.core.util.DateUtil;

/**
 * {@link CrmCcAccountChargeDept}のDaoクラスです。
 * 
 */
public class CrmCcAccountChargeDeptDao extends
        AbstractCrmCcAccountChargeDeptDao<CrmCcAccountChargeDept> {

    /**
     * パラメータロジック
     */
    @Resource
    protected ParameterLogic parameterLogic;

    /**
     * CRMアカウント担当組織を一覧検索します。
     * 
     * @param chargeDeptCriteriaDto
     *            CRMアカウント担当組織検索のDto
     * @return CRM担当組織一覧検索結果
     */
    public List<CrmCcAccountChargeDeptResultDto> getEntityList(
            CrmCcAccountChargeDeptCriteriaDto chargeDeptCriteriaDto) {
        Object departmentSetCd = parameterLogic.getEntity("CRMCC0004");
        if (departmentSetCd != null) {
            chargeDeptCriteriaDto.departmentSetCd = departmentSetCd.toString();
        }
        List<CrmCcAccountChargeDeptResultDto> resultList =
            super.findBySqlFile(
                CrmCcAccountChargeDeptResultDto.class,
                "CrmCcAccountChargeDeptDao_getEntityList.sql",
                chargeDeptCriteriaDto);

        for (CrmCcAccountChargeDeptResultDto dto : resultList) {
            dto.endDate =
                DateUtil.getCalculator(dto.endDate).addDay(-1).asDate();
            dto.deptCmnEndDate =
                DateUtil.getCalculator(dto.deptCmnEndDate).addDay(-1).asDate();
        }
        return resultList;
    }

    /**
     * 登録前CRMアカウント担当組織を一覧検索します。
     * 
     * @param chargeDeptCriteriaDto
     *            CRMアカウント担当組織検索条件のDto
     * @return CRMアカウント担当組織検索結果
     */
    public List<CrmCcAccountChargeDept> getCreateEntityList(
            CrmCcAccountChargeDeptCriteriaDto chargeDeptCriteriaDto) {
        List<CrmCcAccountChargeDept> resultList =
            super.findBySqlFile(
                CrmCcAccountChargeDept.class,
                "CrmCcAccountChargeDeptDao_getCreateEntityList.sql",
                chargeDeptCriteriaDto);
        return resultList;
    }
}