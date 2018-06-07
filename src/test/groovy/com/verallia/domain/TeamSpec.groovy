package com.verallia.domain

import com.verallia.Team
import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification
import spock.lang.Unroll

class TeamSpec extends Specification implements DomainUnitTest<Team>
{
    @Unroll
    def "Team instance that must be valid: #valid with params: #params"()
    {
        def data = new Team(params)

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

    def "Team instance must have unique names and numerical Id's"()
    {
        def team1 = new Team(name: 't1', numerical: 1)
        def team2 = new Team(name: 't1', numerical: 1)

        expect:
        [team1, team2].each { assert it.validate() }

        when:
        assert team1.save(flush:true)

        then:
        assert !team2.save(flush:true)
    }
}
