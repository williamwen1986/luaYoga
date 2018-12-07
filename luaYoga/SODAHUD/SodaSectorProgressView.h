//
//  SodaSectorProgressView.h
//  BaseUI
//
//  Created by boob on 2018/2/27.
//

#import <UIKit/UIKit.h>
/** * 扇形 */
@interface SodaSectorProgressView : UIButton

@property(assign,nonatomic)CGFloat progress;

@property (nonatomic, strong) UIColor * backgroundTintColor;

@property (nonatomic, assign) CGFloat lineWidth;

- (void)setProgress:(CGFloat)progress animated:(BOOL)animated;
@end
