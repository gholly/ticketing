CREATE TABLE `ticketing_order`
(
    `id`               bigint(20) NOT NULL AUTO_INCREMENT,
    `order_number`         varchar(50),
    `flight_number`         varchar(50),
    `status`           varchar(16),
    `created_by`       varchar(20),
    `depart_time` datetime,
    `created_at`       datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`       datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_order_no` (`order_no`)
);
