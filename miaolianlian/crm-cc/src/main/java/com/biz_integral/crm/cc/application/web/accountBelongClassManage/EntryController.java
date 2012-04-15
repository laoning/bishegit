/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountBelongClassManage;

import java.util.List;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.domain.dto.AccountClassAthSearchResultDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountClassAthSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountClassAth;
import com.biz_integral.crm.cc.domain.logic.CrmAccountLogic;
import com.biz_integral.crm.cc.domain.logic.CrmAccountClassAthLogic;
import com.biz_integral.extension.mvc.maskat.annotaion.Execute;
import com.biz_integral.extension.mvc.maskat.annotaion.Validation;
import com.biz_integral.extension.mvc.maskat.beans.PagingResponse;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * アカウント分類所属登録／更新画面のコントローラです。
 */
public class EntryController {

    /**
     * {@link EntryMapper アカウント分類所属登録／更新Mapper}
     */
    @Resource
    protected EntryMapper entryMapper;

    /**
     * {@link CrmAccountLogic アカウント分類所属Logic}
     */
    @Resource
    protected CrmAccountClassAthLogic crmAccountClassAthLogic;

    /**
     * 初期化を行います。
     * 
     */
    @Execute
    public void initialize() {
        // プロセス実行権限で制御するために空の初期処理を作成
    }

    /**
     * 検索結果一覧ページャー押下の処理です。
     * 
     * @param request
     *            アカウント分類所属一覧検索条件モデル
     * @return {@link EntryFilterResponseModel}
     */
    @Execute
    @Validation
    public PagingResponse<EntryFilterResponseModel> filter(
            EntryFilterRequestModel request) {

        CrmCcAccountClassAthSearchCriteriaDto criteria =
            entryMapper.convert(request);
        PagingResponse<EntryFilterResponseModel> result =
            new PagingResponse<EntryFilterResponseModel>();
        PagingResult<AccountClassAthSearchResultDto> pagingResult =
            crmAccountClassAthLogic
                .getAccountBelongClassListForEdit(criteria);

        PagingResult<EntryFilterResponseModel> pagingList =
            entryMapper.convert(pagingResult);

        result.setRows(pagingList.getResultList());
        result.setTotal(pagingList.getAllRecordCount());
        return result;
    }

    /**
     * 追加ボタン押下の処理です。
     * 
     * @param request
     *            追加ボタン押下条件モデル
     */
    @Execute
    @Validation
    public void accountAdd(EntryAccountAddRequestModel request) {
        List<CrmCcAccountClassAth> entityList = entryMapper.convert(request);
        crmAccountClassAthLogic.create(entityList);
    }

    /**
     * 削除ボタン押下の処理です。
     * 
     * @param request
     *            削除ボタン押下条件モデル
     */
    @Execute
    @Validation
    public void accountDelete(EntryAccountDeleteRequestModel request) {
        List<CrmCcAccountClassAth> entityList = entryMapper.convert(request);
        crmAccountClassAthLogic.delete(entityList);
    }
}
