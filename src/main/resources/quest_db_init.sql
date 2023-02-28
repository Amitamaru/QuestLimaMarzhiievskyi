-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE =
        'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema quest_db
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `quest_db`;

-- -----------------------------------------------------
-- Schema quest_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `quest_db` DEFAULT CHARACTER SET utf8;
USE `quest_db`;

-- -----------------------------------------------------
-- Table `quest_db`.`t_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quest_db`.`t_user`
(
    `id`       INT         NOT NULL AUTO_INCREMENT,
    `login`    VARCHAR(45) NOT NULL,
    `password` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `quest_db`.`quest`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quest_db`.`quest`
(
    `id`                   INT          NOT NULL AUTO_INCREMENT,
    `name`                 VARCHAR(45)  NULL,
    `text`                 VARCHAR(264) NULL,
    `starting_question_id` INT          NOT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `quest_db`.`game_state`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quest_db`.`game_state`
(
    `value` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`value`),
    UNIQUE INDEX `value_UNIQUE` (`value` ASC) VISIBLE
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `quest_db`.`question`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quest_db`.`question`
(
    `id`         INT          NOT NULL AUTO_INCREMENT,
    `text`       VARCHAR(264) NULL,
    `game_state` VARCHAR(45)  NOT NULL,
    `quest_id`   INT          NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_question_game_state_idx` (`game_state` ASC) VISIBLE,
    INDEX `fk_question_quest1_idx` (`quest_id` ASC) VISIBLE,
    CONSTRAINT `fk_question_game_state`
        FOREIGN KEY (`game_state`)
            REFERENCES `quest_db`.`game_state` (`value`)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT,
    CONSTRAINT `fk_question_quest1`
        FOREIGN KEY (`quest_id`)
            REFERENCES `quest_db`.`quest` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `quest_db`.`answer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quest_db`.`answer`
(
    `id`               INT         NOT NULL AUTO_INCREMENT,
    `text`             VARCHAR(45) NULL,
    `question_id`      INT         NOT NULL,
    `next_question_id` INT         NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_answer_question1_idx` (`question_id` ASC) VISIBLE,
    CONSTRAINT `fk_answer_question1`
        FOREIGN KEY (`question_id`)
            REFERENCES `quest_db`.`question` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `quest_db`.`game_session`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quest_db`.`game_session`
(
    `id`                  INT         NOT NULL,
    `t_user_id`           INT         NOT NULL,
    `quest_id`            INT         NOT NULL,
    `game_state`          VARCHAR(45) NOT NULL,
    `current_question_id` INT         NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_game_session_t_user1_idx` (`t_user_id` ASC) VISIBLE,
    INDEX `fk_game_session_quest1_idx` (`quest_id` ASC) VISIBLE,
    INDEX `fk_game_session_game_state1_idx` (`game_state` ASC) VISIBLE,
    CONSTRAINT `fk_game_session_t_user1`
        FOREIGN KEY (`t_user_id`)
            REFERENCES `quest_db`.`t_user` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `fk_game_session_quest1`
        FOREIGN KEY (`quest_id`)
            REFERENCES `quest_db`.`quest` (`id`)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT,
    CONSTRAINT `fk_game_session_game_state1`
        FOREIGN KEY (`game_state`)
            REFERENCES `quest_db`.`game_state` (`value`)
            ON DELETE CASCADE
            ON UPDATE RESTRICT
)
    ENGINE = InnoDB;


SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;
