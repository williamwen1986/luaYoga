/**
 * Copyright 2014-present, Facebook, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the license found in the
 * LICENSE-examples file in the root directory of this source tree.
 */

#import "ViewController.h"

#import <YogaKit/UIView+Yoga.h>

@implementation ViewController

- (void)viewDidLoad
{
    UIView * containView = [[UIScrollView alloc] initWithFrame:CGRectZero];
    UIView *root = containView;
    root.backgroundColor = [UIColor redColor];
    root.yoga.isEnabled = YES;
    root.yoga.width = YGPointValue(self.view.bounds.size.width);
    root.yoga.height = YGPointValue(self.view.bounds.size.height);
    root.yoga.alignItems = YGAlignFlexStart;
    root.yoga.flexDirection = YGFlexDirectionColumn;
//    root.yoga.justifyContent = YGJustifyFlexEnd;

    UITableView *child1 = [UITableView new];
    child1.backgroundColor = [UIColor blueColor];
    child1.yoga.isEnabled = YES;
    child1.yoga.width = YGPointValue(self.view.bounds.size.width);
    child1.yoga.aspectRatio = 3;

    [root addSubview:child1];
    
    UIView *child2 = [UIView new];
    child2.yoga.isEnabled = YES;
    child2.backgroundColor = [UIColor greenColor];
    child2.yoga.flexDirection = YGFlexDirectionRow;

    UILabel * summaryPopularityLabel = [UILabel new];
    summaryPopularityLabel.yoga.isEnabled = YES;
    summaryPopularityLabel.text = @"★★★★★";

    [child2 addSubview:summaryPopularityLabel];
    
    UILabel * summaryInfo = [UILabel new];
    summaryInfo.backgroundColor = [UIColor whiteColor];
    summaryInfo.yoga.isEnabled = YES;
    summaryInfo.yoga.marginLeft = YGPointValue(20);;
    summaryInfo.yoga.paddingRight = YGPointValue(20);
    summaryInfo.text = @"summaryInfo";
    [child2 addSubview:summaryInfo];
    
    [root addSubview:child2];
    
    UIView * space = [UIView new];
    space.backgroundColor = [UIColor yellowColor];
    space.yoga.isEnabled = YES;
    space.yoga.width = YGPointValue(self.view.bounds.size.width);
    space.yoga.height = YGPointValue(20);
    [root addSubview:space];
    
    UILabel * content = [UILabel new];
    content.backgroundColor = [UIColor blueColor];
    content.yoga.isEnabled = YES;
    content.text = @"summaryInfosummaryInfosummaryInfosummaryInfosummaryInfosummaryInfosummaryInfosummaryInfosummaryInfosummaryInfosummaryInfosummaryInfosummaryInfosummaryInfosummaryInfosummaryInfosummaryInfo";
    content.numberOfLines = 2;
    
    [root addSubview:content];
    
    [root.yoga applyLayoutPreservingOrigin:NO];
    
    [self.view addSubview:root];
}


@end
