//
//  DemoNavigationViewController.m
//  luaYoga
//
//  Created by wen william on 2019/1/16.
//  Copyright © 2019年 wen william. All rights reserved.
//

#import "DemoNavigationViewController.h"
#import "ViewController.h"
@interface DemoNavigationViewController ()

@end

@implementation DemoNavigationViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    ViewController * c = [[ViewController alloc] init];
    [self pushViewController:c animated:NO];
    // Do any additional setup after loading the view.
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
