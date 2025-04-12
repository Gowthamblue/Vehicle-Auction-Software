<?php
include 'db_connect.php';
session_start();

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $vehicle_name = $_POST['vehicle_name'];
    $brand = $_POST['brand'];
    $model = $_POST['model'];
    $year = $_POST['year'];
    $fuel_type = $_POST['fuel_type'];
    $transmission = $_POST['transmission'];
    $description = $_POST['description'];
    $starting_price = $_POST['starting_price'];
    $auction_start = $_POST['auction_start'];
    $auction_end = $_POST['auction_end'];

    // Get seller_id from session or assign a dummy one for testing
    $seller_id = $_SESSION['user_id'] ?? 1;

    // Handle image upload
    if (isset($_FILES['image']) && $_FILES['image']['error'] == 0) {
        $image = file_get_contents($_FILES['image']['tmp_name']);
    } else {
        die("Image upload failed.");
    }

    $stmt = $conn->prepare("INSERT INTO vehicles (
        seller_id, vehicle_name, brand, model, year, fuel_type,
        transmission, description, image, starting_price,
        auction_start, auction_end
    ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

    $stmt->bind_param(
        "isssissssdss",
        $seller_id,
        $vehicle_name,
        $brand,
        $model,
        $year,
        $fuel_type,
        $transmission,
        $description,
        $image,
        $starting_price,
        $auction_start,
        $auction_end
    );

    if ($stmt->execute()) {
        echo "<script>alert('Vehicle added successfully!'); window.location.href='add_vehicle.html';</script>";
    } else {
        echo "❌ Error: " . $stmt->error;
    }

    $stmt->close();
    $conn->close();
}
?>
