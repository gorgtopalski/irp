package com.verallia

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ProductionController {

    ProductionService productionService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10,100)
        respond productionService.list(params), model:[productionCount: productionService.count()]
    }

    def active(Integer max) {
        params.max = Math.min(max ?: 10,100)
        respond productionService.findAllByActive(true,params), model:[productionCount: productionService.count()], view: 'index'
    }

    def show(Long id) {
        respond productionService.get(id)
    }

    def create() {
        respond new Production(params)
    }

    def save(Production production) {
        if (production == null) {
            notFound()
            return
        }

        try {
            productionService.save(production)
        } catch (ValidationException e) {
            respond production.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'production.label', default: 'Production'), production.toString()])
                flash.alert = 'success'
                redirect production
            }
            '*' { respond production, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond productionService.get(id)
    }

    def update(Production production) {
        if (production == null) {
            notFound()
            return
        }

        try {
            productionService.save(production)
        } catch (ValidationException e) {
            respond production.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'production.label', default: 'Production'), production.toString()])
                flash.alert = 'success'
                redirect production
            }
            '*'{ respond production, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        def name =  productionService.get(id).toString()

        productionService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'production.label', default: 'Production'), name])
                flash.alert = 'danger'
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    def search() {
        notFound()
        return
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'production.label', default: 'Production'), params.id])
                flash.alert = 'alert'
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }


}
