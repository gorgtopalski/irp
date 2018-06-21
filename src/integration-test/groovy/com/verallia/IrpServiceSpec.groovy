package com.verallia

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class IrpServiceSpec extends Specification {

    IrpService irpService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Irp(...).save(flush: true, failOnError: true)
        //new Irp(...).save(flush: true, failOnError: true)
        //Irp irp = new Irp(...).save(flush: true, failOnError: true)
        //new Irp(...).save(flush: true, failOnError: true)
        //new Irp(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //irp.id
    }

    void "test get"() {
        setupData()

        expect:
        irpService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Irp> irpList = irpService.list(max: 2, offset: 2)

        then:
        irpList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        irpService.count() == 5
    }

    void "test delete"() {
        Long irpId = setupData()

        expect:
        irpService.count() == 5

        when:
        irpService.delete(irpId)
        sessionFactory.currentSession.flush()

        then:
        irpService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Irp irp = new Irp()
        irpService.save(irp)

        then:
        irp.id != null
    }
}
