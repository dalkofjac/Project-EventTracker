<?php
header('charset=utf-8');
require "init.php";
$mysqli = new mysqli($server_name, $mysql_user, $mysql_pass, $db_name);
$response = array();

$regName = $_POST["regName"];
$regSurname = $_POST["regSurname"];
$regEmail = $_POST["regEmail"];
$regUsername = $_POST["regUsername"];
$regPassword = $_POST["regPassword"];

$sqlQuery = "INSERT INTO users (name, surname, email, username, password) VALUES ('$regName','$regSurname','$regEmail','$regUsername','$regPassword')";
$results = $mysqli->query($sqlQuery);

$mysqli->close();
    
    

