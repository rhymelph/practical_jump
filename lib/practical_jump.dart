import 'dart:async';

import 'package:flutter/services.dart';

class PracticalJump {
  static const MethodChannel _channel =
      const MethodChannel('practical_jump');

  /// [code] 这个code获取方式
  /// - 将支付宝收款二维码保存到相册
  /// - 通过微信扫一扫上面保存的二维码
  /// - 得到的网址大概为https://qr.alipay.com/fkx09281vnqgwcfsu3hzo2d?t=1562234400582
  /// - 其中的fkx09281vnqgwcfsu3hzo2d 就是我们需要的code
 static Future<bool> jumpToAlipay(String code){
    return _channel.invokeMethod('jumpToAlipay',code);
  }

  /// 指定你要跳转的QQ号码
  static Future<bool> jumpToQQChart(String qqNumber){
    return _channel.invokeMethod('jumpToQQChart',qqNumber);
  }

  /// [qqGroupKey] 这个key获取方法
  /// - 登录[网址](https://qun.qq.com/join.html)
  /// - 选择您的群
  /// - 选择Android代码
  static Future<bool> jumpToQQGroup(String qqGroupKey){
    return _channel.invokeMethod('jumpToQQGroup',qqGroupKey);
  }

  /// 微信二维码扫一扫界面
  static Future<bool> jumpToWeChatScan(){
    return _channel.invokeMethod('jumpToWeChatScan');
  }
}
