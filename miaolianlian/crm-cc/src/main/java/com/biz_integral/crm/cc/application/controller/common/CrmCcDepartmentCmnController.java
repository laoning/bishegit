/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.application.mapper.CrmCcDepartmentCmnMapper;
import com.biz_integral.crm.cc.domain.dto.CrmCcDepartmentCmnDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcDepartmentCmn;
import com.biz_integral.crm.cc.domain.logic.DepartmentLogic;
import com.biz_integral.extension.mvc.maskat.annotaion.Execute;
import com.biz_integral.extension.mvc.maskat.annotaion.Validation;
import com.biz_integral.foundation.core.context.ContextContainer;

/**
 * 
 * 組織コードの一件取得です。
 */
public class CrmCcDepartmentCmnController {

    /**
     * {@link ContextContainer コンテキストコンテナー}
     */
    @Resource
    protected DepartmentLogic departmentLogic;

    /**
     * 組織コードマッパー。
     */
    @Resource
    protected CrmCcDepartmentCmnMapper crmCcDepartmentCmnMapper;

    /**
     * 
     * 組織コードの一件取得です。
     * 
     * @param model
     *            　組織コード検索条件モデル
     * @return 組織コード検索結果モデル
     */
    @Execute
    @Validation
    public CrmCcDepartmentCmnResponseModel getDepartmentName(
            CrmCcDepartmentCmnRequestModel model) {
        if (model.departmentCd == null || model.departmentCd.length() == 0) {
            return new CrmCcDepartmentCmnResponseModel();
        }

        CrmCcDepartmentCmnDto criteria =
            crmCcDepartmentCmnMapper.convert(model);

        CrmCcDepartmentCmn crmCcDepartmentCmn =
            departmentLogic.getEntity(criteria);

        CrmCcDepartmentCmnResponseModel resultmodel =
            crmCcDepartmentCmnMapper.convert(crmCcDepartmentCmn);

        return resultmodel;

    }
}
