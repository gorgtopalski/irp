package com.verallia

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ModelController {

    ModelService modelService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)

        if (params.sort == 'productions')
        {
            params.remove('sort')
            params.remove('order')
        }

        respond modelService.getWithActiveProductions(params), model:[modelCount: modelService.count()]
    }

    def show(Long id) {

        def model = modelService.get(id)
        if (model)
        {
            def sort = params.sort ?: 'startDate'
            def order = params.order ?: 'desc'

            model.productions = model.productions.sort { a,b ->
                if (order == 'desc')
                {
                    b."$sort" <=> a."$sort"
                }
                else {
                    a."$sort" <=> b."$sort"
                }
            }
        }
        respond model
    }

    def create() {
        respond new Model(params)
    }

    def save(Model model) {
        if (model == null) {
            notFound()
            return
        }

        try {
            modelService.save(model)
        } catch (ValidationException e) {
            respond model.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'model.label', default: 'Model'), model.toString()])
                flash.alert = 'success'
                redirect model
            }
            '*' { respond model, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond modelService.get(id)
    }

    def update(Model model) {
        if (model == null) {
            notFound()
            return
        }

        try {
            modelService.save(model)
        } catch (ValidationException e) {
            respond model.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'model.label', default: 'Model'), model.toString()])
                flash.alert = 'success'
                redirect model
            }
            '*'{ respond model, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        def name =  modelService.get(id).toString()

        modelService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'model.label', default: 'Model'), name])
                flash.alert = 'danger'
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'model.label', default: 'Model'), params.id])
                flash.alert = 'alert'
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }


}
