local yogaBuilder = function (container)

container.backgroundColor = {r=1.0}
container.isEnabled = true
container.alignItems = YGAlignFlexStart
container.flexDirection = YGFlexDirectionColumn


local textContent = 'pengweijun is A developer'



textH = heightForTextTable(textContent,100,20)



end

return yogaBuilder
