//
//  SodaSectorProgressView.m
//  BaseUI
//
//  Created by boob on 2018/2/27.
//

#import "SodaSectorProgressView.h"
 

@implementation SodaSectorProgressView

- (void)setProgress:(CGFloat)progress animated:(BOOL)animated{
    self.progress = progress;
}

- (instancetype)initWithFrame:(CGRect)frame
{
    self = [super initWithFrame:frame];
    if (self) {
        self.progress = 0;
        self.lineWidth = 3;
        self.userInteractionEnabled = NO;
    }
    return self;
}

-(UIColor *)backgroundTintColor{
    if (!_backgroundTintColor) {
        _backgroundTintColor = [UIColor whiteColor];//[UIColor colorWithRed:0x1d/(float)0xff green:0x1c/(float)0xff blue:0x2a/(float)0xff alpha:1];
    }
    return _backgroundTintColor;
}


- (void)drawRect:(CGRect)rect {
    
    CGFloat lineWidth = self.lineWidth;
    UIColor * _backgroundTintColor = self.backgroundTintColor;
    // [UIColor blackColor];
    UIColor * fillcolor = _backgroundTintColor;
    
    UIBezierPath *processBackgroundPath = [UIBezierPath bezierPath];
    processBackgroundPath.lineWidth = lineWidth;
    processBackgroundPath.lineCapStyle = kCGLineCapButt;
    CGPoint center = CGPointMake(CGRectGetMidX(self.bounds), CGRectGetMidY(self.bounds));
    CGFloat radius = (self.bounds.size.width - lineWidth)/2;
    radius = radius>0?radius:lineWidth;
    CGFloat startAngle = - ((float)M_PI / 2); // 90 degrees
    CGFloat endAngle = (2 * (float)M_PI) + startAngle;
    [processBackgroundPath addArcWithCenter:center radius:radius startAngle:startAngle endAngle:endAngle clockwise:YES];
    [_backgroundTintColor set];
    [processBackgroundPath stroke];
    
    //    定义扇形中心
    CGPoint origin = CGPointMake(self.bounds.size.width/2., self.bounds.size.height/2); //CGPointMake(100, 100);
    //    定义扇形半径
    //    CGFloat radius = 100.0f;
    //    设定扇形起点位置
    startAngle = - M_PI_2;
    //    根据进度计算扇形结束位置
    endAngle = startAngle + self.progress * M_PI * 2;
    
    //    根据起始点、原点、半径绘制弧线
    UIBezierPath *sectorPath = [UIBezierPath bezierPathWithArcCenter:origin radius:radius startAngle:startAngle endAngle:endAngle clockwise:YES];
    
    //    从弧线结束为止绘制一条线段到圆心。这样系统会自动闭合图形，绘制一条从圆心到弧线起点的线段。
    [sectorPath addLineToPoint:origin];
    //    设置扇形的填充颜色
    [fillcolor set];
    //    设置扇形的填充模式
    [sectorPath fill];
    
}


- (void)setProgress:(CGFloat)progress{
    _progress = progress;
    //    赋值结束之后要刷新UI，不然看不到扇形的变化
    _progress = fmaxf(0, _progress);
    _progress = fminf(1, _progress);
    [self setNeedsDisplay];
}


@end
