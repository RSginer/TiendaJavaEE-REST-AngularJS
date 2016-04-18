 <?php
include 'conexion.php';
$email = ($_POST["email"]);
$pass = ($_POST["password"]);
$password = sha1($pass);
$url = urldecode($_GET['url']);
$consultaEmail = "SELECT * FROM usuarios WHERE email='$email' AND pass='$password'";
$emailV = mysql_query($consultaEmail);
$obj = mysql_fetch_object($emailV);
$rows = mysql_num_rows($emailV);
if ($rows==1) {
    $_SESSION['userid'] = $obj->id;
    header('Location: ' . $url . '');
}else{
    header('Location: ../login.php?error=1');
}
?> 
