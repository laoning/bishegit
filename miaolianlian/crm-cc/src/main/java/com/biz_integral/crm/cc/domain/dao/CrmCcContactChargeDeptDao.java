/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.dao;

import java.util.List;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.domain.dto.CrmCcContactChargeDeptCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactChargeDeptResultDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcContactChargeDept;
import com.biz_integral.crm.cc.domain.logic.ParameterLogic;
import com.biz_integral.foundation.core.util.DateUtil;

/**
 * {@link CrmCcContactChargeDept}のDaoクラスです。
 * 
 */
public class CrmCcContactChargeDeptDao extends
        AbstractCrmCcContactChargeDeptDao<CrmCcContactChargeDept> {

    /**
     * パラメータロジック
     */
    @Resource
    protected ParameterLogic parameterLogic;

    /**
     * CRMコンタクト担当組織を一覧検索します。
     * 
     * @param chargeDeptCriteriaDto
     *            CRMコンタクト担当組織検索のDto
     * @return CRM担当組織一覧検索結果
     */
    public List<CrmCcContactChargeDeptResultDto> getEntityList(
            CrmCcContactChargeDeptCriteriaDto chargeDeptCriteriaDto) {
        Object departmentSetCd = parameterLogic.getEntity("CRMCC0004");
        if (departmentSetCd != null) {
            chargeDeptCriteriaDto.departmentSetCd = departmentSetCd.toString();
        }
        List<CrmCcContactChargeDeptResultDto> resultList =
            super.findBySqlFile(
                CrmCcContactChargeDeptResultDto.class,
                "CrmCcContactChargeDeptDao_getEntityList.sql",
                chargeDeptCriteriaDto);

        for (CrmCcContactChargeDeptResultDto dto : resultList) {
            dto.endDate =
                DateUtil.getCalculator(dto.endDate).addDay(-1).asDate();
            dto.deptCmnEndDate =
                DateUtil.getCalculator(dto.deptCmnEndDate).addDay(-1).asDate();
        }
        return resultList;
    }

    /**
     * 登録前CRMコンタクト担当組織を一覧検索します。
     * 
     * @param chargeDeptCriteriaDto
     *            CRMコンタクト担当組織検索条件のDto
     * @return CRMコンタクト担当組織検索結果のDto
     */
    public List<CrmCcContactChargeDept> getCreateEntityList(
            CrmCcContactChargeDeptCriteriaDto chargeDeptCriteriaDto) {
        List<CrmCcContactChargeDept> resultList =
            super.findBySqlFile(
                CrmCcContactChargeDept.class,
                "CrmCcContactChargeDeptDao_getCreateEntityList.sql",
                chargeDeptCriteriaDto);
        return resultList;
    }
}