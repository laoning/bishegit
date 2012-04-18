/*
 * ライセンスチェック
 */

// パッケージ
package com.biz_integral.crm.cc.domain.context.verify;

// クラスのインポート
import java.io.IOException;
import java.io.OptionalDataException;
import java.net.UnknownHostException;
import jp.co.intra_mart.common.aid.jdk.java.net.Localhost;
import jp.co.intra_mart.system.secure.AppendProduct;
import jp.co.intra_mart.system.secure.AppendProductInitializer;
import jp.co.intra_mart.system.secure.License;
import jp.co.intra_mart.foundation.security.SystemManager;
import jp.co.intra_mart.foundation.security.exception.AccessSecurityException;
import jp.co.intra_mart.foundation.security.license.LicenseManager;
import jp.co.intra_mart.foundation.service.client.information.ExternalDirectory;
import jp.co.intra_mart.foundation.service.client.information.TreasureFile;
import jp.co.intra_mart.framework.system.exception.SystemException;


public final class SFALicenseController implements AppendProductInitializer{
	// クラス変数
	private static boolean certificate = false;

	/**
	 * ゼロパラメータ・コンストラクタ
	 */
	public SFALicenseController(){
		super();
	}

	/**
	 * ライセンスの有無をチェックします。
	 * 試用版ライセンスであり、試用期限が過ぎている場合に例外がスローされます。
	 * @throws IllegalStateException ライセンス違反の場合にスローされます
	 */
	public static void attestation() throws IllegalStateException{
		if(! isLicense()){
			// ライセンス違反
			throw new IllegalStateException("Violation of a license.");
		}
	}

	/**
	 * ライセンスが許可されているかどうかチェックするためのフラグを返します。
	 * @return ライセンスが許可されている場合 true。そうでない場合 false。
	 */
	public static boolean isLicense(){
		return certificate;
	}

	/**
	 * ライセンスのフラグを設定。
	 * ライセンスを無視して動作させるためのテスト環境構築用。
	 */
	public static void setLicense(){
		certificate = true;
	}

	/**
	 * ライセンスのフラグを削除。
	 * {@link #setLicense()} と対を成すメソッド。
	 */
	public static void unsetLicense(){
		certificate = false;
	}

	/**
	 * ライセンスと動作環境をチェックして状態フラグをセットします。
	 * @param appendProduct このプロダクトのライセンス情報
	 */
	public void initialize(AppendProduct appendProduct){
		// ライセンスの使用期限チェック
		if(! appendProduct.isExpired()){
			// ライセンス情報の取得
			License productLisence = appendProduct.getLicense();

			// 試用版のチェック
			if(! productLisence.isTrialLicense()){
				// 時限ライセンスのチェック
				if(productLisence.isTimeLimitLicense()){
					/* 時限ライセンス→ライセンス許可 */
					certificate = true;				// 許可
				}
				else{
					/* 時限ライセンス→ライセンス違反 */
					certificate = false;	// 不許可
				}
			}
			else{
				/* 試用版 */
				certificate = true;					// 許可
			}
		}
		else{
			/* 試用期限切れ */
			certificate = false;					// 不許可
		}
	}

}

/* End of File */