package com.verallia

import grails.converters.JSON

class HomeService {

    IrpService irpService
    ProductionService productionService

    Map getIndexData()
    {
        def data = [:]
        def activeProductions = productionService.findAllByActive(true, [sort: 'line', order: 'asc'])

        data.put('productionList', activeProductions)
        data.put('irpList', irpService.getLast10WhereProductions(activeProductions))
        data.put('data', getDataForLastSevenDays() as JSON)
        data.put('stack',getStackedChartDataForActiveProductions() as JSON )

        return data
    }

    private Map getStackedChartDataForActiveProductions()
    {
        def data = [:]

        def active = productionService.findAllByActive(true,[sort: 'line', order: 'asc'])

        data.put('labels', active.model.name)
        def accepted = []
        def rejected = []

        active.each {

            def map = productionService.getProductionMap(it.id)
            accepted << map.stats.accepted
            rejected << map.stats.rejected
        }

        data.put('accepted', accepted)
        data.put('rejected', rejected)

        return data
    }


    private Map getDataForLastSevenDays()
    {
        def data = [:]

        def irps = irpService.allFromLastSevenDays()

        def dates = []
        def dataset = []


        ((new Date()-6)..(new Date())).eachWithIndex { day, index ->

            dates << day.format('dd/MM/yy').toString()
            def filtered = irps.grep { if (it.date.clearTime() == day.clearTime()) it }

            def sum = 0
            filtered?.each {

                if (it.lastPallet && it.firstPallet)
                    sum += it.firstPallet - it.lastPallet +1
                else
                    sum ++
            }
            dataset << sum
        }

        data.put('labels', dates)
        data.put('dataset',dataset)

        return data
    }

    Map search(String query)
    {
        def data = [:]
        query = '%'+query+'%'

        def models = getModels(query)

        data.put('models', models as List<Model>)

        def prod = [:]
        models.each { model -> prod.put(model.name,getProduction(model)) }
        data.put('productions', prod as Map<String,List<Production>>)

        return data
    }

    private List<Model> getModels(String query)
    {
        (Model.findAllByNameIlike(query) + Model.findAllByBlueprintIlike(query)).unique {a,b  -> a.id <=> b.id }
    }

    private List<Production> getProduction(Model model)
    {
        Production.findAllByModel(model, [order: 'desc', sort: 'startDate', max:10])
    }

}
