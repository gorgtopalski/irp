<div class="mb-4">
    <label for="${property}">
        ${label}
    </label>

    <g:if test="${value != null}">

        <table class="table table-hover table-striped table-sm">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col"><g:message code="irp.date.label"/></th>
                <th scope="col" data-toggle="tooltip" data-placement="top" title="${message(code:'tooltip.ppr')}">PPR</th>
                <th scope="col" data-toggle="tooltip" data-placement="top" title="${message(code:'tooltip.upr')}">UPR</th>
                <th scope="col"><g:message code="irp.motive.label"/></th>
                <th scope="col"><g:message code="irp.critical.label"/></th>
                <th scope="col"><g:message code="irp.pending.label"/></th>
                <th scope="col"><g:message code="irp.shift.label"/></th>
                <th scope="col"><g:message code="irp.team.label"/></th>
                <th scope="col"><g:message code="show"/></th>
            </tr>
            </thead>
            <tbody>
                <g:each in="${value}" var="p" status="i">
                    <tr ondblclick="document.location = '${createLink(action:'show', id:p.id, controller: 'production')}'; ">
                        <th scope="row">${i+1}</th>
                        <td>
                            ${p.date.format('dd-MM-YY')}
                        </td>
                        <td>
                            ${p.firstPallet}
                        </td>
                        <td>
                            ${p.lastPallet}
                        </td>
                        <td>
                            ${p.motive}
                        </td>
                        <td>
                            ${p.critical ? 'Si' : 'No'}
                        </td>
                        <td>
                            ${p.pending ? 'Si' : 'No'}
                        </td>
                        <td>
                            ${p.shift}
                        </td>
                        <td>
                            ${p.team}
                        </td>

                        <td>
                            <g:link class="align-baseline text-success" action="show" resource="${p}">
                                <i class="fas fa-search"></i>
                            </g:link>
                        </td>
                    </tr>
                </g:each>
            </tbody>
        </table>

    </g:if>
</div>