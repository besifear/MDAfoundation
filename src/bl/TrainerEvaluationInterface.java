package bl;

import ejb.Participant;
import ejb.ParticipatingCompanyMember;
import ejb.TTrainerEvaluation;
import ejb.Trainer;
import ejb.TrainingProcess;
import java.util.List;

public interface TrainerEvaluationInterface {
      void create(TTrainerEvaluation tev)throws AppException;
    void edit(TTrainerEvaluation tev)throws AppException;
    void remove(TTrainerEvaluation tev);
    List<TTrainerEvaluation> findAll();
    List<TTrainerEvaluation> findAllActive();
    List<TTrainerEvaluation> findByTrainerID(int TrainerID);
    TTrainerEvaluation findById(String CompanyID);
    TTrainerEvaluation findByEverything(TTrainerEvaluation tte);
    List<TTrainerEvaluation> findByTrainingProcessParticipantForDelete(String tp,int par);
    List<TTrainerEvaluation> findByTPDate(int i);
    List<TTrainerEvaluation> findByTrainingProcessParticipant(TrainingProcess tptemp, Participant partemp);
    List<TTrainerEvaluation> findByPCM(ParticipatingCompanyMember pcm);
    List<TTrainerEvaluation> findByTrainingProcess(TrainingProcess tp);
}
