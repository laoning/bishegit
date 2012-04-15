/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic;

import java.util.List;

import com.biz_integral.crm.cc.domain.dto.ChargeDeptListChangeAfterDto;
import com.biz_integral.crm.cc.domain.dto.ChargeDeptListChangeBeforeDto;
import com.biz_integral.crm.cc.domain.dto.ChargeDeptListChangeResultDto;

/**
 * CRM担当組織一覧のロジックです。
 */
public interface CrmChargeDepartmentListLogic {

    /**
     * CRM担当組織一覧の一覧設定します。
     * 
     * @param changeAfterList
     *            担当組織設定ボタン変更後条件Dto
     * @param changeBeforeList
     *            担当組織設定ボタン変更前条件Dto
     * @return 担当組織一覧
     */
    public abstract List<ChargeDeptListChangeResultDto> setDepartmentList(
            List<ChargeDeptListChangeAfterDto> changeAfterList,
            List<ChargeDeptListChangeBeforeDto> changeBeforeList);

    /**
     * CRM担当組織主担当者設定します。
     * 
     * @param departmentCd
     *            組織コード
     * @param changeBeforeDtoList
     *            主選択設定条件Dto
     * @return 担当組織一覧
     */
    public abstract List<ChargeDeptListChangeResultDto> setMainChargeDepartment(
            String departmentCd,
            List<ChargeDeptListChangeBeforeDto> changeBeforeDtoList);

}
