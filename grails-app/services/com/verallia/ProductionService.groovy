package com.verallia

import grails.gorm.services.Service

@Service(Production)
abstract class ProductionService {

    abstract Production get(Serializable id)

    abstract List<Production> list(Map args)

    abstract Long count()

    abstract void delete(Serializable id)

    abstract Production save(Production production)

    abstract List<Production> findAllByActive(boolean active, Map args)

    abstract List<Production> findAllByLine(Line line, Map args)

    abstract Long countByActive(boolean active)

    abstract Long countByLine(Line line)

    Map getProductionMap(Serializable id)
    {
        def map =  [:]

        def prod = get(id)

        if (prod)
        {
            Map<Integer,PaletMap> data = [:]
            def stats = [:]

            def first = 1
            def last = 0

            def irps = prod.irp
            def maxFromIrp = (irps.firstPallet + irps.lastPallet).max()


            //Updates the total palets if a irp has a higher palet rejected
            if (maxFromIrp > prod.totalPallets)
            {
                last = maxFromIrp
                prod.totalPallets = maxFromIrp
                prod.save(flush:true)
            }
            else {
                last = prod.totalPallets
            }


            //Generates the initial data structure
            (last..first).each {
                def palet = new PaletMap(accepted: true, paletNumber: it)
                data.put(it, palet)
            }

            //Mark palets as rejected
            irps.each { irp ->

                if (irp.firstPallet > 0)
                {
                    if (irp.lastPallet == 0)
                    {

                        def palet = data.get(irp.firstPallet)

                        palet.accepted = false
                        if (palet.irps  == null)
                            palet.irps = []

                        palet.irps << irp
                        data.put(irp.firstPallet,palet)
                    }
                    else {
                        (irp.firstPallet..irp.lastPallet).each {

                            def palet = data.get(it)

                            palet.accepted = false
                            if (palet.irps  == null)
                            {
                                palet.irps = []
                            }
                            palet.irps << irp
                            data.put(it,palet)
                        }
                    }
                }
            }

            //Generate stats values
            def produced = data.values().size()
            def rejected = data.values().count { (!it.accepted) }
            def ratio = rejected / produced

            stats.put('produced', produced)
            stats.put('rejected', rejected)
            stats.put('accepted', produced-rejected)
            stats.put('ratio', ratio)

            //Fill the response map
            map.put('production', prod)
            map.put('palets',data)
            map.put('stats',stats)

        }

        return map
    }

}