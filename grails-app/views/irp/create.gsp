<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="page" value="${message(code: 'irp.view.create.label')}"/>
        <title><g:message code="title"/> / ${page} </title>
    </head>
    <body>
        <div id="create-irp" class="content scaffold-create" role="main">
            <br>
            <h1>${page}</h1>

            <irp:alert flash="${flash}"/>

            <g:form resource="${this.irp}" method="POST">
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
                                <f:field property="pending"/>
                                %{--<f:field property="critical"/>--}%
                                <f:field property="labels"/>
                                <f:field property="wholeShift"/>
                            </div>
                        </div>
                    </div>

                    </f:with>
                </fieldset>

                <fieldset class="buttons">
                    <div class="float-right">
                        <button type="submit" name="create" class="btn btn-success">
                            <i class="far fa-save"></i>
                            ${message(code: 'default.button.create.label', default: 'Crear')}
                        </button>
                    </div>
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
