/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.impl;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.domain.context.CrmContext;
import com.biz_integral.crm.cc.domain.dao.CrmCcParameterDao;
import com.biz_integral.crm.cc.domain.dto.ParameterManageDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcParameter;
import com.biz_integral.crm.cc.domain.logic.ParameterManageLogic;
import com.biz_integral.crm.cc.domain.types.ParameterDataType;
import com.biz_integral.crm.cc.domain.types.UseType;
import com.biz_integral.foundation.core.message.MessageBuilder;
import com.biz_integral.foundation.core.types.EnumNamesRegistry;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.foundation.core.util.LocaleUtil;
import com.biz_integral.foundation.core.util.StringUtil;
import com.biz_integral.foundation.core.validation.ValidationException;
import com.biz_integral.foundation.core.validation.ValidationResult;
import com.biz_integral.foundation.core.validation.ValidationResults;
import com.biz_integral.foundation.core.validation.ValidatorEngine;
import com.biz_integral.foundation.core.validation.constraint.validator.DatePatternValidator;
import com.biz_integral.foundation.core.validation.constraint.validator.HankakuKanaStringValidator;
import com.biz_integral.foundation.core.validation.constraint.validator.MaxLengthValidator;
import com.biz_integral.foundation.core.validation.constraint.validator.NumberValidator;
import com.biz_integral.foundation.core.validation.constraint.validator.NumericAlphaStringValidator;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * {@link ParameterManageLogic}の実装クラス。
 */
public class ParameterManageLogicImpl implements ParameterManageLogic {

    /**
     * 区分に関する名称管理API
     */
    @Resource
    protected EnumNamesRegistry enumNamesRegistry;

    /** CRMコンテキスト */
    @Resource
    protected CrmContext crmContext;

    /** パラメータDAO */
    @Resource
    protected CrmCcParameterDao crmCcParameterDao;

    /** ValidatorEngine */
    @Resource
    protected ValidatorEngine validator;

