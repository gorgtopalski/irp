package com.verallia

class FlashMessageAlertTagLib {

    static namespace = "irp"
    static defaultEncodeAs = [taglib:'raw']


    def alert = {attrs, body ->

        if(attrs.flash)
        {
            def msg = attrs.flash?.message
            def alert = attrs.flash?.alert ?: 'primary'

            out << "<div class=\"alert alert-${alert} alert-dismissible fade show\" role=\"alert\">"
            out << "<i class=\"fas fa-exclamation-circle\"></i>"
            out << " $msg"
            out << "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">"
            out << "<span aria-hidden=\"true\">&times;</span>"
            out << "</button>"
            out << "</div>"
        }
    }
}
