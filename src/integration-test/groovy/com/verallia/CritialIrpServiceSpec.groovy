package com.verallia

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class CritialIrpServiceSpec extends Specification {

    CritialIrpService critialIrpService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new CritialIrp(...).save(flush: true, failOnError: true)
        //new CritialIrp(...).save(flush: true, failOnError: true)
        //CritialIrp critialIrp = new CritialIrp(...).save(flush: true, failOnError: true)
        //new CritialIrp(...).save(flush: true, failOnError: true)
        //new CritialIrp(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //critialIrp.id
    }

    void "test get"() {
        setupData()

        expect:
        critialIrpService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Irp> critialIrpList = critialIrpService.list(max: 2, offset: 2)

        then:
        critialIrpList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        critialIrpService.count() == 5
    }

    void "test delete"() {
        Long critialIrpId = setupData()

        expect:
        critialIrpService.count() == 5

        when:
        critialIrpService.delete(critialIrpId)
        sessionFactory.currentSession.flush()

        then:
        critialIrpService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Irp critialIrp = new Irp()
        critialIrpService.save(critialIrp)

        then:
        critialIrp.id != null
    }
}
