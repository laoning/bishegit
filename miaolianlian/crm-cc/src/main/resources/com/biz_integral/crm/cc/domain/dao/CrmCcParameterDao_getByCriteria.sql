SELECT
    a.parameter_cd,                           -- a.パラメータコード,
    a.parameter_value,                        -- a.パラメータ値,
    b.parameter_name,                         -- b.パラメータ名,
    b.notes,                                  -- b.備考
    a.term_cd,                                -- a.期間コード,
    a.parameter_data_type,                    -- a.パラメータ型区分
    a.parameter_digits,                       -- a.パラメータ桁数
    a.type_id,                                -- a.パラメータ桁数
    a.version_no                              -- b.バージョン番号
FROM
    crm_cc_parameter a,                       -- パラメータ a,   
    crm_cc_parameter_dtl b                    -- パラメータ明細 b
WHERE
        a.company_cd = /*companyCd*/'xxx'                      -- a.会社コード = 引数.会社コード
    AND UPPER(a.parameter_cd) = UPPER(/*parameterCd*/'xxx')    -- a.パラメータコード = 引数.パラメータコード
    AND a.term_cd = /*termCd*/'xxx'                            -- a.期間コード = 引数.期間コード
    AND a.start_date <= /*srchBaseDte*/''
    AND a.end_date > /*srchBaseDte*/''
    AND a.delete_flag = '0'
    AND a.company_cd = b.company_cd
    AND b.locale_id = /*localeId*/'xxx'
    AND a.parameter_cd = b.parameter_cd 