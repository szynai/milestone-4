DROP DATABASE IF EXISTS hoa_db;
CREATE DATABASE IF NOT EXISTS hoa_db;
USE hoa_db;

CREATE TABLE IF NOT EXISTS residents (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    address VARCHAR(150),
    contact VARCHAR(50),
    email VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS visitors (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    resident_to_visit VARCHAR(100),
    purpose VARCHAR(150),
    date VARCHAR(50),
    time_in VARCHAR(50),
    status VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS events (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    contact VARCHAR(50),
    event_name VARCHAR(100),
    event_date VARCHAR(50),
    status VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS payments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    reservation_type VARCHAR(50),
    reservation_id INT,
    amount DOUBLE,
    vat_amount DOUBLE,
    discount_amount DOUBLE,
    total_amount DOUBLE,
    payment_method VARCHAR(50),
    status VARCHAR(50),
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
