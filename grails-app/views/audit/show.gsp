<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="page" value="${message(code: 'audit.view.show.label')}"/>
        <title><g:message code="title"/> / ${page} </title>
    </head>
    <body>
        <div id="show-audit" class="content scaffold-show" role="main">
            <br>
            <h1>${page}</h1>

            <irp:alert flash="${flash}"/>

            <f:display bean="audit" />

            <g:form resource="${this.audit}" method="DELETE">
                <fieldset class="buttons">
                    <div class="float-left">
                        <button type="submit" name="delete" class="btn btn-danger" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">
                            <i class="far fa-trash-alt"></i>
                            ${message(code: 'default.button.delete.label', default: 'Delete')}
                        </button>
                    </div>
                    <div class="float-right">
                        <g:link class="btn btn-warning" action="edit" resource="${this.audit}">
                            <i class="far fa-edit"></i>
                            <g:message code="default.button.edit.label" default="Edit" />
                        </g:link>
                </fieldset>
                </div>
            </g:form>

        </div>
    </body>
</html>
