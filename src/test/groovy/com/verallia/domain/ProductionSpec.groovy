package com.verallia.domain

import com.verallia.Irp
import com.verallia.Line
import com.verallia.Model
import com.verallia.Production
import grails.testing.gorm.DataTest
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class ProductionSpec extends Specification implements DataTest
{
    @Shared model
    @Shared line

    def setupSpec() {
        mockDomain Model
        mockDomain Line
        mockDomain Irp
        mockDomain Production

        model = new Model(name: 'test', blueprint: 'test').save()
        line = new Line(name: '11').save()
    }

    @Unroll
    def "Production instance must be valid: #valid with params: #params"()
    {
        def data = new Production(params)


        expect:
        data.validate() == valid

        and:
        if (valid)
        {
            assert data.save()
            params.each { k, v -> assert data."$k" == v }
        }

        where:
        params                                                                                              ||valid
        [:]                                                                                                 ||false
        [model: null]                                                                                       ||false
        [model: model]                                                                                      ||false
        [line: null]                                                                                        ||false
        [line: line]                                                                                        ||false
        [model: model, line: line]                                                                          ||false
        [model: model, line: line, startDate: null]                                                         ||false
        [model: model, line: line, startDate: new Date().clearTime()]                                       ||true
        [model: model, line: line, startDate: new Date().clearTime(), active: true, finished: false]        ||true
        [model: model, line: line, startDate: new Date().clearTime(), active: false, finished: true]        ||true
        [model: model, line: line, startDate: new Date().clearTime(), active: false, finished: false]       ||false
        [model: model, line: line, startDate: new Date().clearTime(), active: true, finished: true]         ||false
        [model: model, line: line, startDate: new Date().clearTime(), totalPallets: 1000]                   ||true
        [model: model, line: line, startDate: new Date().clearTime(), totalPallets: -1000]                  ||false
        [model: model, line: line, startDate: new Date().clearTime(), totalPallets: 0]                      ||true
    }
}
