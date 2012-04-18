/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.dao;

import java.util.ArrayList;
import java.util.List;

import com.biz_integral.crm.cc.domain.entity.CrmCcItemCatCmn;
import com.biz_integral.crm.pm.domain.dto.ProposalSearchDto;
import com.biz_integral.crm.sa.domain.dto.ItemCatCmmFilterCriteriaDto;
import com.biz_integral.crm.sa.domain.dto.SalesActCheckDto;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * {@link CrmCcItemCatCmn}のDaoクラスです。
 * 
 */
public class CrmCcItemCatCmnDao extends
        AbstractCrmCcItemCatCmnDao<CrmCcItemCatCmn> {

    /**
     * 品目カテゴリ有効期間チェック用SQL
     */
    private static final String ITEM_CAT_ENABLE_TERMS_CHECK_SQL =
        "CrmCcItemCatCmnDao_itemCatEnableTermsCheck.sql";

    /**
     * 品目カテゴリ有効期間チェックを行います。
     * 
     * @param checkDto
     *            営業活動（チェック項目あり）モデル
     * @return true（OKの場合）／false（NGの場合）
     */
    public boolean itemCatEnableTermsCheck(SalesActCheckDto checkDto) {
        boolean result = true;
        long count =
            super.getCountBySqlFile(ITEM_CAT_ENABLE_TERMS_CHECK_SQL, checkDto);
        if (0 == count) {
            result = false;
        }

        return result;
    }

    /**
     * 品目カテゴリ_共通一覧の取得です。
     * 
     * @param searchCriteria
     *            {@link ItemCatCmmFilterCriteriaDto}
     * @return {@link PagingResult}<{@link CrmCcItemCatCmn}>
     */
    public PagingResult<CrmCcItemCatCmn> getItemCatCmnList(
            ItemCatCmmFilterCriteriaDto searchCriteria) {

        ItemCatCmmFilterCriteriaDto condition =
            convertContainEspace(searchCriteria);
        condition.addDefaultOrderBySpec("itemCategoryCd");
        PagingResult<CrmCcItemCatCmn> resultlist =
            super.findPagingBySqlFile(
                CrmCcItemCatCmn.class,
                "CrmCcItemCatCmnDao_itemCatCmmFilterList.sql",
                condition);

        for (CrmCcItemCatCmn cmn : resultlist.getResultList()) {
            if (cmn.getEndDate() != null) {
                cmn.setEndDate(DateUtil.getCalculator(cmn.getEndDate()).addDay(
                    -1).asDate());
            }
        }
        return resultlist;
    }

    /**
     * 部分一致検索条件をSQLエスケープします。
     * 
     * @param criteria
     *            品目カテゴリ_共通一覧を取得の検索条件
     * @return 部分一致検索条件をSQLエスケープした検索条件
     */
    private ItemCatCmmFilterCriteriaDto convertContainEspace(
            ItemCatCmmFilterCriteriaDto criteria) {
        ItemCatCmmFilterCriteriaDto cloned =
            criteria.cloneInstance(ItemCatCmmFilterCriteriaDto.class);

        // 品目カテゴリコード_前方一致
        cloned.itemCategoryCd = likePrefix(criteria.itemCategoryCd);

        // 品目カテゴリ名_含む検索
        cloned.itemCategoryName = likeContain(criteria.itemCategoryName);

        // 品目カテゴリ略称_含む検索
        cloned.itemCategoryShortName =
            likeContain(criteria.itemCategoryShortName);

        // 品目カテゴリ検索名_含む検索
        cloned.itemCategorySearchName =
            likeContain(criteria.itemCategorySearchName);
        return cloned;
    }

    /**
     * 一件取得を取得します。
     * 
     * @param crmCcItemCatCmn
     *            品目カテゴリ_共通Entityモデル
     * @return 品目カテゴリ_共通Entityモデル
     */
    public CrmCcItemCatCmn getEntity(CrmCcItemCatCmn crmCcItemCatCmn) {
        StringBuffer sql = new StringBuffer();
        sql.append("item_category_set_cd = ? AND ");
        sql.append("item_category_cd = ?  AND ");
        sql.append("locale_id = ?  AND ");
        sql.append("start_date <= ?  AND ");
        sql.append("end_date > ?  AND ");
        sql.append("delete_flag = '0'");
        List<Object> data = new ArrayList<Object>();
        data.add(crmCcItemCatCmn.getItemCategorySetCd());
        data.add(crmCcItemCatCmn.getItemCategoryCd());
        data.add(crmCcItemCatCmn.getLocaleId());
        data.add(DateUtil.today());
        data.add(DateUtil.today());

        return jdbcManager.from(CrmCcItemCatCmn.class).where(
            sql.toString(),
            data.toArray()).getSingleResult();
    }

    /**
     * 
     * データ妥当性チェックの品目カテゴリコードの有効チェックです。
     * 
     * @param dto
     *            案件検索条件
     * @return 有効の品目カテゴリ件数
     */
    public long validationItemCategory(ProposalSearchDto dto) {

        return super.getCountBySqlFile(
            "CrmCcItemCatCmnDao_validationItemCategory.sql",
            dto);
    }
}