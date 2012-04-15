/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dao;

import static org.junit.Assert.assertEquals;

import java.sql.Date;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.biz_integral.crm.cc.domain.entity.CrmCcItemCatCmn;
import com.biz_integral.crm.sa.domain.dto.ItemCatCmmFilterCriteriaDto;
import com.biz_integral.service.persistence.pager.PagingResult;
import com.biz_integral.unit.BizIntegralTest;

/**
 * CrmCcItemCatCmnDaoクラスのテストケースです
 */
@RunWith(BizIntegralTest.class)
public class CrmCcItemCatCmnDaoTest {

    /**
     * テスト対象
     */
    private CrmCcItemCatCmnDao crmCcItemCatCmnDao;

    /**
     * 品目カテゴリ_共通一覧検索条件モデル
     */
    private ItemCatCmmFilterCriteriaDto itemCatCmmFilterCriteriaDto =
        new ItemCatCmmFilterCriteriaDto();

    /**
     * 品目カテゴリ_共通一覧を取得するのテスト。<br>
     * 品目カテゴリ_共通一覧検索条件モデルDto。
     */
    @Test
    public void testFindByCriteria() {

        String[][] searchList =
            {
                { "item10", "CRM用品目1", "2001-1-1", "2012-1-1" },
                { "item2", "CRM用品目2", "2001-1-1", "2012-1-1" },
                { "item3", "CRM用品目1", "2001-1-1", "2012-1-1" },
                { "item8", "CRM用品目1", "2001-1-1", "2012-1-1" } };
        // 基準日 = 入力値．基準日
        itemCatCmmFilterCriteriaDto.baseDate = Date.valueOf("2010-08-01");

        // 品目カテゴリコード = 入力値．品目カテゴリコード 前方一致
        itemCatCmmFilterCriteriaDto.itemCategoryCd = "item";

        // 品目カテゴリ名 = 入力値．品目カテゴリ名 含む検索
        itemCatCmmFilterCriteriaDto.itemCategoryName = "品目";

        // 品目カテゴリ略称 = 入力値．品目カテゴリ略称 含む検索
        itemCatCmmFilterCriteriaDto.itemCategoryShortName = "品目略称";

        // 品目カテゴリ検索名 = 入力値．品目カテゴリ検索名 含む検索
        itemCatCmmFilterCriteriaDto.itemCategorySearchName = "検索";

        // 品目カテゴリセットコード
        itemCatCmmFilterCriteriaDto.itemCategorySetCd = "item001";

        // ロケールID = 入力値． ロケールID
        itemCatCmmFilterCriteriaDto.localeId = "ja";

        // 削除フラグ = 入力値． 削除フラグ
        itemCatCmmFilterCriteriaDto.deleteFlag = false;
        PagingResult<CrmCcItemCatCmn> actual =
            crmCcItemCatCmnDao.getItemCatCmnList(itemCatCmmFilterCriteriaDto);

        assertEquals(4, actual.size());

        int i = 0;
        for (CrmCcItemCatCmn entity : actual) {
            assertEquals(entity.getItemCategoryCd(), searchList[i][0]);
            assertEquals(entity.getItemCategoryName(), searchList[i][1]);
            assertEquals(entity.getStartDate(), Date.valueOf(searchList[i][2]));
            assertEquals(entity.getEndDate(), Date.valueOf(searchList[i][3]));
            i++;
        }
    }
}
