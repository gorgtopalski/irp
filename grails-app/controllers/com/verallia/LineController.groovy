package com.verallia

import com.verallia.controller.traits.DefaultListSort

class LineController implements DefaultListSort
{

    LineService lineService
    ProductionService productionService

    def show(Long id, Integer max)
    {
        params.max = Math.min(max ?: 10, 100)
        listByStartDateDesc()
        def line = lineService.get(id)
        respond productionService.findAllByLine(line,params),
                model:[productionCount: productionService.countByLine(line), id:id],
                view: '../production/index'
    }
}
