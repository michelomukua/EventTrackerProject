-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema tizidb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `tizidb` ;

-- -----------------------------------------------------
-- Schema tizidb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `tizidb` DEFAULT CHARACTER SET utf8 ;
USE `tizidb` ;

-- -----------------------------------------------------
-- Table `workout`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `workout` ;

CREATE TABLE IF NOT EXISTS `workout` (
  `workout_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(2000) NOT NULL,
  `duration` INT NOT NULL,
  PRIMARY KEY (`workout_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `member`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `member` ;

CREATE TABLE IF NOT EXISTS `member` (
  `member_id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `date_started` DATETIME NOT NULL,
  `date_ended` DATETIME NULL,
  `active` TINYINT NOT NULL,
  PRIMARY KEY (`member_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `event`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `event` ;

CREATE TABLE IF NOT EXISTS `event` (
  `event_id` INT NOT NULL AUTO_INCREMENT,
  `member_id` INT NOT NULL,
  `workout_id` INT NOT NULL,
  `event_date` DATETIME NOT NULL,
  `comment` VARCHAR(2000) NULL,
  PRIMARY KEY (`event_id`))
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS tizi@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'tizi'@'localhost' IDENTIFIED BY 'tizi';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'tizi'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `workout`
-- -----------------------------------------------------
START TRANSACTION;
USE `tizidb`;
INSERT INTO `workout` (`workout_id`, `name`, `description`, `duration`) VALUES (1, 'track', 'track', 50);
INSERT INTO `workout` (`workout_id`, `name`, `description`, `duration`) VALUES (2, 'core', 'core', 30);
INSERT INTO `workout` (`workout_id`, `name`, `description`, `duration`) VALUES (3 , 'aqua', 'swim', 60);
INSERT INTO `workout` (`workout_id`, `name`, `description`, `duration`) VALUES (4, 'cardio', 'cardio', 25);
INSERT INTO `workout` (`workout_id`, `name`, `description`, `duration`) VALUES (5, 'cycle', 'cycle', 45);
INSERT INTO `workout` (`workout_id`, `name`, `description`, `duration`) VALUES (6, 'hiit', 'hiit', 30);

COMMIT;


-- -----------------------------------------------------
-- Data for table `member`
-- -----------------------------------------------------
START TRANSACTION;
USE `tizidb`;
INSERT INTO `member` (`member_id`, `first_name`, `last_name`, `date_started`, `date_ended`, `active`) VALUES (1, 'James', 'King', '2024-08-10', NULL, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `event`
-- -----------------------------------------------------
START TRANSACTION;
USE `tizidb`;
INSERT INTO `event` (`event_id`, `member_id`, `workout_id`, `event_date`, `comment`) VALUES (1, 1, 4, '2024-03-12T11:30:00', 'Enjoyed Running');

COMMIT;

