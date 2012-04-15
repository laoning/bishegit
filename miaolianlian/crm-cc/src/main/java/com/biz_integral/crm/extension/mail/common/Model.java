/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.extension.mail.common;

import java.io.Serializable;
import java.util.Date;

/**
 * CRMモデル共通インタフェース
 * 
 */
public interface Model extends Serializable {

    /**
     * 登録ユーザコードを取得します。
     * 
     * @return 登録ユーザコード
     */
    public String getRegistUserCd();

    /**
     * 登録ユーザコードをセットします。
     * 
     * @param userCd
     *            登録ユーザコード
     */
    public void setRegistUserCd(String userCd);

    /**
     * 登録ユーザ名を取得します。
     * 
     * @return 登録ユーザ名
     */
    public String getRegistUserName();

    /**
     * 登録ユーザ名をセットします。
     * 
     * @param userName
     *            登録ユーザ名
     */
    public void setRegistUserName(String userName);

    /**
     * 登録日を取得します。
     * 
     * @return 登録日
     */
    public Date getRegistDate();

    /**
     * 登録日をセットします。
     * 
     * @param registDate
     *            登録日
     */
    public void setRegistDate(Date registDate);

    /**
     * 更新ユーザコードを取得します。
     * 
     * @return 更新ユーザコード
     */
    public String getUpdateUserCd();

    /**
     * 更新ユーザコードをセットします。
     * 
     * @param userCd
     *            更新ユーザコード
     */
    public void setUpdateUserCd(String userCd);

    /**
     * 更新ユーザ名を取得します。
     * 
     * @return 更新ユーザ名
     */
    public String getUpdateUserName();

    /**
     * 更新ユーザ名をセットします。
     * 
     * @param userName
     *            更新ユーザ名
     */
    public void setUpdateUserName(String userName);

    /**
     * 更新日を取得します。
     * 
     * @return 更新日
     */
    public Date getUpdateDate();

    /**
     * 更新日をセットします。
     * 
     * @param updateDate
     *            更新日
     */
    public void setUpdateDate(Date updateDate);

    /**
     * 登録ユーザコードを取得します。
     * 
     * @return 登録ユーザコード
     */
    public String getRecordUserCd();

    /**
     * 登録ユーザコードをセットします。
     * 
     * @param recordUserCd
     *            登録ユーザコード
     */
    public void setRecordUserCd(String recordUserCd);
}
