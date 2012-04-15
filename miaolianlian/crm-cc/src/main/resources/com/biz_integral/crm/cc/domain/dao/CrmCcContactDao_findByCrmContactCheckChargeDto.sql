SELECT t1.crm_contact_cd
FROM crm_cc_contact t1
WHERE 
    (t1.company_cd = /*companyCd*/
    AND t1.locale_id = /*localeId*/
    /*IF crmContactCdArray != null */
    AND t1.crm_contact_cd IN /*crmContactCdArray*/('aaa','bbb')
    /*END*/
    /*IF baseDate != null*/ 
    AND t1.start_date <= /*baseDate*/
    AND t1.end_date > /*baseDate*/
    /*END*/
    AND t1.delete_flag = '0'
    AND (EXISTS (SELECT t2.crm_contact_cd
                  FROM crm_cc_contact_charge_user t2
                  WHERE t2.company_cd = /*companyCd*/
                      AND t2.user_cd = /*userCd*/
                      /*IF baseDate != null*/ 
                      AND t2.start_date <= /*baseDate*/
                      AND t2.end_date > /*baseDate*/
                      /*END*/
                      AND t2.delete_flag = '0'
                      AND t1.crm_contact_cd = t2.crm_contact_cd)
    /*IF dataAccessCustomerFlag == "1"*/
    OR EXISTS (SELECT t4.crm_contact_cd
                 FROM crm_cc_contact_charge_dept t4
                WHERE t4.company_cd = /*companyCd*/
                    /*IF deptCdList != null */
                    AND t4.department_cd IN /*deptCdList*/('aaa','bbb')
                    /*END*/
                    /*IF baseDate != null*/ 
                    AND t4.start_date <= /*baseDate*/
                    AND t4.end_date > /*baseDate*/
                    /*END*/
                    AND t4.delete_flag = '0'
                    AND t1.crm_contact_cd = t4.crm_contact_cd)
    /*END*/              
    )
   )
UNION ALL
    SELECT t6.crm_contact_cd
    FROM crm_cc_contact t6
    WHERE 
    (t6.company_cd = /*companyCd*/
     AND t6.locale_id = /*localeId*/
     /*IF crmContactCdArray != null */
     AND t6.crm_contact_cd IN /*crmContactCdArray*/('aaa','bbb')
     /*END*/
     /*IF baseDate != null*/ 
     AND t6.start_date <= /*baseDate*/
     AND t6.end_date > /*baseDate*/
     /*END*/
     AND t6.delete_flag = '0'
     AND (NOT EXISTS (SELECT t7.crm_contact_cd
                        FROM crm_cc_contact_charge_user t7
                        WHERE t7.company_cd = /*companyCd*/
                          /*IF baseDate != null*/ 
                          AND t7.start_date <= /*baseDate*/
                          AND t7.end_date > /*baseDate*/
                          /*END*/
                          AND t7.delete_flag = '0'
                          AND t6.crm_contact_cd = t7.crm_contact_cd)
      /*IF dataAccessCustomerFlag == "1"*/
      AND NOT EXISTS (SELECT t9.crm_contact_cd
                        FROM crm_cc_contact_charge_dept t9
                        WHERE t9.company_cd = /*companyCd*/
                            /*IF baseDate != null*/ 
                            AND t9.start_date <= /*baseDate*/
                            AND t9.end_date > /*baseDate*/
                            /*END*/
                            AND t9.delete_flag = '0'
                            AND t6.crm_contact_cd = t9.crm_contact_cd)
      /*END*/   
      )
    )