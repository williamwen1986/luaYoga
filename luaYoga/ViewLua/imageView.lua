local yogaBuilder = function (container)

container.backgroundColor = {r=1.0}
container.isEnabled = true
container.alignItems = YGAlignFlexStart
container.flexDirection = YGFlexDirectionColumn

---[[
-- ImageView 相关
local imageView = container.addImageView()      --取值->添加图层关系： addImageView()
imageView.isEnabled = true
imageView.backgroundColor = {b=1.0}
imageView.width = container.width
imageView.aspectRatio = 4
--imageView.imageName = 'testImg'                 --赋值->图片资源normal：       .imageName = 'testImg'
--imageView.imageName_hl = 'highlightImage'       --赋值->图片资源highlighted：  .imageName_hl = 'highlightImage'
imageView.imageTable = {imageName='testImg',imageName_hl='highlightImage'}
--imageView.imageColorTable = {r=1.0,b=0.5,g_hl=1.0}

imageView.imageViewContentMode = 1              --赋值->填充模式： .imageViewContentMode = 0-12  ( def 0
imageView.cliping = 0                           --赋值->是否裁剪：.cliping = 0/1  ( def 0
imageView.highlighted = 0                       --赋值->是否高亮模式：.highlighted = 0/1  ( def 0

imageView.tapFunction = function ()
	print("imageView did tap")
    imageView.highlighted = 1
	--imageView.removeFromParent()
	container.reloadYoga()
	--listView.reload()
end

imageView.longPressFunction = function ()
	print("imageView did longPress")
end
--]]               


end

return yogaBuilder
