/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.dao;

import java.util.List;

import com.biz_integral.crm.cc.domain.dto.CrmCcContactChargeUserCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactChargeUserResultDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcContactChargeUser;
import com.biz_integral.crm.sa.domain.dto.SalesActCheckDto;
import com.biz_integral.foundation.core.util.DateUtil;

/**
 * {@link CrmCcContactChargeUser}のDaoクラスです。
 * 
 */
public class CrmCcContactChargeUserDao extends
        AbstractCrmCcContactChargeUserDao<CrmCcContactChargeUser> {

    /**
     * ユーザーの件数を取得用SQL
     */
    private static final String COUNT_OF_USER_SQL =
        "CrmCcContactChargeUserDao_getCountOfUser.sql";

    /**
     * 有効なユーザーの件数を取得用SQL
     */
    private static final String COUNT_OF_ENABLED_USER_SQL =
        "CrmCcContactChargeUserDao_getCountOfEnabledUser.sql";

    /**
     * 一覧検索を取得用SQL
     */
    private static final String ENTITYLIST_SQL =
        "CrmCcContactChargeUserDao_getEntityList.sql";

    /**
     * 登録前CRMコンタクト担当者一覧検索を取得用SQL
     */
    private static final String CREATE_BEFORE_ENTITYLIST_SQL =
        "CrmCcContactChargeUserDao_findByCreateBeforeChargeUserCriteriaDto.sql";

    /**
     * ユーザーの件数を取得します。<br>
     * 
     * @param checkDto
     *            営業活動（チェック項目あり）モデル
     * @return ユーザーの件数
     */
    public long getCountOfUser(SalesActCheckDto checkDto) {

        return super.getCountBySqlFile(COUNT_OF_USER_SQL, checkDto);
    }

    /**
     * 有効なユーザーの件数を取得します。<br>
     * 
     * @param checkDto
     *            営業活動（チェック項目あり）モデル
     * @return 有効なユーザーの件数
     */
    public long getCountOfEnabledUser(SalesActCheckDto checkDto) {

        return super.getCountBySqlFile(COUNT_OF_ENABLED_USER_SQL, checkDto);
    }

    /**
     * CRMコンタクト担当者を一覧検索します。
     * 
     * @param criteriaDto
     *            CRMコンタクト担当者検索のDto
     * @return CRMコンタクト担当者一覧検索結果
     */
    public List<CrmCcContactChargeUserResultDto> getEntityList(
            CrmCcContactChargeUserCriteriaDto criteriaDto) {

        List<CrmCcContactChargeUserResultDto> resultList =
            super.findBySqlFile(
                CrmCcContactChargeUserResultDto.class,
                ENTITYLIST_SQL,
                criteriaDto);
        for (CrmCcContactChargeUserResultDto dto : resultList) {
            dto.endDate =
                DateUtil.getCalculator(dto.endDate).addDay(-1).asDate();
            dto.userCmnEndDate =
                DateUtil.getCalculator(dto.userCmnEndDate).addDay(-1).asDate();
        }
        return resultList;
    }

    /**
     * 登録前CRMコンタクト担当者を一覧検索します。
     * 
     * @param chargeUserCriteriaDto
     *            CRMコンタクト担当者者検索条件のDto
     * @return CRMコンタクト担当者者検索条件のDto
     */
    public List<CrmCcContactChargeUser> getCreateEntityList(
            CrmCcContactChargeUserCriteriaDto chargeUserCriteriaDto) {
        List<CrmCcContactChargeUser> resultList =
            super.findBySqlFile(
                CrmCcContactChargeUser.class,
                CREATE_BEFORE_ENTITYLIST_SQL,
                chargeUserCriteriaDto);
        return resultList;
    }
}