/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.util;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import jp.co.intra_mart.framework.system.exception.SystemException;

/**
 * Javaクラス生成共通関数
 */
public class ClassMakerUtil {

    /**
     * コンストラクタ
     */
    private ClassMakerUtil() {
        // 何もしない
    }

    /**
	 * Javaクラス生成します。
	 * 
	 * @param className
	 *            Javaクラス名
	 * @param arraySize
	 *            配列サイズ
	 * @return Javaクラス
	 * @throws SystemException
	 *             　システム例外が発生した
	 */
    public static Object[] createArrayClassInstance(String className,
            int arraySize) throws SystemException {
        try {
            return (Object[]) Array.newInstance(
                Class.forName(className),
                arraySize);
        } catch (NegativeArraySizeException e) {
            throw new SystemException(e.getMessage(), e);
        } catch (ClassNotFoundException e) {
            throw new SystemException(e.getMessage(), e);
        }
    }

    /**
	 * Javaクラス生成します。
	 * 
	 * @param className
	 *            Javaクラス
	 * @param obj
	 *            実行メソッド
	 * 
	 * @return 生成したJavaクラス
	 * @throws SystemException
	 *             システム例外が発生した
	 */
    public static Object createClassInstanceForDate(String className, Object obj)
        throws SystemException {
        Class<?> clazz;
        try {
            clazz = Class.forName(className);
            Class<?>[] types = { Date.class };
            Constructor<?> constructor;
            constructor = clazz.getConstructor(types);
            return constructor.newInstance(obj);

        } catch (ClassNotFoundException e) {
            throw new SystemException(e.getMessage(), e);
        } catch (SecurityException e) {
            throw new SystemException(e.getMessage(), e);
        } catch (NoSuchMethodException e) {
            throw new SystemException(e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            throw new SystemException(e.getMessage(), e);
        } catch (InstantiationException e) {
            throw new SystemException(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new SystemException(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            throw new SystemException(e.getMessage(), e);
        }
    }

    /**
	 * Javaクラス生成します。
	 * 
	 * @param className
	 *            Javaクラス
	 * @param obj
	 *            実行メソッド
	 * @return Javaクラス
	 * @throws SystemException
	 *             システム例外が発生した
	 */
    public static Object createClassInstance(String className, Object... obj)
        throws SystemException {
        Class<?> clazz;
        try {
            clazz = Class.forName(className);
            Class<?>[] types = { String.class };
            Constructor<?> constructor;
            constructor = clazz.getConstructor(types);
            return constructor.newInstance(obj);

        } catch (ClassNotFoundException e) {
            throw new SystemException(e.getMessage(), e);
        } catch (SecurityException e) {
            throw new SystemException(e.getMessage(), e);
        } catch (NoSuchMethodException e) {
            throw new SystemException(e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            throw new SystemException(e.getMessage(), e);
        } catch (InstantiationException e) {
            throw new SystemException(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new SystemException(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            throw new SystemException(e.getMessage(), e);
        }
    }

    /**
     * Javaクラス生成します。
     * 
     * @param className
     *            Javaクラス
	 * @return Javaクラス
     * @throws SystemException
     *             システム例外が発生した
     */
    public static Object createClassInstance(String className)
        throws SystemException {
        Class<?> clazz;
        try {
            clazz = Class.forName(className);
            return clazz.newInstance();
        } catch (ClassNotFoundException e) {
            throw new SystemException(e.getMessage(), e);
        } catch (SecurityException e) {
            throw new SystemException(e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            throw new SystemException(e.getMessage(), e);
        } catch (InstantiationException e) {
            throw new SystemException(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new SystemException(e.getMessage(), e);
        }
    }

    /**
     * メソッド実行 取得したメソッドを実行します。
     * 
     * @param obj
     *            実行メソッド
     * @param methodName
     *            メソッド名
     * @return 実行メソッドの戻り値
     * @throws SystemException
     *             システム例外が発生した
     */
    public static Object executeMethod(Object obj, String methodName)
        throws SystemException {
        // 実行メソッドを取得する
        Method method = getMethod(obj, methodName, new Object[] {});

        try {
            return method.invoke(obj, new Object[] {});
        } catch (IllegalArgumentException e) {
            throw new SystemException(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new SystemException(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            throw new SystemException(e.getMessage(), e);
        }
    }

    /**
     * メソッド実行 取得したメソッドを実行します。
     * 
     * @param obj
     *            実行メソッド
     * @param methodName
     *            メソッド名
     * @param params
     *            パラメータ
     * @return 実行メソッドの戻り値
     * @throws SystemException
     *             システム例外が発生した
     */
    public static Object executeMethod(Object obj, String methodName,
            Object... params) throws SystemException {
        // 実行メソッドを取得する
        if (params != null) {

            for (Object object : params) {
                if (object == null) {
                    return null;
                }
            }
            Method method = getMethod(obj, methodName, params);

            try {
                return method.invoke(obj, params);
            } catch (IllegalArgumentException e) {
                throw new SystemException(e.getMessage(), e);
            } catch (IllegalAccessException e) {
                throw new SystemException(e.getMessage(), e);
            } catch (InvocationTargetException e) {
                throw new SystemException(e.getMessage(), e);
            }
        } else {
            return null;
        }
    }

    /**
     * メソッド実行 取得したメソッドを実行します。
     * 
     * @param obj
     *            実行メソッド
     * @param method
     *            メソッド
     * @param param
     *            パラメータ
     * @return 実行メソッドの戻り値
     * @throws SystemException
     *             システム例外が発生した
     */
    public static Object invoke(Object obj, Method method, Object param)
        throws SystemException {
        try {
            return method.invoke(obj, param);
        } catch (IllegalArgumentException e) {
            throw new SystemException(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new SystemException(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            throw new SystemException(e.getMessage(), e);
        }
    }

    /**
     * メソッド取得 実行メソッドを取得します。
     * 
     * @param obj
     *            実行メソッド
     * @param methodName
     *            メソッド名
     * @param params
     *            パラメータ
     * @return 実行メソッド
     * @throws SecurityException
     */
    public static Method getMethod(Object obj, String methodName,
            Object[] params) {
        Method[] methList = obj.getClass().getMethods();
        Method ret = null;

        for (int i = 0; i < methList.length; i++) {
            if (methList[i].getName().equals(methodName)) {

                Class<?>[] c = methList[i].getParameterTypes();

                if (c.length != params.length) {
                    continue;
                }
                boolean isnew = true;
                for (int j = 0; j < c.length; j++) {
                    if (params[j] != null && !c[j].equals(params.getClass())) {
                        isnew = false;
                        continue;
                    }
                }
                if (ret == null || (ret != null && isnew)) {
                    ret = methList[i];
                }
            }
        }
        return ret;
    }
}
