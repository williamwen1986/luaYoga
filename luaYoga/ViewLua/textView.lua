local yogaBuilder = function (container)

container.backgroundColor = {r=.9,g=0.5}
container.isEnabled = true
container.alignItems = YGAlignFlexStart
container.flexDirection = YGFlexDirectionColumn





textView = container.addTextView()
textView.isEnabled = true
--textView.backgroundColor = {b=1.0}
textView.width = container.width/2
textView.height = container.width/4
textView.backgroundColor = {r=0.5,g=0.8,b=0.3,a=0.5}
local textContent = 'pengweijun is A developer'

textView.text = textContent

--textH = heightForTextTable({text=textContent,textWidth=100,textFontSize=20,fontName='PingFang'})

textH = heightForTextTable({text=textContent,textWidth=(container.width/2),fontSize=30,fontName='PingFang'})


--textW = widthForTextTable({text='pengweijun',textHeight=20,textFontSize=10,fontName='PingFang'})

textView.height = textH

--textView.textAlignment = 1;
--textView.textColor = {color={r=1.0}};
--textView.textFont = 30;
textView.textTable = {color={r=1.0},fontSize=30,isBold=true,alignment=2};





rightTextView = container.addTextView()
rightTextView.isEnabled = true
rightTextView.backgroundColor = {g=1.0}
rightTextView.width = container.width/2
rightTextView.height = container.width/4
rightTextView.backgroundColor = {r=0.2,g=0.3,b=0.3,a=0.8}


local longTextContent = 'pengwpengwpengwpengwpeAF3DBCB3wpengwpengwAF3DBCB3sdadAF3DBCB3AF3DBCB3asdasdpengwpengwpengwpengwpeAF3DBCB3wpengwpengwAF3DBCB3sdadAF3DBCB3AF3DBCB3asdas'

rightTextView.text = longTextContent

longTextH = heightForTextTable({text=longTextContent,textWidth=(container.width/2),fontSize=20,fontName='PingFang'})

rightTextView.height = longTextH

rightTextView.numberOfLines = 0;



end

return yogaBuilder
