package com.verallia

import grails.gorm.services.Service

@Service(Model)
abstract class ModelService {

    abstract Model get(Serializable id)

    abstract List<Model> list(Map args)

    abstract Long count()

    abstract void delete(Serializable id)

    abstract Model save(Model model)

    List<Model> getWithActiveProductions(Map args)
    {
        def list = list(args)

        list.each { model -> model.productions = model.productions.grep { it.active } }

        return list
    }

    List<Model> search(String query)
    {

        query = '%'+query+'%'

        def values = Model.findAllByNameIlike(query)
        values.addAll(Model.findAllByBlueprintIlike(query))
        values = values.unique { a,b -> a.id <=> b.id }

        return values
    }
}