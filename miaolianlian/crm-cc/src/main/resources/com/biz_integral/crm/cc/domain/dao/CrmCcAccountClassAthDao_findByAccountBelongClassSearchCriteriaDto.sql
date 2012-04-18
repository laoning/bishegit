SELECT
    t4.crm_account_class_cd,
    t4.crm_account_class_name,
    t4.crm_account_cd,
    t5.crm_account_name
FROM 
(
    SELECT
        t2.company_cd,
        t2.crm_account_class_cd,
        t3.crm_account_cd,
        t2.locale_id,
        t2.crm_account_class_name
    FROM
        (
            SELECT
                t1.company_cd,
                t1.crm_account_class_cd,
                t1.locale_id,
                t1.crm_account_class_name
            FROM
                crm_cc_account_class t1
            WHERE
                t1.company_cd = /*companyCd*/'0001'
                /*IF crmAccountClassCd != null && crmAccountClassCd.length() > 0*/
                AND t1.crm_account_class_cd like /*crmAccountClassCd*/
                /*END*/
                /*IF crmAccountClassName != null && crmAccountClassName.length() > 0*/
                AND t1.crm_account_class_name like /*crmAccountClassName*/
                /*END*/
                /*IF localeId != null && localeId.length() > 0*/
                AND t1.locale_id = /*localeId*/'ja'
                /*END*/
                AND t1.delete_flag = '0'
        ) t2
    LEFT OUTER JOIN 
        crm_cc_account_class_ath t3
    ON 
        t2.company_cd = t3.company_cd
    AND t2.crm_account_class_cd = t3.crm_account_class_cd
    AND t3.delete_flag = '0'
    /*IF crmAccountCd != null && crmAccountCd.length() > 0*/
    WHERE t3.crm_account_cd like /*crmAccountCd*/
    /*END*/
) t4
LEFT OUTER JOIN
    crm_cc_account t5
ON
    t4.company_cd = t5.company_cd
AND t4.crm_account_cd = t5.crm_account_cd
AND t4.locale_id = t5.locale_id
AND t5.start_date <= /*systemDate*/'1900/01/01'
AND t5.end_date > /*systemDate*/'1900/01/01'
AND t5.delete_flag = '0'
/*IF crmAccountName != null && crmAccountName.length() > 0*/
WHERE t5.crm_account_name like /*crmAccountName*/
/*END*/
/*IF !countQuery*/
  /*IF order*/
ORDER BY 
    /*$orderByClause*/
  /*END*/
/*END*/
