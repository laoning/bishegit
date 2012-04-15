/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.domain.context.CrmContext;
import com.biz_integral.crm.cc.domain.dao.ConnectCompanySetDao;
import com.biz_integral.crm.cc.domain.dto.ConnectCompanySetDto;
import com.biz_integral.crm.cc.domain.logic.ConnectCompanySetLogic;
import com.biz_integral.extension.mvc.maskat.beans.KeyValueBean;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.service.domain.master.department.BizBasicDepartmentManager;
import com.biz_integral.service.domain.master.department.BizDepartment;
import com.biz_integral.service.persistence.LogicalDelete;

/**
 * ConnectCompanySetLogicロジックの実装です。
 */
public class ConnectCompanySetLogicImpl implements ConnectCompanySetLogic {

    /**
     *CRMアプリケーションコンテキスト
     */
    @Resource
    protected CrmContext crmContext;

    /**
     * 接続会社設定に関するDAO
     */
    @Resource
    protected ConnectCompanySetDao connectCompanySetDao;

    /**
     * bizクラス
     */
    @Resource
    protected BizBasicDepartmentManager bizBasicDepartmentManager;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<KeyValueBean> getHandleCompanyList() {
        // ユーザコードを取得
        String userCd = crmContext.getUserCd();

        // ロケールIDを取得
        String localeId = crmContext.getLocaleId();

        // 処理会社一覧を取得
        List<BizDepartment> departmentList =
            bizBasicDepartmentManager.findCompanies(userCd, localeId, DateUtil
                .nowDate(), LogicalDelete.AVAILABLE);

        ArrayList<KeyValueBean> compList = new ArrayList<KeyValueBean>();
        for (BizDepartment bizDepartment : departmentList) {
            KeyValueBean keyValueBean =
                new KeyValueBean(
                    bizDepartment.getDepartmentName(),
                    bizDepartment.getDepartmentCd());
            compList.add(keyValueBean);
        }

        return compList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<KeyValueBean> getAffiliationDepartmentList(
            ConnectCompanySetDto criteriadto) {

        ArrayList<KeyValueBean> compList = new ArrayList<KeyValueBean>();

        // システム日付
        criteriadto.sysDate =
            DateUtil.parse(
                DateUtil.nowDateString(DateUtil.DATE_FORMAT),
                DateUtil.DATE_FORMAT);

        // CRM用組織セットコードに該当する組織一覧を取得
        List<ConnectCompanySetDto> resultList =
            connectCompanySetDao.getAffiliationDepartmentList(criteriadto);

        if (resultList != null && resultList.size() > 0) {
            // 変換
            for (ConnectCompanySetDto ConnectCompanySetDto : resultList) {
                KeyValueBean keyValueBean =
                    new KeyValueBean(
                        ConnectCompanySetDto.getDepartmentName(),
                        ConnectCompanySetDto.getDepartmentCd());
                compList.add(keyValueBean);
            }
        }

        // 組織一覧が取得できなかった場合
        if (compList == null || compList.size() <= 0) {
            compList.add(new KeyValueBean());
        }

        return compList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMainDepartmentCd(ConnectCompanySetDto criteriadto) {

        String mainDepartmentCd = null;

        // システム日付
        criteriadto.sysDate =
            DateUtil.parse(
                DateUtil.nowDateString(DateUtil.DATE_FORMAT),
                DateUtil.DATE_FORMAT);

        // 主所属組織を取得
        ConnectCompanySetDto result =
            connectCompanySetDao.getMainDepartment(criteriadto);

        if (result != null) {
            mainDepartmentCd = result.getDepartmentCd();
        }

        return mainDepartmentCd;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sessionInfoUpdate(ConnectCompanySetDto connectCompanySetDto) {

        // セッション情報変更
        crmContext.switchSession(
            connectCompanySetDto.getCompanyCd(),
            connectCompanySetDto.getDepartmentCd());
    }
}
