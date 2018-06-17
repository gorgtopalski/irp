<div class="mb-4">
    <label for="${property}">
        ${label}
    </label>

    <g:if test="${value != null}">

        <table class="table table-hover table-striped table-sm">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Inicio</th>
                <th scope="col">Final</th>
                <th scope="col">Linea</th>
                <th scope="col">Finalizada </th>
                <th scope="col">Ver</th>
            </tr>
            </thead>

            <tbody>
                <g:each in="${value.sort{ it.startDate }.reverse()}" var="p" status="i">
                    <tr ondblclick="document.location = '${createLink(action:'show', id:p.id, controller: 'production')}'; ">
                        <th scope="row">${i+1}</th>
                        <td>
                            ${p.startDate.format('dd-MM-YY')}
                        </td>
                        <td>
                            ${p.finishDate?.format('dd-MM-YY')}
                        </td>
                        <td>
                            ${p.line}
                        </td>
                        <td>
                            ${p.finished ? 'Si' : 'No' }
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