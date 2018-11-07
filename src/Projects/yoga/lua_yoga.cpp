extern "C" {
#include "lua.h"
#include "lauxlib.h"
}
#include "tools/lua_helpers.h"
#include "lua_yoga.h"
#include "luaYogaBridge.h"
#include <string>

static void addYogaEnum(lua_State *L);

static void process_bgColor(lua_State *L);

struct YogaFunction {
    void * view;
    YogaType type;
    void * root;
};

static void process_bgColor(lua_State *L,YogaInfo*viewInfo){
    float r = 0, g = 0, b = 0;
    float a = 1.0;
    lua_pushstring(L, "r");
    lua_rawget(L, -2);
    if (!lua_isnil(L, -1)) {
        r = lua_tonumber(L, -1);
    }
    lua_pop(L, 1);
    
    lua_pushstring(L, "g");
    lua_rawget(L, -2);
    if (!lua_isnil(L, -1)) {
        g = lua_tonumber(L, -1);
    }
    lua_pop(L, 1);
    
    lua_pushstring(L, "b");
    lua_rawget(L, -2);
    if (!lua_isnil(L, -1)) {
        b = lua_tonumber(L, -1);
    }
    lua_pop(L, 1);
    
    lua_pushstring(L, "a");
    lua_rawget(L, -2);
    if (!lua_isnil(L, -1)) {
        a = lua_tonumber(L, -1);
    }
    lua_pop(L, 1);
    
    setBackgroundColor(viewInfo->view, r, g, b, a);
}

static int __yogaViewNewIndex(lua_State *L)
{
    BEGIN_STACK_MODIFY(L);
    YogaInfo *viewInfo = (YogaInfo *)luaL_checkudata(L, 1, LUA_YOGA_VIEW_METATABLE_NAME);
    if (!viewInfo->isDead) {
        std::string name = lua_tostring(L, 2);
        
        if (name == BACKGROUND_COLOR)
        {
            process_bgColor(L,viewInfo);
        }
        else if(name == YOGA_IS_ENABLE) {
            bool b = lua_toboolean(L, -1);
            setYogaProperty(viewInfo->view, viewInfo->type ,name, b);
        }
        else if (name == ImageView_Name){
            
            std::string c_imageName =  lua_tostring(L, -1);
            
            setImageName(viewInfo->view, c_imageName);
            
        }
        else if (name == ImageView_Name_HL){
            
            std::string c_imageName =  lua_tostring(L, -1);
            
            setImageName_hl(viewInfo->view, c_imageName);
            
        }
        else if (name == ImageView_ContentMode){
            
            long contentMode  =  lua_tointeger(L, -1);
            
            setImageViewContentMode(viewInfo->view, contentMode);
            
        }
        else if (name == View_Cliping){
            
            long isCliping  =  lua_tointeger(L, -1);
            
            setCliping(viewInfo->view, isCliping);
            
        }
        else if (name == View_Highlighted){
            
            long isHighlighted  =  lua_tointeger(L, -1);
            
            setHighlighted(viewInfo->view, isHighlighted);
            
        }
        else {
            float value = lua_tonumber(L, -1);
            setYogaProperty(viewInfo->view, viewInfo->type ,name, value);
        }
    }
    END_STACK_MODIFY(L, 0);
    return 0;
}

