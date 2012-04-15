/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic;

import java.util.List;

import com.biz_integral.crm.cc.domain.dto.ChargeUserListChangeAfterDto;
import com.biz_integral.crm.cc.domain.dto.ChargeUserListChangeBeforeDto;
import com.biz_integral.crm.cc.domain.dto.ChargeUserListChangeResultDto;

/**
 * CRM担当者一覧のロジックです。
 */
public interface CrmChargeUserListLogic {

    /**
     * CRM担当者一覧の一覧設定します。
     * 
     * @param changeAfterList
     *            担当者設定ボタン変更後条件Dto
     * @param changeBeforeList
     *            担当者設定ボタン変更前条件Dto
     * @return 担当者一覧
     */
    public abstract List<ChargeUserListChangeResultDto> setUserList(
            List<ChargeUserListChangeAfterDto> changeAfterList,
            List<ChargeUserListChangeBeforeDto> changeBeforeList);

    /**
     * CRM担当者主担当者設定します。
     * 
     * @param userCd
     *            ユーザコード 主選択設定条件モデル
     * @param changeBeforeDtoList
     *            担当者設定ボタン変更前条件Dto
     * @return 担当者一覧
     */
    public abstract List<ChargeUserListChangeResultDto> setMainChargeUser(
            String userCd,
            List<ChargeUserListChangeBeforeDto> changeBeforeDtoList);

}
