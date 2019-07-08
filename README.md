# practical_jump（实用跳转）

> 目前该插件只支持Android系统

## 使用
添加pub

```yaml
dependencies:
  practical_jump:
    git: https://github.com/rhymelph/practical_jump.git
```
导包

```dart
import 'package:practical_jump/practical_jump.dart';
```

### 支付宝

- 跳转到支付宝指定账户进行转账

```dart
  /// params: [code] 这个code获取方式
  /// - 将支付宝收款二维码保存到相册
  /// - 通过微信扫一扫上面保存的二维码
  /// - 得到的网址大概为https://qr.alipay.com/fkx09281vnqgwcfsu3hzo2d?t=1562234400582
  /// - 其中的fkx09281vnqgwcfsu3hzo2d 就是我们需要的code
  /// result: 返回是否跳转成功
  PracticalJump.jumpToAlipayPay(String code);

```
- 跳转到支付宝扫一扫

```dart
  /// result: 返回是否跳转成功
  PracticalJump.jumpToAlipayScan();
```
### QQ

- 跳转到QQ进行临时会话

```dart

  /// params: [qqNumber] 指定你要跳转的QQ号码
  /// result：返回是否跳转成功
  PracticalJump.jumpToQQChart(String qqNumber);
```

- 跳转到QQ群进行入群或会话

```dart

  /// params: [qqGroupKey] 这个key获取方式
  /// - 登录[网址](https://qun.qq.com/join.html)
  /// - 选择您的群
  /// - 选择Android代码
  /// result：返回是否跳转成功
  PracticalJump.jumpToQQGroup(String qqGroupKey);
```

### 微信

- 跳转到微信二维码扫一扫界面

```dart

  PracticalJump.jumpToWeChatScan();
```