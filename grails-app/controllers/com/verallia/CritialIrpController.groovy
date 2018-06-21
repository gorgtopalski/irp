package com.verallia

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class CritialIrpController {

    CritialIrpService critialIrpService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond critialIrpService.list(params), model:[critialIrpCount: critialIrpService.count()]
    }

    def show(Long id) {
        respond critialIrpService.get(id)
    }

    def create() {
        respond new CritialIrp(params)
    }

    def save(CritialIrp critialIrp) {
        if (critialIrp == null) {
            notFound()
            return
        }

        try {
            critialIrpService.save(critialIrp)
        } catch (ValidationException e) {
            respond critialIrp.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'critialIrp.label', default: 'CritialIrp'), critialIrp.toString()])
                flash.alert = 'success'
                redirect critialIrp
            }
            '*' { respond critialIrp, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond critialIrpService.get(id)
    }

    def update(CritialIrp critialIrp) {
        if (critialIrp == null) {
            notFound()
            return
        }

        try {
            critialIrpService.save(critialIrp)
        } catch (ValidationException e) {
            respond critialIrp.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'critialIrp.label', default: 'CritialIrp'), critialIrp.toString()])
                flash.alert = 'success'
                redirect critialIrp
            }
            '*'{ respond critialIrp, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        def name =  critialIrpService.get(id).toString()

        critialIrpService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'critialIrp.label', default: 'CritialIrp'), name])
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
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'critialIrp.label', default: 'CritialIrp'), params.id])
                flash.alert = 'alert'
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }


}
