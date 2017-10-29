-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Client :  127.0.0.1
-- Généré le :  Jeu 26 Octobre 2017 à 18:37
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
(3),
(4),
(5),
(6),
(7),
(8),
(9),
(10),
(11),
(12),
(13),
(14),
(15),
(16),
(17),
(18),
(19),
(20),
(21),
(22),
(23),
(24),
(25),
(26),
(27),
(28),
(29),
(30),
(31);

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
(1, 'L\'inspiration me manque pour faire ce test, je sais, je suis désespérante...', '2017-09-21 10:21:25', 1, 6),
(2, 'salut', '2017-10-02 10:23:25', 0, 0),
(3, 'coucou', '2017-10-02 10:23:32', 0, 0),
(4, 'dgfg', '2017-10-02 13:08:20', 0, 0),
(5, 'coucou', '2017-10-24 20:14:29', 0, 0),
(6, 'yes gros', '2017-10-24 20:18:25', 0, 0),
(7, 'cool', '2017-10-24 20:20:44', 0, 0),
(8, 'bien ou bien ?', '2017-10-24 20:21:56', 0, 0),
(9, 'megane', '2017-10-24 20:25:41', 6, 9),
(10, 'cool', '2017-10-24 20:26:20', 6, 9),
(11, 'génial', '2017-10-24 20:36:01', 6, 7),
(12, 'lol', '2017-10-24 21:05:25', 6, 9),
(13, 'sdfq', '2017-10-24 21:09:04', 6, 7),
(14, 'wunderbar', '2017-10-24 21:10:03', 6, 9),
(15, 'manuel', '2017-10-25 10:09:19', 6, 9),
(16, 'coucou', '2017-10-25 19:19:34', 0, 42),
(17, 'ça gazouille ?', '2017-10-25 19:19:48', 0, 42),
(18, 'Alors ?', '2017-10-25 19:20:22', 0, 42),
(19, 'coucou', '2017-10-25 21:21:31', 0, 42),
(20, 'yes !', '2017-10-26 16:32:45', 16, 42),
(21, 'ouiiiii', '2017-10-26 16:58:54', 16, 42),
(22, 'Coucou les gars, ça gazouille ?', '2017-10-26 19:01:22', 0, 44),
(23, 'Salut tout le monde !', '2017-10-26 19:02:59', 0, 44);

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
(39, 'Chounet', '\'fr8r]Mij"1', 'gfdjg.gfdg@fdgf.fdgf'),
(23, 'clarinette', 'xJ*3M((\\jG+%?%o', 'jaime.latouraine@gmail.com'),
(32, 'coco123', 't?/?S7We?', 'fjdgkd@gfdg'),
(37, 'crocrodile', 'D¥sx$dm)K]', 'fdgdfg@gfdgf'),
(29, 'jack', '%uLJ~wwf{}Hg[eyNck', 'dsjfksld.dsf@fdsfs.fsdf'),
(30, 'bridoux', '£>U|(Ay,67Q?YEGpkP', 'dsjfksld.dsf@fdsfs.fsdf'),
(40, 'Chounette', 'L?3Jn??qwN3', 'gfdjg.gfdg@fdgf.fdg'),
(41, 'kadoc', 'ABsC!Q[mfop#(X', 'leclandes.semis-croustillants@perceval.fr'),
(42, 'Manuel', 'azerty', 'denis.freres@renault.fr'),
(43, 'Choupinette', 'azerty', 'choupi@hgjgh.fr'),
(44, 'Bergerac', 'azerty', 'bergerac@vin.fr');

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
(1, 6),
(6, 0),
(6, 0),
(6, 0),
(6, 0),
(6, 0),
(6, 0),
(6, 0),
(6, 0),
(6, 0),
(6, 0),
(6, 40),
(6, 39),
(6, 6),
(6, 7),
(6, 9),
(6, 6),
(6, 23),
(6, 39),
(6, 9),
(6, 39),
(15, 6),
(15, 40),
(15, 42),
(15, 9),
(15, 42),
(16, 6),
(16, 9),
(16, 42),
(16, 23),
(16, 42),
(16, 6),
(16, 9),
(16, 42),
(16, 23),
(16, 7),
(16, 42),
(15, 7),
(15, 43),
(15, 42),
(0, 39),
(0, 42),
(0, 9),
(16, 32),
(16, 42),
(21, 37),
(21, 42),
(22, 7),
(22, 43),
(23, 32),
(24, 32),
(25, 7),
(26, 39),
(27, 6),
(28, 7),
(29, 7),
(29, 39),
(30, 9),
(30, 23),
(30, 44),
(31, 32),
(31, 44);

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
  MODIFY `IDConversation` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;
--
-- AUTO_INCREMENT pour la table `message`
--
ALTER TABLE `message`
  MODIFY `IDMessage` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;
--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
