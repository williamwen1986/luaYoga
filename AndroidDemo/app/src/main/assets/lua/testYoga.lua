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
    listView.height = container.height
    listView.seperatorColor = {b=0.5}

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
        cell.backgroundColor = {g=0.5, r=1.0, b=0.5}
        cell.flexDirection = YGFlexDirectionRow
        cell.alignItems = YGAlignFlexStart
        -- cell 重用 not init 才add，以im为key存储
        if not cell.hasInit then
            cell.im = cell.addImageView()
        end
        cell.im.isEnabled = true;
        cell.im.width = 300
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
    end

    listView.didSelect = function (group ,column)
        listView.reload()
        print('didSelect '..group.."-"..column)
    end



end

return yogaBuilder
