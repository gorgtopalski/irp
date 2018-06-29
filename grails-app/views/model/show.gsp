<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="page" value="${message(code: 'model.view.show.label')}"/>
        <title><g:message code="title"/> / ${page} </title>
    </head>
    <body>
        <div id="show-model" class="content scaffold-show" role="main">
            <br>
            <h1>${page}</h1>

            <irp:alert flash="${flash}"/>

            <f:with bean="model">
                <f:display property="name" wrapper="domainFields"/>
                <f:display property="blueprint" wrapper="domainFields"/>
            </f:with>

            <g:form resource="${this.model}" method="DELETE">
                <fieldset class="buttons">
                    <div class="float-left">
                        <button type="submit" name="delete" class="btn btn-danger" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">
                            <i class="far fa-trash-alt"></i>
                            ${message(code: 'default.button.delete.label', default: 'Delete')}
                        </button>
                    </div>
                    <div class="float-right">
                        <g:link class="btn btn-success" action="create" controller="production" params="['model.id': this.model.id ]">
                            <i class="far fa-plus-square"></i>
                            Nueva produccion
                        </g:link>
                        <g:link class="btn btn-warning" action="edit" resource="${this.model}">
                            <i class="far fa-edit"></i>
                            <g:message code="default.button.edit.label" default="Edit" />
                        </g:link>
                    </div>
                </fieldset>
            </g:form>
            <br>
            <f:table   collection="${this.model.productions}"
                       domainClass="com.verallia.Production"
                       template="showProductionFromModelTable"
                       except="['model', 'id', 'irp']"
            />
        </div>
    </body>
</html>
