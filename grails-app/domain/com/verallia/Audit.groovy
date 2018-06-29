package com.verallia

class Audit {

    static belongsTo = [irp: Irp]
    int palet
    String comment
    boolean ok

    static constraints = {
        palet min: 1
        comment nullable: true, blank: true
        irp nullable: false
    }
}
