#import <Foundation/Foundation.h>
#import <Flutter/Flutter.h>

@interface CompressFileHandler : NSObject
- (void)handleMethodCall:(FlutterMethodCall *)call result:(FlutterResult)result;

- (void)handleCompressFileToFile:(FlutterMethodCall *)call result:(FlutterResult)result;
@end
