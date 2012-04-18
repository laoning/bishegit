/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link AbstractCrmCcSalesResult}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen unknown", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2010/09/02 12:34:06")
public class CrmCcSalesResultNames {

    /**
     * idのプロパティ名を返します。
     * 
     * @return idのプロパティ名
     */
    public static PropertyName<String> id() {
        return new PropertyName<String>("id");
    }

    /**
     * companyCdのプロパティ名を返します。
     * 
     * @return companyCdのプロパティ名
     */
    public static PropertyName<String> companyCd() {
        return new PropertyName<String>("companyCd");
    }

    /**
     * crmAccountCdのプロパティ名を返します。
     * 
     * @return crmAccountCdのプロパティ名
     */
    public static PropertyName<String> crmAccountCd() {
        return new PropertyName<String>("crmAccountCd");
    }

    /**
     * proposalCdのプロパティ名を返します。
     * 
     * @return proposalCdのプロパティ名
     */
    public static PropertyName<String> proposalCd() {
        return new PropertyName<String>("proposalCd");
    }

    /**
     * itemCategoryCdのプロパティ名を返します。
     * 
     * @return itemCategoryCdのプロパティ名
     */
    public static PropertyName<String> itemCategoryCd() {
        return new PropertyName<String>("itemCategoryCd");
    }

    /**
     * salesResultDateのプロパティ名を返します。
     * 
     * @return salesResultDateのプロパティ名
     */
    public static PropertyName<Date> salesResultDate() {
        return new PropertyName<Date>("salesResultDate");
    }

    /**
     * salesResultAmtのプロパティ名を返します。
     * 
     * @return salesResultAmtのプロパティ名
     */
    public static PropertyName<BigDecimal> salesResultAmt() {
        return new PropertyName<BigDecimal>("salesResultAmt");
    }

    /**
     * userCdのプロパティ名を返します。
     * 
     * @return userCdのプロパティ名
     */
    public static PropertyName<String> userCd() {
        return new PropertyName<String>("userCd");
    }

    /**
     * crmDomainCdのプロパティ名を返します。
     * 
     * @return crmDomainCdのプロパティ名
     */
    public static PropertyName<String> crmDomainCd() {
        return new PropertyName<String>("crmDomainCd");
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
    public static class _CrmCcSalesResultNames extends PropertyName<AbstractCrmCcSalesResult> {

        /**
         * インスタンスを構築します。
         */
        public _CrmCcSalesResultNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _CrmCcSalesResultNames(final String name) {
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
        public _CrmCcSalesResultNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

        /**
         * idのプロパティ名を返します。
         *
         * @return idのプロパティ名
         */
        public PropertyName<String> id() {
            return new PropertyName<String>(this, "id");
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
         * crmAccountCdのプロパティ名を返します。
         *
         * @return crmAccountCdのプロパティ名
         */
        public PropertyName<String> crmAccountCd() {
            return new PropertyName<String>(this, "crmAccountCd");
        }

        /**
         * proposalCdのプロパティ名を返します。
         *
         * @return proposalCdのプロパティ名
         */
        public PropertyName<String> proposalCd() {
            return new PropertyName<String>(this, "proposalCd");
        }

        /**
         * itemCategoryCdのプロパティ名を返します。
         *
         * @return itemCategoryCdのプロパティ名
         */
        public PropertyName<String> itemCategoryCd() {
            return new PropertyName<String>(this, "itemCategoryCd");
        }

        /**
         * salesResultDateのプロパティ名を返します。
         *
         * @return salesResultDateのプロパティ名
         */
        public PropertyName<Date> salesResultDate() {
            return new PropertyName<Date>(this, "salesResultDate");
        }

        /**
         * salesResultAmtのプロパティ名を返します。
         *
         * @return salesResultAmtのプロパティ名
         */
        public PropertyName<BigDecimal> salesResultAmt() {
            return new PropertyName<BigDecimal>(this, "salesResultAmt");
        }

        /**
         * userCdのプロパティ名を返します。
         *
         * @return userCdのプロパティ名
         */
        public PropertyName<String> userCd() {
            return new PropertyName<String>(this, "userCd");
        }

        /**
         * crmDomainCdのプロパティ名を返します。
         *
         * @return crmDomainCdのプロパティ名
         */
        public PropertyName<String> crmDomainCd() {
            return new PropertyName<String>(this, "crmDomainCd");
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