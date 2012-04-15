/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.entity;

import java.util.Date;
import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link AbstractCrmCcItemCmn}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen unknown", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2010/09/02 12:34:06")
public class CrmCcItemCmnNames {

    /**
     * companyCdのプロパティ名を返します。
     * 
     * @return companyCdのプロパティ名
     */
    public static PropertyName<String> companyCd() {
        return new PropertyName<String>("companyCd");
    }

    /**
     * itemCdのプロパティ名を返します。
     * 
     * @return itemCdのプロパティ名
     */
    public static PropertyName<String> itemCd() {
        return new PropertyName<String>("itemCd");
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
     * itemNameのプロパティ名を返します。
     * 
     * @return itemNameのプロパティ名
     */
    public static PropertyName<String> itemName() {
        return new PropertyName<String>("itemName");
    }

    /**
     * itemShortNameのプロパティ名を返します。
     * 
     * @return itemShortNameのプロパティ名
     */
    public static PropertyName<String> itemShortName() {
        return new PropertyName<String>("itemShortName");
    }

    /**
     * itemSearchNameのプロパティ名を返します。
     * 
     * @return itemSearchNameのプロパティ名
     */
    public static PropertyName<String> itemSearchName() {
        return new PropertyName<String>("itemSearchName");
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
    public static class _CrmCcItemCmnNames extends PropertyName<AbstractCrmCcItemCmn> {

        /**
         * インスタンスを構築します。
         */
        public _CrmCcItemCmnNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _CrmCcItemCmnNames(final String name) {
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
        public _CrmCcItemCmnNames(final PropertyName<?> parent, final String name) {
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
         * itemCdのプロパティ名を返します。
         *
         * @return itemCdのプロパティ名
         */
        public PropertyName<String> itemCd() {
            return new PropertyName<String>(this, "itemCd");
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
         * itemNameのプロパティ名を返します。
         *
         * @return itemNameのプロパティ名
         */
        public PropertyName<String> itemName() {
            return new PropertyName<String>(this, "itemName");
        }

        /**
         * itemShortNameのプロパティ名を返します。
         *
         * @return itemShortNameのプロパティ名
         */
        public PropertyName<String> itemShortName() {
            return new PropertyName<String>(this, "itemShortName");
        }

        /**
         * itemSearchNameのプロパティ名を返します。
         *
         * @return itemSearchNameのプロパティ名
         */
        public PropertyName<String> itemSearchName() {
            return new PropertyName<String>(this, "itemSearchName");
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