/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.impl;

import java.util.Locale;

import javax.annotation.Resource;

import jp.co.intra_mart.foundation.exception.BizApiException;
import jp.co.intra_mart.foundation.master.company.CompanyManager;
import jp.co.intra_mart.foundation.master.company.model.DepartmentSetBizKey;
import jp.co.intra_mart.foundation.master.company.model.DepartmentTreeNode;
import jp.co.intra_mart.foundation.master.company.model.IDepartmentSetBizKey;

import com.biz_integral.crm.cc.domain.dao.CrmCcDepartmentCmnDao;
import com.biz_integral.crm.cc.domain.dto.DepartmentDialogCriteriaDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcDepartmentCmn;
import com.biz_integral.crm.cc.domain.logic.DepartmentDialogLogic;
import com.biz_integral.foundation.core.util.LocaleUtil;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * 組織ダイアログロジックの実装です。
 */
public final class DepartmentDialogLogicImpl implements DepartmentDialogLogic {

    /**
     * 組織選択ダイアログ一覧取得Dao。
     */
    @Resource
    protected CrmCcDepartmentCmnDao crmCcDepartmentCmnDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public PagingResult<CrmCcDepartmentCmn> getEntityListTree(
            DepartmentDialogCriteriaDto dto) {

        // 組織選択ダイアログを一覧検索する。
        PagingResult<CrmCcDepartmentCmn> result =
            crmCcDepartmentCmnDao.findByDepartmentSelectDialogCriteria(dto);
        IDepartmentSetBizKey bizKey = new DepartmentSetBizKey();
        bizKey.setCompanyCd(dto.companyCd);
        bizKey.setDepartmentSetCd(dto.departmentSetCd);
        CompanyManager companyManager;
        DepartmentTreeNode departmentTreeNode = null;
        Locale locale = LocaleUtil.toLocale(dto.localeId);
        try {
            companyManager = new CompanyManager();
            departmentTreeNode =
                companyManager.getAbsoluteTree(
                    bizKey,
                    dto.searchBaseDate,
                    locale);

        } catch (BizApiException e) {

            e.printStackTrace();
        }

        for (CrmCcDepartmentCmn crmCcDepartmentCmn : result) {
            if (departmentTreeNode != null) {

                String disPlayName = departmentTreeNode.getDisplayName();
                String depCode = departmentTreeNode.getDepartmentCd();

                if (crmCcDepartmentCmn.getDepartmentCd().equals(depCode)) {

                    crmCcDepartmentCmn.setDepartmentName(disPlayName);

                } else {

                    getName(crmCcDepartmentCmn, departmentTreeNode);
                }
            }
        }

        return result;
    }

    /**
	 * 組織選択ダイアログ組織名の取得の処理です。
	 * 
	 * @param crmCcDepartmentCmn
	 *            検索結果
	 * @param departmentTreeNode
	 *            組織ノード
	 */
    private void getName(CrmCcDepartmentCmn crmCcDepartmentCmn,
            DepartmentTreeNode departmentTreeNode) {

        if (departmentTreeNode.hasChildren()) {
            for (DepartmentTreeNode child : departmentTreeNode.getChildren()) {
                if (crmCcDepartmentCmn.getDepartmentCd().equals(
                    child.getDepartmentCd())) {

                    crmCcDepartmentCmn
                        .setDepartmentName(child.getDescription());
                    break;
                }
                getName(crmCcDepartmentCmn, child);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PagingResult<CrmCcDepartmentCmn> getEntityList(
            DepartmentDialogCriteriaDto dto) {

        // 組織設定ダイアログを一覧検索する。
        PagingResult<CrmCcDepartmentCmn> result =
            crmCcDepartmentCmnDao.findByDepartmentSetDialogCriteria(dto);

        return result;
    }
}
