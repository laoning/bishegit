/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.dao;

import java.util.ArrayList;
import java.util.List;

import org.seasar.extension.jdbc.OrderByItem;

import com.biz_integral.crm.cc.domain.dto.AddressSelectDialogCriteriaDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcSkcsCmn;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * {@link CrmCcSkcsCmn}のDaoクラスです。
 * 
 */
public class CrmCcSkcsCmnDao extends AbstractCrmCcSkcsCmnDao<CrmCcSkcsCmn> {

    /**
     * 住所選択ダイアログ対象一覧取得用SQL。
     */
    private static final String CRMCCSKCSCMNDAO_FINDBYADDSELDIALOGCRITERIA_SQL =
        "CrmCcSkcsCmnDao_findByAddSelDialogCriteria.sql";

    /**
     * 住所選択ダイアログ対象一覧の取得の処理です。
     * 
     * @param dto
     *            検索条件
     * 
     * @return 住所選択ダイアログ一覧
     */
    public PagingResult<CrmCcSkcsCmn> findByAddSelDialogCriteria(
            AddressSelectDialogCriteriaDto dto) {

        AddressSelectDialogCriteriaDto criteria = convertLikeEspace(dto);

        List<OrderByItem> orderByItemList = new ArrayList<OrderByItem>();
        OrderByItem item1 = new OrderByItem("tdfkCd");
        OrderByItem item2 = new OrderByItem("skcsCd");
        orderByItemList.add(item1);
        orderByItemList.add(item2);
        criteria.setDefaultOrderBySpecs(orderByItemList);

        PagingResult<CrmCcSkcsCmn> result =
            super.findPagingBySqlFile(
                CrmCcSkcsCmn.class,
                CRMCCSKCSCMNDAO_FINDBYADDSELDIALOGCRITERIA_SQL,
                criteria);
        for (CrmCcSkcsCmn cmn : result.getResultList()) {
            if (cmn.getEndDate() != null) {
                cmn.setEndDate(DateUtil.getCalculator(cmn.getEndDate()).addDay(
                    -1).asDate());
            }
        }
        return result;
    }

    /**
     * 前方一致検索条件をSQLエスケープします。
     * 
     * @param dto
     *            検索条件
     * @return 前方一致検索条件をSQLエスケープした検索条件
     */
    private AddressSelectDialogCriteriaDto convertLikeEspace(
            AddressSelectDialogCriteriaDto dto) {

        AddressSelectDialogCriteriaDto cloned =
            dto.cloneInstance(AddressSelectDialogCriteriaDto.class);

        cloned.tdfkCd = likePrefix(dto.tdfkCd);
        cloned.skcsCd = likePrefix(dto.skcsCd);
        cloned.tdfkAddress = likeContain(dto.tdfkAddress);
        cloned.skcsAddress = likeContain(dto.skcsAddress);
        return cloned;
    }

}