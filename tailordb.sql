-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 29, 2021 at 04:46 PM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tailordb`
--

-- --------------------------------------------------------

--
-- Table structure for table `customerdb`
--

CREATE TABLE `customerdb` (
  `mobno` varchar(10) NOT NULL,
  `cname` varchar(100) NOT NULL,
  `dress` varchar(100) NOT NULL,
  `splist` varchar(100) NOT NULL,
  `mmnts` varchar(100) NOT NULL,
  `days` int(11) NOT NULL,
  `date` date NOT NULL,
  `price` varchar(100) NOT NULL,
  `wish` varchar(100) NOT NULL,
  `oid` int(11) NOT NULL,
  `doac` date NOT NULL,
  `status` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customerdb`
--

INSERT INTO `customerdb` (`mobno`, `cname`, `dress`, `splist`, `mmnts`, `days`, `date`, `price`, `wish`, `oid`, `doac`, `status`) VALUES
('234211', 'sahiol', 'Shirt', 'Aman', '4385493', 3, '2021-03-27', '343', 'dsfas', 1, '2021-03-25', 0),
('9874563210', 'Sahil Mittal', 'Pant', 'Aman', '2-453-2-445-', 10, '2021-04-07', '3000', 'slimfit', 2, '2021-03-29', 1),
('7894561236', 'Rahul', 'Suit', 'Chaman', '42-69-69', 18, '2021-04-15', '2000', 'nothing', 3, '2021-03-29', 1),
('7894561200', 'Jayant', 'Shirt', 'Raman', '42-69-69', 20, '2021-04-17', '2500', 'slim fit', 4, '2021-03-29', 1),
('7894561200', 'Eshaan', 'Kurta Pyjama', 'Raman', '42-69-69', 20, '2021-04-17', '2600', 'slim fit', 5, '2021-03-29', 1);

-- --------------------------------------------------------

--
-- Table structure for table `workersdb`
--

CREATE TABLE `workersdb` (
  `wname` varchar(30) NOT NULL,
  `mobile` varchar(11) NOT NULL,
  `picpath` varchar(1000) NOT NULL,
  `address` varchar(2000) NOT NULL,
  `spl` varchar(1000) NOT NULL,
  `dor` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `workersdb`
--

INSERT INTO `workersdb` (`wname`, `mobile`, `picpath`, `address`, `spl`, `dor`) VALUES
('Aman', '9876543210', 'E:\\business-people-and-employee-graphic-design-vector-illustration-MA9B97.jpg', 'Raj Colony', 'Shirt, Pant, ', '2021-03-24'),
('Raman', '7894561230', 'E:\\1001354452.jpg', 'Ajit Road', 'Shirt, Pant, Kurta Pyjama, ', '2021-03-24'),
('Chaman', '6549873210', 'E:\\1001378641.jpg', 'Arvind Nagar', 'Suit, ', '2021-03-24');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customerdb`
--
ALTER TABLE `customerdb`
  ADD PRIMARY KEY (`oid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customerdb`
--
ALTER TABLE `customerdb`
  MODIFY `oid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
