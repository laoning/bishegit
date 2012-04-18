/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.entity;

import com.biz_integral.crm.cc.domain.entity.AbstractCrmCcItemCmn;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * CrmCcItemCmnエンティティクラスです。
 * 
 * @author S2JDBC-Gen
 */
@Entity
@Table(
    name = "crm_cc_item_cmn")
public class CrmCcItemCmn extends AbstractCrmCcItemCmn {

    /**
     * デフォルト・シリアルバージョンID
     */
    private static final long serialVersionUID = 1L;
}
