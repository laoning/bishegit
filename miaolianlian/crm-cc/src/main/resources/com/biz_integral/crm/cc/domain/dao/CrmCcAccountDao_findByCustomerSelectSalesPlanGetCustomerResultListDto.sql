SELECT
    t10.crm_account_cd,
    t10.crm_account_name,
    t10.important_level,
    t10.item_category_cd,
    t10.item_category_name,
    t10.user_cd,
    t10.user_name,
    SUM(t10.sales_result_amt) AS latest_result_amt
FROM
(
    SELECT
        t1.crm_account_cd,
        t1.crm_account_name,
        t1.important_level,
        t1.company_cd,
        t3.user_cd,
        t3.user_name,
        t8.sales_result_amt,
        t8.item_category_cd,
        t8.item_category_name
    FROM
    (
        SELECT
            crm_account_cd,
            crm_account_name,
            important_level,
            company_cd
        FROM
            crm_cc_account
        WHERE
            company_cd = /*companyCd*/
        AND
            locale_id = /*localeId*/
        /*IF crmAccountName != null && crmAccountName.length() > 0*/
        AND
            crm_account_name LIKE /*crmAccountName*/ ESCAPE '|'
        /*END*/
        /*IF crmAccountNameKana != null && crmAccountNameKana.length() > 0*/
        AND
            crm_account_name_kana LIKE /*crmAccountNameKana*/ ESCAPE '|'
        /*END*/
        /*IF crmContactShortName != null && crmContactShortName.length() > 0*/
        AND
            crm_account_short_name LIKE /*crmContactShortName*/ ESCAPE '|'
        /*END*/
        /*IF crmAccountSearchName != null && crmAccountSearchName.length() > 0*/
        AND
            crm_account_search_name LIKE /*crmAccountSearchName*/ ESCAPE '|'
        /*END*/
        /*IF crmAccountCd != null && crmAccountCd.length() > 0*/
        AND
            crm_account_cd LIKE /*crmAccountCd*/ ESCAPE '|'
        /*END*/
        /*IF importantLevel != null && importantLevel.length() > 0*/
        AND
            important_level = /*importantLevel*/
        /*END*/
        AND
            delete_flag = '0'
    ) t1
    /*IF registeredFlag == "1"*/
    INNER JOIN
        crm_sp_sales_regist_cust t9
    ON
        t9.sales_plan_def_id = /*salesPlanDefId*/
    AND
        t9.crm_account_cd = t1.crm_account_cd
    AND
        t9.delete_flag = '0'
    /*END*/
    LEFT OUTER JOIN
        crm_cc_account_charge_user t2
    ON
        t2.main_charge_flag = '1'
    /*IF crmDomainCd != null && crmDomainCd.length() > 0*/
    AND
        t2.crm_domain_cd = /*crmDomainCd*/
    /*END*/
    AND
        t2.delete_flag = '0'
    AND
        t1.company_cd = t2.company_cd
    AND
        t1.crm_account_cd = t2.crm_account_cd
    LEFT OUTER JOIN
        crm_cc_user_cmn t3
    ON
        t3.locale_id = /*localeId*/
    AND
        t3.start_date <= /*searchBaseDate*/
    AND
        t3.end_date > /*searchBaseDate*/
    AND
        t2.user_cd = t3.user_cd
/*IF (itemCategoryCd != null && itemCategoryCd.length() > 0) || (itemCategoryName != null && itemCategoryName.length() > 0) ||
     (chargeUserCd != null && chargeUserCd.length() > 0) || (chargeUserName != null && chargeUserName.length() > 0) ||
      resultDateFrom != null || resultDateTo != null || resultAmtFrom != null || resultAmtTo != null */
    INNER JOIN
--ELSE
    LEFT OUTER JOIN
/*END*/
    (
        SELECT
            t5.company_cd,
            t5.crm_account_cd,
            t5.sales_result_amt,
            t5.item_category_cd,
            t6.item_category_name
        FROM
            crm_sp_sales_plan_def t4
        INNER JOIN
            crm_cc_sales_result t5 
        ON
            t4.id = /*salesPlanDefId*/
        AND
            t4.delete_flag = '0'
        AND
            t5.sales_result_date >= t4.compare_target_date_from
        AND
            t5.sales_result_date < t4.compare_target_date_to
        AND
            t5.company_cd = t4.company_cd
        /*IF crmDomainCd != null && crmDomainCd.length() > 0*/
        AND t5.crm_domain_cd = /*crmDomainCd*/
        /*END*/
        /*IF resultDateFrom != null*/
        AND t5.sales_result_date >= /*resultDateFrom*/
        /*END*/
        /*IF resultDateTo != null*/
        AND t5.sales_result_date < /*resultDateTo*/
        /*END*/
        /*IF resultAmtFrom != null*/
        AND t5.sales_result_amt >= /*resultAmtFrom*/
        /*END*/
        /*IF resultAmtTo != null*/
        AND t5.sales_result_amt < /*resultAmtTo*/
        /*END*/
        /*IF chargeUserCd != null && chargeUserCd.length() > 0*/
        AND t5.user_cd LIKE /*chargeUserCd*/ ESCAPE '|'
        /*END*/
        /*IF itemCategoryCd != null && itemCategoryCd.length() > 0*/
        AND t5.item_category_cd LIKE /*itemCategoryCd*/ ESCAPE '|'
        /*END*/
        AND t5.delete_flag = '0'
        /*IF itemCategoryName != null && itemCategoryName.length() > 0*/
        INNER JOIN
        --ELSE
        LEFT OUTER JOIN
        /*END*/
            crm_cc_item_cat_cmn t6
        ON
            t5.item_category_cd = t6.item_category_cd
        /*IF itemCategorySetCd != null && itemCategorySetCd.length() > 0*/
        AND t6.item_category_set_cd = /*itemCategorySetCd*/
        /*END*/
        AND t6.locale_id = /*localeId*/
        AND t6.start_date <= /*searchBaseDate*/
        AND t6.end_date > /*searchBaseDate*/
        /*IF itemCategoryName != null && itemCategoryName.length() > 0*/
        AND t6.item_category_name LIKE /*itemCategoryName*/ ESCAPE '|'
        /*END*/
        /*IF chargeUserName != null && chargeUserName.length() > 0*/
        INNER JOIN
            crm_cc_user_cmn t7
        ON
            t5.user_cd = t7.user_cd
        AND t7.locale_id = /*localeId*/
        AND t7.start_date <= /*searchBaseDate*/
        AND t7.end_date > /*searchBaseDate*/
        AND t7.user_name LIKE /*chargeUserName*/ ESCAPE '|'
        /*END*/
    ) t8
    ON
       t1.company_cd = t8.company_cd
    AND
       t1.crm_account_cd = t8.crm_account_cd
) t10
/*IF hasPrivilege == "false"*/
WHERE
    EXISTS
    (
        SELECT
            t13.crm_account_cd
        FROM
            crm_cc_account_charge_user t13
        WHERE
            t10.crm_account_cd = t13.crm_account_cd
        AND
            t13.company_cd = /*companyCd*/
        /*IF crmDomainCd != null && crmDomainCd.length() > 0*/
        AND
            t13.crm_domain_cd = /*crmDomainCd*/
        /*END*/
        /*IF userCd != null && userCd.length() > 0*/
        AND
            t13.user_cd = /*userCd*/
        /*END*/
        AND
            t13.start_date <= /*searchBaseDate*/
        AND
            t13.end_date > /*searchBaseDate*/
        AND
            t13.delete_flag = '0'
    )
    /*IF chargeDepartmentCdArray != null*/
    OR
    EXISTS
    (
        SELECT
            t14.crm_account_cd
        FROM
            crm_cc_account_charge_dept t14
        WHERE
            t10.crm_account_cd = t14.crm_account_cd
        /*IF companyCd != null && companyCd.length() > 0*/
        AND
            t14.company_cd = /*companyCd*/
        /*END*/
        /*IF crmDomainCd != null && crmDomainCd.length() > 0*/
        AND
            t14.crm_domain_cd = /*crmDomainCd*/
        /*END*/
        AND
            t14.department_cd IN /*chargeDepartmentCdArray*/('a' ,'b')
        AND
            t14.start_date <= /*searchBaseDate*/
        AND
            t14.end_date > /*searchBaseDate*/
        AND
            t14.delete_flag = '0'
    )
    /*END*/
/*END*/
GROUP BY
    t10.crm_account_cd,
    t10.crm_account_name,
    t10.important_level,
    t10.item_category_cd,
    t10.item_category_name,
    t10.user_cd,
    t10.user_name
/*IF !countQuery*/
  /*IF order*/
ORDER BY 
    /*$orderByClause*/
  /*END*/
/*END*/
