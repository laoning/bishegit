/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.entity;

import com.biz_integral.crm.cc.domain.entity.AbstractCrmCcAccountGrpModule;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * CrmCcAccountGrpModuleエンティティクラスです。
 * 
 * @author S2JDBC-Gen
 */
@Entity
@Table(
    name = "crm_cc_account_grp_module")
public class CrmCcAccountGrpModule extends AbstractCrmCcAccountGrpModule {

    /**
     * デフォルト・シリアルバージョンID
     */
    private static final long serialVersionUID = 1L;
}
