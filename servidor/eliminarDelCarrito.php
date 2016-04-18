<?php

session_start();
$articulo = $_GET['articulo'];
$array = $_SESSION['carrito'];
$encontrado = false;

if (count($_SESSION['carrito']) == 1) {
    unset($_SESSION['carrito']);
    header('Location: ../carrito.php');
} else {
    for ($i = 0; $i <= count($array) && $encontrado == false; $i++) {
        if ($array[$i] == $articulo) {
            $item = $i;
            $encontrado = true;
        }
    }
    unset($array[$item]);
    $_SESSION['carrito'] = $array;
    header('Location: ../carrito.php');
}