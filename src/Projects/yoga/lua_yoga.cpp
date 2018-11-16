extern "C" {
#include "lua.h"
#include "lauxlib.h"
}
#include "tools/lua_helpers.h"
#include "lua_yoga.h"
#include "luaYogaBridge.h"

#include <vector>
#include <string>
#include <tuple>

enum ActionType {
    LIST_RELOAD,
    VIEW_RELOAD_YOGA,
    VIEW_REMOVE_FROM_PARENT,
};

static void addYogaEnum(lua_State *L);

static std::vector<float> process_bgColor(lua_State *L,bool isHighlighted);

struct YogaFunction {
    void * view;
    YogaType type;
    ActionType action;
    void * root;
};


static LuaModel getValueFromState(lua_State *state,LuaValueType valueType, std::string luaKey){
    
    struct LuaModel result;
    result.type = valueType;
    
    switch (valueType) {
        case Value_String:{
            lua_pushstring(state, luaKey.c_str());
            lua_rawget(state,-2);
            if (!lua_isnil(state, -1)) {
                result.value_string = lua_tostring(state, -1);
            }else{
                result.value_string = "";
            }
            lua_pop(state, 1);
        }
            break;
        case Value_Number:{
            lua_pushstring(state, luaKey.c_str());
            lua_rawget(state, -2);
            if (!lua_isnil(state, -1)) {
                result.value_float = lua_tonumber(state, -1);
            }else{
                result.value_float = (luaKey == "a")?1:0;
            }
            lua_pop(state, 1);
        }
            break;
        default:
            break;
    }
    
    return result;
    
}

static std::tuple<std::string, std::string> process_ImageTable(lua_State *L){
    
    typedef std::tuple<std::string, std::string> ImageTuple;
    
    std::string imageName_normal = getValueFromState(L, Value_String, "imageName").value_string;
    std::string imageName_highlighted = getValueFromState(L, Value_String, "imageName_hl").value_string;
    
    ImageTuple tuple(imageName_normal, imageName_highlighted);
    
    return tuple;
}


static std::vector<float> process_bgColor(lua_State *L,bool isHighlighted){
    std::vector<float> color;
    
    std::string redKey = isHighlighted?"r_hl":"r";
    std::string greenKey = isHighlighted?"g_hl":"g";
    std::string blueKey = isHighlighted?"b_hl":"b";
    std::string aplhaKey = isHighlighted?"a_hl":"a";

    
    color.push_back(getValueFromState(L, Value_Number, redKey).value_float);
    color.push_back(getValueFromState(L, Value_Number, greenKey).value_float);
    color.push_back(getValueFromState(L, Value_Number, blueKey).value_float);
    color.push_back(getValueFromState(L, Value_Number, aplhaKey).value_float);
    return color;
}

