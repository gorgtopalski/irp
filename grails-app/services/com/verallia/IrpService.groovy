package com.verallia

import grails.gorm.services.Service

@Service(Irp)
abstract class IrpService {

    abstract Irp get(Serializable id)

    abstract List<Irp> list(Map args)

    abstract Long count()

    abstract void delete(Serializable id)

    abstract Irp save(Irp irp)

    List<Irp> getLast10WhereProductions(List<Production> productions)
    {
        Irp.findAllByProductionInListAndPending(productions,true, [sort: 'date', order: 'desc', max: 10])
    }

    List<Irp> findAllByProductionAndPending(Production production, boolean pending, Map args)
    {
        Irp.findAllByProductionAndPending(production,pending,args)
    }

    List<Irp> allFromLastSevenDays()
    {
        Irp.findAllByDateBetween(new Date()-6, new Date())
    }

    List<Irp> search(String query)
    {
        println  query
    }

    abstract List<Irp> findAllByPending(boolean pending, Map args)

}