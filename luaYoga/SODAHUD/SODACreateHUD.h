//
//  SODACreateHUD.h
//  BaseUI
//
//  Created by boob on 2018/4/9.
//

#import <UIKit/UIKit.h>
#import "SodaSectorProgressView.h"
#import "SodaCirleProgressView.h"

@interface SODACreateHUD : UIView

+(SodaCirleProgressView *)circleProgressBar;


+(SodaSectorProgressView *)sectorProgressBar;


+(UIImageView *)imageBar:(UIImage *)image;


+(SodaCirleProgressView *)roundProgressbar;

+(UIActivityIndicatorView *)indicatorView;
@end
