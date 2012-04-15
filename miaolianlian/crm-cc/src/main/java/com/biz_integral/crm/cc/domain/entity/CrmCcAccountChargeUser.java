/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.entity;

import com.biz_integral.crm.cc.domain.entity.AbstractCrmCcAccountChargeUser;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * CrmCcAccountChargeUserエンティティクラスです。
 * 
 * @author S2JDBC-Gen
 */
@Entity
@Table(
    name = "crm_cc_account_charge_user")
public class CrmCcAccountChargeUser extends AbstractCrmCcAccountChargeUser {

    /**
     * デフォルト・シリアルバージョンID
     */
    private static final long serialVersionUID = 1L;
}
