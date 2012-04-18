SELECT
    t1.crm_account_name AS crm_account_name,
    t1.crm_account_status AS crm_account_status,
    t1.important_level AS important_level,
    t1.crm_account_type AS crm_account_type,
    t1.address1 AS address,
    t1.address2 AS address2,
    t1.address3 AS address3,
    t1.telephone_number AS telephone_number,
    t1.crm_account_cd AS crm_account_cd,
    t8.user_name AS user_name,
    /*IF easySearchMode!= 1*/
    t9.department_name AS department_name,
    t4.sales_activity_start_date AS business_start_date,
    t5.plan_date AS contract_start_date,
    t9.department_cd AS department_cd,
    /*END*/
    t1.crm_account_name_kana AS crm_account_name_kana,
    t8.user_cd AS user_cd,
    t1.delete_flag AS delete_flag
FROM
    crm_cc_account t1 
    LEFT OUTER JOIN (SELECT 
                           t6.company_cd,
                           t6.crm_account_cd,
                           t6.crm_domain_cd,
                           t6.main_charge_flag,
                           t6.delete_flag,
                           t6.start_date,
                           t6.end_date,
                           t2.locale_id,
                           t2.user_name,
                           t6.user_cd
                       FROM
                           crm_cc_account_charge_user t6 
                       LEFT OUTER JOIN crm_cc_user_cmn t2 ON t6.user_cd = t2.user_cd
                        AND t2.locale_id = /*localeId*/ 
                        AND t2.start_date <= /*systemDate*/
                        AND t2.end_date > /*systemDate*/
                        AND t2.delete_flag = '0'
                       ) t8 
    ON t1.company_cd = t8.company_cd AND t1.crm_account_cd = t8.crm_account_cd
    AND t8.crm_domain_cd = /*crmDomainCd*/    --パラメータ.CRM領域コード
    AND t8.main_charge_flag = '1'
    AND t8.delete_flag = '0'
    AND t8.start_date <= /*systemDate*/
    AND t8.end_date > /*systemDate*/
    /*IF easySearchMode!= 1*/
    LEFT OUTER JOIN (SELECT 
                           t7.company_cd,
                           t7.crm_account_cd,
                           t7.crm_domain_cd,
                           t7.main_charge_flag,
                           t7.delete_flag,
                           t7.start_date,
                           t7.end_date,
                           t3.department_set_cd,
                           t3.locale_id,
                           t3.department_name,
                           t7.department_cd
                       FROM
                           crm_cc_account_charge_dept t7 
                       LEFT OUTER JOIN crm_cc_department_cmn t3 ON t7.department_cd = t3.department_cd
                        AND t3.company_cd = t7.company_cd                --会社コード
                        AND t3.department_set_cd = /*departmentSetCd*/   --パラメータ.CRM用組織セットコード
                        AND t3.locale_id = /*localeId*/                  --ロケールID
                        AND t3.start_date <= /*systemDate*/
                        AND t3.end_date > /*systemDate*/
                        AND t3.delete_flag = '0'
                        ) t9 
    ON t1.company_cd = t9.company_cd AND t1.crm_account_cd = t9.crm_account_cd    --（注）左記条件は、簡易検索モード時は不要。
    AND t9.crm_domain_cd = /*crmDomainCd*/                                         --パラメータ.CRM領域コード
    AND t9.main_charge_flag = '1'
    AND t9.delete_flag = '0'
    AND t9.start_date <= /*systemDate*/
    AND t9.end_date > /*systemDate*/
    LEFT OUTER JOIN (
    SELECT
        MIN(sales_activity_start_date) AS sales_activity_start_date,
        company_cd AS company_cd,
        crm_account_cd AS crm_account_cd
    FROM 
        crm_sa_sales_act
    WHERE
        sales_activity_type IN ('1','2')
    AND
        sales_activity_status = '3'
    AND
        delete_flag = '0'
    GROUP BY 
        company_cd,
        crm_account_cd) t4 ON t1.company_cd = t4.company_cd AND t1.crm_account_cd = t4.crm_account_cd                             --（注）左記条件は、簡易検索モード時は不要。
    LEFT OUTER JOIN (
    SELECT
        MIN(plan_date) AS plan_date,
        company_cd AS company_cd,
        crm_account_cd AS crm_account_cd
    FROM
        crm_pm_proposal
    WHERE
        proposal_stage_status='2'
    AND
        delete_flag = '0'
    GROUP BY 
        company_cd,
        crm_account_cd) t5 ON t1.company_cd = t5.company_cd AND t1.crm_account_cd = t5.crm_account_cd                             --（注）左記条件は、簡易検索モード時は不要。
    /*END*/
WHERE
    t1.locale_id = /*localeId*/                                                --ロケールID
AND
    t1.company_cd = /*companyCd*/                                              --会社コード
    /*IF crmAccountCd!= null && crmAccountCd.length() > 0*/
AND
    t1.crm_account_cd LIKE /*crmAccountCd*/ ESCAPE '|'                         --アカウントコード
    /*END*/
    /*IF crmAccountName!= null && crmAccountName.length() > 0*/
AND 
    t1.crm_account_name LIKE /*crmAccountName*/ ESCAPE '|'                     --アカウント名
    /*END*/
    /*IF crmAccountShortName!= null && crmAccountShortName.length() > 0*/
AND 
    t1.crm_account_short_name LIKE /*crmAccountShortName*/ ESCAPE '|'          --アカウント略称
    /*END*/
    /*IF crmAccountKanaName!= null && crmAccountKanaName.length() > 0*/
