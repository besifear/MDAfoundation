CREATE Database MDAfondation
GO
USE MDAfondation
GO

CREATE TABLE Participant(
	Participant_ID int IDENTITY(1000,1),
	ID_Number BigInt NOT NULL UNIQUE,
	Name varchar(50) NOT NULL ,
	Surname varchar(50) NOT NULL,
	Participant_Address varchar(50),
	City varchar (50),
	Participant_State varchar (50),
	Email varchar (50),
	Phone varchar (50),
	Sex varchar(1) CHECK(Sex IN('M','F')) NOT NULL,
	ZipCode varchar(50),
	DOB date NOT NULL,
	Pozita varchar(50),
	Statusi varchar(40) NOT NULL DEFAULT 'Active',
	CONSTRAINT pk_Participant PRIMARY KEY (Participant_ID)
)
GO

CREATE TABLE Company (
	Company_ID int IDENTITY(1,1),
	Name varchar(200) UNIQUE,
	Companytype varchar (40),
	Statusi varchar(40) NOT NULL DEFAULT 'Active',
	CONSTRAINT pk_Company PRIMARY KEY (Company_ID)
)
GO

CREATE TABLE Client (
	Client_ID int IDENTITY (1,1),
	Emri varchar(200) NOT NULL UNIQUE,
	Statusi varchar(40) NOT NULL DEFAULT 'Active',
	CONSTRAINT pk_Klienti PRIMARY KEY (Client_ID),
)
GO

CREATE TABLE ParticipatingCompanyMember(
	PCM_ID int IDENTITY (1,1),
	Participant_ID int NOT NULL,
	Company_ID int NOT NULL,
	Statusi varchar(40) NOT NULL DEFAULT 'Active',
	CONSTRAINT pk_PCM PRIMARY KEY (PCM_ID), 
	CONSTRAINT fk_PCM_Participant FOREIGN KEY (Participant_ID) REFERENCES Participant(Participant_ID),
	CONSTRAINT fk_PCM_Company FOREIGN KEY (Company_ID) REFERENCES Company(Company_ID),
	CONSTRAINT UNIQUE_PCM UNIQUE CLUSTERED
		(
			Participant_ID,
			Company_ID
		)
)
GO

CREATE TABLE Project (
	Project_ID int IDENTITY (1,1),
	Emri varchar(200) NOT NULL UNIQUE,
	Statusi varchar(40) NOT NULL DEFAULT 'Active',
	CONSTRAINT pk_Project PRIMARY KEY (Project_ID)
)
GO

CREATE TABLE Training (
	Training_ID int IDENTITY (1,1),
	TitleOfTraining_Albanian varchar(250)NOT NULL,
	TitleOfTraining_English varchar(250)NOT NULL,
	TitleOfTraining_Serbian varchar(250),
	Statusi varchar(40) NOT NULL DEFAULT 'Active',
	CONSTRAINT pk_Training PRIMARY KEY (Training_ID),
	CONSTRAINT Unique_trainingTitles UNIQUE 
		(
			TitleOfTraining_Albanian,
			TitleOfTraining_English,
			TitleOfTraining_Serbian
		)
)
GO

CREATE TABLE Training_Project (
	TP_ID int ,
	Training_ID int NOT NULL,
	Project_ID int NOT NULL,
	Statusi varchar(40) NOT NULL DEFAULT 'Active',
	CONSTRAINT pk_Training_Project PRIMARY KEY (TP_ID),
	CONSTRAINT fk_TP_Training FOREIGN KEY (Training_ID) REFERENCES Training(Training_ID),
	CONSTRAINT fk_TP_Project FOREIGN KEY (Project_ID) REFERENCES Project(Project_ID),
	CONSTRAINT UNIQUE_TP UNIQUE CLUSTERED
		(
			Training_ID,
			Project_ID
		)
)
GO

CREATE TABLE Trainer (
	Trainer_ID int UNIQUE,
	Name varchar(50) NOT NULL ,
	Surname varchar(50) NOT NULL,
	Statusi varchar(40) NOT NULL DEFAULT 'Active',
	CONSTRAINT pk_Trainer PRIMARY KEY (Trainer_ID)
)
GO

