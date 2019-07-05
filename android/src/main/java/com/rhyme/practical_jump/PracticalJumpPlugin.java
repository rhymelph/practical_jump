package com.rhyme.practical_jump;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import java.net.URISyntaxException;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/**
 * PracticalJumpPlugin
 */
public class PracticalJumpPlugin implements MethodCallHandler {
    private static Registrar registrar;

    /**
     * Plugin registration.
     */
    public static void registerWith(Registrar registrar) {
        PracticalJumpPlugin.registrar = registrar;
        final MethodChannel channel = new MethodChannel(registrar.messenger(), "practical_jump");
        channel.setMethodCallHandler(new PracticalJumpPlugin());
    }

    private static final String INTENT_URL_FORMAT = "intent://platformapi/startapp?saId=10000007&" +
            "clientVersion=3.7.0.0718&qrcode=https%3A%2F%2Fqr.alipay.com%2F{payCode}%3F_s" +
            "%3Dweb-other&_t=1472443966571#Intent;" +
            "scheme=alipayqr;package=com.eg.android.AlipayGphone;end";

    @Override
    public void onMethodCall(MethodCall call, Result result) {
        if (call.method.equals("jumpToAlipay")) {
            jumpToAlipay(call, result);
        } else if (call.method.equals("jumpToQQChart")) {
            jumpToQQChart(call, result);
        } else if (call.method.equals("jumpToQQGroup")) {
            jumpToQQGroup(call, result);
        } else if (call.method.equals("jumpToWeChatScan")) {
            jumpToWeChatScan(call, result);
        } else {
            result.notImplemented();
        }
    }

    private void jumpToWeChatScan(MethodCall call, Result result) {
        boolean isSuccess = toWeChatScanDirect(PracticalJumpPlugin.registrar.activity());
        result.success(isSuccess);
    }



    private void jumpToQQChart(MethodCall call, Result result) {
        String qqNumber = (String) call.arguments;
        boolean isSuccess = QQTalk(PracticalJumpPlugin.registrar.activity(), qqNumber);
        result.success(isSuccess);
    }

    private void jumpToQQGroup(MethodCall call, Result result) {
        String qqGroupKey = (String) call.arguments;
        boolean isSuccess = joinQQGroup(PracticalJumpPlugin.registrar.activity(), qqGroupKey);
        result.success(isSuccess);
    }

    private static void jumpToAlipay(MethodCall call, Result result) {
        String code = (String) call.arguments;
        boolean isSuccess = PayAlipay(PracticalJumpPlugin.registrar.activity(), INTENT_URL_FORMAT.replace("{payCode}", String.valueOf(code)));
        result.success(isSuccess);
    }

    public static boolean openSinaWeiBoByNickName(Activity activity, String nickName) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setData(Uri.parse("sinaweibo://messagelist?nick=" + (nickName)));
        try {
            activity.startActivity(intent);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @SuppressLint("WrongConstant")
    private static boolean toWeChatScanDirect(Activity activity) {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI"));
            intent.putExtra("LauncherUI.From.Scaner.Shortcut", true);
            intent.setFlags(335544320);
            intent.setAction("android.intent.action.VIEW");
            activity.startActivity(intent);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean QQTalk(Activity activity, String QQ) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("mqqwpa://im/chat?chat_type=wpa&uin=" + QQ));
        try {
            activity.startActivity(intent);
            return true;
        } catch (Exception e) {
            // 未安装手Q或安装的版本不支持
            return false;
        }
    }

    private boolean joinQQGroup(Activity activity, String key) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3D" + key));
        // 此Flag可根据具体产品需要自定义，如设置，则在加群界面按返回，返回手Q主界面，不设置，按返回会返回到呼起产品界面    //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        try {
            activity.startActivity(intent);
            return true;
        } catch (Exception e) {
            // 未安装手Q或安装的版本不支持
            return false;
        }
    }

    @TargetApi(Build.VERSION_CODES.DONUT)
    private static boolean PayAlipay(Activity activity, String intentFullUrl) {
        try {
            Intent intent = Intent.parseUri(intentFullUrl, Intent.URI_INTENT_SCHEME);
            activity.startActivity(intent);
            return true;
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return false;
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
