<?php $pagina = "carrito"; ?>
<html>
    <head>
        <?php include('includes/meta.php'); ?>
    </head>
    <body>
        <?php include('includes/menu.php'); ?>
        <div class="row container">
            <!-- CARRITO -->
            <br>
            <?php
            $total = 0;
            if (isset($_SESSION['carrito'])) {
                foreach ($_SESSION['carrito'] as $producto) {
                    $consulta = "SELECT * FROM productos WHERE id='$producto'";
                    $resultados = mysql_query($consulta);
                    $producto = mysql_fetch_object($resultados);
                    ?>

                    <ul style="height: 65px;"class="collection">
                        <li  class="collection-item avatar">
                            <img src="<?php echo $producto->img; ?>" alt="" class="circle">
                            <span class="title"><?php echo $producto->titulo; ?></span>
                            <span class="right" style="font-size: 18px;margin-right: 65px;margin-top: 5px;"><?= $producto->precio; ?> €</span>
                            <a href="servidor/eliminarDelCarrito.php?articulo=<?= $producto->id ?>" class="secondary-content"><i class="material-icons">delete</i></a>
                        </li>
                    </ul>
                    <?php
                    $total+=$producto->precio;
                }
                ?>
             <div class="row">
                <a class="waves-effect waves-red btn-flat left" href="servidor/vaciarCarrito.php" type="submit" name="action">Vaciar carrito
                <i class="material-icons right">undo</i>
            </a>
            </div>
            <?php } else { ?>
                <ul class="collection">
                    <li class="collection-item">
                        <span class="title">No has añadido productos al carrito</span>
                    </li>
                </ul>
            <?php } ?>
            <div class="row formulario">
                <p style="font-size: 18px">Total:<span style="margin-right: 60px;" class="right text-lighten-2"><?= $total ?> €</span></p>
            </div>
            <div class="row">
                <?php
                if (isset($_SESSION['carrito'])) {
                    ?> <a class="btn waves-effect waves-light right" href="tramitarPedido.php" type="submit" name="action">Continuar
                        <i class="material-icons right">send</i>
                    </a>
                <?php }
                ?>

            </div>
        </div>
        <!-- FIN CARRITO -->
        <?php include ('includes/footer.php'); ?>
        <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
        <script type="text/javascript" src="js/materialize.min.js"></script>
    </body>
</html>
