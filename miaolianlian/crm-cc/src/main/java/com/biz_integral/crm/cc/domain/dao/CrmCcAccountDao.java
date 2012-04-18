/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.seasar.extension.jdbc.OrderByItem;
import org.seasar.extension.jdbc.Where;
import org.seasar.extension.jdbc.where.CompositeWhere;
import org.seasar.extension.jdbc.where.SimpleWhere;

import com.biz_integral.crm.cc.domain.dto.AccountSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.AccountSearchResultDto;
import com.biz_integral.crm.cc.domain.dto.CrmAccountCheckChargeDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountDto;
import com.biz_integral.crm.cc.domain.dto.SaAccountSearchResultDto;
import com.biz_integral.crm.cc.domain.dto.solr.CrawlerWorkDto;
import com.biz_integral.crm.cc.domain.dto.solr.CrawlerWorkPagerDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccount;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountNames;
import com.biz_integral.crm.cc.domain.entity.CrmCcContactNames;
import com.biz_integral.crm.pm.domain.dto.ProposalSearchDto;
import com.biz_integral.crm.sa.domain.dto.AccountContactDto;
import com.biz_integral.crm.sa.domain.dto.CustomerSearchInfoCriteriaDto;
import com.biz_integral.crm.sa.domain.dto.CustomerSearchResultCriteriaDto;
import com.biz_integral.crm.sa.domain.dto.SalesActCheckDto;
import com.biz_integral.crm.sp.domain.dto.CustomerSelectSalesPlanGetCustomerResultListDto;
import com.biz_integral.crm.sp.domain.dto.CustomerSelectSalesPlanSearchCriteraDto;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.foundation.core.util.StringUtil;
import com.biz_integral.service.persistence.exception.OptimisticLockRuntimeException;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * {@link CrmCcAccount}のDaoクラスです。
 * 
 */
public class CrmCcAccountDao extends AbstractCrmCcAccountDao<CrmCcAccount> {

    /**
     * アカウント一覧取得用SQL
     */
    private static final String FIND_BY_ACCOUNT_SEARCH_CRITERIA_SQL =
        "CrmCcAccountDao_findByAccountSearchCriteria.sql";

    /**
     * 一件取得用SQL
     */
    private static final String GET_ENTITY_SQL =
        "CrmCcAccountDao_getEntity.sql";

    /**
     * CRMアカウント有効チェック用SQL
     */
    private static final String ACCOUNT_ENABLE_CHECK =
        "CrmCcAccountDao_crmAccountEnableCheck.sql";

    /**
     * 顧客一覧検索用SQL
     */
    private static final String GET_CUSTOMER_LIST =
        "CrmCcAccountDao_findByCustomerSelectSalesPlanGetCustomerResultListDto.sql";

    /**
     * CRMアカウントの担当判定の場合、アカウント一覧取得用SQL
     */
    private static final String FIND_BY_CRM_CC_ACCOUNT_SQL =
        "CrmCcAccountDao_findByCrmAccountCheckChargeDto.sql";

    /**
     * CRMコンタクトを更新用SQL
     */
    private static final String UPDATE_ACT_ACCOUNT_SQL =
        "CrmCcAccountDao_updateActAccount.sql";

    /**
     * 一件取得を行います。
     * 
     * @param dto
     *            CRMアカウントモデル
     * @return 活動アカウント検索結果モデル
     */
    public SaAccountSearchResultDto getEntity(CrmCcAccountDto dto) {

        SaAccountSearchResultDto result = null;

        List<SaAccountSearchResultDto> resultList =
            super.findBySqlFile(
                SaAccountSearchResultDto.class,
                GET_ENTITY_SQL,
                dto);

        if (resultList != null && resultList.size() != 0) {
            result = resultList.get(0);
        }

        return result;
    }

