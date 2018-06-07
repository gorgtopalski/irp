package com.verallia

import grails.gorm.services.Service

@Service(Model)
abstract class ModelService {

    abstract Model get(Serializable id)

    abstract List<Model> list(Map args)

    abstract Long count()

    abstract void delete(Serializable id)

    abstract Model save(Model model)

}