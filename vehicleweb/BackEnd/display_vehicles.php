<?php
include 'db_connect.php';

$sql = "SELECT v.*, u.username AS seller_name,
        (SELECT MAX(bid_amount) FROM bids b WHERE b.vehicle_id = v.vehicle_id) AS highest_bid
        FROM vehicles v 
        JOIN users u ON v.seller_id = u.user_id 
        WHERE v.status = 'available' 
        ORDER BY auction_start DESC";

$result = $conn->query($sql);
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Available Vehicles</title>
    <link rel="stylesheet" href="Cstyle.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .card-img-top {
            height: 200px; /* Set a fixed height for images */
            object-fit: cover; /* Ensure images are cropped to fit the container */
        }
        .card {
            height: 100%; /* Ensure all cards have the same height */
        }
        .card-body {
            display: flex;
            flex-direction: column;
            justify-content: space-between;
        }
    </style>
</head>
<body>
    <header class="bg-primary text-white text-center py-4">
        <h2>Vehicles Available for Auction</h2>
    </header>

    <main class="container mt-4">
        <div class="row">
            <?php while($row = $result->fetch_assoc()): ?>
                <div class="col-md-4 mb-4">
                    <div class="card shadow-sm">
                        <?php if (!empty($row['image_url'])): ?>
                            <img src="data:image/jpeg;base64,<?= base64_encode($row['image_url']) ?>" class="card-img-top" alt="Vehicle Image">
                        <?php else: ?>
                            <img src="data:image/jpeg;base64,<?= base64_encode($row['image']) ?>" class="card-img-top" alt="Vehicle Image">
                        <?php endif; ?>
                        <div class="card-body">
                            <h5 class="card-title"><?= htmlspecialchars($row['vehicle_name']) ?></h5>
                            <p class="card-text">
                                <strong>Brand:</strong> <?= $row['brand'] ?><br>
                                <strong>Model:</strong> <?= $row['model'] ?><br>
                                <strong>Year:</strong> <?= $row['year'] ?><br>
                                <strong>Fuel:</strong> <?= $row['fuel_type'] ?><br>
                                <strong>Transmission:</strong> <?= $row['transmission'] ?><br>
                                <strong>Price:</strong> ₹<?= number_format($row['starting_price'], 2) ?><br>
                                <?php if ($row['highest_bid'] !== null): ?>
                                    <strong>Current Bid:</strong> ₹<?= number_format($row['highest_bid'], 2) ?><br>
                                <?php else: ?>
                                    <strong>Current Bid:</strong> <span class="text-muted">No bids yet</span><br>
                                <?php endif; ?>

                                <strong>Auction:</strong><br>
                                <span class="text-success">Starts: <?= $row['auction_start'] ?></span><br>
                                <span class="text-danger">Ends: <?= $row['auction_end'] ?></span><br>
                            </p>
                            <div class="d-flex justify-content-between align-items-center">
                                <span><strong>Seller:</strong> <?= $row['seller_name'] ?></span>
                                <a href="place_bid.php?vehicle_id=<?= $row['vehicle_id'] ?>" class="btn btn-sm btn-outline-primary ms-2">Place a Bid</a>
                            </div>
                        </div>
                    </div>
                </div>
            <?php endwhile; ?>
        </div>
    </main>
</body>
</html>
