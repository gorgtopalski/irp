package com.verallia

import java.sql.Time

class CritialIrp extends Irp
{
    boolean critical = true
    boolean isFoundInVC
    boolean isFoundInVF

    boolean isRejected
    int bottleNumber
    Date rejectionStart
    Date rejectionEnd
    boolean isVerified
    String origin
    String corrections

    boolean isRejectedByLNM
    int lnmBotleNumber
    Date lnmRejectionStart
    Date lnmRejectionEnd
    boolean checkLNM5Bottles
    boolean detectedInTDB
    boolean detectedInTDP
    boolean detectedInInspectionMachine
    boolean detectedInFabricacionAndFoundInArcha
    boolean verifyThatIsCorrected
    String comments

    boolean passesReliability
    boolean isAddedToReliabilityPool
    int palletNumberWhenIncident
    boolean palletRejected

    static constraints = {
        isFoundInVC validator: { val,  irp -> irp.isFoundInVF != val}
        isFoundInVF validator: { val,  irp -> irp.isFoundInVC != val}
        isRejected validator: {val, irp ->
            if (val)
            {
                if (!(irp.bottleNumber > 0 && irp.bottleNumber < 127))
                    return false
                if (irp.rejectionStart == null || irp.rejectionEnd == null)
                    return false
                if (irp.rejectionEnd < irp.rejectionStart)
                    return false
                if (irp.origin == null || irp.origin.empty)
                    return false
                if (irp.corrections == null || irp.corrections.empty)
                    return false

                true
            }
        }

        bottleNumber min: 0, max: 127, validator: {val, irp ->
            if (irp.isRejected)
                ((val > 0) && (val < 127))
        }

        rejectionStart nullable: true, validator: {val, irp ->
            if (irp.isRejected)
            {
                if (val == null)
                    return false

                val < irp.rejectionEnd
            }
        }
        rejectionEnd nullable: true, validator: {val, irp ->
            if (irp.isRejected)
            {
                if (val == null)
                    return false

                val > irp.rejectionStart
            }
        }

        origin nullable: true, blank: true, validator: {val, irp ->
            if (irp.isRejected)
            {
                !(val?.empty || val == null)
            }
        }
        corrections nullable: true, blank: true, validator: {val, irp ->
            if (irp.isRejected)
            {
                !(val?.empty || val == null)
            }
        }

        isRejectedByLNM validator: { val, irp ->

            if (val)
            {
                if (!(irp.lnmBotleNumber > 0 && irp.lnmBotleNumber < 127))
                    return false
                if (irp.lnmRejectionStart == null || irp.lnmRejectionEnd == null)
                    return false
                if (irp.lnmRejectionEnd < irp.lnmRejectionStart)
                    return false
                if (irp.comments == null || irp.comments.empty)
                    return false

                true
            }
        }

        lnmBotleNumber min:0, max:127, validator: {val, irp ->
            if (irp.isRejectedByLNM)
                ((val > 0) && (val < 127))
        }

        lnmRejectionStart nullable: true, validator: { val, irp ->
            if(irp.isRejectedByLNM)
            {
                if(val == null)
                    return false

                val < irp.lnmRejectionEnd
            }
        }

        lnmRejectionEnd nullable: true, validator: { val, irp ->
            if(irp.isRejectedByLNM)
            {
                if(val == null)
                    return false

                val > irp.lnmRejectionStart
            }
        }

        comments nullable: true, blank: true, validator: {val, irp ->
            if (irp.isRejectedByLNM)
            {
                !(val?.empty || val == null)
            }
        }
    }

    def beforeInsert()
    {
        critical = true
    }

    def beforeUpdate()
    {
        critical = true
    }

}
