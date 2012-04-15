/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountClassManage;

import java.util.List;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.domain.entity.CrmCcAccountClass;
import com.biz_integral.crm.cc.domain.logic.CrmAccountClassLogic;
import com.biz_integral.extension.mvc.maskat.annotaion.Execute;
import com.biz_integral.extension.mvc.maskat.annotaion.Validation;
import com.biz_integral.extension.mvc.maskat.beans.KeyValueBean;
import com.biz_integral.extension.mvc.maskat.beans.MessageDetailResponse;
import com.biz_integral.extension.mvc.maskat.beans.MessageResponse.MessageType;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.util.CollectionsUtil;
import com.biz_integral.service.domain.master.locale.BizLocaleInfo;
import com.biz_integral.service.domain.master.locale.BizLocaleManager;

/**
 * アカウント分類登録／更新画面のコントローラです。
 */
public class EntryController {

    /**
     * {@link EntryMapper アカウント分類登録／更新Mapper}
     */
    @Resource
    protected EntryMapper entryMapper;

    /**
     * {@link CrmAccountClassLogic アカウント分類Logic}
     */
    @Resource
    protected CrmAccountClassLogic crmAccountClassLogic;

    /**
     * {@link ContextContainer コンテキストコンテナ}
     */
    @Resource
    protected ContextContainer contextContainer;

    /**
     * {@link BizLocaleManager ロケールマネージャー}
     */
    @Resource
    public BizLocaleManager bizLocaleManager;

    /**
     * 初期化を行います。
     * 
     * @return 画面初期化に利用する項目
     */
    @Execute
    public EntryInitializeResponseModel initialize() {

        EntryInitializeResponseModel model = new EntryInitializeResponseModel();

        // ロケールIDの一覧を取得します。
        model.localeIdList.addAll(this.getLocaleForComboList());

        return model;
    }

    /**
     * 更新モード初期化を行います。
     * 
     * @param request
     *            アカウント登録/更新画面の初期化条件
     * @return 画面初期化に利用する項目
     */
    @Execute
    @Validation
    public EntryUpdateInitializeResponseModel updateInitialize(
            EntryUpdateInitializeRequestModel request) {

        CrmCcAccountClass entity = entryMapper.convert(request);
        CrmCcAccountClass resultEntity = crmAccountClassLogic.getByPk(entity);
        return entryMapper.convertInitialResponse(resultEntity);
    }

    /**
     * 登録ボタン押下の処理です。
     * 
     * @param request
     *            アカウント分類登録モデル
     */
    @Execute
    @Validation
    public void registration(EntryRegistrationRequestModel request) {
        CrmCcAccountClass entity = entryMapper.convert(request);
        crmAccountClassLogic.create(entity);
    }

    /**
     * 削除ボタン押下の確認処理です。
     * 
     * @param request
     *            アカウント分類削除モデル
     * @return 削除ボタン押下レスポンスモデル
     */
    @Execute
    @Validation
    public EntryDeleteResponseModel deleteConfirm(
            EntryDeleteRequestModel request) {
        CrmCcAccountClass entity = entryMapper.convert(request);
        EntryDeleteResponseModel model = new EntryDeleteResponseModel();
        entity.setDeleteFlag(false);
        if (crmAccountClassLogic.deleteConfirm(entity)) {
            // メッセージを生成します。
            MessageDetailResponse message =
                new MessageDetailResponse(MessageType.INFO, "I.CRM.CC.00019");
            model.add(message);

        }
        return model;
    }

    /**
     * 更新ボタン押下の処理です。
     * 
     * @param request
     *            アカウント分類更新モデル
     */
    @Execute
    @Validation
    public void update(EntryUpdateRequestModel request) {
        CrmCcAccountClass entity = entryMapper.convert(request);
        crmAccountClassLogic.update(entity);
    }

    /**
     * 削除ボタン押下の処理です。
     * 
     * @param request
     *            アカウント分類削除モデル
     */
    @Execute
    @Validation
    public void delete(EntryDeleteRequestModel request) {
        CrmCcAccountClass entity = entryMapper.convert(request);
        crmAccountClassLogic.delete(entity);
    }

    /**
     * ロケールコンボ用のリストを取得します。
     * 
     * @return ロケールコンボボックス用のリスト
     */
    private List<KeyValueBean> getLocaleForComboList() {

        List<BizLocaleInfo> bizLocaleInfoList =
            bizLocaleManager.getApplicationLocales(contextContainer
                .getCurrentFeatureContext()
                .getApplicationID());

        List<KeyValueBean> localeIdList = CollectionsUtil.newArrayList();
        for (BizLocaleInfo entity : bizLocaleInfoList) {
            KeyValueBean item = new KeyValueBean();
            item.key = entity.getLocaleId();
            item.value = entity.getDisplayName();
            localeIdList.add(item);
        }
        return localeIdList;
    }
}
