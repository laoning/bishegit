/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * 案件担当チェックDto
 */
public final class ProposalCheckChargeDto {

    /**
     * Listの担当者コードをOR条件
     */
    public String[] userCdList;

    /**
     * 案件コードをOR条件
     */
    public String[] proposalCdList;

    /**
     * 案件コードリスト
     */
    public List<String> crmProposalCdList = new ArrayList<String>();

    /**
     * ユーザコード
     */
    public String userCd;

    /**
     * 基準日
     */
    public Date baseDate;

    /**
     * 会社コード
     */
    public String companyCd;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
