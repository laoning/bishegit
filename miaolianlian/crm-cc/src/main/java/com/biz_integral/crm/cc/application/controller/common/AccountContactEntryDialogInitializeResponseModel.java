/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * アカウントコンタクト登録/更新の初期表示responseモデルです。
 */
public final class AccountContactEntryDialogInitializeResponseModel {

    /**
     * 有効期間（自）
     */
    public Date effectiveTermFrom;

    /**
     * 有効期間（至）
     */
    public Date effectiveTermTo;

    /**
     * 備考
     */
    public String notes;

    /**
     * 予備1（テキスト）
     */
    public String customField1;

    /**
     * 予備1（数値）
     */
    public String customField2;

    /**
     * 予備1（日付）
     */
    public Date customField3;

    /**
     * 予備2（テキスト）
     */
    public String customField4;

    /**
     * 予備2（数値）
     */
    public String customField5;

    /**
     * 予備2（日付）
     */
    public Date customField6;

    /**
     * 予備3（テキスト）
     */
    public String customField7;

    /**
     * 予備3（数値）
     */
    public String customField8;

    /**
     * 予備3（日付）
     */
    public Date customField9;

    /**
     * 予備4（テキスト）
     */
    public String customField10;

    /**
     * 予備4（数値）
     */
    public String customField11;

    /**
     * 予備4（日付）
     */
    public Date customField12;

    /**
     * 予備5（テキスト）
     */
    public String customField13;

    /**
     * 予備5（数値）
     */
    public String customField14;

    /**
     * 予備5（日付）
     */
    public Date customField15;

    /**
     * 予備6（テキスト）
     */
    public String customField16;

    /**
     * 予備6（数値）
     */
    public String customField17;

    /**
     * 予備6（日付）
     */
    public Date customField18;

    /**
     * 予備7（テキスト）
     */
    public String customField19;

    /**
     * 予備7（数値）
     */
    public String customField20;

    /**
     * 予備7（日付）
     */
    public Date customField21;

    /**
     * 予備8（テキスト）
     */
    public String customField22;

    /**
     * 予備8（数値）
     */
    public String customField23;

    /**
     * 予備8（日付）
     */
    public Date customField24;

    /**
     * 予備9（テキスト）
     */
    public String customField25;

    /**
     * 予備9（数値）
     */
    public String customField26;

    /**
     * 予備9（日付）
     */
    public Date customField27;

    /**
     * 予備10（テキスト）
     */
    public String customField28;

    /**
     * 予備10（数値）
     */
    public String customField29;

    /**
     * 予備10（日付）
     */
    public Date customField30;

    /**
     * 予備11（テキスト）
     */
    public String customField31;

    /**
     * 予備11（数値）
     */
    public String customField32;

    /**
     * 予備11（日付）
     */
    public Date customField33;

    /**
     * 予備12（テキスト）
     */
    public String customField34;

    /**
     * 予備12（数値）
     */
    public String customField35;

    /**
     * 予備12（日付）
     */
    public Date customField36;

    /**
     * 予備13（テキスト）
     */
    public String customField37;

    /**
     * 予備13（数値）
     */
    public String customField38;

    /**
     * 予備13（日付）
     */
    public Date customField39;

    /**
     * 予備14（テキスト）
     */
    public String customField40;

    /**
     * 予備14（数値）
     */
    public String customField41;

    /**
     * 予備14（日付）
     */
    public Date customField42;

    /**
     * 予備15（テキスト）
     */
    public String customField43;

    /**
     * 予備15（数値）
     */
    public String customField44;

    /**
     * 予備15（日付）
     */
    public Date customField45;

    /**
     * 予備16（テキスト）
     */
    public String customField46;

    /**
     * 予備16（数値）
     */
    public String customField47;

    /**
     * 予備16（日付）
     */
    public Date customField48;

    /**
     * 予備17（テキスト）
     */
    public String customField49;

    /**
     * 予備17（数値）
     */
    public String customField50;

    /**
     * 予備17（日付）
     */
    public Date customField51;

    /**
     * 予備18（テキスト）
     */
    public String customField52;

    /**
     * 予備18（数値）
     */
    public String customField53;

    /**
     * 予備18（日付）
     */
    public Date customField54;

    /**
     * 予備19（テキスト）
     */
    public String customField55;

    /**
     * 予備19（数値）
     */
    public String customField56;

    /**
     * 予備19（日付）
     */
    public Date customField57;

    /**
     * 予備20（テキスト）
     */
    public String customField58;

    /**
     * 予備20（数値）
     */
    public String customField59;

    /**
     * 予備20（日付）
     */
    public Date customField60;

    /**
     * ソートキー
     */
    public String sortKey;

    /**
     * バージョン番号
     */
    public String versionNo;

    /**
     * 作成者
     */
    public String createUserCd;

    /**
     * 作成日
     */
    public String createDate;

    /**
     * CRMアカウントバージョン番号
     */
    public String crmAccountVersionNo;

    /**
     * CRMコンタクトバージョン番号
     */
    public String crmContactVersionNo;

    /**
     * アカウントコンタクト追加情報表示フラグ
     */
    public String accountContactAddInfoShowFlag;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
