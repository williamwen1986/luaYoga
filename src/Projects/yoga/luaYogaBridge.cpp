#include "luaYogaBridge.h"
#include "lua_yoga.h"
#include "jni.h"
#include "JniEnvWrapper.h"

float getYogaProperty(void * view, YogaType type, std::string propertyName)
{
    return 0.0;
}

void * addView(void * parentView, YogaType type)
{
    return NULL;
}

void setYogaProperty(void * view, YogaType type, std::string propertyName, float value)
{
    /*int viewType = -1
    if (YogaType == CONTAINER) {
        viewType = 0;
    } else if (YogaType == IMAGE) {
        viewType = 1;
    } else if (YogaType == TEXT) {
        viewType = 2;
    } else if (YogaType == SVGA) {
        viewType = 3;
    } else if (YogaType == LIST) {
        viewType = 4;
    } else if (YogaType == COLLECTIONVIEW) {
        viewType = 5;
    } else if (YogaType == SCROLLVIEW) {
        viewType = 6;
    } else if (YogaType == OTHER) {
        viewType = 7;
    }
    if (viewType == -1) {
        return;
    }
    JniEnvWrapper env;
    jclass clazzYogaView = env->FindClass("com/example/freeman/myjnisample/YogaView");
    if (clazzYogaView == NULL) {
        return;
    }
    jclass clazzView = env->FindClass("android/view/View");
    jmethodID mid = env->GetMethodID(clazzYogaView, "setYogaProperty", "(clazzView;I;Ljava/lang/String;F;)V");
    env->CallVoidMethod(view, mid, );*/
}

void setBackgroundColor(void * view, float r, float g, float b, float a)
{

}

void setYogaViewContentMode(void * view, float contentModeType)
{

}

