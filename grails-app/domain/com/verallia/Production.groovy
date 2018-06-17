package com.verallia

class Production {

    static belongsTo = [model: Model]
    Line line
    Date startDate
    Date finishDate
    boolean active = true
    boolean finished = false
    int totalPallets = 0
    static hasMany = [irp: Irp]

    static constraints = {
        model           nullable: false
        line            nullable: false
        startDate       nullable: false, max: new Date()
        finishDate      nullable: true, validator: {val, Production prod -> if (val != null) prod.startDate < val else true }     
        active          validator: {val, Production prod -> prod.finished != val}
        finished        validator: {val, Production prod -> prod.active != val}
        totalPallets    min: 0
        irp             nullable: true
    }

    @Override
    String toString() {

        def start = startDate.format('dd/MM/YY')
        def end = finishDate ? finishDate.format('dd/MM/YY') : '*'

        return "[ $start - $end ] L-$line"
    }
}
