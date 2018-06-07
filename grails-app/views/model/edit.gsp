<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'model.label', default: 'Model')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#edit-model" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        %{--<div class="nav" role="navigation">--}%
            %{--<ul>--}%
                %{--<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>--}%
                %{--<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>--}%
                %{--<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>--}%
            %{--</ul>--}%
        %{--</div>--}%
        <div class="nav list-group" role="navigation">
            <ul>
                <li>
                    <a class="list-group-item list-group-item-action" href="${createLink(uri: '/')}">
                        <i class="fas fa-home"></i>
                        <g:message code="default.home.label"/>
                    </a>
                </li>
                <li>
                    <g:link class="list-group-item list-group-item-action" action="index">
                        <i class="far fa-list-alt"></i>
                        <g:message code="default.list.label" args="[entityName]" />
                    </g:link>
                </li>
                <li>
                    <g:link class="list-group-item list-group-item-action" action="create">
                        <i class="far fa-plus-square"></i>
                        <g:message code="default.new.label" args="[entityName]"/>
                    </g:link>
                </li>
            </ul>
        </div>



        <div id="edit-model" class="content scaffold-edit" role="main">
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


            <g:hasErrors bean="${this.model}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.model}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form resource="${this.model}" method="PUT">
                <g:hiddenField name="version" value="${this.model?.version}" />
                <fieldset class="form">
                    <f:all bean="model"/>
                </fieldset>
                <fieldset class="buttons">
                    <input class="save" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
