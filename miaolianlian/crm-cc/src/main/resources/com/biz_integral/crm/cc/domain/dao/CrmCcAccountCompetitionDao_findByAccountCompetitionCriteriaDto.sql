SELECT company_cd,
        crm_account_cd,
        competition_id,
        term_cd,
        start_date,
        end_date,
        competition_name,
        competition_name_kana,
        overview,
        acquirement_proposal_case,
        competition_level,
        notes,
        delete_flag,
        sort_key,
        version_no,
        create_user_cd,
        create_date,
        record_user_cd,
        record_date
FROM crm_cc_account_competition
WHERE delete_flag = '0'
/*IF companyCd != null && companyCd.length() > 0*/
AND  company_cd = /*companyCd*/
/*END*/
/*IF crmAccountCd != null && crmAccountCd.length() > 0*/
AND crm_account_cd = /*crmAccountCd*/
/*END*/
/*IF !countQuery*/
  /*IF order*/
ORDER BY 
    /*$orderByClause*/
  /*END*/
/*END*/
        