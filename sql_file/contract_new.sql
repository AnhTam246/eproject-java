-- phpMyAdmin SQL Dump
-- version 4.9.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Apr 19, 2021 at 12:53 PM
-- Server version: 5.7.26
-- PHP Version: 7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `hr`
--

-- --------------------------------------------------------

--
-- Table structure for table `contract`
--

CREATE TABLE `contract` (
  `id` int(11) NOT NULL,
  `staff_id` int(11) NOT NULL COMMENT 'FK table staff',
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `stop_date` date NOT NULL,
  `base_salary` double NOT NULL DEFAULT '0',
  `create_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `contract`
--

INSERT INTO `contract` (`id`, `staff_id`, `start_date`, `end_date`, `stop_date`, `base_salary`, `create_at`) VALUES
(2, 1, '2021-01-01', '2021-12-31', '2021-12-31', 10000000, '2021-03-19 15:37:23'),
(4, 4, '2021-01-01', '2021-12-31', '2021-12-31', 10000000, '2021-03-19 15:37:23'),
(5, 5, '2021-01-01', '2021-12-31', '2021-12-31', 35000000, '2021-03-19 15:37:23'),
(6, 6, '2021-01-01', '2021-12-31', '2021-12-31', 10000000, '2021-03-19 15:37:23'),
(7, 8, '2021-01-01', '2021-12-31', '2021-12-31', 10000000, '2021-03-19 15:37:23'),
(8, 9, '2021-01-01', '2021-12-31', '2021-12-31', 10000000, '2021-03-19 15:37:23'),
(9, 10, '2021-01-01', '2021-12-31', '2021-12-31', 10000000, '2021-03-19 15:37:23'),
(10, 11, '2021-01-01', '2021-12-31', '2021-12-31', 10000000, '2021-03-19 15:37:23'),
(11, 35, '2021-01-01', '2021-12-31', '2021-12-31', 10000000, '2021-03-19 15:37:23');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `contract`
--
ALTER TABLE `contract`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `contract`
--
ALTER TABLE `contract`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
