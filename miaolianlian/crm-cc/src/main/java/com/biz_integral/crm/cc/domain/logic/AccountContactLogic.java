/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic;

import java.util.List;

import com.biz_integral.crm.cc.domain.dto.AccountContactCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountContactCheckItemDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountContactCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountContactDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountContactResultDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountContact;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * CRMアカウントコンタクトのロジックです。
 */
public interface AccountContactLogic {

    /**
     * CRMアカウントコンタクトのコンタクト一覧を取得します。
     * 
     * @param criteriadto
     *            CRMアカウントコンタクトDTO
     * @return CRMアカウントコンタクトリスト
     */
    public abstract PagingResult<CrmCcAccountContactResultDto> getContactEntityList(
            CrmCcAccountContactCriteriaDto criteriadto);

    /**
     * CRMアカウントコンタクトのアカウント一覧を取得します。
     * 
     * @param criteriadto
     *            CRMアカウントコンタクトDTO
     * @return CRMアカウントコンタクトリスト
     */
    public abstract PagingResult<CrmCcAccountContactResultDto> getAccountEntityList(
            CrmCcAccountContactCriteriaDto criteriadto);

    /**
     * アカウントコンタクトのコンタクト登録です。
     * 
     * @param accountContactCriteriaDtoList
     *            アカウントコンタクトモデルリスト
     * @param crmDomainCd
     *            CRM領域コード
     */
    public abstract void createContact(
            List<AccountContactCriteriaDto> accountContactCriteriaDtoList,
            String crmDomainCd);

    /**
     * アカウントコンタクトの登録です。
     * 
     * @param accountContactCriteriaDtoList
     *            アカウントコンタクトモデルリスト
     * @param crmDomainCd
     *            CRM領域コード
     */
    public abstract void create(
            List<AccountContactCriteriaDto> accountContactCriteriaDtoList,
            String crmDomainCd);

    /**
     * アカウントコンタクトの削除です。
     * 
     * @param criteriaDto
     *            削除セル(コンタクト)押下条件モデル
     * @param crmDomainCd
     *            CRM領域コード
     */
    public abstract void delete(AccountContactCriteriaDto criteriaDto,
            String crmDomainCd);

    /**
	 * アカウントコンタクトの一件を取得します。
	 * 
	 * @param dto
	 *            CRMアカウントコンタクトモデル
	 * @return CrmCcAccountContactエンティティクラス
	 */
    public abstract CrmCcAccountContact getEntity(CrmCcAccountContactDto dto);

    /**
     * アカウントコンタクトの期間化更新判定です。
     * 
     * @param dto
     *            アカウントコンタクト（チェック項目あり）モデル
     */
    public abstract void termUpdateCheck(CrmCcAccountContactCheckItemDto dto);

    /**
     * 
     * アカウントコンタクトの期間化更新です。
     * 
     * @param dto
     *            アカウントコンタクトモデル
     */
    public abstract void termUpdate(CrmCcAccountContact dto);

    /**
     * 
     * アカウントコンタクトの期間化登録です。
     * 
     * @param dto
     *            アカウントコンタクトモデル
     */
    public abstract void termCreate(CrmCcAccountContact dto);

    /**
     * アカウントコンタクトのアカウント登録です。
     * 
     * @param accountContactCriteriaDtoList
     *            アカウントコンタクトモデルリスト
     * @param crmDomainCd
     *            CRM領域コード
     */
    public abstract void createAccount(
            List<AccountContactCriteriaDto> accountContactCriteriaDtoList,
            String crmDomainCd);
}
