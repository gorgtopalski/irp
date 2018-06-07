package com.verallia.domain

import com.verallia.Model
import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification
import spock.lang.Unroll

class ModelSpec extends Specification implements DomainUnitTest<Model>
{

    @Unroll
    def "Model instance that must be valid: #valid with params: #params"()
    {
        def data = new Model(params)

        expect:
        data.validate() == valid

        and:
        if (valid)
        {
            assert data.save()
            params.each { k, v -> assert data."$k" == v }
        }

        where:
        params                                  ||valid
        [:]                                     ||false
        [name: 'name']                          ||false
        [blueprint: 'blueprint']                ||false
        [name: 'name', blueprint: 'blueprint']  ||true
    }

    def "Model instance must have unique blueprint"()
    {
        def model1 = new Model(name: 'model1', blueprint: '1')
        def model2 = new Model(name: 'model2', blueprint: '1')

        expect:
        [model1, model2].each { assert it.validate() }

        when:
        assert model1.save(flush:true)

        then:
        assert !model2.save(flush:true)
    }

}
