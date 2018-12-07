//
//  SodaCirleProgressView.m
//  BaseUI
//
//  Created by boob on 2018/4/4.
//

#import "SodaCirleProgressView.h"
// Common
#define DEGREES_TO_RADIANS(angle) ((angle) / 180.0 * M_PI)

@implementation SodaCirleProgressView

-(void)soda_transform_rotation_z{
    
    self.transform = CGAffineTransformIdentity;
    CABasicAnimation *rotationAnim = [CABasicAnimation animationWithKeyPath:@"transform.rotation.z"];
    rotationAnim.fromValue = @(0);
    rotationAnim.toValue = @(M_PI * 2);
    rotationAnim.repeatCount = MAXFLOAT;
    rotationAnim.duration = 1;
    rotationAnim.removedOnCompletion = NO;
    rotationAnim.cumulative = YES;
    // 添加动画
    [self.layer addAnimation:rotationAnim
                      forKey:@"layout_rotate"];
}

- (instancetype)init
{
    self = [super init];
    if (self) {
        [self configsetting];
    }
    return self;
}

- (instancetype)initWithFrame:(CGRect)frame
{
    self = [super initWithFrame:frame];
    if (self) {
        [self configsetting]; 
    }
    return self;
}

-(void)layoutSubviews{
    [super layoutSubviews];
    if (self.rotation_z) {
        [self.layer removeAllAnimations];
        [self soda_transform_rotation_z];
    }
}

-(void)configsetting{
    
    self.startAngle = -90;
    self.progress = 0;
    self.progressBarWidth = 3;
    self.userInteractionEnabled = NO;
    [self setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
    self.titleLabel.font = [UIFont systemFontOfSize:10];

    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(handelEnterforegroundNotify:) name:UIApplicationWillEnterForegroundNotification object:nil];
}

-(void)handelEnterforegroundNotify:(NSNotification *)notify{
    [self setNeedsLayout];
}

- (void)drawRect:(CGRect)rect {
    
    [super drawRect:rect];
    CGPoint innerCenter = CGPointMake(self.bounds.size.width / 2, self.bounds.size.height / 2);
   
    CGFloat radius = MIN(innerCenter.x, innerCenter.y);

    CGFloat currentProgressAngle = (_progress * 360) + _startAngle;

    CGContextRef context = UIGraphicsGetCurrentContext();
    
    CGContextClearRect(context, rect);

    [self drawProgressBar:context progressAngle:currentProgressAngle center:innerCenter radius:radius];
 
}

-(void)setProgress:(CGFloat)progress{
    _progress = progress;
    if(self.showTitle) [self setTitle:[NSString stringWithFormat:@"%.f%%",progress*100] forState:UIControlStateNormal];
    [self setNeedsDisplay];
}
- (void)setProgress:(CGFloat)progress animated:(BOOL)animated {
    _progress = progress;
    if(self.showTitle) [self setTitle:[NSString stringWithFormat:@"%.0f%%",progress*100] forState:UIControlStateNormal];
     [self setNeedsDisplay];
}
- (void)drawProgressBar:(CGContextRef)context progressAngle:(CGFloat)progressAngle center:(CGPoint)center radius:(CGFloat)radius {
    
    CGFloat barWidth = self.progressBarWidth;
    if (barWidth > radius) {
        barWidth = radius;
    }
    
    CGFloat lineWidth = barWidth;
    UIBezierPath *processBackgroundPath = [UIBezierPath bezierPath];
    processBackgroundPath.lineWidth = lineWidth;
    processBackgroundPath.lineCapStyle = kCGLineCapRound;
    CGFloat startAngle =  DEGREES_TO_RADIANS(_startAngle);
    CGFloat endAngle = DEGREES_TO_RADIANS(progressAngle);// (2 * (float)M_PI) + startAngle;
    [processBackgroundPath addArcWithCenter:center radius:radius - barWidth/2 startAngle:startAngle endAngle:endAngle clockwise:YES];
    [self.progressBarProgressColor set];
    [processBackgroundPath stroke];
    
    CGContextSetFillColorWithColor(context, self.progressBarTrackColor.CGColor);
    CGContextBeginPath(context);
    CGContextAddArc(context, center.x, center.y, radius, DEGREES_TO_RADIANS(progressAngle), DEGREES_TO_RADIANS(_startAngle + 360), 0);
    CGContextAddArc(context, center.x, center.y, radius - barWidth, DEGREES_TO_RADIANS(_startAngle + 360), DEGREES_TO_RADIANS(progressAngle), 1);
    CGContextClosePath(context);
    CGContextFillPath(context);
    
}



- (void)setProgressBarWidth:(CGFloat)progressBarWidth {
    _progressBarWidth = progressBarWidth;
    [self setNeedsDisplay];
}

- (void)setProgressBarProgressColor:(UIColor *)progressBarProgressColor {
    _progressBarProgressColor = progressBarProgressColor;
    [self setNeedsDisplay];
}

- (void)setProgressBarTrackColor:(UIColor *)progressBarTrackColor {
    _progressBarTrackColor = progressBarTrackColor;
    [self setNeedsDisplay];
}

- (void)setStartAngle:(CGFloat)startAngle {
    _startAngle = startAngle;
    [self setNeedsDisplay];
}
@end
