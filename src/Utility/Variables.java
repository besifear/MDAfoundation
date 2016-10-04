package Utility;

public class Variables {
    private String name;
    private boolean checked;
    
    public Variables (){
    }
    
    public Variables (String name,Boolean checked){
        this.name=name;
        this.checked=checked;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name=name;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
    
    public boolean equals(Object o){
        if(o instanceof Variables){
            Variables v=(Variables)o;
            return v.getName().equals(name);
            }
        return false;
    }
    
    public String toString(){
        return name;
    }
}
