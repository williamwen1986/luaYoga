local yogaBuilder = function (container)

	container.backgroundColor = {r=1.0}
	container.isEnabled = true
	container.alignItems = YGAlignFlexStart
	container.flexDirection = YGFlexDirectionColumn

	local child1 = container.addContainer()
	child1.isEnabled = true
	child1.backgroundColor = {b=1.0}
	child1.width = container.width
	child1.aspectRatio = 3

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

end

return yogaBuilder
