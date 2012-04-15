SELECT 
    /*IF company == true*/
    company_cd,
    /*END*/
    /*IF locale == true*/
    locale_id,
    /*END*/
    name

FROM
    crm_cc_synonym_pattern

GROUP BY 
    /*IF company == true*/
    company_cd,
    /*END*/
    /*IF locale == true*/
    locale_id,
    /*END*/
    name

ORDER BY 
    /*IF company == true*/
    company_cd,
    /*END*/
    /*IF locale == true*/
    locale_id,
    /*END*/
    name
