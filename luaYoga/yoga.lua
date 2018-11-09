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
imageView.imageTable = {r=1.0,b=0.5}

imageView.imageViewContentMode = 1              --赋值->填充模式： .imageViewContentMode = 0-12  ( def 0
imageView.cliping = 0                           --赋值->是否裁剪：.cliping = 0/1  ( def 0
--imageView.highlighted = 1                       --赋值->是否高亮模式：.highlighted = 0/1  ( def 0

local listView
imageView.tapFunction = function ()
	print("imageView did tap")
	--imageView.removeFromParent()
	container.reloadYoga()
	listView.reload()
end

imageView.longPressFunction = function ()
	print("imageView did longPress")
end
--]]               

listView = container.addListView()

listView.isEnabled = true
listView.backgroundColor = {g=1.0}
listView.width = container.width
listView.height = 400
listView.seperatorColor = {b=0.5}

listView.identifier = function (section, row)
	return "test"
end

listView.numberOfGroups = function ()
	return 2
end

listView.columnsInGroup = function (group)
	return 10
end

listView.itemHeight = function (group,column)
	return 50
end

listView.groupHeaderHeight = function (group)
	return 10
end

listView.groupFooterHeight = function (group)
	return 0
end

listView.groupHeader = function (headerView)
	headerView.isEnabled = true
	headerView.backgroundColor = {g=1.0, r=0.5}
end

listView.groupFooter = function (footerView)
	footerView.isEnabled = true
	footerView.backgroundColor = {g=0.5, r=1.0}
end

listView.renderItem = function (cell, group ,column)
	cell.isEnabled = true
	cell.backgroundColor = {g=0.5, r=1.0, b=0.5}
	cell.flexDirection = YGFlexDirectionRow
	cell.alignItems = YGAlignFlexStart
	-- cell 重用 not init 才add，以im为key存储
	if not cell.hasInit then
		cell.im = cell.addImageView()
	end
	cell.im.isEnabled = true;
	cell.im.width = 30
	cell.im.height = 30
	cell.im.margin = 5
	if column%2 == 0 then
		cell.im.imageName = 'testImg'
		cell.im.imageName_hl = 'highlightImage' 
	else
		cell.im.imageName = 'highlightImage' 
		cell.im.imageName_hl = 'testImg'
	end
	cell.hasInit = true
end

listView.didSelect = function (group ,column)
	listView.reload()
	print('didSelect '..group.."-"..column)
end



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
