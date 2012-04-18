SELECT
	crm_contact_name,
	belong,
	sex,
	post,
	key_person,
	crm_contact_cd,
	email_address1,
	telephone_number,
	user_name,
	crm_contact_name_kana,
	user_cd
FROM
(
    SELECT
		    case1_t1.crm_contact_name AS crm_contact_name,
		    case1_t1.belong AS belong,
		    case1_t1.sex AS sex,
		    case1_t1.post AS post,
		    case1_t1.key_person AS key_person,
		    case1_t1.crm_contact_cd AS crm_contact_cd,
		    case1_t1.email_address1 AS email_address1,
		    case1_t1.telephone_number AS telephone_number,
		    case1_t3.user_name AS user_name,
		    case1_t1.crm_contact_name_kana AS crm_contact_name_kana,
		    case1_t3.user_cd AS user_cd
		FROM
		    crm_cc_contact case1_t1                                   --CRMコンタクト
		    LEFT OUTER JOIN (SELECT 
		                       case1_t5.main_charge_flag,
		                       case1_t5.company_cd,
		                       case1_t5.crm_contact_cd,
		                       case1_t5.delete_flag,
		                       case1_t5.start_date,
		                       case1_t5.end_date,
		                       case1_t2.locale_id,
		                       case1_t2.start_date AS t2_start_date,
		                       case1_t2.end_date AS t2_end_date,
		                       case1_t2.user_name,
		                       case1_t5.user_cd
		                       FROM crm_cc_contact_charge_user case1_t5 
		                       LEFT OUTER JOIN  crm_cc_user_cmn case1_t2    --ユーザ_共通
		                       ON  case1_t5.user_cd = case1_t2.user_cd
		                       AND case1_t2.start_date <= /*systemDate*/      --開始日
		                       AND case1_t2.end_date > /*systemDate*/         --終了日
                               AND case1_t2.delete_flag = '0'                 --削除フラグ
                               AND case1_t2.locale_id = /*localeId*/          --コンテキスト.ロケールID
                               ) case1_t3    --CRMコンタクト担当者
		    ON  case1_t1.company_cd = case1_t3.company_cd 
		        AND  case1_t1.crm_contact_cd = case1_t3.crm_contact_cd
		        AND  case1_t3.main_charge_flag = '1'            --CRMコンタクト担当者.主担当フラグ
		        AND  case1_t3.start_date <= /*systemDate*/      --開始日
		        AND  case1_t3.end_date > /*systemDate*/         --終了日
		        AND  case1_t3.delete_flag = '0'
		WHERE
		    case1_t1.locale_id = /*localeId*/                         --コンテキスト.ロケールID
		AND
		    case1_t1.company_cd = /*companyCd*/                       --コンテキスト.会社コード
		    /*IF crmContactCd!= null && crmContactCd.length() > 0*/
		AND
		    case1_t1.crm_contact_cd LIKE /*crmContactCd*/  ESCAPE '|'              --コンタクトコード
		    /*END*/
		    /*IF crmContactName!= null && crmContactName.length() > 0*/
		AND 
		    case1_t1.crm_contact_name LIKE /*crmContactName*/  ESCAPE '|'          --コンタクト名
		    /*END*/
		    /*IF crmContactShortName!= null && crmContactShortName.length() > 0*/
		AND 
		    case1_t1.crm_contact_short_name LIKE /*crmContactShortName*/ ESCAPE '|' --コンタクト略称
		    /*END*/
		    /*IF crmContactNameKana!= null && crmContactNameKana.length() > 0*/
		AND 
		    case1_t1.crm_contact_name_kana LIKE /*crmContactNameKana*/  ESCAPE '|'  --コンタクト名（カナ）
		    /*END*/
		    /*IF crmContactSearchName!= null && crmContactSearchName.length() > 0*/
		AND 
		    case1_t1.crm_contact_search_name LIKE /*crmContactSearchName*/  ESCAPE '|'  --コンタクト検索名
		    /*END*/
		    /*IF crmContactType!= null && crmContactType.length() > 0*/
		AND 
		    case1_t1.crm_contact_type LIKE /*crmContactType*/ ESCAPE '|'               --タイプ
		    /*END*/
		    /*IF belong!= null && belong.length() > 0*/
		AND 
		    case1_t1.belong LIKE /*belong*/ ESCAPE '|'                         --所属
		    /*END*/
		    /*IF post!= null && post.length() > 0*/
		AND 
		    case1_t1.post LIKE /*post*/  ESCAPE '|'                             --役職
		    /*END*/
		    /*IF telephoneNumber!= null && telephoneNumber.length() > 0*/
		AND 
		    case1_t1.telephone_number LIKE /*telephoneNumber*/  ESCAPE '|'      --電話番号
		    /*END*/
		    /*IF keyPerson!= null && keyPerson.length() > 0*/
		AND 
		    case1_t1.key_person LIKE /*keyPerson*/ ESCAPE '|'                   --キーパーソン
		    /*END*/
		    /*IF emailAddress!= null && emailAddress.length() > 0*/
		AND 
		    (
		    case1_t1.email_address1 LIKE /*emailAddress*/  ESCAPE '|'           --メールアドレス1
		    OR case1_t1.email_address2 LIKE /*emailAddress*/ ESCAPE '|'         --メールアドレス2 
		    )
		    /*END*/
		AND 
		    case1_t1.delete_flag = '0'           --コンタクト.削除フラグ

		/*IF "0".equals(hasPrivilegeFlag)*/
		AND 
		(
		   (  EXISTS (
		            SELECT
		                case1_t14.crm_contact_cd
		            FROM
		                crm_cc_contact_charge_user case1_t14
		            WHERE
		                case1_t1.crm_contact_cd = case1_t14.crm_contact_cd
		            AND
		                case1_t14.company_cd = /*companyCd*/            --会社コード
		            AND
		                case1_t14.user_cd = /*userCode*/                --ユーザコンテキスト.ユーザコード
		            AND
		                case1_t14.start_date <= /*systemDate*/
		            AND
		                case1_t14.end_date > /*systemDate*/
		            AND
		                case1_t14.delete_flag = '0'
		            )
		    )
		    /*IF "1".equals(hasAceessCustomerFlag)*/
		  OR
		    (  EXISTS 
		            (
		            SELECT
		                case1_t18.crm_contact_cd
		            FROM
		                crm_cc_contact_charge_dept case1_t18
		            WHERE
		                case1_t1.crm_contact_cd = case1_t18.crm_contact_cd
		            AND
		                case1_t18.company_cd = /*companyCd*/                      --会社コード
		            /*IF updepartmentCdListToArray != null*/
		            AND 
		                case1_t18.department_cd IN /*updepartmentCdListToArray*/('aaa','bbb')  --List<担当組織コード>
		            /*END*/
		            AND
		                case1_t18.start_date <= /*systemDate*/
		            AND
		                case1_t18.end_date > /*systemDate*/
		            AND
		                case1_t18.delete_flag = '0'
		            )
		      )
		      /*END*/
		)
		/*END*/
		/*IF "1".equals(hasPrivilegeFlag)*/
		AND 
        (
            EXISTS 
            (
                SELECT
                    case1_privilege.crm_contact_cd
                FROM
                    crm_cc_contact_charge_dept case1_privilege
                WHERE
                    case1_t1.crm_contact_cd = case1_privilege.crm_contact_cd
                AND
                    case1_privilege.company_cd = /*companyCd*/                      --会社コード
                AND
                    case1_privilege.start_date <= /*systemDate*/
                AND
                    case1_privilege.end_date > /*systemDate*/
                AND
                    case1_privilege.delete_flag = '0'
            )
        )
		/*END*/
		AND
		    case1_t1.start_date <= /*systemDate*/
		AND
		    case1_t1.end_date > /*systemDate*/
		    
    UNION
    
        SELECT
            case2_t1.crm_contact_name AS crm_contact_name,
            case2_t1.belong AS belong,
            case2_t1.sex AS sex,
            case2_t1.post AS post,
            case2_t1.key_person AS key_person,
            case2_t1.crm_contact_cd AS crm_contact_cd,
            case2_t1.email_address1 AS email_address1,
            case2_t1.telephone_number AS telephone_number,
            '' AS user_name,
            case2_t1.crm_contact_name_kana AS crm_contact_name_kana,
            '' AS user_cd
        FROM
            crm_cc_contact case2_t1                                   --CRMコンタクト
        WHERE
            case2_t1.locale_id = /*localeId*/                         --コンテキスト.ロケールID
        AND
            case2_t1.company_cd = /*companyCd*/                       --コンテキスト.会社コード
            /*IF crmContactCd!= null && crmContactCd.length() > 0*/
        AND
            case2_t1.crm_contact_cd LIKE /*crmContactCd*/  ESCAPE '|'                   --コンタクトコード
            /*END*/
            /*IF crmContactName!= null && crmContactName.length() > 0*/
        AND 
            case2_t1.crm_contact_name LIKE /*crmContactName*/  ESCAPE '|'              --コンタクト名
            /*END*/
            /*IF crmContactShortName!= null && crmContactShortName.length() > 0*/
        AND 
            case2_t1.crm_contact_short_name LIKE /*crmContactShortName*/ ESCAPE '|'          --コンタクト略称
            /*END*/
            /*IF crmContactNameKana!= null && crmContactNameKana.length() > 0*/
        AND 
            case2_t1.crm_contact_name_kana LIKE /*crmContactNameKana*/  ESCAPE '|'           --コンタクト名（カナ）
            /*END*/
            /*IF crmContactSearchName!= null && crmContactSearchName.length() > 0*/
        AND 
            case2_t1.crm_contact_search_name LIKE /*crmContactSearchName*/  ESCAPE '|'       --コンタクト検索名
            /*END*/
            /*IF crmContactType!= null && crmContactType.length() > 0*/
        AND 
            case2_t1.crm_contact_type LIKE /*crmContactType*/ ESCAPE '|'                     --タイプ
            /*END*/
            /*IF belong!= null && belong.length() > 0*/
        AND 
            case2_t1.belong LIKE /*belong*/ ESCAPE '|'                         --所属
            /*END*/
            /*IF post!= null && post.length() > 0*/
        AND 
            case2_t1.post LIKE /*post*/  ESCAPE '|'                             --役職
            /*END*/
            /*IF telephoneNumber!= null && telephoneNumber.length() > 0*/
        AND 
            case2_t1.telephone_number LIKE /*telephoneNumber*/  ESCAPE '|'      --電話番号
            /*END*/
            /*IF keyPerson!= null && keyPerson.length() > 0*/
        AND 
            case2_t1.key_person LIKE /*keyPerson*/ ESCAPE '|'                   --キーパーソン
            /*END*/
            /*IF emailAddress!= null && emailAddress.length() > 0*/
        AND 
            (
            case2_t1.email_address1 LIKE /*emailAddress*/  ESCAPE '|'           --メールアドレス1
            OR case2_t1.email_address2 LIKE /*emailAddress*/ ESCAPE '|'         --メールアドレス2 
            )
            /*END*/
        AND 
            case2_t1.delete_flag = '0'           --コンタクト.削除フラグ

        /*IF "0".equals(hasPrivilegeFlag)*/
        AND 
        (
            /*IF "0".equals(hasAceessCustomerFlag)*/
           (NOT EXISTS (
                SELECT
                    case2_t12.crm_contact_cd
                FROM
                    crm_cc_contact_charge_user case2_t12
                WHERE
                    case2_t1.crm_contact_cd = case2_t12.crm_contact_cd
                AND
                    case2_t12.company_cd = /*companyCd*/            --会社コード
                AND
                    case2_t12.start_date <= /*systemDate*/
                AND
                    case2_t12.end_date > /*systemDate*/
                AND
                    case2_t12.delete_flag = '0'
                )
            )
            /*END*/
            /*IF "1".equals(hasAceessCustomerFlag)*/
            (NOT EXISTS 
                    (
                    SELECT
                        case2_t16.crm_contact_cd
                    FROM
                        crm_cc_contact_charge_dept case2_t16
                    WHERE
                        case2_t1.crm_contact_cd = case2_t16.crm_contact_cd
                    AND
                        case2_t16.company_cd = /*companyCd*/                 --会社コード
                    AND
                        case2_t16.start_date <= /*systemDate*/
                    AND
                        case2_t16.end_date > /*systemDate*/
                    AND
                       case2_t16.delete_flag = '0'
                    )
              )
              /*END*/
        )
        /*END*/
        /*IF "1".equals(hasPrivilegeFlag)*/
        AND 
        (
            NOT EXISTS 
            (
                SELECT
                    case2_privilege.crm_contact_cd
                FROM
                    crm_cc_contact_charge_dept case2_privilege
                WHERE
                    case2_t1.crm_contact_cd = case2_privilege.crm_contact_cd
                AND
                    case2_privilege.company_cd = /*companyCd*/                      --会社コード
                AND
                    case2_privilege.start_date <= /*systemDate*/
                AND
                    case2_privilege.end_date > /*systemDate*/
                AND
                    case2_privilege.delete_flag = '0'
            )
        )
        /*END*/
        AND
            case2_t1.start_date <= /*systemDate*/
        AND
            case2_t1.end_date > /*systemDate*/

) tb_case
    
/*IF !countQuery*/
  /*IF order*/
ORDER BY 
    /*$orderByClause*/
  /*END*/
/*END*/  
