#include "luaYogaBridge.h"
#include "lua_yoga.h"
#include "jni.h"
#include "JniEnvWrapper.h"
#include "android/log.h"
#include "java_weak_ref.h"

#define INVALID_VIEW_TYPE -1

#define TAG    "LuaYogaDemo-jni" 
#define LOGD(...)  __android_log_print(ANDROID_LOG_INFO,TAG,__VA_ARGS__)

void setPointer(void * self, void * parentView, void * root) {
    jobject jself = ((java_weak_ref *)self)->obj();
    jobject jparentView = ((java_weak_ref *)parentView)->obj();
    jobject jroot = ((java_weak_ref *)root)->obj();

    JniEnvWrapper env;
    jclass selfClass = env->GetObjectClass(jself);
    if (selfClass == NULL) {
        LOGD("Failed, The setPointer selfClass is NULL");
        return;
    }
    jmethodID jmid = env->GetMethodID(selfClass, "setNativePointer", "(JJJ)V");
    if (jmid == NULL) {
        LOGD("Failed!! The method setNativePointer is null");
    }
    env->CallVoidMethod(jself, jmid, (jlong)self, (jlong)parentView, (jlong)root);
}

int convertYogaType(YogaType type) {
    int viewType = INVALID_VIEW_TYPE;
    if (type == CONTAINER) {
        viewType = 0;
    } else if (type == IMAGE) {
        viewType = 1;
    } else if (type == TEXT) {
        viewType = 2;
    } else if (type == BUTTON) {
        viewType = 3;
    } else if (type == SVGA) {
        viewType = 4;
    } else if (type == LIST) {
        viewType = 5;
    } else if (type == COLLECTIONVIEW) {
        viewType = 6;
    } else if (type == SCROLLVIEW) {
        viewType = 7;
    } else if (type == OTHER) {
        viewType = 8;
    }
    return viewType;
}

float getYogaProperty(void * view, YogaType type, std::string propertyName) {
    int viewType = convertYogaType(type);
    if (viewType == INVALID_VIEW_TYPE) {
        return false;
    }
    JniEnvWrapper env;
    jobject jhostView = ((java_weak_ref * )view)->obj();
    jclass jhostViewClass = env->GetObjectClass(jhostView); // get the property in the view own object.
    if (jhostViewClass == NULL) {
        return false;
    }
    jmethodID mid = env->GetMethodID(jhostViewClass, "getYogaProperty", "(ILjava/lang/String;)F");
    if (mid == NULL) {
        return false;
    }
    jstring jpropertyName = env->NewStringUTF(propertyName.c_str());
    if (jpropertyName == NULL) {
        return false;
    }
    jfloat value = (jfloat)env->CallFloatMethod(jhostView, mid, (jint)viewType, jpropertyName);
    return (float)value; 
}

void *addView(void * parentView, YogaType type, void * root) {
    int viewType = convertYogaType(type);
    if (viewType == INVALID_VIEW_TYPE) {
        LOGD("Failed, The view is unsupport.");
        return NULL;
    }
    JniEnvWrapper env;
    jobject jparentView = ((java_weak_ref *)parentView)->obj();
    jobject jroot = ((java_weak_ref *)root)->obj();
    jclass parentViewClass = env->GetObjectClass(jparentView);
    if (parentView == NULL) {
        LOGD("Failed, The parentView is null");
        return NULL;
    }
    jmethodID jmid = env->GetMethodID(parentViewClass, "addYogaView", "(I)Landroid/view/View;");
    if (jmid == NULL) {
        LOGD("Failed, The jmid addYogaView not found");
        return NULL;
    }
    jobject addedView = env->CallObjectMethod(jparentView, jmid, (jint)viewType);
    java_weak_ref *weakRef = new java_weak_ref(addedView);
    setPointer(weakRef, parentView, root); // Keep the jni pionter in java to find tables.
    return weakRef;
}

bool setYogaProperty(void * view, YogaType type, std::string propertyName, float value) {
    int viewType = convertYogaType(type);
    if (viewType == INVALID_VIEW_TYPE) {
        return false;
    }
    JniEnvWrapper env;
    jobject jhostView = ((java_weak_ref * )view)->obj();
    jclass jhostViewClass = env->GetObjectClass(jhostView); // Set the property in the view own object.
    if (jhostViewClass == NULL) {
        return false;
    }
    jmethodID mid = env->GetMethodID(jhostViewClass, "setYogaProperty", "(ILjava/lang/String;F)Z");
    if (mid == NULL) {
        return false;
    }
    jstring jpropertyName = env->NewStringUTF(propertyName.c_str());
    if (jpropertyName == NULL) {
        return false;
    }
    jboolean success = (jboolean)env->CallBooleanMethod(jhostView, mid, (jint)viewType, jpropertyName, (jfloat)value);
    return (bool)success; 
}

