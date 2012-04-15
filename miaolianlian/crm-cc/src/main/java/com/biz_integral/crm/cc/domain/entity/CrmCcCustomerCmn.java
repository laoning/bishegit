/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.entity;

import com.biz_integral.crm.cc.domain.entity.AbstractCrmCcCustomerCmn;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * CrmCcCustomerCmnエンティティクラスです。
 * 
 * @author S2JDBC-Gen
 */
@Entity
@Table(
    name = "crm_cc_customer_cmn")
public class CrmCcCustomerCmn extends AbstractCrmCcCustomerCmn {

    /**
     * デフォルト・シリアルバージョンID
     */
    private static final long serialVersionUID = 1L;
}
