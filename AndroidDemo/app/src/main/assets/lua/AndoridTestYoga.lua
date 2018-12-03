local TAG = "AndroidTestYoga"
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

    local tvContainer = container.addContainer()
    tvContainer.isEnabled = true
    tvContainer.alignItems = YGAlignFlexStart
    tvContainer.flexDirection = YGFlexDirectionColumn --垂直布局
    tvContainer.width = container.width
    tvContainer.height = container.height * 0.5
    tvContainer.backgroundColor = { a = 1.0, r = 1.0, g = 0.0, b = 0.0 }

    local titleTv = tvContainer.addTextView()
    titleTv.isEnabled = true
    titleTv.text = 'MOBILE LEGENDS'
    titleTv.textTable = { color = { a = 1.0, r = 1.0, g = 1.0, b = 1.0 },
                          alignment = TextAlignmentCenter,
                          fontSize = 18, isBold = true }

    local descriptionTv = tvContainer.addTextView()
    descriptionTv.width = tvContainer.width * 0.8
    descriptionTv.isEnabled = true
    descriptionTv.text = '专业的数据分析体系，让你了解你的全面数据'
    descriptionTv.textTable = {
        fontSize = 12,
        isBold = false,
        alignment = TextAlignmentCenter,
        color = { r = 0.5, g = 0.5, b = 0.5, a = 1.0 }
    }
end

return yogaBuilder