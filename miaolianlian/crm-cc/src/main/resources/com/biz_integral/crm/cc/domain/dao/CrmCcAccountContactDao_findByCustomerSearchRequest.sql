SELECT
  a.crm_account_cd AS accountCd                                   -- アカウントコード
  , a.crm_account_name AS crmAccountName                          -- アカウント名
  , a.important_level AS importance                               -- 重要度
  , a.address1 AS address1
  , a.address2 AS address2
  , a.address3 AS address3                                        -- 住所
  , a.crm_contact_cd AS contactCd                                 -- コンタクトコード
  , a.crm_contact_name AS crmContactName                          -- コンタクト名
  , t3.sales_activity_start_date AS salesActivityStartDate        -- 前回訪問日
FROM
  ( 
    SELECT
      tc.crm_contact_cd
      , tc.crm_contact_name
      , ta.crm_account_cd
      , ta.crm_account_name
      , ta.important_level
      , ta.address1
      , ta.address2
      , ta.address3 
    FROM
      crm_cc_account ta 
      INNER JOIN crm_cc_account_contact ac 
        ON ta.crm_account_cd = ac.crm_account_cd 
        AND ac.company_cd = /*companyCd*/'0001'
        AND /*$accountContactCd*/
        AND ac.delete_flag = '0' 
        AND ac.start_date <= /*baseDate*/'2010-11-20 00:00:00' 
        AND ac.end_date > /*baseDate*/'2010-11-20 00:00:00' 
      INNER JOIN crm_cc_account_charge_user acu 
        ON acu.crm_account_cd = ta.crm_account_cd 
        AND acu.company_cd = /*companyCd*/'0001' 
        AND acu.start_date <= /*baseDate*/'2010-11-20 00:00:00' 
        AND acu.end_date > /*baseDate*/'2010-11-20 00:00:00' 
        AND acu.crm_domain_cd = /*crmDomainCd*/'CrmDomain001' 
        AND acu.user_cd = /*userCd*/'baa0101' 
        AND acu.delete_flag = '0' 
      INNER JOIN ( 
        SELECT
          c1.crm_contact_cd
          , c1.crm_contact_name 
        FROM
          crm_cc_contact c1
          , crm_cc_contact_charge_user ccu 
        WHERE
          c1.crm_contact_cd = ccu.crm_contact_cd 
          AND ( 
            c1.new_crm_contact_cd IS NULL 
            OR c1.new_crm_contact_cd = ''
          ) 
          AND c1.delete_flag = 0 
          AND c1.locale_id = /*localeId*/'ja' 
          AND c1.company_cd = /*companyCd*/'0001' 
          AND ccu.company_cd = /*companyCd*/'0001' 
          AND ccu.user_cd = /*userCd*/'baa0101' 
          AND ccu.start_date <= /*baseDate*/'2010-11-20 00:00:00' 
          AND ccu.end_date > /*baseDate*/'2010-11-20 00:00:00' 
          AND ccu.delete_flag = '0' 
        UNION ALL 
        SELECT DISTINCT
          crm_contact_cd
          , crm_contact_name 
        FROM
          crm_cc_contact 
        WHERE
          crm_contact_cd NOT IN ( 
            SELECT DISTINCT
              crm_contact_cd 
            FROM
              crm_cc_contact_charge_user 
            WHERE
              company_cd = /*companyCd*/'0001' 
              AND start_date <= /*baseDate*/'2010-11-20 00:00:00' 
              AND end_date > /*baseDate*/'2010-11-20 00:00:00' 
              AND delete_flag = '0'
          ) 
          AND ( 
            new_crm_contact_cd IS NULL 
            OR new_crm_contact_cd = ''
          ) 
          AND dummy_flag = '0'
          AND delete_flag = '0' 
          AND locale_id = /*localeId*/'ja' 
          AND company_cd = /*companyCd*/'0001'
      ) tc 
        ON ac.crm_contact_cd = tc.crm_contact_cd 
    WHERE
      ta.company_cd = /*companyCd*/'0001' 
      AND ta.locale_id = /*localeId*/'ja' 
      AND ta.start_date <= /*baseDate*/'2010-11-20 00:00:00' 
      AND ta.end_date > /*baseDate*/'2010-11-20 00:00:00' 
      AND ta.delete_flag = '0' 
      AND ( 
        ta.new_crm_account_cd IS NULL 
        OR ta.new_crm_account_cd = ''
      ) 
      AND ta.dummy_flag = '0'
  ) a 
  LEFT OUTER JOIN ( 
    SELECT
      crm_account_cd
      , crm_contact_cd
      , MAX(sales_activity_start_date) AS sales_activity_start_date 
    FROM
      crm_sa_sales_act 
    WHERE
      company_cd = /*companyCd*/'0001' 
      AND sales_activity_status = '3'
      AND (sales_activity_type = '1' OR sales_activity_type = '2')    
      AND delete_flag = '0' 
    GROUP BY
      crm_account_cd
      , crm_contact_cd
  ) t3 
    ON a.crm_account_cd = t3.crm_account_cd 
    AND a.crm_contact_cd = t3.crm_contact_cd 
ORDER BY
    crmAccountName ASC,
    accountCd   ASC,
    crmContactName ASC,
    contactCd   ASC
