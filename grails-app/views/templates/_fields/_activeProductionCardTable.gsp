<div class="row">
    <g:each in="${collection}" var="bean" status="i">
        <div class="col">
            <%
                def border = "border-$bean.line.name"
                def modelName = bean.model.name.size() > 18 ? bean.model.name.take(18).toString()+"..." : bean.model.name
            %>
            <div class="card ${border}">
                <div class="card-header">
                    <h5>L-${bean.line}</h5>
                    <h6>${modelName}</h6>
                </div>
                <div class="card-body">
                    <g:form controller="production" action="updatePallets" method="PUT" id="${bean.id}">
                        <div class="input-group mb-3">
                            <input name="pallets" type="text" class="form-control" value="${bean.totalPallets}">
                            <div class="input-group-append">
                                <button class="btn btn-outline-success" type="submit"><i class="fas fa-box"></i></button>
                            </div>
                        </div>
                    </g:form>
                    <div class="btn-toolbar float-right" role="toolbar">
                        <div class="btn-group btn-group-sm" role="group">
                            <g:link class="btn btn-outline-dark" action="show" resource="${bean}"
                                    data-toggle="tooltip" data-placement="top" title="${message(code:'tooltip.ver')}">
                                <i class="fas fa-search"></i>
                            </g:link>
                            <% def pending = bean.irp.pending.count { it} %>
                            <g:link class="btn btn-outline-dark" controller="irp" action="pending" params="['production': bean.id ]"
                                    data-toggle="tooltip" data-placement="top" title="${message(code:'tooltip.pending')}">
                                <span class="badge badge-info">${pending}</span>
                            </g:link>
                            <g:link class="btn btn-outline-dark" controller="irp" action="create"
                                    params="['production': bean.id]"
                                    data-toggle="tooltip" data-placement="top" title="${message(code:'tooltip.irp')}">
                                <i class="fas fa-plus-square text-warning"></i>
                            </g:link>
                            <g:link class="btn btn-outline-dark" controller="irp" action="createCritical"
                                    params="['production': bean.id]"
                                    data-toggle="tooltip" data-placement="top" title="${message(code:'tooltip.critical')}">
                                <i class="fas fa-exclamation-triangle text-danger"></i>
                            </g:link>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </g:each>
</div>



