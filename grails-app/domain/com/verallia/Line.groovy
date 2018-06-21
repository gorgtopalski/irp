package com.verallia

class Line implements Comparable<Line>{

    String name

    static constraints = {
        name blank: false, nullable: false, unique: true
    }

    @Override
    String toString() {
        return name
    }

    @Override
    int compareTo(Line o) {
        this.id <=> o.id
    }
}
