package com.verallia

import grails.validation.ValidationException
import groovy.time.TimeCategory

import static org.springframework.http.HttpStatus.*

class IrpController {

    IrpService irpService
    ProductionService productionService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond irpService.list(params), model:[irpCount: irpService.count()]
    }

    def show(Long id) {
        respond irpService.get(id)
    }

    def create() {
        def irp = new Irp(params)

        irp.date = irp.date ?: new Date()

        if (params.production.id )
            irp.production = productionService.get(params.production.id as Serializable)

        respond irp
    }

    def save(Irp irp) {
        if (irp == null) {
            notFound()
            return
        }

        try {
            irpService.save(irp)
        } catch (ValidationException e) {
            respond irp.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'irp.label', default: 'Irp'), irp.toString()])
                flash.alert = 'success'
                redirect irp
            }
            '*' { respond irp, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond irpService.get(id)
    }

    def update(Irp irp) {
        if (irp == null) {
            notFound()
            return
        }

        try {
            irpService.save(irp)
        } catch (ValidationException e) {
            respond irp.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'irp.label', default: 'Irp'), irp.toString()])
                flash.alert = 'success'
                redirect irp
            }
            '*'{ respond irp, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        def name =  irpService.get(id).toString()

        irpService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'irp.label', default: 'Irp'), name])
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
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'irp.label', default: 'Irp'), params.id])
                flash.alert = 'alert'
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }


}
