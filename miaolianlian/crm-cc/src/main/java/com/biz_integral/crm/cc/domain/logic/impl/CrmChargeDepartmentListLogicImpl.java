/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.domain.dto.ChargeDeptListChangeAfterDto;
import com.biz_integral.crm.cc.domain.dto.ChargeDeptListChangeBeforeDto;
import com.biz_integral.crm.cc.domain.dto.ChargeDeptListChangeResultDto;
import com.biz_integral.crm.cc.domain.logic.CrmChargeDepartmentListLogic;
import com.biz_integral.crm.cc.domain.types.MainChargeFlg;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.mapper.BeanMapper;
import com.biz_integral.foundation.core.types.EnumNamesRegistry;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.foundation.core.util.LocaleUtil;
import com.biz_integral.service.domain.master.BizConfigurationProvider;

/**
 * CrmChargeDepartmentListLogicロジックの実装です。
 */
public class CrmChargeDepartmentListLogicImpl implements
        CrmChargeDepartmentListLogic {

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
    public List<ChargeDeptListChangeResultDto> setDepartmentList(
            List<ChargeDeptListChangeAfterDto> changeAfterList,
            List<ChargeDeptListChangeBeforeDto> changeBeforeList) {
        List<ChargeDeptListChangeResultDto> resultList =
            new ArrayList<ChargeDeptListChangeResultDto>();
        SimpleDateFormat dateFormat =
            new SimpleDateFormat(DateUtil.DATE_FORMAT_HYPHEN);

        List<String> deptCdBeforeList = new ArrayList<String>();
        for (ChargeDeptListChangeBeforeDto changeBefore : changeBeforeList) {
            deptCdBeforeList.add(changeBefore.departmentCd);
        }
        for (ChargeDeptListChangeAfterDto changeAfter : changeAfterList) {
            if (deptCdBeforeList.contains(changeAfter.departmentCd)) {
                for (ChargeDeptListChangeBeforeDto changeBefore : changeBeforeList) {
                    if (changeAfter.departmentCd
                        .equals(changeBefore.departmentCd)) {

                        // 変更前後をマージします。
                        ChargeDeptListChangeResultDto result =
                            new ChargeDeptListChangeResultDto();
                        result.departmentName = changeAfter.departmentName;
                        result.mainCharge = changeBefore.mainChargeFlag;
                        result.startDate = changeBefore.startDate;
                        result.endDate = changeBefore.endDate;
                        result.departmentCd = changeAfter.departmentCd;
                        result.mainChargeFlag = changeBefore.mainChargeFlag;
                        result.deptCmnStartDate =
                            changeAfter.effectiveTermStart;
                        result.deptCmnEndDate = changeAfter.effectiveTermEnd;
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

                // 新規として追加します。
                ChargeDeptListChangeResultDto result =
                    new ChargeDeptListChangeResultDto();
                result.departmentName = changeAfter.departmentName;
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
                result.departmentCd = changeAfter.departmentCd;
                result.mainChargeFlag = "0";
                result.deptCmnStartDate = changeAfter.effectiveTermStart;
                result.deptCmnEndDate = changeAfter.effectiveTermEnd;
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
    public List<ChargeDeptListChangeResultDto> setMainChargeDepartment(
            String departmentCd,
            List<ChargeDeptListChangeBeforeDto> changeBeforeDtoList) {
        List<ChargeDeptListChangeResultDto> resultList =
            new ArrayList<ChargeDeptListChangeResultDto>();
        String mainChargeCd = departmentCd;
        boolean isHappen = false;
        for (ChargeDeptListChangeBeforeDto changeBeforeDto : changeBeforeDtoList) {

            if (mainChargeCd.equals(changeBeforeDto.departmentCd)) {
                isHappen = true;

                // 主担当に設定します。
                ChargeDeptListChangeResultDto result =
                    setChargeDeptListChangeResultDto(changeBeforeDto);
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
                ChargeDeptListChangeResultDto result =
                    setChargeDeptListChangeResultDto(changeBeforeDto);
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
	 * 主担当に設定の場合、CRM担当組織一覧検索結果の設定します。
	 * 
	 * @param changeBeforeDto
	 *            担当組織の条件モデル
	 * @return 担当組織一覧の結果モデル
	 */
    private ChargeDeptListChangeResultDto setChargeDeptListChangeResultDto(
            ChargeDeptListChangeBeforeDto changeBeforeDto) {
        ChargeDeptListChangeResultDto result =
            beanMapper
                .map(changeBeforeDto, ChargeDeptListChangeResultDto.class);
        result.deptCmnStartDate = changeBeforeDto.effectiveTermStart;
        result.deptCmnEndDate = changeBeforeDto.effectiveTermEnd;
        return result;
    }
}
