<?php

include 'conexion.php';

$hoy = date("Y-m-d");
$nombre = $_POST['nombre'];
$apellidos = $_POST['apellidos'];
$pais = $_POST['pais'];
$ciudad = $_POST['ciudad'];
$cp = $_POST['cp'];
$direccion = $_POST['direccion'];
$email = $_POST['email'];
$telefono = $_POST['telefono'];
$direccionEnvio = "$pais, $ciudad, $cp, $direccion";
print $direccionEnvio;
if (isset($_SESSION['userid'])) {
    $consultapedido = "INSERT INTO pedidos VALUES (''," . $_SESSION['userid'] . ",'',,'$hoy','$direccionEnvio','$email','$telefono')";
    $idpedido = mysql_query("SELECT id FROM pedidos WHERE idcliente=" . $_SESSION['userid'] . " ORDER BY id DESC");
    mysql_query($consultapedido);
} else {
    $consultapedido = "INSERT INTO pedidos VALUES ('',999,'','$hoy','$direccionEnvio','$email','$telefono')";
    $idpedido = mysql_query("SELECT id FROM pedidos WHERE idcliente='999'");
    mysql_query($consultapedido);
}
foreach ($_SESSION['carrito'] as $producto) {
    echo $producto;
    $precio = mysql_query("SELECT precio FROM productos WHERE id='$producto'");
    $consultaDetallePedidos = "INSER INTO detallePedidos VALUES('$idpedido','$hoy','$producto','$precio')";
    mysql_query($consultaDetallePedidos);
    
}