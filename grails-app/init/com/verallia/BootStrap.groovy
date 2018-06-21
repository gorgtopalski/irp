package com.verallia

import grails.util.Environment
import org.dom4j.rule.Mode

class BootStrap {

    def lines = []
    def shifts = []
    def teams = []
    def rand = new Random()


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

        shifts << new Shift(name: 'Noche',    numerical: 1).save()
        shifts << new Shift(name: 'Mañana',   numerical: 2).save()
        shifts << new Shift(name: 'Tarde',    numerical: 3).save()

        teams << new Team(name: 'Uno',   numerical: 1).save()
        teams << new Team(name: 'Dos',   numerical: 2).save()
        teams << new Team(name: 'Tres',  numerical: 3).save()
        teams << new Team(name: 'Cuatro',numerical: 4).save()
        teams << new Team(name: 'Cinco', numerical: 5).save()

        lines << new Line(name: '11').save()
        lines << new Line(name: '11A').save()
        lines << new Line(name: '11B').save()
        lines << new Line(name: '12').save()
        lines << new Line(name: '12A').save()
        lines << new Line(name: '12B').save()
        lines << new Line(name: '13').save()
    }

    def setupSampleData()
    {
        def models = []

        models << new Model(blueprint: '6227WC', name: 'Dorica 25').save()
        models << new Model(blueprint: '6305WB', name: 'Cava STD 20 (BR)').save()
        models << new Model(blueprint: '7111WA', name: 'Cava 75 (835)').save()
        models << new Model(blueprint: '7171WA', name: 'Aceite Moro 50').save()
        models << new Model(blueprint: '4185WB', name: 'Cava 75').save()
        models << new Model(blueprint: '6837WA', name: 'Hoja 75').save()
        models << new Model(blueprint: '6841WB', name: 'Bord 75 Eco Optima RE').save()
        models << new Model(blueprint: '7050WA', name: 'Bord 75 Toro Loco BVS').save(flush:true)

        (0..500).each {

            boolean active = false
            boolean finished = true
            Date startDate = new Date()-(it+5)
            Date finishDate = new Date()-((it+it%5))
            int totalPallets = (((it%50)+1) * 100)
            Model model = models.get((it * rand.nextInt(1000000)) % models.size())
            Line line = lines.get((it * rand.nextInt(1000000)) % lines.size())

            new Production(
                    model: model,
                    line: line,
                    active: active,
                    finished: finished,
                    startDate: startDate,
                    finishDate: finishDate,
                    totalPallets: totalPallets
            ).save(flush: true)
        }

        def active  = []

        active << new Production(
                model: models[0],
                line: lines[1],
                active: true,
                startDate: new Date()-1,
                totalPallets: 20
        ).save()

        active << new Production(
                model: models[1],
                line: lines[2],
                active: true,
                startDate: new Date()-1,
                totalPallets: 20
        ).save()

        active << new Production(
                model: models[2],
                line: lines[4],
                active: true,
                startDate: new Date()-2,
                totalPallets: 125
        ).save()

        active << new Production(
                model: models[3],
                line: lines[5],
                active: true,
                startDate: new Date()-2,
                totalPallets: 112
        ).save()

        active << new Production(
                model: models[4],
                line: lines[6],
                active: true,
                startDate: new Date()-3,
                totalPallets: 225
        ).save(flush:true)

        def motives = ['BA', 'BR', 'IB', 'MEG', 'PIR']
        def criticals = ['RIB', 'AG']

        (0..4).each {

            def production = active.get((it%active.size()))
            def motive = motives.get(it%motives.size())+" Nº${it%25}"
            def shift = shifts.get(it%shifts.size())
            def team = teams.get(it%teams.size())
            def pending = it % 2 == 0
            def labels = it % 2 == 0

            new Irp(
                    production: production,
                    date: new Date()-it,
                    pending: pending,
                    motive: motive,
                    team: team,
                    labels: labels,
                    shift: shift,
            ).save(flush:true)

            new CritialIrp(
                    production: production,
                    date: new Date().clearTime(),
                    pending: pending,
                    motive: criticals.get(it % criticals.size())+" Nº$it" ,
                    team: team,
                    labels: labels,
                    shift: shift,
                    critical: true,
                    isFoundInVF: true,
                    isFoundInVC: false
            ).save(flush:true)
        }

    }
}
