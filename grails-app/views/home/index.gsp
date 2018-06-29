<%@ page import="grails.converters.JSON" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title><g:message code="title"/></title>
        <asset:javascript src="Chart.bundle.min.js"/>
    </head>
    <body>
        <irp:alert flash="${flash}"/>
        <f:table collection="${productionList}" template="activeProductionCardTable"/>
        <br>
        <g:if test="${!irpList.empty}">
            <h5><g:message code="home.pending"></g:message></h5>
            <f:table collection="${irpList}"
                     properties="['date', 'production', 'shift', 'team', 'motive', 'critical']"
                     template="showIrpsTable"
                     displayStyle="noLink"
            />
        </g:if>
        <div class="row">
            <div class="col">
                <canvas id="stackedByLine"></canvas>
            </div>
            <div class="col">
                <canvas id="totalRejection"></canvas>
            </div>
        </div>

        <script type="text/javascript">
            <g:applyCodec encodeAs="none">
                var data = ${data};
            </g:applyCodec>

            var ctx = document.getElementById("totalRejection").getContext('2d');
            var myLineChart = new Chart(ctx, {
                type: 'line',
                data: {
                    labels: data.labels,
                    datasets: [{
                        label: 'Palets rechazados',
                        fill: false,
                        backgroundColor: 'rgba(237, 83, 83, 1)',
                        borderColor: 'rgba(237, 83, 83, 0.6)',
                        data: data.dataset
                    }]
                },
                options: {
                    legend: {
                        display: true,
                        position: 'bottom',
                    },
                    responsive: true,
                    title: {
                        display: true,
                        text: "${message(code: 'chart.title')}"
                    },
                    tooltips: {
                        mode: 'index',
                        intersect: false,
                    },
                    hover: {
                        mode: 'nearest',
                        intersect: true
                    },
                    scales: {
                        xAxes: [{
                            display: true,
                            scaleLabel: {
                                display: true
                                //labelString: "${message(code: 'chart.x.label')}"
                            }
                        }],
                        yAxes: [{
                            // ticks: {
                            //     stepSize: 1
                            // },
                            display: true,
                            scaleLabel: {
                                display: true,
                                labelString: "${message(code: 'chart.y.label')}"
                            }
                        }]
                    }
                }
            });
        </script>
        <script type="text/javascript">
        <g:applyCodec encodeAs="none">
        var data = ${stack};
        </g:applyCodec>

        var ctx = document.getElementById("stackedByLine").getContext('2d');
        var barChartData = {
            labels: data.labels,
            datasets: [{
                label: 'Aceptados',
                backgroundColor: 'rgba(155, 219, 77, 1)',
                data: data.accepted
            }, {
                label: 'Rechazados',
                backgroundColor: 'rgba(237, 83, 83, 1)',
                data: data.rejected
            }]
        };
        var myLineChart = new Chart(ctx, {
            type: 'horizontalBar',
            data: barChartData,
            options: {
                legend: {
                    display: true,
                    position: 'bottom',
                },
                title: {
                    display: true,
                    text: 'Rechazos por modelo'
                },
                tooltips: {
                    mode: 'index',
                    intersect: false
                },
                responsive: true,
                scales: {
                    xAxes: [{
                        stacked: true,
                    }],
                    yAxes: [{
                        stacked: true
                    }]
                }
            }
        });
        </script>
    </body>
</html>