    /**
     * CRMアカウント有効チェックを行います。
     * 
     * @param checkDto
     *            営業活動（チェック項目あり）モデル
     * @return true（OKの場合）／false（NGの場合）
     */
    public boolean crmAccountEnableCheck(SalesActCheckDto checkDto) {

        boolean result = true;
        long count = super.getCountBySqlFile(ACCOUNT_ENABLE_CHECK, checkDto);
        if (count == 0) {
            result = false;
        }

        return result;
    }

    /**
     * CRMアカウントの一件取得です。
     * 
     * @param crmCcAccountDto
     *            CRMアカウントDTO
     * @return CRMアカウント
     */
    public CrmCcAccount getAccountEntityWithoutDeleteFlag(
            CrmCcAccountDto crmCcAccountDto) {
        StringBuilder str = new StringBuilder();
        List<Object> values = new ArrayList<Object>();
        getSqlWhere(crmCcAccountDto, str, values);

        return jdbcManager.from(CrmCcAccount.class).where(
            str.toString(),
            values.toArray()).getSingleResult();
    }

    /**
     * CRMアカウントの一件取得です。
     * 
     * @param crmCcAccountDto
     *            CRMアカウントDTO
     * @return CRMアカウント
     */
    public CrmCcAccount getAccountEntity(CrmCcAccountDto crmCcAccountDto) {
        StringBuilder str = new StringBuilder();
        List<Object> values = new ArrayList<Object>();
        getSqlWhere(crmCcAccountDto, str, values);
        str.append(" and ");
        str.append("delete_flag = '0'");

        return jdbcManager.from(CrmCcAccount.class).where(
            str.toString(),
            values.toArray()).getSingleResult();
    }

    /**
     * 登録前CRMアカウントコードの重複をチェックします。
     * 
     * @param crmCcAccountDto
     *            CRMアカウントDTO
     * @return CRMアカウント
     */
    public List<CrmCcAccount> getEntityForCheck(CrmCcAccountDto crmCcAccountDto) {
        StringBuilder str = new StringBuilder();
        List<Object> values = new ArrayList<Object>();
        getSqlWhere(crmCcAccountDto, str, values);
        List<CrmCcAccount> resultList =
            jdbcManager.from(CrmCcAccount.class).where(
                str.toString(),
                values.toArray()).getResultList();
        return resultList;

    }

    /**
     * CRMアカウントの一件取得（担当判定あり）SQLの設定です。
     * 
     * @param crmCcAccountDto
     *            CRMアカウントDTO
     * @param str
     *            SQL
     * @param values
     *            SQLパラメータ
     * @return SQL
     */
    private StringBuilder getSqlWhere(CrmCcAccountDto crmCcAccountDto,
            StringBuilder str, List<Object> values) {
        boolean found = false;
        if (crmCcAccountDto.companyCd != null) {
            str.append("company_cd = ?");
            values.add(crmCcAccountDto.companyCd);
            found = true;
        }
        if (crmCcAccountDto.crmAccountCd != null) {
            if (found) {
                str.append(" and ");
            }
            str.append("crm_account_cd = ?");
            values.add(crmCcAccountDto.crmAccountCd);
            found = true;
        }
        if (crmCcAccountDto.localeId != null) {
            if (found) {
                str.append(" and ");
            }
            str.append("locale_id = ?");
            values.add(crmCcAccountDto.localeId);
            found = true;
        }
        if (crmCcAccountDto.startDate != null) {
            if (found) {
                str.append(" and ");
            }
            str.append("start_date <= ?");
            values.add(DateUtil.nowDate());
            found = true;
        }
        if (crmCcAccountDto.endDate != null) {
            if (found) {
                str.append(" and ");
            }
            str.append("end_date > ?");
            values.add(DateUtil.nowDate());
            found = true;
        }
        return str;
    }

