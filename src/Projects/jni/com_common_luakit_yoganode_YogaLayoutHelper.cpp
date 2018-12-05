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
    if(lua_type(state, -1) == LUA_TUSERDATA) {
        lua_getfield(state, -1, List_ColumnsInGroup);
        if (lua_type(state, -1) == LUA_TFUNCTION) {
            lua_pushinteger(state, (int)section);
            lua_pcall(state, 1, 1, 0);
            num = lua_tointeger(state, -1);
        }
    } else {
        LOGD("tableView getItemcount no userdata");
        assert(false);
    }
    END_STACK_MODIFY(state, 0)
    return num;
}

JNIEXPORT jstring JNICALL Java_com_common_luakit_yoganode_YogaLayoutHelper_getItemViewType
  (JNIEnv *env, jobject thiz, jlong hostView, jlong rootView, jint position) {
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
        lua_getfield(state, -1, List_Identifier);
        if (lua_type(state, -1) == LUA_TFUNCTION) {
            lua_pushinteger(state, (int)0);
            lua_pushinteger(state, (int)position);
            lua_pcall(state, 2, 1, 0);
            s = lua_tostring(state, -1);
            LOGD("The identifier is %s", s);
        } else {
            LOGD("tableView List_Identifier not function");
            assert(false);
        }
    } else {
        LOGD("tableView getItemViewType no userdata");
        assert(false);
    }
    END_STACK_MODIFY(state, 0)
    jstring jidentifier = env->NewStringUTF(s);
    return jidentifier;
}

JNIEXPORT jfloat JNICALL Java_com_common_luakit_yoganode_YogaLayoutHelper_getItemHeight
  (JNIEnv *env, jobject thiz, jlong hostView, jlong rootView, jint position) {
    LOGD("Java_com_common_luakit_yoganode_YogaLayoutHelper_getItemHeight");

    float height = 0;
    lua_State * state = BusinessThread::GetCurrentThreadLuaState();
    BEGIN_STACK_MODIFY(state);
    assert(self.luaRoot != nil);
    pushUserdataInStrongTable(state,(void *)rootView);
    assert(lua_type(state, -1) == LUA_TTABLE);
    lua_pushlightuserdata(state, (void *)hostView);
    lua_rawget(state, -2);
    assert(lua_type(state, -1) == LUA_TUSERDATA);
    if(lua_type(state, -1) == LUA_TUSERDATA){
        lua_getfield(state, -1, List_ItemHeight);
        if (lua_type(state, -1) == LUA_TFUNCTION) {
            lua_pushinteger(state, (int)position);
            lua_pcall(state, 1, 1, 0);
            height = lua_tonumber(state, -1);
        }
    } else {
        LOGD("tableView getItemHeight no userdata");
        assert(false);
    }
    END_STACK_MODIFY(state, 0)
    return (jfloat)height;
  }

void setPointer(void * self) {
    jobject jself = ((java_weak_ref *)self)->obj();

    JniEnvWrapper env;
    jclass selfClass = env->GetObjectClass(jself);
    if (selfClass == NULL) {
        LOGD("Failed, The setPointer selfClass is NULL");
        return;
    }
    jmethodID jmid = env->GetMethodID(selfClass, "setSelfPointer", "(J)V");
    if (jmid == NULL) {
        LOGD("Failed!! The method setSelfPointer is null");
    }
    env->CallVoidMethod(jself, jmid, (jlong)self);
}

JNIEXPORT void JNICALL Java_com_common_luakit_yoganode_YogaLayoutHelper_onCreateView
  (JNIEnv *env , jobject thiz, jlong hostView, jlong rootView, jobject contentView) {
    LOGD("Java_com_common_luakit_yoganode_YogaLayoutHelper_onCreateView");

    lua_State * state = BusinessThread::GetCurrentThreadLuaState();
    BEGIN_STACK_MODIFY(state);

    java_weak_ref *view = new java_weak_ref(contentView);
    setPointer(view);
    size_t nbytes = sizeof(YogaInfo);
    YogaInfo *yi = (YogaInfo *)lua_newuserdata(state, nbytes);
    luaL_getmetatable(state, LUA_YOGA_VIEW_METATABLE_NAME);
    lua_setmetatable(state, -2);
    lua_newtable(state);
    lua_setfenv(state, -2);
    yi->view = (void *)view;
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
}

JNIEXPORT jobject JNICALL Java_com_common_luakit_yoganode_YogaLayoutHelper_onBindView
  (JNIEnv *env, jobject thiz, jlong hostView, jlong rootView, jlong contentView, jint position) {
    LOGD("Java_com_common_luakit_yoganode_YogaLayoutHelper_onCreateView");
    assert(hostView != 0);
    java_weak_ref *view = (java_weak_ref *)contentView;
    LOGD("The view is %d", view);
    lua_State * state = BusinessThread::GetCurrentThreadLuaState();
    BEGIN_STACK_MODIFY(state);
    pushUserdataInStrongTable(state,(void *)rootView);
    assert(lua_type(state, -1) == LUA_TTABLE);
    lua_pushlightuserdata(state, (void *)hostView);
    lua_rawget(state, -2);
    assert(lua_type(state, -1) == LUA_TUSERDATA);
    if(lua_type(state, -1) == LUA_TUSERDATA) {

        lua_getfield(state, -1, List_RenderItem);
        if (lua_type(state, -1) == LUA_TFUNCTION) {
            pushStrongUserdataTable(state);
            lua_pushlightuserdata(state, (void *)view);
            lua_rawget(state, -2);
            lua_pushlightuserdata(state, (void *)view);
            lua_rawget(state, -2);
            lua_remove(state, -2);
            lua_remove(state, -2);
            lua_pushinteger(state, 0);
            lua_pushinteger(state, position);
            lua_pcall(state, 3, 0, 0);
        } else {
            LOGD("tableView onBindView not function");
            assert(false);
        }
    } else {
        LOGD("tableView onBindView no userdata");
        assert(false);
    }
    END_STACK_MODIFY(state, 0)
}
