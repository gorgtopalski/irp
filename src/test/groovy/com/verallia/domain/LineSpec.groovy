package com.verallia.domain

import com.verallia.Line
import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification
import spock.lang.Unroll

class LineSpec extends Specification implements DomainUnitTest<Line> {

    @Unroll
    def "Team instance that must be valid: #valid with params: #params"()
    {
        def data = new Line(params)

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
        [name: 'name']                  ||true
    }
}
