
package Utility;

import ejb.Trainer;
import java.util.ArrayList;
import java.util.Iterator;

public class TrainerTblCheck{
    
    ArrayList<Trainer> trainerat;
    int countTrainers=0;
    Trainer tra;
    public TrainerTblCheck(ArrayList<Trainer> trainerat){
        this.trainerat=trainerat;
    }
    
    public int checkTbl(){
        Iterator<Trainer> iteratori= trainerat.iterator();
        while (iteratori.hasNext()){
            tra=iteratori.next();
            try{
            if (!tra.getName().isEmpty() && !tra.getSurname().isEmpty())
                countTrainers++;
            }catch (NullPointerException npe){}
        }
        return countTrainers;
    }
 }
    

