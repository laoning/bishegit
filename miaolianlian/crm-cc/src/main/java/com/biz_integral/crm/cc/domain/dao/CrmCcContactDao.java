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

import com.biz_integral.crm.cc.domain.dto.ContactSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.ContactSearchResultDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactDto;
import com.biz_integral.crm.cc.domain.dto.CrmContactCheckChargeDto;
import com.biz_integral.crm.cc.domain.dto.SaContactSearchResultDto;
import com.biz_integral.crm.cc.domain.dto.solr.CrawlerWorkDto;
import com.biz_integral.crm.cc.domain.dto.solr.CrawlerWorkPagerDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcContact;
import com.biz_integral.crm.cc.domain.entity.CrmCcContactNames;
import com.biz_integral.crm.sa.domain.dto.SalesActCheckDto;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.service.persistence.exception.OptimisticLockRuntimeException;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * {@link CrmCcContact}のDaoクラスです。
 * 
 */
public class CrmCcContactDao extends AbstractCrmCcContactDao<CrmCcContact> {

    /**
     * コンタクト一覧取得用SQL
     */
    private static final String FIND_BY_CONTACT_SEARCH_CRITERIA_SQL =
        "CrmCcContactDao_findByContactSearchCriteria.sql";

    /**
     * 一件取得用SQL
     */
    private static final String GET_ENTITY_SQL =
        "CrmCcContactDao_getEntity.sql";

    /**
     * CRMコンタクト有効チェック用SQL
     */
    private static final String CONTACT_ENABLE_CHECK =
        "CrmCcContactDao_crmCountEnabledCheck.sql";

    /**
     * 担当チェック用SQL
     */
    private static final String FIND_BY_CONTACTDTO_SQL =
        "CrmCcContactDao_findByCrmContactCheckChargeDto.sql";

    /**
     * CRMコンタクト更新用SQL
     */
    private static final String UPDATE_ACT_CONTACT_SQL =
        "CrmCcContactDao_updateActContact.sql";

    /**
     * 一件取得を行います。
     * 
     * @param dto
     *            CRMコンタクトモデル
     * @return 活動コンタクトコード検索結果モデル
     */
    public SaContactSearchResultDto getEntity(CrmCcContactDto dto) {

        SaContactSearchResultDto result = null;
        List<SaContactSearchResultDto> resultList =
            super.findBySqlFile(
                SaContactSearchResultDto.class,
                GET_ENTITY_SQL,
                dto);
        if (resultList != null && resultList.size() != 0) {
            result = resultList.get(0);
        }

        return result;
    }

    /**
     * CRMコンタクト有効チェックを行います。
     * 
     * @param checkDto
     *            営業活動（チェック項目あり）モデル
     * @return true（OKの場合）／false（NGの場合）
     */
    public boolean crmCountEnabledCheck(SalesActCheckDto checkDto) {

        boolean result = true;
        long count = super.getCountBySqlFile(CONTACT_ENABLE_CHECK, checkDto);
        if (0 == count) {
            result = false;
        }

        return result;
    }

    /**
     * コンタクト一覧の取得です。
     * 
     * @param criteria
     *            コンタクトの検索条件
     * 
     * @return コンタクト一覧
     */
    public PagingResult<ContactSearchResultDto> findByContactSearchCriteria(
            ContactSearchCriteriaDto criteria) {
        ContactSearchCriteriaDto condition = convertContainEspace(criteria);
        condition.addDefaultOrderBySpec("crmContactCd");
        PagingResult<ContactSearchResultDto> result =
            super.findPagingBySqlFile(
                ContactSearchResultDto.class,
                FIND_BY_CONTACT_SEARCH_CRITERIA_SQL,
                condition);
        return result;
    }

