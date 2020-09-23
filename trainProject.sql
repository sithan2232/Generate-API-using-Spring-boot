-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Sep 23, 2020 at 04:56 PM
-- Server version: 10.4.13-MariaDB
-- PHP Version: 7.4.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `trainProject`
--

-- --------------------------------------------------------

--
-- Table structure for table `like`
--

CREATE TABLE `like` (
  `like_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `post_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `like`
--

INSERT INTO `like` (`like_id`, `user_id`, `post_id`) VALUES
(19, 1, 25),
(21, 11, 25);

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `login_id` int(11) NOT NULL,
  `time_login` datetime(6) DEFAULT NULL,
  `time_logout` datetime(6) DEFAULT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`login_id`, `time_login`, `time_logout`, `user_id`) VALUES
(10, '2020-09-10 08:35:46.000000', '2020-09-11 02:43:53.000000', 1),
(11, '2020-09-11 02:44:05.000000', '2020-09-11 02:44:28.000000', 1),
(12, '2020-09-11 02:48:34.000000', '2020-09-14 07:40:54.000000', 1),
(13, '2020-09-14 07:41:00.000000', '2020-09-14 07:51:56.000000', 1),
(14, '2020-09-14 07:52:05.000000', '2020-09-14 08:23:10.000000', 1),
(15, '2020-09-16 03:30:01.000000', '2020-09-17 02:02:05.000000', 1),
(16, '2020-09-17 02:02:14.000000', '2020-09-17 02:02:16.000000', 1),
(17, '2020-09-17 02:22:48.000000', '2020-09-17 03:10:31.000000', 1),
(18, '2020-09-17 03:10:48.000000', '2020-09-17 03:10:51.000000', 1),
(19, '2020-09-17 03:10:58.000000', '2020-09-17 08:14:16.000000', 1),
(20, '2020-09-17 08:14:31.000000', '2020-09-17 08:37:13.000000', 1),
(21, '2020-09-17 08:37:19.000000', '2020-09-17 08:42:54.000000', 1),
(22, '2020-09-17 08:42:57.000000', '2020-09-17 08:42:59.000000', 1),
(23, '2020-09-17 08:43:07.000000', '2020-09-17 16:06:08.000000', 1),
(25, '2020-09-17 16:06:31.000000', '2020-09-18 04:14:18.000000', 1),
(28, '2020-09-18 04:14:21.000000', '2020-09-18 04:15:00.000000', 1),
(29, '2020-09-18 04:15:27.000000', '2020-09-20 12:11:52.000000', 1),
(30, '2020-09-20 12:11:54.000000', NULL, 1),
(31, '2020-09-20 12:14:44.000000', NULL, 11);

-- --------------------------------------------------------

--
-- Table structure for table `post`
--

CREATE TABLE `post` (
  `post_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `topic_name` char(100) DEFAULT NULL,
  `description` char(100) DEFAULT NULL,
  `color` int(10) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `post`
--

INSERT INTO `post` (`post_id`, `user_id`, `topic_name`, `description`, `color`) VALUES
(25, 1, 'topic1', 'description1', 1),
(26, 1, 'topic2', 'description2', 1);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `first_name` char(100) DEFAULT NULL,
  `last_name` char(100) DEFAULT NULL,
  `age` int(10) DEFAULT NULL,
  `status` tinyint(1) DEFAULT 0,
  `type` int(10) DEFAULT NULL,
  `username` char(100) DEFAULT NULL,
  `password` char(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `first_name`, `last_name`, `age`, `status`, `type`, `username`, `password`) VALUES
(1, 'admin', 'admin', 4, 1, 1, 'Admin', '$2a$10$5OYVxf31/LAyHcOaCRfy4uQDdPhQiYv1U7RwmEbHOthAz0IYf0y0y'),
(11, 'name', 'name', 4, 1, 0, 'user1', '$2a$10$zzui4LVlsAwXMniAcs.Gi.0s9YFZktb.ljH.Og0zQmwwq0vh8RWua');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `like`
--
ALTER TABLE `like`
  ADD PRIMARY KEY (`like_id`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`login_id`);

--
-- Indexes for table `post`
--
ALTER TABLE `post`
  ADD PRIMARY KEY (`post_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `like`
--
ALTER TABLE `like`
  MODIFY `like_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `login`
--
ALTER TABLE `login`
  MODIFY `login_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `post`
--
ALTER TABLE `post`
  MODIFY `post_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
