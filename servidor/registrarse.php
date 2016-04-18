<?php
include'conexion.php';
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$nombre = $_POST['nombre'];
$apellidos = $_POST['apellidos'];
$pais = $_POST['pais'];
$ciudad = $_POST['ciudad'];
$cp = $_POST['cp'];
$direccion = $_POST['direccion'];
$email = $_POST['email'];
$pass = $_POST['password'];
$contra = sha1($pass);
$consulta = "SELECT * FROM usuarios WHERE email = '$email'";
$rows = mysql_num_rows(mysql_query($consulta));
if ($rows==0) {
    mysql_query("INSERT INTO usuarios (nombre,apellidos,pais,ciudad,cp,direccion,email,pass)
    VALUES('$nombre','$apellidos','$pais','$ciudad','$cp','$direccion','$email','$contra')");
    header('Location: ../login.php?registro=1');
}else{
    header('Location: ../registro.php?error=1');
}