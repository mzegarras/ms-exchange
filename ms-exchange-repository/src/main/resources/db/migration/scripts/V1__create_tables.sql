

CREATE TABLE IF NOT EXISTS `exchange_rate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `origin` varchar(120) NULL,
  `destiny` varchar(120) NOT NULL,
  `rate` numeric(8,4)  NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY  (`id`),
  INDEX `exchange_rate_idx` (`origin` ASC)
);


insert into exchange_rate (origin,destiny,rate) values('USD','PEN',3);
insert into exchange_rate (origin,destiny,rate) values('USD','PON',2);