CREATE TABLE TrainerContact (
	Contact_ID int IDENTITY (1,1),
	Contact_Type varchar(50) CHECK (Contact_Type IN ('Email','Telefon','Address')) NOT NULL,
	Contact_Value varchar(50) NOT NULL,
	Trainer_ID int NOT NULL,
	Statusi varchar(40) NOT NULL DEFAULT 'Active',
	CONSTRAINT pk_TrainerContact PRIMARY KEY (Contact_ID),
	CONSTRAINT fk_TC_Trainer FOREIGN KEY (Trainer_ID) REFERENCES Trainer (Trainer_ID),
	CONSTRAINT UNIQUE_TrainerContact UNIQUE CLUSTERED
		(
			Contact_Type,
			Contact_Value
		)
)
GO


CREATE TABLE TrainersTeam(
	TrainersTeamID int IDENTITY(1,1),
	Statusi varchar(40) NOT NULL DEFAULT 'Active',
	CONSTRAINT pk_TrainersTeam PRIMARY KEY (TrainersTeamID)
)
GO

CREATE TABLE TrainerTeamCombo(
	TrainerTeamComboID int IDENTITY(1,1),
	Trainer_ID int NOT NULL,
	TrainersTeamID int NOT NULL,
	Statusi varchar(40) NOT NULL DEFAULT 'Active',
	CONSTRAINT pk_TrainingTeam PRIMARY KEY (TrainerTeamComboID),
	CONSTRAINT fk_TT_Trainer FOREIGN KEY (Trainer_ID) REFERENCES Trainer(Trainer_ID),
	CONSTRAINT fk_TT_TrainersTeamID FOREIGN KEY (TrainersTeamID) REFERENCES TrainersTeam(TrainersTeamID),
	CONSTRAINT UNIQUE_ttc UNIQUE CLUSTERED
		(
			Trainer_ID,
			TrainersTeamID
		)
) 
GO

CREATE TABLE Team
(
	Team_ID int IDENTITY (1,1),
	Statusi varchar(40) NOT NULL DEFAULT 'Active',
	CONSTRAINT pk_Team_Participants  PRIMARY KEY (Team_ID)
)
GO

CREATE TABLE Training_Process(
	TProcess_ID varchar(50) NOT NULL UNIQUE,
	Place varchar(200) NOT NULL,
	StartDate DATE NOT NULL,
	EndDate DATE NOT NULL,
	TrainersTeamID int,
	TrainingProjectID int NOT NULL,
	Klienti_ID int NOT NULL,
	Team_ID int,
	Statusi varchar(40) NOT NULL DEFAULT 'Active',
	CONSTRAINT pk_TPID PRIMARY KEY (TProcess_ID),
	CONSTRAINT fk_TPTTID FOREIGN KEY (TrainersTeamID) REFERENCES TrainersTeam(TrainersTeamID),
	CONSTRAINT fk_TPTPID FOREIGN KEY (TrainingProjectID) REFERENCES Training_Project(TP_ID),
	CONSTRAINT fk_TPK FOREIGN KEY (Klienti_ID) REFERENCES Client(Client_ID),
	CONSTRAINT fk_Team_TProces_ID  FOREIGN KEY (Team_ID) REFERENCES Team(Team_ID) 
)
GO

CREATE TABLE Participant_Team
(
	PartTeam_ID int IDENTITY (1,1),
	PCM_ID int NOT NULL,
	Team_ID  int NOT NULL,
	Statusi varchar(40) NOT NULL DEFAULT 'Active',
	CONSTRAINT pk_PartTeam PRIMARY KEY (PartTeam_ID),
	CONSTRAINT fk_PT_PCM FOREIGN KEY (PCM_ID) REFERENCES ParticipatingCompanyMember(PCM_ID),
	CONSTRAINT fk_PT_Team FOREIGN KEY (Team_ID) REFERENCES Team(Team_ID),
	CONSTRAINT UNIQUE_PT UNIQUE CLUSTERED
		(
			PCM_ID,
			Team_ID
		)
)
GO

