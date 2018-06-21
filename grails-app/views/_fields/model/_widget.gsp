<select class="custom-select" id="${property}" name="${property}">
    <g:each var="option" in="${type.list()}">

        <g:if test="${ value != null}">
            <g:if test="${value == option}">
                <option selected value=${option.id}>${option}</option>
            </g:if>
        </g:if>

        <option value=${option.id}>${option}</option>
    </g:each>
</select>
