<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="page" value="${message(code: 'irp.view.edit.label')}"/>
        <title><g:message code="title"/> / ${page} </title>
    </head>
    <body>
        <div id="edit-irp" class="content scaffold-edit" role="main">
            <br>
            <h1>${page}</h1>

            <irp:alert flash="${flash}"/>

            <g:form resource="${this.irp}" method="PUT">
                <g:hiddenField name="version" value="${this.irp?.version}" />
                <fieldset class="form">
            %{--<f:all bean="irp"/>--}%

                <f:with bean="irp">
                    <div class="row">
                        <div class="col">
                            <f:field property="date"/>
                        </div>
                        <div class="col">
                            <f:field property="production"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <f:field property="motive"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <f:field property="team"/>
                            <f:field property="shift"/>

                        </div>
                        <div class="col">
                            <f:field property="firstPallet"/>
                            <f:display property="lastPallet" wrapper="domainFields"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <f:field property="comment"/>
                        </div>
                        <div class="col">
                            <div class="row">
                                <div class="col">
                                    <f:field property="critical"/>
                                    <f:field property="pending"/>
                                </div>
                                <div class="col">
                                    <f:field property="labels"/>
                                    <f:field property="wholeShift"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col border-right">
                            <div class="row">

                                <div class="col">
                                    <f:field property="isFoundInVC"/>
                                    <f:field property="isRejected"/>
                                </div>
                                <div class="col">
                                    <f:field property="bottleNumber"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col">
                                    <f:field property="rejectionStart" widget="ejectionTime" wrapper="ejectionTime" theme="ejectionTime"/>
                                </div>
                                <div class="col">
                                    <f:field property="rejectionEnd" widget="ejectionTime" wrapper="ejectionTime" theme="ejectionTime"/>
                                </div>
                            </div>


                            <f:field property="isVerified"/>
                            <f:field property="origin"/>
                            <f:field property="corrections"/>
                        </div>
                        <div class="col">

                            <div class="row">
                                <div class="col">
                                    <f:field property="isFoundInVF"/>
                                    <f:field property="isRejectedByLNM"/>
                                </div>
                                <div class="col">
                                    <f:field property="lnmBotleNumber"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col">
                                    <f:field property="lnmRejectionStart" widget="ejectionTime" wrapper="ejectionTime" theme="ejectionTime"/>
                                </div>
                                <div class="col">
                                    <f:field property="lnmRejectionEnd" widget="ejectionTime" wrapper="ejectionTime" theme="ejectionTime"/>
                                </div>
                            </div>

                            <f:field property="checkLNM5Bottles"/>

                            <div class="row">
                                <div class="col">

                                    <label for="detected">
                                        Se encuentra en:
                                    </label>
                                    <div class="form-group" id="detected">
                                        <div class="form-check">
                                            <f:field property="detectedInInspectionMachine"/>
                                            <f:field property="detectedInFabricacionAndFoundInArcha"/>
                                            <f:field property="detectedInTDB"/>
                                            <f:field property="detectedInTDP"/>
                                        </div>

                                    </div>
                                </div>
                            </div>
                            <f:field property="verifyThatIsCorrected"/>
                            <f:field property="comments"/>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col">
                            <f:field property="passesReliability"/>
                            <f:field property="isAddedToReliabilityPool"/>

                        </div>
                        <div class="col">
                            <f:field property="palletNumberWhenIncident"/>
                            <f:field property="palletRejected"/>
                        </div>
                    </div>
                    </div>

                </f:with>

                </fieldset>

                <fieldset class="buttons">
                    <div class="float-right">
                        <button type="submit" name="update" class="btn btn-warning">
                            <i class="far fa-edit"></i>
                            <g:message code="default.button.update.label" default="Update" />
                        </button>
                    </div>
                </fieldset>
            </g:form>





        </div>
    </body>
</html>
