/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic;

import java.util.List;

import com.biz_integral.crm.cc.domain.dto.ConnectCompanySetDto;
import com.biz_integral.extension.mvc.maskat.beans.KeyValueBean;

/**
 * 接続会社設定のロジックです。
 */
public interface ConnectCompanySetLogic {

    /**
     * 処理会社一覧を検索します。
     * 
     * @return List<KeyValueBean>
     */
    public abstract List<KeyValueBean> getHandleCompanyList();

    /**
     * 所属組織一覧を取得します。
     * 
     * @param connectCompanySetDto
     * 
     * @return List<KeyValueBean>
     */
    public abstract List<KeyValueBean> getAffiliationDepartmentList(
            ConnectCompanySetDto connectCompanySetDto);

    /**
     * 主所属組織を取得します。
     * 
     * @param connectCompanySetDto
     * 
     * @return String
     */
    public abstract String getMainDepartmentCd(
            ConnectCompanySetDto connectCompanySetDto);

    /**
     * 接続会社設定情報を登録する。
     * 
     * @param connectCompanySetDto
     */
    public abstract void sessionInfoUpdate(
            ConnectCompanySetDto connectCompanySetDto);
}
