local mlDataSource = {}
local listView
local getRankTable = function()
    if mlDataSource ~= null then
        if mlDataSource["MLRank"] then
            local mlRank = cjson.decode(mlDataSource["MLRank"])
            return mlRank
        end
    end
end

local observeReloadNotification = function()
    lua_notification.createListener(function (l)
        local listener = l
        listener:AddObserver(3,
                function (data)
                    print("testNotification postNotification")
                    if data then
                        for k,v in pairs(data) do
                            mlDataSource[k] = v
                            if listView == null then
                                print("listView null")
                            end
                            listView.reload()
                            print("testYoga test")
                           -- print("mlDataSource" .. mlDataSource[k])
                        end
                    end
                end
        )
    end);
end

local yogaBuilder = function(container)
    --container.backgroundColor = {r=1.0}
    container.isEnabled = true
    container.alignItems = YGAlignFlexStart
    container.flexDirection = YGFlexDirectionColumn
    observeReloadNotification()
    listView = container.addListView()

    listView.isEnabled = true
    listView.backgroundColor = { g = 1.0 }
    listView.width = container.width
    listView.height = container.height
    --listView.seperatorColor = {b=0.5}
    listView.identifier = function(section, row)
        -- It should to use number to identify the item type
        if (row == 0)
        then
            return "test"
        else
            return "test1"
        end
    end

    listView.numberOfGroups = function()
        return 1
    end

    listView.columnsInGroup = function(group)
        return 2
    end

    listView.itemHeight = function(group, column)
        return 50
    end

    listView.renderItem = function(cell, group, column)
        print("testYoga test 0")
        cell.isEnabled = true
        cell.width = 360
        cell.height = 180
        --cell.backgroundColor = {g=0.5, r=1.0, b=0.5}
        cell.flexDirection = YGFlexDirectionColumn
        cell.alignItems = YGAlignCenter
        cell.backgroundColor = { a = 1.0, r = 0.114, g = 0.122, b = 0.129 }

        -- cell 重用 not init 才add，以im为key存储
        if not cell.hasInit then
            cell.backgroundIv = cell.addImageView()
            cell.allContentContainer = cell.addContainer()
            cell.titleContainer = cell.allContentContainer.addContainer()
            cell.titleTv = cell.titleContainer.addTextView()
            cell.userInfoContainer = cell.allContentContainer.addContainer()
            cell.userAvatarIv = cell.userInfoContainer.addImageView()
            cell.userNameContainer = cell.userInfoContainer.addContainer()
            cell.userNameTv = cell.userInfoContainer.addTextView()
            cell.nearlyStatusContainer = cell.userNameContainer.addContainer()
            cell.nearlyStatusIv = cell.nearlyStatusContainer.addImageView()
            cell.nearlyStatusTv = cell.nearlyStatusContainer.addTextView()
            cell.rankContainer = cell.userInfoContainer.addContainer()
            cell.rankBgIv = cell.rankContainer.addImageView()
            cell.rankIv = cell.rankContainer.addImageView()
            cell.dataContainer = cell.allContentContainer.addContainer()
            cell.starsContainer = cell.rankContainer.addContainer()
            cell.starsContainer = cell.rankContainer.addContainer()
            cell.winLossContainer = cell.dataContainer.addContainer()
            cell.winLostValueTv = cell.winLossContainer.addTextView()
            cell.winLossTv = cell.winLossContainer.addTextView()
            cell.killsContainer = cell.dataContainer.addContainer()
            cell.killsValueTv = cell.killsContainer.addTextView()
            cell.killsTv = cell.killsContainer.addTextView()
            cell.assistsContainer = cell.dataContainer.addContainer()
            cell.assistsValueTv = cell.assistsContainer.addTextView()
            cell.assistsTv = cell.assistsContainer.addTextView()
            cell.ratingContainer = cell.dataContainer.addContainer()
            cell.ratingValueTv = cell.ratingContainer.addTextView()
            cell.ratingTv = cell.ratingContainer.addTextView()
        end

        cell.backgroundIv.isEnabled = true
        cell.backgroundIv.width = 334
        cell.backgroundIv.height = 180
        cell.backgroundIv.imagePath = 'tools_card_ml.png'

        cell.allContentContainer.width = 334
        cell.allContentContainer.height = 180
        cell.allContentContainer.marginTop = -180
        cell.allContentContainer.alignItems = YGAlignCenter
        cell.allContentContainer.flexDirection = YGFlexDirectionColumn

        cell.titleContainer.width = 334
        cell.titleContainer.height = 45
        cell.titleContainer.isEnabled = true
        cell.titleContainer.alignItems = YGAlignCenter
        cell.titleContainer.flexDirection = YGFlexDirectionColumn
        cell.titleContainer.marginTop = 14

        cell.titleTv.width = 300
        cell.titleTv.height = 20
        cell.titleTv.text = "Mobile Legends"
        cell.titleTv.textTable = {
            fontSize = 16,
            isBold = false,
            alignment = TextAlignmentCenter,
            color = { a = 1.0, r = 1.0, g = 1.0, b = 1.0 }
        }
        cell.userInfoContainer.isEnabled = true
        cell.userInfoContainer.width = 334
        cell.userInfoContainer.height = 66
        cell.userInfoContainer.alignItems = YGAlignFlexStart
        cell.userInfoContainer.flexDirection = YGFlexDirectionRow

        cell.userAvatarIv.width = 46
        cell.userAvatarIv.height = 46
        cell.userAvatarIv.viewCornerRadius = 23
        print("testYoga test 1")
        if mlDataSource then
            if mlDataSource["userIcon"] then
                cell.userAvatarIv.imagePath = mlDataSource["userIcon"]
            else
                cell.userAvatarIv.imagePath = 'zz.png'
            end
        end
        print("testYoga test 2")
        cell.userAvatarIv.marginStart = 16

        cell.userNameContainer.width = 172
        cell.userNameContainer.height = 36
        cell.userNameContainer.alignItems = YGAlignFlexStart
        cell.userNameContainer.flexDirection = YGFlexDirectionColumn
        cell.userNameContainer.marginStart = 9
        cell.userNameContainer.marginTop = 4

        cell.userNameTv.width = 172
        cell.userNameTv.height = 18
        if mlDataSource then
            if mlDataSource.nickName then
                print("nickName test: " .. mlDataSource["nickName"])
                cell.userNameTv.text = mlDataSource.nickName
            else
                cell.userNameTv.text = "---"
            end
        end
        print("testYoga test 3")
        cell.userNameTv.textTable = {
            fontSize = 15,
            isBold = false,
            alignment = TextAlignmentLeft,
            color = { a = 1.0, r = 1.0, g = 1.0, b = 1.0 },
            numberOfLines = 1
        }

        cell.nearlyStatusContainer.width = 172
        cell.nearlyStatusContainer.height = 14
        cell.nearlyStatusContainer.marginTop = 2
        cell.nearlyStatusContainer.alignItems = YGAlignCenter
        cell.nearlyStatusContainer.flexDirection = YGFlexDirectionRow

        cell.nearlyStatusIv.width = 12
        cell.nearlyStatusIv.height = 12
        -- 读取最近上升还是下降
        cell.nearlyStatusIv.imagePath = "stars_rise.png"

        print("testYoga test 4")
        cell.nearlyStatusTv.width = 172
        cell.nearlyStatusTv.height = 18
        --读取最近等级状态
        cell.nearlyStatusTv.text = "Nearly status:" .. " " .. "-" .. " " .. "stars"
        cell.nearlyStatusTv.textTable = {
            fontSize = 12,
            isBold = false,
            alignment = TextAlignmentLeft,
            color = { a = 0.6, r = 1.0, g = 1.0, b = 1.0 }
        }

        print("testYoga test 5")
        cell.rankContainer.width = 60
        cell.rankContainer.height = 64
        cell.rankContainer.marginStart = 15
        cell.rankContainer.alignItems = YGAlignFlexStart
        cell.rankContainer.flexDirection = YGFlexDirectionColumn

        cell.rankBgIv.width = 60
        cell.rankBgIv.height = 52
        -- 根据等级匹配图片
        cell.rankBgIv.imagePath = "rank_4.png"

        cell.rankIv.width = 60
        cell.rankIv.height = 52
        cell.rankIv.marginTop = -52
        -- 根据等级匹配图片
        cell.rankIv.imagePath = "rank_master.png"

        cell.starsContainer.width = 60
        cell.starsContainer.height = 9
        cell.starsContainer.alignItems = YGAlignFlexStart
        cell.starsContainer.flexDirection = YGFlexDirectionRow
        cell.starsContainer.justifyContent = YGJustifyCenter

        -- 根据星星数量增加星星数量
        --if mlRank ~= null then
        --    local stars = mlRank["star"]
        --    if stars ~= null and stars > 0 then
        --        for i = 1, stars do
        --            local starsIv = starsContainer.addImageView()
        --            starsIv.width = 9
        --            starsIv.height = 9
        --            starsIv.imagePath = "star.png"
        --        end
        --    end
        --end

        cell.dataContainer.width = 334
        cell.dataContainer.height = 44
        cell.dataContainer.marginTop = 6
        cell.dataContainer.alignItems = YGAlignCenter
        cell.dataContainer.justifyContent = YGJustifySpaceBetween
        cell.dataContainer.flexDirection = YGFlexDirectionRow

        local dataSubContainerWidth = 82
        local dataSubContainerHeight = 44
        local textViewWidth = 82
        local textValueHeight = 22
        local textTipHeight = 16
        local dataSubContainerAlignItems = YGAlignCenter
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


        cell.winLossContainer.width = dataSubContainerWidth
        cell.winLossContainer.height = dataSubContainerHeight
        cell.winLossContainer.alignItems = dataSubContainerAlignItems
        cell.winLossContainer.flexDirection = dataSubContainerFlexDirection

        cell.winLostValueTv.width = textViewWidth
        cell.winLostValueTv.height = textValueHeight
        --读取最近十场胜负数
        cell.winLostValueTv.text = "---"
        cell.winLostValueTv.textTable = subContainerTextTable_Value

        cell.winLossTv.width = textViewWidth
        cell.winLossTv.height = textTipHeight
        cell.winLossTv.marginTop = 1
        --多语言
        cell.winLossTv.text = "Win/Loss"
        cell.winLossTv.textTable = subContainerTextTable_Tip

        cell.killsContainer.width = dataSubContainerWidth
        cell.killsContainer.height = dataSubContainerHeight
        cell.killsContainer.alignItems = dataSubContainerAlignItems
        cell.killsContainer.flexDirection = dataSubContainerFlexDirection

        cell.killsValueTv.width = textViewWidth
        cell.killsValueTv.height = textValueHeight
        -- 最近十场最高击杀
        cell.killsValueTv.text = "---"
        cell.killsValueTv.textTable = subContainerTextTable_Value

        cell.killsTv.width = textViewWidth
        cell.killsTv.height = textTipHeight
        cell.killsTv.marginTop = 1
        -- 多语言
        cell.killsTv.text = "Kills"
        cell.killsTv.textTable = subContainerTextTable_Tip

        cell.assistsContainer.width = dataSubContainerWidth
        cell.assistsContainer.height = dataSubContainerHeight
        cell.assistsContainer.alignItems = dataSubContainerAlignItems
        cell.assistsContainer.flexDirection = dataSubContainerFlexDirection

        cell.assistsValueTv.width = textViewWidth
        cell.assistsValueTv.height = textValueHeight
        --最近十场最高助攻数
        cell.assistsValueTv.text = "---"
        cell.assistsValueTv.textTable = subContainerTextTable_Value

        cell.assistsTv.width = textViewWidth
        cell.assistsTv.height = textTipHeight
        cell.assistsTv.marginTop = 1
        --多语言
        cell.assistsTv.text = "Assists"
        cell.assistsTv.textTable = subContainerTextTable_Tip

        cell.ratingContainer.width = dataSubContainerWidth
        cell.ratingContainer.height = dataSubContainerHeight
        cell.ratingContainer.alignItems = dataSubContainerAlignItems
        cell.ratingContainer.flexDirection = dataSubContainerFlexDirection

        cell.ratingValueTv.width = textViewWidth
        cell.ratingValueTv.height = textValueHeight
        cell.ratingValueTv.marginStart = -6
        --综合评分
        cell.ratingValueTv.text = "---"
        cell.ratingValueTv.textTable = subContainerTextTable_Value

        cell.ratingTv.width = textViewWidth
        cell.ratingTv.height = textTipHeight
        cell.ratingTv.marginStart = -6
        --多语言
        cell.ratingTv.text = "Rating"
        cell.ratingTv.textTable = subContainerTextTable_Tip
        print("testYoga test 6")
        cell.hasInit = true
    end

    listView.didSelect = function(group, column)
        print('LuaYogaDemo_didSelect ' .. group .. "-" .. column)
        if column == 0 then
            local moduleName = "record_search"
            local moduleVersion = ""
            local type = "flutter"
            local url = "www.google.com"
            goFlutter(moduleName, moduleVersion, type, url)
        else
            goFlutter("", "", "", "") --TODO
        end
    end
end

return yogaBuilder
