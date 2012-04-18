/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dto;

import javax.persistence.Column;
import javax.persistence.Id;

import com.biz_integral.crm.cc.domain.entity.AbstractCrmCcUserCmn;

/**
 * ユーザ設定ダイアログ一覧検索結果モデルです。
 * 
 */
public final class UserDialogReslutDto extends AbstractCrmCcUserCmn {

    /**
     * デフォルト・シリアルバージョンID
     */
    private static final long serialVersionUID = -5835466819951556869L;

    /** 組織コード */
    @Id
    @Column(name = "department_cd", columnDefinition = "varchar2(100)", nullable = false, unique = false)
    private String departmentCd;

    /** 組織名 */
    @Column(name = "department_name", columnDefinition = "varchar2(1000)", nullable = false, unique = false)
    private String departmentName;

    /**
     * 組織コードを取得します。
     * 
     * @return 組織コード
     */
    public String getDepartmentCd() {
        return departmentCd;
    }

    /**
     * 組織コードを設定します。
     * 
     * @param departmentCd
     *            組織コード
     */
    public void setDepartmentCd(String departmentCd) {
        this.departmentCd = departmentCd;
    }

    /**
     * 組織名を取得します。
     * 
     * @return 組織名
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * 組織名を設定します。
     * 
     * @param departmentName
     *            組織名
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
