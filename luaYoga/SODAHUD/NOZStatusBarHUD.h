//
//  NOZStatusBarHUD.h
//  AFNetworking
//
//  Created by wangdecai on 2018/7/18.
//

#import <Foundation/Foundation.h>

@interface NOZStatusBarHUD : NSObject

/**
 * 显示图片+文字信息H
 */
//+ (void)showImage:(UIImage *)image text:(NSString *)text;
//+ (void)showTipImage:(UIImage *)image text:(NSString *)text;
/**
 * 显示成功信息,未给到默认图
 */
+ (void)showSuccess:(NSString *)text;
/**
 * 显示失败信息，未给到默认图
 */
+ (void)showError:(NSString *)text;
/**
 * 显示正在处理的信息，未给到默认图
 */
+ (void)showLoading:(NSString *)text;

/**
 * 显示普通信息
 */
+ (void)showText:(NSString *)text;

/**
 * 隐藏
 */
+ (void)hide;

@end
