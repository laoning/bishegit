/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.entity;

import java.util.Date;
import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link AbstractBizSkcs}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen unknown", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2010/08/18 15:30:59")
public class BizSkcsNames {

    /**
     * tdfkCdのプロパティ名を返します。
     * 
     * @return tdfkCdのプロパティ名
     */
    public static PropertyName<String> tdfkCd() {
        return new PropertyName<String>("tdfkCd");
    }

    /**
     * skcsCdのプロパティ名を返します。
     * 
     * @return skcsCdのプロパティ名
     */
    public static PropertyName<String> skcsCd() {
        return new PropertyName<String>("skcsCd");
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
     * majorCityFlagのプロパティ名を返します。
     * 
     * @return majorCityFlagのプロパティ名
     */
    public static PropertyName<Boolean> majorCityFlag() {
        return new PropertyName<Boolean>("majorCityFlag");
    }

    /**
     * tdfkAddressのプロパティ名を返します。
     * 
     * @return tdfkAddressのプロパティ名
     */
    public static PropertyName<String> tdfkAddress() {
        return new PropertyName<String>("tdfkAddress");
    }

    /**
     * tdfkAddressKanaのプロパティ名を返します。
     * 
     * @return tdfkAddressKanaのプロパティ名
     */
    public static PropertyName<String> tdfkAddressKana() {
        return new PropertyName<String>("tdfkAddressKana");
    }

    /**
     * skcsAddressのプロパティ名を返します。
     * 
     * @return skcsAddressのプロパティ名
     */
    public static PropertyName<String> skcsAddress() {
        return new PropertyName<String>("skcsAddress");
    }

    /**
     * skcsAddressKanaのプロパティ名を返します。
     * 
     * @return skcsAddressKanaのプロパティ名
     */
    public static PropertyName<String> skcsAddressKana() {
        return new PropertyName<String>("skcsAddressKana");
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
    public static class _BizSkcsNames extends PropertyName<AbstractBizSkcs> {

        /**
         * インスタンスを構築します。
         */
        public _BizSkcsNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _BizSkcsNames(final String name) {
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
        public _BizSkcsNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

        /**
         * tdfkCdのプロパティ名を返します。
         *
         * @return tdfkCdのプロパティ名
         */
        public PropertyName<String> tdfkCd() {
            return new PropertyName<String>(this, "tdfkCd");
        }

        /**
         * skcsCdのプロパティ名を返します。
         *
         * @return skcsCdのプロパティ名
         */
        public PropertyName<String> skcsCd() {
            return new PropertyName<String>(this, "skcsCd");
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
         * majorCityFlagのプロパティ名を返します。
         *
         * @return majorCityFlagのプロパティ名
         */
        public PropertyName<Boolean> majorCityFlag() {
            return new PropertyName<Boolean>(this, "majorCityFlag");
        }

        /**
         * tdfkAddressのプロパティ名を返します。
         *
         * @return tdfkAddressのプロパティ名
         */
        public PropertyName<String> tdfkAddress() {
            return new PropertyName<String>(this, "tdfkAddress");
        }

        /**
         * tdfkAddressKanaのプロパティ名を返します。
         *
         * @return tdfkAddressKanaのプロパティ名
         */
        public PropertyName<String> tdfkAddressKana() {
            return new PropertyName<String>(this, "tdfkAddressKana");
        }

        /**
         * skcsAddressのプロパティ名を返します。
         *
         * @return skcsAddressのプロパティ名
         */
        public PropertyName<String> skcsAddress() {
            return new PropertyName<String>(this, "skcsAddress");
        }

        /**
         * skcsAddressKanaのプロパティ名を返します。
         *
         * @return skcsAddressKanaのプロパティ名
         */
        public PropertyName<String> skcsAddressKana() {
            return new PropertyName<String>(this, "skcsAddressKana");
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