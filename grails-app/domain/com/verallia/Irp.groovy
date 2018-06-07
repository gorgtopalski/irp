package com.verallia

class Irp
{
    Date date
    Team team
    Shift shift
    static belongsTo = [production: Production]
    boolean critical
    int firstPallet
    int lastPallet
    boolean wholeShift
    boolean labels
    boolean pending
    String comment
    String motive
    static hasMany = [audits: Audit]

    static constraints = {
        date        nullable: false, max: new Date()
        production  nullable: false
        firstPallet min: 0, validator: {val, Irp irp -> irp.lastPallet <= val }
        lastPallet  min: 0, validator: {val, Irp irp -> irp.firstPallet >= val }
        motive      nullable: false, blank: false
        team        nullable: false
        shift       nullable: false
        comment     nullable: true, blank: true
        audits      nullable: true
    }
}