    /**
     * アカウント一覧の取得です。
     * 
     * @param criteria
     *            アカウントの検索条件
     * 
     * @return アカウント一覧
     */
    public PagingResult<AccountSearchResultDto> findByAccountSearchCriteria(
            AccountSearchCriteriaDto criteria) {
        criteria.addDefaultOrderBySpec("crmAccountCd");
        AccountSearchCriteriaDto condition = convertContainEspace(criteria);
        PagingResult<AccountSearchResultDto> result =
            super.findPagingBySqlFile(
                AccountSearchResultDto.class,
                FIND_BY_ACCOUNT_SEARCH_CRITERIA_SQL,
                condition);
        return result;
    }

    /**
     * 部分一致検索条件をSQLエスケープします。
     * 
     * @param criteria
     *            アカウントの検索条件
     * @return 部分一致検索条件をSQLエスケープした検索条件
     */
    private AccountSearchCriteriaDto convertContainEspace(
            AccountSearchCriteriaDto criteria) {
        AccountSearchCriteriaDto cloned =
            criteria.cloneInstance(AccountSearchCriteriaDto.class);
        cloned.crmAccountCd = likePrefix(criteria.crmAccountCd);
        cloned.crmAccountName = likeContain(criteria.crmAccountName);
        cloned.crmAccountShortName = likeContain(criteria.crmAccountShortName);
        cloned.crmAccountKanaName = likeContain(criteria.crmAccountKanaName);
        cloned.crmAccountSearchName =
            likeContain(criteria.crmAccountSearchName);
        cloned.customerCd = likePrefix(criteria.customerCd);
        cloned.address = likeContain(criteria.address);
        cloned.telephoneNumber = likeContain(criteria.telephoneNumber);
        cloned.userCd = likePrefix(criteria.userCd);
        cloned.userName = likeContain(criteria.userName);
        return cloned;
    }

    /**
     * CRMアカウントの担当判定の場合、CRMアカウントDaoを一覧取得します。
     * 
     * @param crmAccountCheckChargeDto
     *            アカウントの検索条件
     * @return アカウント一覧
     */
    public List<CrmCcAccount> findByCrmAccountCheckChargeDto(
            CrmAccountCheckChargeDto crmAccountCheckChargeDto) {
        List<CrmCcAccount> result =
            super.findBySqlFile(
                CrmCcAccount.class,
                FIND_BY_CRM_CC_ACCOUNT_SQL,
                crmAccountCheckChargeDto);
        return result;

    }

    /**
     * CRMコンタクトを更新します。
     * 
     * @param dto
     *            CRMアカウントモデル
     */
    public void updateActAccount(CrmCcAccountDto dto) {

        int count = super.updateBySqlFile(UPDATE_ACT_ACCOUNT_SQL, dto);
        if (0 == count) {
            throw new OptimisticLockRuntimeException(null);
        }
    }

    /**
     * 担当アカウントコンタクト一覧を検索します。
     * 
     * @param customeSearchRequestDto
     *            顧客表示対象検索条件モデル
     * 
     * @return List<顧客表示モデル>
     */
    public List<AccountContactDto> findByCustomerSearchRequestDto(
            CustomerSearchInfoCriteriaDto customeSearchRequestDto) {
        return super.findBySqlFile(
            AccountContactDto.class,
            "CrmCcAccountDao_findByCustomerSearchRequestDto.sql",
            customeSearchRequestDto);
    }

    /**
     * 
     * 顧客アカウントコンタクト一覧を検索します。
     * 
     * @param customeSearchRequestDto
     *            顧客表示対象検索条件モデル
     * 
     * @return List<顧客表示モデル>
     */
    public List<CustomerSearchResultCriteriaDto> findByCustomerSearchRequest(
            CustomerSearchInfoCriteriaDto customeSearchRequestDto) {
        return super.findBySqlFile(
            CustomerSearchResultCriteriaDto.class,
            "CrmCcAccountDao_findByCustomerSearchRequest.sql",
            customeSearchRequestDto);
    }

