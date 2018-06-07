package com.verallia.domain

import com.verallia.*
import grails.testing.gorm.DataTest
import grails.testing.gorm.DomainUnitTest
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class IrpSpec extends Specification implements DataTest
{
    @Shared model
    @Shared line
    @Shared production
    @Shared shift
    @Shared team

    void setupSpec() {
        mockDomains Model, Line, Irp, Production, Shift, Team

        team = new Team(name: 'Eq. 1', numerical: 1).save()
        shift = new Shift(name: 'Night', numerical: 1).save()
        model = new Model(name: 'test', blueprint: 'test').save()
        line = new Line(name: '11').save()
        production = new Production(
                model: model,
                line: line,
                startDate: new Date()-5,
                totalPallets: 50,
                active: true,
                finished: false).save()
    }

    @Unroll
    def "Irp instance must be valid: #valid with params: #params"()
    {

        def data = new Irp(params)

        expect:
        data.validate() == valid

        and:
        if (valid)
        {
            assert data.save()
            params.each { k, v -> assert data."$k" == v }
        }

        where:
        params                                                                                                  ||valid
        [:]                                                                                                     ||false
        [date: null]                                                                                            ||false
        [team: null]                                                                                            ||false
        [shift: null]                                                                                           ||false
        [motive: null]                                                                                          ||false
        [production: null]                                                                                      ||false
        [date: new Date().clearTime()]                                                                          ||false
        [team: team]                                                                                            ||false
        [shift: shift]                                                                                          ||false
        [team: team]                                                                                            ||false
        [motive: 'motive']                                                                                      ||false
        [production: production]                                                                                ||false
        [date: new Date().clearTime(), team: team, shift: shift, motive: 'motive', production: production]      ||true
    }

    def "Default values of an Irp instance"()
    {
        def date = new Date().clearTime()

        def irp = new Irp(
                date: date,
                team: team,
                shift: shift,
                motive: 'motive',
                production: production
        )

        expect:
        irp.validate()

        when:
        irp.save()

        then:
        irp.date == date
        irp.team == team
        irp.shift == shift
        irp.production == production
        irp.motive == 'motive'

        //Defaults
        irp.firstPallet == 0
        irp.lastPallet == 0
        irp.wholeShift == false
        irp.labels == false
        irp.pending == false
        irp.critical == false
        irp.comment == null
    }

    @Unroll
    def "Test pallets limits"()
    {
        def data = new Irp(params)

        expect:
        data.validate() == valid

        and:
        if (valid)
        {
            assert data.save()
            params.each { k, v -> assert data."$k" == v }
        }

        where:
        params                                                                                                                                          ||valid
        [date: new Date().clearTime(), team: team, shift: shift, motive: 'motive', production: production]                                              ||true
        [date: new Date().clearTime(), team: team, shift: shift, motive: 'motive', production: production, firstPallet: 0, lastPallet: 0]               ||true
        [date: new Date().clearTime(), team: team, shift: shift, motive: 'motive', production: production, firstPallet: 5, lastPallet: 0]               ||true
        [date: new Date().clearTime(), team: team, shift: shift, motive: 'motive', production: production, firstPallet: 0, lastPallet: 5]               ||false
        [date: new Date().clearTime(), team: team, shift: shift, motive: 'motive', production: production, firstPallet: -5, lastPallet: 0]              ||false
        [date: new Date().clearTime(), team: team, shift: shift, motive: 'motive', production: production, firstPallet: 0, lastPallet: -5]              ||false
        [date: new Date().clearTime(), team: team, shift: shift, motive: 'motive', production: production, firstPallet: 50, lastPallet: 50]             ||true
        [date: new Date().clearTime(), team: team, shift: shift, motive: 'motive', production: production, firstPallet: 50, lastPallet: 5]              ||true
    }
}
