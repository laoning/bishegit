SELECT company_cd,
        crm_contact_cd,
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
FROM crm_cc_contact_competition
WHERE delete_flag = '0'
/*IF companyCd != null && companyCd.length() > 0*/
AND  company_cd = /*companyCd*/
/*END*/
/*IF crmContactCd != null && crmContactCd.length() > 0*/
AND crm_contact_cd = /*crmContactCd*/
/*END*/
/*IF !countQuery*/
  /*IF order*/
ORDER BY 
    /*$orderByClause*/
  /*END*/
/*END*/

