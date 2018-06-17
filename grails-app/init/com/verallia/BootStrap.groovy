package com.verallia

import grails.util.Environment
import org.dom4j.rule.Mode

class BootStrap {

    def init = { servletContext ->

        setupConstantData()

        Environment.executeForCurrentEnvironment {
            development {
                setupSampleData()
            }
        }

    }

    def destroy = {
    }


    def setupConstantData()
    {

        new Shift(name: 'Noche',    numerical: 1).save()
        new Shift(name: 'Mañana',   numerical: 2).save()
        new Shift(name: 'Tarde',    numerical: 3).save()

        new Team(name: 'Uno',   numerical: 1).save()
        new Team(name: 'Dos',   numerical: 2).save()
        new Team(name: 'Tres',  numerical: 3).save()
        new Team(name: 'Cuatro',numerical: 4).save()
        new Team(name: 'Cinco', numerical: 5).save()

        new Line(name: '11').save()
        new Line(name: '11A').save()
        new Line(name: '11B').save()
        new Line(name: '12').save()
        new Line(name: '12A').save()
        new Line(name: '12B').save()
        new Line(name: '13').save()
    }

    def setupSampleData()
    {
        def dorica = new Model(blueprint: '6227WC', name: 'Dorica 25').save()
        new Model(blueprint: '6305WB', name: 'Cava STD 20 (BR)').save()
        new Model(blueprint: '7111WA', name: 'Cava 75 (835)').save()
        new Model(blueprint: '7171WA', name: 'Aceite Moro 50').save()
        new Model(blueprint: '4185WB', name: 'Cava 75').save()
        new Model(blueprint: '6837WA', name: 'Hoja 75').save()
        new Model(blueprint: '6841WB', name: 'Bord 75 Eco Optima RE').save()
        new Model(blueprint: '7050WA', name: 'Bord 75 Toro Loco BVS').save(flush:true)


        new Production(model: dorica, line: Line.get(1), active: true, startDate: new Date()-1).save()
        new Production(model: dorica, line: Line.get(1), finished: true, active: false, startDate: new Date()-20, finishDate: new Date()-10, totalPallets: 1000).save()
        new Production(model: dorica, line: Line.get(2), finished: true, active: false, startDate: new Date()-30, finishDate: new Date()-20, totalPallets: 1250).save()
        new Production(model: dorica, line: Line.get(3), finished: true, active: false, startDate: new Date()-40, finishDate: new Date()-30, totalPallets: 1511).save()
        new Production(model: dorica, line: Line.get(4), finished: true, active: false, startDate: new Date()-50, finishDate: new Date()-40, totalPallets: 8000).save()
        new Production(model: dorica, line: Line.get(1), finished: true, active: false, startDate: new Date()-60, finishDate: new Date()-50, totalPallets: 5415).save()
        new Production(model: dorica, line: Line.get(2), finished: true, active: false, startDate: new Date()-70, finishDate: new Date()-60, totalPallets: 10).save()
        new Production(model: dorica, line: Line.get(2), finished: true, active: false, startDate: new Date()-80, finishDate: new Date()-70, totalPallets: 150).save()
        new Production(model: dorica, line: Line.get(5), finished: true, active: false, startDate: new Date()-90, finishDate: new Date()-80, totalPallets: 1150).save()
        new Production(model: dorica, line: Line.get(5), finished: true, active: false, startDate: new Date()-100, finishDate: new Date()-90, totalPallets: 101541).save()
        new Production(model: dorica, line: Line.get(1), finished: true, active: false, startDate: new Date()-110, finishDate: new Date()-100, totalPallets: 1000).save()
        new Production(model: dorica, line: Line.get(1), finished: true, active: false, startDate: new Date()-120, finishDate: new Date()-110, totalPallets: 1000).save(flush:true)




    }
}
