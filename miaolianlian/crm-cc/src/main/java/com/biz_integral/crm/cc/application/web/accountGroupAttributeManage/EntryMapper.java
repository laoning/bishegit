/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountGroupAttributeManage;

import java.util.List;

import com.biz_integral.crm.cc.domain.entity.CrmCcAccountGrpAttr;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountGrpModule;

/**
 * アカウントグループ属性登録／更新画面で利用するマッパーです。
 */
public interface EntryMapper {

    /**
     * アカウントグループ属性登録／更新画面で入力された情報をEntityに変換します。
     * 
     * @param EntryInitializeRequestModel
     *            アカウントグループ属性登録／更新画面で入力された情報
     * @return CrmCcAccountGrpAttr
     */
    public abstract CrmCcAccountGrpAttr convert(
            EntryInitializeRequestModel model);

    /**
     * アカウントグループ属性登録／更新画面で入力された情報をEntityに変換します。
     * 
     * @param entryFilterRequestModel
     *            アカウントグループ属性登録／更新画面で入力された情報
     * @return {@link AccountBelongClassSearchCriteriaDto}
     */
    public abstract CrmCcAccountGrpAttr convertToCrmCcAccountGrpAttr(
            EntryRequestModel model);

    /**
     * アカウントグループ属性登録／更新画面で入力された情報をEntityに変換します。
     * 
     * @param entryFilterRequestModel
     *            アカウントグループ属性登録／更新画面で入力された情報
     * @return {@link AccountBelongClassSearchCriteriaDto}
     */
    public abstract List<CrmCcAccountGrpModule> convertToCrmCcAccountGrpModule(
            EntryRequestModel model);
}
