package com.verallia

import groovy.transform.Canonical

@Canonical
class PaletMap
{
    Integer paletNumber
    boolean accepted
    List<Irp> irps
}
