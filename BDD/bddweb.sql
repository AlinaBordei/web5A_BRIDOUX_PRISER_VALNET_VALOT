-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Client :  127.0.0.1
-- Généré le :  Jeu 28 Septembre 2017 à 19:05
-- Version du serveur :  5.7.14
-- Version de PHP :  5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `bddweb`
--

-- --------------------------------------------------------

--
-- Structure de la table `conversation`
--

CREATE TABLE `conversation` (
  `IDConversation` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `conversation`
--

INSERT INTO `conversation` (`IDConversation`) VALUES
(1);

-- --------------------------------------------------------

--
-- Structure de la table `message`
--

CREATE TABLE `message` (
  `IDMessage` int(11) NOT NULL,
  `message` varchar(255) NOT NULL,
  `date` datetime NOT NULL,
  `id_conversation` int(11) NOT NULL,
  `userID` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `message`
--

INSERT INTO `message` (`IDMessage`, `message`, `date`, `id_conversation`, `userID`) VALUES
(1, 'L\'inspiration me manque pour faire ce test, je sais, je suis désespérante...', '2017-09-21 10:21:25', 1, 6);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `mail` varchar(256) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`id`, `name`, `password`, `mail`) VALUES
(1, 'First', 'zozzo', ''),
(2, 'John', 'Woo', ''),
(3, 'Jeff', 'Dean', ''),
(4, 'Josh', 'Bloch', ''),
(5, 'Josh', 'Long', ''),
(6, 'claraouu', 'youpi', 'calara.bridoux@gmail'),
(7, 'coco', 'coco', 'coco@coco'),
(9, 'Gilbert', 'azerty', 'jaime.latouraine@gmail.com'),
(21, 'Gilbert', 'tutyu', 'mementomori77160@hotmail.fr'),
(20, 'Gilbert', 'jujtyj', 'mementomori77160@hotmail.fr');

-- --------------------------------------------------------

--
-- Structure de la table `user_conversation`
--

CREATE TABLE `user_conversation` (
  `conversationID` int(11) NOT NULL,
  `userID` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `user_conversation`
--

INSERT INTO `user_conversation` (`conversationID`, `userID`) VALUES
(1, 6);

--
-- Index pour les tables exportées
--

--
-- Index pour la table `conversation`
--
ALTER TABLE `conversation`
  ADD PRIMARY KEY (`IDConversation`);

--
-- Index pour la table `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`IDMessage`),
  ADD KEY `id_conversation` (`id_conversation`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `conversation`
--
ALTER TABLE `conversation`
  MODIFY `IDConversation` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `message`
--
ALTER TABLE `message`
  MODIFY `IDMessage` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
