-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Client :  127.0.0.1
-- Généré le :  Dim 29 Octobre 2017 à 20:56
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
(1),
(2),
(3);

-- --------------------------------------------------------

--
-- Structure de la table `message`
--

CREATE TABLE `message` (
  `IDMessage` int(11) NOT NULL,
  `message` varchar(255) NOT NULL,
  `date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id_conversation` int(11) NOT NULL,
  `userID` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `message`
--

INSERT INTO `message` (`IDMessage`, `message`, `date`, `id_conversation`, `userID`) VALUES
(1, 'Salut Clara ! ', '2017-10-29 21:22:31', 1, 2),
(2, 'Salut ma poule !', '2017-10-29 21:22:52', 1, 3),
(3, 'Salut Franck ! ', '2017-10-29 21:24:09', 2, 2),
(4, 'Salut les amis !', '2017-10-29 21:25:30', 3, 2);

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
(1, 'Rob', 'rob2017', 'rob@gmail.com'),
(2, 'Alina', 'jojo2017', 'alina.valnet@gmail.com'),
(3, 'Clara', 'clara2017', 'bridoux@et.esiea.fr'),
(4, 'Franck', 'franck2017', 'valot@et.esiea.fr'),
(5, 'Axel', 'axel2017', 'priser@et.esiea.fr'),
(6, 'Jean', 'jean2017', 'dupont@et.esiea.fr'),
(7, 'Thomas', 'thomas2017', 'alvares@et.esiea.fr'),
(8, 'Antoine', 'antoine2017', 'castellane@et.esiea.fr'),
(9, 'Nicolas', 'nico2017', 'cadot@et.esiea.fr'),
(10, 'Roland', 'roland2017', 'garros@et.esiea.fr');

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
(1, 2),
(1, 3),
(2, 2),
(2, 4),
(3, 3),
(3, 4),
(3, 2);

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
  MODIFY `IDConversation` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT pour la table `message`
--
ALTER TABLE `message`
  MODIFY `IDMessage` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
