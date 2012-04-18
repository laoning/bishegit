/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic;

import java.util.List;

import com.biz_integral.crm.cc.domain.dto.CrmCcUserCmnDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcUserCmn;

/**
 * ユーザーロジックです。
 */
public interface UserLogic {

    /**
     * 
     * 担当者の取得です。
     * 
     * @param dto
     *            担当者の検索条件
     * @return {@link CrmCcUserCmn}
     */
    public CrmCcUserCmn getEntity(CrmCcUserCmnDto dto);

    /**
     * 
     * 担当者一覧の取得です。
     * 
     * @param entity
     *            担当者の検索条件
     * @return {@link CrmCcUserCmn}
     */
    public List<CrmCcUserCmn> getEntityList(CrmCcUserCmn entity);

}
