SELECT t2.department_name AS departmentName,
        t1.main_charge_flag AS mainCharge,
        t1.start_date AS startDate,
        t1.end_date AS endDate,
        t2.department_cd AS departmentCd,
        t1.main_charge_flag AS mainChargeFlag,
        t2.start_date AS deptCmnStartDate,
        t2.end_date AS deptCmnEndDate
FROM crm_cc_contact_charge_dept t1,
        crm_cc_department_cmn t2
WHERE t1.department_cd = t2.department_cd
AND t1.company_cd = t2.company_cd
AND t1.company_cd = /*companyCd*/
/*IF crmContactCd != null && crmContactCd.length() > 0*/
AND t1.crm_contact_cd = /*crmContactCd*/
/*END*/
AND t1.delete_flag = '0'
/*IF departmentSetCd != null && departmentSetCd.length() > 0*/
AND t2.department_set_cd = /*departmentSetCd*/
/*END*/
AND t2.locale_id = /*localeId*/
AND t2.start_date <= /*systemDate*/
AND t2.end_date > /*systemDate*/
ORDER BY departmentCd