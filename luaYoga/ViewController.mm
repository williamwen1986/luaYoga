//
//  ViewController.m
//  luaYoga
//
//  Created by wen william on 2018/10/25.
//  Copyright © 2018年 wen william. All rights reserved.
//

#import "ViewController.h"
#import <YogaKit/UIView+Yoga.h>
#import "LuaYogaView.h"

#include "lua_yoga.h"


@interface ViewController ()

@end

@implementation ViewController

- (void)viewDidLoad {
    LuaYogaView *root = [[LuaYogaView alloc] initWithFrame:CGRectMake(0, 200, self.view.bounds.size.width, self.view.bounds.size.height*2/3)];
    
//    [root loadLua:@"yoga" type:CONTAINER];
//    [root loadLua:@"listView" type:CONTAINER];
    [root loadLua:@"imageView" type:CONTAINER];

    //    UIView * containView = [[UIScrollView alloc] initWithFrame:CGRectZero];
    //    UIView *root = containView;
    //    root.backgroundColor = [UIColor redColor];
    //    root.yoga.isEnabled = YES;
    //    root.yoga.width = YGPointValue(self.view.bounds.size.width);
    //    root.yoga.height = YGPointValue(self.view.bounds.size.height);
    //    root.yoga.alignItems = YGAlignFlexStart;
    //    root.yoga.flexDirection = YGFlexDirectionColumn;
    //    //    root.yoga.justifyContent = YGJustifyFlexEnd;
    //
    //    UITableView *child1 = [UITableView new];
    //    [root addSubview:child1];
    //    child1.backgroundColor = [UIColor blueColor];
    //    child1.yoga.isEnabled = YES;
    //    child1.yoga.width = YGPointValue(self.view.bounds.size.width);
    //    child1.yoga.aspectRatio = 3;
    //
    //
    //
    //    UIView *child2 = [UIView new];
    //    [root addSubview:child2];
    //    child2.yoga.isEnabled = YES;
    //    child2.backgroundColor = [UIColor greenColor];
    //    child2.yoga.flexDirection = YGFlexDirectionRow;
    //
    //    UILabel * summaryPopularityLabel = [UILabel new];
    //    [child2 addSubview:summaryPopularityLabel];
    //    summaryPopularityLabel.yoga.isEnabled = YES;
    //    summaryPopularityLabel.text = @"★★★★★";
    //
    //
    //
    //    UILabel * summaryInfo = [UILabel new];
    //    [child2 addSubview:summaryInfo];
    //    summaryInfo.backgroundColor = [UIColor whiteColor];
    //    summaryInfo.yoga.isEnabled = YES;
    //    summaryInfo.yoga.marginLeft = YGPointValue(20);;
    //    summaryInfo.yoga.paddingRight = YGPointValue(20);
    //    summaryInfo.text = @"summaryInfo";
    //
    //
    //
    //
    //    UIView * space = [UIView new];
    //    [root addSubview:space];
    //    space.backgroundColor = [UIColor yellowColor];
    //    space.yoga.isEnabled = YES;
    //    space.yoga.width = YGPointValue(self.view.bounds.size.width);
    //    space.yoga.height = YGPointValue(20);
    //
    //
    //    UILabel * content = [UILabel new];
    //    [root addSubview:content];
    //    content.backgroundColor = [UIColor blueColor];
    //    content.yoga.isEnabled = YES;
    //    content.text = @"summaryInfosummaryInfosummaryInfosummaryInfosummaryInfosummaryInfosummaryInfosummaryInfosummaryInfosummaryInfosummaryInfosummaryInfosummaryInfosummaryInfosummaryInfosummaryInfosummaryInfo";
    //    content.numberOfLines = 2;
    //
    //    [root.yoga applyLayoutPreservingOrigin:NO];
    [self.view addSubview:root];
    [super viewDidLoad];
    // Do any additional setup after loading the view, typically from a nib.
}


@end
