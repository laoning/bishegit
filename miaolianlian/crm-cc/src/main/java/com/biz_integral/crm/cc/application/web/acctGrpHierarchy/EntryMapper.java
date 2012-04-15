/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.acctGrpHierarchy;

import java.util.List;

import com.biz_integral.crm.cc.domain.dto.AcctGrpHierarchyDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAcctGrpIncAth;
import com.biz_integral.crm.sp.application.web.salesPlanList.ExportFileRequestModel;

/**
 * アカウントグループ階層登録／更新マッパー
 */
public interface EntryMapper {

    /**
     * モデルを作成します。
     * 
     * @param requestModel
     *            アカウントグループ階層登録／更新画面submitモデル
     * @return アカウントグループ階層登録／更新画面モデル
     */
    public abstract AcctGrpHierarchyDto createModel(
            EntryRequestModel requestModel);

    /**
     * モデルを作成します。
     * 
     * @param requestModel
     *            アカウントグループ階層登録／更新画面submitモデル
     * @return アカウントグループ階層登録／更新画面モデル
     */
    public abstract AcctGrpHierarchyDto createExportModel(
            ExportFileRequestModel model);

    /**
     * 登録初期化モデルを作成します。
     * 
     * @param requestModel
     *            アカウントグループ階層登録／更新初期表示submitモデル
     * @return アカウントグループ階層登録／更新初期化モデル
     */
    public abstract AcctGrpHierarchyDto createSearchModel(
            EntryInitializeRequestModel requestModel);

    /**
     * アカウントグループ階層登録／更新画面初期表示responseモデルを作成します。
     * 
     * @param model
     *            アカウントグループ階層DTOモデル
     * @return アカウントグループ階層登録／更新画面初期表示responseモデル
     */
    public abstract EntryInitializeResponseModel createResponseModel(
            List<AcctGrpHierarchyDto> list);

    /**
     * アカウントグループ階層登録／更新画面の下位から表示responseモデルを作成します。
     * 
     * @param model
     *            アカウントグループ階層DTOモデル
     * @return アカウントグループ階層登録／更新画面responseモデル
     */
    public abstract EntryInitializeResponseModel createRespMdlFromLower(
            List<AcctGrpHierarchyDto> list);

    /**
     * アカウントグループ階層登録／更新画面の下位から表示responseモデルを作成します。
     * 
     * @param model
     *            アカウントグループ階層DTOモデル
     * @return アカウントグループ階層登録／更新画面responseモデル
     */
    public abstract CrmCcAcctGrpIncAth entityMapper(CrmCcAcctGrpIncAth entity);

}
