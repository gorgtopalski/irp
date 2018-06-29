<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="Verallia Montblanc IRP"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />

    <asset:stylesheet src="application.css"/>

    <g:layoutHead/>
</head>
<body class="bg-light">

    <nav class="navbar navbar-expand-lg navbar-light bg-white px-md-4 mb-3 border-bottom">
        <g:link controller="home" action="index" class="navbar-brand">
            <img src="/assets/logo.png" height="50" alt="Verallia Montblanc IRPs">
            Verallia Montblanc IRP
        </g:link>
        <button class="navbar-toggler" type="button"
                data-toggle="collapse" data-target="#navbar"
                aria-controls="navbar" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbar">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item mx-1">
                    <g:link controller="irp" action="pending" params="[max:50, sort:'date', order:'desc']" class="btn btn-outline-danger  form-control">
                        <i class="fas fa-eye" aria-hidden="true"></i>
                        Pendientes
                    </g:link>
                </li>
                <li class="nav-item dropdown mx-1">
                    <a class="btn btn-primary dropdown-toggle form-control" href="#" id="irpDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="fa fa-id-card" aria-hidden="true"></i>
                        IRPs
                    </a>
                    <div class="dropdown-menu" aria-labelledby="irpDropdown">
                        <g:link controller="irp" action="index" class="dropdown-item">
                            <i class="fas fa-search" aria-hidden="true"></i>
                            Ver IRPs
                        </g:link>
                    </div>
                </li>
                <li class="nav-item dropdown mx-1">
                    <a class="btn btn-success dropdown-toggle form-control" href="#" id="productionDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="fa fa-industry" aria-hidden="true"></i>
                        <g:message code="production.label"/>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="productionDropdown">
                        <g:link controller="production" action="active" class="dropdown-item">
                            <i class="fa fa-industry" aria-hidden="true"></i>
                            Ver producciones activas
                        </g:link>
                        <g:link controller="production" action="create" class="dropdown-item">
                            <i class="fa fa-plus-square" aria-hidden="true"></i>
                            Nueva producción
                        </g:link>
                        <g:link controller="production" action="index" class="dropdown-item">
                            <i class="fa fa-search" aria-hidden="true"></i>
                            Ver producciones
                        </g:link>
                    </div>
                </li>
                <li class="nav-item dropdown mx-1">
                    <a class="btn btn-secondary dropdown-toggle form-control"
                       href="#" id="modelDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="fa fa-database" aria-hidden="true"></i>
                        <g:message code="model.label"/>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="modelDropdown">
                        <g:link controller="model" action="create" class="dropdown-item">
                            <i class="fa fa-plus-square" aria-hidden="true"></i>
                            Nuevo modelo
                        </g:link>
                        <g:link controller="model" action="index" class="dropdown-item">
                            <i class="fa fa-search" aria-hidden="true"></i>
                            Ver modelos
                        </g:link>
                    </div>
                </li>
            </ul>
            <g:form method="GET" controller="home" action="search" class="form-inline my-2 my-lg-0">
                <input name="query" class="form-control mr-sm-2" type="search" placeholder="${message(code: 'search')}..." aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">${message(code: 'search')}</button>
            </g:form>
        </div>
    </nav>

    <div class="container">
        <main role="main" class="mb-3">
            <g:layoutBody/>
        </main>
    </div>

    <div class="footer" role="contentinfo"></div>

    %{--<div id="spinner" class="spinner" style="display:none;">--}%
        %{--<g:message code="spinner.alt" default="Loading&hellip;"/>--}%
    %{--</div>--}%

    <asset:javascript src="application.js"/>
</body>
</html>
