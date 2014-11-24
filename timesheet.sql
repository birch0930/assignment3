CREATE DATABASE TIMESHEET;

GRANT ALL PRIVILEGES ON TIMESHEET. TO TSHIRTS@localhost IDENTIFIED BY 'TSHIRTS' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON TIMESHEET. TO TSHIRTS@% IDENTIFIED BY 'TSHIRTS' WITH GRANT OPTION;
DROP TABLE TIMESHEET.TIMESHEETS;
DROP TABLE TIMESHEET.EMPLOYEE;
DROP TABLE TIMESHEET.CREDENTIALS;


CREATE TABLE TIMESHEET.CREDENTIALS
( 
	USER_NAME 	VARCHAR(30) 	NOT NULL, 
	PASSWORD 	VARCHAR(30) 	NOT NULL,	
	PRIMARY KEY (USER_NAME)
);

CREATE TABLE TIMESHEET.EMPLOYEE
(
	EMPLOYEE_ID 	INTEGER 		NOT NULL	AUTO_INCREMENT, 
	EMPLOYEE_NAME 	VARCHAR(30) 	NOT NULL,
	USER_NAME 		VARCHAR(30) 	NOT NULL, 
	AUTHORITY 		INTEGER 		NOT NULL,
	PRIMARY KEY (EMPLOYEE_ID)
);

CREATE TABLE TIMESHEET.TIMESHEETS
(
	TIMESHEET_ID INTEGER NOT NULL AUTO_INCREMENT,
	EMPLOYEE_ID INTEGER NOT NULL, 
	WEEKNO INTEGER NOT NULL,
	PROJECT_ID INTEGER NOT NULL, 
	WP VARCHAR(6) NOT NULL, 
	TOTAL DECIMAL(6,1), 
	SAT DECIMAL(6,1), 
	SUN DECIMAL(6,1), 
	MON DECIMAL(6,1), 
	TUE DECIMAL(6,1), 
	WED DECIMAL(6,1), 
	THU DECIMAL(6,1), 
	FRI DECIMAL(6,1), 
	NOTES VARCHAR(30),
	PRIMARY KEY(TIMESHEET_ID),
	FOREIGN KEY(EMPLOYEE_ID) REFERENCES TIMESHEET.EMPLOYEE(EMPLOYEE_ID) 
);

INSERT INTO TIMESHEET.CREDENTIALS VALUES( 'super','super' );
INSERT INTO TIMESHEET.CREDENTIALS VALUES( 'u1','aaa' );
INSERT INTO TIMESHEET.CREDENTIALS VALUES( 'u2','bbb' );
INSERT INTO TIMESHEET.CREDENTIALS VALUES( 'u3','ccc' );
INSERT INTO TIMESHEET.CREDENTIALS VALUES( 'u4','ddd' );
INSERT INTO TIMESHEET.CREDENTIALS VALUES( 'u5','eee' );

INSERT INTO TIMESHEET.EMPLOYEE VALUES( '','Bruce','super',0 );
INSERT INTO TIMESHEET.EMPLOYEE VALUES( '','Huanan','u1',1 );
INSERT INTO TIMESHEET.EMPLOYEE VALUES( '','Fred','u2',1 );
INSERT INTO TIMESHEET.EMPLOYEE VALUES( '','Jin','u3',1 );
INSERT INTO TIMESHEET.EMPLOYEE VALUES( '','Student','u4',1 );
INSERT INTO TIMESHEET.EMPLOYEE VALUES( '','Bloggs','u5',1 );

INSERT INTO TIMESHEET.TIMESHEETS VALUES( '',1,46,132,'AA123','4.0','','','','4','','','','' );
INSERT INTO TIMESHEET.TIMESHEETS VALUES( '',1,46,132,'AB112','2.0','','','','2','','','','Requested by Tracy' );
INSERT INTO TIMESHEET.TIMESHEETS VALUES( '',1,46,132,'AA221','8.0','','','8.0','','','','','' );
INSERT INTO TIMESHEET.TIMESHEETS VALUES( '',1,46,1205,'12110','0.0','','','','','','','','' );
INSERT INTO TIMESHEET.TIMESHEETS VALUES( '',1,46,13000,'13000','3.5','','','','','3.5','','','' );
INSERT INTO TIMESHEET.TIMESHEETS VALUES( '',1,46,12450,'12450','10.0','','','','2.0','3.0','3.0','2.0','' );
INSERT INTO TIMESHEET.TIMESHEETS VALUES( '',1,46,32000,'32000','9.0','','','','','5.0','','4.0','' );
INSERT INTO TIMESHEET.TIMESHEETS VALUES( '',1,46,45010,'45010','9.0','','','','','','','4.0','5.0' );