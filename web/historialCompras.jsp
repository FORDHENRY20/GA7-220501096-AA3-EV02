<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.login.Venta"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>Mis Compras</title>

<style>

body{

    font-family: Arial;
    background:#f5f5f5;
    margin:30px;

}

h1{

    color:#333;

}

table{

    width:100%;
    border-collapse:collapse;
    background:white;

}

th{

    background:#0d6efd;
    color:white;
    padding:12px;

}

td{

    padding:10px;
    border-bottom:1px solid #ddd;

}

tr:hover{

    background:#f1f1f1;

}

a{

    text-decoration:none;

}

.boton{

    background:#198754;
    color:white;
    padding:8px 15px;
    border-radius:5px;

}

</style>

</head>

<body>

<h1>Historial de Compras</h1>

<table>

<tr>

<th>ID Venta</th>

<th>Fecha</th>

<th>Total</th>

<th>Detalle</th>

</tr>

<%

List<Venta> lista=(List<Venta>)request.getAttribute("listaVentas");

if(lista!=null){

for(Venta venta:lista){

%>

<tr>

<td><%=venta.getIdVenta()%></td>

<td><%=venta.getFecha()%></td>

<td>$ <%=venta.getTotal()%></td>

<td>

<a class="boton" href="#">

Ver detalle

</a>

</td>

</tr>

<%

}

}

%>

</table>

<br><br>

<a href="index.jsp">← Volver a la tienda</a>

</body>

</html>