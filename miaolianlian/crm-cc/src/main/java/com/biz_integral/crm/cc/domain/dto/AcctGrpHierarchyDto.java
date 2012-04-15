/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dto;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.biz_integral.crm.cc.domain.types.FileKind;

/**
 * アカウントグループ階層登録/更新画面用DTOです。
 */
public class AcctGrpHierarchyDto {

    /**
     * 追加先がTOPフラグ（最上位）
     */
    public String isTop = "0";

    /**
     * 追加先がルートフラグ
     */
    public String isRoot = "0";

    /** ローケルID */
    public String grpCd;

    /** 会社コード */
    public String companyCd;

    /** ローケルID */
    public String localeId;

    /** 親CRMアカウントグループコード */
    public String parentCrmAccountGroupCd;

    /** CRMアカウントグループコード */
    public String crmAccountGroupCd;

    /** パス */
    public String path;

    /** パス */
    public String targetPath;

    /** CRMアカウントグループ名 */
    public String crmAccountGroupName;

    /** 期間コード */
    public String termCd;

    /** 開始日 */
    public Date startDate;

    /** 終了日 */
    public Date endDate;

    /** 階層レベル */
    public Integer depth;

    /** ルートフラグ */
    public boolean rootFlag;

    /** 削除フラグ */
    public boolean deleteFlag;

    /** システム日付 */
    public Date sysDate;

    /**
     * ファイル種類
     */
    public FileKind fileKind;

    /**
     * ファイル名
     */
    public String fileName;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
