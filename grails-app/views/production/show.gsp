<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="page" value="${message(code: 'production.view.show.label')}"/>
        <title><g:message code="title"/> / ${page} </title>
    </head>
    <body>
        <div id="show-production" class="content scaffold-show" role="main">
            <br>
            <h1>${page}</h1>

            <irp:alert flash="${flash}"/>

            <f:with bean="production">
                <f:display property="model" wrapper="domainFields"/>

                <div class="row">
                    <div class="col-md-6">
                        <f:display property="startDate" wrapper="domainDate"/>
                        <f:display property="finishDate" wrapper="domainDate"/>
                    </div>
                    <div class="col-md-6">
                        <f:display property="line" wrapper="domainFields"/>
                        <f:display property="active" wrapper="domainBoolean"/>
                        <f:display property="finished" wrapper="domainBoolean"/>
                    </div>
                </div>
                <f:display property="totalPallets" wrapper="domainFields"/>


                    <fieldset class="buttons">
                        <div class="float-left">
                            <g:form resource="${this.production}" method="DELETE">
                                <button type="submit" name="delete" class="btn btn-danger" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">
                                    <i class="fas fa-trash-alt"></i>
                                    ${message(code: 'default.button.delete.label', default: 'Delete')}
                                </button>
                            </g:form>
                        </div>
                        <div class="float-right">
                            <g:link class="btn btn-info" action="map" resource="${this.production}">
                                <i class="fas fa-globe"></i>
                                <g:message code="production.button.map.label" default="Ver mapa" />
                            </g:link>
                            <g:if test="${!production.finished}">
                                <g:link class="btn btn-success" action="close" resource="${this.production}">
                                    <i class="fas fa-check"></i>
                                    Cerrar producción
                                </g:link>
                            </g:if>
                            <g:link class="btn btn-warning" action="edit" resource="${this.production}">
                                <i class="far fa-edit"></i>
                                <g:message code="default.button.edit.label" default="Edit" />
                            </g:link>
                    </fieldset>
                    </div>

            </f:with>
            <br>
            <f:display bean='production' property="irp" wrapper="irpDisplayWrapper"/>
        </div>
    </body>
</html>