void setBackgroundColor(void * view, float r, float g, float b, float a) {
    JniEnvWrapper env;
    jobject jhostView = ((java_weak_ref *)view)->obj();
    jclass jhostViewClass = env->GetObjectClass(jhostView);
    jmethodID mid = env->GetMethodID(jhostViewClass, "nativeSetBackgroundColor", "(FFFF)V");
    if (mid == NULL) {
        return;
    }
    env->CallVoidMethod(jhostView, mid, (jfloat)r, (jfloat)g, (jfloat)b, (jfloat)a);
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

void listReload(void * view) { //notifyDataChanged()
    JniEnvWrapper env;
    jobject jhostView = ((java_weak_ref *)view)->obj();
    jclass jhostViewClass = env->GetObjectClass(jhostView);
    jmethodID jmid = env->GetMethodID(jhostViewClass, "nativeListReload", "()V");
    if (jmid == NULL) {
        LOGD("Failed, The method nativeListReload is not found");
    }
    env->CallVoidMethod(jhostView, jmid);
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
                        std::vector<float> color,    //普通状态-颜色生成Image
                        std::vector<float> color_hl  //高亮状态-颜色生成Image
                        ) {

}

void setImagePath(void * imageView,  std::string imagePath) {
    JniEnvWrapper env;
    jobject jhostView = ((java_weak_ref*)imageView)->obj();
    jclass jhostViewClass = env->GetObjectClass(jhostView);
    jmethodID jmid = env->GetMethodID(jhostViewClass, "nativeSetImagePath", "(Ljava/lang/String;)V");
    if (jmid == NULL) {
        LOGD("Failed, method nativeSetImagePath not found");
        return;
    }
    jstring jpath = env->NewStringUTF(imagePath.c_str());
    env->CallVoidMethod(jhostView, jmid, jpath);
}

void setTextAlignment(void * textView,  float textAlignment) {
    JniEnvWrapper env;
    jobject jhostView = ((java_weak_ref *)textView)->obj();
    jclass jhostViewClass = env->GetObjectClass(jhostView);
    jmethodID jmid = env->GetMethodID(jhostViewClass, "nativeSetTextAlignment", "(F)V");
    if (jmid == NULL) {
        LOGD("Failed!! method nativeSetTextAlignment not found");
        return;
    }
    env->CallVoidMethod(jhostView, jmid, (jfloat)textAlignment);

}

void setText(void * textView,  std::string text) {
    JniEnvWrapper env;
    jobject jhostView = ((java_weak_ref *) textView)->obj();
    jclass jhostViewClass = env->GetObjectClass(jhostView);
    jmethodID jmid = env->GetMethodID(jhostViewClass, "nativeSetText", "(Ljava/lang/String;)V");
    if (jmid == NULL) {
        LOGD("Failed!! method nativeSetText not found");
        return;
    }
    jstring jtext = env->NewStringUTF(text.c_str());
    env->CallVoidMethod(jhostView, jmid, jtext);
}

void setTextColor(void * textView,  std::vector<float> color) {
    JniEnvWrapper env;
    jobject jhostView = ((java_weak_ref *) textView)->obj();
    jclass jhostViewClass = env->GetObjectClass(jhostView);
    LOGD("Text Color size: %d", color.size());
    LOGD("Text hilght Color size: %d", color_hl.size());
    jmethodID jmid = env->GetMethodID(jhostViewClass, "nativeSetTextColor", "(FFFFZ)V");
    if (jmid == NULL) {
        LOGD("Failed!! method nativeSetTextColor not found");
        return;
    }
    if (color.size() == 4) {
        env->CallVoidMethod(jhostView, jmid, (jfloat)color[0], (jfloat)color[1], (jfloat)color[2], (jfloat)color[3], (jboolean)false);
    }
    
}

void setTextHighlightedColor(void * view,  std::vector<float> color)
{

}

void setTextFont(void * view, float fontSize, bool isBold) { //对应移动端默认字体 iOS-> PingFang ，默认字号是17pt
    JniEnvWrapper env;
    jobject jhostView = ((java_weak_ref *)view)->obj();
    jclass jhostViewClass = env->GetObjectClass(jhostView);
    jmethodID jmid = env->GetMethodID(jhostViewClass, "nativeSetTextFont", "(FZ)V");
    if (jmid == NULL) {
        LOGD("Failed!! method nativeSetTextFont not found");
        return;
    }
    env->CallVoidMethod(jhostView, jmid, (jfloat)fontSize, (jboolean)isBold);
}

void setTextNumberOfLines(void *view, float numberOfLines) {
    JniEnvWrapper env;
    jobject jhostView = ((java_weak_ref *)view)->obj();
    jclass jhostViewClass = env->GetObjectClass(jhostView);
    jmethodID jmid = env->GetMethodID(jhostViewClass, "nativeSetTextNumberOfLines", "(F)V");
    if (jmid == NULL) {
        LOGD("Failed!! method nativeSetTextNumberOfLines not found");
        return;
    }
    env->CallVoidMethod(jhostView, jmid, (jfloat)numberOfLines);
}

