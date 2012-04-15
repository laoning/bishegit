SELECT 
    department_cd    --組織コード
FROM 
    crm_cc_department_ath_cmn
WHERE
    company_cd        = /*companyCd*/'xx'            --会社コード
AND department_set_cd = /*departmentSetCd*/'xx'      --会社組織セットコード
AND user_cd           = /*userCd*/'xx'               --ユーザコード
AND start_date        <= /*sysDate*/
AND end_date          > /*sysDate*/
AND delete_flag       ='0' 
AND department_main   ='1' 
