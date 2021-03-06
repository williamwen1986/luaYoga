local yogaBuilder = function (container)

container.backgroundColor = {r=1.0}
container.isEnabled = true
container.alignItems = YGAlignFlexStart
container.flexDirection = YGFlexDirectionColumn
 

local listView

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
    showToast('啊点了第'..group..column)
	print('didSelect '..group.."-"..column)
end

 

end

return yogaBuilder
