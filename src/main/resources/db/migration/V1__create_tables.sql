CREATE TABLE IF NOT EXISTS `seekdb`.`candidates` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `gender` VARCHAR(45) NULL,
  `salary_expected` DOUBLE NULL,
  `nationality` VARCHAR(45) NULL,
  `age` INT NULL,
  PRIMARY KEY (`id`));