    /**
     * 部分一致検索条件をSQLエスケープします。
     * 
     * @param criteria
     *            コンタクトの検索条件
     * @return 部分一致検索条件をSQLエスケープした検索条件
     */
    private ContactSearchCriteriaDto convertContainEspace(
            ContactSearchCriteriaDto criteria) {
        ContactSearchCriteriaDto cloned =
            criteria.cloneInstance(ContactSearchCriteriaDto.class);
        cloned.crmContactCd = likePrefix(criteria.crmContactCd);
        cloned.crmContactName = likeContain(criteria.crmContactName);
        cloned.crmContactShortName = likeContain(criteria.crmContactShortName);
        cloned.crmContactNameKana = likeContain(criteria.crmContactNameKana);
        cloned.crmContactSearchName =
            likeContain(criteria.crmContactSearchName);
        cloned.crmContactType = likeContain(criteria.crmContactType);
        cloned.belong = likeContain(criteria.belong);
        cloned.post = likeContain(criteria.post);
        cloned.telephoneNumber = likeContain(criteria.telephoneNumber);
        cloned.keyPerson = likeContain(criteria.keyPerson);
        cloned.emailAddress = likeContain(criteria.emailAddress);
        return cloned;
    }

    /**
     * 担当チェックの場合、CRMアカウントエンティティの検索を行います。
     * 
     * @param crmContactCheckChargeDto
     *            CRMコンタクトモデル
     * @return CRMコンタクトエンティティ
     */
    public List<CrmCcContact> findByCrmContactCheckChargeDto(
            CrmContactCheckChargeDto crmContactCheckChargeDto) {
        List<CrmCcContact> result =
            super.findBySqlFile(
                CrmCcContact.class,
                FIND_BY_CONTACTDTO_SQL,
                crmContactCheckChargeDto);
        return result;
    }

    /**
     * 活動更新を行います。
     * 
     * @param dto
     *            CRMコンタクトモデル
     */
    public void updateActContact(CrmCcContactDto dto) {

        int count = super.updateBySqlFile(UPDATE_ACT_CONTACT_SQL, dto);
        if (0 == count) {
            throw new OptimisticLockRuntimeException(null);
        }
    }

    /**
     * CRMコンタクトの一件取得です。
     * 
     * @param crmCcContactDto
     *            CRMコンタクトDTO
     * @return CRMコンタクト
     */
    public CrmCcContact getEntityByDtoWithoutDeleteFlag(
            CrmCcContactDto crmCcContactDto) {
        StringBuilder str = new StringBuilder();
        List<Object> values = new ArrayList<Object>();
        getSqlWhere(crmCcContactDto, str, values);

        return jdbcManager.from(CrmCcContact.class).where(
            str.toString(),
            values.toArray()).getSingleResult();
    }

    /**
     * CRMコンタクトの一件取得です。
     * 
     * @param crmCcContactDto
     *            CRMコンタクトDTO
     * @return CRMコンタクト
     */
    public CrmCcContact getEntityByDto(CrmCcContactDto crmCcContactDto) {
        StringBuilder str = new StringBuilder();
        List<Object> values = new ArrayList<Object>();
        getSqlWhere(crmCcContactDto, str, values);
        str.append(" and ");
        str.append("delete_flag = '0'");

        return jdbcManager.from(CrmCcContact.class).where(
            str.toString(),
            values.toArray()).getSingleResult();
    }

