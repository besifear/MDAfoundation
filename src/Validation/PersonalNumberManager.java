package Validation;

import javax.swing.JTextField;

public class PersonalNumberManager {
    char characters[];
    int count;
    JTextField jcb;
    
    public PersonalNumberManager(JTextField jcb){
        this.jcb=jcb;
        checkValidInput();
    }
    
    public void checkValidInput(){
        characters = jcb.getText().trim().toCharArray();
        count=0;
        char temp;
        for (int i=0;i<characters.length;i++){
            temp=characters[i];
            if (temp<'0' || temp>'9'){
                count++;
            }
        }
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
}
