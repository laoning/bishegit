/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dto;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.biz_integral.service.persistence.pager.PagerSupport;

/**
 * パラメータ管理モデルです。
 */
public class ParameterManageDto extends PagerSupport {

    /** 会社コード */
    private String companyCd;

    /** パラメータコード */
    private String parameterCd;

    /** 期間コード */
    private String termCd;

    /** モジュール分類 */
    private String moduleCategory;

    /** パラメータ分類 */
    private String parameterCategory;

    /** パラメータ値 */
    private String parameterValue;

    /** 削除フラグ */
    private boolean deleteFlag;

    /** バージョン番号 */
    private Long versionNo;

    /** ロケールID */
    private String localeId;

    /** パラメータ名 */
    private String parameterName;

    /** 備考 */
    private String notes;

    /** 検索基準日 */
    private Date srchBaseDte;

    /** ユーザコード */
    private String userCd;

    /**
     * 会社コードを取得します。
     * 
     * @return 会社コード
     */
    public String getCompanyCd() {
        return companyCd;
    }

    /**
     * 会社コードを設定します。
     * 
     * @param companyCd
     *            会社コード
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }

    /**
     * パラメータコードを取得します。
     * 
     * @return パラメータコード
     */
    public String getParameterCd() {
        return parameterCd;
    }

    /**
     * パラメータコードを設定します。
     * 
     * @param parameterCd
     *            パラメータコード
     */
    public void setParameterCd(String parameterCd) {
        this.parameterCd = parameterCd;
    }

    /**
     * 期間コードを取得します。
     * 
     * @return 期間コード
     */
    public String getTermCd() {
        return termCd;
    }

    /**
     * 期間コードを設定します。
     * 
     * @param termCd
     *            期間コード
     */
    public void setTermCd(String termCd) {
        this.termCd = termCd;
    }

    /**
     * モジュール分類を取得します。
     * 
     * @return モジュール分類
     */
    public String getModuleCategory() {
        return moduleCategory;
    }

    /**
     * モジュール分類を設定します。
     * 
     * @param moduleCategory
     *            モジュール分類
     */
    public void setModuleCategory(String moduleCategory) {
        this.moduleCategory = moduleCategory;
    }

    /**
     * パラメータ分類を取得します。
     * 
     * @return パラメータ分類
     */
    public String getParameterCategory() {
        return parameterCategory;
    }

    /**
     * パラメータ分類を設定します。
     * 
     * @param parameterCategory
     *            パラメータ分類
     */
    public void setParameterCategory(String parameterCategory) {
        this.parameterCategory = parameterCategory;
    }

    /**
     * パラメータ値を取得します。
     * 
     * @return パラメータ値
     */
    public String getParameterValue() {
        return parameterValue;
    }

    /**
     * パラメータ値を設定します。
     * 
     * @param parameterValue
     *            パラメータ値
     */
    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
    }

    /**
     * 削除フラグを取得します。
     * 
     * @return 削除フラグ
     */
    public boolean getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * 削除フラグを設定します。
     * 
     * @param deleteFlag
     *            削除フラグ
     */
    public void setDeleteFlag(boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    /**
     * バージョン番号を取得します。
     * 
     * @return バージョン番号
     */
    public Long getVersionNo() {
        return versionNo;
    }

    /**
     * バージョン番号を設定します。
     * 
     * @param versionNo
     *            バージョン番号
     */
    public void setVersionNo(Long versionNo) {
        this.versionNo = versionNo;
    }

    /**
     * ロケールIDを取得します。
     * 
     * @return ロケールID
     */
    public String getLocaleId() {
        return localeId;
    }

    /**
     * ロケールIDを設定します。
     * 
     * @param localeId
     *            ロケールID
     */
    public void setLocaleId(String localeId) {
        this.localeId = localeId;
    }

    /**
     * パラメータ名を取得します。
     * 
     * @return パラメータ名
     */
    public String getParameterName() {
        return parameterName;
    }

    /**
     * パラメータ名を設定します。
     * 
     * @param parameterName
     *            パラメータ名
     */
    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    /**
     * 備考を取得します。
     * 
     * @return 備考
     */
    public String getNotes() {
        return notes;
    }

    /**
     * 備考を設定します。
     * 
     * @param notes
     *            備考
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * 検索基準日を設定します。
     * 
     * @param srchBaseDte
     *            検索基準日に設定するDate
     */
    public void setSrchBaseDte(Date srchBaseDte) {
        this.srchBaseDte = srchBaseDte;
    }

    /**
     * 検索基準日を取得します。
     * 
     * @return the srchBaseDte 検索基準日
     */
    public Date getSrchBaseDte() {
        return srchBaseDte;
    }

    /**
     * ユーザコードを設定します。
     * 
     * @param userCd
     *            ユーザコード
     */
    public void setUserCd(String userCd) {
        this.userCd = userCd;
    }

    /**
     * ユーザコードを取得します。
     * 
     * 
     * @return ユーザコード
     */
    public String getUserCd() {
        return userCd;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}