<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'production.label', default: 'Production')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
            <div class="navbar-nav mr-auto">
                <a class="nav-item nav-link" href="${createLink(uri: '/')}">
                    <i class="fas fa-home"></i>
                    <g:message code="default.home.label"/>
                </a>
                <g:link class="nav-item nav-link" action="create">
                    <i class="far fa-plus-square"></i>
                    <g:message code="default.new.label" args="[entityName]"/>
                </g:link>
                <g:link class="nav-item nav-link" action="index">
                    <i class="far fa-list-alt"></i>
                    <g:message code="default.list.label" args="[entityName]" />
                </g:link>
            </div>
            <g:form method="GET" action="search" controller="production" class="form-inline my-2 my-lg-0">
                <input name="query" class="form-control mr-sm-2" type="search" placeholder="Buscar..." aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Buscar</button>
            </g:form>
        </nav>


        <div id="show-production" class="content scaffold-show" role="main">
            <br>
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>

            <g:if test="${flash.message}">
                <div class="alert alert-${flash.alert ?: 'primary'} alert-dismissible fade show" role="alert">
                    <i class="fas fa-exclamation-circle"></i>
                    ${flash.message}
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
            </g:if>

            <f:display bean="production" />

            <g:form resource="${this.production}" method="DELETE">
                <fieldset class="buttons">
                    <div class="float-left">
                        <button type="submit" name="delete" class="btn btn-danger" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">
                            <i class="far fa-trash-alt"></i>
                            ${message(code: 'default.button.delete.label', default: 'Delete')}
                        </button>
                    </div>
                    <div class="float-right">
                        <g:link class="btn btn-warning" action="edit" resource="${this.production}">
                            <i class="far fa-edit"></i>
                            <g:message code="default.button.edit.label" default="Edit" />
                        </g:link>
                </fieldset>
                </div>
            </g:form>

        </div>
    </body>
</html>
