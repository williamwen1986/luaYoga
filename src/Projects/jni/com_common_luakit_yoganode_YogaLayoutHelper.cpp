#include "com_common_luakit_yoganode_YogaLayoutHelper.h"
#include "JNIModel.h"
#include "common/business_runtime.h"
#include "lua_yoga.h"
#include "java_weak_ref.h"
#include "lua_helpers.h"
#include "jni.h"
#include "android/log.h"

#define TAG    "LuaYogaDemo-jni" 
#define LOGD(...)  __android_log_print(ANDROID_LOG_INFO,TAG,__VA_ARGS__)

JNIEXPORT jlong JNICALL Java_com_common_luakit_yoganode_YogaLayoutHelper_getItemcount 
	(JNIEnv *env, jobject thiz, jlong hostView) {
	LOGD("Java_com_common_luakit_yoganode_YogaLayoutHelper_getItemcount");
	return 0;
}

JNIEXPORT jlong JNICALL Java_com_common_luakit_yoganode_YogaLayoutHelper_getItemViewType
  (JNIEnv *env, jobject thiz, jlong hostView, jint positon) {
  	LOGD("Java_com_common_luakit_yoganode_YogaLayoutHelper_getItemViewType");
  	return 0;
}

JNIEXPORT void * JNICALL Java_com_common_luakit_yoganode_YogaLayoutHelper_onCreateView
  (JNIEnv *env , jobject thiz, jlong hostView) {
  	LOGD("Java_com_common_luakit_yoganode_YogaLayoutHelper_onCreateView");
  	return NULL;
}
