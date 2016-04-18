<?php
session_start();
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$item = $_GET['articulo'];
if (isset($_SESSION['carrito'])) {
    $_SESSION['carrito'];
    array_push($_SESSION['carrito'], $item);
    header('Location: ../index.php');
    
}else{
    $_SESSION['carrito']=[$item];
     header('Location: ../index.php');
}
