/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.extension.solr.model;

import java.io.Serializable;

import com.biz_integral.service.maskat.event.listener.ContextLoadingEventListener;
import com.biz_integral.service.maskat.service.ContextLoadingServiceController;

/**
 * <p>
 * Maskat表示に利用するコンテキスト値を保持します。
 * </p>
 * 
 * <p>
 * 本クラスは{@link ContextLoadingServiceController}および
 * {@link ContextLoadingEventListener}にて値を補完されます。
 * </p>
 * 
 * @author btsatok
 * 
 */
public class ContextLoadingModel implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    // /**
    // * Maskatレイアウト表示ページパス
    // */
    // private String maskatPath = null;
    //
    // /**
    // * Maskatレイアウト表示フレーム名
    // */
    // private String maskatFrameName = null;
    //
    /**
     * アプリケーションID
     */
    private String applicationID;

    /**
     * モジュールID
     */
    private String moduleID;

    /**
     * ユースケースID
     */
    private String usecaseID;

    // /**
    // * レイアウトID
    // */
    // private String layoutID;
    //
    /**
     * 会社コード
     */
    private String companyCode;

    /**
     * ユーザID
     */
    private String userID;

    /**
     * ログイングループID
     */
    private String loginGroupID;

    /**
     * ユーザロケールID
     */
    private String localeID;

    // /**
    // * <p>
    // * 外部パラメータです。
    // * </p>
    // * <p>
    // * パラメータ名をキーに値を保持した{@code Map}です。
    // * </p>
    // */
    // private Map<String, String> externalParameters =
    // new HashMap<String, String>();

    // /**
    // * Maskatレイアウト表示ページパスを取得します。
    // *
    // * @return Maskatレイアウト表示ページパス
    // */
    // public String getMaskatPath() {
    // return maskatPath;
    // }
    //
    // /**
    // * Maskatレイアウト表示ページパスを設定します。
    // *
    // * @param maskatPath
    // * Maskatレイアウト表示ページパス
    // */
    // public void setMaskatPath(String maskatPath) {
    // this.maskatPath = maskatPath;
    // }
    //
    // /**
    // * Maskatレイアウト表示フレーム名を取得します。
    // *
    // * @return Maskatレイアウト表示フレーム名
    // */
    // public String getMaskatFrameName() {
    // return maskatFrameName;
    // }
    //
    // /**
    // * Maskatレイアウト表示フレーム名を設定します。
    // *
    // * @param maskatFrameName
    // * mMaskatレイアウト表示フレーム名
    // */
    // public void setMaskatFrameName(String maskatFrameName) {
    // this.maskatFrameName = maskatFrameName;
    // }

    /**
     * アプリケーションIDを取得します。
     * 
     * @return アプリケーションID
     */
    public String getApplicationID() {
        return applicationID;
    }

    /**
     * アプリケーションIDを設定します。
     * 
     * @param applicationID
     *            アプリケーションID
     */
    public void setApplicationID(String applicationID) {
        this.applicationID = applicationID;
    }

    /**
     * モジュールIDを取得します。
     * 
     * @return モジュールID
     */
    public String getModuleID() {
        return moduleID;
    }

    /**
     * モジュールIDを設定します。
     * 
     * @param moduleID
     *            モジュールID
     */
    public void setModuleID(String moduleID) {
        this.moduleID = moduleID;
    }

    /**
     * ユースケースIDを取得します。
     * 
     * @return ユースケースID
     */
    public String getUsecaseID() {
        return usecaseID;
    }

    /**
     * ユースケースIDを設定します。
     * 
     * @param usecaseID
     *            ユースケースID
     */
    public void setUsecaseID(String usecaseID) {
        this.usecaseID = usecaseID;
    }

    // /**
    // * レイアウトIDを取得します。
    // *
    // * @return レイアウトID
    // */
    // public String getLayoutID() {
    // return layoutID;
    // }
    //
    // /**
    // * レイアウトIDを設定します。
    // *
    // * @param layoutID
    // * レイアウトID
    // */
    // public void setLayoutID(String layoutID) {
    // this.layoutID = layoutID;
    // }
    //
    // /**
    // * 外部パラメータを保持した{@code Map}を取得します。
    // *
    // * @return 外部パラメータを保持した{@code Map}
    // */
    // public Map<String, String> getExternalParameters() {
    // return externalParameters;
    // }
    //
    // /**
    // * 外部パラメータを追加します。
    // *
    // * @param key
    // * パラメータ名
    // * @param value
    // * パラメータ値
    // */
    // public void addExternalParameters(String key, String value) {
    // this.externalParameters.put(key, value);
    // }

    /**
     * 会社コードを取得します。
     * 
     * @return the companyCode
     */
    public String getCompanyCode() {
        return companyCode;
    }

    /**
     * 会社コードを設定します。
     * 
     * @param companyCode
     *            companyCodeに設定するString
     */
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    /**
     * ユーザIDを取得します。
     * 
     * @return ユーザID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * ユーザIDを設定します。
     * 
     * @param userID
     *            ユーザID
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * ログイングループIDを取得します。
     * 
     * @return ログイングループID
     */
    public String getLoginGroupID() {
        return loginGroupID;
    }

    /**
     * ログイングループIDを設定します。
     * 
     * @param loginGroupID
     *            ログイングループID
     */
    public void setLoginGroupID(String loginGroupID) {
        this.loginGroupID = loginGroupID;
    }

    /**
     * ユーザロケールIDを取得します。
     * 
     * @return ユーザロケールID
     */
    public String getLocaleID() {
        return localeID;
    }

    /**
     * ユーザロケールIDを設定します。
     * 
     * @param localeID
     *            ユーザロケールID
     */
    public void setLocaleID(String localeID) {
        this.localeID = localeID;
    }

}
