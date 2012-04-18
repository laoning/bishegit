/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.acctGrp;

import com.biz_integral.crm.cc.domain.dto.AcctGrpDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountChargeDeptCriteriaDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAcctGrp;
import com.biz_integral.crm.cc.domain.entity.CrmCcAcctGrpChargeDept;
import com.biz_integral.crm.cc.domain.entity.CrmCcAcctGrpChargeUser;

/**
 * アカウントグループ登録/更新で利用するマッピーです。
 */
public interface EntryMapper {

    /**
     * アカウントグループ登録/更新エンティティを登録モデルに変換します。
     * 
     * @param crmCcAccount
     *            アカウントグループ登録/更新エンティティ
     * @param response
     *            初期表示レスポンスモデル
     */
    public CrmCcAcctGrp convertToCrmCcAcctGrp(EntryRequestModel request);

    /**
     * アカウントグループ所属検索モデルに変換します。
     * 
     * @param crmCcAccount
     *            アカウントグループ登録/更新エンティティ
     * @param response
     *            初期表示レスポンスモデル
     */
    public AcctGrpDto convertToSearchAccount(AcctGrpAthRequestModel request);

    /**
     * アカウントグループ登録/更新で登録の処理引数CRMアカウント担当組織DTOに変換します。
     * 
     * @param request
     *            アカウントグループ登録/更新で登録条件
     * @return {@link CrmCcAccountChargeDeptCriteriaDto}
     */
    public CrmCcAcctGrpChargeDept convertToCrmCcAcctGrpCharDeptEntity(
            CrmCcAcctGrpChargeDept request);

    /**
     * アカウントグループ登録/更新で登録の処理引数CRMアカウント担当組織DTOに変換します。
     * 
     * @param request
     *            アカウントグループ登録/更新で登録条件
     * @return {@link CrmCcAccountChargeDeptCriteriaDto}
     */
    public CrmCcAcctGrpChargeUser convertToCrmCcAcctGrpCharUserEntity(
            CrmCcAcctGrpChargeUser request);

}
