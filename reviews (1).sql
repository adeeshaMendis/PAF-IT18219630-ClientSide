-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: May 14, 2021 at 05:48 PM
-- Server version: 5.5.15
-- PHP Version: 7.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `review`
--

-- --------------------------------------------------------

--
-- Table structure for table `reviews`
--

DROP TABLE IF EXISTS `reviews`;
CREATE TABLE IF NOT EXISTS `reviews` (
  `reviewID` int(11) NOT NULL AUTO_INCREMENT,
  `reviewType` varchar(30) NOT NULL,
  `reviewDesc` varchar(500) NOT NULL,
  `reviewValue` int(11) NOT NULL,
  PRIMARY KEY (`reviewID`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reviews`
--

INSERT INTO `reviews` (`reviewID`, `reviewType`, `reviewDesc`, `reviewValue`) VALUES
(21, 'positive feedback', 'Excellent app....no words to describe it....each and every time I book gives me a wonderful experience.', 5),
(26, 'positive feedback', 'I use it often but not all the time I have the best experience, even so I do agree that helps at time and nothing is perfect. It does what it suppose to do, but like everything made by human isn\'t at the perfect standard all the time.', 5),
(44, 'negative feedback', 'This app i have been using it for some time now i had no problem with it until i removed from my phone and download it, and after re-download it the app doesn\'t open.', 1),
(46, 'question', 'This app constantly opens two different windows for no reason and freezes/crashes every other time, making me have to close and reopen it. I have uninstalled and reinstalled, rebooted my phone, updated it. Same thing occurs. I\'m on a Samsung Galaxy S20+, not some random, no name device.', 3);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
