#include "com_common_luakit_yoganode_YogaLayoutHelper.h"
#include "JNIModel.h"
#include "common/business_runtime.h"
#include "lua_yoga.h"
#include "java_weak_ref.h"
#include "lua_helpers.h"
#include "jni.h"
#include "android/log.h"

extern "C" {
#include "lua.h"
#include "lauxlib.h"
}

#define TAG    "LuaYogaDemo-jni" 
#define LOGD(...)  __android_log_print(ANDROID_LOG_INFO,TAG,__VA_ARGS__)

JNIEXPORT jint JNICALL Java_com_common_luakit_yoganode_YogaLayoutHelper_getItemcount 
	(JNIEnv *env, jobject thiz, jint section, jlong hostView, jlong rootView) {
	LOGD("Java_com_common_luakit_yoganode_YogaLayoutHelper_getItemcount");

    int num = 0;
    lua_State * state = BusinessThread::GetCurrentThreadLuaState();
    BEGIN_STACK_MODIFY(state);
    assert(rootView != 0);
    pushUserdataInStrongTable(state,(void *)rootView);
    assert(lua_type(state, -1) == LUA_TTABLE);
    lua_pushlightuserdata(state, (void *)hostView);
    lua_rawget(state, -2);
    assert(lua_type(state, -1) == LUA_TUSERDATA);
    if(lua_type(state, -1) == LUA_TUSERDATA){
        lua_getfield(state, -1, List_ColumnsInGroup);
        if (lua_type(state, -1) == LUA_TFUNCTION) {
            lua_pushinteger(state, (int)section);
            lua_pcall(state, 1, 1, 0);
            num = lua_tointeger(state, -1);
        }
    } else {
        LOGD("tableView numberOfRowsInSection no userdata");
        assert(false);
    }
    END_STACK_MODIFY(state, 0)
    return num;
}

JNIEXPORT jstring JNICALL Java_com_common_luakit_yoganode_YogaLayoutHelper_getItemViewType
  (JNIEnv *env, jobject thiz, jlong hostView, jlong rootView, jint positon) {
  	LOGD("Java_com_common_luakit_yoganode_YogaLayoutHelper_getItemViewType");
  	const char * s;
    lua_State * state = BusinessThread::GetCurrentThreadLuaState();
    BEGIN_STACK_MODIFY(state);
    assert(hostView != 0);
    pushUserdataInStrongTable(state,(void *)rootView);
    assert(lua_type(state, -1) == LUA_TTABLE);
    lua_pushlightuserdata(state, (void *)hostView);
    lua_rawget(state, -2);
    assert(lua_type(state, -1) == LUA_TUSERDATA);
    if(lua_type(state, -1) == LUA_TUSERDATA){
        lua_getfield(state, -1, List_ItemHeight);
        if (lua_type(state, -1) == LUA_TFUNCTION) {
            lua_pushinteger(state, (int)0);
            lua_pushinteger(state, (int)positon);
            lua_pcall(state, 2, 1, 0);
            s = lua_tostring(state, -1);
        } else {
            LOGD("tableView List_ItemHeight not function");
            assert(false);
        }
    } else {
        LOGD("tableView heightForRowAtIndexPath no userdata");
        assert(false);
    }
    END_STACK_MODIFY(state, 0)
    jstring jidentifier = env->NewStringUTF(s);
    return jidentifier;
}

JNIEXPORT void * JNICALL Java_com_common_luakit_yoganode_YogaLayoutHelper_onCreateView
  (JNIEnv *env , jobject thiz, jlong hostView) {
  	LOGD("Java_com_common_luakit_yoganode_YogaLayoutHelper_onCreateView");
  	return NULL;
}
