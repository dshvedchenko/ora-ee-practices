create TABLE GANRE (
	ID integer auto_increment,
	TITLE varchar(64),
	PRIMARY KEY (ID)
);

ALTER TABLE AUDIOS ADD column GANRE_ID integer REFERENCES GANRE(ID) ON DELETE CASCADE;

create table GROUPS (
	ID integer auto_increment,
	TITLE varchar(64),
	PRIMARY KEY (ID)
);

ALTER TABLE AUDIOS ADD column GROUP_ID integer REFERENCES GROUPS(ID) ON DELETE CASCADE;
