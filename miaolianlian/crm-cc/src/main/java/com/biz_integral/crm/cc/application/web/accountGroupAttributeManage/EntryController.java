/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountGroupAttributeManage;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.biz_integral.crm.cc.domain.entity.CrmCcAccountGrpAttr;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountGrpModule;
import com.biz_integral.crm.cc.domain.logic.CrmAcctGrpAttrLogic;
import com.biz_integral.extension.mvc.maskat.annotaion.Execute;
import com.biz_integral.extension.mvc.maskat.annotaion.Validation;
import com.biz_integral.foundation.core.context.ContextContainer;

/**
 * アカウントグループ登録／更新画面のコントローラークラスです。<br/>
 */
public class EntryController {

    /**
     * {@link ContextContainer コンテキストコンテナー}
     */
    @Resource
    protected ContextContainer contextContainer;

    /**
     * {@link ContextContainer コンテキストコンテナー}
     */
    @Resource
    protected CrmAcctGrpAttrLogic crmAcctGrpAttrLogic;

    /**
     * {@link ContextContainer コンテキストコンテナー}
     */
    @Resource
    protected EntryMapper entryMapper;

    /**
     * 画面の初期化時に利用する情報を取得し、返します。<br/>
     * <p>
     * <ul>
     * <li>CRMアカウントグループ属性を取得します。</li>
     * </ul>
     * </p>
     * 
     * @param requestModel
     *            画面の初期化時に利用するリクエストモデル
     * @return CRMアカウントグループ属性情報をセットしたレスポンスモデル
     */
    @Execute
    public EntryResponseModel initialize(EntryInitializeRequestModel request) {

        EntryResponseModel response;

        // 登録モード
        if (StringUtils.isEmpty(request.processModeType)
            || !"update".equals(request.processModeType)) {
            response = crmAcctGrpAttrLogic.initialize(request);
        } else {
            // 更新モード
            CrmCcAccountGrpAttr dto = entryMapper.convert(request);

            // CRMCRMアカウントグループ属性利用モジュール設定を検索します。
            response = crmAcctGrpAttrLogic.initializeUpdate(dto);
        }
        return response;
    }

    /**
     * CRMアカウントグループ属性登録を行いします。
     * 
     * @param EntryRequestModel
     *            レスポンスモデル
     * @return なし
     */
    @Execute
    @Validation
    public EntryResponseModel entry(EntryRequestModel request) {

        // CRMアカウントグループ属性を登録します。
        CrmCcAccountGrpAttr dto =
            entryMapper.convertToCrmCcAccountGrpAttr(request);
        crmAcctGrpAttrLogic.create(dto);

        // CRMアカウントグループ利用モジュール設定を登録します。
        List<CrmCcAccountGrpModule> moduleDtoList =
            entryMapper.convertToCrmCcAccountGrpModule(request);
        crmAcctGrpAttrLogic.createModule(moduleDtoList);

        EntryResponseModel response = new EntryResponseModel();

        response.versionNo = crmAcctGrpAttrLogic.getVersionNo(dto);

        return response;
    }

    /**
     * CRMCRMアカウントグループ属性更新を行いします。
     * 
     * @param EntryRequestModel
     *            レスポンスモデル
     * @return なし
     */
    @Execute
    @Validation
    public void update(EntryRequestModel request) {

        // CRMアカウントグループ属性を更新します。
        CrmCcAccountGrpAttr dto =
            entryMapper.convertToCrmCcAccountGrpAttr(request);
        crmAcctGrpAttrLogic.update(dto);

        // CRMアカウントグループ利用モジュール設定を更新します。
        List<CrmCcAccountGrpModule> moduleDtoList =
            entryMapper.convertToCrmCcAccountGrpModule(request);
        crmAcctGrpAttrLogic.createModule(moduleDtoList);
    }

    /**
     * CRMアカウントグループ属性の削除を行いします。
     * 
     * @param EntryRequestModel
     *            レスポンスモデル
     * @return なし
     */
    @Execute
    @Validation
    public void delete(EntryRequestModel request) {

        // CRMアカウントグループ属性を登録します。
        CrmCcAccountGrpAttr dto =
            entryMapper.convertToCrmCcAccountGrpAttr(request);
        crmAcctGrpAttrLogic.delete(dto);
    }

}
