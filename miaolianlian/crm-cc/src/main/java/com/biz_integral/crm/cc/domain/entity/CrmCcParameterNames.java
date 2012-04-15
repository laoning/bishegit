/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link AbstractCrmCcParameter}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen unknown", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2010/09/02 12:34:06")
public class CrmCcParameterNames {

    /**
     * companyCdのプロパティ名を返します。
     * 
     * @return companyCdのプロパティ名
     */
    public static PropertyName<String> companyCd() {
        return new PropertyName<String>("companyCd");
    }

    /**
     * parameterCdのプロパティ名を返します。
     * 
     * @return parameterCdのプロパティ名
     */
    public static PropertyName<String> parameterCd() {
        return new PropertyName<String>("parameterCd");
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
     * moduleCategoryのプロパティ名を返します。
     * 
     * @return moduleCategoryのプロパティ名
     */
    public static PropertyName<String> moduleCategory() {
        return new PropertyName<String>("moduleCategory");
    }

    /**
     * parameterCategoryのプロパティ名を返します。
     * 
     * @return parameterCategoryのプロパティ名
     */
    public static PropertyName<String> parameterCategory() {
        return new PropertyName<String>("parameterCategory");
    }

    /**
     * parameterValueのプロパティ名を返します。
     * 
     * @return parameterValueのプロパティ名
     */
    public static PropertyName<String> parameterValue() {
        return new PropertyName<String>("parameterValue");
    }

    /**
     * parameterDataTypeのプロパティ名を返します。
     * 
     * @return parameterDataTypeのプロパティ名
     */
    public static PropertyName<String> parameterDataType() {
        return new PropertyName<String>("parameterDataType");
    }

    /**
     * parameterDigitsのプロパティ名を返します。
     * 
     * @return parameterDigitsのプロパティ名
     */
    public static PropertyName<BigDecimal> parameterDigits() {
        return new PropertyName<BigDecimal>("parameterDigits");
    }

    /**
     * typeIdのプロパティ名を返します。
     * 
     * @return typeIdのプロパティ名
     */
    public static PropertyName<String> typeId() {
        return new PropertyName<String>("typeId");
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
    public static class _CrmCcParameterNames extends PropertyName<AbstractCrmCcParameter> {

        /**
         * インスタンスを構築します。
         */
        public _CrmCcParameterNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _CrmCcParameterNames(final String name) {
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
        public _CrmCcParameterNames(final PropertyName<?> parent, final String name) {
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
         * parameterCdのプロパティ名を返します。
         *
         * @return parameterCdのプロパティ名
         */
        public PropertyName<String> parameterCd() {
            return new PropertyName<String>(this, "parameterCd");
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
         * moduleCategoryのプロパティ名を返します。
         *
         * @return moduleCategoryのプロパティ名
         */
        public PropertyName<String> moduleCategory() {
            return new PropertyName<String>(this, "moduleCategory");
        }

        /**
         * parameterCategoryのプロパティ名を返します。
         *
         * @return parameterCategoryのプロパティ名
         */
        public PropertyName<String> parameterCategory() {
            return new PropertyName<String>(this, "parameterCategory");
        }

        /**
         * parameterValueのプロパティ名を返します。
         *
         * @return parameterValueのプロパティ名
         */
        public PropertyName<String> parameterValue() {
            return new PropertyName<String>(this, "parameterValue");
        }

        /**
         * parameterDataTypeのプロパティ名を返します。
         *
         * @return parameterDataTypeのプロパティ名
         */
        public PropertyName<String> parameterDataType() {
            return new PropertyName<String>(this, "parameterDataType");
        }

        /**
         * parameterDigitsのプロパティ名を返します。
         *
         * @return parameterDigitsのプロパティ名
         */
        public PropertyName<BigDecimal> parameterDigits() {
            return new PropertyName<BigDecimal>(this, "parameterDigits");
        }

        /**
         * typeIdのプロパティ名を返します。
         *
         * @return typeIdのプロパティ名
         */
        public PropertyName<String> typeId() {
            return new PropertyName<String>(this, "typeId");
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