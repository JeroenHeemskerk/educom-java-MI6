-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Dec 14, 2022 at 03:50 PM
-- Server version: 10.4.25-MariaDB
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mi6`
--

-- --------------------------------------------------------

--
-- Table structure for table `agents`
--

CREATE TABLE `agents` (
  `id` int(10) NOT NULL,
  `agent_number` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `personal_sentence` varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL,
  `active` tinyint(1) NOT NULL,
  `licence_to_kill` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `agents`
--

INSERT INTO `agents` (`id`, `agent_number`, `personal_sentence`, `active`, `licence_to_kill`) VALUES
(1, '002', 'For The Royal QUEEN', 1, '2025-01-01'),
(2, '005', 'For The Royal QUEEN', 1, '2020-01-01'),
(3, '007', 'For The Royal QUEEN', 1, '2025-01-01'),
(4, '030', 'For The Royal QUEEN', 1, NULL),
(5, '102', 'For The Royal QUEEN', 1, NULL),
(6, '777', 'For The Royal QUEEN', 0, '2025-01-01');

-- --------------------------------------------------------

--
-- Table structure for table `login_attempts`
--

CREATE TABLE `login_attempts` (
  `id` int(10) NOT NULL,
  `agent_number` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `time_login` datetime(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  `login_success` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `login_attempts`
--

INSERT INTO `login_attempts` (`id`, `agent_number`, `time_login`, `login_success`) VALUES
(1, '001', '2022-12-14 12:16:41.903797', 0),
(2, '002', '2022-12-14 12:16:56.551737', 0),
(3, '002', '2022-12-14 12:17:12.473981', 0),
(4, '007', '2022-12-14 12:34:11.032848', 0),
(5, '007', '2022-12-14 12:34:32.058841', 0),
(6, '007', '2022-12-14 13:26:54.705921', 0),
(7, '002', '2022-12-14 13:36:57.802445', 0),
(8, '005', '2022-12-14 13:45:18.568079', 0),
(9, '002', '2022-12-14 13:53:41.552337', 0),
(10, '003', '2022-12-14 13:54:37.226673', 0),
(11, '003', '2022-12-14 13:54:43.094548', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `agents`
--
ALTER TABLE `agents`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `login_attempts`
--
ALTER TABLE `login_attempts`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `agents`
--
ALTER TABLE `agents`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `login_attempts`
--
ALTER TABLE `login_attempts`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
