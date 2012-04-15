/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.entity;

import java.util.Date;
import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link AbstractCrmCcCountryCmn}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen unknown", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2010/09/02 12:34:06")
public class CrmCcCountryCmnNames {

    /**
     * companyCdのプロパティ名を返します。
     * 
     * @return companyCdのプロパティ名
     */
    public static PropertyName<String> companyCd() {
        return new PropertyName<String>("companyCd");
    }

    /**
     * countryCdのプロパティ名を返します。
     * 
     * @return countryCdのプロパティ名
     */
    public static PropertyName<String> countryCd() {
        return new PropertyName<String>("countryCd");
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
     * countryNameのプロパティ名を返します。
     * 
     * @return countryNameのプロパティ名
     */
    public static PropertyName<String> countryName() {
        return new PropertyName<String>("countryName");
    }

    /**
     * countryShortNameのプロパティ名を返します。
     * 
     * @return countryShortNameのプロパティ名
     */
    public static PropertyName<String> countryShortName() {
        return new PropertyName<String>("countryShortName");
    }

    /**
     * countrySearchNameのプロパティ名を返します。
     * 
     * @return countrySearchNameのプロパティ名
     */
    public static PropertyName<String> countrySearchName() {
        return new PropertyName<String>("countrySearchName");
    }

    /**
     * countryAlpha2のプロパティ名を返します。
     * 
     * @return countryAlpha2のプロパティ名
     */
    public static PropertyName<String> countryAlpha2() {
        return new PropertyName<String>("countryAlpha2");
    }

    /**
     * countryAlpha3のプロパティ名を返します。
     * 
     * @return countryAlpha3のプロパティ名
     */
    public static PropertyName<String> countryAlpha3() {
        return new PropertyName<String>("countryAlpha3");
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
    public static class _CrmCcCountryCmnNames extends PropertyName<AbstractCrmCcCountryCmn> {

        /**
         * インスタンスを構築します。
         */
        public _CrmCcCountryCmnNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _CrmCcCountryCmnNames(final String name) {
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
        public _CrmCcCountryCmnNames(final PropertyName<?> parent, final String name) {
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
         * countryCdのプロパティ名を返します。
         *
         * @return countryCdのプロパティ名
         */
        public PropertyName<String> countryCd() {
            return new PropertyName<String>(this, "countryCd");
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
         * countryNameのプロパティ名を返します。
         *
         * @return countryNameのプロパティ名
         */
        public PropertyName<String> countryName() {
            return new PropertyName<String>(this, "countryName");
        }

        /**
         * countryShortNameのプロパティ名を返します。
         *
         * @return countryShortNameのプロパティ名
         */
        public PropertyName<String> countryShortName() {
            return new PropertyName<String>(this, "countryShortName");
        }

        /**
         * countrySearchNameのプロパティ名を返します。
         *
         * @return countrySearchNameのプロパティ名
         */
        public PropertyName<String> countrySearchName() {
            return new PropertyName<String>(this, "countrySearchName");
        }

        /**
         * countryAlpha2のプロパティ名を返します。
         *
         * @return countryAlpha2のプロパティ名
         */
        public PropertyName<String> countryAlpha2() {
            return new PropertyName<String>(this, "countryAlpha2");
        }

        /**
         * countryAlpha3のプロパティ名を返します。
         *
         * @return countryAlpha3のプロパティ名
         */
        public PropertyName<String> countryAlpha3() {
            return new PropertyName<String>(this, "countryAlpha3");
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