<?php
session_start();
include 'db_connect.php';

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $username = $_POST['username'];
    $password = $_POST['password'];

    // Fetch user by username
    $sql = "SELECT * FROM users WHERE username = ?";
    $stmt = mysqli_prepare($conn, $sql);
    mysqli_stmt_bind_param($stmt, "s", $username);
    mysqli_stmt_execute($stmt);
    $result = mysqli_stmt_get_result($stmt);

    if ($row = mysqli_fetch_assoc($result)) {
        // Verify password
        if (password_verify($password, $row['password'])) {
            // Login success
            $_SESSION['user_id'] = $row['user_id'];
            $_SESSION['username'] = $row['username'];
            $_SESSION['role'] = $row['role'];

            // Redirect based on role
            if ($row['role'] == 'buyer') {
                header("Location: buyer_dashboard.php");
            } elseif ($row['role'] == 'seller') {
                header("Location: admin.html");
            } else {
                echo "<script>alert('Invalid role!'); window.location.href='login.html';</script>";
            }
        } else {
            // Invalid password
            echo "<script>alert('Incorrect password!'); window.location.href='login.html';</script>";
        }
    } else {
        // Username not found
        echo "<script>alert('User not found!'); window.location.href='login.html';</script>";
    }
}
?>
