/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountGroupAttributeManage;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.domain.dto.AccountGroupAttributeSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.AccountGroupAttributeSearchResultDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountGrpAttr;
import com.biz_integral.crm.cc.domain.entity.CrmCcModule;
import com.biz_integral.crm.cc.domain.logic.CrmAccountGroupAttributeLogic;
import com.biz_integral.crm.cc.domain.logic.CrmAccountLogic;
import com.biz_integral.crm.cc.domain.logic.CrmModuleLogic;
import com.biz_integral.crm.cc.domain.logic.ExportFileManagerLogic;
import com.biz_integral.crm.cc.domain.logic.FunctionAccessLogic;
import com.biz_integral.crm.cc.domain.logic.ParameterLogic;
import com.biz_integral.crm.cc.domain.types.ConfirmWay;
import com.biz_integral.crm.cc.domain.types.DealClass;
import com.biz_integral.extension.mvc.maskat.annotaion.Execute;
import com.biz_integral.extension.mvc.maskat.annotaion.Validation;
import com.biz_integral.extension.mvc.maskat.beans.KeyValueBean;
import com.biz_integral.extension.mvc.maskat.beans.MessageDetailResponse;
import com.biz_integral.extension.mvc.maskat.beans.PagingResponse;
import com.biz_integral.extension.mvc.maskat.beans.MessageResponse.MessageType;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.types.EnumNamesRegistry;
import com.biz_integral.service.persistence.LogicalDelete;
import com.biz_integral.service.persistence.pager.PagingResult;
import com.biz_integral.service.storage.SharedFile;
import com.biz_integral.service.storage.SharedStorageManager;

/**
 * アカウントグループ属性検索画面のコントローラです。
 */
public class SearchController {

    /**
     * {@link SearchMapper アカウントグループ属性検索Mapper}
     */
    @Resource
    protected SearchMapper searchMapper;

    /**
     * {@link CrmAccountLogic アカウントグループ属性Logic}
     */
    @Resource
    protected CrmAccountGroupAttributeLogic crmAccountGroupAttributeLogic;

    /**
     * {@link CrmModuleLogic 利用モジュール設定Logic}
     */
    @Resource
    protected CrmModuleLogic crmModuleLogic;

    /**
     * {@link ContextContainer コンテキストコンテナ}
     */
    @Resource
    protected ContextContainer contextContainer;

    /**
     * 共有ストレージAPIです。
     */
    @Resource
    protected SharedStorageManager sharedStorageManager;

    /**
     * 区分に関する名称管理APIです。
     */
    @Resource
    protected EnumNamesRegistry enumNamesRegistry;

    /**
     * {@link ParameterLogic}です
     */
    @Resource
    protected ParameterLogic parameterLogic;

    /**
     * {@link ExportFileManagerLogic 非同期タスクキュー登録ビジネスロジック}
     */
    @Resource
    protected ExportFileManagerLogic<AccountGroupAttributeSearchCriteriaDto> exportFileManagerLogic;

    /**
     * {@link FunctionAccessLogic}です
     */
    @Resource
    protected FunctionAccessLogic functionAccessLogic;

    /**
     * アカウントグループ属性ファイル名
     */
    private static final String FILE_NAME_PARAMATER = "CRMCC0023";

    /**
     * 非同期タスクID
     */
    private static final String FILE_EXPORT =
        "crm.cc.accountGroupAttributeManage.search.export";

    /**
     * アカウントグループ属性新規の権限ID
     */
    private static final String ACCESS_NEW_ACCT_GRP_ATTR_ID =
        "CRM.CC.FUNC_ACCESS_NEW_ACCT_GRP_ATTR";

    /**
     * 初期化を行います。
     * 
     * @return 画面初期化に利用する項目
     */
    @Execute
    public SearchInitializeResponseModel initialize() {

        SearchInitializeResponseModel model =
            new SearchInitializeResponseModel();

        model.moduleIdList.addAll(getModuleId());
        model.dealClassList.addAll(getDealClass());
        model.fileName = (String) parameterLogic.getEntity(FILE_NAME_PARAMATER);

        model.accountGroupAttributeNewFlag =
            functionAccessLogic.checkAuthority(ACCESS_NEW_ACCT_GRP_ATTR_ID);

        return model;
    }

