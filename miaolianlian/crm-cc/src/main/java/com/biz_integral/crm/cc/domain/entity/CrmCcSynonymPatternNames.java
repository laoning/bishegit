/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.entity;

import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link AbstractCrmCcSynonymPattern}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen unknown", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2010/10/25 16:14:47")
public class CrmCcSynonymPatternNames {

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
     * localeIdのプロパティ名を返します。
     * 
     * @return localeIdのプロパティ名
     */
    public static PropertyName<String> localeId() {
        return new PropertyName<String>("localeId");
    }

    /**
     * synonymTypeのプロパティ名を返します。
     * 
     * @return synonymTypeのプロパティ名
     */
    public static PropertyName<String> synonymType() {
        return new PropertyName<String>("synonymType");
    }

    /**
     * cdのプロパティ名を返します。
     * 
     * @return cdのプロパティ名
     */
    public static PropertyName<String> cd() {
        return new PropertyName<String>("cd");
    }

    /**
     * nameのプロパティ名を返します。
     * 
     * @return nameのプロパティ名
     */
    public static PropertyName<String> name() {
        return new PropertyName<String>("name");
    }

    /**
     * synonymNameのプロパティ名を返します。
     * 
     * @return synonymNameのプロパティ名
     */
    public static PropertyName<String> synonymName() {
        return new PropertyName<String>("synonymName");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _CrmCcSynonymPatternNames extends PropertyName<AbstractCrmCcSynonymPattern> {

        /**
         * インスタンスを構築します。
         */
        public _CrmCcSynonymPatternNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _CrmCcSynonymPatternNames(final String name) {
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
        public _CrmCcSynonymPatternNames(final PropertyName<?> parent, final String name) {
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
         * localeIdのプロパティ名を返します。
         *
         * @return localeIdのプロパティ名
         */
        public PropertyName<String> localeId() {
            return new PropertyName<String>(this, "localeId");
        }

        /**
         * synonymTypeのプロパティ名を返します。
         *
         * @return synonymTypeのプロパティ名
         */
        public PropertyName<String> synonymType() {
            return new PropertyName<String>(this, "synonymType");
        }

        /**
         * cdのプロパティ名を返します。
         *
         * @return cdのプロパティ名
         */
        public PropertyName<String> cd() {
            return new PropertyName<String>(this, "cd");
        }

        /**
         * nameのプロパティ名を返します。
         *
         * @return nameのプロパティ名
         */
        public PropertyName<String> name() {
            return new PropertyName<String>(this, "name");
        }

        /**
         * synonymNameのプロパティ名を返します。
         *
         * @return synonymNameのプロパティ名
         */
        public PropertyName<String> synonymName() {
            return new PropertyName<String>(this, "synonymName");
        }
    }
}