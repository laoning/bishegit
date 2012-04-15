/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic;

import com.biz_integral.crm.cc.domain.dto.SalesActivityCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.SalesActivityResultDto;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * 営業活動のロジックです。
 */
public interface SalesActivityLogic {

    /**
     * 営業活動の一覧を取得します。
     * 
     * @param salesActivityCriteriaDto
     *            営業活動DTO
     * @return 営業活動リスト
     */
    public abstract PagingResult<SalesActivityResultDto> getEntityList(
            SalesActivityCriteriaDto salesActivityCriteriaDto);

}
