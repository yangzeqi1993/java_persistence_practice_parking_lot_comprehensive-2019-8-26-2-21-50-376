CREATE TABLE employee (
  id INTEGER PRIMARY KEY,
  name VARCHAR(64) NOT NULL,
  age   int(4) NOT NULL
);

CREATE TABLE `parkinglot` (
  `id` INTEGER PRIMARY KEY,
  `capacity` int(11) NOT NULL,
  `availablePositionCount` int(11) NOT NULL,
  `parkingBoyId` INTEGER NOT NULL,
  --FOREIGN KEY(parkingBoyId) REFERENCES employee(id)
);

-- INSERT INTO `employee` VALUES (1, 'yang', 32);
-- INSERT INTO `employee` VALUES (2, 'kathy', 22);
--
-- INSERT INTO `parkinglot` VALUES (1 ,5 ,5 ,1);
-- INSERT INTO `parkinglot` VALUES (2 ,8 ,8 ,2);

