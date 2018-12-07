//
//  SODACreateHUD.m
//  BaseUI
//
//  Created by boob on 2018/4/9.
//

#import "SODACreateHUD.h"

@implementation SODACreateHUD
 
+(SodaCirleProgressView *)circleProgressBar{
    
    SodaCirleProgressView *_bar = [[SodaCirleProgressView alloc] initWithFrame:CGRectMake(0, 0, 37, 37)];
    _bar.showTitle = YES;
    _bar.backgroundColor = [UIColor clearColor];
    _bar.progressBarWidth = 3;
    _bar.progressBarProgressColor =  [UIColor colorWithWhite:1 alpha:1];
    _bar.progressBarTrackColor = [UIColor colorWithWhite:1 alpha:0.5];
    return _bar;
}

+(SodaSectorProgressView *)sectorProgressBar{
    
    SodaSectorProgressView *_bar = [[SodaSectorProgressView alloc] initWithFrame:CGRectMake(0, 0, 37, 37)];
    return _bar;
}

+(UIImageView *)imageBar:(UIImage *)image{
    
    UIImageView * view = [[UIImageView alloc] initWithImage:image]; 
    return view;
}

+(SodaCirleProgressView *)roundProgressbar{
    SodaCirleProgressView * _bar = [[SodaCirleProgressView alloc] initWithFrame:CGRectMake(0, 0, 37, 37)];
    _bar.showTitle = NO;
    _bar.backgroundColor = [UIColor clearColor];
    _bar.progressBarWidth = 3;
    _bar.progressBarProgressColor =  [UIColor colorWithWhite:1 alpha:1];
    _bar.progressBarTrackColor = [UIColor colorWithWhite:1 alpha:0.5];
    return _bar;
}

+(UIActivityIndicatorView *)indicatorView{
    
    UIActivityIndicatorView * view = [[UIActivityIndicatorView alloc] initWithFrame:CGRectMake(0, 0, 37, 37 )];
    view.activityIndicatorViewStyle = UIActivityIndicatorViewStyleWhiteLarge;
    [view startAnimating];
    return view;
}


@end
