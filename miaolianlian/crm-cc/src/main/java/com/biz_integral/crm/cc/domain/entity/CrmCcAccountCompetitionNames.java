/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.entity;

import java.util.Date;
import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link AbstractCrmCcAccountCompetition}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen unknown", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2010/09/02 12:34:06")
public class CrmCcAccountCompetitionNames {

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
     * competitionIdのプロパティ名を返します。
     * 
     * @return competitionIdのプロパティ名
     */
    public static PropertyName<String> competitionId() {
        return new PropertyName<String>("competitionId");
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
     * competitionNameのプロパティ名を返します。
     * 
     * @return competitionNameのプロパティ名
     */
    public static PropertyName<String> competitionName() {
        return new PropertyName<String>("competitionName");
    }

    /**
     * competitionNameKanaのプロパティ名を返します。
     * 
     * @return competitionNameKanaのプロパティ名
     */
    public static PropertyName<String> competitionNameKana() {
        return new PropertyName<String>("competitionNameKana");
    }

    /**
     * overviewのプロパティ名を返します。
     * 
     * @return overviewのプロパティ名
     */
    public static PropertyName<String> overview() {
        return new PropertyName<String>("overview");
    }

    /**
     * acquirementProposalCaseのプロパティ名を返します。
     * 
     * @return acquirementProposalCaseのプロパティ名
     */
    public static PropertyName<String> acquirementProposalCase() {
        return new PropertyName<String>("acquirementProposalCase");
    }

    /**
     * competitionLevelのプロパティ名を返します。
     * 
     * @return competitionLevelのプロパティ名
     */
    public static PropertyName<String> competitionLevel() {
        return new PropertyName<String>("competitionLevel");
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
    public static class _CrmCcAccountCompetitionNames extends PropertyName<AbstractCrmCcAccountCompetition> {

        /**
         * インスタンスを構築します。
         */
        public _CrmCcAccountCompetitionNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _CrmCcAccountCompetitionNames(final String name) {
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
        public _CrmCcAccountCompetitionNames(final PropertyName<?> parent, final String name) {
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
         * crmAccountCdのプロパティ名を返します。
         *
         * @return crmAccountCdのプロパティ名
         */
        public PropertyName<String> crmAccountCd() {
            return new PropertyName<String>(this, "crmAccountCd");
        }

        /**
         * competitionIdのプロパティ名を返します。
         *
         * @return competitionIdのプロパティ名
         */
        public PropertyName<String> competitionId() {
            return new PropertyName<String>(this, "competitionId");
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
         * competitionNameのプロパティ名を返します。
         *
         * @return competitionNameのプロパティ名
         */
        public PropertyName<String> competitionName() {
            return new PropertyName<String>(this, "competitionName");
        }

        /**
         * competitionNameKanaのプロパティ名を返します。
         *
         * @return competitionNameKanaのプロパティ名
         */
        public PropertyName<String> competitionNameKana() {
            return new PropertyName<String>(this, "competitionNameKana");
        }

        /**
         * overviewのプロパティ名を返します。
         *
         * @return overviewのプロパティ名
         */
        public PropertyName<String> overview() {
            return new PropertyName<String>(this, "overview");
        }

        /**
         * acquirementProposalCaseのプロパティ名を返します。
         *
         * @return acquirementProposalCaseのプロパティ名
         */
        public PropertyName<String> acquirementProposalCase() {
            return new PropertyName<String>(this, "acquirementProposalCase");
        }

        /**
         * competitionLevelのプロパティ名を返します。
         *
         * @return competitionLevelのプロパティ名
         */
        public PropertyName<String> competitionLevel() {
            return new PropertyName<String>(this, "competitionLevel");
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