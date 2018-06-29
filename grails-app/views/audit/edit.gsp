<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="page" value="${message(code: 'audit.view.edit.label')}"/>
        <title><g:message code="title"/> / ${page} </title>
    </head>
    <body>

        <div id="edit-audit" class="content scaffold-edit" role="main">
            <br>
            <h1>${page}</h1>

            <irp:alert flash="${flash}"/>

            <g:form resource="${this.audit}" method="PUT">
                <g:hiddenField name="version" value="${this.audit?.version}" />
                <fieldset class="form">
                    <f:all bean="audit"/>
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
