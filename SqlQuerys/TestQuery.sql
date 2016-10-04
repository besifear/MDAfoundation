USE MDAfondation;




BEGIN TRAN
DELETE FROM USERS WHERE Username NOT LIKE 'Qazim@s'
COMMIT;
SELECT * FROM Users

DROP user administratori
DROP login Ardit@seemda
DROP USER Ardit@seemda

USE MDAfondation;
CREATE LOGIN Besnik WITH password= '11',DEFAULT_DATABASE = MDAfondation,DEFAULT_LANGUAGE = english,CHECK_EXPIRATION = OFF;
USE MDAfondation;
CREATE USER Besnik FOR LOGIN Besnik;
USE MDAfondation;
ALTER ROLE  Stafi 
ADD MEMBER Besnik;
SELECT 1;





BEGIN TRAN
SELECT * FROM Users;
INSERT INTO USERS VALUES('Qazim@s',HASHBYTES('SHA2_512','morqiqazimi'),'morqi','Qazimi','Minister','Drejtsise','Active');
SELECT * FROM Users;
COMMIT;

SELECT * FROM TTrainer_Evaluation



CREATE LOGIN Qazim@s WITH password= 'qazimi',DEFAULT_DATABASE = MDAfondation
	,DEFAULT_LANGUAGE = english
	,CHECK_EXPIRATION = OFF;
COMMIT

CREATE USER Qazim@s FOR LOGIN Qazim@s


DELETE FROM Logs
DELETE FROM Users WHERE users.Username='Qazim@s'


DROP LOGIN Mentor

SELECT * FROM Users

SELECT CASE WHEN EXISTS (SELECT * FROM Users u
WHERE u.Username = 'Ardit' AND u.statusi='Active') then 1 ELSE 0 END



CREATE LOGIN besniku WITH PASSWORD='12345' MUST_CHANGE,DEFAULT_DATABASE = MDAfondation
    ,DEFAULT_LANGUAGE = english
    ,CHECK_EXPIRATION = ON
GO

USE MDAfondation
GO
CREATE USER besnik@seemda FOR LOGIN besniku
GO

USE MDAfondation
GO
sp_addrolemember 'stafi', 'besnik@seemda'  
GO



