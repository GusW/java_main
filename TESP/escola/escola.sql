-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 28, 2015 at 05:16 AM
-- Server version: 5.6.16
-- PHP Version: 5.5.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `escola`
--

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Table structure for table `member`
--

CREATE TABLE IF NOT EXISTS `member` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `name` varchar(25) NOT NULL,
  `phone_number` varchar(12) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_9qv6yhjqm8iafto8qk452gx8h` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `member`
--

INSERT INTO `member` (`id`, `email`, `name`, `phone_number`) VALUES
(0, 'john.smith@mailinator.com', 'John Smith', '2125551212');

-- --------------------------------------------------------

--
-- Table structure for table `tb_aluno`
--

CREATE TABLE IF NOT EXISTS `tb_aluno` (
  `dataAniversario` date NOT NULL,
  `matricula` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_7qp4rbvequxsx7ke5cbt9m67y` (`matricula`),
  KEY `FK_dukjmeukwfsm0mpbfrpgcykr` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_aluno`
--

INSERT INTO `tb_aluno` (`dataAniversario`, `matricula`, `id`) VALUES
('1991-07-24', 21, 1),
('1992-07-24', 22, 2),
('2015-04-06', 23, 9);

-- --------------------------------------------------------

--
-- Table structure for table `tb_pessoa`
--

CREATE TABLE IF NOT EXISTS `tb_pessoa` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cpf` char(14) NOT NULL,
  `nome` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_5yk299a77740w681hxx04j7b3` (`cpf`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `tb_pessoa`
--

INSERT INTO `tb_pessoa` (`id`, `cpf`, `nome`) VALUES
(1, '102.999.555-22', 'indiana jones'),
(2, '102.999.555-33', 'reitor'),
(4, '123.123.123.14', 'Joao Valentao'),
(5, '123.123.123-12', 'Joana'),
(9, '123.123.123-15', 'Tainara'),
(10, '123.123.123-16', 'Ian Thompson');

-- --------------------------------------------------------

--
-- Table structure for table `tb_professor`
--

CREATE TABLE IF NOT EXISTS `tb_professor` (
  `salario` decimal(19,2) NOT NULL,
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ol2cjgwcp0r1jfnwyscvu38sg` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_professor`
--

INSERT INTO `tb_professor` (`salario`, `id`) VALUES
('2650.00', 4),
('4345.67', 5),
('1234.56', 10);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tb_aluno`
--
ALTER TABLE `tb_aluno`
  ADD CONSTRAINT `FK_dukjmeukwfsm0mpbfrpgcykr` FOREIGN KEY (`id`) REFERENCES `tb_pessoa` (`id`);

--
-- Constraints for table `tb_professor`
--
ALTER TABLE `tb_professor`
  ADD CONSTRAINT `FK_ol2cjgwcp0r1jfnwyscvu38sg` FOREIGN KEY (`id`) REFERENCES `tb_pessoa` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
