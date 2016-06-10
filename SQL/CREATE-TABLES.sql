-- Create UserData

CREATE TABLE UserData (
	userId INT PRIMARY KEY auto_increment,
	userName VARCHAR(32)
);

CREATE TABLE UserProfile (
	userId INT references UserData(userId),
	highestScore INT, 
	highestRank INT,
	houseId INT references House(houseId),
	totalCatCoins INT
);

CREATE TABLE HighScores (
	userId INT references UserData(userId),
	userName VARCHAR(32) references UserData(userName),
	rank INT,
	score INT
);

CREATE TABLE Shop (
	itemId INT PRIMARY KEY auto_increment,
	decorationName VARCHAR(32),
	price INT,
	category VARCHAR(32)	
);

CREATE TABLE Cats (
	userId INT references UserData(userId),
	userName VARCHAR(32) references UserData(userName),
	catName VARCHAR(32)
);

CREATE TABLE Inventory (
	userId INT references UserData(userId),
	userName VARCHAR(32) references UserData(userName),
	itemId INT references Shop(itemId),
	inUse BOOLEAN
)