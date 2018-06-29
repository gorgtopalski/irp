package com.verallia

import grails.gorm.services.Service

@Service(Audit)
abstract class AuditService {

    abstract Audit get(Serializable id)

    abstract List<Audit> list(Map args)

    abstract Long count()

    abstract void delete(Serializable id)

    abstract Audit save(Audit audit)

    void updatePalets(Audit audit)
    {
        def audits = Audit.findAllByIrp(audit.irp)
        def irp = Irp.get(audit.irp.id)

        irp.firstPallet = audits.palet.max()
        irp.lastPallet = audits.palet.min()

        irp.save(flush:true)
    }

    void createFirstAudit(Irp irp)
    {
        if (irp.firstPallet && (irp.audits == null || irp.audits?.empty))
        {
            def audit = new Audit()
            audit.irp = irp
            audit.palet = irp.firstPallet
            audit.ok = false
            audit.comment = irp.motive

            save(audit)
        }
    }

}