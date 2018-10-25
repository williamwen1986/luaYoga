#include "lua_yoga.h"
#include <string>

float getYogaProperty(void * view, YogaType type, std::string propertyName);

void * addView(void * parentView, YogaType type);

void setYogaProperty(void * view, YogaType type, std::string propertyName, float value);

void setBackgroundColor(void * view, float r, float g, float b, float a);
