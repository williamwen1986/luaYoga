//
//  UIViewController+HBHUD.m
//  pengmi
//
//  Created by BOOB on 18/1/3.
//  Copyright (c) 2018年 YY.Inc All rights reserved.
//
#import "NSObject+SODAHUD.h"
#import <objc/runtime.h>
#define KEY_OBJECT_HUD @"UIViewController.HBHUD"
#define KEY_HUD @"soda_hud_key"

static long tag_view_hud = 0xe3e3e3;

///**
// * 扇形
// */
//@interface HBSectorProgressView : UIButton
//@property(assign,nonatomic)CGFloat progress;
//@end


@implementation NSObject(HBHUD)
static char HBHUDOperationKey;

//#define HBHUDBGCOLOR [UIColor whiteColor]
//
///**
// * 显示扇形进度条
// */
//-(MBProgressHUD *)presentSectorHud:(NSString *)text progress:(CGFloat)progress{
//
//    UIView * superview = [self HUDSuperView];
//    if(!superview) return nil;
//    MBProgressHUD *hud = [superview viewWithTag:tag_view_hud];
//    if (!hud) {
//        hud = [MBProgressHUD showHUDAddedTo:superview animated:YES];
//        hud.tag = tag_view_hud;
//        hud.mode = MBProgressHUDModeCustomView;
//        [self setHUD:hud];
//    }
//    hud.bezelView.backgroundColor = HBHUDBGCOLOR;
//    HBSectorProgressView * sectorview = hud.customView;
//    if (!sectorview || ![[sectorview class] isSubclassOfClass:[HBSectorProgressView class]]) {
//        sectorview = [[HBSectorProgressView alloc] initWithFrame:CGRectMake(0, 0, 50, 50)];
//    }
//    hud.customView = sectorview;
//    sectorview.progress = progress;
//    hud.label.text = text;
//    if (progress>=0.999) {
//        dispatch_async(dispatch_get_main_queue(), ^{
//            [hud hideAnimated:YES];
//        });
//    }
//    return hud;
//}
//
//-(void)soda_hidehud{
//    MBProgressHUD *hud = [self obj_MBHUD];
//   if(hud) hud.hidden = YES;
//}
///**
// * 显示扇形进度条,含有取消按钮
// */
//-(MBProgressHUD *)presentSectorHud:(NSString *)text
//                            cancel:(NSString *)cancelTitle
//                          progress:(CGFloat)progress{
//
//    UIView * superview = [self HUDSuperView];
//    if(!superview) return nil;
//    MBProgressHUD *hud = [superview viewWithTag:tag_view_hud];
//    if (!hud) {
//        hud = [MBProgressHUD showHUDAddedTo:superview animated:YES];
//        hud.tag = tag_view_hud;
//        hud.mode = MBProgressHUDModeCustomView;
//        [self setHUD:hud];
//
//        if (cancelTitle) {
//            UIButton * btn = [UIButton buttonWithType:UIButtonTypeCustom];
//            btn.backgroundColor = [UIColor colorWithWhite:0 alpha:0.2];
//            [btn setTitle:cancelTitle forState: UIControlStateNormal];
//            btn.titleLabel.font = [UIFont systemFontOfSize:13];
//            btn.frame = CGRectMake(0, 0, hud.bounds.size.width, 20);
//            [hud addSubview:btn];
//            [btn addTarget:self action:@selector(soda_hidehud) forControlEvents:UIControlEventTouchUpInside];
//        }
//    }
//    hud.bezelView.backgroundColor = HBHUDBGCOLOR;
//    HBSectorProgressView * sectorview = hud.customView;
//    if (!sectorview || ![[sectorview class] isSubclassOfClass:[HBSectorProgressView class]]) {
//        sectorview = [[HBSectorProgressView alloc] initWithFrame:CGRectMake(0, 0, 50, 50)];
//    }
//    hud.customView = sectorview;
//    sectorview.progress = progress;
//    hud.label.text = text;
//    if (progress>=0.999) {
//        dispatch_async(dispatch_get_main_queue(), ^{
//            [hud hideAnimated:YES];
//        });
//    }
//    return hud;
//}
//
///**
// * 显示圆形进度条
// */
//-(void)presentCircleHud:(NSString *)text progress:(CGFloat)progress{
//
//    UIView * superview = [self HUDSuperView];
//    if(!superview) return ;
//    MBProgressHUD *hud = [superview viewWithTag:tag_view_hud];
//    if (!hud) {
//        hud = [MBProgressHUD showHUDAddedTo:superview animated:YES];
//        hud.tag = tag_view_hud;
//    }
//    hud.bezelView.backgroundColor = HBHUDBGCOLOR ;
//    hud.mode = MBProgressHUDModeAnnularDeterminate;
//    hud.label.text = text;
//    hud.progress = progress;
//    if (progress>=0.999) {
//        dispatch_async(dispatch_get_main_queue(), ^{
//            [hud hideAnimated:YES];
//        });
//    }
//}
//
///**
// * 图片提示框
// */
//-(MBProgressHUD *)presentImageHud:(NSString *)imgName text:(NSString *)text{
//    UIView * superview = [self HUDSuperView];
//    if(!superview) return nil;
//    MBProgressHUD *hud = [superview viewWithTag:tag_view_hud];
//    if (!hud) {
//        hud = [MBProgressHUD showHUDAddedTo:superview animated:YES];
//        hud.tag = tag_view_hud;
//    }
//    hud.mode = MBProgressHUDModeCustomView;
//    hud.bezelView.backgroundColor = HBHUDBGCOLOR ;
//    UIImage *image = [UIImage imageNamed:imgName];
//    hud.customView = [[UIImageView alloc] initWithImage:image];
//    hud.label.text = text;
//    dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(2 * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
//        [hud hide:YES];
//    });
//    return hud;
//}
//
///**
// * 自定义等待框
// */
//-(MBProgressHUD *)presentCustomViewHud:(UIView *)customView text:(NSString *)text{
//    UIView * superview = [self HUDSuperView];
//    if(!superview) return nil;
//    MBProgressHUD *hud = [superview viewWithTag:tag_view_hud];
//    if (!hud) {
//        hud = [MBProgressHUD showHUDAddedTo:superview animated:YES];
//        hud.tag = tag_view_hud;
//        hud.mode = MBProgressHUDModeCustomView;
//    }
//    hud.bezelView.backgroundColor = HBHUDBGCOLOR ;
//    hud.customView = customView;
//    //    hud.square = YES;
//    hud.label.text = text;
//    return hud;
//}
//
///**
// * 显示条形进度条
// */
//-(MBProgressHUD *)presentbarhud:(NSString *)text progress:(CGFloat)progress{
//
//    UIView * superview = [self HUDSuperView];
//    if(!superview) return nil;
//    MBProgressHUD *hud = [superview viewWithTag:tag_view_hud];
//    if (!hud) {
//        hud = [MBProgressHUD showHUDAddedTo:superview animated:YES];
//        hud.tag = tag_view_hud;
//    }
//    hud.bezelView.backgroundColor = HBHUDBGCOLOR ;
//    hud.mode = MBProgressHUDModeDeterminateHorizontalBar;
//    hud.label.text = text;
//    hud.progress = progress;
//    if (progress>=0.999) {
//        dispatch_async(dispatch_get_main_queue(), ^{
//            [hud hideAnimated:YES];
//        });
//    }
//    return hud;
//}
//-(void)presentMessageTips:(NSString *)message
//{
//    dispatch_async(dispatch_get_main_queue(), ^{
//        [self presentMessageTips_:message];
//    });
//}
//
//
//-(void)presentMessageTips:(NSString *)message dismisblock:(void (^)())dismissblock
//{
//    dispatch_async(dispatch_get_main_queue(), ^{
//        [self presentMessageTips_:message dismisblock:dismissblock];
//    });
//}
//
//
//-(void)presentFailureTips:(NSString *)message
//{
//    dispatch_async(dispatch_get_main_queue(), ^{
//        [self presentMessageTips_:message];
//    });
//}
//
//-(MBProgressHUD *)presentMessageTips_:(NSString *)message
//{
//    UIView * superview = [self HUDSuperView];
//    if(!superview) return nil;
//    MBProgressHUD *hud = [MBProgressHUD showHUDAddedTo:superview animated:YES];
//    hud.bezelView.backgroundColor = HBHUDBGCOLOR;
//    // Configure for text only and offset down
//    hud.mode = MBProgressHUDModeText;
//    hud.labelText = message;
//    hud.margin = 10.f;
//    hud.yOffset = 0.f;
//    hud.removeFromSuperViewOnHide = YES;
//    hud.userInteractionEnabled = NO;
//    dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(2 * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
//        @try{
//            [hud hide:YES];
//        }@catch(NSException * e){
//
//        }
//
//    });
//    return hud;
//}
//
//
//- (void)presentMessageTips_:(NSString *)message dismisblock:(void(^)())dismissblock
//{
//    UIView * superview = [self HUDSuperView];
//    if(!superview) return;
//    //    superview = superview.window;
//    MBProgressHUD *hud = [MBProgressHUD showHUDAddedTo:superview animated:YES];
//    hud.bezelView.backgroundColor = HBHUDBGCOLOR;
//    hud.mode = MBProgressHUDModeText;
//    hud.labelText = message;
//    hud.margin = 10.f;
//    hud.yOffset = 0;
//    //    hud.removeFromSuperViewOnHide = YES;
//
//    dispatch_queue_t queue = dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0);
//    __block typeof(self) wself = self;
//    __block typeof(MBProgressHUD) *whud = hud;
//    [hud showAnimated:YES whileExecutingBlock:^{
//        [wself sodasleeptask];
//    } onQueue:queue completionBlock:^{
//        dismissblock();
//        [whud removeFromSuperview];
//    }];
//}
//
//-(void)presentLoadinghud
//{
//    UIView * superview = [self HUDSuperView];
//    if(!superview) return ;
//    MBProgressHUD * HUD = [MBProgressHUD showHUDAddedTo:superview animated:YES];
//    UIButton * button = [UIButton buttonWithType:UIButtonTypeCustom];
//    [button setImage:[UIImage imageNamed:@"closeBtn"] forState:UIControlStateNormal];
//    button.frame = CGRectMake(5, [UIScreen mainScreen].bounds.size.height - 30, 25, 25);
//    [button addTarget:self action:@selector(hideloadingTips:) forControlEvents:UIControlEventTouchUpInside];
//    [HUD addSubview:button];
//}
//
//-(void)dismissAllTips
//{
//    UIView * superview = [self HUDSuperView];
//    if(!superview) return ;
//    [MBProgressHUD hideAllHUDsForView:superview animated:YES];
//    UIView * view = [superview viewWithTag:tag_view_circlehud];
//    if (view) {
//        [view removeFromSuperview];
//    }
//}
//
//-(MBProgressHUD *)presentLoadingTips:(NSString *)message
//{
//    UIView * superview = [self HUDSuperView];
//    if(!superview) return nil;
//    MBProgressHUD *HUD = [MBProgressHUD showHUDAddedTo:superview animated:YES];
//    [self setHUD:HUD];
//    HUD.mode = MBProgressHUDModeIndeterminate;//MBProgressHUDModeDeterminate;
//    HUD.delegate = self;
//    HUD.labelText = message;
//    UIButton * button = [UIButton buttonWithType:UIButtonTypeCustom];
//    [button setImage:[UIImage imageNamed:@"closeBtn"] forState:UIControlStateNormal];
//    button.frame = CGRectMake(5, [UIScreen mainScreen].bounds.size.height - 30, 25, 25);
//    [button addTarget:self action:@selector(hideloadingTips:) forControlEvents:UIControlEventTouchUpInside];
//    [HUD addSubview:button];
//    return HUD;
//}
//
//-(void)hideloadingTips:(id)sender
//{
//    MBProgressHUD * hud = [self obj_MBHUD];
//    [hud hide:YES];
//}
//
//-(void)dismissTips
//{
//    MBProgressHUD * hud = [self obj_MBHUD];
//    [hud hide:YES];
//    UIView * view = [[self HUDSuperView] viewWithTag:tag_view_hud];
//    if (view) {
//        [view removeFromSuperview];
//    }
//}
//
//
//- (void)sodasleeptask {
//    // Do something usefull in here instead of sleeping ...
//    sleep(1);
//}
//- (void)myProgressTask {
//    // This just increases the progress indicator in a loop
//
//    MBProgressHUD * hud = [self obj_MBHUD];
//    float progress = 0.0f;
//    while (progress < 1.0f) {
//        progress += 0.01f;
//        hud.progress = progress;
//        usleep(50000);
//    }
//}
//
//
//
//-(UIView *)HUDSuperView
//{
//    UIView * parentView = nil;
//    if ([[self class] isSubclassOfClass:[UINavigationController class]]) {
//        UINavigationController * ctr = (UINavigationController *)self;
//        parentView = ctr.view;
//    }
//    else if ([[self class] isSubclassOfClass:[UIViewController class]]) {
//        UIViewController * ctr = (UIViewController *)self;
//        parentView = ctr.view;
//    }
//    else if ([[self class] isSubclassOfClass:[UIView class]]) {
//        UIView * ctr = (UIView *)self;
//        parentView = ctr;
//    }
//    else if ([[self class] isSubclassOfClass:[UIWindow class]]) {
//        UIWindow * ctr = (UIWindow *)self;
//        parentView = ctr;
//
//    }
//    else if ([[self class] isSubclassOfClass:[UIResponder class]]) {
//        //        AppDelegate * ctr = (AppDelegate *)self;
//        UIWindow * ctr = [UIApplication sharedApplication].delegate.window;
//        parentView = ctr;
//    }
//    else{
//        UIWindow * ctr = [UIApplication sharedApplication].delegate.window;
//        parentView = ctr;
//    }
//    return parentView;
//}
//
//
//-(void)setHUD:(MBProgressHUD *)HUD
//{
//    NSMutableDictionary *opreations = (NSMutableDictionary*)objc_getAssociatedObject(self, &HBHUDOperationKey);
//    if(opreations == nil)
//    {
//        opreations = [[NSMutableDictionary alloc] init];
//        objc_setAssociatedObject(self, &HBHUDOperationKey, opreations, OBJC_ASSOCIATION_RETAIN);
//    }
//    [opreations setObject:HUD forKey:KEY_HUD];
//}
//
//-(MBProgressHUD *)obj_MBHUD
//{
//    NSMutableDictionary *opreations = (NSMutableDictionary*)objc_getAssociatedObject(self, &HBHUDOperationKey);
//    if(opreations == nil) return nil;
//    MBProgressHUD * aHUD = [opreations objectForKey:KEY_HUD];
//    return aHUD;
//}
//
//@end
//
//
//@implementation HBSectorProgressView
//
//- (instancetype)initWithFrame:(CGRect)frame
//{
//    self = [super initWithFrame:frame];
//    if (self) {
//        self.progress = 0;
//    }
//    return self;
//}
//- (void)drawRect:(CGRect)rect {
//
//    CGFloat lineWidth = 3;
//    UIColor * _backgroundTintColor = [UIColor colorWithRed:0x1d/(float)0xff green:0x1c/(float)0xff blue:0x2a/(float)0xff alpha:1];
//    // [UIColor blackColor];
//    UIColor * fillcolor = _backgroundTintColor;
//
//    UIBezierPath *processBackgroundPath = [UIBezierPath bezierPath];
//    processBackgroundPath.lineWidth = lineWidth;
//    processBackgroundPath.lineCapStyle = kCGLineCapButt;
//    CGPoint center = CGPointMake(CGRectGetMidX(self.bounds), CGRectGetMidY(self.bounds));
//    CGFloat radius = (self.bounds.size.width - lineWidth)/2;
//    CGFloat startAngle = - ((float)M_PI / 2); // 90 degrees
//    CGFloat endAngle = (2 * (float)M_PI) + startAngle;
//    [processBackgroundPath addArcWithCenter:center radius:radius startAngle:startAngle endAngle:endAngle clockwise:YES];
//    [_backgroundTintColor set];
//    [processBackgroundPath stroke];
//
//    //    定义扇形中心
//    CGPoint origin = CGPointMake(self.bounds.size.width/2., self.bounds.size.height/2); //CGPointMake(100, 100);
//    //    定义扇形半径
//    //    CGFloat radius = 100.0f;
//    //    设定扇形起点位置
//    startAngle = - M_PI_2;
//    //    根据进度计算扇形结束位置
//    endAngle = startAngle + self.progress * M_PI * 2;
//
//    //    根据起始点、原点、半径绘制弧线
//    UIBezierPath *sectorPath = [UIBezierPath bezierPathWithArcCenter:origin radius:radius startAngle:startAngle endAngle:endAngle clockwise:YES];
//
//    //    从弧线结束为止绘制一条线段到圆心。这样系统会自动闭合图形，绘制一条从圆心到弧线起点的线段。
//    [sectorPath addLineToPoint:origin];
//    //    设置扇形的填充颜色
//    [fillcolor set];
//    //    设置扇形的填充模式
//    [sectorPath fill];
//
//}
//
//
////重写progress的set方法，可以在赋值的同时给label赋值
//- (void)setProgress:(CGFloat)progress{
//    _progress = progress;
//    //    赋值结束之后要刷新UI，不然看不到扇形的变化
//    [self setNeedsDisplay];
//}

@end

