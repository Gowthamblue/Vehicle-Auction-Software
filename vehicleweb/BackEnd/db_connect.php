<?php
$conn = new mysqli("localhost", "root", "", "web_vehicle_auction");
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
?>