AND 
    t1.crm_account_name_kana LIKE /*crmAccountKanaName*/ ESCAPE '|'            --アカウント名（カナ）
    /*END*/
    /*IF crmAccountSearchName!= null && crmAccountSearchName.length() > 0*/
AND 
    t1.crm_account_search_name LIKE /*crmAccountSearchName*/ ESCAPE '|'        --アカウント検索名
    /*END*/
    /*IF customerCd!= null && customerCd.length() > 0*/
AND 
    t1.customer_cd LIKE /*customerCd*/ ESCAPE '|'                              --取引先コード
    /*END*/
    /*IF crmAccountStatus!= null && crmAccountStatus.length() > 0*/
AND 
    t1.crm_account_status = /*crmAccountStatus*/                               --状況
    /*END*/
    /*IF importantLevel!= null && importantLevel.length() > 0*/
AND 
    t1.important_level = /*importantLevel*/                                    --重要度
    /*END*/
    /*IF crmAccountType!= null && crmAccountType.length() > 0*/
AND 
    t1.crm_account_type = /*crmAccountType*/                                   --区分
    /*END*/
    /*IF address!= null && address.length() > 0*/
AND 
    (
    t1.address1 LIKE /*address*/ ESCAPE '|'                                    --住所
    OR t1.address2 LIKE /*address*/ ESCAPE '|'                                 --住所 
    OR t1.address3 LIKE /*address*/ ESCAPE '|'                                 --住所
    )
    /*END*/
    /*IF telephoneNumber!= null && telephoneNumber.length() > 0*/
AND 
    t1.telephone_number LIKE /*telephoneNumber*/ ESCAPE '|'                    --電話番号
    /*END*/
    /*IF deleteFlag == 0*/
AND
    t1.delete_flag = '0'
    /*END*/
    /*IF deleteFlag == 1*/
AND
    t1.delete_flag = '1'
    /*END*/
    /*IF deleteFlag == null*/
AND
    t1.delete_flag IN ('0','1')
    /*END*/
    /*IF (userCd!= null && userCd.length() > 0) || (userName!= null && userName.length() > 0)*/ 
AND
    EXISTS 
        (
        SELECT
            t11.crm_account_cd
        FROM
            crm_cc_account_charge_user t11,
            crm_cc_user_cmn t12
        WHERE
            t1.crm_account_cd = t11.crm_account_cd
        AND
            t11.user_cd = t12.user_cd
        AND
            t11.company_cd = /*companyCd*/                                     --会社コード
        AND 
            t11.crm_domain_cd = /*crmDomainCd*/                                --パラメータ.CRM領域コード
        /*IF userCd!= null && userCd.length() > 0*/
        AND
            t11.user_cd LIKE /*userCd*/ ESCAPE '|'                             --担当者コード
        /*END*/
        AND
            t11.delete_flag = '0'
        AND
            t12.locale_id = /*localeId*/                                       --ロケールID
        AND
            t12.start_date <= /*systemDate*/
        AND
            t12.end_date > /*systemDate*/
        /*IF userName!= null && userName.length() > 0*/
        AND
            t12.user_name LIKE /*userName*/ ESCAPE '|'                         --担当者名
        /*END*/
        )
   /*END*/
   /*IF crmAccountCatCdListToArray != null*/
AND
   EXISTS
        (
        SELECT 
            t_ath.crm_account_cd 
        FROM crm_cc_account_class_ath t_ath 
        WHERE
            t_ath.company_cd = /*companyCd*/ 
            AND t1.crm_account_cd = t_ath.crm_account_cd   
            AND t_ath.crm_account_class_cd IN /*crmAccountCatCdListToArray*/('aaa','bbb')
            AND t_ath.delete_flag = '0'
        )
   /*END*/
/*IF "0".equals(hasPrivilegeFlag)*/
AND (
        EXISTS
            (
            SELECT
                t21.crm_account_cd
            FROM
                crm_cc_account_charge_user t21
            WHERE
                t1.crm_account_cd = t21.crm_account_cd
            AND
                t21.company_cd = /*companyCd*/                                 --会社コード
            AND 
                t21.crm_domain_cd = /*crmDomainCd*/                            --入力値.CRM領域コード
            AND 
                t21.user_cd = /*userCode*/                                     --ユーザコンテキスト.ユーザコード
            AND
                t21.start_date <= /*systemDate*/
            AND
                t21.end_date > /*systemDate*/
            AND
                t21.delete_flag = '0'
            )
    /*IF "1".equals(hasAceessCustomerFlag)*/
    OR
        EXISTS 
            (
            SELECT
                t31.crm_account_cd
            FROM
                crm_cc_account_charge_dept t31
            WHERE
                t31.crm_account_cd = t1.crm_account_cd
            AND
                t31.company_cd = /*companyCd*/                                 --会社コード
            AND 
                t31.crm_domain_cd = /*crmDomainCd*/                            --入力値.CRM領域コード
            /*IF updepartmentCdListToArray != null*/
            AND 
                t31.department_cd IN /*updepartmentCdListToArray*/('aaa','bbb')           --List<担当組織コード>
            /*END*/
            AND
                t31.start_date <= /*systemDate*/
            AND
                t31.end_date > /*systemDate*/
            AND
                t31.delete_flag = '0'
            )
    /*END*/
    )
/*END*/
AND
    t1.start_date <= /*systemDate*/
AND
    t1.end_date > /*systemDate*/

/*IF !countQuery*/
  /*IF order*/
ORDER BY 
    /*$orderByClause*/
  /*END*/
/*END*/
