/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountBelongClassManage;

import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.domain.dto.AccountClassAthSearchResultDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountClassAthSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountClassAth;
import com.biz_integral.crm.cc.domain.logic.CrmAccountClassAthLogic;
import com.biz_integral.crm.cc.domain.logic.CrmAccountLogic;
import com.biz_integral.crm.cc.domain.logic.ExportFileManagerLogic;
import com.biz_integral.crm.cc.domain.logic.ParameterLogic;
import com.biz_integral.crm.cc.domain.types.ConfirmWay;
import com.biz_integral.extension.mvc.maskat.annotaion.Execute;
import com.biz_integral.extension.mvc.maskat.annotaion.Validation;
import com.biz_integral.extension.mvc.maskat.beans.KeyValueBean;
import com.biz_integral.extension.mvc.maskat.beans.MessageDetailResponse;
import com.biz_integral.extension.mvc.maskat.beans.PagingResponse;
import com.biz_integral.extension.mvc.maskat.beans.MessageResponse.MessageType;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.util.CollectionsUtil;
import com.biz_integral.service.domain.master.locale.BizLocaleInfo;
import com.biz_integral.service.domain.master.locale.BizLocaleManager;
import com.biz_integral.service.persistence.pager.PagingResult;
import com.biz_integral.service.storage.SharedFile;
import com.biz_integral.service.storage.SharedStorageManager;

/**
 * アカウント分類所属検索画面のコントローラです。
 */
public class SearchController {

    /**
     * {@link SearchMapper アカウント分類所属検索Mapper}
     */
    @Resource
    protected SearchMapper searchMapper;

    /**
     * {@link CrmAccountLogic アカウント分類所属Logic}
     */
    @Resource
    protected CrmAccountClassAthLogic crmAccountClassAthLogic;

    /**
     * 共有ストレージAPIです。
     */
    @Resource
    protected SharedStorageManager sharedStorageManager;

    /**
     * {@link ContextContainer コンテキストコンテナ}
     */
    @Resource
    protected ContextContainer contextContainer;

    /**
     * {@link BizLocaleManager ロケールマネージャ}
     */
    @Resource
    public BizLocaleManager bizLocaleManager;

    /**
     * {@link ParameterLogic}です
     */
    @Resource
    protected ParameterLogic parameterLogic;

    /**
     * {@link ExportFileManagerLogic 非同期タスクキュー登録ビジネスロジック}
     */
    @Resource
    protected ExportFileManagerLogic<CrmCcAccountClassAthSearchCriteriaDto> exportFileManagerLogic;

    /**
     * アカウント分類所属ファイル名
     */
    private static final String FILE_NAME_PARAMATER = "CRMCC0020";

    /**
     * 非同期タスクID
     */
    private static final String FILE_EXPORT =
        "crm.cc.accountBelongClassManage.search.export";

    /**
     * 初期化を行います。
     * 
     * @return 画面初期化に利用する項目
     */
    @Execute
    public SearchInitializeResponseModel initialize() {

        SearchInitializeResponseModel model =
            new SearchInitializeResponseModel();

        // ロケールIDの一覧を取得します。
        model.localeId.addAll(this.getLocaleForComboList());

        // 初期ロケールID
        model.defaultLocaleId = contextContainer.getUserContext().getLocaleID();

        // ファイル名を取得します
        model.fileName =
            parameterLogic.getEntity(FILE_NAME_PARAMATER).toString();

        return model;
    }

    /**
     * 検索結果一覧ページャー押下の処理です。
     * 
     * @param request
     *            アカウント分類所属一覧検索条件モデル
     * @return {@link SearchFilterResponseModel}
     */
    @Execute
    @Validation
    public PagingResponse<SearchFilterResponseModel> filter(
            SearchFilterRequestModel request) {

        CrmCcAccountClassAthSearchCriteriaDto criteria =
            searchMapper.convert(request);
        PagingResponse<SearchFilterResponseModel> result =
            new PagingResponse<SearchFilterResponseModel>();
        PagingResult<AccountClassAthSearchResultDto> pagingResult =
            crmAccountClassAthLogic.getAccountBelongClassList(criteria);

        PagingResult<SearchFilterResponseModel> pagingList =
            searchMapper.convert(pagingResult);

        result.setRows(pagingList.getResultList());
        result.setTotal(pagingList.getAllRecordCount());
        return result;
    }

    /**
     * 検索結果一覧ページャー押下の処理です。
     * 
     * @return List<{@link KeyValueBean}>
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

    /**
     * ファイルのインポートを行います。
     * 
     * @param requestModel
     *            ファイルのインポートの引数です。
     */
    @Execute
    public void importFile(SearchImportFileRequestModel requestModel) {

        String companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();

        // 「ファイル管理情報を取得する」について
        List<SharedFile> sharedFiles =
            sharedStorageManager.findFileInfo(requestModel.uploadContextId);

        // 「ファイルを取得する」について
        for (SharedFile sharedFile : sharedFiles) {

            InputStream uploadFileStream =
                sharedStorageManager.loadSharedFileAsStream(sharedFile
                    .getStoragePath());

            // アカウント分類所属モデルを作成します。
            List<CrmCcAccountClassAth> importList =
                crmAccountClassAthLogic.createModel(uploadFileStream);

            // アカウント分類コード、アカウントコードの存在チェックを実行します。
            crmAccountClassAthLogic.isExistModel(importList);

            // アカウント分類所属を更新します。
            crmAccountClassAthLogic.replace(companyCd, importList);
        }

    }

    /**
     * ファイルのエクスポート処理です。
     * 
     * @param request
     *            ファイルのエクスポート処理リクエストモデル
     * @return ファイルのエクスポート処理レスポンスモデル
     */
    @Execute
    public SearchExportFileResponseModel exportFile(
            SearchExportFileRequestModel request) {

        CrmCcAccountClassAthSearchCriteriaDto criteria =
            searchMapper.convert(request);

        // ファイルを作成します。
        SearchExportFileResponseModel model =
            new SearchExportFileResponseModel();
        model.fileName = request.fileName;

        // 入力値．確認方法＝区分値.確認方法.あとで確認する の場合
        if (ConfirmWay.LATER_CONFIRM.value().equals(request.confirmWay)) {

            // 非同期タスク登録IDを取得する
            exportFileManagerLogic.register(criteria, FILE_EXPORT);

            // メッセージを生成します。
            MessageDetailResponse message =
                new MessageDetailResponse(MessageType.INFO, "I.CRM.CC.00004");
            model.add(message);

        } else {

            model = crmAccountClassAthLogic.createFile(criteria);
            if (model.fileId == null) {
                // メッセージを生成します。
                MessageDetailResponse message =
                    new MessageDetailResponse(
                        MessageType.INFO,
                        "I.CRM.CC.00005");
                model.add(message);
            }
        }
        return model;
    }

}