static int __yogaViewNewIndex(lua_State *L)
{
    BEGIN_STACK_MODIFY(L);
    YogaInfo *viewInfo = (YogaInfo *)luaL_checkudata(L, 1, LUA_YOGA_VIEW_METATABLE_NAME);
    if (!viewInfo->isDead) {
        std::string name = lua_tostring(L, 2);
        
        if (name == BACKGROUND_COLOR)
        {
            std::vector<float> color = process_bgColor(L,false);
            setBackgroundColor(viewInfo->view, color[0], color[1], color[2], color[3]);
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
        else if (name == ImageView_Image){
            
            std::tuple<std::string, std::string> imageTuble = process_ImageTable(L);
            
            setImageTable(viewInfo->view,
                          std::get<0>(imageTuble),
                          std::get<1>(imageTuble));
            
        }
        else if (name == ImageView_ColorImage){
            
            std::vector<float> color = process_bgColor(L,false);
            std::vector<float> color_gl = process_bgColor(L,true);

            setImageColorTable(viewInfo->view,
                          color[0],color[1],color[2],color[3],
                          color_gl[0],color_gl[1],color_gl[2],color_gl[3]);
        }
        else if (name == View_Cliping){
            
            long isCliping  =  lua_tointeger(L, -1);
            
            setCliping(viewInfo->view, isCliping);
            
        }
        else if (name == Text_Alignment){
            
            long alignment  =  lua_tointeger(L, -1);
            
//            setTextAlignment(viewInfo->view, alignment);
            
        }
        else if (name == Text_Text){
            
            std::string contentText  =  lua_tostring(L, -1);
            
            setText(viewInfo->view, contentText);
            
        }
        
        else if (name == View_Highlighted){
            
            long isHighlighted  =  lua_tointeger(L, -1);
            
            setHighlighted(viewInfo->view, isHighlighted);
            
        } else if (name == List_SeperatorColor){
            std::vector<float> color = process_bgColor(L,false);
            setListSeperatorColor(viewInfo->view, color[0], color[1], color[2], color[3]);
        } else if (name == TAP_FUNCTION){
            addTapGesture(viewInfo->view, viewInfo->root);
            lua_getfenv(L, 1);
            lua_insert(L, 2);
            lua_rawset(L, 2);
        } else if (name == LONGPRESS_FUNCTION){
            addLongPressGesture(viewInfo->view, viewInfo->root);
            lua_getfenv(L, 1);
            lua_insert(L, 2);
            lua_rawset(L, 2);
        }
        else {
            float value = lua_tonumber(L, -1);
            bool hasSetProperty = setYogaProperty(viewInfo->view, viewInfo->type ,name, value);
            if (!hasSetProperty) {
                lua_getfenv(L, 1);
                lua_insert(L, 2);
                lua_rawset(L, 2);
            }
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
        else if (name == RELOAD_YOGA){
            size_t nbytes = sizeof(YogaFunction);
            YogaFunction *yf = (YogaFunction *)lua_newuserdata(L, nbytes);
            luaL_getmetatable(L, LUA_YOGA_FUNCTION_METATABLE_NAME);
            lua_setmetatable(L, -2);
            yf->view = viewInfo->view;
            yf->type = OTHER;
            yf->action = VIEW_RELOAD_YOGA;
            yf->root = viewInfo->root;
        }
        else if (name == REMOVE_FROM_PARENT){
            size_t nbytes = sizeof(YogaFunction);
            YogaFunction *yf = (YogaFunction *)lua_newuserdata(L, nbytes);
            luaL_getmetatable(L, LUA_YOGA_FUNCTION_METATABLE_NAME);
            lua_setmetatable(L, -2);
            viewInfo->isDead = true;
            yf->view = viewInfo->view;
            yf->type = OTHER;
            yf->action = VIEW_REMOVE_FROM_PARENT;
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
        }
        else if(name == ADD_TEXT){
            size_t nbytes = sizeof(YogaFunction);
            YogaFunction *yf = (YogaFunction *)lua_newuserdata(L, nbytes);
            luaL_getmetatable(L, LUA_YOGA_FUNCTION_METATABLE_NAME);
            lua_setmetatable(L, -2);
            yf->view = viewInfo->view;
            yf->type = TEXT;
            yf->root = viewInfo->root;
        }
        else if(name == List_Reload){
            size_t nbytes = sizeof(YogaFunction);
            YogaFunction *yf = (YogaFunction *)lua_newuserdata(L, nbytes);
            luaL_getmetatable(L, LUA_YOGA_FUNCTION_METATABLE_NAME);
            lua_setmetatable(L, -2);
            yf->view = viewInfo->view;
            yf->type = OTHER;
            yf->action = LIST_RELOAD;
            yf->root = viewInfo->root;
        }
        else if (name == WIDTH ||
                 name == HEIGHT){
            float ret = getYogaProperty(viewInfo->view, viewInfo->type, name);
            lua_pushnumber(L, ret);
        }
        else if (name == IS_DEAD){
            lua_pushboolean(L, viewInfo->isDead);
        }
        else {
            lua_getfenv(L, -2);
            lua_pushvalue(L, -2);
            lua_rawget(L, 3);
        }
    } else {
        if(name == ADD_CONTAINER || name == ADD_ListView || name == ADD_ImageView || name == ADD_TEXT){
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
        if (yf->type != OTHER) {
            void * root = NULL;
            if (yf->root != NULL) {
                root = yf->root;
            } else {
                root = yf->view;
            }
            void * child = addView(yf->view, yf->type, root);
            size_t nbytes = sizeof(YogaInfo);
            YogaInfo *yi = (YogaInfo *)lua_newuserdata(L, nbytes);
            luaL_getmetatable(L, LUA_YOGA_VIEW_METATABLE_NAME);
            lua_setmetatable(L, -2);
            
            lua_newtable(L);
            lua_setfenv(L, -2);
            
            yi->view = child;
            yi->type = yf->type;
            yi->isDead = false;
            yi->root = root;
            pushUserdataInStrongTable(L,yi->root);
            lua_pushlightuserdata(L, child);
            lua_pushvalue(L, -3);
            lua_rawset(L, -3);
            lua_pop(L, 1);
        } else if(yf->action == LIST_RELOAD){
            listReload(yf->view);
            lua_pushnil(L);
        } else if(yf->action == VIEW_RELOAD_YOGA){
            reloadYoga(yf->view);
            lua_pushnil(L);
        } else if(yf->action == VIEW_REMOVE_FROM_PARENT){
            removeFromParent(yf->view);
            lua_pushnil(L);
        }
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
