-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 21, 2017 at 11:55 AM
-- Server version: 10.1.26-MariaDB
-- PHP Version: 7.1.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ebaithak`
--

-- --------------------------------------------------------

--
-- Table structure for table `attachment`
--

CREATE TABLE `attachment` (
  `id` bigint(20) NOT NULL,
  `file` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `baithak`
--

CREATE TABLE `baithak` (
  `id` bigint(20) NOT NULL,
  `created_at` varchar(255) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `discription` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `baithak`
--

INSERT INTO `baithak` (`id`, `created_at`, `created_by`, `discription`, `image`, `name`) VALUES
(1, NULL, 1, 'This is virus baithak', 'Photo.jpg', 'Virus'),
(2, NULL, 2, 'This is virus baithak', 'Photo.jpg', 'Virus');

-- --------------------------------------------------------

--
-- Table structure for table `baithakmembers`
--

CREATE TABLE `baithakmembers` (
  `id` bigint(20) NOT NULL,
  `addedBy` bigint(20) DEFAULT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `groupName` varchar(255) DEFAULT NULL,
  `senderName` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `baithakmembers`
--

INSERT INTO `baithakmembers` (`id`, `addedBy`, `groupId`, `groupName`, `senderName`, `status`, `userId`) VALUES
(1, 1, 1, NULL, NULL, 1, 1),
(2, 2, 2, NULL, NULL, 1, 2),
(3, 2, 2, NULL, NULL, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `emoji`
--

CREATE TABLE `emoji` (
  `id` bigint(20) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `friends`
--

CREATE TABLE `friends` (
  `id` bigint(20) NOT NULL,
  `friendId` bigint(20) DEFAULT NULL,
  `senderName` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `friends`
--

INSERT INTO `friends` (`id`, `friendId`, `senderName`, `status`, `userId`) VALUES
(1, 1, NULL, 1, 2),
(2, 2, NULL, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `messgae`
--

CREATE TABLE `messgae` (
  `id` bigint(20) NOT NULL,
  `createdAt` varchar(255) DEFAULT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `senderId` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `messgae`
--

INSERT INTO `messgae` (`id`, `createdAt`, `groupId`, `message`, `senderId`) VALUES
(1, 'Sun Nov 12 17:29:50 NPT 2017', 2, 'hi', 1);

-- --------------------------------------------------------

--
-- Table structure for table `notification`
--

CREATE TABLE `notification` (
  `id` bigint(20) NOT NULL,
  `created_at` varchar(255) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `notification`
--

INSERT INTO `notification` (`id`, `created_at`, `message`, `userId`) VALUES
(1, 'Sun Nov 12 12:50:40 ', '<b>Demo (demo)</b> added you to the group <b>Virus</b>.', 1),
(2, 'Sun Nov 12 12:50:40 ', '<b>Demo (demo)</b> has been added to the group <b>Virus</b>.', 1),
(3, 'Sun Nov 12 17:28:20 ', '<b>Demo 1 (demo1)</b> added you as a friend', 1),
(4, 'Sun Nov 12 17:28:20 ', '<b>Demo (demo)</b> added as a friend', 2),
(5, 'Sun Nov 12 17:28:59 ', '<b>Demo 1 (demo1)</b> added you to the group <b>Virus</b>.', 2),
(6, 'Sun Nov 12 17:28:59 ', '<b>Demo 1 (demo1)</b> has been added to the group <b>Virus</b>.', 2),
(7, 'Sun Nov 12 17:29:19 ', '<b>Demo 1 (demo1)</b> added you to the group <b>Virus</b>.', 1),
(8, 'Sun Nov 12 17:29:19 ', '<b>Demo (demo)</b> has been added to the group <b>Virus</b>.', 2);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_leave`
--

CREATE TABLE `tbl_leave` (
  `id` bigint(20) NOT NULL,
  `baithak_id` bigint(20) DEFAULT NULL,
  `created_at` varchar(255) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `name`, `password`, `photo`, `role`, `status`, `username`) VALUES
(1, 'Demo', '8277e091-0d75-3195-b448-797616e091ad', 'Photo.jpg', NULL, 1, 'demo'),
(2, 'Demo 1', 'fe01ce2a-7fba-38fa-baed-7c982a04e229', 'Photo.jpg', NULL, 0, 'demo1');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `attachment`
--
ALTER TABLE `attachment`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `baithak`
--
ALTER TABLE `baithak`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `baithakmembers`
--
ALTER TABLE `baithakmembers`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `emoji`
--
ALTER TABLE `emoji`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `friends`
--
ALTER TABLE `friends`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `messgae`
--
ALTER TABLE `messgae`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `notification`
--
ALTER TABLE `notification`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_leave`
--
ALTER TABLE `tbl_leave`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `attachment`
--
ALTER TABLE `attachment`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `baithak`
--
ALTER TABLE `baithak`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `baithakmembers`
--
ALTER TABLE `baithakmembers`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `emoji`
--
ALTER TABLE `emoji`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `friends`
--
ALTER TABLE `friends`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `messgae`
--
ALTER TABLE `messgae`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `notification`
--
ALTER TABLE `notification`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `tbl_leave`
--
ALTER TABLE `tbl_leave`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
