Pod::Spec.new do |s|

  s.name         = "SODAHUD"
  s.version      = "1.0.1"
  s.summary      = "SODAHUD"
  s.homepage     = "https://github.com/natoto/HBLocalPod"  
  s.license      = "MIT"
  s.author             = { "HUANGBO" => "nonato@foxmail.com" }
  s.platform     = :ios
  s.ios.deployment_target = "7.0"
  s.source       = { :git => "."}
  s.requires_arc = true
  s.source_files  = "*.{h,m}"
  s.public_header_files = "*.h"
  s.framework  = "UIKit","Foundation"
  s.dependency 'MBProgressHUD'

end
