CREATE DATABASE IF NOT EXISTS park;
USE park;

CREATE TABLE IF NOT EXISTS
  territory
(
  id               INT(4) PRIMARY KEY NOT NULL,
  x                INT(4),
  y                INT(4),
  width            INT(4),
  height           INT(4)
);
CREATE TABLE IF NOT EXISTS
  attractions
(
  id               INT(4) PRIMARY KEY NOT NULL,
  name             VARCHAR(255),
  build_price      INT(4),
  time_to_repair   BIGINT(8),
  type             VARCHAR(30),
  visitors_love    TINYINT(1),
  ride_time        BIGINT(8),
  ticket_price     INT(4),
  territory_id     INT(4),
  FOREIGN KEY (territory_id) REFERENCES territory (id) ON DELETE RESTRICT ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS
  serviceBuildings
(
  id               INT(4) PRIMARY KEY NOT NULL,
  name             VARCHAR(255),
  build_price      INT(4),
  time_to_repair   BIGINT(8),
  service          VARCHAR(255),
  price            INT(4),
  territory_id     INT(4),
  FOREIGN KEY (territory_id) REFERENCES territory (id) ON DELETE RESTRICT ON UPDATE CASCADE
);