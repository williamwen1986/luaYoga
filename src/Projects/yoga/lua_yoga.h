#pragma once
extern "C" {
#include "lua.h"
}

#include <vector>
#include <string>

#define LUA_YOGA_VIEW_METATABLE_NAME "lua.yoga.view"
#define LUA_YOGA_FUNCTION_METATABLE_NAME "lua.yoga.function"
#define LUA_YOGA_DYNAMIC_FUNCTION_METATABLE_NAME "lua.yoga.dynamic.function"
 
enum YogaType {
    CONTAINER,
    IMAGE,
    TEXT,
    BUTTON,
    SVGA,
    LIST,
    COLLECTIONVIEW,
    SCROLLVIEW,
    OTHER
};

enum LuaValueType {
    Value_String,
    Value_Number,
    Value_Boolean,
};

struct YogaInfo {
    void * view;
    bool isDead;
    YogaType type;
    void * root;
};

struct LuaModel {
    LuaValueType type;
    std::string value_string;
    bool value_bool;
    float value_float;
};



#define IS_DEAD "isDead"

#pragma mark - 图层添加方法
#define ADD_CONTAINER "addContainer"
#define ADD_ListView "addListView"
#define ADD_ImageView "addImageView"
#define ADD_TEXT "addTextView"
#define ADD_CollectionView "addCollectionView"



#pragma mark - 通用属性
#define ALPHA "alpha"
#define BACKGROUND_COLOR "backgroundColor"
#define WIDTH "width"
#define HEIGHT "height"



#pragma mark - Action相关
#define TAP_FUNCTION "tapFunction"
#define LONGPRESS_FUNCTION "longPressFunction"
#define RELOAD_YOGA "reloadYoga"
#define REMOVE_FROM_PARENT "removeFromParent"



#pragma mark - View相关
#define View_Cliping "cliping"
#define View_Highlighted "highlighted"
#define View_CornerRadius "viewCornerRadius"




#pragma mark - ImageView相关
#define ImageView_Image "imageTable"
#define ImageView_ColorImage "imageColorTable"

#define ImageView_Name          "imageName"
#define ImageView_Name_HL       "imageName_hl"
#define ImageView_ContentMode   "imageViewContentMode"
#define ImageView_Path          "imagePath"



#pragma mark - Text/UILabel 相关

#define Text_Text           "text"
#define Text_TextFont       "textFont"
#define Text_TextColor      "textColor"
#define Text_TextHighlightedColor      "textHighlightedColor"


#define Text_Alignment      "textAlignment"
#define Text_NumberOfLines  "numberOfLines"
#define Text_Table          "textTable"




#pragma mark - ListView/TableView 相关
#define List_NumberOfGroups "numberOfGroups"
#define List_Identifier "identifier"
#define List_ColumnsInGroup "columnsInGroup"
#define List_RenderItem "renderItem"
#define List_ItemHeight "itemHeight"
#define List_GroupHeaderHeight "groupHeaderHeight"
#define List_GroupFooterHeight "groupFooterHeight"
#define List_GroupHeader "groupHeader"
#define List_GroupFooter "groupFooter"
#define List_DidSelect "didSelect"
#define List_SeperatorColor "seperatorColor"
#define List_Reload "reload"



#pragma mark - YGLayout 相关

#define YOGA_IS_ENABLE "isEnabled"
#define YOGA_FLEX_DIRECTION "flexDirection"
#define YOGA_JUSTIFY_CONTENT "justifyContent"
#define YOGA_ALIGN_CONTENT "alignContent"
#define YOGA_ALIGN_ITEMS "alignItems"
#define YOGA_ALIGN_SELF "alignSelf"
#define YOGA_POSITION "position"
#define YOGA_FLEX_WRAP "flexWrap"
#define YOGA_OVERFLOW "overflow"
#define YOGA_DISPLAY "display"
#define YOGA_FLEX_GROW "flexGrow"
#define YOGA_FLEX_SHRINK "flexShrink"
#define YOGA_FLEX_BASIS "flexBasis"
#define YOGA_LEFT "left"
#define YOGA_TOP "top"
#define YOGA_RIGHT "right"
#define YOGA_BOTTOM "bottom"
#define YOGA_START "start"
#define YOGA_END "end"
#define YOGA_MARGIN_LEFT "marginLeft"
#define YOGA_MARGIN_TOP "marginTop"
#define YOGA_MARGIN_RIGHT "marginRight"
#define YOGA_MARGIN_BOTTOM "marginBottom"
#define YOGA_MARGIN_START "marginStart"
#define YOGA_MARGIN_END "marginEnd"
#define YOGA_MARGIN_HORIZONTAL "marginHorizontal"
#define YOGA_MARGIN_VERTICAL "marginVertical"
#define YOGA_MARGIN "margin"
#define YOGA_PADDING_LEFT "paddingLeft"
#define YOGA_PADDING_TOP "paddingTop"
#define YOGA_PADDING_RIGHT "paddingRight"
#define YOGA_PADDING_BOTTOM "paddingBottom"
#define YOGA_PADDING_START "paddingStart"
#define YOGA_PADDING_END "paddingEnd"
#define YOGA_PADDING_HORIZONTAL "paddingHorizontal"
#define YOGA_PADDING_VERTICAL "paddingVertical"
#define YOGA_PADDING "padding"
#define YOGA_BORDER_LEFT "borderLeftWidth"
#define YOGA_BORDER_TOP "borderTopWidth"
#define YOGA_BORDER_RIGHT "borderRightWidth"
#define YOGA_BORDER_BOTTOM "borderBottomWidth"
#define YOGA_BORDER_START "borderStartWidth"
#define YOGA_BORDER_END "borderEndWidth"
#define YOGA_BORDER "borderWidth"
#define YOGA_WIDTH "width"
#define YOGA_HEIGHT "height"
#define YOGA_MIN_WIDTH "minWidth"
#define YOGA_MIN_HEIGHT "minHeight"
#define YOGA_MAX_WIDTH "maxWidth"
#define YOGA_MAX_HEIGHT "maxHeight"
#define YOGA_ASPECT_RATIO "aspectRatio"

extern int luaopen_yoga(lua_State* L);
extern int luaopen_yoga_func(lua_State *L);
extern int luaopen_dynamic_func(lua_State *L);
extern void callbackToYoga(int type, void * v);
extern void registerFunction(lua_State *L, const char* simpleClassNames[], const char* classNames[], const char* methodNames[], const char* returnNames[], const char* paramsNames[], const char* methodSignatures[]);

