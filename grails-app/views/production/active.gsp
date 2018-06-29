<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="page" value="${message(code: 'production.view.active.label')}"/>
        <title><g:message code="title"/> / ${page} </title>
    </head>
    <body>
        <div id="list-production" class="content scaffold-list" role="main">
            <br>
            <h1>${page}</h1>

            <irp:alert flash="${flash}"/>

            <f:table collection="${productionList}" template="activeProductionTable"/>

            <div class="pagination">
                <g:paginate total="${productionCount ?: 0}" />
            </div>

        </div>
    </body>
</html>