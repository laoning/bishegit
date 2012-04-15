/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic;

import com.biz_integral.crm.cc.domain.dto.CrmCcDepartmentCmnDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcDepartmentCmn;

/**
 * 組織ロジックです。
 */
public interface DepartmentLogic {

    /**
     * 
     * 組織コード
     * 
     * @param dto
     *            組織コード一件取得条件
     * @return {@link CrmCcDepartmentCmn}
     */
    public CrmCcDepartmentCmn getEntity(CrmCcDepartmentCmnDto dto);
}
