<ul id="dropdown1" class="dropdown-content">
    <?php 
        if ($_SESSION['userid']==6) {?>
             <li><a href="#">Administrar tienda</a></li>
       <?php }
    ?>
    <li><a href="#">Mis Pedidos</a></li>
    <li><a href="#">Ajustes</a></li>
    <li class="divider"></li>
    <li><a href="servidor/cerrarSesion.php">Salir</a></li>
</ul>
<nav class="blue">
    <div class="nav-wrapper container">
        <a href="index.php" class="brand-logo">Tienda FP Skills</a>
        <ul class="right hide-on-med-and-down">
            <!-- Dropdown Trigger -->
            <?php
            
            if (isset($_SESSION["userid"])) {
                $usuario = $_SESSION['userid'];?>
                <li><a class="dropdown-button" href="#!" data-activates="dropdown1"><i class="material-icons left">person_pin</i>
             <?php $nombre =  mysql_query("SELECT * FROM usuarios WHERE id='$usuario'");
              $objeto = mysql_fetch_object($nombre);
              echo utf8_encode($objeto->nombre);
              echo '<i class="material-icons right">arrow_drop_down</i></a></li>';
            } else {
              echo '<li><a href="login.php" class="waves-effect waves-light btn botonmenu">Iniciar sesión</a></li>
              <li><a href="registro.php" class="waves-effect waves-light btn botonmenu">Crear cuenta</a></li>';
            }
            ?>
            <li><a href="#"><i class="material-icons left">shopping_cart</i><span id="carrito" style="background-color: white; border-radius: 50%; color: rgb(33, 150, 243); padding: 0px 5px;">0</span></a></li>
        </ul>
    </div>
</nav>
<nav class="light-blue">
    <div class="nav-wrapper container">
        <div class="col s12">
            <a href="index.php" class="breadcrumb">Inicio</a>
            <?php
            if ($pagina == "login") {
                echo' <a href="login.php" class="breadcrumb">Iniciar sesión</a>';
            } else if ($pagina == "registro") {
                echo' <a href="registro.php.php" class="breadcrumb">Registrarse</a>';
            }
            ?>
        </div>
    </div>
</nav>  