CREATE TABLE TTrainer_Evaluation(
	Evaluation_ID int IDENTITY (1,1),
	Trainer_Preperation int NOT NULL,
	Trainer_Discussion int NOT NULL,
	Presentation int NOT NULL,
	Trainer_ID int NOT NULL,
	TProcess_ID varchar(50) NOT NULL,
	PCM_ID int NOT NULL,
	Statusi varchar(40) NOT NULL DEFAULT 'Active',
	CONSTRAINT pk_TTEvalu PRIMARY KEY (Evaluation_ID),
	CONSTRAINT fk_TTE_PartTeam FOREIGN KEY (PCM_ID) REFERENCES ParticipatingCompanyMember(PCM_ID),
	CONSTRAINT fk_TTE_Trainer FOREIGN KEY (Trainer_ID) REFERENCES Trainer(Trainer_ID),
	CONSTRAINT fk_TTE_TProcess FOREIGN KEY (TProcess_ID) REFERENCES Training_Process(TProcess_ID),
	CONSTRAINT UNIQUE_te UNIQUE CLUSTERED
		(
			Trainer_ID,
			TProcess_ID,
			PCM_ID
		)	
)
GO

CREATE TABLE Topics_Covered
(
	Topic_ID int IDENTITY (1,1),
	Topic_Covered varchar(350) NOT NULL,
	Language varchar (50) NOT NULL ,
	Statusi varchar(40) NOT NULL DEFAULT 'Active',
	CONSTRAINT pk_TC PRIMARY KEY (Topic_ID),
	CONSTRAINT UNIQUE_Topic_Covered_Language UNIQUE CLUSTERED
		(
			Topic_Covered ,
			Language 
		)
)
GO


CREATE TABLE Topics_Combo
(
	Topic_Combo_ID int IDENTITY (1,1),
	TProcess_ID varchar(50),
	Topic_ID int ,
	CONSTRAINT pk_TCC PRIMARY KEY (Topic_Combo_ID),
	CONSTRAINT fk_Topic_TC FOREIGN KEY (TProcess_ID) REFERENCES Training_Process(TProcess_ID),
	CONSTRAINT fk_TP_TC FOREIGN KEY (Topic_ID) REFERENCES Topics_Covered(Topic_ID),
	Statusi varchar(40) NOT NULL DEFAULT 'Active',
	CONSTRAINT UNIQUE_T_TP UNIQUE CLUSTERED
		(
			TProcess_ID ,
			Topic_ID 
		)
)
GO

CREATE TABLE CTPcombo(
	ctpcomboID int IDENTITY (1,1),
	CompanyID int,
	TProcess_ID varchar(50),
	Statusi varchar(40) NOT NULL DEFAULT 'Active',
	CONSTRAINT pk_CTP PRIMARY KEY (ctpcomboID),
	CONSTRAINT fk_companyCTP FOREIGN KEY (CompanyID) REFERENCES Company(Company_ID),
	CONSTRAINT fk_trainingpCTP FOREIGN KEY (TProcess_ID) REFERENCES Training_Process(TProcess_ID)
)
GO

CREATE TABLE Users(
	Username varchar(50) PRIMARY KEY NOT NULL,
	Passwordi BINARY(64)NOT NULL,
	salt varchar(64) NOT NULL,
	Name varchar(50)NOT NULL,
	Pozita varchar (50) NOT NULL,
	numOfLogins int,
	Surname varchar(50)NOT NULL,
	statusi varchar(20) NOT NULL
)
GO

CREATE TABLE Logs(
	LogsID int PRIMARY KEY IDENTITY(1,1),
	Username varchar(50),
	Useri varchar (100),
	Dita Date,
	Koha Time,
	Lloji varchar(30),
	Mesazhi varchar(500),
	Statusi varchar(20),
	CONSTRAINT fk_Username FOREIGN KEY (Username) REFERENCES Users(Username)
)
GO


