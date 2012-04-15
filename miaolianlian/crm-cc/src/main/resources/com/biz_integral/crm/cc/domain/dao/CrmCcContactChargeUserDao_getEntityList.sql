SELECT t2.user_name AS userName,
        t1.main_charge_flag AS mainCharge,
        t1.start_date AS startDate,
        t1.end_date AS endDate,
        t2.user_cd AS userCd,
        t1.main_charge_flag AS mainChargeFlag,
        t2.start_date AS userCmnStartDate,
        t2.end_date AS userCmnEndDate
FROM crm_cc_contact_charge_user t1,
        crm_cc_user_cmn t2
WHERE t1.user_cd = t2.user_cd
/*IF companyCd != null && companyCd.length() > 0*/
AND t1.company_cd = /*companyCd*/
/*END*/
/*IF crmContactCd != null && crmContactCd.length() > 0*/
AND t1.crm_contact_cd = /*crmContactCd*/
/*END*/
AND t1.delete_flag = '0'
/*IF localeId != null && localeId.length() > 0*/
AND t2.locale_id = /*localeId*/
/*END*/
AND t2.start_date <= /*systemDate*/
AND t2.end_date > /*systemDate*/
ORDER BY userCd