/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic;

import java.util.List;

/**
 * 組織所属_共通のロジックです。
 */
public interface CrmCcDepartmentAthCmnLogic {

    /**
	 * ユーザコード一覧検索です。
	 * 
	 * @param departmentCdList
	 *            組織所属コードリスト
	 * @return ユーザコード一覧
	 */
    public List<String> getUserCdList(List<String> departmentCdList);

    /**
     * 組織コード一覧検索です。
     * 
	 * @param userCd
	 *            ユーザコード
	 * @return 組織コード一覧
     */
    public List<String> getDepartmentCdList(String userCd);

}
