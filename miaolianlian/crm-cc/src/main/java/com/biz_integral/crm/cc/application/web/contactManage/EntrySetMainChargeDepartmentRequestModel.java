/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.contactManage;

import static com.biz_integral.foundation.core.util.CollectionsUtil.newArrayList;

import java.util.List;

import com.biz_integral.extension.validation.annotation.Arg;
import com.biz_integral.extension.validation.annotation.BeanCollection;
import com.biz_integral.extension.validation.annotation.DomainConstraint;

/**
 * アカウント登録/更新画面の担当組織主選択設定条件モデルです。
 */
public final class EntrySetMainChargeDepartmentRequestModel {

    /**
     * 選択組織コード
     */
    @DomainConstraint(namespace = "crm.cc", type = "departmentCd", arg0 = @Arg(key = "CRM.CC.departmentCd"))
    public String selectedDepartmentCd;

    /**
     * 担当組織一覧グリッド
     */
    @BeanCollection
    public List<EntryGridChargeDeptListRequestModel> gridChargeDeptList =
        newArrayList();

}
