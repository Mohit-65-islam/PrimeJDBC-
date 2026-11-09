-- Run in phpMyAdmin (SQL tab) or the mysql client
CREATE DATABASE IF NOT EXISTS lab_db;
USE lab_db;

-- Rename the table if you want to follow the assignment exactly,
-- e.g. nickname_name_prime -> mohitul_islam_prime
CREATE TABLE IF NOT EXISTS prime_number (
    id        INT AUTO_INCREMENT PRIMARY KEY,
    n         INT NOT NULL,
    nth_prime INT NOT NULL
);

-- Verify after running PrimeJDBC
SELECT * FROM prime_number;
