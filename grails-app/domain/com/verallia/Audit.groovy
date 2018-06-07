package com.verallia

class Audit {

    static belongsTo = [irp: Irp]
    int pallet
    String comment
    boolean ok

    static constraints = {
        pallet min: 1
        comment nullable: false, blank: false
        irp nullable: false
    }
}
