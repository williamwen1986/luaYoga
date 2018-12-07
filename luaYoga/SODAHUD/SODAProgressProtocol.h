//
//  SODAProgressProtocol.h
//  Pods
//
//  Created by boob on 2018/4/9.
//

#ifndef SODAProgressProtocol_h
#define SODAProgressProtocol_h

@protocol SODAProgressProtocol

@required
@property (nonatomic, assign) CGFloat progress;
- (void)setProgress:(CGFloat)progress animated:(BOOL)animated;

@end
#endif /* SODAProgressProtocol_h */
