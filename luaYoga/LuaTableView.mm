//
//  LuaTableView.m
//  luaYoga
//
//  Created by wen william on 2018/11/6.
//  Copyright © 2018年 wen william. All rights reserved.
//

#import "LuaTableView.h"
extern "C" {
#include "lua.h"
#include "lauxlib.h"
}
#include "common/business_runtime.h"
#include "tools/lua_helpers.h"
#import "lua_yoga.h"
#import "LuaYogaView.h"

#define LUA_CELL_TAG 999

@interface LuaTableView()<UITableViewDataSource, UITableViewDelegate>

@end

@implementation LuaTableView

-(instancetype)initWithFrame:(CGRect)frame
{
    self = [super initWithFrame:frame];
    if (self) {
        self.delegate = self;
        self.dataSource = self;
    }
    return self;
}

-(instancetype)init
{
    self = [super init];
    if (self) {
        self.delegate = self;
        self.dataSource = self;
    }
    return self;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    NSInteger num = 0;
    lua_State * state = BusinessThread::GetCurrentThreadLuaState();
    BEGIN_STACK_MODIFY(state);
    assert(self.luaRoot != nil);
    pushUserdataInStrongTable(state,(__bridge void *)self.luaRoot);
    assert(lua_type(state, -1) == LUA_TTABLE);
    lua_pushlightuserdata(state, (__bridge void *)self);
    lua_rawget(state, -2);
    assert(lua_type(state, -1) == LUA_TUSERDATA);
    if(lua_type(state, -1) == LUA_TUSERDATA){
        lua_getfield(state, -1, List_ColumnsInGroup);
        if (lua_type(state, -1) == LUA_TFUNCTION) {
            lua_pushinteger(state, section);
            lua_pcall(state, 1, 1, 0);
            num = lua_tointeger(state, -1);
        }
    } else {
        NSLog(@"tableView numberOfRowsInSection no userdata ");
        assert(false);
    }
    END_STACK_MODIFY(state, 0)
    return num;
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    NSInteger num = 1;
    lua_State * state = BusinessThread::GetCurrentThreadLuaState();
    BEGIN_STACK_MODIFY(state);
    assert(self.luaRoot != nil);
    pushUserdataInStrongTable(state,(__bridge void *)self.luaRoot);
    assert(lua_type(state, -1) == LUA_TTABLE);
    lua_pushlightuserdata(state, (__bridge void *)self);
    lua_rawget(state, -2);
    assert(lua_type(state, -1) == LUA_TUSERDATA);
    if(lua_type(state, -1) == LUA_TUSERDATA){
        lua_getfield(state, -1, List_NumberOfGroups);
        if (lua_type(state, -1) == LUA_TFUNCTION) {
            lua_pcall(state, 0, 1, 0);
            num = lua_tointeger(state, -1);
        }
    } else {
        NSLog(@"tableView numberOfSectionsInTableView no userdata ");
        assert(false);
    }
    END_STACK_MODIFY(state, 0)
    return num;
}


- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    NSString * identifier = @"cell";
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:identifier];
    lua_State * state = BusinessThread::GetCurrentThreadLuaState();
    BEGIN_STACK_MODIFY(state);
    if (!cell) {
        cell = [[UITableViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:identifier];
        LuaYogaView * view = [[LuaYogaView alloc] initWithFrame:CGRectMake(0, 0, tableView.frame.size.width, [self heightForSection:indexPath.section row:indexPath.row])];
        size_t nbytes = sizeof(YogaInfo);
        YogaInfo *yi = (YogaInfo *)lua_newuserdata(state, nbytes);
        luaL_getmetatable(state, LUA_YOGA_VIEW_METATABLE_NAME);
        lua_setmetatable(state, -2);
        lua_newtable(state);
        lua_setfenv(state, -2);
        yi->view = (__bridge void *)view;
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
        lua_pop(state, 2);
        [cell addSubview:view];
        view.tag = LUA_CELL_TAG;
    }
    LuaYogaView * v = [cell viewWithTag:LUA_CELL_TAG];
    assert(self.luaRoot != nil);
    pushUserdataInStrongTable(state,(__bridge void *)self.luaRoot);
    assert(lua_type(state, -1) == LUA_TTABLE);
    lua_pushlightuserdata(state, (__bridge void *)self);
    lua_rawget(state, -2);
    assert(lua_type(state, -1) == LUA_TUSERDATA);
    if(lua_type(state, -1) == LUA_TUSERDATA){
        lua_getfield(state, -1, List_RenderItem);
        if (lua_type(state, -1) == LUA_TFUNCTION) {
            pushStrongUserdataTable(state);
            lua_pushlightuserdata(state, (__bridge void *)v);
            lua_rawget(state, -2);
            lua_pushlightuserdata(state, (__bridge void *)v);
            lua_rawget(state, -2);
            lua_remove(state, -2);
            lua_remove(state, -2);
            lua_pcall(state, 1, 0, 0);
        } else {
            NSLog(@"tableView List_ItemHeight not function ");
            assert(false);
        }
    } else {
        NSLog(@"tableView cellForRowAtIndexPath no userdata ");
        assert(false);
    }
    END_STACK_MODIFY(state, 0)
    return cell;
}

-(CGFloat)heightForSection:(NSInteger)section row:(NSInteger)row
{
    CGFloat height = 0;
    lua_State * state = BusinessThread::GetCurrentThreadLuaState();
    BEGIN_STACK_MODIFY(state);
    assert(self.luaRoot != nil);
    pushUserdataInStrongTable(state,(__bridge void *)self.luaRoot);
    assert(lua_type(state, -1) == LUA_TTABLE);
    lua_pushlightuserdata(state, (__bridge void *)self);
    lua_rawget(state, -2);
    assert(lua_type(state, -1) == LUA_TUSERDATA);
    if(lua_type(state, -1) == LUA_TUSERDATA){
        lua_getfield(state, -1, List_ItemHeight);
        if (lua_type(state, -1) == LUA_TFUNCTION) {
            lua_tointeger(state, (int)section);
            lua_tointeger(state, (int)row);
            lua_pcall(state, 2, 1, 0);
            height = lua_tonumber(state, -1);
        } else {
            NSLog(@"tableView List_ItemHeight not function ");
            assert(false);
        }
    } else {
        NSLog(@"tableView heightForRowAtIndexPath no userdata ");
        assert(false);
    }
    END_STACK_MODIFY(state, 0)
    return height;
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    return [self heightForSection:indexPath.section row:indexPath.row];
}

-(CGFloat)headerHeightForSection:(NSInteger)section
{
    CGFloat height = 0;
    lua_State * state = BusinessThread::GetCurrentThreadLuaState();
    BEGIN_STACK_MODIFY(state);
    assert(self.luaRoot != nil);
    pushUserdataInStrongTable(state,(__bridge void *)self.luaRoot);
    assert(lua_type(state, -1) == LUA_TTABLE);
    lua_pushlightuserdata(state, (__bridge void *)self);
    lua_rawget(state, -2);
    assert(lua_type(state, -1) == LUA_TUSERDATA);
    if(lua_type(state, -1) == LUA_TUSERDATA){
        lua_getfield(state, -1, List_GroupHeaderHeight);
        if (lua_type(state, -1) == LUA_TFUNCTION) {
            lua_tointeger(state, (int)section);
            lua_pcall(state, 1, 1, 0);
            height = lua_tonumber(state, -1);
        }
    } else {
        NSLog(@"tableView headerHeightForSection no userdata ");
        assert(false);
    }
    END_STACK_MODIFY(state, 0)
    return height;
}

