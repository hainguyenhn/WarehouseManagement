-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 17, 2014 at 05:42 AM
-- Server version: 5.6.20
-- PHP Version: 5.5.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `warehouse`
--

-- --------------------------------------------------------

--
-- Table structure for table `inventory`
--

CREATE TABLE IF NOT EXISTS `inventory` (
`productid` int(10) NOT NULL,
  `productname` varchar(50) NOT NULL,
  `description` varchar(50) NOT NULL,
  `categoryid` int(10) NOT NULL,
  `quantity` int(10) NOT NULL,
  `price` double NOT NULL,
  `updateAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=14 ;

--
-- Dumping data for table `inventory`
--

INSERT INTO `inventory` (`productid`, `productname`, `description`, `categoryid`, `quantity`, `price`, `updateAt`) VALUES
(6, 'ruler', 'my ruler	', 12, 12, 1.99, '2014-11-15 19:03:12'),
(7, 'admin', 'Product Description:', 1, 10, 1.22, '2014-11-15 19:03:12'),
(8, 'hai', 'ngsadf	', 1, 12, 1.88, '2014-11-15 19:03:47'),
(9, 'fdas', 'fdas', 2, 1, 22, '2014-11-15 19:06:18'),
(10, 'meeee', 'Product Description:			', 1, 13, 2.992, '2014-11-15 19:17:06'),
(11, 'pencil', 'my pencil desectiption		', 13, 12, 1.22, '2014-11-15 19:17:48'),
(12, 'pen', 'hadf	', 144, 44, 122, '2014-11-15 19:19:01'),
(13, 'ruler', 'kaka	', 11, 100, 299.3, '2014-11-15 22:48:43');

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE IF NOT EXISTS `transaction` (
  `customerId` int(10) NOT NULL,
`transactionID` int(10) NOT NULL,
  `Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `usertable`
--

CREATE TABLE IF NOT EXISTS `usertable` (
`customerid` int(10) NOT NULL,
  `userName` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `firstName` varchar(50) NOT NULL,
  `lastName` varchar(50) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `address` varchar(100) NOT NULL,
  `role` enum('Admin','Customer','','') NOT NULL,
  `updateAt` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `usertable`
--

INSERT INTO `usertable` (`customerid`, `userName`, `password`, `firstName`, `lastName`, `phone`, `address`, `role`, `updateAt`) VALUES
(4, 'admin', 'admin', 'hai', 'nguyen', '4086169241', '2608 Carmella Ct San Jose 95135', 'Admin', '0000-00-00 00:00:00'),
(5, 'hainguyenhn', 'haih', 'hai', 'nguyen', '4086169242', 'Addressfdafdsfjdlksa', 'Admin', '2014-11-16 23:55:36'),
(6, 'kim', 'kim', 'kim', 'pham', '4082097763', '1829 Trudean Way, San Jose, Ca 95135', 'Customer', '2014-11-14 23:50:14'),
(7, 'User Name', 'jPasswordField1', 'First Name', 'Last Name', '1', 'Address:', 'Customer', '2014-11-14 23:53:30'),
(8, 'User Namedd', 'jPasswordField1', 'First Name', 'Last Name', '2', 'Address:', 'Admin', '0000-00-00 00:00:00');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `inventory`
--
ALTER TABLE `inventory`
 ADD PRIMARY KEY (`productid`);

--
-- Indexes for table `transaction`
--
ALTER TABLE `transaction`
 ADD PRIMARY KEY (`transactionID`), ADD KEY `customerId` (`customerId`), ADD KEY `customerId_2` (`customerId`);

--
-- Indexes for table `usertable`
--
ALTER TABLE `usertable`
 ADD PRIMARY KEY (`customerid`), ADD KEY `customerid` (`customerid`), ADD KEY `customerid_2` (`customerid`), ADD KEY `customerid_3` (`customerid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `inventory`
--
ALTER TABLE `inventory`
MODIFY `productid` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT for table `transaction`
--
ALTER TABLE `transaction`
MODIFY `transactionID` int(10) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `usertable`
--
ALTER TABLE `usertable`
MODIFY `customerid` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=9;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `transaction`
--
ALTER TABLE `transaction`
ADD CONSTRAINT `transaction_ibfk_1` FOREIGN KEY (`customerId`) REFERENCES `usertable` (`customerid`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
