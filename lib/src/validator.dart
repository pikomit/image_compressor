import 'package:flutter/services.dart';

import 'compress_format.dart';

class Validator {
  final MethodChannel channel;
  Validator(this.channel);

  bool ignoreCheckExtName = false;
  bool ignoreCheckSupportPlatform = false;

  void checkFileNameAndFormat(String name, CompressFormat format) {
    if (ignoreCheckExtName) {
      return;
    }
    name = name.toLowerCase();
    if (format == CompressFormat.jpeg) {
      assert((name.endsWith(".jpg") || name.endsWith(".jpeg")), "The jpeg format name must end with jpg or jpeg.");
    } else if (format == CompressFormat.png) {
      assert(name.endsWith(".png"), "The jpeg format name must end with png.");
    }
  }
}
