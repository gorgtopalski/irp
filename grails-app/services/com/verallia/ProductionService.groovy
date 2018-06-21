package com.verallia

import grails.gorm.services.Service

@Service(Production)
abstract class ProductionService {

    abstract Production get(Serializable id)

    abstract List<Production> list(Map args)

    abstract Long count()

    abstract void delete(Serializable id)

    abstract Production save(Production production)

    abstract List<Production> findAllByActive(boolean active, Map args)

    abstract List<Production> findAllByLine(Line line, Map args)

    abstract Long countByActive(boolean active)

    abstract Long countByLine(Line line)
}