<%@ page import="java.time.LocalTime; java.time.temporal.ChronoField; java.time.temporal.TemporalField; java.time.Instant; java.sql.Time" %>
<%
    def shift
    switch(LocalTime.now().hour) {
        case 6..14:
            shift = 2
            break
        case 14..22:
            shift = 3
            break
        default:
            shift = 1
    }
%>
<select class="custom-select" id="${property}" name="${property}">
    <g:each var="option" in="${com.verallia.Shift.all}">

        <g:if test="${option.id == shift }">
            <option value="${option.id}" selected>${option.name}</option>
        </g:if>
        <g:else>
            <option value="${option.id}">${option.name}</option>
        </g:else>
    </g:each>
</select>