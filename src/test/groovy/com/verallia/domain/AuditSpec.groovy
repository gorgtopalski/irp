package com.verallia.domain

import com.verallia.Audit
import com.verallia.Irp
import com.verallia.Line
import com.verallia.Model
import com.verallia.Production
import com.verallia.Shift
import com.verallia.Team
import grails.testing.gorm.DataTest
import grails.testing.gorm.DomainUnitTest
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class AuditSpec extends Specification implements DataTest {

    @Shared model
    @Shared line
    @Shared production
    @Shared shift
    @Shared team
    @Shared Irp irp

    void setupSpec() {
        mockDomains Model, Line, Irp, Production, Shift, Team, Audit

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

        def date = new Date().clearTime()

        irp = new Irp(
                date: date,
                team: team,
                shift: shift,
                motive: 'motive',
                production: production
        ).save(flush:true)
    }

    @Unroll
    def "Audit instance must be valid: #valid with params: #params"()
    {
        given:
        def data = new Audit(params)


        expect:
        data.validate() == valid

        and:
        if (valid)
        {
            data = data.save(flush:true)

            irp.getAudits().size() == 1
            irp.getAudits().first() == data
            data.irp == irp

        }

        where:
        params                                  ||valid
        [:]                                     ||false
        [pallet: 1]                             ||false
        [comment: 'comment']                    ||false
        [irp: irp]                              ||false
        [irp: irp, pallet: 1, comment: 'q']     ||true

    }
}
