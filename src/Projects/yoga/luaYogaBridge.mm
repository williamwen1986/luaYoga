#include "luaYogaBridge.h"
#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>
#import <YogaKit/UIView+Yoga.h>

float getYogaProperty(void * view, YogaType type, std::string propertyName)
{
    if (propertyName == "width") {
        UIView * v = (__bridge UIView *)view;
        return v.frame.size.width;
    } else if(propertyName == "height"){
        UIView * v = (__bridge UIView *)view;
        return v.frame.size.height;
    } else {
        NSLog(@"luaYogaBridge no such property %s ",propertyName.c_str());
        assert(false);
    }
    return 0;
}

void * mos_addView(void * parentView, YogaType type)
{
    UIView * v = (__bridge UIView *)parentView;
    UIView * child = nil;
    switch (type) {
        case CONTAINER:{
            child = [[UIView alloc] init];
            [v addSubview:child];
        }
            break;
        case IMAGE:{
            child = [[UIImageView alloc]init];
            [v addSubview:child];
        }
            break;
        default:
            break;
    }
    
    return (__bridge void *)child;
}

void setYogaProperty(void * view, YogaType type, std::string propertyName, float value)
{
    UIView * v = (__bridge UIView *)view;
    if (propertyName == YOGA_IS_ENABLE) {
        v.yoga.isEnabled = (BOOL)value;
    } else if(propertyName == YOGA_FLEX_DIRECTION){
        v.yoga.flexDirection = (YGFlexDirection)value;
    } else if(propertyName == YOGA_FLEX_DIRECTION){
        v.yoga.flexDirection = (YGFlexDirection)value;
    } else if(propertyName == YOGA_JUSTIFY_CONTENT){
        v.yoga.justifyContent = (YGJustify)value;
    } else if(propertyName == YOGA_ALIGN_CONTENT){
        v.yoga.alignContent = (YGAlign)value;
    } else if(propertyName == YOGA_ALIGN_ITEMS){
        v.yoga.alignItems = (YGAlign)value;
    } else if(propertyName == YOGA_ALIGN_SELF){
        v.yoga.alignSelf = (YGAlign)value;
    } else if(propertyName == YOGA_POSITION){
        v.yoga.position = (YGPositionType)value;
    } else if(propertyName == YOGA_FLEX_WRAP){
        v.yoga.flexWrap = (YGWrap)value;
    } else if(propertyName == YOGA_OVERFLOW){
        v.yoga.overflow = (YGOverflow)value;
    } else if(propertyName == YOGA_DISPLAY){
        v.yoga.display = (YGDisplay)value;
    } else if(propertyName == YOGA_FLEX_GROW){
        v.yoga.flexGrow = value;
    } else if(propertyName == YOGA_FLEX_SHRINK){
        v.yoga.flexShrink = value;
    } else if(propertyName == YOGA_FLEX_BASIS){
        v.yoga.flexBasis = YGPointValue(value);
    } else if(propertyName == YOGA_LEFT){
        v.yoga.left = YGPointValue(value);
    } else if(propertyName == YOGA_TOP){
        v.yoga.top = YGPointValue(value);
    } else if(propertyName == YOGA_RIGHT){
        v.yoga.right = YGPointValue(value);
    } else if(propertyName == YOGA_BOTTOM){
        v.yoga.bottom = YGPointValue(value);
    } else if(propertyName == YOGA_START){
        v.yoga.start = YGPointValue(value);
    } else if(propertyName == YOGA_END){
        v.yoga.end= YGPointValue(value);
    } else if(propertyName == YOGA_MARGIN_LEFT){
        v.yoga.marginLeft = YGPointValue(value);
    } else if(propertyName == YOGA_MARGIN_TOP){
        v.yoga.marginTop = YGPointValue(value);
    } else if(propertyName == YOGA_MARGIN_RIGHT){
        v.yoga.marginRight = YGPointValue(value);
    } else if(propertyName == YOGA_MARGIN_BOTTOM){
        v.yoga.marginBottom = YGPointValue(value);
    } else if(propertyName == YOGA_MARGIN_START){
        v.yoga.marginStart = YGPointValue(value);
    } else if(propertyName == YOGA_MARGIN_END){
        v.yoga.marginEnd= YGPointValue(value);
    } else if(propertyName == YOGA_MARGIN_HORIZONTAL){
        v.yoga.marginHorizontal = YGPointValue(value);
    } else if(propertyName == YOGA_MARGIN_VERTICAL){
        v.yoga.marginVertical= YGPointValue(value);
    } else if(propertyName == YOGA_MARGIN){
        v.yoga.margin= YGPointValue(value);
    } else if(propertyName == YOGA_PADDING_LEFT){
        v.yoga.paddingLeft = YGPointValue(value);
    } else if(propertyName == YOGA_PADDING_TOP){
        v.yoga.paddingTop = YGPointValue(value);
    } else if(propertyName == YOGA_PADDING_RIGHT){
        v.yoga.paddingRight = YGPointValue(value);
    } else if(propertyName == YOGA_PADDING_BOTTOM){
        v.yoga.paddingBottom = YGPointValue(value);
    } else if(propertyName == YOGA_PADDING_START){
        v.yoga.paddingStart = YGPointValue(value);
    } else if(propertyName == YOGA_PADDING_END){
        v.yoga.paddingEnd= YGPointValue(value);
    } else if(propertyName == YOGA_PADDING_HORIZONTAL){
        v.yoga.paddingHorizontal = YGPointValue(value);
    } else if(propertyName == YOGA_PADDING_VERTICAL){
        v.yoga.paddingVertical= YGPointValue(value);
    } else if(propertyName == YOGA_PADDING){
        v.yoga.padding= YGPointValue(value);
    } else if(propertyName == YOGA_BORDER_LEFT){
        v.yoga.borderLeftWidth = value;
    } else if(propertyName == YOGA_BORDER_TOP){
        v.yoga.borderTopWidth = value;
    } else if(propertyName == YOGA_BORDER_RIGHT){
        v.yoga.borderRightWidth = value;
    } else if(propertyName == YOGA_BORDER_BOTTOM){
        v.yoga.borderBottomWidth = value;
    } else if(propertyName == YOGA_BORDER_START){
        v.yoga.borderStartWidth = value;
    } else if(propertyName == YOGA_BORDER_END){
        v.yoga.borderEndWidth= value;
    } else if(propertyName == YOGA_BORDER){
        v.yoga.borderWidth= value;
    } else if(propertyName == YOGA_WIDTH){
        v.yoga.width = YGPointValue(value);
    } else if(propertyName == YOGA_HEIGHT){
        v.yoga.height = YGPointValue(value);
    } else if(propertyName == YOGA_MIN_WIDTH){
        v.yoga.minWidth = YGPointValue(value);
    } else if(propertyName == YOGA_MIN_HEIGHT){
        v.yoga.minHeight = YGPointValue(value);
    } else if(propertyName == YOGA_MAX_WIDTH){
        v.yoga.maxWidth= YGPointValue(value);
    } else if(propertyName == YOGA_MAX_HEIGHT){
        v.yoga.maxHeight= YGPointValue(value);
    } else if(propertyName == YOGA_ASPECT_RATIO){
        v.yoga.aspectRatio= value;
    } else if(propertyName == ALPHA){
        v.alpha= value;
    } else {
        NSLog(@"luaYogaBridge setYogaProperty no such property %s ",propertyName.c_str());
        assert(false);
    }
}

