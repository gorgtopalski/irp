package com.verallia

class Team {

    String name
    Integer numerical

    static constraints = {
        name        nullable: false, blank: false, unique: true
        numerical   nullable: false, blank: false, unique: true
    }

    @Override
    String toString() {
        return "$numerical"
    }
}
