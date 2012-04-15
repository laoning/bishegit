/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.dao;

import com.biz_integral.crm.cc.domain.dto.DepartmentDialogCriteriaDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcDepartmentCmn;
import com.biz_integral.crm.sa.domain.dto.SalesActInformHasCheckDto;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * {@link CrmCcDepartmentCmn}のDaoクラスです。
 * 
 */
public class CrmCcDepartmentCmnDao extends
        AbstractCrmCcDepartmentCmnDao<CrmCcDepartmentCmn> {

    /**
     * 共有組織コード有効期間チェック用SQL
     */
    private static final String DEPTCD_ENABLE_CHECK =
        "CrmCcDepartmentCmnDao_deptCdEnableCheck.sql";

    /**
     * 組織選択ダイアログ対象一覧取得用SQL
     */
    private static final String CRMCCDEPARTMENTCMNDAO_FINDBYDEPARTMENTSELECTDIALOGCRITERIA_SQL =
        "CrmCcDepartmentCmnDao_findByDepartmentSelectDialogCriteria.sql";

    /**
     * 組織設定ダイアログ対象一覧取得用SQL
     */
    private static final String CRMCCDEPARTMENTCMNDAO_FINDBYDEPARTMENTSETDIALOGCRITERIA_SQL =
        "CrmCcDepartmentCmnDao_findByDepartmentSetDialogCriteria.sql";

    /**
     * 共有組織コード有効期間チェックを行います。<br>
     * 
     * @param checkDto
     *            営業活動報告（チェック項目あり）モデル
     * @return true（OKの場合）／false（NGの場合）
     */
    public boolean deptCdEnableCheck(SalesActInformHasCheckDto checkDto) {
        boolean result = true;
        long count = super.getCountBySqlFile(DEPTCD_ENABLE_CHECK, checkDto);
        if (0 == count) {
            result = false;
        }

        return result;
    }

    /**
     * 組織選択ダイアログ対象一覧の取得の処理です。
     * 
     * @param dto
     *            検索条件
     * 
     * @return 組織選択ダイアログ一覧
     */
    public PagingResult<CrmCcDepartmentCmn> findByDepartmentSelectDialogCriteria(
            DepartmentDialogCriteriaDto dto) {

        DepartmentDialogCriteriaDto criteria = convertLikeEspace(dto);
        criteria.addDefaultOrderBySpec("departmentCd");
        PagingResult<CrmCcDepartmentCmn> result =
            super.findPagingBySqlFile(
                CrmCcDepartmentCmn.class,
                CRMCCDEPARTMENTCMNDAO_FINDBYDEPARTMENTSELECTDIALOGCRITERIA_SQL,
                criteria);

        for (CrmCcDepartmentCmn cmn : result.getResultList()) {
            if (cmn.getEndDate() != null) {
                cmn.setEndDate(DateUtil.getCalculator(cmn.getEndDate()).addDay(
                    -1).asDate());
            }
        }
        return result;
    }

    /**
     * 組織設定ダイアログ対象一覧の取得の処理です。
     * 
     * @param dto
     *            検索条件
     * 
     * @return 組織選択ダイアログ一覧
     */
    public PagingResult<CrmCcDepartmentCmn> findByDepartmentSetDialogCriteria(
            DepartmentDialogCriteriaDto dto) {

        DepartmentDialogCriteriaDto criteria = convertLikeEspace(dto);
        criteria.addDefaultOrderBySpec("departmentCd");
        PagingResult<CrmCcDepartmentCmn> result =
            super.findPagingBySqlFile(
                CrmCcDepartmentCmn.class,
                CRMCCDEPARTMENTCMNDAO_FINDBYDEPARTMENTSETDIALOGCRITERIA_SQL,
                criteria);
        for (CrmCcDepartmentCmn cmn : result.getResultList()) {
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
    private DepartmentDialogCriteriaDto convertLikeEspace(
            DepartmentDialogCriteriaDto dto) {

        DepartmentDialogCriteriaDto cloned =
            dto.cloneInstance(DepartmentDialogCriteriaDto.class);

        cloned.departmentCd = likePrefix(dto.departmentCd);
        cloned.departmentName = likeContain(dto.departmentName);
        cloned.departmentShortName = likeContain(dto.departmentShortName);
        cloned.departmentSearchName = likeContain(dto.departmentSearchName);
        return cloned;
    }

}