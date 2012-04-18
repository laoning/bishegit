/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.entity;

import java.util.Date;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link AbstractCrmCcAccountGrpAttr}のプロパティ名の集合です。
 * 
 */
public class CrmCcAccountGrpAttrNames {

    /**
     * companyCdのプロパティ名を返します。
     * 
     * @return companyCdのプロパティ名
     */
    public static PropertyName<String> companyCd() {
        return new PropertyName<String>("companyCd");
    }

    /**
     * dealClassのプロパティ名を返します。
     * 
     * @return dealClassのプロパティ名
     */
    public static PropertyName<String> dealClass() {
        return new PropertyName<String>("dealClass");
    }

    /**
     * crmAccountClassCdのプロパティ名を返します。
     * 
     * @return crmAccountClassCdのプロパティ名
     */
    public static PropertyName<String> crmAccountClassCd() {
        return new PropertyName<String>("crmAccountClassCd");
    }

    /**
     * maintenanceTargetFlagのプロパティ名を返します。
     * 
     * @return maintenanceTargetFlagのプロパティ名
     */
    public static PropertyName<Boolean> maintenanceTargetFlag() {
        return new PropertyName<Boolean>("maintenanceTargetFlag");
    }

    /**
     * ifUpdateWayのプロパティ名を返します。
     * 
     * @return ifUpdateWayのプロパティ名
     */
    public static PropertyName<String> ifUpdateWay() {
        return new PropertyName<String>("ifUpdateWay");
    }

    /**
     * deleteFlagのプロパティ名を返します。
     * 
     * @return deleteFlagのプロパティ名
     */
    public static PropertyName<Boolean> deleteFlag() {
        return new PropertyName<Boolean>("deleteFlag");
    }

    /**
     * sortKeyのプロパティ名を返します。
     * 
     * @return sortKeyのプロパティ名
     */
    public static PropertyName<Long> sortKey() {
        return new PropertyName<Long>("sortKey");
    }

    /**
     * versionNoのプロパティ名を返します。
     * 
     * @return versionNoのプロパティ名
     */
    public static PropertyName<Long> versionNo() {
        return new PropertyName<Long>("versionNo");
    }

    /**
     * createUserCdのプロパティ名を返します。
     * 
     * @return createUserCdのプロパティ名
     */
    public static PropertyName<String> createUserCd() {
        return new PropertyName<String>("createUserCd");
    }

    /**
     * createDateのプロパティ名を返します。
     * 
     * @return createDateのプロパティ名
     */
    public static PropertyName<Date> createDate() {
        return new PropertyName<Date>("createDate");
    }

    /**
     * recordUserCdのプロパティ名を返します。
     * 
     * @return recordUserCdのプロパティ名
     */
    public static PropertyName<String> recordUserCd() {
        return new PropertyName<String>("recordUserCd");
    }

    /**
     * recordDateのプロパティ名を返します。
     * 
     * @return recordDateのプロパティ名
     */
    public static PropertyName<Date> recordDate() {
        return new PropertyName<Date>("recordDate");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _CrmCcAccountGrpAttrNames extends PropertyName<AbstractCrmCcAccountGrpAttr> {

        /**
         * インスタンスを構築します。
         */
        public _CrmCcAccountGrpAttrNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _CrmCcAccountGrpAttrNames(final String name) {
            super(name);
        }

        /**
         * インスタンスを構築します。
         * 
         * @param parent
         *            親
         * @param name
         *            名前
         */
        public _CrmCcAccountGrpAttrNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

        /**
         * companyCdのプロパティ名を返します。
         *
         * @return companyCdのプロパティ名
         */
        public PropertyName<String> companyCd() {
            return new PropertyName<String>(this, "companyCd");
        }

        /**
         * dealClassのプロパティ名を返します。
         *
         * @return dealClassのプロパティ名
         */
        public PropertyName<String> dealClass() {
            return new PropertyName<String>(this, "dealClass");
        }

        /**
         * crmAccountClassCdのプロパティ名を返します。
         *
         * @return crmAccountClassCdのプロパティ名
         */
        public PropertyName<String> crmAccountClassCd() {
            return new PropertyName<String>(this, "crmAccountClassCd");
        }

        /**
         * maintenanceTargetFlagのプロパティ名を返します。
         *
         * @return maintenanceTargetFlagのプロパティ名
         */
        public PropertyName<Boolean> maintenanceTargetFlag() {
            return new PropertyName<Boolean>(this, "maintenanceTargetFlag");
        }

        /**
         * ifUpdateWayのプロパティ名を返します。
         *
         * @return ifUpdateWayのプロパティ名
         */
        public PropertyName<String> ifUpdateWay() {
            return new PropertyName<String>(this, "ifUpdateWay");
        }

        /**
         * deleteFlagのプロパティ名を返します。
         *
         * @return deleteFlagのプロパティ名
         */
        public PropertyName<Boolean> deleteFlag() {
            return new PropertyName<Boolean>(this, "deleteFlag");
        }

        /**
         * sortKeyのプロパティ名を返します。
         *
         * @return sortKeyのプロパティ名
         */
        public PropertyName<Long> sortKey() {
            return new PropertyName<Long>(this, "sortKey");
        }

        /**
         * versionNoのプロパティ名を返します。
         *
         * @return versionNoのプロパティ名
         */
        public PropertyName<Long> versionNo() {
            return new PropertyName<Long>(this, "versionNo");
        }

        /**
         * createUserCdのプロパティ名を返します。
         *
         * @return createUserCdのプロパティ名
         */
        public PropertyName<String> createUserCd() {
            return new PropertyName<String>(this, "createUserCd");
        }

        /**
         * createDateのプロパティ名を返します。
         *
         * @return createDateのプロパティ名
         */
        public PropertyName<Date> createDate() {
            return new PropertyName<Date>(this, "createDate");
        }

        /**
         * recordUserCdのプロパティ名を返します。
         *
         * @return recordUserCdのプロパティ名
         */
        public PropertyName<String> recordUserCd() {
            return new PropertyName<String>(this, "recordUserCd");
        }

        /**
         * recordDateのプロパティ名を返します。
         *
         * @return recordDateのプロパティ名
         */
        public PropertyName<Date> recordDate() {
            return new PropertyName<Date>(this, "recordDate");
        }
    }
}