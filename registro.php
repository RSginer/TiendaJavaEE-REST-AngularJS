<?php $pagina = "registro"; ?>
<html>
    <head>
        <?php include('includes/meta.php'); ?>
    </head>
    <body>
        <?php include('includes/menu.php'); ?>
        <!-- FORMULARIO -->
        <br>
        <div class="row container">

            <form class="col s12 formulario" id="f" action="servidor/registrarse.php" method="POST">
                <h2 class=" header center text-teal text-lighten-1">Formulario de registro</h2>
                <div class="row">
                    <div class="input-field col s6">
                        <input id="nombre" required="required" name="nombre" type="text" class="validate">
                        <label for="nombre" name="nombre">Nombre</label>
                    </div>
                    <div class="input-field col s6">
                        <input id="apellidos" required="required" name="apellidos" type="text" class="validate">
                        <label for="apellidos">Apellidos</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s2">
                        <input  id="pais" required="required" name="pais" type="text" class="validate">
                        <label for="pais">Pais</label>
                    </div>
                    <div class="input-field col s2">
                        <input  id="ciudad" required="required" name="ciudad" type="text" class="validate">
                        <label for="ciudad">Ciudad</label>
                    </div>
                    <div class="input-field col s2">
                        <input  id="cp" required="required" name="cp" type="text" class="validate">
                        <label for="cp">CP</label>
                    </div>
                    <div class="input-field col s6">
                        <input  id="direccion" required="required" name="direccion" type="text" class="validate">
                        <label for="direccion">Dirección</label>
                    </div>

                </div>
                <div class="row">
                    <div class="input-field col s12">
                        <input id="email" name="email" required="required" type="email" class="validate">
                        <label for="email" data-error="Email invalido" data-success="Correcto">Email</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12">
                        <input id="password" name="password" required="required" type="password" class="validate">
                        <label for="password" data-error="Las contraseñas no coinciden">Contraseña</label>
                    </div>
                </div>
                <?php
                if (isset($_GET['error'])) {
                    echo ' <div class="row">
                    <p style="color:red;">Ese email ya esta registrado</p>
                </div>';
                }
                ?>
                <br>
                <div class="row">
                    <div class="input-field col s6 center">
                        <button class="btn waves-effect waves-light " type="reset"  name="action">Reiniciar
                            <i class="material-icons right">replay</i>

                        </button>
                    </div>
                    <div class="input-field col s6 center">
                        <button class="btn waves-effect waves-light " type="submit" name="action">Enviar
                            <i class="material-icons left">send</i>
                        </button>
                    </div>
                </div>
            </form>
        </div>

        <!-- FIN FORMULARIO -->
<?php include ('includes/footer.php'); ?>
        <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
        <script type="text/javascript" src="js/materialize.min.js"></script>
    </body>
</html>

