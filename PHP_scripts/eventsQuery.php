<?php
$queryId = $_POST["queryId"];

header('charset=utf-8');

require "init.php";

$mysqli = new mysqli($server_name, $mysql_user, $mysql_pass, $db_name);

$response = array();

if($queryId == "1"){
    // queryId - 1 -> Requesting all events from one user (userId)
    
    $userId = $_POST["userId"];
    
    $sqlQuery = "SELECT id, type, name, date FROM events WHERE user = '$userId'";
    $results = $mysqli->query($sqlQuery);
    while ($row = mysqli_fetch_array($results)) {
		array_push($response, array("id"=>$row[0],"type"=>$row[1], "name"=>$row[2],"date"=>$row[3]));
    }
    
    echo json_encode(array("event"=>$response));
    
} else if($queryId == "2"){
    // queryId - 2 -> Deleting one event from one user (userId, eventId)
    
    $userId = $_POST["userId"];
    $eventId = $_POST["eventId"];
    
    $sqlQuery = "DELETE FROM events WHERE id = '$eventId' AND user = '$userId'";
    $results = $mysqli->query($sqlQuery);
    
} else if($queryId == "3"){
    // queryId - 3-> Adding new event to one user (given all attributes)
    
    $userId = $_POST["userId"];
    $eventId = $_POST["eventId"];
    $eventType = $_POST["eventType"];
    $eventName = $_POST["eventName"];
    $eventDate = $_POST["eventDate"];
    
    $sqlQuery = "INSERT INTO events (id, type, name, date, user) VALUES ('$eventId','$eventType','$eventName','$eventDate','$userId')";
    $results = $mysqli->query($sqlQuery);
    
} else{
    echo "Unexpected entry!";
}

$mysqli->close();

