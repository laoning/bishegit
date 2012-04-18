SELECT 
    t1.crm_account_group_cd        AS crmAccountGroupCd,
    t1.parent_crm_account_group_cd AS parentCrmAccountGroupCd,
    t1.path                        AS path,
    t1.root_flag                   AS rootFlag,
    t1.depth                       AS depth,
    t2.crm_account_group_name      AS crmAccountGroupName
FROM 
    crm_cc_acct_grp_inc_ath t1
    LEFT JOIN
    crm_cc_acct_grp t2
    ON t1.crm_account_group_cd = t2.crm_account_group_cd
    AND t2.company_cd  = /*companyCd*/
    AND t2.locale_id   = /*localeId*/
    AND t2.start_date  <= /*sysDate*/
    AND t2.end_date    > /*sysDate*/
    AND t2.delete_flag = '0'
WHERE 
        t1.company_cd  = /*companyCd*/
    AND t1.start_date  <= /*sysDate*/
    AND t1.end_date    > /*sysDate*/
    AND t1.delete_flag = '0'

ORDER BY 
    t1.crm_account_group_cd ,
    t1.depth