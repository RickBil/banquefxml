-- phpMyAdmin SQL Dump
-- version 4.9.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Oct 15, 2022 at 03:12 PM
-- Server version: 5.7.26
-- PHP Version: 7.4.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `java_l3_ism_2022`
--

-- --------------------------------------------------------

--
-- Table structure for table `agence`
--

CREATE TABLE `agence` (
  `id` int(11) NOT NULL,
  `numero` varchar(10) NOT NULL,
  `adresse` text NOT NULL,
  `tel` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `agence`
--

INSERT INTO `agence` (`id`, `numero`, `adresse`, `tel`) VALUES
(1, 'AG_1', 'Point E', '338331010'),
(2, 'AG_2', 'Fass', '338331011'),
(3, 'AG_3', 'Dakar Plateau', '33 866 95 95'),
(4, 'AG_1', 'Colobane', '33 866 10 15'),
(5, 'AG_1', 'xxxxx', 'xxxx'),
(6, 'AG_1', 'kkkkkk', 'kkkk');

-- --------------------------------------------------------

--
-- Table structure for table `compte`
--

CREATE TABLE `compte` (
  `id` int(11) NOT NULL,
  `numero` varchar(10) NOT NULL,
  `solde` float NOT NULL,
  `type` varchar(20) NOT NULL,
  `taux` float DEFAULT NULL,
  `frais` float DEFAULT NULL,
  `client_id` int(11) NOT NULL,
  `agence_id` int(11) NOT NULL,
  `carte_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `compte`
--

INSERT INTO `compte` (`id`, `numero`, `solde`, `type`, `taux`, `frais`, `client_id`, `agence_id`, `carte_id`) VALUES
(1, 'CPT_1', 20000, 'Epargne', 0.05, NULL, 2, 1, NULL),
(2, 'CPT_2', 20000, 'Cheque', NULL, 200, 1, 1, NULL),
(3, 'CPT_1', 20000, 'Epargne', 0.05, NULL, 1, 1, NULL),
(4, 'CPT_1', 1900000, 'Epargne', 5, NULL, 5, 1, NULL),
(5, 'CPT_2', 200000, 'Cheque', NULL, 200, 5, 1, NULL),
(6, 'CPT_1', 20000000, 'Cheque', NULL, 200, 2, 1, NULL),
(7, 'NUM_1', 3000000, 'Epargne', 0.02, NULL, 2, 1, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `role` varchar(20) NOT NULL,
  `login` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `nom_complet` varchar(50) NOT NULL,
  `tel` varchar(11) NOT NULL,
  `agence_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `role`, `login`, `password`, `nom_complet`, `tel`, `agence_id`) VALUES
(1, 'Gestionnaire', 'admin@gmail.com', 'admin', 'Admin', '', 1),
(2, 'Client', 'test', 'test', 'Test Test', '771001010', NULL),
(3, 'Client', 'wane', 'wane', 'Wane Baila', '772001010', NULL),
(4, 'Client', '222', '222', '222', '222', NULL),
(5, 'Client', 'ww', 'www', 'www', '3444', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `agence`
--
ALTER TABLE `agence`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `compte`
--
ALTER TABLE `compte`
  ADD PRIMARY KEY (`id`),
  ADD KEY `client_id` (`client_id`),
  ADD KEY `agence_id` (`agence_id`),
  ADD KEY `carte_id` (`carte_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `agence`
--
ALTER TABLE `agence`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `compte`
--
ALTER TABLE `compte`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--