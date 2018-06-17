package com.verallia

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ProductionServiceSpec extends Specification {

    ProductionService productionService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Production(...).save(flush: true, failOnError: true)
        //new Production(...).save(flush: true, failOnError: true)
        //Production production = new Production(...).save(flush: true, failOnError: true)
        //new Production(...).save(flush: true, failOnError: true)
        //new Production(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //production.id
    }

    void "test get"() {
        setupData()

        expect:
        productionService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Production> productionList = productionService.list(max: 2, offset: 2)

        then:
        productionList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        productionService.count() == 5
    }

    void "test delete"() {
        Long productionId = setupData()

        expect:
        productionService.count() == 5

        when:
        productionService.delete(productionId)
        sessionFactory.currentSession.flush()

        then:
        productionService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Production production = new Production()
        productionService.save(production)

        then:
        production.id != null
    }
}
