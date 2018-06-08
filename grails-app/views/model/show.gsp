<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'model.label', default: 'Model')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        %{--<a href="#show-model" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>--}%
        %{--<div class="nav" role="navigation">--}%
            %{--<ul>--}%
                %{--<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>--}%
                %{--<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>--}%
                %{--<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>--}%
            %{--</ul>--}%
        %{--</div>--}%
        %{--<div class="nav list-group" role="navigation">--}%
            %{--<ul>--}%
                %{--<li>--}%
                    %{--<a class="list-group-item list-group-item-action" href="${createLink(uri: '/')}">--}%
                        %{--<i class="fas fa-home"></i>--}%
                        %{--<g:message code="default.home.label"/>--}%
                    %{--</a>--}%
                %{--</li>--}%
                %{--<li>--}%
                    %{--<g:link class="list-group-item list-group-item-action" action="index">--}%
                        %{--<i class="far fa-list-alt"></i>--}%
                        %{--<g:message code="default.list.label" args="[entityName]" />--}%
                    %{--</g:link>--}%
                %{--</li>--}%
                %{--<li>--}%
                    %{--<g:link class="list-group-item list-group-item-action" action="create">--}%
                        %{--<i class="far fa-plus-square"></i>--}%
                        %{--<g:message code="default.new.label" args="[entityName]"/>--}%
                    %{--</g:link>--}%
                %{--</li>--}%
            %{--</ul>--}%
        %{--</div>--}%

        <nav class="navbar navbar-expand-lg navbar-light bg-light">
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
            <g:form method="GET" action="search" controller="model" class="form-inline my-2 my-lg-0">
                <input name="query" class="form-control mr-sm-2" type="search" placeholder="Buscar..." aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Buscar</button>
            </g:form>
        </nav>


        <div id="show-model" class="content scaffold-show" role="main">
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

            <f:display bean="model" />
            <g:form resource="${this.model}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.model}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
