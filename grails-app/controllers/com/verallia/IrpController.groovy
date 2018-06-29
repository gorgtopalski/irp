package com.verallia

import grails.validation.ValidationException

import java.text.ParseException
import java.text.SimpleDateFormat

import static org.springframework.http.HttpStatus.*

class IrpController {

    IrpService irpService
    ProductionService productionService
    AuditService auditService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond irpService.list(params), model:[irpCount: irpService.count()]
    }

    def pending(Long production)
    {
        params.sort='date'
        params.order='desc'

        if (production)
        {
            def prod = productionService.get(production)
            respond irpService.findAllByProductionAndPending(prod,true,params)
        }
        else {
            respond irpService.findAllByPending(true,params)
        }
    }

    def show(Long id) {
        respond irpService.get(id)
    }

    def create() {
        def irp = new Irp(params)

        irp.date = irp.date ?: new Date()
        irp.pending = irp.pending ?: true

        respond irp
    }

    def close(Irp irp)
    {
        irp.pending=false

        try {
            irpService.save(irp)
        } catch (ValidationException e) {
            respond irp.errors, view:'create'
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'irp.label', default: 'Irp'), irp.toString()])
        flash.alert = 'success'

        respond irp, view: 'show'
    }

    def createCritical() {
        def irp = new Irp(params)

        irp.date = irp.date ?: new Date()
        irp.critical = irp.critical ?: true
        irp.pending = irp.pending ?: true

        respond irp
    }

    def save(Irp irp) {

        if (irp == null) {
            notFound()
            return
        }

        try {
            irp = fillDatesFromForm(irp)
            irpService.save(irp)
        } catch (ValidationException e) {
            respond irp.errors, view: irp.critical ? 'createCritical' : 'create'
            return
        }

        auditService.createFirstAudit(irp)

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
            irp = fillDatesFromForm(irp)
            irpService.save(irp)
        } catch (ValidationException e) {
            respond irp.errors, view: irp.critical ? 'createCritical' : 'create'
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

    private Irp fillDatesFromForm(Irp irp)
    {
        def sdf = new SimpleDateFormat('dd/MM/yyyy HH:mm')

        if (params.rejectionStart)
        {
            def rejectTime = 'rejectionStart'
            def dateString = params.get(rejectTime).toString().trim() +" "+
                    params.get(rejectTime+"Hours").toString() +":"+
                    params.get(rejectTime+"Minutes").toString()

            try {
                irp.rejectionStart = sdf.parse(dateString)
            }
            catch(ParseException ex) {}
        }

        if (params.rejectionEnd)
        {
            def rejectTime = 'rejectionEnd'
            def dateString = params.get(rejectTime).toString().trim() +" "+
                    params.get(rejectTime+"Hours").toString() +":"+
                    params.get(rejectTime+"Minutes").toString()

            try {
                irp.rejectionEnd = sdf.parse(dateString)
            }
            catch(ParseException ex) {}
        }

        if (params.lnmRejectionStart)
        {
            def rejectTime = 'lnmRejectionStart'
            def dateString = params.get(rejectTime).toString().trim() +" "+
                    params.get(rejectTime+"Hours").toString() +":"+
                    params.get(rejectTime+"Minutes").toString()

            try {
                irp.rejectionEnd = sdf.parse(dateString)
            }
            catch(ParseException ex) {}
        }

        if (params.lnmRejectionEnd)
        {
            def rejectTime = 'lnmRejectionEnd'
            def dateString = params.get(rejectTime).toString().trim() +" "+
                    params.get(rejectTime+"Hours").toString() +":"+
                    params.get(rejectTime+"Minutes").toString()

            try {
                irp.rejectionEnd = sdf.parse(dateString)
            }
            catch(ParseException ex) {}
        }

        return irp
    }

}
