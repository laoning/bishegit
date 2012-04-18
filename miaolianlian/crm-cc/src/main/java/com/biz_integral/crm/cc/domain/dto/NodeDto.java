/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dto;

/**
 * ツリー情報を保持するモデルです。<br/>
 */
public class NodeDto {

    // treeが必要としている項目
    protected String name;
    protected String parent;
    protected String label;
    protected String type;

    // 各マスタの項目
    private String parentCd;
    private String path;
    private String grpCd;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setParentCd(String parentCd) {
        this.parentCd = parentCd;
    }

    public String getParentCd() {
        return parentCd;
    }

    public void setGrpCd(String grpCd) {
        this.grpCd = grpCd;
    }

    public String getGrpCd() {
        return grpCd;
    }

}
