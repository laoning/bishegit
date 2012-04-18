SELECT
    a.parameter_cd, 
    a.parameter_value, 
    b.parameter_name, 
    b.notes,
    a.term_cd,
    a.version_no,
    a.module_category
FROM
    crm_cc_parameter a, 
    crm_cc_parameter_dtl b
WHERE
    a.company_cd = /*companyCd*/'xxx'
    /*IF moduleCategory != null*/
    AND UPPER(a.module_category) = UPPER(/*moduleCategory*/'xxx')
    /*END*/
    /*IF parameterName != null*/
    AND b.parameter_name LIKE /*parameterName*/
    /*END*/
    AND a.parameter_category = /*parameterCategory*/'0'
    AND a.start_date <= /*srchBaseDte*/''
    AND a.end_date > /*srchBaseDte*/''
    AND a.company_cd = b.company_cd
    AND a.parameter_cd = b.parameter_cd
    AND b.locale_id = /*localeId*/'xxx'
    AND a.delete_flag = '0'
/*IF !countQuery*/
ORDER BY
    /*IF order*/
    /*$orderByClause*/a.company_cd,
    /*END*/
    a.module_category,
    a.parameter_cd
/*END*/

