SELECT 
    t2.department_cd,                               --組織コード
    t2.department_name                              --組織名
FROM 
    crm_cc_department_ath_cmn  t1,
    crm_cc_department_cmn      t2
WHERE
    t1.company_cd         = /*companyCd*/'xxx'           --会社コード
AND t1.department_set_cd  = /*departmentSetCd*/'xxx'     --会社組織セットコード
AND t1.user_cd            = /*userCd*/'xxx'              --ユーザコード
AND t1.start_date         <= /*sysDate*/
AND t1.end_date           > /*sysDate*/
AND t1.delete_flag        ='0' 

AND t2.company_cd         = /*companyCd*/'xxx'           --会社コード
AND t2.department_set_cd  = /*departmentSetCd*/'xxx'     --会社組織セットコード
AND t2.locale_id          = /*localeId*/'xxx'            --ロケールID
AND t2.start_date         <= /*sysDate*/
AND t2.end_date           > /*sysDate*/
AND t2.delete_flag        ='0' 

AND t2.department_cd = t1.department_cd
ORDER BY
department_cd