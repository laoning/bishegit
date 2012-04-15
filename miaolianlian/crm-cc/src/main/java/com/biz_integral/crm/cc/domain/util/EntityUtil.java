/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.biz_integral.crm.sa.domain.dto.TypeConversionItemDefineDto;
import com.biz_integral.foundation.core.util.StringUtil;

/**
 * 共通関数（エンティティ）クラス（EntityUtil）.<br>
 * エンティティに関する共通処理を行う。
 */
public class EntityUtil<T> {

    /**
     * 値設定.<br>
     * エンティティに型変換項目定義エンティティの値を設定する。<br>
     * 
     * @param entity
     *            エンティティ
     * @param typeConvItemDefList
     *            型変換項目定義モデルリスト
     * @throws InvocationTargetException
     *             , IllegalAccessException, NoSuchMethodException
     */
    public void setEntityItems(T entity,
            List<TypeConversionItemDefineDto> typeConvItemDefList)
        throws InvocationTargetException, IllegalAccessException,
        NoSuchMethodException {

        String cmlTableName = "";
        String cmlItemName = "";

        for (TypeConversionItemDefineDto item : typeConvItemDefList) {

            cmlTableName = StringUtil.camelize(item.equivalentTable);

            if (!cmlTableName.equalsIgnoreCase(entity
                .getClass()
                .getSimpleName())) {
                continue;
            }

            cmlItemName = StringUtil.camelize(item.equivalentItem);

            for (Field field : entity
                .getClass()
                .getSuperclass()
                .getDeclaredFields()) {

                if (cmlItemName.equalsIgnoreCase(field.getName())) {

                    String fieldName =
                        Character.toUpperCase(field.getName().charAt(0))
                            + field.getName().substring(1);

                    String methodName = "get" + fieldName;

                    Class[] types = new Class[] {};

                    Method getter =
                        entity.getClass().getMethod(methodName, types);

                    Object result = getter.invoke(entity, new Object[0]);

                    Method setter = null;
                    methodName = "set" + fieldName;

                    types = new Class[] { field.getType() };
                    setter = entity.getClass().getMethod(methodName, types);

                    if (field.getType() == String.class) {

                        setter.invoke(entity, item.importValue);

                    } else if (field.getType() == Date.class) {

                        setter.invoke(
                            entity,
                            com.biz_integral.foundation.core.util.DateUtil
                                .parse(item.importValue, item.conversionForm));

                    } else if (field.getType() == Integer.class) {

                        setter
                            .invoke(entity, Integer.valueOf(item.importValue));

                    } else if (field.getType() == Long.class) {

                        setter.invoke(entity, Long.valueOf(item.importValue));

                    } else if (field.getType() == BigDecimal.class) {

                        setter.invoke(entity, new BigDecimal(item.importValue));

                    } else if (field.getType() == boolean.class) {

                        setter.invoke(entity, Boolean
                            .getBoolean(item.importValue));

                    }
                    break;
                }
            }
        }
    }

}