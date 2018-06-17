<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'production.label', default: 'Production')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
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

        <div id="edit-production" class="content scaffold-edit" role="main">
            <br>
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>

            <g:if test="${flash.message}">
                <div class="alert alert-${flash.alert ?: 'primary'} alert-dismissible fade show" role="alert">
                    <i class="fas fa-exclamation-circle"></i>
                    ${flash.message}
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
            </g:if>

            <g:hasErrors bean="${this.production}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.production}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form resource="${this.production}" method="PUT">
                <g:hiddenField name="version" value="${this.production?.version}" />
                <fieldset class="form">
                    <f:all bean="production"/>
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
