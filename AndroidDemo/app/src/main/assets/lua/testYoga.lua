local yogaBuilder = function(container)

    --container.backgroundColor = { r = 0.5, g = 0.2, b = 0.7 }
    container.isEnabled = true
    container.alignItems = YGAlignFlexStart
    container.flexDirection = YGFlexDirectionColumn --垂直布局

    -- ImageView 相关
    local imageView = container.addImageView()      --取值->添加图层关系： addImageView()
    imageView.isEnabled = true
    imageView.backgroundColor = { r = 0.5, g = 0.2, b = 0.7 }
    imageView.width = container.width;
    imageView.height = container.height/2
    --imageView.width = container.width
    --imageView.aspectRatio = 4
    --imageView.imageName = 'testImg'                 --赋值->图片资源normal：       .imageName = 'testImg'
    --imageView.imageName_hl = 'highlightImage'       --赋值->图片资源highlighted：  .imageName_hl = 'highlightImage'
    --imageView.imageViewContentMode = 1              --赋值->填充模式： .imageViewContentMode = 0-12  ( def 0
    --imageView.cliping = 0                           --赋值->是否裁剪：.cliping = 0/1  ( def 0
    --imageView.highlighted = 1                       --赋值->是否高亮模式：.highlighted = 0/1  ( def 0

    local textView = container.addTextView()
    textView.isEnabled = true
    textView.margin=100
    textView.width = 600.0
    textView.height = 500.0
    textView.backgroundColor = { r = 0.5, g = 0.8, b = 0.3, a = 0.5 }
    textView.text = 'Freeman'
    textView.textFont={fontSize = 80.0, isBold = false}

end

return yogaBuilder