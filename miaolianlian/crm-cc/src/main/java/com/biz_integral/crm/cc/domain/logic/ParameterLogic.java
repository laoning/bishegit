/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic;

/**
 * パラメータのロジックです。
 */
public interface ParameterLogic {
    /**
     * 
     * パラメータ値取得.
     * <p>
     * 次の条件で、パラメータからパラメータ値を適切な型変換をして取得します.
     * </p>
     * <ul>
     * <li>引数dtoの会社コードに合致する
     * <li>引数dtoのパラメータコードが合致する
     * <li>引数dtoの取得基準日が有効期間内である
     * </ul>
     * <p>
     * 削除フラグが未削除を取得対象とします。
     * </p>
     * <p>
     * 変換可能な型は、以下の通りです。
     * <ul>
     * <li>String</li>
     * <li>Integer</li>
     * <li>UseType</li>
     * </ul>
     * </li> </ul>
     * </p>
     * 
     * @param parameterCd
     *            パラメータコード
     * 
     * @return パラメータ値
     *         <p>
     *         型変換後の値をObjectで返すので、呼び出し元では適切にキャストする必要があります
     *         </p>
     *         <p>
     *         例：Integer param = (Integer)palameterLogic.getEntity("CRMSA0004");
     *         </p>
     * @throws IllegalArgumentException
     *             次の場合。
     *             <ul>
     *             <li>指定された型（クラス）が見つからない場合</li>
     *             </ul>
     */
    public Object getEntity(String parameterCd) throws IllegalArgumentException;

}