static int __yogaViewIndex(lua_State *L)
{
    BEGIN_STACK_MODIFY(L);
    YogaInfo *viewInfo = (YogaInfo *)luaL_checkudata(L, 1, LUA_YOGA_VIEW_METATABLE_NAME);
    std::string name = lua_tostring(L, 2);
    
    if (!viewInfo->isDead) {
        if(name == ADD_CONTAINER){
            size_t nbytes = sizeof(YogaFunction);
            YogaFunction *yf = (YogaFunction *)lua_newuserdata(L, nbytes);
            luaL_getmetatable(L, LUA_YOGA_FUNCTION_METATABLE_NAME);
            lua_setmetatable(L, -2);
            yf->view = viewInfo->view;
            yf->type = CONTAINER;
            yf->root = viewInfo->root;
        }
        else if(name == ADD_ListView){
            size_t nbytes = sizeof(YogaFunction);
            YogaFunction *yf = (YogaFunction *)lua_newuserdata(L, nbytes);
            luaL_getmetatable(L, LUA_YOGA_FUNCTION_METATABLE_NAME);
            lua_setmetatable(L, -2);
            yf->view = viewInfo->view;
            yf->type = LIST;
            yf->root = viewInfo->root;
        }
        else if(name == ADD_ImageView){
            size_t nbytes = sizeof(YogaFunction);
            YogaFunction *yf = (YogaFunction *)lua_newuserdata(L, nbytes);
            luaL_getmetatable(L, LUA_YOGA_FUNCTION_METATABLE_NAME);
            lua_setmetatable(L, -2);
            yf->view = viewInfo->view;
            yf->type = IMAGE;
            yf->root = viewInfo->root;
        } else {
            float ret = getYogaProperty(viewInfo->view, viewInfo->type, name);
            lua_pushnumber(L, ret);
        }
    } else {
        if(name == ADD_CONTAINER || name == ImageView_Name || name == ADD_ListView){
            size_t nbytes = sizeof(YogaFunction);
            YogaFunction *yf = (YogaFunction *)lua_newuserdata(L, nbytes);
            luaL_getmetatable(L, LUA_YOGA_FUNCTION_METATABLE_NAME);
            lua_setmetatable(L, -2);
            yf->view = NULL;
        } else {
            lua_pushnumber(L, 0);
        }
    }
    END_STACK_MODIFY(L, 1)
    return 1;
}

static int __yogaFuncCall(lua_State *L)
{
    BEGIN_STACK_MODIFY(L);
    YogaFunction *yf = (YogaFunction *)luaL_checkudata(L, 1, LUA_YOGA_FUNCTION_METATABLE_NAME);
    if(yf->view != NULL){
        void * child = addView(yf->view, yf->type);
        size_t nbytes = sizeof(YogaInfo);
        YogaInfo *yi = (YogaInfo *)lua_newuserdata(L, nbytes);
        luaL_getmetatable(L, LUA_YOGA_VIEW_METATABLE_NAME);
        lua_setmetatable(L, -2);
        yi->view = child;
        yi->type = yf->type;
        yi->isDead = false;
        if (yf->root != NULL) {
            yi->root = yf->root;
        } else {
            yi->root = yf->view;
        }
        pushUserdataInStrongTable(L,yi->root);
        lua_pushlightuserdata(L, child);
        lua_pushvalue(L, -3);
        lua_rawset(L, -3);
        lua_pop(L, 1);
    } else {
        lua_pushnil(L);
    }
    END_STACK_MODIFY(L, 1)
    return 1;
}

static const struct luaL_Reg yogaViewMetaFunctions[] = {
    {"__newindex",__yogaViewNewIndex},
    {"__index",__yogaViewIndex},
    {NULL, NULL}
};

static const struct luaL_Reg yogaViewFunctions[] = {
    {NULL, NULL}
};

static const struct luaL_Reg yogaFuncMetaFunctions[] = {
    {"__call",__yogaFuncCall},
    {NULL, NULL}
};

static const struct luaL_Reg yogaFuncFunctions[] = {
    {NULL, NULL}
};

extern int luaopen_yoga(lua_State *L) {
    BEGIN_STACK_MODIFY(L);
    luaL_newmetatable(L, LUA_YOGA_VIEW_METATABLE_NAME);
    luaL_register(L, NULL, yogaViewMetaFunctions);
    luaL_register(L, LUA_YOGA_VIEW_METATABLE_NAME, yogaViewFunctions);
    lua_pushvalue(L, -2);
    lua_setmetatable(L, -2);
    addYogaEnum(L);
    END_STACK_MODIFY(L, 0)
    return 0;
}

