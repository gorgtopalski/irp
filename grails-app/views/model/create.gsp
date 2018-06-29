<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="page" value="${message(code: 'model.view.create.label')}"/>
        <title><g:message code="title"/> / ${page} </title>
    </head>
    <body>
        <div id="create-model" class="content scaffold-create" role="main">
            <br>
            <h1>${page}</h1>

            <irp:alert flash="${flash}"/>

            <g:form resource="${this.model}" method="POST">
                <fieldset class="form">
                    <f:with bean="model">
                        <f:field property="name"/>
                        <f:field property="blueprint"/>
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
