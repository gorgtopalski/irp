<select class="custom-select" id="${property}" name="${property}">
    <g:each var="option" in="${type}">
        <option value=${option}>${option.value}</option>
    </g:each>
</select>
