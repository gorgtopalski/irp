<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="page" value="${message(code: 'production.view.map.label')}"/>
        <title><g:message code="title"/> / ${page} </title>
    </head>
    <body>
        <div id="show-production" class="content scaffold-show" role="main">
            <br>
            <h1>${page}</h1>

            <irp:alert flash="${flash}"/>

            <f:display bean="production" property="model" wrapper="domainFields"/>

            <div class="row">
                    <div class="col">
                        <label>
                            Producidos
                        </label>
                        <input type="text" class="form-control" readonly value="${stats.produced}">
                    </div>
                    <div class="col">
                        <label>
                            Aceptados
                        </label>
                        <input type="text" class="form-control" readonly value="${stats.accepted}">
                    </div>
                    <div class="col">
                        <label>
                            Rechazados
                        </label>
                        <input type="text" class="form-control" readonly value="${stats.rejected}">
                    </div>
                    <div class="col">
                        <label>
                            Tasa
                        </label>
                        <input type="text" class="form-control" readonly value="${((stats.ratio * 100) as Double).round(2)} %">
                    </div>
                </div>

                <hr>
                <table class="table table-hover table-striped table-sm">
                    <thead>
                    <tr>
                        <th scope="col">Palet</th>
                        <th scope="col">A/R</th>
                        <th scope="col">Motivo</th>
                        <th scope="col">Turno</th>
                        <th scope="col">Equipo</th>
                        <th scope="col">IRP</th>
                    </tr>
                    </thead>
                    <tbody>
                    <g:each in="${palets}" var="key,value" status="i">
                        <tr class="${value.accepted ? '' : 'bg-danger-lite' }">
                            <th scope="row">${key}</th>
                            <td>
                                ${value.accepted ? 'A' : 'R' }
                            </td>
                            <td>
                                ${value.irps?.motive ?: '' }
                            </td>

                            <td>
                                ${value.irps?.shift?.unique() ?: '' }
                            </td>
                            <td>
                                ${value.irps?.team?.unique() ?: '' }
                            </td>
                            <td>
                                <g:if test="${value.irps}">
                                    <g:each in="${value.irps}" var="irp">
                                        <g:link class="align-baseline text-primary" controller="irp" action="show" id="${irp.id}"
                                            data-toggle="tooltip" data-placement="top" title="${irp.motive}">
                                            <i class="fas fa-search"></i>
                                        </g:link>
                                    </g:each>
                                </g:if>
                            </td>
                        </tr>

                    </g:each>
                    </tbody>
                </table>
            <br>
        </div>
    </body>
</html>
