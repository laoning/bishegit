SELECT
  a.crm_account_cd AS accountCd                               -- アカウントコード
  , a.crm_account_name AS crmAccountName                      -- アカウント名
  , a.important_level AS importance                           -- 重要度
  , a.address1 AS address1
  , a.address2 AS address2
  , a.address3 AS address3                                    -- 住所
  , t3.sales_activity_start_date AS salesActivityStartDate    -- 前回訪問日
FROM
  ( 
    SELECT
      ta.crm_account_cd
      , ta.crm_account_name
      , ta.important_level
      , ta.address1
      , ta.address2
      , ta.address3
      , ta.delete_flag 
    FROM
      crm_cc_account ta
      , crm_cc_account_charge_user cac 
    WHERE
      ta.company_cd = /*companyCd*/'0002' 
      AND ta.crm_account_cd IN /*accountCd*/('MIZAC204','MIZAC20A2','MIZAC20A3','MIZAC20A4','MIZAC211','MIZAC212')
      AND ta.locale_id = /*localeId*/'ja' 
      AND ta.start_date <= /*baseDate*/'2010-11-19 00:00:00' 
      AND ta.end_date > /*baseDate*/'2010-11-19 00:00:00' 
      AND ta.delete_flag = '0'
      AND ( 
        ta.new_crm_account_cd IS NULL 
        OR ta.new_crm_account_cd = ''
      ) 
      AND ta.dummy_flag = '0' 
      AND cac.company_cd = /*companyCd*/'0002' 
      AND cac.crm_domain_cd = /*crmDomainCd*/'CrmDomain001' 
      AND cac.user_cd = /*userCd*/'zbc0102' 
      AND cac.delete_flag = '0' 
      AND cac.start_date <= /*baseDate*/'2010-11-19 00:00:00' 
      AND cac.end_date > /*baseDate*/'2010-11-19 00:00:00' 
      AND ta.crm_account_cd = cac.crm_account_cd
  ) a 
  LEFT OUTER JOIN ( 
    SELECT
      crm_account_cd
      , MAX(sales_activity_start_date) AS sales_activity_start_date 
    FROM
      crm_sa_sales_act 
    WHERE
      company_cd = /*companyCd*/'0002' 
      AND ( 
        crm_contact_cd IS NULL 
        OR crm_contact_cd = ''
      ) 
      AND sales_activity_status = '3' 
      AND (sales_activity_type = '1' OR sales_activity_type = '2')    
      AND delete_flag = '0' 
    GROUP BY
      crm_account_cd
  ) t3 
    ON a.crm_account_cd = t3.crm_account_cd
ORDER BY
    crmAccountName ASC,
    accountCd   ASC
