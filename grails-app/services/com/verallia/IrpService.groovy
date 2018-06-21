package com.verallia

import grails.gorm.services.Service

@Service(Irp)
abstract class IrpService {

    abstract Irp get(Serializable id)

    abstract List<Irp> list(Map args)

    abstract Long count()

    abstract void delete(Serializable id)

    abstract Irp save(Irp irp)

    List<Irp> getLast10WhereProductions(Collection<Production> productions)
    {
        Irp.findAllByProductionInListAndPending(productions.toList(),true)
                .sort{ a,b -> b.date <=> a.date}
                .take(10)
    }


}