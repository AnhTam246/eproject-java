-- phpMyAdmin SQL Dump
-- version 4.9.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Apr 02, 2021 at 10:05 AM
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
(2, 1, '2021-01-01', '2021-01-01', '2021-12-31', 10000000, '2021-03-19 15:37:23'),
(3, 5, '2021-01-01', '2021-01-01', '2021-12-31', 15000000, '2021-03-21 09:24:59'),
(4, 4, '2021-01-01', '2021-12-31', '2021-12-31', 10000000, '2021-03-19 15:37:23'),
(5, 5, '2021-01-01', '2021-12-31', '2021-12-31', 10000000, '2021-03-19 15:37:23'),
(6, 6, '2021-01-01', '2021-12-31', '2021-12-31', 10000000, '2021-03-19 15:37:23'),
(7, 8, '2021-01-01', '2021-12-31', '2021-12-31', 10000000, '2021-03-19 15:37:23'),
(8, 9, '2021-01-01', '2021-12-31', '2021-12-31', 10000000, '2021-03-19 15:37:23'),
(9, 10, '2021-01-01', '2021-12-31', '2021-12-31', 10000000, '2021-03-19 15:37:23'),
(10, 11, '2021-01-01', '2021-12-31', '2021-12-31', 10000000, '2021-03-19 15:37:23'),
(11, 35, '2021-01-01', '2021-12-31', '2021-12-31', 10000000, '2021-03-19 15:37:23');

-- --------------------------------------------------------

--
-- Table structure for table `salary`
--

CREATE TABLE `salary` (
  `id` int(11) NOT NULL,
  `from_date` date DEFAULT NULL,
  `to_date` date DEFAULT NULL,
  `status` varchar(50) COLLATE utf8_unicode_ci DEFAULT 'pending'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `salary_detail`
--

CREATE TABLE `salary_detail` (
  `id` int(11) NOT NULL,
  `salary_id` int(11) NOT NULL,
  `details` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `staff_id` int(11) NOT NULL COMMENT 'FK table staff',
  `salary` double NOT NULL,
  `base_salary_contract` double NOT NULL DEFAULT '0',
  `total_day_work` float NOT NULL DEFAULT '0',
  `total_special_day` float NOT NULL DEFAULT '0',
  `salary_ot` double NOT NULL DEFAULT '0',
  `total_allowance` double NOT NULL DEFAULT '0',
  `allowance_details` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin,
  `total_insurance` double NOT NULL DEFAULT '0',
  `insurance_details` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin,
  `total_tax` double NOT NULL DEFAULT '0',
  `tax_details` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin,
  `income_tax` double NOT NULL DEFAULT '0' COMMENT 'Thu nhập chịu thuế',
  `taxable_income` double NOT NULL DEFAULT '0' COMMENT 'Thu nhập tính thuế',
  `personal_tax` double NOT NULL DEFAULT '0' COMMENT 'Thuế TNCC',
  `salary_actually_received` double NOT NULL DEFAULT '0' COMMENT 'Lương thực nhận',
  `kpi_reward` double DEFAULT '0',
  `other_reward` double DEFAULT '0',
  `create_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

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
(1, 'ALLOWANCE', 0, 'EAT', 'Tiền cơm', 'NUMBER', 660000, 730000),
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
-- Indexes for table `contract`
--
ALTER TABLE `contract`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `salary`
--
ALTER TABLE `salary`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `salary_detail`
--
ALTER TABLE `salary_detail`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `salary_options`
--
ALTER TABLE `salary_options`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `contract`
--
ALTER TABLE `contract`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `salary`
--
ALTER TABLE `salary`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `salary_detail`
--
ALTER TABLE `salary_detail`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `salary_options`
--
ALTER TABLE `salary_options`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
