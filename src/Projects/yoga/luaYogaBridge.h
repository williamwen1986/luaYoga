#include "lua_yoga.h"
#include <string>

float getYogaProperty(void * view, YogaType type, std::string propertyName);

void *addView(void * parentView, YogaType type, void * root);

void setYogaProperty(void * view, YogaType type, std::string propertyName, float value);

void setBackgroundColor(void * view, float r, float g, float b, float a);




//ImageView 相关
void setImageViewContentMode(void * imageView, float contentModeType);

void setImageName(void * imageView,  std::string imageName);

void setImageName_hl(void * imageView,  std::string imageName);

void setCliping(void * parentView,  float isCliping);

void setHighlighted(void * imageView,  float isHighlighted);