void setBackgroundColor(void * view, float r, float g, float b, float a)
{
    UIView * v = (__bridge UIView *)view;
    [v setBackgroundColor:[UIColor colorWithRed:r green:g blue:b alpha:a]];
}

void setImageName(void * imageView,  std::string imageName)
{
    UIImageView * v = (__bridge UIImageView *)imageView;
    
    NSString *str= [NSString stringWithFormat:@"%s",imageName.c_str()];
    
    v.image = [UIImage imageNamed:str];
}

void setImageName_hl(void * imageView,  std::string imageName)
{
    UIImageView * v = (__bridge UIImageView *)imageView;
    
    NSString *str= [NSString stringWithFormat:@"%s",imageName.c_str()];
    
    v.highlightedImage = [UIImage imageNamed:str];
}

void setImageViewContentMode(void *imageView, float contentModeType)
{
    UIView * v = (__bridge UIView *)imageView;
    
    v.contentMode = (UIViewContentMode)contentModeType;
}

void setCliping(void * parentView,  float isCliping){
    
    UIView * v = (__bridge UIView *)parentView;
    
    v.clipsToBounds = isCliping;
}

void setHighlighted(void * imageView,  float isHighlighted){
    
    UIImageView * v = (__bridge UIImageView *)imageView;
    
    [v setHighlighted:isHighlighted];
}



