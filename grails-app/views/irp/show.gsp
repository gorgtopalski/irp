<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="page" value="${message(code: 'irp.view.show.label')}"/>
        <title><g:message code="title"/> / ${page} </title>
    </head>
    <body>
        <div class="container-fluid">
            <br>
            <h1>${page}</h1>

            <irp:alert flash="${flash}"/>

            <f:with bean="irp">
                <div class="row">
                    <div class="col">
                        <f:display property="date" wrapper="domainDate"/>
                    </div>
                    <div class="col">
                        <f:display property="production" wrapper="domainFields"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <f:display property="motive" wrapper="domainFields"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <f:display property="team" wrapper="domainFields"/>
                        <f:display property="shift" wrapper="domainFields"/>

                    </div>

                        <div class="col">
                            <f:display property="firstPallet" wrapper="domainFields"/>
                            <f:display property="lastPallet" wrapper="domainFields"/>
                        </div>

                </div>
                <div class="row">
                    <div class="col">
                        <f:display property="comment" wrapper="domainFields"/>
                    </div>
                    <div class="col">
                        <div class="row">
                            <div class="col">
                                <f:display property="critical" wrapper="domainBoolean"/>
                                <f:display property="pending" wrapper="domainBoolean"/>
                            </div>
                            <div class="col">
                                <f:display property="labels" wrapper="domainBoolean"/>
                                <f:display property="wholeShift" wrapper="domainBoolean"/>
                            </div>
                        </div>
                    </div>
                </div>

                <g:if test="${this.irp.critical}">
                    <hr>
                    <div class="row">
                        <div class="col border-right">
                            <div class="row">

                                <div class="col">
                                    <f:display property="isFoundInVC" wrapper="domainBoolean"/>
                                    <f:display property="isRejected" wrapper="domainBoolean"/>
                                </div>
                                <div class="col">
                                    <f:display property="bottleNumber" wrapper="domainFields"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col">
                                    <f:display property="rejectionStart" wrapper="domainTime"/>
                                </div>
                                <div class="col">
                                    <f:display property="rejectionEnd" wrapper="domainTime"/>
                                </div>
                            </div>


                            <f:display property="isVerified" wrapper="domainBoolean"/>
                            <f:display property="origin" wrapper="domainFields"/>
                            <f:display property="corrections" wrapper="domainFields"/>
                        </div>
                        <div class="col">

                            <div class="row">
                                <div class="col">
                                    <f:display property="isFoundInVF" wrapper="domainBoolean"/>
                                    <f:display property="isRejectedByLNM" wrapper="domainBoolean"/>
                                </div>
                                <div class="col">
                                    <f:display property="lnmBotleNumber" wrapper="domainFields"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col">
                                    <f:display property="lnmRejectionStart" wrapper="domainTime"/>
                                </div>
                                <div class="col">
                                    <f:display property="lnmRejectionEnd" wrapper="domainTime"/>
                                </div>
                            </div>

                            <f:display property="checkLNM5Bottles" wrapper="domainBoolean"/>

                            <div class="row">
                                <div class="col">

                                    <label for="detected">
                                        Se encuentra en:
                                    </label>
                                    <div class="form-group" id="detected">
                                        <div class="form-check">
                                            <f:display property="detectedInInspectionMachine" wrapper="domainBoolean"/>
                                            <f:display property="detectedInFabricacionAndFoundInArcha" wrapper="domainBoolean"/>
                                            <f:display property="detectedInTDB" wrapper="domainBoolean"/>
                                            <f:display property="detectedInTDP" wrapper="domainBoolean"/>
                                        </div>

                                    </div>
                                </div>
                            </div>
                            <f:display property="verifyThatIsCorrected" wrapper="domainBoolean"/>
                            <f:display property="comments" wrapper="domainFields"/>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col">
                            <f:display property="passesReliability" wrapper="domainBoolean"/>
                            <f:display property="isAddedToReliabilityPool" wrapper="domainBoolean"/>

                        </div>
                        <div class="col">
                            <f:display property="palletNumberWhenIncident" wrapper="domainFields"/>
                            <f:display property="palletRejected" wrapper="domainBoolean"/>
                        </div>
                    </div>

                </g:if>


            </f:with>

            <g:if test="${this.irp.audits}">

                <%
                    def audits = this.irp.audits.sort { a,b -> b.palet <=> a.palet }
                    def first = audits.first().palet
                    def last = audits.last().palet
                    def w = "6rem"
                    def h = "4rem"
                %>
                <hr>
                <div class="row">
                    <g:each in="${first..last}" var="palet" status="i">
                        <%
                            def found = audits.palet.contains(palet)
                            def audit = found ? audits.find { it.palet == palet } : null
                            def ok = audit ? audit.ok : false
                        %>
                        <g:if test="${found}">
                            <div class="card ${ ok ? 'border-success' : 'border-danger'} ${ ok ? 'btn-outline-success' : 'btn-outline-danger'}" style="width: ${w}; height: ${h};">
                                <div class="card-body"
                                     onclick="document.location = '${createLink(action:'edit', controller: 'audit', id: audit.id)}';">
                                    <h6 class="text-center align-middle">${audit.palet}
                                        <g:if test="${ok}">
                                            <i class="far fa-thumbs-up"></i>
                                        </g:if>
                                        <g:else>
                                            <i class="fas fa-ban"></i>
                                        </g:else>
                                    </h6>
                                </div>
                            </div>
                        </g:if>
                        <g:else>
                            <div class="card btn-outline-secondary" style="width: ${w}; height: ${h};">
                                <div class="card-body"
                                     onclick="document.location = '${createLink(action: 'create', controller: 'audit',  params: [irp: this.irp.id, palet: palet])}';"
                                >
                                    <h6 class="text-center align-middle">${palet}</h6>
                                </div>
                            </div>
                        </g:else>
                    </g:each>
                </div>
                <hr>
            </g:if>

            <fieldset class="buttons">
                <div class="float-left">
                    <g:form resource="${this.irp}" method="DELETE">
                        <button type="submit" name="delete" class="btn btn-danger" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">
                            <i class="far fa-trash-alt"></i>
                            ${message(code: 'default.button.delete.label', default: 'Delete')}
                        </button>
                    </g:form>
                </div>
                <div class="float-right">
                    <g:if test="${this.irp.pending}">
                        <% def p = irp.lastPallet ? irp.lastPallet-1 : irp.firstPallet ? irp.firstPallet-1 : 0%>
                        <g:link class="btn btn-info" controller="audit" action="create" params="[irp: irp.id, palet: p]">
                            <i class="fas fa-clipboard-list"></i>
                            <g:message code="audit.new.label" default="Auditar palet" />
                        </g:link>
                        <g:link class="btn btn-success" action="close" resource="${this.irp}">
                            <i class="far fa-times-circle"></i>
                            <g:message code="default.button.close.label" default="Close" />
                        </g:link>
                    </g:if>
                    <g:link class="btn btn-warning" action="edit" resource="${this.irp}">
                        <i class="far fa-edit"></i>
                        <g:message code="default.button.edit.label" default="Edit" />
                    </g:link>
            </fieldset>
        </div>
    </body>
</html>
