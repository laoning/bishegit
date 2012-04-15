/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic;

import java.util.List;

import com.biz_integral.crm.cc.domain.dto.ContactSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.ContactSearchResultDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactDto;
import com.biz_integral.crm.cc.domain.dto.CrmContactCheckChargeDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcContact;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * CRMコンタクトロジックです 。
 */
public interface CrmContactLogic {

    /**
     * CRMコンタクト一覧を検索します。
     * 
     * @param criteriaDto
     *            検索条件
     * @return {@link ContactSearchResultDto}
     */
    public abstract PagingResult<ContactSearchResultDto> getEntityList(
            ContactSearchCriteriaDto criteriaDto);

    /**
     * CRMコンタクトの担当判定です。
     * 
     * @param crmContactCheckChargeDto
     *            CRMコンタクト担当チェックモデル
     */
    public abstract void isCharge(
            CrmContactCheckChargeDto crmContactCheckChargeDto);

    /**
     * CRMコンタクトの担当チェックです。
     * 
     * @param crmContactCheckChargeDto
     *            CRMコンタクト担当チェックモデル
     * @return List<CRMコンタクトコード>
     */
    public abstract List<String> checkCharge(
            CrmContactCheckChargeDto crmContactCheckChargeDto);

    /**
     * コンタクトの一件取得です。
     * 
     * @param crmCcContactDto
     *            CRMコンタクトDTO
     * @return CRMコンタクト
     */
    public abstract CrmCcContact getEntity(CrmCcContactDto crmCcContactDto);

    /**
     * CRMコンタクトを登録します。
     * 
     * @param crmCcContactDto
     *            CRMコンタクトDTO
     * @return CrmCcContactエンティティクラス
     */
    public abstract CrmCcContact create(CrmCcContactDto crmCcContactDto);

    /**
     * CRMコンタクトを更新します。
     * 
     * @param crmCcContactDto
     *            CRMコンタクトDTO
     */
    public abstract void update(CrmCcContactDto crmCcContactDto);

    /**
     * CRMコンタクトを削除します。
     * 
     * @param crmCcContactDto
     *            CRMコンタクトDTO
     */
    public abstract void delete(CrmCcContactDto crmCcContactDto);
}
