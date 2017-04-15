<?php
$userId = $_POST["userId"];

header('charset=utf-8');

require "init.php";

$response = array();

$sqlQuery = "SELECT id, name, surname, email FROM users WHERE id = '$userId'";

$results = $mysqli->query($sqlQuery);

while ($row = mysqli_fetch_array($results)) {
		array_push($response, array("id"=>$row[0],"name"=>$row[1], "surname"=>$row[2],"email"=>$row[3]));
	}
echo json_encode(array("user"=>$response));
