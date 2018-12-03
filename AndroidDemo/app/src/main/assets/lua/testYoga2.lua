local TAG = "testYoga2"
local yogaBuilder = function(container)
    container.isEnabled = true
    container.alignItems = YGAlignFlexStart
    container.flexDirection = YGFlexDirectionColumn --垂直布局

    -- ImageView 相关
    local backgroundIv = container.addImageView()      --取值->添加图层关系： addImageView()
    backgroundIv.isEnabled = true
    backgroundIv.width = container.width * 0.93;
    backgroundIv.height = container.height * 0.32
    backgroundIv.imagePath = 'zz.png'
    backgroundIv.start = container.width * 0.07 / 2

    local titleTv = container.addTextView()
    titleTv.isEnabled = true
    titleTv.position = YGPositionTypeRelative
    titleTv.top = -160
    titleTv.start = 90
    titleTv.text = 'MOBILE LEGENDS'
    titleTv.textFont = 18
    titleTv.textColor = { color = { r = 1.0, g = 1.0, b = 1.0, a = 1.0 } }

    local descriptionTv = container.addTextView()
    descriptionTv.width = container.width * 0.8
    descriptionTv.isEnabled = true
    descriptionTv.position = YGPositionTypeRelative
    descriptionTv.top = -90
    descriptionTv.start = 90
    descriptionTv.text = '专业的数据分析体系，让你了解你的全面数据'
    descriptionTv.textFont = 12
    descriptionTv.textColor = { color = { r = 0.5, g = 0.5, b = 0.5, a = 1.0 } }
end

return yogaBuilder