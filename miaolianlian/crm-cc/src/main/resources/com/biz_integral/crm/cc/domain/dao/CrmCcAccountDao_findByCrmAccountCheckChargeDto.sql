SELECT t1.crm_account_cd
FROM crm_cc_account t1
WHERE  t1.delete_flag = '0'
   AND t1.company_cd = /*companyCd*/
   AND t1.locale_id = /*localeId*/
/*IF crmAccountCdArray != null */
   AND t1.crm_account_cd IN /*crmAccountCdArray*/('aaa', 'bbb')
/*END*/
/*IF baseDate != null*/ 
   AND t1.start_date <= /*baseDate*/
   AND t1.end_date > /*baseDate*/
/*END*/   
   AND (EXISTS (SELECT t2.crm_account_cd
                FROM crm_cc_account_charge_user t2
                WHERE t2.delete_flag = '0'
                   AND t2.company_cd = /*companyCd*/
                /*IF crmDomainCd != null && crmDomainCd.length() > 0*/   
                   AND t2.crm_domain_cd = /*crmDomainCd*/
                /*END*/
                /*IF userCd != null && userCd.length() > 0*/
                   AND t2.user_cd = /*userCd*/
                /*END*/
                /*IF baseDate != null*/ 
                   AND t2.start_date <= /*baseDate*/
                   AND t2.end_date > /*baseDate*/
                /*END*/  
                   AND t1.crm_account_cd = t2.crm_account_cd)
   /*IF dataAccessCustomerFlag == "1"*/
   OR EXISTS (SELECT t4.crm_account_cd
                FROM crm_cc_account_charge_dept t4
                WHERE t4.delete_flag = '0'
                  AND t4.company_cd = /*companyCd*/
                /*IF crmDomainCd != null && crmDomainCd.length() > 0*/ 
                  AND t4.crm_domain_cd = /*crmDomainCd*/
                /*END*/
                /*IF deptCdList != null */ 
                  AND t4.department_cd IN /*deptCdList*/('aaa', 'bbb')
                /*END*/
                /*IF baseDate != null */ 
                  AND t4.start_date <= /*baseDate*/
                  AND t4.end_date > /*baseDate*/
                /*END*/
                  AND t1.crm_account_cd = t4.crm_account_cd)
   /*END*/
   )