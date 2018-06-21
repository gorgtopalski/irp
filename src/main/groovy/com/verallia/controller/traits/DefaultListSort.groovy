package com.verallia.controller.traits

trait DefaultListSort
{
    def listByStartDateAsc()
    {
        if (!params.sort)
        {
            params.sort = 'startDate'
            params.order = 'desc'
        }
    }
}