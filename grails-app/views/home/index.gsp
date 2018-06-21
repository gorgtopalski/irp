<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'model.label', default: 'Model')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>

    </head>
    <body>
        <f:table collection="${productionList}" template="activeProductionCardTable"/>
        <br>
        <h5>Ultimos 10 IRPs pendientes</h5>
        <f:table collection="${irpList}"
                 properties="['date', 'production', 'shift', 'team', 'motive', 'critical']"
                 template="showIrpsTable"/>
    </body>

</html>