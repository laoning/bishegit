/*
 * ライセンスチェック
 */

// パッケージ
package com.biz_integral.crm.cc.domain.logic.impl;

// クラスのインポート
import javax.annotation.Resource;

import jp.co.intra_mart.framework.system.exception.SystemException;

import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.context.listener.ProcessLifecycleListener;
import com.biz_integral.foundation.core.context.listener.ProcessLifecycleListenerSupport;

public final class SFALicenseCheckLogicImpl extends
        ProcessLifecycleListenerSupport implements ProcessLifecycleListener {
    private static final String SFA_APPLICATION_ID = "ZT";

    @Resource
    protected ContextContainer contextContainer;

    /**
     * ゼロパラメータ・コンストラクタ
     */
    public SFALicenseCheckLogicImpl() {
        super();
    }

    /**
     * アプリケーションロールの保持判定.<BR>
     * アプリケーションロールを保持しているか否かを判断する。
     * 
     * @return チェック結果（保持：true、保持していない：false）
     * 
     * @exception SystemException
     *                アプリケーションロールの保持判定に失敗
     */
    public void onStart() {
        // LicenseManager licenseManagerObject;
        // UserContext ucx;
        // String loginGpId;
        // String userId;
        //
        // ucx = contextContainer.getUserContext();
        // loginGpId = ucx.getLoginGroupID();
        // userId = ucx.getUserID();
        // if (userId != null && userId.compareTo("crm_sfa_batch_user") == 0) {
        // return;
        // }
        // try {
        // licenseManagerObject = new LicenseManager(loginGpId);
        //
        // if (!licenseManagerObject.isRegisteredApplicationLicenseToAccount(
        // userId,
        // SFA_APPLICATION_ID)) {
        // System.out.println("CRM/SFA Application License Error:user_id="
        // + userId);
        // ValidationResults results = new ValidationResults();
        // results.add(new ValidationResult(MessageBuilder.create(
        // "You have no license!").toMessage()));
        // throw new ValidationException(results);
        // }
        // } catch (AccessSecurityException ase) {
        // System.err.println(ase);
        // }
    }

    /*
     * public void beforeFinish(){ }
     */
    public String getApplicationID() {
        return "crm";
    }
}

/* End of File */