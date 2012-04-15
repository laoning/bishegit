/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.entity;

import com.biz_integral.crm.cc.domain.entity.AbstractCrmCcDepartmentCmn;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * CrmCcDepartmentCmnエンティティクラスです。
 * 
 * @author S2JDBC-Gen
 */
@Entity
@Table(
    name = "crm_cc_department_cmn")
public class CrmCcDepartmentCmn extends AbstractCrmCcDepartmentCmn {

    /**
     * デフォルト・シリアルバージョンID
     */
    private static final long serialVersionUID = 1L;
}