    /**
     * 顧客一覧を検索します。
     * 
     * @param searchCriteraDto
     *            顧客選択（販売計画）一覧検索条件モデル
     * @return PagingResult＜顧客選択（販売計画）一覧検索結果モデル＞
     */
    public PagingResult<CustomerSelectSalesPlanGetCustomerResultListDto> findByCustomerSelectSalesPlanGetCustomerResultListDto(
            CustomerSelectSalesPlanSearchCriteraDto searchCriteraDto) {

        CustomerSelectSalesPlanSearchCriteraDto criteraDto =
            convertEscape(searchCriteraDto);

        List<OrderByItem> orderByItemList = new ArrayList<OrderByItem>();
        if (StringUtil.isNotEmpty(criteraDto.firstKey)) {
            OrderByItem firstKey = new OrderByItem(criteraDto.firstKey);
            orderByItemList.add(firstKey);
        }
        if (StringUtil.isNotEmpty(criteraDto.secondKey)) {
            OrderByItem secondKey = new OrderByItem(criteraDto.secondKey);
            orderByItemList.add(secondKey);
        }
        if (StringUtil.isNotEmpty(criteraDto.thirdKey)) {
            OrderByItem thirdKey = new OrderByItem(criteraDto.thirdKey);
            orderByItemList.add(thirdKey);
        }
        if (orderByItemList.isEmpty()) {
            OrderByItem other = new OrderByItem("crmAccountCd");
            orderByItemList.add(other);
        }

        criteraDto.setDefaultOrderBySpecs(orderByItemList);

        return super.findPagingBySqlFile(
            CustomerSelectSalesPlanGetCustomerResultListDto.class,
            GET_CUSTOMER_LIST,
            criteraDto);
    }

    /**
     * 部分一致検索条件をSQLエスケープします。
     * 
     * @param searchCriteraDto
     *            顧客選択（販売計画）一覧検索条件
     * @return 部分一致検索条件をSQLエスケープした検索条件
     */
    private CustomerSelectSalesPlanSearchCriteraDto convertEscape(
            CustomerSelectSalesPlanSearchCriteraDto searchCriteraDto) {
        CustomerSelectSalesPlanSearchCriteraDto cloned =
            searchCriteraDto
                .cloneInstance(CustomerSelectSalesPlanSearchCriteraDto.class);

        cloned.crmAccountCd = likePrefix(searchCriteraDto.crmAccountCd);
        cloned.itemCategoryCd = likePrefix(searchCriteraDto.itemCategoryCd);
        cloned.chargeUserCd = likePrefix(searchCriteraDto.chargeUserCd);

        cloned.crmAccountName = likeContain(searchCriteraDto.crmAccountName);
        cloned.crmAccountNameKana =
            likeContain(searchCriteraDto.crmAccountNameKana);
        cloned.crmContactShortName =
            likeContain(searchCriteraDto.crmContactShortName);
        cloned.crmAccountSearchName =
            likeContain(searchCriteraDto.crmAccountSearchName);
        cloned.chargeUserName = likeContain(searchCriteraDto.chargeUserName);
        cloned.itemCategoryName =
            likeContain(searchCriteraDto.itemCategoryName);

        return cloned;
    }

    /**
     * 
     * データ妥当性チェックのアカウント有効チェックです。
     * 
     * @param dto
     *            案件検索条件
     * @return 有効のアカウントの件数
     */
    public long validationAccount(ProposalSearchDto dto) {

        return super.getCountBySqlFile(
            "CrmCcAccountDao_validationAccount.sql",
            dto);
    }

