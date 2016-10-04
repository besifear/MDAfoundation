package Validation;

import javax.swing.JComboBox;
import javax.swing.JTextField;

public class TrainingCodeManager {
    String LastTrainingCode="";
    char characters[];
    int count;
    boolean viza;
    JTextField jcb;
    public TrainingCodeManager(JTextField jcb){
        this.jcb = jcb;
        checkValidInput();
    }
    
    public  void checkValidInput(){
        characters=jcb.getText().trim().toCharArray();
        count=0;
        viza=false;
        char temp;
        for (int i=0;i<characters.length;i++){
            temp=characters[i];
            if (temp<'0' || temp>'9'){
                count++;
                if(i==8 && temp=='-')
                    viza=true;
            }
        }
    }
    
    public String getTrainingCode(){
        return jcb.getText().trim();
    }
    
    public String getLastTrainingCode() {
        return LastTrainingCode;
    }

    public void setLastTrainingCode(String LastTrainingCode) {
        this.LastTrainingCode = LastTrainingCode;
    }

    
    public char[] getCharacters() {
        return characters;
    }

    public void setCharacters(char[] characters) {
        this.characters = characters;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isViza() {
        return viza;
    }

    public void setViza(boolean viza) {
        this.viza = viza;
    }
    
    
}
