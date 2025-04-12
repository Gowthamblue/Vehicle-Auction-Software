<?php
include 'db_connect.php';

$username = $_POST['username'];
$email = $_POST['email'];
$password = password_hash($_POST['password'], PASSWORD_BCRYPT);
$phone = $_POST['phone'];
$address = $_POST['address'];
$role = $_POST['role'];

// Check for duplicate username
$check = "SELECT * FROM users WHERE username = '$username'";
$result = mysqli_query($conn, $check);

if (mysqli_num_rows($result) > 0) {
    echo "<script>alert('Username already exists! Please choose another.'); window.location.href='register.html';</script>";
    exit();
}

// Insert into database
$sql = "INSERT INTO users (username, password, email, phone, address, role) 
        VALUES ('$username', '$password', '$email', '$phone', '$address', '$role')";

if (mysqli_query($conn, $sql)) {
    echo "<script>alert('Registration successful!'); window.location.href='login.html';</script>";
} else {
    echo "❌ Error: " . mysqli_error($conn);
}
?>