extern int luaopen_yoga_func(lua_State *L) {
    BEGIN_STACK_MODIFY(L);
    luaL_newmetatable(L, LUA_YOGA_FUNCTION_METATABLE_NAME);
    luaL_register(L, NULL, yogaFuncMetaFunctions);
    luaL_register(L, LUA_YOGA_FUNCTION_METATABLE_NAME, yogaFuncFunctions);
    lua_pushvalue(L, -2);
    lua_setmetatable(L, -2);
    END_STACK_MODIFY(L, 0)
    return 0;
}

static void addYogaEnum(lua_State *L) {
    //    YGAlign
    lua_pushinteger(L, 0);
    lua_setglobal(L, "YGAlignAuto");
    lua_pushinteger(L, 1);
    lua_setglobal(L, "YGAlignFlexStart");
    lua_pushinteger(L, 2);
    lua_setglobal(L, "YGAlignCenter");
    lua_pushinteger(L, 3);
    lua_setglobal(L, "YGAlignFlexEnd");
    lua_pushinteger(L, 4);
    lua_setglobal(L, "YGAlignStretch");
    lua_pushinteger(L, 5);
    lua_setglobal(L, "YGAlignBaseline");
    lua_pushinteger(L, 6);
    lua_setglobal(L, "YGAlignSpaceBetween");
    lua_pushinteger(L, 7);
    lua_setglobal(L, "YGAlignSpaceAround");
    
    //    YGDirection
    lua_pushinteger(L, 0);
    lua_setglobal(L, "YGDirectionInherit");
    lua_pushinteger(L, 1);
    lua_setglobal(L, "YGDirectionLTR");
    lua_pushinteger(L, 2);
    lua_setglobal(L, "YGDirectionRTL");
    
    //    YGDisplay
    lua_pushinteger(L, 0);
    lua_setglobal(L, "YGDisplayFlex");
    lua_pushinteger(L, 1);
    lua_setglobal(L, "YGDisplayNone");
    
    //    YGFlexDirection
    lua_pushinteger(L, 0);
    lua_setglobal(L, "YGFlexDirectionColumn");
    lua_pushinteger(L, 1);
    lua_setglobal(L, "YGFlexDirectionColumnReverse");
    lua_pushinteger(L, 2);
    lua_setglobal(L, "YGFlexDirectionRow");
    lua_pushinteger(L, 3);
    lua_setglobal(L, "YGFlexDirectionRowReverse");
    
    //    YGJustify
    lua_pushinteger(L, 0);
    lua_setglobal(L, "YGJustifyFlexStart");
    lua_pushinteger(L, 1);
    lua_setglobal(L, "YGJustifyCenter");
    lua_pushinteger(L, 2);
    lua_setglobal(L, "YGJustifyFlexEnd");
    lua_pushinteger(L, 3);
    lua_setglobal(L, "YGJustifySpaceBetween");
    lua_pushinteger(L, 4);
    lua_setglobal(L, "YGJustifySpaceAround");
    lua_pushinteger(L, 5);
    lua_setglobal(L, "YGJustifySpaceEvenly");
    
    //    YGPositionType
    lua_pushinteger(L, 0);
    lua_setglobal(L, "YGPositionTypeRelative");
    lua_pushinteger(L, 1);
    lua_setglobal(L, "YGPositionTypeAbsolute");
    
    //    YGWrap
    lua_pushinteger(L, 0);
    lua_setglobal(L, "YGWrapNoWrap");
    lua_pushinteger(L, 1);
    lua_setglobal(L, "YGWrapWrap");
    lua_pushinteger(L, 2);
    lua_setglobal(L, "YGWrapWrapReverse");
    
    //    YGOverflow
    lua_pushinteger(L, 0);
    lua_setglobal(L, "YGOverflowVisible");
    lua_pushinteger(L, 1);
    lua_setglobal(L, "YGOverflowHidden");
    lua_pushinteger(L, 2);
    lua_setglobal(L, "YGOverflowScroll");
}
