-- 企業情報
CREATE TABLE `company` (
  `company_id` bigint NOT NULL AUTO_INCREMENT,
  `company_code` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `biz_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `biz_place` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`company_id`),
  UNIQUE KEY `UK_i2jcjcejgnwuafxofwmgosd13` (`company_code`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;