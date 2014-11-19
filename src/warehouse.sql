-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 19, 2014 at 04:11 AM
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
-- Table structure for table `categorytable`
--

CREATE TABLE IF NOT EXISTS `categorytable` (
`categoryId` int(10) NOT NULL,
  `categoryName` varchar(50) NOT NULL,
  `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `categorytable`
--

INSERT INTO `categorytable` (`categoryId`, `categoryName`, `updatedAt`) VALUES
(1, 'pencil', '2014-11-17 17:46:10'),
(2, 'pen', '2014-11-17 17:46:10'),
(3, 'ruler', '2014-11-17 17:46:33');

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
  `updateAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=18 ;

--
-- Dumping data for table `inventory`
--

INSERT INTO `inventory` (`productid`, `productname`, `description`, `categoryid`, `quantity`, `price`, `updateAt`) VALUES
(17, 'ruler', 'Product Description:', 3, 100, 2.99, '2014-11-17 18:43:07');

-- --------------------------------------------------------

--
-- Table structure for table `returntable`
--

CREATE TABLE IF NOT EXISTS `returntable` (
`returnId` int(10) NOT NULL,
  `transactioinId` int(10) NOT NULL,
  `productId` int(10) NOT NULL,
  `quantity` int(10) NOT NULL,
  `Date` date NOT NULL,
  `updateAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `salerecord`
--

CREATE TABLE IF NOT EXISTS `salerecord` (
  `transactionId` int(10) NOT NULL,
  `productId` int(10) NOT NULL,
  `quantity` int(10) NOT NULL,
  `updateAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=15 ;

--
-- Dumping data for table `usertable`
--

INSERT INTO `usertable` (`customerid`, `userName`, `password`, `firstName`, `lastName`, `phone`, `address`, `role`, `updateAt`) VALUES
(4, 'admin', 'admin', 'hai', 'nguyen', '4086169241', '2608 Carmella Ct San Jose 95135', 'Admin', '0000-00-00 00:00:00'),
(14, 'user', 'user', 'First Name', 'Last Name', '1233', 'Address:', 'Customer', '0000-00-00 00:00:00');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categorytable`
--
ALTER TABLE `categorytable`
 ADD PRIMARY KEY (`categoryId`);

--
-- Indexes for table `inventory`
--
ALTER TABLE `inventory`
 ADD PRIMARY KEY (`productid`);

--
-- Indexes for table `returntable`
--
ALTER TABLE `returntable`
 ADD PRIMARY KEY (`returnId`), ADD KEY `transactioinId` (`transactioinId`,`productId`);

--
-- Indexes for table `salerecord`
--
ALTER TABLE `salerecord`
 ADD PRIMARY KEY (`transactionId`);

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
-- AUTO_INCREMENT for table `categorytable`
--
ALTER TABLE `categorytable`
MODIFY `categoryId` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `inventory`
--
ALTER TABLE `inventory`
MODIFY `productid` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=18;
--
-- AUTO_INCREMENT for table `returntable`
--
ALTER TABLE `returntable`
MODIFY `returnId` int(10) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `transaction`
--
ALTER TABLE `transaction`
MODIFY `transactionID` int(10) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `usertable`
--
ALTER TABLE `usertable`
MODIFY `customerid` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=15;
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
