local yogaBuilder = function (container)

    PluginUtils_goFlutter("a", "b", "c", "d")
    local result = PluginUtils_test("string", 1, true, 1.1, { a = 1.0, r = 1.0, g = 1.0, b = 1.0 }) -- test(String s, Integer a, Boolean b, Double d, HashMap map)
    --container.backgroundColor = {r=1.0}
    container.isEnabled = true
    container.alignItems = YGAlignFlexStart
    container.flexDirection = YGFlexDirectionColumn

    local textView = container.addTextView()
    textView.isEnabled = true
    textView.width = 300
    textView.height = 20
    textView.text = result
    textView.textTable = {
        fontSize = 16,
        isBold = false,
        alignment = TextAlignmentCenter,
        color = { a = 1.0, r = 1.0, g = 1.0, b = 1.0 }
    }
    textView.backgroundColor = {b=1.0}

    local button = container.addTextView()
    button.isEnabled = true
    button.width = 300
    button.height = 20
    button.text = "add view"
    button.textTable = {
        fontSize = 16,
        isBold = false,
        alignment = TextAlignmentCenter,
        color = { a = 1.0, r = 1.0, g = 1.0, b = 1.0 }
    }
    button.backgroundColor = {g=1.0}
    button.tapFunction = function()
        button.text = "clicked"
        textView.removeFromParent()
        container.reloadYoga()

    end
    button.longPressFunction = function()
        button.text = "button longPressFunction"
        local addTextView = container.addTextView()
        addTextView.isEnabled = true
        addTextView.width = 300
        addTextView.height = 20
        addTextView.text = "added view"
        addTextView.textTable = {
            fontSize = 16,
            isBold = false,
            alignment = TextAlignmentCenter,
            color = { a = 1.0, r = 1.0, g = 1.0, b = 1.0 }
        }
        addTextView.backgroundColor = {b=1.0}
        container.reloadYoga()
    end

    local listView

    listView = container.addListView()

    listView.isEnabled = true
    listView.backgroundColor = {g=1.0}
    listView.width = container.width
    listView.height = container.height
    --listView.seperatorColor = {b=0.5}

    listView.identifier = function (section, row)
        -- It should to use number to identify the item type
        if (row == 0)
        then
            return "test"
        else
            return "test1"
        end
    end

    listView.numberOfGroups = function ()
        return 1
    end

    listView.columnsInGroup = function (group)
        return 2
    end

    listView.itemHeight = function (group, column)
        return 50
    end

    listView.renderItem = function (cell, group ,column)
        cell.isEnabled = true
        cell.width=360
        cell.height=180
        --cell.backgroundColor = {g=0.5, r=1.0, b=0.5}
        cell.flexDirection = YGFlexDirectionColumn
        cell.alignItems = YGAlignCenter
        -- cell 重用 not init 才add，以im为key存储
        --[[
        if not cell.hasInit then
            cell.im = cell.addImageView()
        end
        cell.im.isEnabled = true;
        cell.im.width = 300
        cell.im.imageViewContentMode = ContentModeTopLeft
        cell.im.height = 100
        cell.im.margin = 5
        if column%2 == 0 then
            cell.im.imagePath = 'zz.png'
            cell.im.imageName_hl = 'highlightImage'
        else
            cell.im.imagePath = 'zz.png'
            cell.im.imageName_hl = 'testImg'
        end
        cell.hasInit = true
        --]]

        local backgroundIv = cell.addImageView()      --取值->添加图层关系： addImageView()
        backgroundIv.isEnabled = true
        backgroundIv.width = 334
        backgroundIv.height = 180
        backgroundIv.imagePath = 'tools_card_ml.png'
        backgroundIv.marginStart = 13

        local titleContainer = cell.addContainer()
        titleContainer.width = 334
        titleContainer.height = 45
        titleContainer.isEnabled = true
        titleContainer.alignItems = YGAlignCenter
        titleContainer.flexDirection = YGFlexDirectionColumn
        titleContainer.marginTop = -85
        titleContainer.marginStart = 13

        local titleTv = titleContainer.addTextView()
        titleTv.width = 300
        titleTv.height = 20
        titleTv.text = "Mobile Legends"
        titleTv.textTable = {
            fontSize = 16,
            isBold = false,
            alignment = TextAlignmentCenter,
            color = { a = 1.0, r = 1.0, g = 1.0, b = 1.0 }
        }

        local userInfoContainer = cell.addContainer()
        userInfoContainer.isEnabled = true
        userInfoContainer.width = 334
        userInfoContainer.height = 66
        userInfoContainer.alignItems = YGAlignFlexStart
        userInfoContainer.flexDirection = YGFlexDirectionRow
        userInfoContainer.marginTop = -40
        userInfoContainer.marginStart = 13

        local userAvatarIv = userInfoContainer.addImageView()
        userAvatarIv.width = 46
        userAvatarIv.height = 46
        userAvatarIv.viewCornerRadius = 23
        userAvatarIv.imagePath = 'zz.png'
        userAvatarIv.marginStart = 16

        local userNameContainer = userInfoContainer.addContainer()
        userNameContainer.width = 172
        userNameContainer.height = 36
        userNameContainer.alignItems = YGAlignFlexStart
        userNameContainer.flexDirection = YGFlexDirectionColumn
        userNameContainer.marginStart = 9
        userNameContainer.marginTop = 2

        local userNameTv = userNameContainer.addTextView()
        userNameTv.width = 172
        userNameTv.height = 18
        userNameTv.text = "Gary Wu"
        userNameTv.textTable = {
            fontSize = 15,
            isBold = false,
            alignment = TextAlignmentLeft,
            color = { a = 1.0, r = 1.0, g = 1.0, b = 1.0 },
            numberOfLines = 1
        }

        local nearlyStatusContainer = userNameContainer.addContainer()
        nearlyStatusContainer.width = 172
        nearlyStatusContainer.height = 14
        nearlyStatusContainer.marginTop = 2
        nearlyStatusContainer.alignItems = YGAlignCenter
        nearlyStatusContainer.flexDirection = YGFlexDirectionRow

        local nearlyStatusIv = nearlyStatusContainer.addImageView()
        nearlyStatusIv.width = 12
        nearlyStatusIv.height = 12
        nearlyStatusIv.imagePath = "stars_rise.png"

        local nearlyStatusTv = nearlyStatusContainer.addTextView()
        nearlyStatusTv.width = 172
        nearlyStatusTv.height = 18
        nearlyStatusTv.text = "Nearly status:" .. " " .. "5" .. " " .. "stars"
        nearlyStatusTv.textTable = {
            fontSize = 12,
            isBold = false,
            alignment = TextAlignmentLeft,
            color = { a = 0.6, r = 1.0, g = 1.0, b = 1.0 }
        }

        local rankContainer = userInfoContainer.addContainer()
        rankContainer.width = 60
        rankContainer.height = 64
        rankContainer.marginStart = 13
        rankContainer.alignItems = YGAlignFlexStart
        rankContainer.flexDirection = YGFlexDirectionColumn

        local rankBgIv = rankContainer.addImageView()
        rankBgIv.width = 60
        rankBgIv.height = 52
        rankBgIv.imagePath = "rank_4.png"

        local rankIv = rankContainer.addImageView()
        rankIv.width = 60
        rankIv.height = 52
        rankIv.marginTop = -27
        rankIv.imagePath = "rank_master.png"

        local starsContainer = rankContainer.addContainer()
        starsContainer.width = 60
        starsContainer.height = 9
        starsContainer.marginTop = -12
        starsContainer.alignItems = YGAlignFlexStart
        starsContainer.flexDirection = YGFlexDirectionRow
        starsContainer.justifyContent = YGJustifyCenter

        local starsIv = starsContainer.addImageView()
        starsIv.width = 9
        starsIv.height = 9
        starsIv.imagePath = "star.png"

        local dataContainer = cell.addContainer()
        dataContainer.width = 334
        dataContainer.height = 40
        dataContainer.marginTop = -18
        dataContainer.marginStart = 19
        dataContainer.alignItems = YGAlignFlexStart
        dataContainer.justifyContent = YGJustifySpaceBetween
        dataContainer.flexDirection = YGFlexDirectionRow

        local dataSubContainerWidth = 82
        local dataSubContainerHeight = 40
        local textViewWidth = 70
        local textValueHeight = 22
        local textTipHeight = 14
        local dataSubContainerAlignItems = YGAlignFlexStart
        local dataSubContainerFlexDirection = YGFlexDirectionColumn
        local subContainerTextTable_Value = {
            fontSize = 18,
            alignment = TextAlignmentCenter,
            fontName = "DIN-Bold.otf",
            color = { a = 1.0, r = 1.0, g = 1.0, b = 1.0 }
        }

        local subContainerTextTable_Tip = {
            fontSize = 12,
            alignment = TextAlignmentCenter,
            color = { a = 0.6, r = 1.0, g = 1.0, b = 1.0 }
        }

        local sessionsContainer = dataContainer.addContainer()
        sessionsContainer.width = dataSubContainerWidth
        sessionsContainer.height = dataSubContainerHeight
        sessionsContainer.alignItems = dataSubContainerAlignItems
        sessionsContainer.flexDirection = dataSubContainerFlexDirection

        local sessionsValueTv = sessionsContainer.addTextView()
        sessionsValueTv.width = textViewWidth
        sessionsValueTv.height = textValueHeight
        sessionsValueTv.text = "1490"
        sessionsValueTv.textTable = subContainerTextTable_Value

        local sessionsTv = sessionsContainer.addTextView()
        sessionsTv.width = textViewWidth
        sessionsTv.height = textTipHeight
        sessionsTv.marginTop = 1
        sessionsTv.text = "Sessions"
        sessionsTv.textTable = subContainerTextTable_Tip


        local winRateContainer = dataContainer.addContainer()
        winRateContainer.width = dataSubContainerWidth
        winRateContainer.height = dataSubContainerHeight
        winRateContainer.alignItems = dataSubContainerAlignItems
        winRateContainer.flexDirection = dataSubContainerFlexDirection

        local winRateValueTv = winRateContainer.addTextView()
        winRateValueTv.width = textViewWidth
        winRateValueTv.height = textValueHeight
        winRateValueTv.text = "49" .. "%"
        winRateValueTv.textTable = subContainerTextTable_Value

        local winRateTv = winRateContainer.addTextView()
        winRateTv.width = textViewWidth
        winRateTv.height = textTipHeight
        winRateTv.marginTop = 1
        winRateTv.text = "Win"
        winRateTv.textTable = subContainerTextTable_Tip

        local mvpContainer = dataContainer.addContainer()
        mvpContainer.width = dataSubContainerWidth
        mvpContainer.height = dataSubContainerHeight
        mvpContainer.alignItems = dataSubContainerAlignItems
        mvpContainer.flexDirection = dataSubContainerFlexDirection

        local mvpValueTv = mvpContainer.addTextView()
        mvpValueTv.width = textViewWidth
        mvpValueTv.height = textValueHeight
        mvpValueTv.text = "398"
        mvpValueTv.textTable = subContainerTextTable_Value

        local mvpTv = mvpContainer.addTextView()
        mvpTv.width = textViewWidth
        mvpTv.height = textTipHeight
        mvpTv.marginTop = 1
        mvpTv.text = "MVP"
        mvpTv.textTable = subContainerTextTable_Tip

        local powerContainer = dataContainer.addContainer()
        powerContainer.width = dataSubContainerWidth
        powerContainer.height = dataSubContainerHeight
        powerContainer.alignItems = dataSubContainerAlignItems
        powerContainer.flexDirection = dataSubContainerFlexDirection

        local powerValueTv = powerContainer.addTextView()
        powerValueTv.width = textViewWidth
        powerValueTv.height = textValueHeight
        powerValueTv.marginStart = -6
        powerValueTv.text = "7878"
        powerValueTv.textTable = subContainerTextTable_Value

        local powerTv = powerContainer.addTextView()
        powerTv.width = textViewWidth
        powerTv.height = textTipHeight
        powerTv.marginStart = -6
        powerTv.text = "Power"
        powerTv.textTable = subContainerTextTable_Tip

    end

    listView.didSelect = function (group ,column)
        listView.reload()
        print('didSelect '..group.."-"..column)
    end



end

return yogaBuilder
