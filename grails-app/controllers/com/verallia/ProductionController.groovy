package com.verallia

import com.verallia.controller.traits.DefaultListSort
import grails.validation.ValidationException

import java.text.SimpleDateFormat

import static org.springframework.http.HttpStatus.*

class ProductionController implements DefaultListSort
{
    ProductionService productionService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE", closeProduction: 'PUT', updatePallets: 'PUT']

    def index(Integer max) {
        params.max = Math.min(max ?: 10,100)
        listByStartDateAsc()
        respond productionService.list(params), model:[productionCount: productionService.count()]
    }

    def active(Integer max) {
        params.max = Math.min(max ?: 10,100)
        listByStartDateAsc()
        def active = productionService.findAllByActive(true,params)
        respond active, model:[productionCount: productionService.countByActive(true)]
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
            production = fillGaps(production)
            productionService.save(production)
        } catch (ValidationException e) {

            production.errors.each {
                println it
            }

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

    def edit(Long id)
    {
        respond productionService.get(id)
    }

    def close(Long id)
    {
        def production = productionService.get(id)
        if (production)
        {
            production.active = false
            production.finished = true
            production.finishDate = new Date()
        }

        respond production
    }

    def closeProduction(Production production)
    {
        if (production == null) {
            notFound()
            return
        }

        try {
            production.active = false
            production.finished = true
            production.finishDate = new Date()
            production.totalPallets = params.getInt('totalPallets') ?: 0
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

    def update(Production production) {
        if (production == null) {
            notFound()
            return
        }

        try {
            production = fillGaps(production)
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

    def updatePallets(Long id, int pallets)
    {
        def prod = productionService.get(id)

        if (prod)
        {
            if (prod.totalPallets < pallets)
            {
                prod.totalPallets = pallets
                try {
                    productionService.save(prod)
                } catch (ValidationException e) { }
            }

            redirect(controller: 'home')
        }

//        request.withFormat {
//            form multipartForm {
//                flash.message = message(code: 'default.updated.message', args: [message(code: 'production.label', default: 'Production'), production.toString()])
//                flash.alert = 'success'
//                redirect production
//            }
//            '*'{ respond production, [status: OK] }
//        }

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

    private Production fillGaps(Production production)
    {
        Date startDate = params.getDate('startDate', "dd/MM/yyyy")?.clearTime()
        if (startDate == null)
            startDate = new Date().clearTime()

        if (!production.startDate)
            production.startDate = startDate

        Date finishDate = params.getDate('finishDate', "dd/MM/yyyy")?.clearTime()
        if (!production.finishDate)
            production.finishDate = finishDate

        def active = params.getBoolean('active') ? true : false
        production.active = active

        def finished = params.getBoolean('finished') ? true : false
        production.finished = finished

        return production
    }
}
