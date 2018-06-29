<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="page" value="${message(code: 'audit.view.create.label')}"/>
        <title><g:message code="title"/> / ${page} </title>
    </head>
    <body>
        <div id="create-audit" class="content scaffold-create" role="main">
            <br>
            <h1>${page}</h1>

            <irp:alert flash="${flash}"/>

            <g:form resource="${this.audit}" method="POST">
                <fieldset class="form">
                    %{--<f:all bean="audit"/>--}%
                    <f:with bean="audit">

                        <div class="row">
                            <div class="col">
                                <f:field property="irp"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <f:field property="palet"/>
                            </div>
                            <div class="col">
                                <f:field property="comment"/>
                                <f:field property="ok"/>
                            </div>
                        </div>
                    </f:with>
               </fieldset>

                <fieldset class="buttons">
                    <div class="float-right">
                        <button type="submit" name="create" class="btn btn-success">
                            <i class="far fa-save"></i>
                            ${message(code: 'default.button.create.label', default: 'Crear')}
                        </button>
                    </div>
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
