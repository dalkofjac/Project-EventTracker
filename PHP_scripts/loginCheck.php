<?php
 $username = $_POST["username"];
 $password = $_POST["password"];
 
 header('charset=utf-8');
 
 require "init.php";

 $mysqli = new mysqli($server_name, $mysql_user, $mysql_pass, $db_name);

 //SQL injection protection
 $username = stripslashes($username);
 $password = stripslashes($password);
 $username = mysqli_real_escape_string($mysqli, $username);
 $password = mysqli_real_escape_string($mysqli, $password);
 
 $sqlQuery = "SELECT * FROM users WHERE username = '$username' AND password = '$password'";
    
 $results = $mysqli->query($sqlQuery);
 $userData = $results->fetch_row();

 echo "$userData[0]";

 $mysqli->close();
