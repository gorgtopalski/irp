<select class="custom-select" id="${property}" name="${property}" required>
    <g:each var="option" in="${type.list()}">
        <option value=${option.id}>${option}</option>
    </g:each>
        <option value="1" selected>1</option>
</select>

