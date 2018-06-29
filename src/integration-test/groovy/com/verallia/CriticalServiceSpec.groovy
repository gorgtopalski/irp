package com.verallia

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class CriticalServiceSpec extends Specification {

    CriticalService criticalService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Critical(...).save(flush: true, failOnError: true)
        //new Critical(...).save(flush: true, failOnError: true)
        //Critical critical = new Critical(...).save(flush: true, failOnError: true)
        //new Critical(...).save(flush: true, failOnError: true)
        //new Critical(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //critical.id
    }

    void "test get"() {
        setupData()

        expect:
        criticalService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Irp> criticalList = criticalService.list(max: 2, offset: 2)

        then:
        criticalList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        criticalService.count() == 5
    }

    void "test delete"() {
        Long criticalId = setupData()

        expect:
        criticalService.count() == 5

        when:
        criticalService.delete(criticalId)
        sessionFactory.currentSession.flush()

        then:
        criticalService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Irp critical = new Irp()
        criticalService.save(critical)

        then:
        critical.id != null
    }
}
