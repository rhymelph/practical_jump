# practical_jump（实用跳转）

> 目前该插件只支持Android系统

- 跳转到支付宝指定账户进行转账

```dart
  /// [code] 这个code获取方式
  /// - 将支付宝收款二维码保存到相册
  /// - 通过微信扫一扫上面保存的二维码
  /// - 得到的网址大概为https://qr.alipay.com/fkx09281vnqgwcfsu3hzo2d?t=1562234400582
  /// - 其中的fkx09281vnqgwcfsu3hzo2d 就是我们需要的code
  PracticalJump.jumpToAlipay(String code);

```

- 跳转到QQ进行临时会话

```dart

  /// 指定你要跳转的QQ号码
  PracticalJump.jumpToQQChart(String qqNumber);
```