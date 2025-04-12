<?php
session_start();
include 'db_connect.php';

$vehicle_id = $_GET['vehicle_id'];
$user_id = $_SESSION['user_id'] ?? null;

if (!$user_id) {
    die("Please login to place a bid.");
}

// Fetch current highest bid and vehicle details
$query = "SELECT v.*, 
            (SELECT MAX(bid_amount) FROM bids WHERE vehicle_id = v.vehicle_id) AS highest_bid 
          FROM vehicles v 
          WHERE v.vehicle_id = ?";
$stmt = $conn->prepare($query);
$stmt->bind_param("i", $vehicle_id);
$stmt->execute();
$vehicle = $stmt->get_result()->fetch_assoc();

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $bid_amount = $_POST['bid_amount'];

    if ($bid_amount <= $vehicle['starting_price'] || ($vehicle['highest_bid'] && $bid_amount <= $vehicle['highest_bid'])) {
        echo "<p class='text-danger'>Your bid must be higher than the current price or highest bid.</p>";
    } else {
        $insert = $conn->prepare("INSERT INTO bids (vehicle_id, user_id, bid_amount) VALUES (?, ?, ?)");
        $insert->bind_param("iid", $vehicle_id, $user_id, $bid_amount);
        $insert->execute();
        echo "<p class='text-success'>Bid placed successfully!</p>";
    }
}
?>

<!DOCTYPE html>
<html>
<head>
    <title>Place Bid</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container my-5">
    <h2>Place Bid for <?= htmlspecialchars($vehicle['vehicle_name']) ?></h2>
    <p><strong>Current Highest Bid:</strong> ₹<?= $vehicle['highest_bid'] ?: $vehicle['starting_price'] ?></p>
    <form method="POST">
        <div class="mb-3">
            <label for="bid_amount" class="form-label">Your Bid (₹):</label>
            <input type="number" name="bid_amount" step="0.01" class="form-control" required>
        </div>
        <button class="btn btn-primary">Submit Bid</button>
    </form>
</body>
</html>