-(CGFloat)footerHeightForSection:(NSInteger)section
{
    CGFloat height = 0;
    lua_State * state = BusinessThread::GetCurrentThreadLuaState();
    BEGIN_STACK_MODIFY(state);
    assert(self.luaRoot != nil);
    pushUserdataInStrongTable(state,(__bridge void *)self.luaRoot);
    assert(lua_type(state, -1) == LUA_TTABLE);
    lua_pushlightuserdata(state, (__bridge void *)self);
    lua_rawget(state, -2);
    assert(lua_type(state, -1) == LUA_TUSERDATA);
    if(lua_type(state, -1) == LUA_TUSERDATA){
        lua_getfield(state, -1, List_GroupFooterHeight);
        if (lua_type(state, -1) == LUA_TFUNCTION) {
            lua_tointeger(state, (int)section);
            lua_pcall(state, 1, 1, 0);
            height = lua_tonumber(state, -1);
        }
    } else {
        NSLog(@"tableView footerHeightForSection no userdata ");
        assert(false);
    }
    END_STACK_MODIFY(state, 0)
    return height;
}

- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section
{
    return [self headerHeightForSection:section];
}

- (CGFloat)tableView:(UITableView *)tableView heightForFooterInSection:(NSInteger)section
{
    return [self footerHeightForSection:section];
}

- (nullable UIView *)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section
{
    CGFloat height = [self headerHeightForSection:section];
    if (height > 0) {
        LuaYogaView * view = [[LuaYogaView alloc] initWithFrame:CGRectMake(0, 0, tableView.frame.size.width, height)];
        lua_State * state = BusinessThread::GetCurrentThreadLuaState();
        BEGIN_STACK_MODIFY(state);
        assert(self.luaRoot != nil);
        pushUserdataInStrongTable(state,(__bridge void *)self.luaRoot);
        assert(lua_type(state, -1) == LUA_TTABLE);
        lua_pushlightuserdata(state, (__bridge void *)self);
        lua_rawget(state, -2);
        assert(lua_type(state, -1) == LUA_TUSERDATA);
        if(lua_type(state, -1) == LUA_TUSERDATA){
            lua_getfield(state, -1, List_GroupHeader);
            if (lua_type(state, -1) == LUA_TFUNCTION) {
                size_t nbytes = sizeof(YogaInfo);
                YogaInfo *yi = (YogaInfo *)lua_newuserdata(state, nbytes);
                luaL_getmetatable(state, LUA_YOGA_VIEW_METATABLE_NAME);
                lua_setmetatable(state, -2);
                lua_newtable(state);
                lua_setfenv(state, -2);
                yi->view = (__bridge void *)view;
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
                lua_pcall(state, 1, 0, 0);
            }
        } else {
            NSLog(@"tableView viewForHeaderInSection no userdata ");
            assert(false);
        }
        END_STACK_MODIFY(state, 0)
        return view;
    } else {
        return nil;
    }
}

- (nullable UIView *)tableView:(UITableView *)tableView viewForFooterInSection:(NSInteger)section
{
    CGFloat height = [self footerHeightForSection:section];
    if (height > 0) {
        LuaYogaView * view = [[LuaYogaView alloc] initWithFrame:CGRectMake(0, 0, tableView.frame.size.width, height)];
        lua_State * state = BusinessThread::GetCurrentThreadLuaState();
        BEGIN_STACK_MODIFY(state);
        assert(self.luaRoot != nil);
        pushUserdataInStrongTable(state,(__bridge void *)self.luaRoot);
        assert(lua_type(state, -1) == LUA_TTABLE);
        lua_pushlightuserdata(state, (__bridge void *)self);
        lua_rawget(state, -2);
        assert(lua_type(state, -1) == LUA_TUSERDATA);
        if(lua_type(state, -1) == LUA_TUSERDATA){
            lua_getfield(state, -1, List_GroupFooter);
            if (lua_type(state, -1) == LUA_TFUNCTION) {
                size_t nbytes = sizeof(YogaInfo);
                YogaInfo *yi = (YogaInfo *)lua_newuserdata(state, nbytes);
                luaL_getmetatable(state, LUA_YOGA_VIEW_METATABLE_NAME);
                lua_setmetatable(state, -2);
                lua_newtable(state);
                lua_setfenv(state, -2);
                yi->view = (__bridge void *)view;
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
                lua_pcall(state, 1, 0, 0);
            }
        } else {
            NSLog(@"tableView viewForFooterInSection no userdata ");
            assert(false);
        }
        END_STACK_MODIFY(state, 0)
        return view;
    } else {
        return nil;
    }
}

@end
