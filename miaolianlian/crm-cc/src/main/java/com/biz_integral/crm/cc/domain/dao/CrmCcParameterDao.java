/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.dao;

import java.util.List;

import com.biz_integral.crm.cc.domain.dto.ParameterManageDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcParameter;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * {@link CrmCcParameter}のDaoクラスです。
 * 
 */
public class CrmCcParameterDao extends
        AbstractCrmCcParameterDao<CrmCcParameter> {

    /**
     * パラメータの値(String)を取得します。
     * 
     * @param paramCd
     *            パラメータコード
     * @param companyCd
     *            会社コード
     * @return パラメータの値(String)
     */
    public String getParameterValue(String paramCd, String companyCd) {
        String paramValue = null;

        CrmCcParameter param = new CrmCcParameter();
        param.setParameterCd(paramCd);
        param.setCompanyCd(companyCd);

        List<CrmCcParameter> result =
            super.findBySqlFile(
                CrmCcParameter.class,
                "CrmCcParameterDao_getParameterValue.sql",
                param);

        if (0 < result.size()) {
            paramValue = result.get(0).getParameterValue();
        }

        return paramValue;
    }

    /**
     * 
     * パラメータ明細取得。
     * <p>
     * 引数の条件に従ってパラメータ、パラメータ明細を検索し、成功した場合はユーザロケールIDに従った名称にて検索結果を返却します。
     * </p>
     * <ul>
     * <li>引数dtoの会社コードに合致する
     * <li>引数dtoのパラメータコードに合致する
     * <li>引数dtoの期間コードに合致する
     * <li>引数dtoのロケールIDに合致する
     * </ul>
     * 
     * @param dto
     *            パラメータ登録初期化モデル。次の条件を満たすこと。
     *            <ul>
     *            <li>引数dtoの会社コードが{@code null}でない、かつ空文字でない</li>
     *            <li>引数dtoのパラメータコードが{@code null}でない、かつ空文字でない</li>
     *            <li>引数dtoの期間コードが{@code null}でない、かつ空文字でない</li>
     *            <li>引数dtoのモジュール分類が{@code null}でない、かつ空文字でない</li>
     *            <li>引数dtoのロケールIDが{@code null}でない、かつ空文字でない</li>
     *            </ul>
     * @return パラメータViewDTOモデル
     */
    public ParameterManageDto getByCriteria(ParameterManageDto dto) {

        List<ParameterManageDto> list =
            findBySqlFile(
                ParameterManageDto.class,
                "CrmCcParameterDao_getByCriteria.sql",
                dto);

        if (list.isEmpty()) {
            return null;
        }

        return list.get(0);
    }

    /**
     * 
     * 初期設定/表示コントロール、パラメータ明細一覧取得。
     * <p>
     * 引数の条件に従ってパラメータ、パラメータ明細を検索し、検索結果を返却します。
     * </p>
     * <ul>
     * <li>引数dtoの会社コードに合致する
     * <li>引数dtoのロケールIDに合致する
     * <li>引数dtoのパラメータ名に合致する
     * <li>引数dtoのモジュール分類に合致する
     * </ul>
     * 
     * @param dto
     *            パラメータ検索初期化モデル。次の条件を満たすこと。
     *            <ul>
     *            <li>引数dtoの会社コードが{@code null}でない、かつ空文字でない</li>
     *            <li>引数dtoのパラメータコードが{@code null}でない、かつ空文字でない</li>
     *            <li>引数dtoのモジュール分類が{@code null}でない、かつ空文字でない</li>
     *            <li>引数dtoのロケールIDが{@code null}でない、かつ空文字でない</li>
     *            </ul>
     * @return ページング検索結果＜パラメータViewDTO＞
     */
    public PagingResult<ParameterManageDto> findByCriteria(
            ParameterManageDto dto) {
        dto = convertLikeEspace(dto);
        PagingResult<ParameterManageDto> result =
            findPagingBySqlFile(
                ParameterManageDto.class,
                "CrmCcParameterDao_findByCriteria.sql",
                dto);
        return result;
    }

    /**
     * 含む検索条件をSQLエスケープします。
     * 
     * @param criteria
     *            検索条件
     * @return 前方一致検索条件をSQLエスケープした検索条件
     */
    private ParameterManageDto convertLikeEspace(ParameterManageDto criteria) {
        ParameterManageDto cloned =
            criteria.cloneInstance(ParameterManageDto.class);
        cloned
            .setParameterName(this.likeContain((criteria.getParameterName())));
        return cloned;
    }

}