CREATE VIEW Report_Month
AS
SELECT YEAR(tp.EndDate) as 'Viti' ,MONTH(tp.EndDate) as 'Muaji' , COUNT(DISTINCT tp.TProcess_ID) as 'Numri_i_Trainimeve', COUNT(DISTINCT tte.Evaluation_ID) as 'Numri_i_Vleresimeve_te_Trainereve',COUNT(DISTINCT pcm.Participant_ID) as 'Numri_i_Pjesemarresve'
FROM Training_Process tp
LEFT JOIN TTrainer_Evaluation tte ON tp.TProcess_ID=tte.TProcess_ID AND tte.Statusi='Active'
LEFT JOIN Team t ON tp.Team_ID=t.Team_ID AND t.Statusi='Active'
LEFT JOIN Participant_Team pt ON t.Team_ID=pt.Team_ID AND pt.Statusi='Active'
LEFT JOIN ParticipatingCompanyMember pcm ON pt.PCM_ID=pcm.PCM_ID AND pcm.Statusi='Active'
WHERE tp.Statusi='Active'
GROUP BY YEAR(tp.EndDate),MONTH(tp.EndDate);
--VIEW nuk permban order by | ORDER BY YEAR(tp.EndDate) ASC; |--
GO

CREATE VIEW Report_Year
AS
SELECT YEAR(tp.EndDate) as 'Viti' ,COUNT(DISTINCT tp.TProcess_ID) as 'Numri_i_Trainimeve', COUNT(DISTINCT tte.Evaluation_ID) as 'Numri_i_Vleresimeve_te_Trainereve',COUNT(DISTINCT pcm.Participant_ID) as 'Numri_i_Pjesemarresve'
FROM Training_Process tp
LEFT JOIN TTrainer_Evaluation tte ON tp.TProcess_ID=tte.TProcess_ID AND tte.Statusi='Active'
LEFT JOIN Team t ON tp.Team_ID=t.Team_ID AND t.Statusi='Active'
LEFT JOIN Participant_Team pt ON t.Team_ID=pt.Team_ID AND pt.Statusi='Active'
LEFT JOIN ParticipatingCompanyMember pcm ON pt.PCM_ID=pcm.PCM_ID AND pcm.Statusi='Active'
WHERE tp.Statusi='Active'
GROUP BY YEAR(tp.EndDate);
--VIEW nuk permban order by | ORDER BY YEAR(tp.EndDate); |--
GO

CREATE VIEW Report_Trainer_Evaluation
AS
SELECT CONCAT(t.Name,' ',t.Surname) as 'Trainer ',tp.TProcess_ID as 'Kodi_Trainimit',
CASE WHEN (par.ID_Number IS NULL AND par.Name IS NULL AND par.Surname IS NULL) then 'NULL' 
ELSE CONCAT(par.ID_Number,' - ', par.Name,' ',par.Surname) END as 'Pjesemaresit_e_trajnuar',
CASE WHEN (tte.Presentation IS NULL) then '0' else tte.Presentation END AS 'Prezantimi' ,
CASE WHEN (tte.Trainer_Discussion IS NULL) then '0' else tte.Trainer_Discussion END AS 'Diskutimi' ,
CASE WHEN (tte.Trainer_Preperation IS NULL) then '0' else tte.Trainer_Preperation END AS 'Pergaditja'
FROM Trainer t
LEFT JOIN TrainerTeamCombo ttc ON t.Trainer_ID=ttc.Trainer_ID AND ttc.Statusi='Active'
LEFT JOIN TrainersTeam tt ON ttc.TrainersTeamID=tt.TrainersTeamID AND tt.Statusi='Active'
LEFT JOIN Training_Process tp ON tt.TrainersTeamID=tp.TrainersTeamID AND tp.Statusi='Active'
LEFT JOIN Team te ON tp.Team_ID=te.Team_ID AND te.Statusi='Active'
LEFT JOIN Participant_Team pt ON te.Team_ID=pt.Team_ID AND pt.Statusi='Active'
LEFT JOIN ParticipatingCompanyMember pcm ON pcm.PCM_ID=pt.PCM_ID AND pcm.Statusi='Active'
LEFT JOIN Participant par ON par.Participant_ID = pcm.Participant_ID AND par.Statusi='Active'
LEFT JOIN TTrainer_Evaluation tte ON  tte.TProcess_ID=tp.TProcess_ID 
AND tte.Trainer_ID=t.Trainer_ID AND tte.PCM_ID =pcm.PCM_ID AND tte.Statusi='Active'
WHERE t.Statusi='Active'
GROUP BY CONCAT(t.Name,' ',t.Surname),tp.TProcess_ID,par.ID_Number,par.Name,par.Surname,
tte.Presentation,tte.Trainer_Discussion,tte.Trainer_Preperation;
--VIEW nuk permban order by | ORDER BY CONCAT(t.Name,' ',t.Surname) |--
GO



