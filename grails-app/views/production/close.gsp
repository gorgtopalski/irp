<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="page" value="${message(code: 'production.view.close.label')}"/>
        <title><g:message code="title"/> / ${page} </title>
    </head>
    <body>
        <div id="edit-production" class="content scaffold-edit" role="main">
            <br>
            <h1>${page}</h1>

            <irp:alert flash="${flash}"/>

            <f:with bean="production">
                <f:display property="model" wrapper="domainSelect"/>

                <div class="row">
                    <div class="col-md-6">
                        <f:display property="startDate" wrapper="domainDate"/>
                        <f:display property="finishDate" wrapper="domainDate"/>
                    </div>
                    <div class="col-md-6">
                        <f:display property="line" wrapper="domainSelect"/>
                        <f:display property="active" wrapper="domainBoolean"/>
                        <f:display property="finished" wrapper="domainBoolean"/>
                    </div>
                </div>
                <hr>
            </f:with>
            <g:form id="${this.production.id}" method="PUT" action="closeProduction">
                <g:hiddenField name="version" value="${this.production?.version}" />
                <fieldset class="form">
                    <f:with bean="production">
                        <f:field property="totalPallets"/>
                    </f:with>
                </fieldset>
                <fieldset class="buttons">
                    <div class="float-right">
                        <button type="submit" name="update" class="btn btn-success">
                            <i class="fas fa-check"></i>
                            <g:message code="production.button.close.label" default="Cerrar" />
                        </button>
                    </div>
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
