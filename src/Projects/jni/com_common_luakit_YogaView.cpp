#include "com_common_luakit_YogaView.h"
#include "JNIModel.h"
#include "common/business_runtime.h"
#include "lua_yoga.h"
#include "java_weak_ref.h"
#include "lua_helpers.h"
#ifdef __cplusplus
extern "C" {
#endif
#include "lua.h"
#include "lauxlib.h"


JNIEXPORT jlong JNICALL Java_com_common_luakit_YogaView_loadLua
(JNIEnv *env, jobject thiz, jstring moduleName) {
    std::string moduleStr = JNIModel::String::ConvertToNative(env,moduleName);
    std::string lua = "LUA_YOGA_VIEW_FACTORY = require('"+moduleStr+"')";
    lua_State * state = BusinessThread::GetCurrentThreadLuaState();
    luaL_dostring(state, lua.c_str());
    lua_getglobal(state, "LUA_YOGA_VIEW_FACTORY");
    java_weak_ref *weakRef = new java_weak_ref(thiz);
    if (lua_isfunction(state, -1)) {
        size_t nbytes = sizeof(YogaInfo);
        YogaInfo *yi = (YogaInfo *)lua_newuserdata(state, nbytes);
        luaL_getmetatable(state, LUA_YOGA_VIEW_METATABLE_NAME);
        lua_setmetatable(state, -2);
        yi->view = (void *)weakRef;
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
    return (jlong)weakRef;
  }

JNIEXPORT void JNICALL Java_com_common_luakit_YogaView_dispose
(JNIEnv *env, jobject thiz, jlong ref) {
	  lua_State * state = BusinessThread::GetCurrentThreadLuaState();
    java_weak_ref *v = (java_weak_ref *)ref;
    pushStrongUserdataTable(state);
    lua_pushlightuserdata(state, v);
    lua_rawget(state, -2);

    assert(lua_istable(state, -1));
    lua_pushnil(state);  /* first key */
    while (lua_next(state, -2)) {
        YogaInfo *viewInfo = (YogaInfo *)luaL_checkudata(state, 1, LUA_YOGA_VIEW_METATABLE_NAME);
        java_weak_ref *javaView = (java_weak_ref *)viewInfo->view;
        delete javaView;
        viewInfo->view = NULL;
        viewInfo->isDead = true;
        lua_pop(state, 1); // Pop off the value
    }
    lua_pop(state, 1);
    
    lua_pushlightuserdata(state, v);
    lua_pushnil(state);
    lua_rawset(state, -3);
    lua_pop(state, 1);
  }

#ifdef __cplusplus
}
#endif