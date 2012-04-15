/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.dao;

import java.util.List;

import javax.annotation.Resource;

import org.seasar.extension.jdbc.name.PropertyName;

import com.biz_integral.crm.cc.domain.dto.CrmCcAccountCompetitionDto;
import com.biz_integral.crm.cc.domain.dto.DeleteCompetitionCriteriaDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountCompetition;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountCompetitionNames;
import com.biz_integral.foundation.core.mapper.BeanMapper;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * {@link CrmCcAccountCompetition}のDaoクラスです。
 * 
 */
public class CrmCcAccountCompetitionDao extends
        AbstractCrmCcAccountCompetitionDao<CrmCcAccountCompetition> {

    /**
     * {@link BeanMapper}の実装
     */
    @Resource
    protected BeanMapper beanMapper;

    /**
     * 一覧検索用SQL
     */
    private static final String FIND_ENTITYLIST_SQL =
        "CrmCcAccountCompetitionDao_findByAccountCompetitionCriteriaDto.sql";

    /**
     * 一件取得用SQL
     */
    private static final String GET_ENTITY_SQL =
        "CrmCcAccountCompetitionDao_getEntity.sql";

    /**
     * CRMアカウント競合を一覧検索します。
     * 
     * @param dto
     *            CRMアカウント競合のDto
     * @return CRMアカウント競合一覧
     */
    public PagingResult<CrmCcAccountCompetition> getEntityList(
            CrmCcAccountCompetitionDto dto) {

        dto.addDefaultOrderBySpec("competitionName");
        PagingResult<CrmCcAccountCompetition> result =
            super.findPagingBySqlFile(
                CrmCcAccountCompetition.class,
                FIND_ENTITYLIST_SQL,
                dto);

        return result;
    }

    /**
     * CRMアカウント競合を削除します。
     * 
     * @param criteriadto
     *            CRMアカウント競合のDto
     */
    public void updateDeleteCompetitionCriteria(
            DeleteCompetitionCriteriaDto criteriadto) {
        CrmCcAccountCompetition entity =
            beanMapper.map(criteriadto, CrmCcAccountCompetition.class);
        entity.setDeleteFlag(true);
        super.updateIncludes(
            entity,
            CrmCcAccountCompetitionNames.deleteFlag(),
            CrmCcAccountCompetitionNames.versionNo(),
            CrmCcAccountCompetitionNames.recordUserCd(),
            CrmCcAccountCompetitionNames.recordDate());
    }

    /**
     * CRMアカウント競合を一件取得します。
     * 
     * @param dto
     *            CRMアカウント競合のDto
     * @return CRMアカウント競合一件取得
     */
    public CrmCcAccountCompetition getEntity(CrmCcAccountCompetitionDto dto) {

        CrmCcAccountCompetition result = null;
        List<CrmCcAccountCompetition> resultList =
            super.findBySqlFile(
                CrmCcAccountCompetition.class,
                GET_ENTITY_SQL,
                dto);

        if (resultList != null && resultList.size() != 0) {
            result = resultList.get(0);
        }

        return result;
    }

    /**
     * CRMアカウント競合を登録します。
     * 
     * @param dto
     *            CRMアカウント競合のDto
     */
    public void create(CrmCcAccountCompetitionDto dto) {

        CrmCcAccountCompetition entity =
            beanMapper.map(dto, CrmCcAccountCompetition.class);
        super.insert(entity);
    }

    /**
     * CRMアカウント競合を更新します。
     * 
     * @param dto
     *            CRMアカウント競合のDto
     */
    @SuppressWarnings("unchecked")
    public void update(CrmCcAccountCompetitionDto dto) {
        CrmCcAccountCompetition entity =
            beanMapper.map(dto, CrmCcAccountCompetition.class);

        CrmCcAccountCompetition result = new CrmCcAccountCompetition();
        List<CrmCcAccountCompetition> entityList =
            super.findByPkIgnoreTerm(
                dto.companyCd,
                dto.crmAccountCd,
                dto.competitionId);
        if (entityList != null && entityList.size() != 0) {
            result = entityList.get(0);
        }
        entity.setTermCd(result.getTermCd());

        super.updateExcludes(
            entity,
            new PropertyName<String>("companyCd"),
            new PropertyName<String>("crmAccountCd"),
            new PropertyName<String>("competitionId"),
            new PropertyName<String>("termCd"),
            new PropertyName<String>("startDate"),
            new PropertyName<String>("endDate"),
            new PropertyName<String>("competitionNameKana"),
            new PropertyName<String>("deleteFlag"),
            new PropertyName<String>("sortKey"),
            new PropertyName<String>("createUserCd"),
            new PropertyName<String>("createDate"));
    }

}