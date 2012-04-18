/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountManage;

import static com.biz_integral.foundation.core.util.CollectionsUtil.newArrayList;

import java.util.List;

import com.biz_integral.extension.validation.annotation.Arg;
import com.biz_integral.extension.validation.annotation.BeanCollection;
import com.biz_integral.extension.validation.annotation.DomainConstraint;

/**
 * アカウント登録/更新画面の担当者主選択設定条件モデルです。
 */
public final class EntrySetMainChargeUserRequestModel {

    /**
     * 選択ユーザコード
     */
    @DomainConstraint(namespace = "crm.cc", type = "userCd", arg0 = @Arg(key = "CRM.CC.userCd"))
    public String selectedUserCd;

    /**
     * 担当者一覧
     */
    @BeanCollection
    public List<EntryGridChargeUserListRequestModel> gridChargeUserList =
        newArrayList();

}
