package com.rhyme.practical_jump;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ActivityNotFoundException;
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
        }else{
            result.notImplemented();
        }
    }

    private void jumpToQQChart(MethodCall call, Result result) {
        String qqNumber = (String) call.arguments;
        boolean isSuccess = QQTalk(PracticalJumpPlugin.registrar.activity(), qqNumber);
        result.success(isSuccess);
    }

    private static void jumpToAlipay(MethodCall call, Result result) {
        String code = (String) call.arguments;
        boolean isSuccess = PayAlipay(PracticalJumpPlugin.registrar.activity(), INTENT_URL_FORMAT.replace("{payCode}", String.valueOf(code)));
        result.success(isSuccess);
    }

    private static boolean QQTalk(Activity activity,String QQ) {
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
