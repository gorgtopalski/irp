package com.verallia

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class AuditController {

    AuditService auditService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond auditService.list(params), model:[auditCount: auditService.count()]
    }

    def show(Long id) {
        respond auditService.get(id)
    }

    def create() {
        respond new Audit(params)
    }

    def save(Audit audit) {
        if (audit == null) {
            notFound()
            return
        }

        try {
            auditService.save(audit)
            auditService.updatePalets(audit)
        } catch (ValidationException e) {
            respond audit.errors, view:'create'
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'audit.label', default: 'Audit'), audit.toString()])
        flash.alert = 'success'
        redirect(controller: "irp", action: "show", id: audit.irp.id)
    }

    def edit(Long id) {
        respond auditService.get(id)
    }

    def update(Audit audit) {
        if (audit == null) {
            notFound()
            return
        }

        try {
            auditService.save(audit)
            auditService.updatePalets(audit)
        } catch (ValidationException e) {
            respond audit.errors, view:'edit'
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'audit.label', default: 'Audit'), audit.toString()])
        flash.alert = 'success'
        redirect(controller: "irp", action: "show", id: audit.irp.id)
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        def name =  auditService.get(id).toString()

        auditService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'audit.label', default: 'Audit'), name])
                flash.alert = 'danger'
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'audit.label', default: 'Audit'), params.id])
                flash.alert = 'alert'
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }


}
