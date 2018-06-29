<table class="table table-hover table-striped table-sm">
    <thead>
    <tr>
        <th scope="col">#</th>
        <g:each in="${domainProperties}" var="p" status="i">
            <th scope="col">${p.label}</th>
        </g:each>
        <th scope="col"><g:message code="edit"/></th>
        <th scope="col"><g:message code="show"/></th>
        <th scope="col"><g:message code="audit"/></th>
    </tr>
    </thead>
    <tbody>
    <g:each in="${collection}" var="bean" status="i">
        <tr ondblclick="document.location = '${createLink(controller: 'irp', action: 'show', id: bean.id)}'">
            <th scope="row">${i+1}</th>
            <g:each in="${domainProperties}" var="p">
                <td>
                    <f:display bean="${bean}" property="${p.property}" displayStyle="${displayStyle?:'table'}" theme="${theme}"/>
                </td>
            </g:each>
            <td>
                <g:link class="text-primary" action="edit" resource="${bean}">
                    <i class="far fa-edit"></i>
                </g:link>
            </td>
            <td>
                <g:link class="text-success" action="show" resource="${bean}">
                    <i class="fas fa-search"></i>
                </g:link>
            </td>
            <td>
                <% def p = bean.lastPallet ? bean.lastPallet-1 : bean.firstPallet ? bean.firstPallet-1 : 0%>
                <g:link class="text-info" controller="audit" action="create" params="[irp: bean.id, palet: p]">
                    <i class="fas fa-clipboard-list"></i>
                </g:link>
            </td>
        </tr>
    </g:each>
    </tbody>
</table>

