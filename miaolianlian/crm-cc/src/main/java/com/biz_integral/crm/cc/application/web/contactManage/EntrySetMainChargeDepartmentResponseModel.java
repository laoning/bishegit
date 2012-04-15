/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.contactManage;

import java.util.ArrayList;
import java.util.List;

/**
 * アカウント登録/更新画面の担当組織主選択設定結果モデルです。
 */
public final class EntrySetMainChargeDepartmentResponseModel {

    /**
     * 担当組織一覧
     */
    public List<EntryContactChargeDeptResponseModel> contactChargeDeptResponseList =
        new ArrayList<EntryContactChargeDeptResponseModel>();

}