CREATE VIEW Report_Trainer
AS													
SELECT CONCAT(t.Name,' ',t.Surname) as 'Trainer ',
CASE WHEN ( ROUND(((AVG(CAST(TTE.Presentation AS float)) +AVG(CAST(TTE.Trainer_Discussion AS float))
+AVG(CAST(TTE.Trainer_Preperation AS float)))/3),2) IS NULL) then '0'
ELSE ROUND(((AVG(CAST(TTE.Presentation AS float)) +AVG(CAST(TTE.Trainer_Discussion AS float))
+AVG(CAST(TTE.Trainer_Preperation AS float)))/3),2) END AS 'Mesatarja',
CASE WHEN (ROUND(AVG(CAST(TTE.Presentation AS float)),2) IS NULL)THEN '0' 
ELSE ROUND(AVG(CAST(TTE.Presentation AS float)),2) END AS 'Prezantimi',
CASE WHEN (ROUND(AVG(CAST(TTE.Trainer_Discussion AS float)),2) IS NULL)THEN '0' 
ELSE ROUND(AVG(CAST(TTE.Trainer_Discussion AS float)),2) END AS 'Diskutimi',
CASE WHEN (ROUND(AVG(CAST(TTE.Presentation AS float)),2) IS NULL)THEN '0' 
ELSE ROUND(AVG(CAST(TTE.Trainer_Preperation AS float)),2) END AS 'Pergaditja',
COUNT(DISTINCT tp.TProcess_ID) as 'Numri_i_Trainimeve_te_mbajtura',
COUNT(DISTINCT pcm.Participant_ID) as 'Numri_i_Personave_te_trainuar',
(SELECT COUNT(tte2.Evaluation_ID) FROM TTrainer_Evaluation tte2 WHERE tte2.Trainer_ID=t.Trainer_ID ) as 'Numri_Vlersimeve'
FROM Trainer t
LEFT JOIN TrainerTeamCombo ttc ON t.Trainer_ID=ttc.Trainer_ID AND ttc.Statusi='Active'
LEFT JOIN TrainersTeam tt ON tt.TrainersTeamID=ttc.TrainersTeamID AND tt.Statusi='Active'
LEFT JOIN Training_Process tp ON tt.TrainersTeamID=tp.TrainersTeamID AND tp.Statusi='Active'
LEFT JOIN Team te ON tp.Team_ID=te.Team_ID AND te.Statusi='Active'
LEFT JOIN Participant_Team pt ON pt.Team_ID=te.Team_ID AND pt.Statusi='Active'
LEFT JOIN ParticipatingCompanyMember pcm ON pcm.PCM_ID=pt.PCM_ID AND pcm.Statusi='Active'
LEFT JOIN TTrainer_Evaluation tte ON tte.Trainer_ID=t.Trainer_ID
WHERE t.Statusi='Active'
GROUP BY  CONCAT(t.Name,' ',t.Surname),t.Trainer_ID;
--VIEW nuk permban order by |  ORDER BY 'Mesatarja' DESC,'Prezantimi' DESC,'Diskutimi' DESC,'Pergaditja' DESC; |--
GO

