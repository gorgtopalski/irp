<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="page" value="${message(code: 'production.view.index.label')}"/>
        <title><g:message code="title"/> / ${page} </title>
    </head>
    <body>
        <div id="list-production" class="content scaffold-list" role="main">
            <br>
            <div>
                <div class="float-left h1">
                    ${page}
                </div>

                <div class="float-right align-baseline">
                    <g:form action="index">
                        <div class="input-group">
                            <g:select name="max" from="${[10,20,50,100]}" value="${params.max}" class="custom-select"/>
                            <div class="input-group-append">
                                <button class="btn btn-secondary" type="submit" name="index">
                                    <i class="fas fa-search"></i>
                                    <g:message code="show"/>
                                </button>
                            </div>
                        </div>
                    </g:form>
                </div>
            </div>

            <irp:alert flash="${flash}"/>

            <f:table collection="${productionList}"/>

            <div class="pagination">
                <g:paginate total="${productionCount ?: 0}" id="${id}"/>
            </div>

        </div>
    </body>
</html>