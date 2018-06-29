<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="page" value="${message(code: 'audit.view.index.label')}"/>
        <title><g:message code="title"/> / ${page} </title>
    </head>
    <body>

        <div id="list-audit" class="content scaffold-list" role="main">
            <br>
            <h1>${page}</h1>

            <irp:alert flash="${flash}"/>

            <f:table collection="${auditList}" />

            <div class="pagination">
                <g:paginate total="${auditCount ?: 0}" />
            </div>

        </div>
    </body>
</html>