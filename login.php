<?php $pagina = "login";?>
<html>
    <head>
        <?php include('includes/meta.php'); 
         if (isset($_SESSION['userid'])) {
     header('Location: index.php');
}?>
    </head>
    <body>
        <?php include('includes/menu.php');
        $url=  urldecode($_GET['url']);
        ?>
        <!-- FORMULARIO -->
        <br>
        <div class="row container">
            <form action="servidor/iniciarSesion.php?url=<?=  urlencode($url)?>" method="post" class="col s12 m12 formulario" style="">
                <?php
                if (isset($_GET['registro'])) {
                    echo'<p style="color:green;">¡Registro completado! Inicia sesión para comenzar a usar tu cuenta</p>'
                ;}
                ?>
                <div class="row" style="text-align: center;">
                    <img src="https://www.accrinet.com/images/3030_orig.png">
                </div>
                <div class="row">
                    
                    <div class="input-field col s12" >
                        <input id="email" type="email"  name="email" class="validate">
                        <label for="email" data-error="Email invalido">Email</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12">
                        <input id="password" type="password" name="password" class="validate">
                        <label for="password">Contraseña</label>
                    </div>
                </div>
                <?php
                if (isset($_GET['error'])) {
                echo '<p style="color:red;">
                Email o contraseña incorrecta, vuelve a intentarlo
                </p>';
                
                 }?>
                <div class="row">
                    <div class="input-field col s12 center">
                        <button class="btn waves-effect waves-light " type="submit"  name="action">Entrar
                            <i class="material-icons right">send</i>
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

