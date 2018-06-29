package com.verallia.controller.traits

trait DefaultListSort
{
    def listByStartDateDesc()
    {
        if (!params.sort)
        {
            params.sort = 'startDate'
            params.order = 'desc'
        }
    }

    def listByLineAsc()
    {
        if (!params.sort)
        {
            params.sort = 'line'
            params.order = 'asc'
        }
    }
}