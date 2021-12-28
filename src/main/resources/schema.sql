DROP TABLE IF EXISTS Users;

CREATE TABLE Users (
                     id INT AUTO_INCREMENT  PRIMARY KEY,
                     email VARCHAR(250) NOT NULL,
                     enabled BOOLEAN NOT NULL,
                     first_name VARCHAR(250) NOT NULL,
                     last_name VARCHAR(250) NOT NULL,
                     password VARCHAR(250) NOT NULL,
                     username VARCHAR(250) NOT NULL


);