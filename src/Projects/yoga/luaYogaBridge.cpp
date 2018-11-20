#include "luaYogaBridge.h"
#include "lua_yoga.h"
#include "jni.h"
#include "JniEnvWrapper.h"
#include "android/log.h"
#include "java_weak_ref.h"

#define TAG    "LuaYogaDemo-jni" 
#define LOGD(...)  __android_log_print(ANDROID_LOG_INFO,TAG,__VA_ARGS__)

float getYogaProperty(void * view, YogaType type, std::string propertyName)
{
    return 0.0;
}

void setPointer(void * self, void * parentView, void * root) {

}

void *addView(void * parentView, YogaType type, void * root)
{
    return NULL;
}

bool setYogaProperty(void * view, YogaType type, std::string propertyName, float value)
{
    int viewType = -1;
    if (type == CONTAINER) {
        viewType = 0;
    } else if (type == IMAGE) {
        viewType = 1;
    } else if (type == TEXT) {
        viewType = 2;
    } else if (type == SVGA) {
        viewType = 3;
    } else if (type == LIST) {
        viewType = 4;
    } else if (type == COLLECTIONVIEW) {
        viewType = 5;
    } else if (type == SCROLLVIEW) {
        viewType = 6;
    } else if (type == OTHER) {
        viewType = 7;
    }
    if (viewType == -1) {
        return false;
    }
    JniEnvWrapper env;
    jobject hostView = ((java_weak_ref * )view)->obj();

    // jclass clazzYogaView = env->FindClass("com/common/luakit/YogaView");
    jclass hostViewClass = env->GetObjectClass(hostView); // Set the property in the view own object.
    if (hostViewClass == NULL) {
        return false;
    }
    jmethodID mid = env->GetMethodID(hostViewClass, "setYogaProperty", "(Lcom/common/luakit/YogaView;ILjava/lang/String;F)Z");
    if (mid == NULL) {
        return false;
    }
    jstring jpropertyName = env->NewStringUTF(propertyName.c_str());
    if (jpropertyName == NULL) {
        return false;
    }
    
    jboolean success = (jboolean)env->CallBooleanMethod(hostView, mid, hostView, (jint)viewType, jpropertyName, (jfloat)value);
    return (bool)success; 
}

void setBackgroundColor(void * view, float r, float g, float b, float a)
{

}

void addTapGesture(void * view, void *root)
{

}

void addLongPressGesture(void * view, void *root)
{

}

void reloadYoga(void * view)
{

}

void removeFromParent(void * view)
{

}

void setListSeperatorColor(void * view, float r, float g, float b, float a)
{

}

void listReload(void * view)
{

}

void setImageViewContentMode(void * imageView, float contentModeType)
{

}

void setImageName(void * imageView,  std::string imageName)
{

}

void setImageName_hl(void * imageView,  std::string imageName)
{

}

void setCliping(void * parentView,  float isCliping)
{

}

void setHighlighted(void * imageView,  float isHighlighted)
{

}

void setImageTable(void * imageView,
                   std::string imageName_Normal , //普通状态资源名
                   std::string imageName_Highlighted)  //高亮状态
{

}

void setImageColorTable(void * imageView,
                        float r, float g, float b, float a,     //普通状态-颜色生成Image
                        float r_hl, float g_hl, float b_hl, float a_hl //高亮状态-颜色生成Image
                        ) 
{

}

void setTextAlignment(void * textView,  float textAlignment)
{

}

void setText(void * textView,  std::string imageName_Normal)
{

}

