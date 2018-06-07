package com.verallia

class Shift {

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

    String fristChar()
    {
        name.substring(0,1).capitalize()
    }
}
