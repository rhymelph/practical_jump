import 'package:flutter/material.dart';

import 'package:flutter/widgets.dart';
import 'package:practical_jump/practical_jump.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: ListView(
          children: <Widget>[
            ListTile(
              title: Text('支付宝支付'),
              onTap: () {
                PracticalJump.jumpToAlipayPay('fkx09281vnqgwcfsu3hzo2d');
              },
            ),
            ListTile(
              title: Text('支付宝二维码扫一扫'),
              onTap: () {
                PracticalJump.jumpToAlipayScan();
              },
            ),
            ListTile(
              title: Text('QQ临时会话'),
              onTap: () {
                PracticalJump.jumpToQQChart('708959817');
              },
            ),
            ListTile(
              title: Text('QQ群入群或会话'),
              onTap: () {
                PracticalJump.jumpToQQGroup('rHLb4DsCDGs4zx_tyuO2vthmRSQz0QHX');
              },
            ),
            ListTile(
              title: Text('微信二维码扫一扫'),
              onTap: () {
                PracticalJump.jumpToWeChatScan();
              },
            ),
            ListTile(
              title: Text('微信分享文本给朋友'),
              onTap: () {
                PracticalJump.jumpToWeChatFriendShare(
                    content:'分享的文本');
              },
            ),
            ListTile(
              title: Text('微信付款码'),
              onTap: () {
                PracticalJump.jumpToWeChatWalletCoinDirect();
              },
            ),
          ],
        ),
      ),
    );
  }
}
