
#include "lua_yoga.h"
#include "luaYogaBridge.h"
#include <vector>
#include <string>
#include <tuple>




static float lua_heightForText(std::string text,float textWidth){
    return heightForTextTable(text, textWidth, 17.0, "");
}

static float lua_heightForTextTable(std::string text,float textWidth,float textFontSize,std::string fontName){
    return heightForTextTable(text, textWidth, textFontSize, fontName);
}

static float lua_widthForText(std::string text,float textHeight){
    return widthForTextTable(text, textHeight, 17, "");
}

static float lua_widthForTextTable(std::string text,float textHeight,float textFontSize,std::string fontName){
    return widthForTextTable(text, textHeight, textFontSize, fontName);
}


