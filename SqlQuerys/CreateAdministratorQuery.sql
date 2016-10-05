use master;

BEGIN TRANSACTION
--krijon Checkerin user--
	USE MDAfondation;
	CREATE ROLE Public_Checker AUTHORIZATION dbo;
	USE MDAfondation;
	CREATE LOGIN Checker WITH password='12345',
	DEFAULT_DATABASE = MDAfondation, 
	DEFAULT_LANGUAGE =english,CHECK_EXPIRATION=OFF;
	USE MDAfondation;
	CREATE USER Checker FOR LOGIN Checker;
	USE MDAfondation
	ALTER ROLE Public_Checker 
	ADD MEMBER Checker;
	USE MDAfondation
	GRANT SELECT ON [dbo].[Test_Admin] TO Public_Checker;
--krijon rolin Administratorit--
	USE MDAfondation;
	CREATE ROLE Administrator AUTHORIZATION dbo;

--Cakton t'drejtat e Adminit--
USE MDAfondation;
GRANT SELECT,INSERT,UPDATE ON SCHEMA::dbo TO Administrator;
USE MDAfondation;
GRANT DELETE ON [dbo].[Participant_Team] TO Administrator;
USE MDAfondation;
GRANT DELETE ON [dbo].[Users] TO Administrator;
USE MDAfondation;
GRANT DELETE ON [dbo].[Logs] TO Administrator;

USE MDAfondation;
CREATE ROLE Stafi AUTHORIZATION Administrator;

USE MDAfondation
GRANT SELECT,INSERT,UPDATE ON SCHEMA::dbo TO stafi;
USE MDAfondation
GRANT DELETE ON [dbo].[Participant_Team] TO stafi;

DECLARE @UserExist BIT = 0 
DECLARE @RoleName varchar(50) = 'Administrator'


--- Set Bit to 1 if any user is member of Role 'db_datareader'
SELECT  @UserExist = 1
FROM    sys.database_role_members
WHERE   IS_ROLEMEMBER( @RoleName , USER_NAME(role_principal_id)) = 1


--- Get Output 
SELECT  @UserExist as 'Numri i Userave'

COMMIT;


/* fshirja e nje logini forced 
DECLARE @loginNameToDrop sysname
SET @loginNameToDrop = 'Ardit';

DECLARE sessionsToKill CURSOR FAST_FORWARD FOR
    SELECT session_id
    FROM sys.dm_exec_sessions
    WHERE login_name = @loginNameToDrop
OPEN sessionsToKill

DECLARE @sessionId INT
DECLARE @statement NVARCHAR(200)

FETCH NEXT FROM sessionsToKill INTO @sessionId

WHILE @@FETCH_STATUS = 0
BEGIN
    PRINT 'Killing session ' + CAST(@sessionId AS NVARCHAR(20)) + ' for login ' + @loginNameToDrop

    SET @statement = 'KILL ' + CAST(@sessionId AS NVARCHAR(20))
    EXEC sp_executesql @statement

    FETCH NEXT FROM sessionsToKill INTO @sessionId
END

CLOSE sessionsToKill
DEALLOCATE sessionsToKill

PRINT 'Dropping login ' + @loginNameToDrop
SET @statement = 'DROP LOGIN [' + @loginNameToDrop + ']'
EXEC sp_executesql @statement
*/
