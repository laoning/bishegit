/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.entity;

import java.util.Date;
import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link AbstractCrmCcCustBankAcctCmn}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen unknown", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2010/09/02 12:34:06")
public class CrmCcCustBankAcctCmnNames {

    /**
     * companyCdのプロパティ名を返します。
     * 
     * @return companyCdのプロパティ名
     */
    public static PropertyName<String> companyCd() {
        return new PropertyName<String>("companyCd");
    }

    /**
     * customerCdのプロパティ名を返します。
     * 
     * @return customerCdのプロパティ名
     */
    public static PropertyName<String> customerCd() {
        return new PropertyName<String>("customerCd");
    }

    /**
     * customerBankAccountIdのプロパティ名を返します。
     * 
     * @return customerBankAccountIdのプロパティ名
     */
    public static PropertyName<Long> customerBankAccountId() {
        return new PropertyName<Long>("customerBankAccountId");
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
     * mainBankAccountTypeのプロパティ名を返します。
     * 
     * @return mainBankAccountTypeのプロパティ名
     */
    public static PropertyName<String> mainBankAccountType() {
        return new PropertyName<String>("mainBankAccountType");
    }

    /**
     * virtualBankAccountTypeのプロパティ名を返します。
     * 
     * @return virtualBankAccountTypeのプロパティ名
     */
    public static PropertyName<String> virtualBankAccountType() {
        return new PropertyName<String>("virtualBankAccountType");
    }

    /**
     * bankCdのプロパティ名を返します。
     * 
     * @return bankCdのプロパティ名
     */
    public static PropertyName<String> bankCd() {
        return new PropertyName<String>("bankCd");
    }

    /**
     * bankBranchCdのプロパティ名を返します。
     * 
     * @return bankBranchCdのプロパティ名
     */
    public static PropertyName<String> bankBranchCd() {
        return new PropertyName<String>("bankBranchCd");
    }

    /**
     * depositClassのプロパティ名を返します。
     * 
     * @return depositClassのプロパティ名
     */
    public static PropertyName<String> depositClass() {
        return new PropertyName<String>("depositClass");
    }

    /**
     * bankAccountNoのプロパティ名を返します。
     * 
     * @return bankAccountNoのプロパティ名
     */
    public static PropertyName<String> bankAccountNo() {
        return new PropertyName<String>("bankAccountNo");
    }

    /**
     * bankNomineeNameのプロパティ名を返します。
     * 
     * @return bankNomineeNameのプロパティ名
     */
    public static PropertyName<String> bankNomineeName() {
        return new PropertyName<String>("bankNomineeName");
    }

    /**
     * currencyCdのプロパティ名を返します。
     * 
     * @return currencyCdのプロパティ名
     */
    public static PropertyName<String> currencyCd() {
        return new PropertyName<String>("currencyCd");
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
    public static class _CrmCcCustBankAcctCmnNames extends PropertyName<AbstractCrmCcCustBankAcctCmn> {

        /**
         * インスタンスを構築します。
         */
        public _CrmCcCustBankAcctCmnNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _CrmCcCustBankAcctCmnNames(final String name) {
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
        public _CrmCcCustBankAcctCmnNames(final PropertyName<?> parent, final String name) {
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
         * customerCdのプロパティ名を返します。
         *
         * @return customerCdのプロパティ名
         */
        public PropertyName<String> customerCd() {
            return new PropertyName<String>(this, "customerCd");
        }

        /**
         * customerBankAccountIdのプロパティ名を返します。
         *
         * @return customerBankAccountIdのプロパティ名
         */
        public PropertyName<Long> customerBankAccountId() {
            return new PropertyName<Long>(this, "customerBankAccountId");
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
         * mainBankAccountTypeのプロパティ名を返します。
         *
         * @return mainBankAccountTypeのプロパティ名
         */
        public PropertyName<String> mainBankAccountType() {
            return new PropertyName<String>(this, "mainBankAccountType");
        }

        /**
         * virtualBankAccountTypeのプロパティ名を返します。
         *
         * @return virtualBankAccountTypeのプロパティ名
         */
        public PropertyName<String> virtualBankAccountType() {
            return new PropertyName<String>(this, "virtualBankAccountType");
        }

        /**
         * bankCdのプロパティ名を返します。
         *
         * @return bankCdのプロパティ名
         */
        public PropertyName<String> bankCd() {
            return new PropertyName<String>(this, "bankCd");
        }

        /**
         * bankBranchCdのプロパティ名を返します。
         *
         * @return bankBranchCdのプロパティ名
         */
        public PropertyName<String> bankBranchCd() {
            return new PropertyName<String>(this, "bankBranchCd");
        }

        /**
         * depositClassのプロパティ名を返します。
         *
         * @return depositClassのプロパティ名
         */
        public PropertyName<String> depositClass() {
            return new PropertyName<String>(this, "depositClass");
        }

        /**
         * bankAccountNoのプロパティ名を返します。
         *
         * @return bankAccountNoのプロパティ名
         */
        public PropertyName<String> bankAccountNo() {
            return new PropertyName<String>(this, "bankAccountNo");
        }

        /**
         * bankNomineeNameのプロパティ名を返します。
         *
         * @return bankNomineeNameのプロパティ名
         */
        public PropertyName<String> bankNomineeName() {
            return new PropertyName<String>(this, "bankNomineeName");
        }

        /**
         * currencyCdのプロパティ名を返します。
         *
         * @return currencyCdのプロパティ名
         */
        public PropertyName<String> currencyCd() {
            return new PropertyName<String>(this, "currencyCd");
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