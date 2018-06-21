package com.verallia

import grails.gorm.services.Service

@Service(Line)
abstract class LineService {

    abstract Line get(Serializable id)

    abstract List<Line> list(Map args)

    abstract Long count()

    abstract void delete(Serializable id)

    abstract Line save(Line line)

}