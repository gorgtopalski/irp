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
<body class="bg-light">
    <div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom box-shadow">
        <a class="my-0 mr-md-auto font-weight-normal h5" href="/">Verallia Montblanc IRPs</a>
        <nav class="my-2 my-md-0 mr-md-3">
            <g:link controller="irp" action="index" class="btn btn-outline-danger">
                <i class="fas fa-eye" aria-hidden="true"></i>
                Pendientes
            </g:link>

            <div class="btn-group">
                <button class="btn btn-primary dropdown-toggle" type="button" id="irpDropDown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <i class="fa fa-id-card" aria-hidden="true"></i>
                    IRPs
                </button>
                <div class="dropdown-menu" aria-labelledby="irpDropDown">
                    <g:link controller="irp" action="index" class="dropdown-item">
                        <i class="fas fa-search" aria-hidden="true"></i>
                        Ver IRPs
                    </g:link>
                </div>
            </div>

            <div class="btn-group">
                <button class="btn btn-success dropdown-toggle" type="button" id="productionDropDown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <i class="fa fa-industry" aria-hidden="true"></i>
                    Producción
                </button>
                <div class="dropdown-menu" aria-labelledby="productionDropDown">
                    <g:link controller="production" action="active" class="dropdown-item">
                        <i class="fa fa-industry" aria-hidden="true"></i>
                        Ver producciones activas
                    </g:link>
                    <g:link controller="production" action="create" class="dropdown-item">
                        <i class="fa fa-plus-square" aria-hidden="true"></i>
                        Nueva producción
                    </g:link>
                    %{--<g:link controller="production" action="edit" class="dropdown-item">--}%
                        %{--<i class="fa fa-check-square" aria-hidden="true"></i>--}%
                        %{--Cerrar producción--}%
                    %{--</g:link>--}%
                    <g:link controller="production" action="index" class="dropdown-item">
                        <i class="fa fa-search" aria-hidden="true"></i>
                        Ver producciones
                    </g:link>
                </div>
            </div>

            <div class="btn-group">
                <button class="btn btn-secondary dropdown-toggle" type="button" id="productionDropDown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <i class="fa fa-database" aria-hidden="true"></i>
                    Modelo
                </button>
                <div class="dropdown-menu" aria-labelledby="productionDropDown">
                    <g:link controller="model" action="create" class="dropdown-item">
                        <i class="fa fa-plus-square" aria-hidden="true"></i>
                        Nuevo modelo
                    </g:link>
                    <g:link controller="model" action="index" class="dropdown-item">
                        <i class="fa fa-search" aria-hidden="true"></i>
                        Ver modelos
                    </g:link>
                </div>
            </div>

        </nav>
    </div>

    <div class="container">
        <main role="main" class="mb-3">
            <g:layoutBody/>
        </main>
    </div>




<div class="footer" role="contentinfo"></div>

    <div id="spinner" class="spinner" style="display:none;">
        <g:message code="spinner.alt" default="Loading&hellip;"/>
    </div>

    <asset:javascript src="application.js"/>

</body>
</html>
