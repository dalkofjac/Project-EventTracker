<?php
 $username = $_POST["username"];
 $password = $_POST["password"];
 
 header('charset=utf-8');
 
 require "init.php";
 
 $sqlQuery = "SELECT * FROM users WHERE username = '$username' AND password = '$password'";
    
 $results = $mysqli->query($sqlQuery);
 $userData = $results->fetch_row();

 echo "$userData[1]";
