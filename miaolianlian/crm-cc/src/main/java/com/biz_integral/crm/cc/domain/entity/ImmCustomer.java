/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * ImmCustomerエンティティクラスです。
 * 
 * @author S2JDBC-Gen
 */
@Entity
@Table(name = "crm_cc_customer_cmn")
public class ImmCustomer extends AbstractImmCustomer {

    /**
     * デフォルト・シリアルバージョンID
     */
    private static final long serialVersionUID = 1L;
}
