local yogaBuilder = function (container)

container.backgroundColor = {r=1.0}
container.isEnabled = true
container.alignItems = YGAlignFlexStart
container.flexDirection = YGFlexDirectionColumn

---[[
--ImageView 相关

local imageView = container.addImageView()      --取值->添加图层关系： addImageView()
imageView.isEnabled = true
imageView.backgroundColor = {b=1.0}
imageView.width = container.width
imageView.aspectRatio = 3

imageView.imageName = 'testImg'                 --赋值->图片资源normal：       .imageName = 'testImg'
imageView.imageName_hl = 'highlightImage'       --赋值->图片资源highlighted：  .imageName_hl = 'highlightImage'

imageView.imageViewContentMode = 1              --赋值->填充模式： .imageViewContentMode = 0-12  ( def 0
imageView.cliping = 0                           --赋值->是否裁剪：.cliping = 0/1  ( def 0
imageView.highlighted = 1                       --赋值->是否高亮模式：.highlighted = 0/1  ( def 0
--]]




--[[
local child2 = container.addContainer()
child2.isEnabled = true
child2.width = container.width
child2.backgroundColor = {g=1.0}
child2.flexDirection = YGFlexDirectionRow;
child2.height = 50

local child21 = child2.addContainer()
child21.isEnabled = true
child21.backgroundColor = {}
child21.margin = 10
child21.flexGrow = 1.0

local child22 = child2.addContainer()
child22.isEnabled = true
child22.backgroundColor = {g=0.2}
child22.flexGrow = 2.0

local child23 = child2.addContainer()
child23.isEnabled = true
child23.backgroundColor = {r=0.3,g=0.5}
child23.flexGrow = 1.0
--]]


end

return yogaBuilder
