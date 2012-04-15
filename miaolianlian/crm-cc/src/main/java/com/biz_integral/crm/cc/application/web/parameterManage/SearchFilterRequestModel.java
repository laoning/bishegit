/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.parameterManage;

import com.biz_integral.extension.mvc.maskat.beans.PagingRequest;
import com.biz_integral.extension.validation.annotation.Arg;
import com.biz_integral.extension.validation.annotation.DomainConstraint;

/**
 * パラメータ一覧検索条件です。
 */
public class SearchFilterRequestModel extends PagingRequest {

    /**
     * 対象モジュール
     */
    @DomainConstraint(namespace = "crm.cc", type = "moduleCategory", arg0 = @Arg(key = "CRM.CC.moduleCategory"))
    public String moduleCategory;

    /**
     * パラメータ名
     */
    @DomainConstraint(namespace = "crm.cc", type = "parameterName", arg0 = @Arg(key = "CRM.CC.parameterName"))
    public String parameterName;

}
