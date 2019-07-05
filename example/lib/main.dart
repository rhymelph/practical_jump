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
                PracticalJump.jumpToAlipay('fkx09281vnqgwcfsu3hzo2d');
              },
            ),
            ListTile(
              title: Text('QQ临时会话'),
              onTap: () {
                PracticalJump.jumpToQQChart('2211476137');
              },
            ),
          ],
        ),
      ),
    );
  }
}
