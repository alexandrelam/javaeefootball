-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Ven 20 Mai 2016 à 16:38
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `francefoot`
--

-- --------------------------------------------------------

--
-- Structure de la table `blog`
--

CREATE TABLE IF NOT EXISTS `blog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `titre` varchar(255) NOT NULL,
  `contenu` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Contenu de la table `blog`
--

INSERT INTO `blog` (`id`, `titre`, `contenu`, `image`, `date`) VALUES
(1, 'Angleterre : Giroud offre la place de dauphin à Arsenal', 'ainqueur d''Aston Villa (4-0), grâce à un triplé d''Olivier Giroud, Arsenal a pris la 2e place du Championnat lors de la dernière journée ce dimanche, profitant de la faillite de Tottenham à Newcastle (5-1). Alors que le match de Manchester United a été rep', '', '2016-05-16 03:00:00'),
(2, 'Pascal Dupraz, l''entraîneur du TFC, rend hommage à «18 guerriers»', 'ainqueur d''Aston Villa (4-0), grâce à un triplé d''Olivier Giroud, Arsenal a pris la 2e place du Championnat lors de la dernière journée ce dimanche, profitant de la faillite de Tottenham à Newcastle (5-1). Alors que le match de Manchester United a été rep', '', '2016-05-16 03:00:00'),
(3, 'Pascal Dupraz, l''entraîneur du TFC, rend hommage à «18 guerriers»', 'ainqueur d''Aston Villa (4-0), grâce à un triplé d''Olivier Giroud, Arsenal a pris la 2e place du Championnat lors de la dernière journée ce dimanche, profitant de la faillite de Tottenham à Newcastle (5-1). Alors que le match de Manchester United a été rep', '', '2016-05-16 03:00:00'),
(4, 'Pascal Dupraz, l''entraîneur du TFC, rend hommage à «18 guerriers»', 'ainqueur d''Aston Villa (4-0), grâce à un triplé d''Olivier Giroud, Arsenal a pris la 2e place du Championnat lors de la dernière journée ce dimanche, profitant de la faillite de Tottenham à Newcastle (5-1). Alors que le match de Manchester United a été rep', '', '2016-05-16 03:00:00'),
(5, 'Pascal Dupraz, l''entraîneur du TFC, rend hommage à «18 guerriers»', 'ainqueur d''Aston Villa (4-0), grâce à un triplé d''Olivier Giroud, Arsenal a pris la 2e place du Championnat lors de la dernière journée ce dimanche, profitant de la faillite de Tottenham à Newcastle (5-1). Alors que le match de Manchester United a été rep', '', '2016-05-16 03:00:00'),
(6, 'Pascal Dupraz, l''entraîneur du TFC, rend hommage à «18 guerriers»', 'ainqueur d''Aston Villa (4-0), grâce à un triplé d''Olivier Giroud, Arsenal a pris la 2e place du Championnat lors de la dernière journée ce dimanche, profitant de la faillite de Tottenham à Newcastle (5-1). Alors que le match de Manchester United a été rep', '', '2016-05-16 03:00:00'),
(7, 'Pascal Dupraz, l''entraîneur du TFC, rend hommage à «18 guerriers»', 'ainqueur d''Aston Villa (4-0), grâce à un triplé d''Olivier Giroud, Arsenal a pris la 2e place du Championnat lors de la dernière journée ce dimanche, profitant de la faillite de Tottenham à Newcastle (5-1). Alors que le match de Manchester United a été rep', '', '2016-05-16 03:00:00');

-- --------------------------------------------------------

--
-- Structure de la table `championnat`
--

CREATE TABLE IF NOT EXISTS `championnat` (
  `id_championnat` int(11) NOT NULL AUTO_INCREMENT,
  `nom_championnat` varchar(35) NOT NULL,
  `logo_championnat` varchar(30) NOT NULL,
  `pays_championnat` varchar(35) NOT NULL,
  `nb_equipe` int(11) NOT NULL,
  `pts_victoire` int(11) NOT NULL,
  `pts_nul` int(11) NOT NULL,
  `pts_defaite` int(11) NOT NULL,
  `regle_classement` varchar(50) NOT NULL,
  PRIMARY KEY (`id_championnat`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Contenu de la table `championnat`
--

INSERT INTO `championnat` (`id_championnat`, `nom_championnat`, `logo_championnat`, `pays_championnat`, `nb_equipe`, `pts_victoire`, `pts_nul`, `pts_defaite`, `regle_classement`) VALUES
(1, 'Ligue 1', 'ligue1.png', 'France', 20, 3, 1, 0, 'goalaverage'),
(2, 'Ligue 2', 'ligue2.png', 'France', 20, 3, 1, 0, 'Goalaverage'),
(4, 'National', 'national.png', 'France', 18, 3, 1, 0, 'Goalaverage');

-- --------------------------------------------------------

--
-- Structure de la table `equipe`
--

CREATE TABLE IF NOT EXISTS `equipe` (
  `id_equipe` int(20) NOT NULL AUTO_INCREMENT,
  `nom_equipe` varchar(40) NOT NULL,
  `surnom_equipe` varchar(30) NOT NULL,
  `abr_equipe` varchar(3) DEFAULT NULL,
  `logo_equipe` varchar(30) NOT NULL,
  `maillot_dom` varchar(20) NOT NULL,
  `maillot_ext` varchar(20) NOT NULL,
  `maillot_trois` varchar(20) NOT NULL,
  `nom_stade` varchar(35) NOT NULL,
  `capacite_stade` varchar(11) NOT NULL,
  `id_championnat` int(11) NOT NULL,
  PRIMARY KEY (`id_equipe`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=23 ;

--
-- Contenu de la table `equipe`
--

INSERT INTO `equipe` (`id_equipe`, `nom_equipe`, `surnom_equipe`, `abr_equipe`, `logo_equipe`, `maillot_dom`, `maillot_ext`, `maillot_trois`, `nom_stade`, `capacite_stade`, `id_championnat`) VALUES
(1, 'PARIS-SAINT-GERMAIN FOOTBALL CLUB ', 'Paris Saint-Germain', 'PSG', 'psg.png', 'parisDom', 'parisExt', 'parisTrois', 'Parc des princes', '47428', 1),
(2, 'ASSOCIATION SPORTIVE DE MONACO FC ', 'Monaco', 'ASM', 'monaco.png', 'monacoDom', 'monacoExt', 'monacoTrois', 'Louis II', '18524', 1),
(3, 'OLYMPIQUE GYMNASTE CLUB NICE CÔTE D''AZUR', 'Nice', 'OGC', 'nice.png', 'niceDom', 'niceExt', 'niceTrois', 'Allianz Riviera', '35624', 1),
(4, 'OLYMPIQUE LYONNAIS', 'Lyon', 'OL', 'lyon.png', 'lyonDom', 'lyonExt', 'lyonTrois', 'Parc Olympique Lyonnais', '59186', 1),
(5, 'STADE RENNAIS FOOTBALL CLUB', 'Rennes', 'REN', 'rennes.png', 'rennesDom', 'rennesExt', 'rennesTrois', 'Roazhon Park', '29778', 1),
(6, 'STADE MALHERBE DE CAEN', 'Caen', 'SMC', 'caen.png', 'caenDom', 'caenExt', 'caenTrois', 'Michel-d''Ornano', '22864', 1),
(7, 'ASSOCIATION SPORTIVE DE ST-ETIENNE', 'St-Etienne', 'STE', 'saint-etienne.png', 'stEtienneDom', 'stEtienneExt', 'stEtienneTrois', 'Geoffroy-Guichard', '35600', 1),
(8, 'FOOTBALL CLUB DE NANTES', 'Nantes', 'FCN', 'nantes.png', 'nantesDom', 'nantesExt', 'nantesTrois', 'La Beaujoire', '37583', 1),
(9, 'LOSC LILLE', 'Lille', 'STE', 'lille.png', 'lilleDom', 'lilleExt', 'lilleTrois', 'Pierre-Mauroy', '50186', 1),
(10, 'ANGERS SPORTING CLUB DE L''OUEST', 'Angers', 'SCA', 'angers.png', 'angersDom', 'angersExt', 'angersTrois', 'Jean-Bouin', '17000', 1),
(11, 'SPORTING CLUB DE BASTIA', 'Bastia', 'SCB', 'bastia.png', 'bastiaDom', 'bastiaExt', 'bastiaTrois', 'Armand-Cesari', '16460', 1),
(12, 'OLYMPIQUE DE MARSEILLE', 'Marseille', 'OM', 'marseille.png', 'marseilleDom', 'marseilleExt', 'marseilleTrois', 'Vélodrome', '67000', 1),
(13, 'FOOTBALL CLUB LORIENT-BRETAGNE SUD', 'Lorient', 'FCL', 'lorient.png', 'lorientDom', 'lorientExt', 'lorientTrois', 'Yves-Allainmat', '18110', 1),
(14, 'FOOTBALL CLUB DES GIRONDINS DE BORDEAUX', 'Bordeaux', 'FCB', 'bordeaux.png', 'bordeauxDom', 'bordeauxExt', 'bordeauxTrois', 'Matmut Atlantique', '34263', 1),
(15, 'EN AVANT DE GUINGAMP', 'Guingamp', 'EAG', 'guingamp.png', 'guingampDom', 'guingampExt', 'guingampTrois', 'Stade du Roudourou', '18250', 1),
(16, 'MONTPELLIER-HERAULT SPORT CLUB', 'Montpellier', 'MHC', 'montpellier.png', 'montpellierDom', 'montpellierExt', 'montpellierTrois', 'La Mosson', '32500', 1),
(17, 'STADE DE REIMS', 'Reims', 'SDR', 'reims.png', 'reimsDom', 'reimsExt', 'reimsTrois', 'Auguste-Delaune', '21684', 1),
(18, 'GAZELEC FOOTBALL CLUB AJACCIO', 'GFC Ajaccio', 'GFC', 'ajaccio.png', 'ajaccioDom', 'ajaccioExt', 'ajaccioTrois', 'Ange-Casanova', '10045', 1),
(19, 'TOULOUSE FOOTBALL CLUB', 'Toulouse', 'TFC', 'toulouse.png', 'toulouseDom', 'toulouseExt', 'toulouseTrois', 'Stadium Municipal', '35575', 1),
(20, 'ESPÉRANCE SPORTIVE TROYES AUBE CHAMPAGNE', 'Troyes', 'EST', 'troyes.png', 'troyesDom', 'troyesExt', 'troyesTrois', 'Stade de l''Aube', '20842', 1),
(21, 'VALENCIENNES FOOTBALL CLUB', 'Valenciennes', 'VA', 'valenciennes.png', 'valenciennesDom', 'valenciennesExt', 'valenciennesTrois', 'Stade du Hainaut', '25132', 2),
(22, '', '', '', '', '', '', '', '', '', 0);

-- --------------------------------------------------------

--
-- Structure de la table `match`
--

CREATE TABLE IF NOT EXISTS `match` (
  `id_match` int(11) NOT NULL AUTO_INCREMENT,
  `date_match` date NOT NULL,
  `buts_dom` int(11) NOT NULL,
  `buts_ext` int(11) NOT NULL,
  `id_dom` int(11) NOT NULL,
  `id_ext` int(11) NOT NULL,
  `id_journee` int(11) NOT NULL,
  `id_championnat` int(11) NOT NULL,
  PRIMARY KEY (`id_match`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=35 ;

--
-- Contenu de la table `match`
--

INSERT INTO `match` (`id_match`, `date_match`, `buts_dom`, `buts_ext`, `id_dom`, `id_ext`, `id_journee`, `id_championnat`) VALUES
(1, '2015-08-07', 0, 1, 9, 1, 1, 1),
(2, '2015-08-07', 4, 0, 4, 13, 1, 1),
(4, '2015-08-08', 2, 1, 11, 5, 1, 1),
(5, '2015-08-08', 1, 2, 3, 2, 1, 1),
(6, '2015-08-08', 1, 0, 8, 15, 1, 1),
(7, '2015-08-08', 0, 1, 12, 6, 1, 1),
(8, '2015-08-08', 0, 0, 20, 18, 1, 1),
(9, '2015-08-08', 0, 2, 16, 10, 1, 1),
(10, '2015-08-09', 2, 1, 19, 7, 1, 1),
(11, '2015-08-09', 1, 2, 14, 17, 1, 1),
(14, '2015-08-14', 0, 0, 2, 9, 2, 1),
(15, '2015-08-15', 1, 1, 7, 14, 2, 1),
(16, '2015-08-15', 0, 0, 10, 8, 2, 1),
(17, '2015-08-15', 1, 0, 6, 19, 2, 1),
(18, '2015-08-15', 3, 3, 20, 3, 2, 1),
(19, '2015-08-15', 0, 1, 15, 4, 2, 1),
(20, '2015-08-15', 1, 0, 5, 16, 2, 1),
(21, '2015-08-16', 1, 0, 17, 12, 2, 1),
(22, '2015-08-16', 1, 1, 13, 11, 2, 1),
(23, '2015-08-16', 2, 0, 1, 18, 2, 1),
(24, '2015-08-21', 0, 1, 16, 1, 3, 1),
(25, '2015-08-22', 1, 2, 4, 5, 3, 1),
(26, '2015-08-22', 1, 0, 8, 17, 3, 1),
(27, '2015-08-22', 2, 1, 3, 6, 3, 1),
(28, '2015-08-22', 0, 2, 18, 10, 3, 1),
(29, '2015-08-22', 3, 0, 11, 15, 3, 1),
(30, '2015-08-22', 1, 1, 19, 2, 3, 1),
(31, '2015-08-23', 0, 0, 9, 14, 3, 1),
(32, '2015-08-23', 0, 1, 13, 7, 3, 1),
(33, '2015-08-23', 6, 0, 12, 20, 3, 1);

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `users`
--

INSERT INTO `users` (`id`, `nom`, `prenom`, `email`, `password`) VALUES
(1, 'super', 'admin', 'alexandre.lam@outlook.fr', '123456');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
