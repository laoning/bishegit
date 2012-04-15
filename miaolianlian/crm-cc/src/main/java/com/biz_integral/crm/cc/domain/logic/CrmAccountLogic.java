/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic;

import java.util.List;

import com.biz_integral.crm.cc.domain.dto.AccountSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.AccountSearchResultDto;
import com.biz_integral.crm.cc.domain.dto.CrmAccountCheckChargeDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccount;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * CRMアカウントロジックです 。
 */
public interface CrmAccountLogic {

    /**
     * CRMアカウント一覧を検索します。
     * 
     * @param criteriaDto
     *            検索条件
     * @return {@link AccountSearchResultDto}
     */
    public abstract PagingResult<AccountSearchResultDto> getEntityList(
            AccountSearchCriteriaDto criteriaDto);

    /**
     * アカウントの一件取得です。
     * 
     * @param crmCcAccountDto
     *            CRMアカウントDTO
     * @return CRMアカウント
     */
    public abstract CrmCcAccount getEntity(CrmCcAccountDto crmCcAccountDto);

    /**
	 * CRMアカウントを登録します。
	 * 
	 * @param crmCcAccountDto
	 *            CRMアカウントDTO
	 * @return CrmCcAccountエンティティクラス
	 */
    public abstract CrmCcAccount create(CrmCcAccountDto crmCcAccountDto);

    /**
     * CRMアカウントを更新します。
     * 
     * @param crmCcAccountDto
     *            CRMアカウントDTO
     */
    public abstract void update(CrmCcAccountDto crmCcAccountDto);

    /**
     * CRMアカウントを削除します。
     * 
     * @param crmCcAccountDto
     *            CRMアカウントDTO
     */
    public abstract void delete(CrmCcAccountDto crmCcAccountDto);

    /**
     * CRMアカウントの担当判定です。
     * 
     * @param crmAccountCheckChargeDto
     *            CRMアカウント担当チェックモデル
     */
    public abstract void isCharge(
            CrmAccountCheckChargeDto crmAccountCheckChargeDto);

    /**
     * CRMアカウントの担当チェックです。
     * 
     * @param crmAccountCheckChargeDto
     *            CRMアカウント担当チェックモデル
     * @return List<CRMアカウントコード>
     */
    public abstract List<String> checkCharge(
            CrmAccountCheckChargeDto crmAccountCheckChargeDto);

    /**
     * CRMアカウントを取引先登録します。
     * 
     * @param crmCcAccountDto
     *            CRMアカウントDTO
     */
    public abstract void createCustomer(CrmCcAccountDto crmCcAccountDto);
}
