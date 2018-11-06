//
//  LuaYogaView.h
//  yogaTest
//
//  Created by wen william on 2018/10/24.
//  Copyright © 2018年 wen william. All rights reserved.
//

#import <UIKit/UIKit.h>
#include "lua_yoga.h"

NS_ASSUME_NONNULL_BEGIN

@interface LuaYogaView : UIView

- (void)loadLua:(NSString *)module type:(YogaType)type;

@end

NS_ASSUME_NONNULL_END
