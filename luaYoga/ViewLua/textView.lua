local yogaBuilder = function (container)

container.backgroundColor = {r=1.0}
container.isEnabled = true
container.alignItems = YGAlignFlexStart
container.flexDirection = YGFlexDirectionColumn





textView = container.addTextView()
textView.isEnabled = true
textView.backgroundColor = {b=1.0}
textView.width = container.width/2
textView.height = container.width/4
--textView.backgroundColor = {r=0.5,g=0.8,b=0.3}
textView.text = 'pengweijun'


 


end

return yogaBuilder
