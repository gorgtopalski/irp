<div class="form-group">
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
    <div class="mb-3">

        <label for="${property}">
            ${label}
            <g:if test="${required}">
                <span class="required-indicator">*</span>
            </g:if>
        </label>

        <f:widget property="${property}"/>
    </div>
</div>




