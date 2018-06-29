package com.verallia

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class AuditServiceSpec extends Specification {

    AuditService auditService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Audit(...).save(flush: true, failOnError: true)
        //new Audit(...).save(flush: true, failOnError: true)
        //Audit audit = new Audit(...).save(flush: true, failOnError: true)
        //new Audit(...).save(flush: true, failOnError: true)
        //new Audit(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //audit.id
    }

    void "test get"() {
        setupData()

        expect:
        auditService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Audit> auditList = auditService.list(max: 2, offset: 2)

        then:
        auditList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        auditService.count() == 5
    }

    void "test delete"() {
        Long auditId = setupData()

        expect:
        auditService.count() == 5

        when:
        auditService.delete(auditId)
        sessionFactory.currentSession.flush()

        then:
        auditService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Audit audit = new Audit()
        auditService.save(audit)

        then:
        audit.id != null
    }
}
