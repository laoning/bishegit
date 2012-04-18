/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.dao;

import static com.biz_integral.crm.cc.domain.entity.CrmCcSynonymPatternNames.companyCd;
import static com.biz_integral.crm.cc.domain.entity.CrmCcSynonymPatternNames.localeId;
import static com.biz_integral.crm.cc.domain.entity.CrmCcSynonymPatternNames.name;
import static org.seasar.extension.jdbc.operation.Operations.asc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.seasar.extension.jdbc.Where;
import org.seasar.extension.jdbc.where.SimpleWhere;

import com.biz_integral.crm.cc.domain.entity.CrmCcSynonymPattern;
import com.biz_integral.foundation.core.util.UniqueIdGenerator;

/**
 * {@link CrmCcSynonymPattern}のDaoクラスです。
 * 
 */
public class CrmCcSynonymPatternDao extends
        AbstractCrmCcSynonymPatternDao<CrmCcSynonymPattern> {

    /**
     * 名称一覧取得のSQL文
     */
    private static final String FIND_NAME_LIST_SQL =
        "CrmCcSynonymPatternDao_findNameList.sql";

    /**
     * 類義語パターンを一件取得します。
     * 
     * @param pattern
     *            類義語パターンエンティティ
     * @return 類義語パターンエンティティ
     */
    public CrmCcSynonymPattern getEntity(CrmCcSynonymPattern pattern) {
        StringBuffer sql = new StringBuffer();
        sql.append("company_cd = ? AND ");
        sql.append("locale_id = ?  AND ");
        sql.append("synonym_type = ?  AND ");
        sql.append("cd = ?  AND ");
        sql.append("name = ?  AND ");
        sql.append("synonym_name = ?");
        List<Object> data = new ArrayList<Object>();
        data.add(pattern.getCompanyCd());
        data.add(pattern.getLocaleId());
        data.add(pattern.getSynonymType());
        data.add(pattern.getCd());
        data.add(pattern.getName());
        data.add(pattern.getSynonymName());

        return jdbcManager.from(CrmCcSynonymPattern.class).where(
            sql.toString(),
            data.toArray()).getSingleResult();
    }

    /**
     * 類義語パターンを登録します。
     * 
     * @param pattern
     *            類義語パターンエンティティ
     */
    public void createSynonymPattern(CrmCcSynonymPattern pattern) {
        pattern.setId(UniqueIdGenerator.getInstance().create());
        this.insert(pattern);
    }

    /**
     * 名称一覧を取得します。
     * 
     * @param criteria
     *            検索条件モデル
     * @return 類義語パターンリスト
     */
    public List<CrmCcSynonymPattern> findNameList(Map criteria) {

        return super.findBySqlFile(
            CrmCcSynonymPattern.class,
            FIND_NAME_LIST_SQL,
            criteria);
    }

    /**
     * 類義語名称一覧を取得します。
     * 
     * @param entity
     *            類義語パターンentity
     * @return 類義語パターンリスト
     */
    public List<CrmCcSynonymPattern> findSynonymNameList(
            CrmCcSynonymPattern entity) {

        return jdbcManager
            .from(CrmCcSynonymPattern.class)
            .where(createFindSynonymNameListWhere(entity))
            .orderBy(asc(name()))
            .getResultList();
    }

    /**
     * {@link CrmCcSynonymPatternDao#findSynonymNameList(CrmCcSynonymPattern)}
     * 用の検索条件を作成して返します。
     * 
     * @param entity
     *            類義語パターンentity
     * @return 検索条件
     */
    private Where createFindSynonymNameListWhere(CrmCcSynonymPattern entity) {

        SimpleWhere where =
            new SimpleWhere().eq(companyCd(), entity.getCompanyCd()).eq(
                localeId(),
                entity.getLocaleId()).eq(name(), entity.getName());

        return where;
    }
}