    /**
     * 検索結果一覧ページャー押下の処理です。
     * 
     * @param request
     *            アカウントグループ属性一覧検索条件モデル
     * @return {@link SearchFilterResponseModel}
     */
    @Execute
    @Validation
    public PagingResponse<SearchFilterResponseModel> filter(
            SearchFilterRequestModel request) {

        AccountGroupAttributeSearchCriteriaDto criteria =
            searchMapper.convert(request);
        PagingResponse<SearchFilterResponseModel> result =
            new PagingResponse<SearchFilterResponseModel>();
        PagingResult<AccountGroupAttributeSearchResultDto> pagingResult =
            crmAccountGroupAttributeLogic
                .getAccountGroupAttributeList(criteria);

        PagingResult<SearchFilterResponseModel> pagingList =
            searchMapper.convert(pagingResult);

        result.setRows(pagingList.getResultList());
        result.setTotal(pagingList.getAllRecordCount());
        return result;
    }

    /**
     * ファイルのインポートを行います。
     * 
     * @param requestModel
     *            ファイルのインポートの引数です。
     */
    @Execute
    public void importFile(SearchImportFileRequestModel requestModel) {

        // 「ファイル管理情報を取得する」について
        List<SharedFile> sharedFiles =
            sharedStorageManager.findFileInfo(requestModel.uploadContextId);

        // 「ファイルを取得する」について
        for (SharedFile sharedFile : sharedFiles) {

            InputStream uploadFileStream =
                sharedStorageManager.loadSharedFileAsStream(sharedFile
                    .getStoragePath());

            // アカウントグループ属性モデルを作成します。
            List<CrmCcAccountGrpAttr> importList =
                crmAccountGroupAttributeLogic.createModel(
                    getCompanyCd(),
                    uploadFileStream);

            // アカウントグループ属性コード、アカウントコードの存在チェックを実行します。
            crmAccountGroupAttributeLogic.isExistModel(
                getCompanyCd(),
                getLocaleId(),
                importList);

            // アカウントグループ属性を更新します。
            crmAccountGroupAttributeLogic.replace(getCompanyCd(), importList);
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

        AccountGroupAttributeSearchCriteriaDto criteria =
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

            model = crmAccountGroupAttributeLogic.createFile(criteria);
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

    /**
     * モジュールID：コンボボックスのリストを作成します。<br/>
     * 
     * @return モジュールIDリスト
     */
    private List<KeyValueBean> getModuleId() {
        List<KeyValueBean> result = new ArrayList<KeyValueBean>();

        result.add(new KeyValueBean("", ""));

        for (CrmCcModule item : crmModuleLogic.findByCompanyCd(
            getCompanyCd(),
            LogicalDelete.AVAILABLE)) {
            result.add(new KeyValueBean(item.getModuleId(), item
                .getModuleName()));
        }

        return result;
    }

    /**
     * 区分値「取引種別：コンボボックス」のリストを作成します。<br/>
     * 
     * @return 区分値リスト
     */
    private List<KeyValueBean> getDealClass() {

        List<KeyValueBean> result = new ArrayList<KeyValueBean>();

        result.add(new KeyValueBean("", ""));

        for (DealClass item : DealClass.values()) {
            String name = this.enumNamesRegistry.getName(item, getLocale());
            result.add(new KeyValueBean(item.value(), name));
        }

        return result;
    }

    /**
     * ロケールを返却します。
     * 
     * @return ロケール
     */
    private Locale getLocale() {
        return new Locale(getLocaleId());
    }

    /**
     * ロケールIDを返却します。
     * 
     * @return ロケールID
     */
    private String getLocaleId() {
        return contextContainer.getUserContext().getLocaleID();
    }

    /**
     * 会社コードを返却します。
     * 
     * @return 会社コード
     */
    private String getCompanyCd() {
        return contextContainer.getCurrentFeatureContext().getCompanyCode();
    }
}
