USE MDAfondation

IF (EXISTS (SELECT * 
                 FROM INFORMATION_SCHEMA.TABLES 
                 WHERE TABLE_SCHEMA = 'dbo' 
                 AND  TABLE_NAME = 'TTrainer_Evaluation'))
BEGIN
    DELETE FROM TTrainer_Evaluation
	DBCC CHECKIDENT ('[TTrainer_Evaluation]', RESEED, 1)
END;

IF (EXISTS (SELECT * 
                 FROM INFORMATION_SCHEMA.TABLES 
                 WHERE TABLE_SCHEMA = 'dbo' 
                 AND  TABLE_NAME = 'Topics_Combo'))
BEGIN
    DELETE FROM Topics_Combo
	DBCC CHECKIDENT ('[Topics_Combo]', RESEED, 1)
END;

IF (EXISTS (SELECT * 
                 FROM INFORMATION_SCHEMA.TABLES 
                 WHERE TABLE_SCHEMA = 'dbo' 
                 AND  TABLE_NAME = 'Topics_Covered'))
BEGIN
    DELETE FROM Topics_Covered
	DBCC CHECKIDENT ('[Topics_Covered]', RESEED, 1)
END;

IF (EXISTS (SELECT * 
                 FROM INFORMATION_SCHEMA.TABLES 
                 WHERE TABLE_SCHEMA = 'dbo' 
                 AND  TABLE_NAME = 'CTPcombo'))
BEGIN
    DELETE FROM CTPcombo
	DBCC CHECKIDENT ('[CTPcombo]', RESEED, 1)
END;

IF (EXISTS (SELECT * 
                 FROM INFORMATION_SCHEMA.TABLES 
                 WHERE TABLE_SCHEMA = 'dbo' 
                 AND  TABLE_NAME = 'Participant_Team'))
BEGIN
    DELETE FROM Participant_Team
	DBCC CHECKIDENT ('[Participant_Team]', RESEED, 1)
END;


IF (EXISTS (SELECT * 
                 FROM INFORMATION_SCHEMA.TABLES 
                 WHERE TABLE_SCHEMA = 'dbo' 
                 AND  TABLE_NAME = 'ParticipatingCompanyMember'))
BEGIN
    DELETE FROM ParticipatingCompanyMember
	DBCC CHECKIDENT ('[ParticipatingCompanyMember]', RESEED, 1)
END;

IF (EXISTS (SELECT * 
                 FROM INFORMATION_SCHEMA.TABLES 
                 WHERE TABLE_SCHEMA = 'dbo' 
                 AND  TABLE_NAME = 'Participant'))
BEGIN
    DELETE FROM Participant
	DBCC CHECKIDENT ('[Participant]', RESEED, 999)
END;

IF (EXISTS (SELECT * 
                 FROM INFORMATION_SCHEMA.TABLES 
                 WHERE TABLE_SCHEMA = 'dbo' 
                 AND  TABLE_NAME = 'Company'))
BEGIN
    DELETE FROM Company
	DBCC CHECKIDENT ('[Company]', RESEED, 1)
END;



IF (EXISTS (SELECT * 
                 FROM INFORMATION_SCHEMA.TABLES 
                 WHERE TABLE_SCHEMA = 'dbo' 
                 AND  TABLE_NAME = 'Training_Process'))
BEGIN
    DELETE FROM Training_Process
END;

IF (EXISTS (SELECT * 
                 FROM INFORMATION_SCHEMA.TABLES 
                 WHERE TABLE_SCHEMA = 'dbo' 
                 AND  TABLE_NAME = 'Training_Project'))
BEGIN
    DELETE FROM Training_Project
END;



IF (EXISTS (SELECT * 
                 FROM INFORMATION_SCHEMA.TABLES 
                 WHERE TABLE_SCHEMA = 'dbo' 
                 AND  TABLE_NAME = 'Training'))
BEGIN
    DELETE FROM Training
	DBCC CHECKIDENT ('[Training]', RESEED, 1)
END;

IF (EXISTS (SELECT * 
                 FROM INFORMATION_SCHEMA.TABLES 
                 WHERE TABLE_SCHEMA = 'dbo' 
                 AND  TABLE_NAME = 'Project'))
BEGIN
    DELETE FROM Project
	DBCC CHECKIDENT ('[Project]', RESEED, 1)
END;

IF (EXISTS (SELECT * 
                 FROM INFORMATION_SCHEMA.TABLES 
                 WHERE TABLE_SCHEMA = 'dbo' 
                 AND  TABLE_NAME = 'Client'))
BEGIN
    DELETE FROM Client
	DBCC CHECKIDENT ('[Client]', RESEED, 1)
END;

IF (EXISTS (SELECT * 
                 FROM INFORMATION_SCHEMA.TABLES 
                 WHERE TABLE_SCHEMA = 'dbo' 
                 AND  TABLE_NAME = 'Team'))
BEGIN
    DELETE FROM Team
	DBCC CHECKIDENT ('[Team]', RESEED, 1)
END;

IF (EXISTS (SELECT * 
                 FROM INFORMATION_SCHEMA.TABLES 
                 WHERE TABLE_SCHEMA = 'dbo' 
                 AND  TABLE_NAME = 'TrainerTeamCombo'))
BEGIN
    DELETE FROM TrainerTeamCombo
	DBCC CHECKIDENT ('[TrainerTeamCombo]', RESEED, 1)
END;

IF (EXISTS (SELECT * 
                 FROM INFORMATION_SCHEMA.TABLES 
                 WHERE TABLE_SCHEMA = 'dbo' 
                 AND  TABLE_NAME = 'TrainersTeam'))
BEGIN
    DELETE FROM TrainersTeam
	DBCC CHECKIDENT ('[TrainersTeam]', RESEED, 1)
END;


IF (EXISTS (SELECT * 
                 FROM INFORMATION_SCHEMA.TABLES 
                 WHERE TABLE_SCHEMA = 'dbo' 
                 AND  TABLE_NAME = 'Trainer'))
BEGIN
    DELETE FROM Trainer
END;

IF (EXISTS (SELECT * 
                 FROM INFORMATION_SCHEMA.TABLES 
                 WHERE TABLE_SCHEMA = 'dbo' 
                 AND  TABLE_NAME = 'TrainerContact'))
BEGIN
    DELETE FROM TrainerContact
	DBCC CHECKIDENT ('[TrainerContact]', RESEED, 1)
END;

/*
IF (EXISTS (SELECT * 
                 FROM INFORMATION_SCHEMA.TABLES 
                 WHERE TABLE_SCHEMA = 'dbo' 
                 AND  TABLE_NAME = 'Users'))
BEGIN
    DELETE FROM Users
END;
*/
IF (EXISTS (SELECT * 
                 FROM INFORMATION_SCHEMA.TABLES 
                 WHERE TABLE_SCHEMA = 'dbo' 
                 AND  TABLE_NAME = 'Logs'))
BEGIN
    DELETE FROM Logs;
	DBCC CHECKIDENT ('[Logs]', RESEED, 1);
END;

