package com.biz_integral.crm.extension.solr.model;

import jp.co.nttdata.intra_mart.solr.domain.SolrSearchCondition;

import com.biz_integral.crm.extension.solr.constants.BizIntegralSolrConstants.SEARCH_FUNCTION;

/**
 * 検索機能を条件に設定します。
 */
public class SearchFunctionCondition extends SolrSearchCondition {

    /**
     * 検索機能を条件に設定するコンストラクタです。
     */
    public SearchFunctionCondition() {
        super();
    }

    /**
     * 検索機能を条件に設定するコンストラクタです。
     * 
     * @param functionArray
     *            検索する文書の対応機能
     */
    public SearchFunctionCondition(SEARCH_FUNCTION... functionArray) {
        super();
        setFunction(functionArray);
    }

    /**
     * 検索機能を条件に設定するコンストラクタです。
     * 
     * @param functionArray
     *            検索する文書の対応機能
     */
    public void setFunction(SEARCH_FUNCTION... functionArray) {
        for (SEARCH_FUNCTION function : functionArray) {
            term(function.field(), "1");
        }

    }
}