    /**
     * 
     * CRMアカウントを一覧検索します。
     * 
     * @param crmCcAccountDto
     *            CRMアカウントモデル
     * @return {@link CrmCcAccount}
     */
    public List<CrmCcAccount> getAccountList(CrmCcAccountDto crmCcAccountDto) {

        StringBuffer sql = new StringBuffer();
        sql.append("company_cd = ? AND ");
        sql.append("crm_account_cd = ?  AND ");
        sql.append("locale_id = ?  AND ");
        sql.append("delete_flag = ?");
        List<Object> data = new ArrayList<Object>();
        data.add(crmCcAccountDto.companyCd);
        data.add(crmCcAccountDto.crmAccountCd);
        data.add(crmCcAccountDto.localeId);
        data.add("0");

        List<CrmCcAccount> resultList =
            jdbcManager.from(CrmCcAccount.class).where(
                sql.toString(),
                data.toArray()).getResultList();
        return resultList;

    }

    // ----- 以下solr用の赤坂追加分 -----
    /**
     * 期間内に更新記録を持つCRMアカウントを一覧検索します。<br>
     * offsetとlimitの指定により、範囲で返却します。
     * 
     * @param dto
     *            クローラワークページャーDTO
     * @return CRMアカウントリスト
     */
    public List<CrmCcAccount> findByUpdate(CrawlerWorkPagerDto dto) {

        OrderByItem cdOrder = new OrderByItem(CrmCcAccountNames.crmAccountCd());
        OrderByItem localeOrder = new OrderByItem(CrmCcContactNames.localeId());

        return super.jdbcManager
            .from(CrmCcAccount.class)
            .where(
                getWhere(
                    dto.companyCdList,
                    dto.startDate,
                    dto.endDate,
                    dto.simpleStartDate,
                    dto.simpleEndDate))
            .offset(dto.getOffset())
            .limit(dto.getLimit())
            .orderBy(cdOrder)
            .orderBy(localeOrder)
            .getResultList();
    }

    /**
     * 期間内に更新記録を持つCRMアカウントの件数を取得します。
     * 
     * @param dto
     *            クローラワークDTO
     * @return CRMアカウントの期間内更新件数
     */
    public long getCountByUpdate(CrawlerWorkDto dto) {

        return super.jdbcManager.from(CrmCcAccount.class).where(
            getWhere(
                dto.companyCdList,
                dto.startDate,
                dto.endDate,
                dto.simpleStartDate,
                dto.simpleEndDate)).getCount();
    }

    /**
     * 更新された対象を取得する条件を構築します。<br>
     * 対象の期間内で、更新日が変更されているデータ、または、<br>
     * 期間が切り替わったデータを取得する条件を構築します。
     * 
     * @param companyCdList
     *            会社コードリスト
     * @param startDate
     *            開始日
     * @param endDate
     *            終了日（現在日付）
     * @param simpleStartDate
     *            開始日（時分秒なしの開始日）
     * @param simpleEndDate
     *            終了日（終了日+1日で時分秒なし）
     * @return 更新対象取得条件
     */
    private Where getWhere(List<String> companyCdList, Date startDate,
            Date endDate, Date simpleStartDate, Date simpleEndDate) {
        // 必須条件
        SimpleWhere essentialCondition = new SimpleWhere();
        essentialCondition.excludesWhitespace();
        essentialCondition.in(CrmCcAccountNames.companyCd(), companyCdList);
        essentialCondition.le(CrmCcAccountNames.startDate(), endDate);
        essentialCondition.gt(CrmCcAccountNames.endDate(), endDate);
        essentialCondition.eq(CrmCcAccountNames.dummyFlag(), false);

        // 更新日の条件
        SimpleWhere recordCondition = new SimpleWhere();
        recordCondition.gt(CrmCcAccountNames.recordDate(), startDate);
        recordCondition.le(CrmCcAccountNames.recordDate(), endDate);

        // 期間の条件
        SimpleWhere termCondition = new SimpleWhere();
        termCondition.ge(CrmCcAccountNames.startDate(), simpleStartDate);
        termCondition.lt(CrmCcAccountNames.startDate(), simpleEndDate);

        // 条件の結合
        CompositeWhere updateWhere =
            new CompositeWhere("OR", recordCondition, termCondition);
        return new CompositeWhere("AND", essentialCondition, updateWhere);

    }
}
