CREATE TABLE employee (
  id INTEGER PRIMARY KEY,
  name VARCHAR(64) NOT NULL,
  age   int(4) NOT NULL
);

CREATE TABLE `parkinglot` (
  `parkinglot_id` INTEGER PRIMARY KEY,
  `parkinglot_capasity` int(11) NOT NULL,
  `parkinglot_available_position_count` int(11) NOT NULL,
  `parkingboy_id` INTEGER NOT NULL,
  FOREIGN KEY(parkingboy_id) REFERENCES employee(id)
);

INSERT INTO `employee` VALUES ('1', 'yang', '32');
INSERT INTO `employee` VALUES ('2', 'kathy', '22');

INSERT INTO `parkinglot` VALUES ('1', '5', '5', '1');
INSERT INTO `parkinglot` VALUES ('2', '8', '8', '2');

