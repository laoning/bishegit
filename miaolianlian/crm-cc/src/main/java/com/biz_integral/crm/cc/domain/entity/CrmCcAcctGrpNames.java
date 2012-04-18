/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.entity;

import java.util.Date;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link AbstractCrmCcAcctGrp}のプロパティ名の集合です。
 * 
 */
public class CrmCcAcctGrpNames {

    /**
     * companyCdのプロパティ名を返します。
     * 
     * @return companyCdのプロパティ名
     */
    public static PropertyName<String> companyCd() {
        return new PropertyName<String>("companyCd");
    }

    /**
     * crmAccountGroupCdのプロパティ名を返します。
     * 
     * @return crmAccountGroupCdのプロパティ名
     */
    public static PropertyName<String> crmAccountGroupCd() {
        return new PropertyName<String>("crmAccountGroupCd");
    }

    /**
     * localeIdのプロパティ名を返します。
     * 
     * @return localeIdのプロパティ名
     */
    public static PropertyName<String> localeId() {
        return new PropertyName<String>("localeId");
    }

    /**
     * termCdのプロパティ名を返します。
     * 
     * @return termCdのプロパティ名
     */
    public static PropertyName<String> termCd() {
        return new PropertyName<String>("termCd");
    }

    /**
     * startDateのプロパティ名を返します。
     * 
     * @return startDateのプロパティ名
     */
    public static PropertyName<Date> startDate() {
        return new PropertyName<Date>("startDate");
    }

    /**
     * endDateのプロパティ名を返します。
     * 
     * @return endDateのプロパティ名
     */
    public static PropertyName<Date> endDate() {
        return new PropertyName<Date>("endDate");
    }

    /**
     * crmAccountGroupNameのプロパティ名を返します。
     * 
     * @return crmAccountGroupNameのプロパティ名
     */
    public static PropertyName<String> crmAccountGroupName() {
        return new PropertyName<String>("crmAccountGroupName");
    }

    /**
     * crmAccountGroupShortNameのプロパティ名を返します。
     * 
     * @return crmAccountGroupShortNameのプロパティ名
     */
    public static PropertyName<String> crmAccountGroupShortName() {
        return new PropertyName<String>("crmAccountGroupShortName");
    }

    /**
     * crmAccountGroupSearchNameのプロパティ名を返します。
     * 
     * @return crmAccountGroupSearchNameのプロパティ名
     */
    public static PropertyName<String> crmAccountGroupSearchName() {
        return new PropertyName<String>("crmAccountGroupSearchName");
    }

    /**
     * notesのプロパティ名を返します。
     * 
     * @return notesのプロパティ名
     */
    public static PropertyName<String> notes() {
        return new PropertyName<String>("notes");
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
    public static class _CrmCcAcctGrpNames extends PropertyName<AbstractCrmCcAcctGrp> {

        /**
         * インスタンスを構築します。
         */
        public _CrmCcAcctGrpNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _CrmCcAcctGrpNames(final String name) {
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
        public _CrmCcAcctGrpNames(final PropertyName<?> parent, final String name) {
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
         * crmAccountGroupCdのプロパティ名を返します。
         *
         * @return crmAccountGroupCdのプロパティ名
         */
        public PropertyName<String> crmAccountGroupCd() {
            return new PropertyName<String>(this, "crmAccountGroupCd");
        }

        /**
         * localeIdのプロパティ名を返します。
         *
         * @return localeIdのプロパティ名
         */
        public PropertyName<String> localeId() {
            return new PropertyName<String>(this, "localeId");
        }

        /**
         * termCdのプロパティ名を返します。
         *
         * @return termCdのプロパティ名
         */
        public PropertyName<String> termCd() {
            return new PropertyName<String>(this, "termCd");
        }

        /**
         * startDateのプロパティ名を返します。
         *
         * @return startDateのプロパティ名
         */
        public PropertyName<Date> startDate() {
            return new PropertyName<Date>(this, "startDate");
        }

        /**
         * endDateのプロパティ名を返します。
         *
         * @return endDateのプロパティ名
         */
        public PropertyName<Date> endDate() {
            return new PropertyName<Date>(this, "endDate");
        }

        /**
         * crmAccountGroupNameのプロパティ名を返します。
         *
         * @return crmAccountGroupNameのプロパティ名
         */
        public PropertyName<String> crmAccountGroupName() {
            return new PropertyName<String>(this, "crmAccountGroupName");
        }

        /**
         * crmAccountGroupShortNameのプロパティ名を返します。
         *
         * @return crmAccountGroupShortNameのプロパティ名
         */
        public PropertyName<String> crmAccountGroupShortName() {
            return new PropertyName<String>(this, "crmAccountGroupShortName");
        }

        /**
         * crmAccountGroupSearchNameのプロパティ名を返します。
         *
         * @return crmAccountGroupSearchNameのプロパティ名
         */
        public PropertyName<String> crmAccountGroupSearchName() {
            return new PropertyName<String>(this, "crmAccountGroupSearchName");
        }

        /**
         * notesのプロパティ名を返します。
         *
         * @return notesのプロパティ名
         */
        public PropertyName<String> notes() {
            return new PropertyName<String>(this, "notes");
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