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
#import <LuakitPod/lua_helpers.h>
#include "lua_yoga.h"


int main(int argc, char * argv[]) {
    
    NSArray *paths = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory,NSUserDomainMask, YES);
    NSString *path = [paths objectAtIndex:0];
    NSString *luaPath = [NSString stringWithFormat:@"%@/lua",path];
    NSBundle *bundle = [NSBundle mainBundle];
    NSString *bundlePath = [bundle bundlePath];
    NSString *luaBundlePath = [NSString stringWithFormat:@"%@/lua",bundlePath];
    NSFileManager *manager = [NSFileManager defaultManager];
    [manager removeItemAtPath:luaPath error:nil];
    [manager copyItemAtPath:luaBundlePath toPath:luaPath error:nil];
    luaSetPackagePath([luaPath cStringUsingEncoding:NSUTF8StringEncoding]);
    startLuakit(argc, argv);
    lua_State * state = getCurrentThreadLuaState();
    luaopen_yoga(state);
    luaopen_yoga_func(state);
    
   
    
    
    
    @autoreleasepool {
        return UIApplicationMain(argc, argv, nil, NSStringFromClass([AppDelegate class]));
    }
}