CREATE VIEW Report_Training_Process
AS
SELECT tp.Place, CONCAT('ID: ',tp.TProcess_ID,' - ',p.Emri) as 'Trainimi',
CONCAT(tra.TitleOfTraining_Albanian,' - ',tra.TitleOfTraining_English,' - ',tra.TitleOfTraining_Serbian) as 'Titujt_e_Trainimit' ,
COUNT(DISTINCT t.Trainer_ID) as 'Numri_i_Trainereve',
COUNT(DISTINCT pcm.Participant_ID) as 'Numri_i_Pjesmarresve',
COUNT(DISTINCT tte.Evaluation_ID) as 'Numri_i_Vleresimeve'
FROM Training_Process tp
LEFT JOIN Training_Project tpro ON tpro.TP_ID=tp.TrainingProjectID AND tpro.Statusi='Active'
LEFT JOIN Project p ON p.Project_ID=tpro.Project_ID AND p.Statusi='Active'
LEFT JOIN Training tra ON tra.Training_ID=tpro.Training_ID AND tra.Statusi='Active'
LEFT JOIN TrainersTeam trat ON trat.TrainersTeamID=tp.TrainersTeamID AND trat.Statusi='Active'
LEFT JOIN TrainerTeamCombo ttc ON ttc.TrainersTeamID=trat.TrainersTeamID AND ttc.Statusi='Active'
LEFT JOIN Trainer t ON t.Trainer_ID=ttc.Trainer_ID AND t.Statusi='Active'
LEFT JOIN TTrainer_Evaluation tte ON tte.TProcess_ID=tp.TProcess_ID AND tte.Statusi='Active'
LEFT JOIN Team tea ON tp.Team_ID=tea.Team_ID AND tea.Statusi='Active'
LEFT JOIN Participant_Team pt ON tea.Team_ID=pt.Team_ID AND pt.Statusi='Active'
LEFT JOIN ParticipatingCompanyMember pcm ON pt.PCM_ID=pcm.PCM_ID AND pcm.Statusi='Active'
WHERE tp.Statusi='Active'
GROUP BY tp.Place, p.Emri,tp.TProcess_ID,
tra.TitleOfTraining_Albanian,tra.TitleOfTraining_English,tra.TitleOfTraining_Serbian;
--VIEW nuk permban order by |  ORDER BY tp.Place |--
GO

SELECT * 
FROM Report_Month rm 
WHERE CONVERT(date,CONCAT(CAST(rm.viti AS VARCHAR (4)),'-',CAST(rm.muaji AS VARCHAR(2)),'-',CAST('01' AS VARCHAR (2)))) 
BETWEEN CONVERT(date,CONCAT(CAST( '1995' AS VARCHAR (4)),'-',CAST( '4' AS VARCHAR(2)),'-',CAST('01' AS VARCHAR (2)))) 
AND CONVERT(date,CONCAT(CAST( '2020' AS VARCHAR (4)),'-',CAST( '5' AS VARCHAR(2)),'-',CAST('01' AS VARCHAR (2))))
GO

SELECT * FROM Training_Process tp
LEFT JOIN Topics_Combo tcomb ON tcomb.TProcess_ID=tp.TProcess_ID AND tcomb.Statusi='Active'
LEFT JOIN Topics_Covered tcov ON tcov.Topic_ID=tcomb.Topic_ID AND tcov.Statusi='Active'
LEFT JOIN Training_Project tproj ON tproj.TP_ID=tp.TrainingProjectID AND tproj.Statusi='Active'
LEFT JOIN Training tr ON tr.Training_ID=tproj.Training_ID AND tr.Statusi='Active'
LEFT JOIN Project pr ON pr.Project_ID=tproj.Project_ID AND pr.Statusi='Active'
LEFT JOIN Client cli ON cli.Client_ID=tp.Klienti_ID AND cli.Statusi='Active'
LEFT JOIN TrainersTeam tteam ON tteam.TrainersTeamID=tp.TrainersTeamID AND tteam.Statusi='Active'
LEFT JOIN TrainerTeamCombo ttcombo ON ttcombo.TrainersTeamID=tteam.TrainersTeamID AND ttcombo.Statusi='Active'
LEFT JOIN Trainer te ON te.Trainer_ID=ttcombo.Trainer_ID AND te.Statusi='Active'
LEFT JOIN Team par ON par.Team_ID= tp.Team_ID AND par.Statusi='Active'
LEFT JOIN Participant_Team partt ON partt.Team_ID=par.Team_ID AND partt.Statusi='Active'
LEFT JOIN ParticipatingCompanyMember pcm ON pcm.PCM_ID=partt.PCM_ID AND pcm.Statusi='Active'
LEFT JOIN TTrainer_Evaluation tte ON tte.Trainer_ID=te.Trainer_ID 
AND tte.TProcess_ID=tp.TProcess_ID AND tte.PCM_ID=pcm.PCM_ID AND tte.Statusi='Active'
LEFT JOIN Participant part ON part.Participant_ID=pcm.Participant_ID AND part.Statusi='Active'
LEFT JOIN Company com ON com.Company_ID=pcm.Company_ID AND pcm.Statusi='Active'
WHERE tp.Statusi='Active'
GO


