<div class="form-group mb-3">
    <g:if test="${errors}">
        <g:each in="${errors}" var="error">
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                <g:message error="${error}"/>
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </g:each>
    </g:if>

    <label for="${property}">
        ${label}
        <g:if test="${required}">
            <span class="required-indicator">*</span>
        </g:if>
    </label>

    <%
        def time = value ?: new Date()
        def selectHour = time?.hours
        def selectedMinutes =  time?.minutes - time?.minutes % 5
    %>

    <div class="input-group">
        <input name="${property}" type="text" class="pickdate form-control w-50" value="${value?.format('dd/MM/yyyy')}">
        <select name="${property}Hours" class="custom-select px-0 mx-0 w-25">
            <g:each in="${0..23}" var="i">
                <% def val = i >= 0 && i <= 9 ? "0$i" : i   %>
                <g:if test="${i == selectHour}">
                    <option value="${val}" selected>${val}</option>
                </g:if>
                <g:else>
                    <option value="${val}">${val}</option>
                </g:else>
            </g:each>
        </select>
        <select name="${property}Minutes" class="custom-select px-0 mx-0 w-25">
            <g:each in="${(0..59).grep { it % 5 == 0}}" var="i">
                <% def val = i >= 0 && i <= 9 ? "0$i" : i   %>
                <g:if test="${i == selectedMinutes}">
                    <option value="${val}" selected>${val}</option>
                </g:if>
                <g:else>
                    <option value="${val}">${val}</option>
                </g:else>
            </g:each>
        </select>

    </div>

</div>
