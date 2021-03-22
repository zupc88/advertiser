

CREATE TABLE advertiser (
    id INT PRIMARY KEY auto_increment,
    name VARCHAR,
    contactName VARCHAR,
    creditLimit DECIMAL
);

INSERT INTO advertiser (name , contactName, creditLimit) VALUES ('My Company', 'Thiagu', '1000.00');