CREATE VIEW TrainersByTp 
AS 
SELECT tp2.TProcess_ID ,(
SELECT STUFF(
(SELECT ',' + CONCAT(t.Name,' ',t.Surname)
FROM Training_Process tp
INNER JOIN TrainersTeam tt ON tp.TrainersTeamID=tt.TrainersTeamID AND tt.Statusi='Active'
INNER JOIN TrainerTeamCombo ttc ON tt.TrainersTeamID=ttc.TrainersTeamID AND ttc.Statusi='Active'
INNER JOIN Trainer t ON ttc.Trainer_ID=t.Trainer_ID AND t.Statusi='Active'
WHERE 
tp2.TProcess_ID=tp.TProcess_ID
AND tp2.Statusi='Active'
FOR XML PATH('')),1,1,'')) as 'Trainers'
FROM Training_Process tp2
GO



CREATE VIEW TopicsCoveredByTp
AS
SELECT tp2.TProcess_ID,(
SELECT STUFF(
(SELECT ';'+ tcov.Topic_Covered
FROM Training_Process tp 
INNER JOIN Topics_Combo tc ON tc.TProcess_ID=tp.TProcess_ID AND tc.Statusi='Active'
INNER JOIN Topics_Covered tcov ON tcov.Topic_ID=tc.Topic_ID AND tcov.Statusi='Active'
WHERE tp.TProcess_ID=tp2.TProcess_ID AND tp2.Statusi='Active' AND tcov.Language='Albanian'
FOR XML PATH('')),1,1,'')) as 'Topics_Covered_Albanian',
(
SELECT STUFF(
(SELECT ';'+ tcov.Topic_Covered
FROM Training_Process tp 
INNER JOIN Topics_Combo tc ON tc.TProcess_ID=tp.TProcess_ID AND tc.Statusi='Active'
INNER JOIN Topics_Covered tcov ON tcov.Topic_ID=tc.Topic_ID AND tcov.Statusi='Active'
WHERE tp.TProcess_ID=tp2.TProcess_ID AND tp2.Statusi='Active' AND tcov.Language='English'
FOR XML PATH('')),1,1,'')) as 'Topics_Covered_English',
(
SELECT STUFF(
(SELECT ';'+ tcov.Topic_Covered
FROM Training_Process tp 
INNER JOIN Topics_Combo tc ON tc.TProcess_ID=tp.TProcess_ID AND tc.Statusi='Active'
INNER JOIN Topics_Covered tcov ON tcov.Topic_ID=tc.Topic_ID AND tcov.Statusi='Active'
WHERE tp.TProcess_ID=tp2.TProcess_ID AND tp2.Statusi='Active' AND tcov.Language='Serbian'
FOR XML PATH('')),1,1,'')) as 'Topics_Covered_Serbian'
FROM Training_Process tp2
GO