    /**
     * CRMコンタクトの一件取得（担当判定あり）SQLの設定です。
     * 
     * @param crmCcContactDto
     *            CRMコンタクトDTO
     * @param str
     *            SQL
     * @param values
     *            SQLパラメータ
     * @return SQL
     */
    private StringBuilder getSqlWhere(CrmCcContactDto crmCcContactDto,
            StringBuilder str, List<Object> values) {
        boolean found = false;
        if (crmCcContactDto.companyCd != null) {
            str.append("company_cd = ?");
            values.add(crmCcContactDto.companyCd);
            found = true;
        }
        if (crmCcContactDto.crmContactCd != null) {
            if (found) {
                str.append(" and ");
            }
            str.append("crm_contact_cd = ?");
            values.add(crmCcContactDto.crmContactCd);
            found = true;
        }
        if (crmCcContactDto.localeId != null) {
            if (found) {
                str.append(" and ");
            }
            str.append("locale_id = ?");
            values.add(crmCcContactDto.localeId);
            found = true;
        }
        if (crmCcContactDto.startDate != null) {
            if (found) {
                str.append(" and ");
            }
            str.append("start_date <= ?");
            values.add(DateUtil.nowDate());
            found = true;
        }
        if (crmCcContactDto.endDate != null) {
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
     * 登録前CRMコンタクトコードの重複をチェックします。
     * 
     * @param crmCcContactDto
     *            CRMコンタクトDTO
     * @return CRMコンタクト
     */
    public List<CrmCcContact> getEntityForCheck(CrmCcContactDto crmCcContactDto) {
        StringBuilder str = new StringBuilder();
        List<Object> values = new ArrayList<Object>();
        getSqlWhere(crmCcContactDto, str, values);
        List<CrmCcContact> resultList =
            jdbcManager.from(CrmCcContact.class).where(
                str.toString(),
                values.toArray()).getResultList();
        return resultList;

    }

    /**
     * 
     * CRMコンタクトの一覧を検索します。
     * 
     * @param dto
     *            CRMコンタクトを一覧検索する
     * @return {@link CrmCcContact}
     */
    public List<CrmCcContact> getContactList(CrmCcContactDto dto) {

        StringBuffer sql = new StringBuffer();
        sql.append("company_cd = ? AND ");
        sql.append("crm_contact_cd = ?  AND ");
        sql.append("locale_id = ?  AND ");
        sql.append("delete_flag = ?");
        List<Object> data = new ArrayList<Object>();
        data.add(dto.companyCd);
        data.add(dto.crmContactCd);
        data.add(dto.localeId);
        data.add("0");

        List<CrmCcContact> resultList =
            jdbcManager.from(CrmCcContact.class).where(
                sql.toString(),
                data.toArray()).getResultList();
        return resultList;

    }

    // ----- 以下solr用の赤坂追加分 -----
    /**
     * 期間内に更新記録を持つCRMコンタクトを一覧検索します。<br>
     * offsetとlimitの指定により、範囲で返却します。
     * 
     * @param dto
     *            クローラワークページャーDTO
     * @return CRMコンタクトリスト
     */
    public List<CrmCcContact> findByUpdate(CrawlerWorkPagerDto dto) {

        OrderByItem cdOrder = new OrderByItem(CrmCcContactNames.crmContactCd());
        OrderByItem localeOrder = new OrderByItem(CrmCcContactNames.localeId());

        return super.jdbcManager
            .from(CrmCcContact.class)
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
     * 期間内に更新記録を持つCRMコンタクトの件数を取得します。
     * 
     * @param dto
     *            クローラワークDTO
     * @return CRMコンタクトの期間内更新件数
     */
    public long getCountByUpdate(CrawlerWorkDto dto) {

        return super.jdbcManager.from(CrmCcContact.class).where(
            getWhere(
                dto.companyCdList,
                dto.startDate,
                dto.endDate,
                dto.simpleStartDate,
                dto.simpleEndDate)).getCount();
    }

    /**
     * 更新された対象を取得する条件を構築します。<br>
     * 対象の期間内で更新日が変更されているデータや、<br>
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
        essentialCondition.in(CrmCcContactNames.companyCd(), companyCdList);
        essentialCondition.le(CrmCcContactNames.startDate(), endDate);
        essentialCondition.gt(CrmCcContactNames.endDate(), endDate);
        essentialCondition.eq(CrmCcContactNames.dummyFlag(), "0");

        // 更新日の条件
        SimpleWhere recordCondition = new SimpleWhere();
        recordCondition.gt(CrmCcContactNames.recordDate(), startDate);
        recordCondition.le(CrmCcContactNames.recordDate(), endDate);

        // 期間の条件
        SimpleWhere termCondition = new SimpleWhere();
        termCondition.ge(CrmCcContactNames.startDate(), simpleStartDate);
        termCondition.lt(CrmCcContactNames.startDate(), simpleEndDate);

        // 条件の結合
        CompositeWhere updateWhere =
            new CompositeWhere("OR", recordCondition, termCondition);
        return new CompositeWhere("AND", essentialCondition, updateWhere);

    }
}