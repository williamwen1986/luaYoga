//
//  YogaViewController.m
//  luaYoga
//
//  Created by wen william on 2019/1/16.
//  Copyright © 2019年 wen william. All rights reserved.
//

#import "YogaViewController.h"
#import "LuaYogaView.h"
@interface YogaViewController ()

@end

@implementation YogaViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    if (self.customTitle.length) {
        [self.navigationController setTitle:self.customTitle];
    }
    LuaYogaView *yogaView = [[LuaYogaView alloc] initWithFrame:self.view.bounds];
    [self.view addSubview:yogaView];
    
    if (self.lua.length) {
        [yogaView loadLuaStr:self.lua];
    } else if(self.moduleName.length){
        [yogaView loadLua:self.moduleName];
    }
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
