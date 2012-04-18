SELECT crm_account_cd AS crmAccountCd,
       competition_name AS competitionName,
       competition_id AS competitionId,
       overview AS overview,
       acquirement_proposal_case AS acquirementProposalCase,
       competition_level AS competitionLevel,
       notes AS notes,
       version_no AS versionNo,
       term_cd AS termCd
FROM crm_cc_account_competition
WHERE 
/*IF companyCd != null && companyCd.length() > 0*/
company_cd = /*companyCd*/
/*END*/
/*IF crmAccountCd != null && crmAccountCd.length() > 0*/
AND crm_account_cd = /*crmAccountCd*/
/*END*/
AND competition_id = /*competitionId*/
AND start_date <= /*systemDate*/
AND end_date > /*systemDate*/
AND delete_flag = '0'
