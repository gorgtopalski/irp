<select class="custom-select" id="${property}" name="${property}">
    <g:each var="option" in="${com.verallia.enums.Line.values()}">
        <%= option.properties.each { println it } %>

        <option value="${option}">${option.display}</option>
    </g:each>
</select>

