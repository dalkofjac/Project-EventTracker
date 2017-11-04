<?php
header('charset=utf-8');
require "init.php";
$mysqli = new mysqli($server_name, $mysql_user, $mysql_pass, $db_name);

$regUsername = $_POST["regUsername"];

$sqlQuery1 = "SELECT * FROM users WHERE username = '$regUsername'";

$results1 = $mysqli->query($sqlQuery1);
$userData = $results1->fetch_row();

if(strcmp($userData[0], "") == 0){
    $regName = $_POST["regName"];
    $regSurname = $_POST["regSurname"];
    $regEmail = $_POST["regEmail"];
    $regPassword = $_POST["regPassword"];
    
    $sqlQuery2 = "INSERT INTO users (name, surname, email, username, password) VALUES ('$regName','$regSurname','$regEmail','$regUsername','$regPassword')";
    $results2 = $mysqli->query($sqlQuery2);
    echo "pass";
}
else{
    echo "stop";
}

$mysqli->close();
    
    

