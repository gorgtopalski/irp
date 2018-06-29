package com.verallia.domain

import com.verallia.Irp
import com.verallia.Line
import com.verallia.Model
import com.verallia.Production
import com.verallia.Shift
import com.verallia.Team
import grails.testing.gorm.DataTest
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class IrpSpec extends Specification implements DataTest
{

    @Shared model
    @Shared line
    @Shared production
    @Shared shift
    @Shared team

    void setupSpec() {
        mockDomains Model, Line, Irp, Production, Shift, Team

        team = new Team(name: 'Eq. 1', numerical: 1).save()
        shift = new Shift(name: 'Night', numerical: 1).save()
        model = new Model(name: 'test', blueprint: 'test').save()
        line = new Line(name: '11').save()
        production = new Production(
                model: model,
                line: line,
                startDate: new Date()-5,
                totalPallets: 50,
                active: true,
                finished: false).save()
    }

    @Unroll
    def "CritialIrp instance must be valid: #valid with params: #params"()
    {
        given:
        def data = new Irp(params)

        expect:
        data.validate() == valid

        and:
        if (valid)
        {
            data = data.save()
            params.each { k, v ->
                if (k != 'critical')
                    assert data."$k" == v
            }
            data.critical == true
        }

        where:
        params                                                                                                                                                                                                                  ||valid
        [:]                                                                                                                                                                                                                     ||false
        [date: null]                                                                                                                                                                                                            ||false
        [team: null]                                                                                                                                                                                                            ||false
        [shift: null]                                                                                                                                                                                                           ||false
        [motive: null]                                                                                                                                                                                                          ||false
        [production: null]                                                                                                                                                                                                      ||false
        [date: new Date().clearTime()]                                                                                                                                                                                          ||false
        [team: team]                                                                                                                                                                                                            ||false
        [shift: shift]                                                                                                                                                                                                          ||false
        [team: team]                                                                                                                                                                                                            ||false
        [motive: 'motive']                                                                                                                                                                                                      ||false
        [production: production]                                                                                                                                                                                                ||false
        [date: new Date().clearTime(), team: team, shift: shift, motive: 'motive', production: production, isFoundInVC: true, isFoundInVF: false]                                                                               ||true
        [date: new Date().clearTime(), team: team, shift: shift, motive: 'motive', production: production, isFoundInVC: false, isFoundInVF: true]                                                                               ||true
        [date: new Date().clearTime(), team: team, shift: shift, motive: 'motive', production: production, isFoundInVC: true, isFoundInVF: true]                                                                                ||false
        [date: new Date().clearTime(), team: team, shift: shift, motive: 'motive', production: production, isFoundInVC: false, isFoundInVF: false]                                                                              ||false
        [date: new Date().clearTime(), team: team, shift: shift, motive: 'motive', production: production, isFoundInVC: true, isFoundInVF: false]                                                                               ||true
        [date: new Date().clearTime(), team: team, shift: shift, motive: 'motive', production: production, isFoundInVC: true, isFoundInVF: false, critical: true]                                                               ||true
        //Critical will always be set to true
        [date: new Date().clearTime(), team: team, shift: shift, motive: 'motive', production: production, isFoundInVC: true, isFoundInVF: false, critical: false]                                                              ||true
        [date: new Date().clearTime(), team: team, shift: shift, motive: 'motive', production: production, isFoundInVC: true, isFoundInVF: false, bottleNumber: 1]                                                              ||true
        [date: new Date().clearTime(), team: team, shift: shift, motive: 'motive', production: production, isFoundInVC: true, isFoundInVF: false, bottleNumber: -5]                                                             ||false
        [date: new Date().clearTime(), team: team, shift: shift, motive: 'motive', production: production, isFoundInVC: true, isFoundInVF: false, bottleNumber: 256]                                                            ||false
        [date: new Date().clearTime(), team: team, shift: shift, motive: 'motive', production: production, isFoundInVC: true, isFoundInVF: false,
         bottleNumber: 1, isRejected: true]                                                                                                                                                                                     ||false
        [date: new Date().clearTime(), team: team, shift: shift, motive: 'motive', production: production, isFoundInVC: true, isFoundInVF: false,
         bottleNumber: 1, isRejected: true, rejectionStart: new Date()-1,rejectionEnd: new Date(), corrections: 'corrections', origin: 'origin']                                                                                ||true
        [date: new Date().clearTime(), team: team, shift: shift, motive: 'motive', production: production, isFoundInVC: true, isFoundInVF: false,
         bottleNumber: 1, isRejected: true, rejectionStart: new Date()+1,rejectionEnd: new Date(), corrections: 'corrections', origin: 'origin']                                                                                ||false
        [date: new Date().clearTime(), team: team, shift: shift, motive: 'motive', production: production, isFoundInVC: true, isFoundInVF: false,
         bottleNumber: 1, isRejected: true, rejectionStart: new Date(),rejectionEnd: new Date()-1, corrections: 'corrections', origin: 'origin']                                                                                ||false
        [date: new Date().clearTime(), team: team, shift: shift, motive: 'motive', production: production, isFoundInVC: true, isFoundInVF: false,
         bottleNumber: 0, isRejected: true, rejectionStart: new Date()-1,rejectionEnd: new Date(), corrections: 'corrections', origin: 'origin']                                                                                ||false
        [date: new Date().clearTime(), team: team, shift: shift, motive: 'motive', production: production, isFoundInVC: true, isFoundInVF: false,
         bottleNumber: 1, isRejected: true, rejectionStart: new Date()-1,rejectionEnd: new Date(), corrections: '', origin: 'origin']                                                                                           ||false
        [date: new Date().clearTime(), team: team, shift: shift, motive: 'motive', production: production, isFoundInVC: true, isFoundInVF: false,
         bottleNumber: 1, isRejected: true, rejectionStart: new Date()-1,rejectionEnd: new Date(), corrections: 'corrections', origin: '']                                                                                      ||false
        [date: new Date().clearTime(), team: team, shift: shift, motive: 'motive', production: production, isFoundInVC: true, isFoundInVF: false,
         bottleNumber: 1, isRejected: true, rejectionStart: new Date()-1,rejectionEnd: new Date(), corrections: null, origin: null]                                                                                             ||false
        [date: new Date().clearTime(), team: team, shift: shift, motive: 'motive', production: production, isFoundInVC: true, isFoundInVF: false,
         bottleNumber: 1, isRejected: false, corrections: 'corrections', origin: 'origin']                                                                                                                                      ||true
        [date: new Date().clearTime(), team: team, shift: shift, motive: 'motive', production: production, isFoundInVC: true, isFoundInVF: false,
         lnmBotleNumber: 0, isRejectedByLNM: true, lnmRejectionStart: new Date()-1,  lnmRejectionEnd: new Date(), comments: 'comments']                                                                                         ||false
        [date: new Date().clearTime(), team: team, shift: shift, motive: 'motive', production: production, isFoundInVC: true, isFoundInVF: false,
         lnmBotleNumber: 127, isRejectedByLNM: true, lnmRejectionStart: new Date()-1,  lnmRejectionEnd: new Date(), comments: 'comments']                                                                                       ||false
        [date: new Date().clearTime(), team: team, shift: shift, motive: 'motive', production: production, isFoundInVC: true, isFoundInVF: false,
         lnmBotleNumber: 2, isRejectedByLNM: true, lnmRejectionStart: new Date()-1,  lnmRejectionEnd: new Date(), comments: 'comments']                                                                                         ||true
        [date: new Date().clearTime(), team: team, shift: shift, motive: 'motive', production: production, isFoundInVC: true, isFoundInVF: false,
         lnmBotleNumber: 2, isRejectedByLNM: false, lnmRejectionStart: new Date()-1,  lnmRejectionEnd: new Date(), comments: 'comments']                                                                                        ||true
        [date: new Date().clearTime(), team: team, shift: shift, motive: 'motive', production: production, isFoundInVC: true, isFoundInVF: false,
         lnmBotleNumber: 2, isRejectedByLNM: true, lnmRejectionStart: new Date(),  lnmRejectionEnd: new Date()-1, comments: 'comments']                                                                                         ||false
        [date: new Date().clearTime(), team: team, shift: shift, motive: 'motive', production: production, isFoundInVC: true, isFoundInVF: false,
         lnmBotleNumber: 2, isRejectedByLNM: true, lnmRejectionStart: new Date()-1,  lnmRejectionEnd: new Date(), comments: '']                                                                                                 ||false
        [date: new Date().clearTime(), team: team, shift: shift, motive: 'motive', production: production, isFoundInVC: true, isFoundInVF: false,
         lnmBotleNumber: 2, isRejectedByLNM: true, lnmRejectionStart: new Date()-1,  lnmRejectionEnd: new Date(), comments: null]                                                                                               ||false
    }

    def "CriticalIrp default values"()
    {
        given:
        def date = new Date().clearTime()

        def irp = new Irp(
                date: date,
                production: production,
                team: team,
                shift: shift,
                motive: 'motive',
                isFoundInVF: true,
                isFoundInVC: false
        )

        expect:
        irp.validate()


        when:
        irp = irp.save()

        then:
        irp.date == date
        irp.production == production
        irp.team == team
        irp.shift == shift
        irp.motive == 'motive'
        irp.isFoundInVC ==  false
        irp.isFoundInVF == true

        irp.passesReliability == false
        irp.comment == null
        irp.palletRejected == false
        irp.lnmRejectionStart == null
        irp.isAddedToReliabilityPool == false
        irp.corrections == null
        irp.critical == true
        irp.detectedInInspectionMachine == false
        irp.rejectionEnd == null
        irp.wholeShift == false
        irp.firstPallet == 0
        irp.isVerified == false
        irp.pending == false
        irp.isFoundInVF == true
        irp.isRejectedByLNM == false
        irp.lnmBotleNumber == 0
        irp.comments == null
        irp.palletNumberWhenIncident == 0
        irp.checkLNM5Bottles == false
        irp.lastPallet == 0
        irp.bottleNumber == 0
        irp.detectedInTDB == false
        irp.isRejected == false
        irp.verifyThatIsCorrected == false
        irp.rejectionStart == null
        irp.productionId == 1
        irp.lnmRejectionEnd == null
        irp.origin == null
        irp.labels == false
        irp.detectedInTDP == false
        irp.detectedInFabricacionAndFoundInArcha == false
    }
}
