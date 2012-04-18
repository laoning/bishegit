/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.extension.mail.model;

import javax.mail.Part;

/**
 * パートデータ
 */
public class PartData {
    private String name;
    private Part data;

    /**
     * デフォルトコンストラクタ
     */
    public PartData() {
        super();
    }

    /**
     * パートデータを返却します
     * 
     * @return Part パートデータ
     */
    public Part getData() {
        return data;
    }

    /**
     * 名前を返却します
     * 
     * @return String 名前
     */
    public String getName() {
        return name;
    }

    /**
     * パートデータをセットします
     * 
     * @param data
     *            パートデータ
     */
    public void setData(Part data) {
        this.data = data;
    }

    /**
     * 名前をセットします
     * 
     * @param name
     *            名前
     */
    public void setName(String name) {
        this.name = name;
    }
}
