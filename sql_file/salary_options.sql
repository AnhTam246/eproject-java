-- phpMyAdmin SQL Dump
-- version 4.9.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Mar 22, 2021 at 08:27 PM
-- Server version: 5.7.26
-- PHP Version: 7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `hr`
--

-- --------------------------------------------------------

--
-- Table structure for table `salary_options`
--

CREATE TABLE `salary_options` (
  `id` int(11) NOT NULL,
  `type` varchar(50) NOT NULL,
  `have_tax` tinyint(4) NOT NULL DEFAULT '0',
  `key` varchar(225) NOT NULL,
  `name` varchar(225) NOT NULL,
  `unit` varchar(20) NOT NULL,
  `value` double NOT NULL DEFAULT '0',
  `min_tax` double NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `salary_options`
--

INSERT INTO `salary_options` (`id`, `type`, `have_tax`, `key`, `name`, `unit`, `value`, `min_tax`) VALUES
(1, 'ALLOWANCE', 0, 'EAT', 'Tiền cơm', 'NUMBER', 30000, 730000),
(2, 'ALLOWANCE', 0, 'PHONE', 'Điện thoại', 'NUMBER', 0, 0),
(3, 'ALLOWANCE', 1, 'OIL', 'Xăng', 'NUMBER', 0, 0),
(4, 'INSURANCE', 0, 'BHXH', 'Bảo hiểm xã hội', 'PERCENT', 0.08, 0),
(5, 'INSURANCE', 0, 'BHYT', 'Bảo hiểm y tế', 'PERCENT', 0.015, 0),
(6, 'INSURANCE', 0, 'BHTN', 'Bảo hiểm thất nghiệp', 'PERCENT', 0.01, 0),
(7, 'INSURANCE', 0, 'UNION', 'Kinh phí công đoàn', 'PERCENT', 0.01, 0),
(8, 'DEDUCTION', 0, 'SALARY_ADVANCE', 'Tạm ứng lương', 'NUMBER', 0, 0),
(9, 'TAX', 0, 'TAX_YOURSELF', 'Giảm trừ gia cảnh bản thân', 'NUMBER', 11000000, 0),
(10, 'TAX', 0, 'TAX_FAMILY', 'Giảm trừ người phụ thuộc', 'NUMBER', 0, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `salary_options`
--
ALTER TABLE `salary_options`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `salary_options`
--
ALTER TABLE `salary_options`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
