/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.entity;

import com.biz_integral.crm.cc.domain.entity.AbstractCrmCcDomain;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * CrmCcDomainエンティティクラスです。
 * 
 * @author S2JDBC-Gen
 */
@Entity
@Table(
    name = "crm_cc_domain")
public class CrmCcDomain extends AbstractCrmCcDomain {

    /**
     * デフォルト・シリアルバージョンID
     */
    private static final long serialVersionUID = 1L;
}
