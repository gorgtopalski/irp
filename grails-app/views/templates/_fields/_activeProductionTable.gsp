<table class="table table-hover table-striped table-sm">
    <thead>
    <tr>
        <th scope="col">#</th>
        <g:each in="${domainProperties}" var="p" status="i">
            <g:sortableColumn property="${p.property}" title="${p.label}" scope="col"/>
        </g:each>
        <th scope="col">Editar</th>
        <th scope="col">Cerrar</th>
    </tr>
    </thead>
    <tbody>
    <g:each in="${collection}" var="bean" status="i">
        <tr ondblclick="document.location = '${createLink( action:'show', id: bean.id)}'; ">
            <th scope="row">${i+1}</th>
            <g:each in="${domainProperties}" var="p">
                <td>
                    <f:display bean="${bean}" property="${p.property}"  displayStyle="${displayStyle?:'table'}" theme="${theme}"/>
                </td>
            </g:each>
            <td>
                <g:link class="align-baseline text-primary" action="edit" resource="${bean}">
                    <i class="far fa-edit"></i>
                </g:link>
            </td>
            <td>
                <g:link class="align-baseline text-success" action="close" resource="${bean}">
                    <i class="far fa-check-square"></i>
                </g:link>
            </td>
        </tr>

    </g:each>
    </tbody>

</table>

