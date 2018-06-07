package com.verallia

class Model {

    String name
    String blueprint
    static hasMany = [productions: Production]

    static constraints = {
        name blank: false, nullable: false
        blueprint blank: false, nullable: false, unique: true
        productions nullable: true
    }

    @Override
    String toString() {
        return "[$blueprint] $name"
    }
}
