#include "lua_yoga.h"
#include <string>

float getYogaProperty(void * view, YogaType type, std::string propertyName);

void *addView(void * parentView, YogaType type, void * root);

bool setYogaProperty(void * view, YogaType type, std::string propertyName, float value);

void setBackgroundColor(void * view, float r, float g, float b, float a);

void addTapGesture(void * view, void *root);

void addLongPressGesture(void * view, void *root);

void reloadYoga(void * view);

void removeFromParent(void * view);

//list 相关
void setListSeperatorColor(void * view, float r, float g, float b, float a);

void listReload(void * view);

//ImageView 相关
void setImageViewContentMode(void * imageView, float contentModeType);

void setImageName(void * imageView,  std::string imageName);

void setImageName_hl(void * imageView,  std::string imageName);

void setHighlighted(void * imageView,  float isHighlighted);

void setImageTable(void * imageView,
                   std::string imageName_Normal , //普通状态资源名
                   std::string imageName_Highlighted);  //高亮状态


void setImageColorTable(void * imageView,
                        std::vector<float> color,    //普通状态-颜色生成Image
                        std::vector<float> color_hl  //高亮状态-颜色生成Image
                        );


void setCliping(void * parentView,  float isCliping);

void setTextAlignment(void * textView,  float textAlignment);

void setText(void * textView,  std::string imageName_Normal);

void setTextColor(void * view,  std::vector<float> color);

void setTextFont(void * view, float fontSize, bool isBold); //对应移动端默认字体 iOS-> PingFang ，默认字号是17pt

void setTextNumberOfLines(void *view,float numberOfLines);
