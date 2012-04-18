/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.entity;

import com.biz_integral.crm.cc.domain.entity.AbstractCrmCcContactChargeUser;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * CrmCcContactChargeUserエンティティクラスです。
 * 
 * @author S2JDBC-Gen
 */
@Entity
@Table(
    name = "crm_cc_contact_charge_user")
public class CrmCcContactChargeUser extends AbstractCrmCcContactChargeUser {

    /**
     * デフォルト・シリアルバージョンID
     */
    private static final long serialVersionUID = 1L;
}
