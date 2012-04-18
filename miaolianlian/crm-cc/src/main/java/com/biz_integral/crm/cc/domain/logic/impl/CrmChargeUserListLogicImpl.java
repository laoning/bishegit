/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.domain.dto.ChargeUserListChangeAfterDto;
import com.biz_integral.crm.cc.domain.dto.ChargeUserListChangeBeforeDto;
import com.biz_integral.crm.cc.domain.dto.ChargeUserListChangeResultDto;
import com.biz_integral.crm.cc.domain.logic.CrmChargeUserListLogic;
import com.biz_integral.crm.cc.domain.types.MainChargeFlg;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.mapper.BeanMapper;
import com.biz_integral.foundation.core.types.EnumNamesRegistry;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.foundation.core.util.LocaleUtil;
import com.biz_integral.service.domain.master.BizConfigurationProvider;

/**
 * CrmChargeUserListLogicロジックの実装です。
 */
public class CrmChargeUserListLogicImpl implements CrmChargeUserListLogic {

    /**
     * {@link BizConfigurationProvider コンテキストコンテナー}
     */
    @Resource
    protected BizConfigurationProvider bizConfigurationProvider;

    /**
     * 区分に関する名称管理API
     */
    @Resource
    protected EnumNamesRegistry enumNamesRegistry;

    /**
     * {@link BeanMapper}
     */
    @Resource
    protected BeanMapper beanMapper;

    /**
     * {@link ContextContainer コンテキストコンテナー}
     */
    @Resource
    protected ContextContainer contextContainer;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ChargeUserListChangeResultDto> setUserList(
            List<ChargeUserListChangeAfterDto> changeAfterList,
            List<ChargeUserListChangeBeforeDto> changeBeforeList) {
        List<ChargeUserListChangeResultDto> resultList =
            new ArrayList<ChargeUserListChangeResultDto>();

        SimpleDateFormat dateFormat =
            new SimpleDateFormat(DateUtil.DATE_FORMAT_HYPHEN);

        List<String> deptCdBeforeList = new ArrayList<String>();
        for (ChargeUserListChangeBeforeDto changeBefore : changeBeforeList) {
            deptCdBeforeList.add(changeBefore.userCd);
        }
        for (ChargeUserListChangeAfterDto changeAfter : changeAfterList) {
            if (deptCdBeforeList.contains(changeAfter.userCd)) {
                for (ChargeUserListChangeBeforeDto changeBefore : changeBeforeList) {
                    if (changeAfter.userCd.equals(changeBefore.userCd)) {
                        ChargeUserListChangeResultDto result =
                            new ChargeUserListChangeResultDto();

                        // 変更前後をマージします。
                        result.userName = changeAfter.userName;
                        result.mainCharge = changeBefore.mainChargeFlag;
                        result.startDate = changeBefore.startDate;
                        result.endDate = changeBefore.endDate;
                        result.userCd = changeAfter.userCd;
                        result.mainChargeFlag = changeBefore.mainChargeFlag;
                        result.userCmnStartDate =
                            changeAfter.effectiveTermStart;
                        result.userCmnEndDate = changeAfter.effectiveTermEnd;
                        result.mainCharge =
                            this.enumNamesRegistry.getName(MainChargeFlg
                                .getEnum(result.mainCharge), LocaleUtil
                                .toLocale(contextContainer
                                    .getUserContext()
                                    .getLocaleID()));
                        resultList.add(result);

                    }
                }
            } else {
                ChargeUserListChangeResultDto result =
                    new ChargeUserListChangeResultDto();

                // 新規として追加します。
                result.userName = changeAfter.userName;
                result.mainCharge = "0";
                result.startDate =
                    dateFormat.format(DateUtil.nowDate()).toString();
                result.endDate =
                    dateFormat.format(
                        DateUtil
                            .getCalculator(
                                bizConfigurationProvider.getEndDate())
                            .addDay(-1)
                            .asDate()).toString();
                result.userCd = changeAfter.userCd;
                result.mainChargeFlag = "0";
                result.userCmnStartDate = changeAfter.effectiveTermStart;
                result.userCmnEndDate = changeAfter.effectiveTermEnd;
                result.mainCharge =
                    this.enumNamesRegistry.getName(MainChargeFlg
                        .getEnum(result.mainCharge), LocaleUtil
                        .toLocale(contextContainer
                            .getUserContext()
                            .getLocaleID()));
                resultList.add(result);
            }
        }
        return resultList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ChargeUserListChangeResultDto> setMainChargeUser(String userCd,
            List<ChargeUserListChangeBeforeDto> changeBeforeDtoList) {
        List<ChargeUserListChangeResultDto> resultList =
            new ArrayList<ChargeUserListChangeResultDto>();
        String mainChargeCd = userCd;
        boolean isHappen = false;
        for (ChargeUserListChangeBeforeDto changeBeforeDto : changeBeforeDtoList) {
            if (mainChargeCd.equals(changeBeforeDto.userCd)) {
                isHappen = true;

                // 主担当に設定します。
                ChargeUserListChangeResultDto result =
                    setChargeUserListChangeResultDto(changeBeforeDto);
                result.mainCharge = "1";
                result.mainChargeFlag = "1";
                result.mainCharge =
                    this.enumNamesRegistry.getName(MainChargeFlg
                        .getEnum(result.mainCharge), LocaleUtil
                        .toLocale(contextContainer
                            .getUserContext()
                            .getLocaleID()));
                resultList.add(result);
            } else {

                // 主担当を解除します。
                ChargeUserListChangeResultDto result =
                    setChargeUserListChangeResultDto(changeBeforeDto);
                result.mainCharge = "0";
                result.mainChargeFlag = "0";
                result.mainCharge =
                    this.enumNamesRegistry.getName(MainChargeFlg
                        .getEnum(result.mainCharge), LocaleUtil
                        .toLocale(contextContainer
                            .getUserContext()
                            .getLocaleID()));
                resultList.add(result);
            }
        }
        if (!isHappen) {
            throw new IllegalArgumentException();
        }
        return resultList;
    }

    /**
     * 主担当に設定の場合、CRM担当者一覧検索結果の設定します。
     * 
     * @param changeBeforeDto
     *            担当者の条件Dto
     * @return 担当者の結果Dto
     */
    private ChargeUserListChangeResultDto setChargeUserListChangeResultDto(
            ChargeUserListChangeBeforeDto changeBeforeDto) {
        return beanMapper.map(
            changeBeforeDto,
            ChargeUserListChangeResultDto.class);
    }
}
