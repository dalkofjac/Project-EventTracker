<?php
$queryId = $_POST["queryId"];
$userId = $_POST["userId"];

header('charset=utf-8');

require "init.php";

$mysqli = new mysqli($server_name, $mysql_user, $mysql_pass, $db_name);

$response = array();

if($queryId == "1"){
    $sqlQuery = "SELECT id, type, name, date FROM events WHERE user = '$userId'";
}
else{
    $sqlQuery = null;
}

$results = $mysqli->query($sqlQuery);

while ($row = mysqli_fetch_array($results)) {
		array_push($response, array("id"=>$row[0],"type"=>$row[1], "name"=>$row[2],"date"=>$row[3]));
	}
echo json_encode(array("event"=>$response));

$mysqli->close();

