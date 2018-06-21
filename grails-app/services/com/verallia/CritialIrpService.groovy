package com.verallia

import grails.gorm.services.Service

@Service(CritialIrp)
abstract class CritialIrpService {

    abstract CritialIrp get(Serializable id)

    abstract List<CritialIrp> list(Map args)

    abstract Long count()

    abstract void delete(Serializable id)

    abstract CritialIrp save(CritialIrp critialIrp)

}