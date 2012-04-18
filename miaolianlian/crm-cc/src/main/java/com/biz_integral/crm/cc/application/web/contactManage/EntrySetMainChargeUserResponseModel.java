/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.contactManage;

import java.util.ArrayList;
import java.util.List;

/**
 * アカウント登録/更新画面の担当者主選択設定結果モデルです。
 */
public final class EntrySetMainChargeUserResponseModel {

    /**
     * 担当者一覧
     */
    public List<EntryContactChargeUserResponseModel> contactChargeUserResponseList =
        new ArrayList<EntryContactChargeUserResponseModel>();

}
