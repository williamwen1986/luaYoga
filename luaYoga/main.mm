//
//  main.m
//  luaYoga
//
//  Created by wen william on 2018/10/25.
//  Copyright © 2018年 wen william. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "AppDelegate.h"
#include "base/command_line.h"
#include "base/memory/scoped_ptr.h"
#include "common/business_main_delegate.h"
#include "common/business_runtime.h"
#include "tools/lua_helpers.h"
#include "base/thread_task_runner_handle.h"
#include "common/base_lambda_support.h"
#include "lua_yoga.h"

extern "C" {
#include "lua.h"
#include "lualib.h"
#include "lauxlib.h"
}

int main(int argc, char * argv[]) {
    CommandLine::Init(argc, argv);
    NSString *bundlePath = [[NSBundle mainBundle] bundlePath];
    luaSetPackagePath([bundlePath cStringUsingEncoding:NSUTF8StringEncoding]);
    setXXTEAKeyAndSign("2dxLua", strlen("2dxLua"), "XXTEA", strlen("XXTEA"));
    scoped_ptr<BusinessMainDelegate> delegate(new BusinessMainDelegate());
    scoped_ptr<BusinessRuntime> business_runtime(BusinessRuntime::Create());
    business_runtime->Initialize(delegate.get());
    business_runtime->Run();
    
    lua_State * state = BusinessThread::GetCurrentThreadLuaState();
    luaopen_yoga(state);
    luaopen_yoga_func(state);
    @autoreleasepool {
        return UIApplicationMain(argc, argv, nil, NSStringFromClass([AppDelegate class]));
    }
}
