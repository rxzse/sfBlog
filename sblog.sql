CREATE TABLE Auth (
    id int NOT NULL DEFAULT 0,
    password varchar(50) NOT NULL,
    createTime DATE,
	modifyTime DATE
    CONSTRAINT PK_Auth PRIMARY KEY (ID)
);

CREATE TABLE Category (
    id int NOT NULL IDENTITY(1,1),
    name varchar(255) NOT NULL,
    alias varchar(255) NOT NULL unique,
	[sequence] int not null,
	createTime DATE not null,
	modifyTime DATE not null
    CONSTRAINT PK_Category PRIMARY KEY (ID)
);

CREATE TABLE Post (
    id int NOT NULL IDENTITY(1,1),
	category int not null,
    title varchar(255) NOT NULL,
    alias varchar(255) NOT NULL unique,
	html text not null,
	markdown text not null,
	isDraft bit not null,
	isActive bit not null,
	createTime DATE not null,
	modifyTime DATE not null,
	publishTime DATE not null
    CONSTRAINT PK_Post PRIMARY KEY (ID)
	FOREIGN KEY (category) REFERENCES Category(id)
);