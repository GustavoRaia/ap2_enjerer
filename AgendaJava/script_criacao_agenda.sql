DROP DATABASE IF EXISTS mydb;

-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8mb3 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`aluno`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`aluno` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(150) NOT NULL,
  `cpf` CHAR(11) NOT NULL,
  `telefone` VARCHAR(20) NOT NULL,
  `data_nascimento` DATE NOT NULL,
  `idade` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`professor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`professor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(150) NOT NULL,
  `cpf` CHAR(11) NOT NULL,
  `data_nascimento` DATE NOT NULL,
  `idade` INT NOT NULL,
  `salario` FLOAT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`aula`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`aula` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_aluno` INT NOT NULL,
  `id_professor` INT NOT NULL,
  `tipo` VARCHAR(45) NOT NULL,
  `dia_aula` DATE NOT NULL,
  `horario_inicio` TIME NOT NULL,
  `horario_fim` TIME NOT NULL,
  PRIMARY KEY (`id`, `id_aluno`, `id_professor`),
  INDEX `Fk_Aula_Aluno_idx` (`id_aluno` ASC) VISIBLE,
  INDEX `Fk_Aula_Professor_idx` (`id_professor` ASC) VISIBLE,
  CONSTRAINT `Fk_Aula_Aluno`
    FOREIGN KEY (`id_aluno`)
    REFERENCES `mydb`.`aluno` (`id`),
  CONSTRAINT `Fk_Aula_Professor`
    FOREIGN KEY (`id_professor`)
    REFERENCES `mydb`.`professor` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`ficha_medica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`ficha_medica` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `altura` FLOAT NOT NULL,
  `peso` FLOAT NOT NULL,
  `imc` FLOAT NOT NULL,
  `tipo_sanguineo` VARCHAR(3) NOT NULL,
  `contato_emergencia` VARCHAR(20) NOT NULL,
  `data_exame` DATE NOT NULL,
  `id_aluno` INT NOT NULL,
  PRIMARY KEY (`id`, `id_aluno`),
  UNIQUE INDEX `id_aluno_UNIQUE` (`id_aluno` ASC) VISIBLE,
  INDEX `Fk_Ficha_Aluno_idx` (`id_aluno` ASC) VISIBLE,
  CONSTRAINT `Fk_Ficha_Aluno`
    FOREIGN KEY (`id_aluno`)
    REFERENCES `mydb`.`aluno` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`telefone_professor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`telefone_professor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_professor` INT NOT NULL,
  `tipo` INT NOT NULL,
  `codigo_pais` INT NOT NULL,
  `codigo_area` INT NOT NULL,
  `numero` INT NOT NULL,
  PRIMARY KEY (`id`, `id_professor`),
  INDEX `Fk_Telefone_Professor_idx` (`id_professor` ASC) VISIBLE,
  CONSTRAINT `Fk_Telefone_Professor`
    FOREIGN KEY (`id_professor`)
    REFERENCES `mydb`.`professor` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;