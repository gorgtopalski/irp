<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'critialIrp.label', default: 'CritialIrp')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
            <div class="navbar-nav mr-auto">
                <a class="nav-item nav-link" href="${createLink(uri: '/')}">
                    <i class="fas fa-home"></i>
                    <g:message code="default.home.label"/>
                </a>
                <g:link class="nav-item nav-link" action="index">
                    <i class="far fa-list-alt"></i>
                    <g:message code="default.list.label" args="[entityName]" />
                </g:link>
            </div>
            <g:form method="GET" action="search" controller="critialIrp" class="form-inline my-2 my-lg-0">
                <input name="query" class="form-control mr-sm-2" type="search" placeholder="Buscar..." aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Buscar</button>
            </g:form>
        </nav>

        <div id="create-critialIrp" class="content scaffold-create" role="main">
            <br>
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>

            <g:if test="${flash.message}">
                <div class="alert alert-${flash.alert ?: 'primary'} alert-dismissible fade show" role="alert">
                    <i class="fas fa-exclamation-circle"></i>
                    ${flash.message}
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
            </g:if>


            <g:hasErrors bean="${this.critialIrp}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.critialIrp}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form resource="${this.critialIrp}" method="POST">
                <fieldset class="form">
                    <f:all bean="critialIrp"/>
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
