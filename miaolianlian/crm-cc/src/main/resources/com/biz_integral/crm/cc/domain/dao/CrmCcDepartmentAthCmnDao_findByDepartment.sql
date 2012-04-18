-- 指定されたユーザと同一の所属組織に属する、組織所属を取得する。
SELECT
    dept.company_cd
    , dept.department_set_cd
    , dept.department_cd
    , dept.user_cd
    , dept.term_cd
    , dept.start_date
    , dept.end_date
    , dept.department_main
    , dept.delete_flag
    , dept.sort_key
    , dept.create_user_cd
    , dept.create_date
    , dept.record_user_cd
    , dept.record_date 
FROM
    crm_cc_department_ath_cmn dept 
WHERE
    dept.delete_flag = '0' 
    AND dept.start_date <= /*nowDate*/'2010/11/01' 
    AND dept.end_date > /*nowDate*/'2010/11/01'
    AND EXISTS(
        SELECT
            *
        FROM
            crm_cc_department_ath_cmn inner_dept
        WHERE
            inner_dept.delete_flag = '0' 
            AND inner_dept.company_cd = /*companyCd*/'0001' 
            AND inner_dept.department_set_cd = /*departmentSetCd*/'CrmDept001' 
            AND inner_dept.start_date <= /*nowDate*/'2010/11/01' 
            AND inner_dept.end_date > /*nowDate*/'2010/11/01' 
            AND inner_dept.user_cd = /*userCd*/'ba00102'
            AND dept.company_cd = inner_dept.company_cd 
            AND dept.department_set_cd = inner_dept.department_set_cd 
            AND dept.department_cd = inner_dept.department_cd 
        )
