
//Call bootstrap date picker
$(".pickdate").datepicker({
    format: "dd/mm/yyyy",
    todayBtn: "linked",
    language: 'es',
    autoclose: true,
    todayHighlight: true
});


//Bootstrap tooltip
$(function () {
    $('[data-toggle="tooltip"]').tooltip()
});