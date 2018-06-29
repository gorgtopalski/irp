<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="page" value="${message(code: 'production.view.edit.label')}"/>
        <title><g:message code="title"/> / ${page} </title>
    </head>
    <body>
        <div id="edit-production" class="content scaffold-edit" role="main">
            <br>
            <h1>${page}</h1>

            <irp:alert flash="${flash}"/>

            <g:form resource="${this.production}" method="PUT">
                <g:hiddenField name="version" value="${this.production?.version}" />
                <fieldset class="form">
                    <f:with bean="production" >
                        <f:field property="model"/>
                        <f:field property="line"/>
                        <f:field property="startDate"/>
                        <f:field property="active"/>
                        <hr>
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <f:field property="finishDate"/>
                                <f:field property="finished"/>
                            </div>
                            <div class="col-md-6 mb-3">
                                <f:field property="totalPallets"/>
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
