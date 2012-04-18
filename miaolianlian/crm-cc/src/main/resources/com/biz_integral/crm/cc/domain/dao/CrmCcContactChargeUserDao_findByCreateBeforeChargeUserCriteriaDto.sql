SELECT t1.company_cd,
        t1.crm_contact_cd,
        t1.user_cd,
        t1.term_cd,
        t1.start_date,
        t1.end_date,
        t1.main_charge_flag,
        t1.delete_flag
FROM crm_cc_contact_charge_user t1
WHERE
    t1.company_cd = /*companyCd*/
/*IF crmContactCd != null && crmContactCd.length() > 0*/
    AND t1.crm_contact_cd = /*crmContactCd*/
/*END*/
    AND (
    /*IF userCd != null && userCd.length() > 0*/
        t1.user_cd = /*userCd*/ 
        OR
    /*END*/
     EXISTS
     (SELECT t2.user_cd
                      FROM crm_cc_contact_charge_user t2
                      WHERE t2.delete_flag = '0'
                      /*IF companyCd != null && companyCd.length() > 0*/
                      AND t2.company_cd = /*companyCd*/
                      /*END*/
                      /*IF crmContactCd != null && crmContactCd.length() > 0*/
                      AND t2.crm_contact_cd = /*crmContactCd*/
                      /*END*/
                      AND t1.user_cd = t2.user_cd)
         )
/*END*/
ORDER BY t1.user_cd,t1.start_date,t1.end_date