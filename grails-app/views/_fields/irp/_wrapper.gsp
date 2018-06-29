<div class="form-group mb-3">
    <label for="${property}">
        ${label}

        <g:if test="${required}">
            <span class="required-indicator">*</span>
        </g:if>

    </label>

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

    <f:widget property="${property}"/>
</div>