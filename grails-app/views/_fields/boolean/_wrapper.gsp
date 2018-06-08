<div class="form-group form-check">
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
    <label class="form-check-label" for="${property}">${label}</label>
</div>




