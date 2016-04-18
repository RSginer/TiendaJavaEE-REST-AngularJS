<?php
// datos para la coneccion a mysql
define('DB_SERVER','127.0.0.1');
define('DB_NAME','fpskills');
define('DB_USER','root');
define('DB_PASS','root');
$con = mysql_connect(DB_SERVER,DB_USER,DB_PASS);
mysql_select_db(DB_NAME,$con);
mb_internal_encoding("UTF-8");
session_start();
?>