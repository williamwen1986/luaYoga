//
//  main.m
//  luaYoga
//
//  Created by wen william on 2018/10/25.
//  Copyright © 2018年 wen william. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "AppDelegate.h"
#import <LuakitPod/oc_helpers.h>
#include "lua_yoga.h"


int main(int argc, char * argv[]) {
    startLuakit(argc, argv);
    lua_State * state = getCurrentThreadLuaState();
    luaopen_yoga(state);
    luaopen_yoga_func(state);
    @autoreleasepool {
        return UIApplicationMain(argc, argv, nil, NSStringFromClass([AppDelegate class]));
    }
}
