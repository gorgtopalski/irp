<table class="table table-hover table-striped table-sm">
    <thead>
    <tr>
        <th scope="col">#</th>
        <g:each in="${columnProperties}" var="p" status="i">
            <g:sortableColumn property="${p.property}" title="${p.label}" scope="col"/>
        </g:each>
        <th scope="col">Editar</th>
    </tr>
    </thead>
    <tbody>
    <g:each in="${collection}" var="bean" status="i">
        <tr ondblclick="document.location = '${createLink( controller: 'production', action: 'show', id: bean.id)}'; ">
            <th scope="row">${i+1}</th>
            <g:each in="${columnProperties}" var="p">
                <td>
                    <f:display bean="${bean}" property="${p.property}"  displayStyle="${displayStyle?:'table'}" theme="${theme}"/>
                </td>
            </g:each>
            <td>
                <g:link class="align-baseline text-warning" action="edit" resource="${bean}">
                    <i class="far fa-edit"></i>
                </g:link>
            </td>
        </tr>

    </g:each>
    </tbody>
</table>
