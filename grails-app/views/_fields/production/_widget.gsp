%{--<select class="custom-select" id="${property}" name="${property}" required>--}%
    %{--<g:each var="option" in="${type.list()}">--}%
        %{--<option value=${option.id}>${option}</option>--}%
    %{--</g:each>--}%
        %{--<option value="1" selected>1</option>--}%
%{--</select>--}%

<select class="custom-select" id="${property}" name="${property}">
    <g:each var="option" in="${com.verallia.Production.findAllByActive(true)}">
        <option value="${option.id}">${option.model}</option>
        <g:if test="${option.id == value?.id }">
            <option value="${option.id}" selected>${option.model}</option>
        </g:if>
    </g:each>
</select>