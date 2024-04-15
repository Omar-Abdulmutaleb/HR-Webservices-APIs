-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema hr
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema hr
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `hr` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `hr` ;

-- -----------------------------------------------------
-- Table `hr`.`department`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hr`.`department` (
  `department_id` INT NOT NULL AUTO_INCREMENT,
  `department_name` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`department_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `hr`.`jobtitle`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hr`.`jobtitle` (
  `job_title_id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`job_title_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `hr`.`employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hr`.`employee` (
  `employee_id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(50) NULL DEFAULT NULL,
  `last_name` VARCHAR(50) NULL DEFAULT NULL,
  `date_of_birth` DATE NULL DEFAULT NULL,
  `gender` ENUM('Male', 'Female') NULL DEFAULT NULL,
  `email` VARCHAR(100) NULL DEFAULT NULL,
  `phone` VARCHAR(20) NULL DEFAULT NULL,
  `address` VARCHAR(255) NULL DEFAULT NULL,
  `hire_date` DATE NULL DEFAULT NULL,
  `department_id` INT NULL DEFAULT NULL,
  `job_title_id` INT NULL DEFAULT NULL,
  `manager_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`employee_id`),
  INDEX `department_id` (`department_id` ASC) VISIBLE,
  INDEX `job_title_id` (`job_title_id` ASC) VISIBLE,
  INDEX `manager_id` (`manager_id` ASC) VISIBLE,
  CONSTRAINT `employee_ibfk_1`
    FOREIGN KEY (`department_id`)
    REFERENCES `hr`.`department` (`department_id`),
  CONSTRAINT `employee_ibfk_2`
    FOREIGN KEY (`job_title_id`)
    REFERENCES `hr`.`jobtitle` (`job_title_id`),
  CONSTRAINT `employee_ibfk_3`
    FOREIGN KEY (`manager_id`)
    REFERENCES `hr`.`employee` (`employee_id`),
  CONSTRAINT `FK_jobTitle`
    FOREIGN KEY (`job_title_id`)
    REFERENCES `hr`.`jobtitle` (`job_title_id`)
    ON DELETE SET NULL)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `hr`.`attendancerecord`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hr`.`attendancerecord` (
  `attendance_id` INT NOT NULL AUTO_INCREMENT,
  `employee_id` INT NULL DEFAULT NULL,
  `time_in` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `time_out` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`attendance_id`),
  INDEX `employee_id` (`employee_id` ASC) VISIBLE,
  CONSTRAINT `attendancerecord_ibfk_1`
    FOREIGN KEY (`employee_id`)
    REFERENCES `hr`.`employee` (`employee_id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `hr`.`performancereview`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hr`.`performancereview` (
  `review_id` INT NOT NULL AUTO_INCREMENT,
  `employee_id` INT NULL DEFAULT NULL,
  `review_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `reviewer` INT NULL DEFAULT NULL,
  `rating` INT NULL DEFAULT NULL,
  PRIMARY KEY (`review_id`),
  INDEX `employee_id` (`employee_id` ASC) VISIBLE,
  INDEX `fk_reviewer_employee` (`reviewer` ASC) VISIBLE,
  CONSTRAINT `fk_reviewer_employee`
    FOREIGN KEY (`reviewer`)
    REFERENCES `hr`.`employee` (`employee_id`),
  CONSTRAINT `performancereview_ibfk_1`
    FOREIGN KEY (`employee_id`)
    REFERENCES `hr`.`employee` (`employee_id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `hr`.`salary`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hr`.`salary` (
  `salary_id` INT NOT NULL AUTO_INCREMENT,
  `employee_id` INT NOT NULL,
  `deductions` DECIMAL(10,2) NULL DEFAULT NULL,
  `gross_salary` DECIMAL(10,2) NULL DEFAULT NULL,
  `net_salary` DECIMAL(10,2) NULL DEFAULT NULL,
  PRIMARY KEY (`salary_id`),
  UNIQUE INDEX `employee_id_UNIQUE` (`employee_id` ASC) VISIBLE,
  INDEX `employee_id` (`employee_id` ASC) VISIBLE,
  CONSTRAINT `salary_ibfk_1`
    FOREIGN KEY (`employee_id`)
    REFERENCES `hr`.`employee` (`employee_id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 13
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `hr`.`trainingsession`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hr`.`trainingsession` (
  `session_id` INT NOT NULL AUTO_INCREMENT,
  `training_name` VARCHAR(100) NULL DEFAULT NULL,
  `employee_id` INT NULL DEFAULT NULL,
  `start_date` TIMESTAMP NULL DEFAULT NULL,
  `end_date` TIMESTAMP NULL DEFAULT NULL,
  `location` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`session_id`),
  UNIQUE INDEX `training_name_UNIQUE` (`training_name` ASC) VISIBLE,
  INDEX `TrainingFK_idx` (`employee_id` ASC) VISIBLE,
  CONSTRAINT `TrainingFK`
    FOREIGN KEY (`employee_id`)
    REFERENCES `hr`.`employee` (`employee_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `hr`.`training_employee_session`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hr`.`training_employee_session` (
  `training_id` INT NOT NULL,
  `employee_id` INT NOT NULL,
  PRIMARY KEY (`training_id`, `employee_id`),
  INDEX `EmployeeSessionFK_idx` (`employee_id` ASC) VISIBLE,
  CONSTRAINT `EmployeeSessionFK`
    FOREIGN KEY (`employee_id`)
    REFERENCES `hr`.`employee` (`employee_id`),
  CONSTRAINT `TrainingSessionFK`
    FOREIGN KEY (`training_id`)
    REFERENCES `hr`.`trainingsession` (`session_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
