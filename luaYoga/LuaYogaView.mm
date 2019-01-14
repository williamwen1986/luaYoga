//
//  LuaYogaView.m
//  yogaTest
//
//  Created by wen william on 2018/10/24.
//  Copyright © 2018年 wen william. All rights reserved.
//

#import "LuaYogaView.h"
#include <string>
#include "lua_yoga.h"
#include <LuakitPod/lua_helpers.h>
#include <LuakitPod/business_client_thread.h>
#import <YogaKit/UIView+Yoga.h>

#ifdef __cplusplus
extern "C" {
#endif
#include "lua.h"
#include "lauxlib.h"
#ifdef __cplusplus
}
#endif

@implementation LuaYogaView

/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
}
*/

- (void)loadLua:(NSString *)module
{
    std::string moduleStr = [module cStringUsingEncoding:NSUTF8StringEncoding];
    std::string lua = "LUA_YOGA_VIEW_FACTORY = require('"+moduleStr+"')";
    lua_State * state = BusinessThread::GetCurrentThreadLuaState();
    luaL_dostring(state, lua.c_str());
    lua_getglobal(state, "LUA_YOGA_VIEW_FACTORY");
    if (lua_isfunction(state, -1)) {
        size_t nbytes = sizeof(YogaInfo);
        YogaInfo *yi = (YogaInfo *)lua_newuserdata(state, nbytes);
        luaL_getmetatable(state, LUA_YOGA_VIEW_METATABLE_NAME);
        lua_setmetatable(state, -2);
        lua_newtable(state);
        lua_setfenv(state, -2);
        yi->view = (__bridge void *)self;
        yi->type = CONTAINER;
        yi->isDead = false;
        yi->root = NULL;
        pushStrongUserdataTable(state);
        lua_pushlightuserdata(state, yi->view);
        lua_newtable(state);
        lua_pushlightuserdata(state, yi->view);
        lua_pushvalue(state, -5);
        lua_rawset(state, -3);
        lua_rawset(state, -3);
        lua_pop(state, 1);
        int err = lua_pcall(state, 1, 0, 0);
        if (err != 0) {
            luaL_error(state,"LUA_YOGA_VIEW_FACTORY call error");
        }
    } else {
        luaL_error(state,"LUA_YOGA_VIEW_FACTORY is not a function");
    }
    [self.yoga applyLayoutPreservingOrigin:YES];
}

- (void)dealloc
{
    lua_State * state = BusinessThread::GetCurrentThreadLuaState();
    void *v = (__bridge void*)self;
    pushStrongUserdataTable(state);
    lua_pushlightuserdata(state, v);
    lua_rawget(state, -2);
    
    assert(lua_istable(state, -1));
    lua_pushnil(state);  /* first key */
    while (lua_next(state, -2)) {
        YogaInfo *viewInfo = (YogaInfo *)luaL_checkudata(state, -1, LUA_YOGA_VIEW_METATABLE_NAME);
        viewInfo->isDead = true;
        lua_pop(state, 1); // Pop off the value
    }
    lua_pop(state, 1);
    
    lua_pushlightuserdata(state, v);
    lua_pushnil(state);
    lua_rawset(state, -3);
    lua_pop(state, 1);
}

@end
