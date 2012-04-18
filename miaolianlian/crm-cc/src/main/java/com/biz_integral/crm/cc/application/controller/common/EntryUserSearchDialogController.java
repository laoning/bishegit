/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import com.biz_integral.extension.mvc.maskat.annotaion.Execute;
import com.biz_integral.extension.mvc.maskat.annotaion.Validation;

/**
 * 登録済ユーザ検索（表示条件指定）ダイアログ画面のコントローラです。 <br/>
 */
public class EntryUserSearchDialogController {

    /**
     * 検索ボタン押下の処理です。
     * 
     * @param request
     *            登録済ユーザ検索（表示条件指定）検索リクエストモデル
     */
    @Execute
    @Validation
    public void filter(EntryUserSearchDialogFilterRequestModel request) {
    }
}
