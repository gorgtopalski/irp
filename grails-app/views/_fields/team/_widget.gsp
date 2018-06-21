<select class="custom-select" id="${property}" name="${property}">
    <g:each var="option" in="${com.verallia.Team.all}">
        <option value="${option.id}">${option.id}</option>
        <g:if test="${option.id == value?.id }">
            <option value="${option.id}" selected>${option.name}</option>
        </g:if>
    </g:each>
</select>
