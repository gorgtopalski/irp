<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="Grails"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />

    <asset:stylesheet src="application.css"/>

    <g:layoutHead/>
</head>
<body>
    <div class="container-fluid">


        <div class="row">
            <div class="veralliaHeader"></div>
        </div>


        <div class="row">

            <nav class="col-md-2 d-none d-md-block bg-light sidebar">
                <div class="sidebar-sticky">
                    <div class="list-group">
                        <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-danger">
                            <span>IRPS</span>
                            <i class="fa fa-id-card" aria-hidden="true"></i>
                        </h6>
                        <g:link controller="IRP" action="create" class="list-group-item list-group-item-action">
                            <i class="fa fa-plus-square" aria-hidden="true"></i>
                            Nuevo IRP
                        </g:link>
                        <g:link controller="IRP" action="index" class="list-group-item list-group-item-action">
                            <i class="fas fa-eye" aria-hidden="true"></i>
                            Ver pendientes
                        </g:link>
                        <g:link controller="IRP" action="index" class="list-group-item list-group-item-action">
                            <i class="fas fa-search" aria-hidden="true"></i>
                            Ver IRPs
                        </g:link>
                    </div>

                    <div class="list-group">
                        <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-success">
                            <span>Producción</span>
                            <i class="fa fa-industry" aria-hidden="true"></i>
                        </h6>
                        <g:link controller="production" action="active" class="list-group-item list-group-item-action">
                            <i class="fa fa-industry" aria-hidden="true"></i>
                            Ver producciones activas
                        </g:link>
                        <g:link controller="production" action="create" class="list-group-item list-group-item-action">
                            <i class="fa fa-plus-square" aria-hidden="true"></i>
                            Nueva producción
                        </g:link>
                        <g:link controller="production" action="edit" class="list-group-item list-group-item-action">
                            <i class="fa fa-check-square" aria-hidden="true"></i>
                            Cerrar producción
                        </g:link>
                        <g:link controller="production" action="index" class="list-group-item list-group-item-action">
                            <i class="fa fa-search" aria-hidden="true"></i>
                            Ver producciones
                        </g:link>
                    </div>

                    <div class="list-group">
                        <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-primary">
                            <span>Modelo</span>
                            <i class="fa fa-database" aria-hidden="true"></i>
                        </h6>
                        <g:link controller="model" action="create" class="list-group-item list-group-item-action">
                            <i class="fa fa-plus-square" aria-hidden="true"></i>
                            Nuevo modelo
                        </g:link>
                        <g:link controller="model" action="index" class="list-group-item list-group-item-action">
                            <i class="fa fa-search" aria-hidden="true"></i>
                            Ver modelos
                        </g:link>
                    </div>
                </div>
            </nav>


            <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-3 mt-3 mb-1">
                <g:layoutBody/>
            </main>
        </div>
    </div>

    <div class="footer" role="contentinfo"></div>

    <div id="spinner" class="spinner" style="display:none;">
        <g:message code="spinner.alt" default="Loading&hellip;"/>
    </div>

    <asset:javascript src="application.js"/>

</body>
</html>
