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
-- Table `activity`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `activity` ;

CREATE TABLE IF NOT EXISTS `activity` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(2000) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `member`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `member` ;

CREATE TABLE IF NOT EXISTS `member` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `date_started` DATETIME NOT NULL,
  `date_ended` DATETIME NULL,
  `active` TINYINT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `workout`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `workout` ;

CREATE TABLE IF NOT EXISTS `workout` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `workout_date` DATETIME NOT NULL,
  `comment` VARCHAR(2000) NULL,
  `duration` INT NULL,
  `activity_id` INT NOT NULL,
  `member_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_workout_activity_idx` (`activity_id` ASC) VISIBLE,
  INDEX `fk_workout_member1_idx` (`member_id` ASC) VISIBLE,
  CONSTRAINT `fk_workout_activity`
    FOREIGN KEY (`activity_id`)
    REFERENCES `activity` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_workout_member1`
    FOREIGN KEY (`member_id`)
    REFERENCES `member` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
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
-- Data for table `activity`
-- -----------------------------------------------------
START TRANSACTION;
USE `tizidb`;
INSERT INTO `activity` (`id`, `name`, `description`) VALUES (1, 'track', 'track');
INSERT INTO `activity` (`id`, `name`, `description`) VALUES (2, 'core', 'core');
INSERT INTO `activity` (`id`, `name`, `description`) VALUES (3 , 'aqua', 'swim');
INSERT INTO `activity` (`id`, `name`, `description`) VALUES (4, 'cardio', 'cardio');
INSERT INTO `activity` (`id`, `name`, `description`) VALUES (5, 'cycle', 'cycle');
INSERT INTO `activity` (`id`, `name`, `description`) VALUES (6, 'hiit', 'hiit');

COMMIT;


-- -----------------------------------------------------
-- Data for table `member`
-- -----------------------------------------------------
START TRANSACTION;
USE `tizidb`;
INSERT INTO `member` (`id`, `first_name`, `last_name`, `date_started`, `date_ended`, `active`) VALUES (1, 'James', 'King', '2024-08-10', NULL, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `workout`
-- -----------------------------------------------------
START TRANSACTION;
USE `tizidb`;
INSERT INTO `workout` (`id`, `workout_date`, `comment`, `duration`, `activity_id`, `member_id`) VALUES (1, '2024-03-12T11:30:00', 'Enjoyed Running', 30, 1, 1);

COMMIT;

