/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountManage;

import java.util.ArrayList;
import java.util.List;

/**
 * アカウント登録/更新画面の担当組織設定ボタン結果モデルです。
 */
public final class EntrySetDepartmentListResponseModel {

    /**
     * 担当組織一覧
     */
    public List<EntryAccountChargeDeptResponseModel> accountChargeDeptResponseList =
        new ArrayList<EntryAccountChargeDeptResponseModel>();

}
