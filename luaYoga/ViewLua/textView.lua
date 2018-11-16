local yogaBuilder = function (container)

container.backgroundColor = {r=1.0}
container.isEnabled = true
container.alignItems = YGAlignFlexStart
container.flexDirection = YGFlexDirectionColumn





textView = container.addTextView()
textView.isEnabled = true
--textView.backgroundColor = {b=1.0}
textView.width = container.width/2
textView.height = container.width/4
textView.backgroundColor = {r=0.5,g=0.8,b=0.3,a=0.5}
textView.text = 'pengweijun'

rightTextView = container.addTextView()
rightTextView.isEnabled = true
rightTextView.backgroundColor = {g=1.0}
rightTextView.width = container.width/2
rightTextView.height = container.width/4
rightTextView.backgroundColor = {r=0.2,g=0.3,b=0.3,a=0.8}
rightTextView.text = 'pengw'
 


end

return yogaBuilder
