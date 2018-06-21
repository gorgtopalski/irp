package com.verallia

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class LineServiceSpec extends Specification {

    LineService lineService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Line(...).save(flush: true, failOnError: true)
        //new Line(...).save(flush: true, failOnError: true)
        //Line line = new Line(...).save(flush: true, failOnError: true)
        //new Line(...).save(flush: true, failOnError: true)
        //new Line(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //line.id
    }

    void "test get"() {
        setupData()

        expect:
        lineService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Line> lineList = lineService.list(max: 2, offset: 2)

        then:
        lineList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        lineService.count() == 5
    }

    void "test delete"() {
        Long lineId = setupData()

        expect:
        lineService.count() == 5

        when:
        lineService.delete(lineId)
        sessionFactory.currentSession.flush()

        then:
        lineService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Line line = new Line()
        lineService.save(line)

        then:
        line.id != null
    }
}
