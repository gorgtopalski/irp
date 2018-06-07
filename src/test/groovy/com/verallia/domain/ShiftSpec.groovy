package com.verallia.domain

import com.verallia.Shift
import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification
import spock.lang.Unroll

class ShiftSpec extends Specification implements DomainUnitTest<Shift> {

    @Unroll
    def "Shift instance that must be valid: #valid with params: #params"()
    {
        def data = new Shift(params)

        expect:
        data.validate() == valid

        and:
        if (valid)
        {
            assert data.save()
            params.each { k, v -> assert data."$k" == v }
        }

        where:
        params                          ||valid
        [:]                             ||false
        [name: 'name']                  ||false
        [numerical: 1]                  ||false
        [name: 'name', numerical: 21213]||true
    }

    def "Shift instance must have unique name and numerical Id"()
    {
        def shift1 = new Shift(name: 's1', numerical: 1)
        def shift2 = new Shift(name: 's1', numerical: 1)

        expect:
        [shift1, shift2].each { assert it.validate() }

        when:
        assert shift1.save(flush:true)

        then:
        assert !shift2.save(flush:true)
    }
}
