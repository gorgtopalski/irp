<%@ page import="grails.converters.JSON" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title><g:message code="title"/> / <g:message code="search"/> ${params.query}</title>
    </head>
    <body>

        <irp:alert flash="${flash}"/>
        <g:if test="${data.models}">
            <h4>
                Modelos
            </h4>
            <f:table collection="${data.models}" domainClass="com.verallia.Model" except="productions, id" />
        </g:if>

        <g:if test="${data.productions}">
            <br>
            <h4>
                Producciones
            </h4>

            <div class="nav nav-tabs" id="v-pills-tab" role="tablist">
                <g:each in="${data.productions}" var="key,value" status="i">

                    <g:if test="${i == 0}">
                        <a class="nav-link active"
                           id="${i}-tab" data-toggle="pill" href="#${i}"
                           role="tab" aria-controls="v-pills-home" aria-selected="true">${key}</a>
                    </g:if>
                    <g:else>
                        <a class="nav-link"
                           id="${i}-tab" data-toggle="pill" href="#${i}"
                           role="tab" aria-controls="v-pills-home" aria-selected="false">${key}</a>
                    </g:else>

                </g:each>
            </div>

            <div class="tab-content" id="v-pills-tabContent">
                <g:each in="${data.productions}" var="key,value" status="i">

                    <g:if test="${i == 0}">
                        <div class="tab-pane fade show active" id="${i}" role="tabpanel" aria-labelledby="${i}-tab">
                            <f:table collection="${value}" domainClass="com.verallia.Production"/>
                        </div>
                    </g:if>
                    <g:else>
                        <div class="tab-pane fade" id="${i}" role="tabpanel" aria-labelledby="${i}-tab">
                            <f:table collection="${value}" domainClass="com.verallia.Production"/>
                        </div>
                    </g:else>

                </g:each>
            </div>
        </g:if>
    </body>
</html>