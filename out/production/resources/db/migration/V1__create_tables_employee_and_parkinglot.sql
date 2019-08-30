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
--   FOREIGN KEY(parkingBoyId) REFERENCES employee(id)
);
