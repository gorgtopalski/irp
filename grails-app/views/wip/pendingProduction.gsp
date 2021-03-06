<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="model" value="${production}" />
        <g:set var="entityName" value="${message(code: 'wip.label', default: 'Irp')}" />
        <title><g:message code="irp.pending.list.label" args="[model]" /></title>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
            <div class="navbar-nav mr-auto">
                <a class="nav-item nav-link" href="${createLink(uri: '/')}">
                    <i class="fas fa-home"></i>
                    <g:message code="default.home.label"/>
                </a>
                <g:link class="nav-item nav-link" action="create" params="[production: production.id]">
                    <i class="far fa-plus-square"></i>
                    <g:message code="irp.new.label"/>
                </g:link>
                <g:link class="nav-item nav-link text-danger" controller="critialIrp" action="create" params="[production: production.id]">
                    <i class="fas fa-exclamation-circle"></i>
                    <g:message code="irp.new.critical.label"/>
                </g:link>
            </div>
            <g:form method="GET" action="search" controller="irp" class="form-inline my-2 my-lg-0">
                <input name="query" class="form-control mr-sm-2" type="search" placeholder="Buscar..." aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Buscar</button>
            </g:form>
        </nav>

        <div id="list-irp" class="content scaffold-list" role="main">
            <br>
            <h1><g:message code="irp.pending.list.label" args="[model]" /></h1>

            <g:if test="${flash.message}">
                <div class="alert alert-${flash.alert ?: 'primary'} alert-dismissible fade show" role="alert">
                    <i class="fas fa-exclamation-circle"></i>
                    ${flash.message}
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
            </g:if>
            <f:table collection="${irps}"/>
        </div>
    </body>
</html>