<?php $pagina = "inicio" ?>
<html>
    <head>
        <?php include('includes/meta.php'); ?>
    </head>
    <body>
        <?php include('includes/menu.php'); ?>
        <!-- IMAGEN PARALLAX -->
        <div id="index-banner" class="parallax-container">
            <div class="section no-pad-bot">
                <div class="container">
                    <br>
                    <h1 class="header center teal-text text-darken-4">Bienvenido a mi tienda</h1>
                    <div class="row center">
                        <h5 class="header col s12 light">Esto es una tienda para practicar las FP Skills</h5>

                    </div>

                    <br>
                </div>
            </div>
            <div class="parallax"><img src="img/tienda.jpg" alt="Unsplashed background img 1"></div>
        </div>
        <!-- FIN IMAGEN -->
        <!-- PRODUCTOS -->
        <div class="container">
            <div class="row">
                <br>
                <?php
                $consulta = mysql_query("SELECT * FROM productos");
                while ($consulta_resultados = mysql_fetch_object($consulta)) {
                    ?><div class="col s12 m4">
                        <div class="card hoverable">
                            <div class="card-image">
                                <img  style="height: 200px;"src="<?php echo $consulta_resultados->img ?>">
                                <span class="card-title"><?php echo $consulta_resultados->titulo ?></span>
                            </div>
                            <div class="card-content">
                                <p><?= $consulta_resultados->descripcion ?></p>
                                <blockquote class="teal-text text-lighten-1" style="font-size: 30px;text-align: right;"><?php echo $consulta_resultados->precio ?>€</blockquote>
                            </div>
                            <div class="card-action">
                                <a href="#">Comprar</a>
                                <a href="servidor/añadirAlCarrito.php?articulo=<?= $consulta_resultados->id ?>">Añadir al carro</a>      
                            </div>
                        </div>
                    </div>
                <?php }
                ?>
            </div>
            <!--Import jQuery before materialize.js-->
        </div>
        <!-- FIN PRODUCTOS -->
        <?php include ('includes/footer.php'); ?>
        <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
        <script type="text/javascript" src="js/materialize.min.js"></script>
        <script>
            $(document).ready(function () {
                $('.parallax').parallax();
            });
        </script>
    </body>
</html>


