SELECT t1.company_cd,
        t1.crm_contact_cd,
        t1.department_cd,
        t1.term_cd,
        t1.start_date,
        t1.end_date,
        t1.main_charge_flag,
        t1.delete_flag
FROM crm_cc_contact_charge_dept t1
/*BEGIN*/
WHERE 
/*IF companyCd != null && companyCd.length() > 0*/
    t1.company_cd = /*companyCd*/
/*END*/
/*IF crmContactCd != null && crmContactCd.length() > 0*/     
    AND t1.crm_contact_cd = /*crmContactCd*/
/*END*/
    AND (
    /*IF departmentCd != null && departmentCd.length() > 0*/  
        t1.department_cd = /*departmentCd*/ 
        OR
    /*END*/
     EXISTS
     (SELECT t2.department_cd
                      FROM crm_cc_contact_charge_dept t2
                      WHERE t2.delete_flag = '0'
                      /*IF crmContactCd != null && crmContactCd.length() > 0*/
                      AND t2.crm_contact_cd = /*crmContactCd*/
                      /*END*/
                      /*IF companyCd != null && companyCd.length() > 0*/
                      AND t2.company_cd = /*companyCd*/
                      /*END*/
                      AND t1.department_cd = t2.department_cd)
         )
/*END*/
ORDER BY t1.department_cd,t1.start_date,t1.end_date
