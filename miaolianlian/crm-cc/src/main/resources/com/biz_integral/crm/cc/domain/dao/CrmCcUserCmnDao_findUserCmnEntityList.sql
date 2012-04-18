SELECT 
t1.user_cd
FROM crm_cc_user_cmn t1,
      crm_cc_department_cmn t2,
      crm_cc_department_ath_cmn t3
WHERE 
t1.user_cd = t3.user_cd
AND t1.locale_id = t2.locale_id
AND t2.company_cd = t3.company_cd
AND t2.department_set_cd = t3.department_set_cd
AND t2.department_cd = t3.department_cd

AND t1.user_cd = /*userCd*/
AND t1.locale_id = /*localeId*/
AND t1.start_date <= /*systemDate*/ 
AND t1.end_date > /*systemDate*/

AND t2.start_date <= /*systemDate*/ 
AND t2.end_date > /*systemDate*/
AND t2.delete_flag = '0'

AND t3.company_cd = /*companyCd*/
AND t3.department_set_cd = /*departmentSetCd*/
AND t3.start_date <= /*systemDate*/ 
AND t3.end_date > /*systemDate*/
AND t3.delete_flag = '0'