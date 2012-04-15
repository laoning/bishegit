/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.seasar.extension.jdbc.OrderByItem;
import org.seasar.extension.jdbc.OrderByItem.OrderingSpec;

import com.biz_integral.crm.cc.domain.dto.AccountContactCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountContactCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountContactDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountContactResultDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountContact;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountContactNames;
import com.biz_integral.crm.sa.domain.dto.AccountContactDto;
import com.biz_integral.crm.sa.domain.dto.CustomerSearchInfoCriteriaDto;
import com.biz_integral.crm.sa.domain.dto.CustomerSearchResultCriteriaDto;
import com.biz_integral.crm.sa.domain.dto.SalesActCheckDto;
import com.biz_integral.foundation.core.mapper.BeanMapper;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * {@link CrmCcAccountContact}のDaoクラスです。
 * 
 */
public class CrmCcAccountContactDao extends
        AbstractCrmCcAccountContactDao<CrmCcAccountContact> {

    /**
     * {@link BeanMapper}の実装
     */
    @Resource
    protected BeanMapper beanMapper;

    /**
     * アカウントコンタクト有効チェック（営業活動日）用SQL
     */
    private static final String ACCOUNT_CONTACT_ENABLE_CHECK =
        "CrmCcAccountContactDao_accountContactEnabledCheck.sql";

    /**
     * アカウントコンタクトDaoのコンタクト一覧取得用SQL
     */
    private static final String CONTACT_ENTITYLIST_SQL =
        "CrmCcAccountContactDao_getContactEntityList.sql";

    /**
     * アカウントコンタクトDaoのアカウント一覧取得用SQL
     */
    private static final String ACCOUNT_ENTITYLIST_SQL =
        "CrmCcAccountContactDao_getAccountEntityList.sql";

    /**
     * アカウントコンタクト有効チェック（営業活動日）を行います。
     * 
     * @param checkDto
     *            営業活動（チェック項目あり）モデル
     * @return true（OKの場合）／false（NGの場合）
     */
    public boolean accountContactEnabledCheck(SalesActCheckDto checkDto) {

        boolean result = true;
        long count =
            super.getCountBySqlFile(ACCOUNT_CONTACT_ENABLE_CHECK, checkDto);
        if (0 == count) {
            result = false;
        }

        return result;
    }

    /**
     * アカウントコンタクトDaoのコンタクトの一覧取得です。
     * 
     * @param criteriadto
     *            CRMアカウントコンタクトDto
     * @return コンタクト結果Dto
     */
    public PagingResult<CrmCcAccountContactResultDto> getContactEntityList(
            CrmCcAccountContactCriteriaDto criteriadto) {
        List<OrderByItem> orderByItemList = new ArrayList<OrderByItem>();
        OrderByItem item1 = new OrderByItem("crmContactCd", OrderingSpec.ASC);
        OrderByItem item2 = new OrderByItem("startDate", OrderingSpec.DESC);
        orderByItemList.add(item1);
        orderByItemList.add(item2);
        criteriadto.setDefaultOrderBySpecs(orderByItemList);

        PagingResult<CrmCcAccountContactResultDto> result =
            super.findPagingBySqlFile(
                CrmCcAccountContactResultDto.class,
                CONTACT_ENTITYLIST_SQL,
                criteriadto);
        return result;
    }

    /**
     * アカウントコンタクトDaoのアカウント一覧取得です。
     * 
     * @param criteriadto
     *            CRMアカウントコンタクトDto
     * @return アカウント結果Dto
     */
    public PagingResult<CrmCcAccountContactResultDto> getAccountEntityList(
            CrmCcAccountContactCriteriaDto criteriadto) {

        List<OrderByItem> orderByItemList = new ArrayList<OrderByItem>();
        OrderByItem item1 = new OrderByItem("crmAccountCd", OrderingSpec.ASC);
        OrderByItem item2 = new OrderByItem("startDate", OrderingSpec.DESC);
        orderByItemList.add(item1);
        orderByItemList.add(item2);
        criteriadto.setDefaultOrderBySpecs(orderByItemList);

        PagingResult<CrmCcAccountContactResultDto> result =
            super.findPagingBySqlFile(
                CrmCcAccountContactResultDto.class,
                ACCOUNT_ENTITYLIST_SQL,
                criteriadto);
        return result;
    }

    /**
     * アカウントコンタクトの登録です。
     * 
     * @param accountContactCriteriaDto
     *            アカウントコンタクトDto
     * @return コンタクト結果リスト
     */
    public List<CrmCcAccountContact> getCreateEntityList(
            AccountContactCriteriaDto accountContactCriteriaDto) {
        StringBuilder str = new StringBuilder();
        List<Object> values = new ArrayList<Object>();
        boolean found = false;
        if (accountContactCriteriaDto.companyCd != null) {
            str.append("company_cd = ?");
            values.add(accountContactCriteriaDto.companyCd);
            found = true;
        }
        if (accountContactCriteriaDto.crmAccountCd != null) {
            if (found) {
                str.append(" and ");
            }
            str.append("crm_account_cd = ?");
            values.add(accountContactCriteriaDto.crmAccountCd);
            found = true;
        }
        if (accountContactCriteriaDto.crmContactCd != null) {
            if (found) {
                str.append(" and ");
            }
            str.append("crm_contact_cd = ?");
            values.add(accountContactCriteriaDto.crmContactCd);
            found = true;
        }
        if (found) {
            str.append(" and ");
        }
        str.append("start_date <= ?");
        values.add(DateUtil.nowDate());
        str.append(" and ");
        str.append("end_date > ?");
        values.add(DateUtil.nowDate());
        List<CrmCcAccountContact> resultList =
            jdbcManager.from(CrmCcAccountContact.class).where(
                str.toString(),
                values.toArray()).getResultList();
        return resultList;
    }

    /**
     * アカウントコンタクトの削除です。
     * 
     * @param criteriaDto
     *            削除セル(コンタクト)押下条件Dto
     */
    public void delete(AccountContactCriteriaDto criteriaDto) {
        CrmCcAccountContact entity =
            beanMapper.map(criteriaDto, CrmCcAccountContact.class);
        entity.setDeleteFlag(true);
        super.updateIncludes(
            entity,
            CrmCcAccountContactNames.deleteFlag(),
            CrmCcAccountContactNames.versionNo(),
            CrmCcAccountContactNames.recordUserCd(),
            CrmCcAccountContactNames.recordDate());
    }

    /**
     * 担当顧客アカウントコンタクト一覧を検索します。
     * 
     * @param customeSearchRequestDto
     *            顧客表示対象検索条件モデル
     * 
     * @return 顧客表示モデルリスト
     */
    public List<AccountContactDto> findByCustomerSearchRequestDto(
            CustomerSearchInfoCriteriaDto customeSearchRequestDto) {
        return super.findBySqlFile(
            AccountContactDto.class,
            "CrmCcAccountContactDao_findByCustomerSearchRequestDto.sql",
            customeSearchRequestDto);
    }

    /**
     * 
     * 顧客アカウントコンタクト一覧を検索します。
     * 
     * @param customeSearchRequestDto
     *            顧客表示対象検索条件モデル
     * 
     * @return 顧客表示モデルリスト
     */
    public List<CustomerSearchResultCriteriaDto> findByCustomerSearchRequest(
            CustomerSearchInfoCriteriaDto customeSearchRequestDto) {
        return super.findBySqlFile(
            CustomerSearchResultCriteriaDto.class,
            "CrmCcAccountContactDao_findByCustomerSearchRequest.sql",
            customeSearchRequestDto);
    }

    /**
     * 
     * CRMアカウントコンタクト一件を検索します。
     * 
     * @param dto
     *            CRMアカウントコンタクトモデル
     * @return {@link CrmCcAccountContact}
     */
    public CrmCcAccountContact getEntity(CrmCcAccountContactDto dto) {

        StringBuilder str = new StringBuilder();
        List<Object> values = new ArrayList<Object>();
        getSqlWhere(dto, str, values);

        return jdbcManager.from(CrmCcAccountContact.class).where(
            str.toString(),
            values.toArray()).getSingleResult();
    }

    /**
     *CRMアカウントコンタクトの一件取得SQLの設定です。
     * 
     * @param dto
     *            CRMアカウントコンタクトDTO
     * @param str
     *            SQL
     * @param values
     *            SQLパラメータ
     * @return SQL
     */
    private StringBuilder getSqlWhere(CrmCcAccountContactDto dto,
            StringBuilder str, List<Object> values) {
        boolean found = false;
        if (dto.companyCd != null) {
            str.append("company_cd = ?");
            values.add(dto.companyCd);
            found = true;
        }
        if (dto.crmAccountCd != null) {
            if (found) {
                str.append(" and ");
            }
            str.append("crm_account_cd = ?");
            values.add(dto.crmAccountCd);
            found = true;
        }
        if (dto.crmContactCd != null) {
            if (found) {
                str.append(" and ");
            }
            str.append("crm_contact_cd = ?");
            values.add(dto.crmContactCd);
            found = true;
        }
        if (dto.termCd != null) {
            if (found) {
                str.append(" and ");
            }
            str.append("term_cd = ?");
            values.add(dto.termCd);
            found = true;
        }
        if (dto.startDate != null) {
            if (found) {
                str.append(" and ");
            }
            str.append("start_date = ?");
            values.add(dto.startDate);
            found = true;
        }
        if (dto.endDate != null) {
            if (found) {
                str.append(" and ");
            }
            str.append("end_date = ?");
            values.add(dto.endDate);
            found = true;
        }
        if (dto.deleteFlag != null) {
            if (found) {
                str.append(" and ");
            }
            str.append("delete_flag = ?");
            values.add(dto.deleteFlag);
            found = true;
        }

        return str;
    }
}