package com.verallia

class HomeController {

    ProductionService productionService
    IrpService irpService

    def index() {

        def activeProductions = productionService.findAllByActive(true, [sort: 'line', order: 'asc'])
        def last10Irps = irpService.getLast10WhereProductions(activeProductions)

        respond([productionList: activeProductions, irpList: last10Irps], view: 'index')
    }
}