    /**
     * {@inheritDoc}
     */
    public ParameterManageDto getEntity(ParameterManageDto dto) {

        ParameterManageDto retDto = crmCcParameterDao.getByCriteria(dto);

        return retDto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PagingResult<ParameterManageDto> search(ParameterManageDto model) {

        // 検索結果を取得 : ページング検索結果＜パラメータViewDTO＞
        PagingResult<ParameterManageDto> resultList =
            crmCcParameterDao.findByCriteria(model);

        return resultList;

    }

    /**
     * {@inheritDoc}
     */
    public ValidationResults validate(ParameterManageDto dto) {

        // 該当パラメータコードのパラメータ型区分、桁数、区分Idを取得する。
        // エンティティ１件取得
        CrmCcParameter retDto =
            crmCcParameterDao.getByPk(crmContext.getCompanyCd(), dto
                .getParameterCd(), dto.getTermCd());

        // パラメータ値のデータ型について検証を行う。
        String value = dto.getParameterValue();
        String dataType = retDto.getParameterDataType();
        String typeId = retDto.getTypeId();
        Integer digits = retDto.getParameterDigits().intValue();

        Boolean isUnset = false;

        ValidationResults results = new ValidationResults();

        // 入力値.パラメータ値
        if (StringUtil.isAllEmpty(dto.getParameterValue())) {
            throw new RuntimeException();
        }

        boolean isValid = true;

        // パラメータ値のデータ型について検証を行う。
        if (!dataType.isEmpty()) {
            // IF 入力値.パラメータ型区分 == 半角数字
            if (ParameterDataType.ONEBYTENUMBER.value().equals(dataType)) {

                // 数値チェッククラス
                NumberValidator numberValidator = new NumberValidator();

                // IF 変数.パラメータ値 != 半角数字
                isValid = numberValidator.isValid(value);

                if (!isValid) {
                    String dtTp =
                        enumNamesRegistry.getName(
                            ParameterDataType.ONEBYTENUMBER,
                            LocaleUtil.toLocale(crmContext.getLocaleId()));

                    results.add(new ValidationResult(MessageBuilder.create(
                        "E.CRM.CC.00078").addParameter(dtTp).toMessage()));

                    return results;
                }

            } else if (ParameterDataType.ONEBYTEENGLISHNUMBER.value().equals(
                dataType)) {
                // ELSE IF 入力値.パラメータ型区分 == 半角英数字

                // 半角英数字属性チェッククラス
                NumericAlphaStringValidator numericAlphaStringValidator =
                    new NumericAlphaStringValidator();

                // IF 変数.パラメータ値 != 半角英数字
                isValid = numericAlphaStringValidator.isValid(value);

                if (!isValid) {
                    String dtTp =
                        enumNamesRegistry.getName(
                            ParameterDataType.ONEBYTEENGLISHNUMBER,
                            LocaleUtil.toLocale(crmContext.getLocaleId()));

                    results.add(new ValidationResult(MessageBuilder.create(
                        "E.CRM.CC.00078").addParameter(dtTp).toMessage()));

                    return results;
                }

            } else if (ParameterDataType.TWOBYTECHARACTERS.value().equals(
                dataType)) {
                // // ELSE IF 入力値.パラメータ型区分 == 全角文字
                // チェックしない。
            } else if (ParameterDataType.ONEBYTEKANA.value().equals(dataType)) {
                // ELSE IF 入力値.パラメータ型区分 == 半角カナ

                // 半角カナ属性チェッククラス
                HankakuKanaStringValidator hankakuKanaStringValidator =
                    new HankakuKanaStringValidator();

                // IF 変数.パラメータ値 != 半角カナ
                isValid = hankakuKanaStringValidator.isValid(value);

                if (!isValid) {
                    String dtTp =
                        enumNamesRegistry.getName(
                            ParameterDataType.ONEBYTEKANA,
                            LocaleUtil.toLocale(crmContext.getLocaleId()));

                    results.add(new ValidationResult(MessageBuilder.create(
                        "E.CRM.CC.00078").addParameter(dtTp).toMessage()));

                    return results;
                }

            } else {
                // 入力値.パラメータ型区分 == 未設定
                isUnset = true;
            }
        }

        // パラメータ値　パラメータ桁数チェック
        // IF 変数.パラメータ値 > 入力値.パラメータ桁数
        if (digits != null) {
            // 最大バイト長チェッククラス
            MaxLengthValidator maxLengthValidator = new MaxLengthValidator();

            if (!maxLengthValidator.isValid(value, digits)) {
                // メッセージを生成する。
                results.add(new ValidationResult(MessageBuilder.create(
                    "E.CRM.CC.00082").addParameter(digits).toMessage()));
                return results;
            }
        }

        // IF 入力値.区分ID != NULL
        if (StringUtil.isNotEmpty(typeId)) {
            isValid = false;
            if ("crm.cc.use_type".equalsIgnoreCase(typeId)) {
                for (UseType ut : UseType.values()) {
                    if (ut.value().equals(value)) {
                        isValid = true;
                    }
                }
                // 不正パラメータ区分値
                if (!isValid) {
                    String msgParm = "(";
                    // メッセージを生成する。
                    for (UseType ut : UseType.values()) {
                        if (!"(".equals(msgParm)) {
                            msgParm = msgParm.concat(",");
                        }
                        msgParm = msgParm.concat(ut.value());
                    }
                    msgParm = msgParm.concat(")");

                    results.add(new ValidationResult(MessageBuilder.create(
                        "E.CRM.CC.00078").addParameter(msgParm).toMessage()));
                }
            } else if ("date".equalsIgnoreCase(typeId)) {
                DatePatternValidator datePatternValidator =
                    new DatePatternValidator();

                // 半角英数字属性チェッククラス
                NumberValidator numberValidator = new NumberValidator();

                // IF 変数.パラメータ値 != 半角数字
                isValid = numberValidator.isValid(value.replace("/", ""));

                if (isValid) {
                    if (value.length() == 10) {
                        isValid =
                            datePatternValidator.isValid(
                                value,
                                DateUtil.DATE_FORMAT_SEPARATE);
                    } else if (value.length() == 8) {
                        isValid =
                            datePatternValidator.isValid(
                                value,
                                DateUtil.DATE_FORMAT);
                    } else {
                        isValid = false;
                    }
                }

                if (!isValid) {
                    results.add(new ValidationResult(MessageBuilder.create(
                        "E.CRM.CC.00104").toMessage()));
                }

            } else {
                // テーブル値不正
                throw new RuntimeException();
            }

        } else if (isUnset) {
            // パラメータ値区分IDも未設定の場合
            // パラメータ値チェック方法がない（テーブル値不正）
            throw new RuntimeException();
        }
        return results;
    }

    /**
     * {@inheritDoc}
     */
    public void update(ParameterManageDto dto) {

        // ロックあり１件取得
        CrmCcParameter parameter =
            crmCcParameterDao.getByPkWithLock(crmContext.getCompanyCd(), dto
                .getParameterCd(), dto.getTermCd());

        // バージョン番号チェック
        if (!parameter.getVersionNo().equals(dto.getVersionNo())) {
            ValidationResults results = new ValidationResults();
            results.add(new ValidationResult(MessageBuilder.create(
                "E.CRM.CC.00065").toMessage()));
            throw new ValidationException(results);
        }

        // パラメータ値セット
        parameter.setParameterValue(dto.getParameterValue());

        // パラメータ更新処理を実行する。
        crmCcParameterDao.update(parameter);
    }
}
