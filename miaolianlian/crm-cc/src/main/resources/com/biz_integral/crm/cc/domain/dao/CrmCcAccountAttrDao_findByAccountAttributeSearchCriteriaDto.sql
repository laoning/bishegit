SELECT
    t1.crm_account_class_cd
    , t2.crm_account_class_name
    , t1.deal_class
    , t1.crm_account_status
    , t1.maintenance_target_flag
    , t1.if_update_way 
FROM
    crm_cc_account_attr t1 
    INNER JOIN crm_cc_account_class t2 
        ON t2.company_cd = /*companyCd*/'0001'
        AND t2.locale_id = /*localeId*/'ja' 
        AND t2.delete_flag = /*deleteFlag*/'0' 
        AND t1.company_cd = t2.company_cd 
        AND t1.crm_account_class_cd = t2.crm_account_class_cd 
WHERE
    t1.company_cd = /*companyCd*/'0001'
    AND t1.delete_flag = /*deleteFlag*/'0'
    /*IF crmAccountClassCd != null && crmAccountClassCd.length() > 0*/
    AND t1.crm_account_class_cd LIKE /*crmAccountClassCd*/'crmAccountClassCd'
    /*END*/
    /*IF crmAccountClassName != null && crmAccountClassName.length() > 0*/
    AND t2.crm_account_class_name LIKE /*crmAccountClassName*/'crmAccountClassName'
    /*END*/
    /*IF dealClass != null*/
    AND t1.deal_class = /*dealClass*/'1'
    /*END*/
    /*IF crmAccountStatus != null*/
    AND t1.crm_account_status = /*crmAccountStatus*/'1'
    /*END*/
    /*IF moduleId != null && moduleId.length() > 0*/
    AND exists( 
        SELECT
            * 
        FROM
            crm_cc_account_module t3 
        WHERE
            t3.company_cd = /*companyCd*/'0001'
            AND t3.delete_flag = /*deleteFlag*/'0' 
            AND t3.module_id = /*moduleId*/'cc' 
            AND t1.company_cd = t3.company_cd 
            AND t1.deal_class = t3.deal_class 
            AND t1.crm_account_class_cd = t3.crm_account_class_cd 
            AND t1.crm_account_status = t3.crm_account_status
    )
    /*END*/
/*IF !countQuery*/
    /*IF order*/
ORDER BY
    /*$orderByClause*/
    /*END*/
/*END*/
    
