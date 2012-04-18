/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.dao;

import java.util.List;

import javax.annotation.Resource;

import org.seasar.extension.jdbc.name.PropertyName;

import com.biz_integral.crm.cc.domain.dto.ContactDeleteCompetitionCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactCompetitionCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactCompetitionDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcContactCompetition;
import com.biz_integral.crm.cc.domain.entity.CrmCcContactCompetitionNames;
import com.biz_integral.foundation.core.mapper.BeanMapper;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * {@link CrmCcContactCompetition}のDaoクラスです。
 * 
 */
public class CrmCcContactCompetitionDao extends
        AbstractCrmCcContactCompetitionDao<CrmCcContactCompetition> {

    /**
     * {@link BeanMapper}の実装
     */
    @Resource
    protected BeanMapper beanMapper;

    /**
     * 一覧検索用SQL
     */
    private static final String FIND_ENTITYLIST_SQL =
        "CrmCcContactCompetitionDao_findByContactCompetitionCriteriaDto.sql";

    /**
     * 一件取得用SQL
     */
    private static final String GET_ENTITY_SQL =
        "CrmCcContactCompetitionDao_getEntity.sql";

    /**
     * CRMコンタクト競合を一覧検索します。
     * 
     * @param criteriadto
     *            CRMコンタクト競合のDto
     * @return CRMコンタクト競合一覧
     */
    public PagingResult<CrmCcContactCompetition> getEntityList(
            CrmCcContactCompetitionCriteriaDto criteriadto) {
        criteriadto.addDefaultOrderBySpec("competitionName");
        PagingResult<CrmCcContactCompetition> result =
            super.findPagingBySqlFile(
                CrmCcContactCompetition.class,
                FIND_ENTITYLIST_SQL,
                criteriadto);
        return result;
    }

    /**
     * CRMコンタクト競合を削除します。
     * 
     * @param criteriadto
     *            CRMコンタクト競合のDto
     */
    public void updateDeleteCompetitionCriteria(
            ContactDeleteCompetitionCriteriaDto criteriadto) {
        CrmCcContactCompetition entity =
            beanMapper.map(criteriadto, CrmCcContactCompetition.class);
        entity.setDeleteFlag(true);
        super.updateIncludes(
            entity,
            CrmCcContactCompetitionNames.deleteFlag(),
            CrmCcContactCompetitionNames.versionNo(),
            CrmCcContactCompetitionNames.recordUserCd(),
            CrmCcContactCompetitionNames.recordDate());
    }

    /**
     * CRMコンタクト競合を一件取得します。
     * 
     * @param dto
     *            CRMコンタクト競合のDto
     * @return CRMコンタクト競合一件取得
     */
    public CrmCcContactCompetition getEntity(CrmCcContactCompetitionDto dto) {

        CrmCcContactCompetition result = null;
        List<CrmCcContactCompetition> resultList =
            super.findBySqlFile(
                CrmCcContactCompetition.class,
                GET_ENTITY_SQL,
                dto);

        if (resultList != null && resultList.size() != 0) {
            result = resultList.get(0);
        }

        return result;
    }

    /**
     * CRMコンタクト競合を登録します。
     * 
     * @param dto
     *            CRMコンタクト競合のDto
     */
    public void create(CrmCcContactCompetitionDto dto) {

        CrmCcContactCompetition entity =
            beanMapper.map(dto, CrmCcContactCompetition.class);
        super.insert(entity);
    }

    /**
     * CRMコンタクト競合を更新します。
     * 
     * @param dto
     *            CRMコンタクト競合のDto
     */
    @SuppressWarnings("unchecked")
    public void update(CrmCcContactCompetitionDto dto) {
        CrmCcContactCompetition entity =
            beanMapper.map(dto, CrmCcContactCompetition.class);
        CrmCcContactCompetition result = new CrmCcContactCompetition();
        List<CrmCcContactCompetition> entityList =
            super.findByPkIgnoreTerm(
                dto.companyCd,
                dto.crmContactCd,
                dto.competitionId);
        if (entityList != null && entityList.size() != 0) {
            result = entityList.get(0);
        }
        entity.setTermCd(result.getTermCd());

        super.updateExcludes(
            entity,
            new PropertyName<String>("companyCd"),
            new PropertyName<String>("crmContactCd"),
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