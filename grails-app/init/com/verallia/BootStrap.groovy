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
        new Model(blueprint: '6227WC', name: 'Dorica 25').save()
        new Model(blueprint: '6305WB', name: 'Cava STD 20 (BR)').save()
        new Model(blueprint: '7111WA', name: 'Cava 75 (835)').save()
        new Model(blueprint: '7171WA', name: 'Aceite Moro 50').save()
        new Model(blueprint: '4185WB', name: 'Cava 75').save()
        new Model(blueprint: '6837WA', name: 'Hoja 75').save()
        new Model(blueprint: '6841WB', name: 'Bord 75 Eco Optima RE').save()
        new Model(blueprint: '7050WA', name: 'Bord 75 Toro Loco BVS').save()
    }
}
