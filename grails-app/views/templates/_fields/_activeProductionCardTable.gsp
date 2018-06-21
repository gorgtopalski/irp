<div class="row">
    <g:each in="${collection}" var="bean" status="i">
        <div class="col">
            <%
                def border = "border-$bean.line.name"
            %>
            <div class="card ${border}">
                <div class="card-header">
                    <h5>L-${bean.line}</h5>
                </div>
                <div class="card-body">
                    <h6 class="card-title">${bean.model.name}</h6>
                    <g:form controller="production" action="updatePallets" method="PUT" id="${bean.id}">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fas fa-box"></i></span>
                            </div>

                            <input name="pallets" type="text" class="form-control" value="${bean.totalPallets}">
                            <div class="input-group-append">
                                <button class="btn btn-outline-success" type="submit"><i class="fas fa-coins"></i></button>
                            </div>
                        </div>
                    </g:form>
                    <div class="btn-group btn-group-sm" role="group">
                        <g:link class="btn btn-outline-secondary" action="show" resource="${bean}"
                                data-toggle="tooltip" data-placement="top" title="${message(code:'tooltip.ver')}">
                            <i class="fas fa-search"></i>
                        </g:link>
                        <g:link class="btn btn-outline-dark" controller="irp" action="create" params="['production.id': bean.id ]"
                                data-toggle="tooltip" data-placement="top" title="${message(code:'tooltip.irp')}">
                            <i class="fas fa-plus-square"></i>
                        </g:link>
                        <g:link class="btn btn-outline-danger" controller="critialIrp" action="create" params="['production.id': bean.id ]"
                                data-toggle="tooltip" data-placement="top" title="${message(code:'tooltip.critical')}">
                            <i class="fas fa-exclamation-triangle"></i>
                        </g:link>
                    </div>
                </div>
            </div>
        </div>
    </g:each>
</div>