CREATE VIEW Training_View
AS 
SELECT  tp.TProcess_ID,c.Emri as 'Emri_Klientit',pr.Emri as 'Emri_Projektit',tr.TitleOfTraining_Albanian,
CASE WHEN (tcbt.Topics_Covered_Albanian IS NULL) then 'NULL' else tcbt.Topics_Covered_Albanian END AS 'Temat_Shqip' ,
CASE WHEN (tcbt.Topics_Covered_English IS NULL) then 'NULL' else tcbt.Topics_Covered_English END AS 'Temat_Anglisht' ,
CASE WHEN (tcbt.Topics_Covered_Serbian IS NULL) then 'NULL' else tcbt.Topics_Covered_Serbian END AS 'Temat_Serbisht' ,
tp.Place,tp.StartDate,tp.EndDate,(
SELECT tbtp.Trainers FROM TrainersByTp tbtp 
WHERE tbtp.TProcess_ID= tp.TProcess_ID ) as 'Trainerat'
,
tr.TitleOfTraining_English,tr.TitleOfTraining_Serbian FROM Training_Process tp
INNER JOIN TopicsCoveredByTp tcbt ON tcbt.TProcess_ID=tp.TProcess_ID
INNER JOIN Client c ON c.Client_ID=tp.Klienti_ID AND c.Statusi='Active'
INNER JOIN Training_Project tproj ON tproj.TP_ID=tp.TrainingProjectID AND tproj.Statusi='Active'
INNER JOIN Training tr ON tr.Training_ID=tproj.Training_ID AND tr.Statusi='Active'
INNER JOIN Project pr ON pr.Project_ID=tproj.Project_ID AND pr.Statusi='Active'
WHERE tp.Statusi='Active'
GO

CREATE VIEW Participant_View
AS 
SELECT tp.TProcess_ID,p.Participant_ID,CONCAT(p.Name,' ',p.Surname) as 'Pjesmarresi',p.Sex as 'Gjinia',p.DOB as 'Data_e_Lindjës',p.ID_Number,p.Email,p.Phone,p.Participant_State,p.City,p.ZipCode,p.Participant_Address,CONCAT(t.Name,' ',t.Surname) AS 'Traineri',tte.Presentation,tte.Trainer_Discussion,tte.Trainer_Preperation
FROM Participant p 
LEFT JOIN ParticipatingCompanyMember pcm ON pcm.Participant_ID=p.Participant_ID AND pcm.Statusi='Active'
LEFT JOIN Participant_Team pt ON pt.PCM_ID=pcm.PCM_ID AND pt.Statusi='Active'
LEFT JOIN Team te ON pt.Team_ID=te.Team_ID AND te.Statusi='Active'
LEFT JOIN Training_Process tp on TP.Team_ID=TE.Team_ID AND tp.Statusi='Active'
INNER JOIN TTrainer_Evaluation tte ON tte.TProcess_ID=tp.TProcess_ID AND tte.PCM_ID=pcm.PCM_ID AND tte.Statusi='Active'
INNER JOIN Trainer t ON t.Trainer_ID=tte.Trainer_ID AND t.Statusi='Active'
WHERE p.Statusi='Active'
GO

CREATE VIEW Test_Admin
AS
SELECT COUNT (u.Username) AS 'Numri_Userave' FROM Users u;
GO

/*
SELECT  * FROM CTPcombo ctpc,Company c, ParticipatingCompanyMember pcm,Training_Process tp WHERE ctpc.CompanyID =c.Company_ID AND pcm.Company_ID =c.Company_ID AND pcm.participant_ID = '1004949940' AND tp.TProcess_ID like '41023082' AND ctpc.TProcess_ID like tp.TProcess_ID;
qetu je tregu fag 


SELECT *
FROM Report_Month rm 
WHERE CONVERT(date,CONCAT(CAST(rm.viti AS VARCHAR (4)),'-',CAST(rm.muaji AS VARCHAR(2)),'-',CAST('01' AS VARCHAR (2)))) 
BETWEEN CONVERT(date,CONCAT(CAST( '1995' AS VARCHAR (4)),'-',CAST( '4' AS VARCHAR(2)),'-',CAST('01' AS VARCHAR (2)))) 
AND CONVERT(date,CONCAT(CAST( '2020' AS VARCHAR (4)),'-',CAST( '5' AS VARCHAR(2)),'-',CAST('01' AS VARCHAR (2))))
GO
*/

USE MDAfondation;