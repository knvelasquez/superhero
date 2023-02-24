-- -----------------------------------------------------
-- Database master01 Production environment
-- -----------------------------------------------------

CREATE SCHEMA IF NOT EXISTS `master01` AUTHORIZATION sa;

USE `master01`;

-- -----------------------------------------------------
-- Table `privilege_user`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `user_privilege` (
            `id` INT NOT NULL AUTO_INCREMENT,
            `id_user` INT NOT NULL,
            `id_privilege` VARCHAR(20) NOT NULL,
PRIMARY KEY (`id`));