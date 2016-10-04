package Validation;

import com.toedter.calendar.JDateChooser;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Besniku
 */
public class StartEndDateManager {
    
    private JDateChooser startDateCalendar;
    private JDateChooser endDateCalendar;
    private JLabel startDateLbl;
    private JLabel endDateLbl;
    
    public StartEndDateManager(JDateChooser startDateCalendar,JDateChooser endDateCalendar,JLabel instructionLbl,JLabel endDateLbl){
        this.startDateCalendar=startDateCalendar;
        this.endDateCalendar=endDateCalendar;
        this.startDateLbl=startDateLbl;
        this.endDateLbl=endDateLbl;
        addListeners(this.startDateCalendar);
        addListeners(this.endDateCalendar);
    }
    
    private  void addListeners(JDateChooser target) {
        ((JTextField)target.getDateEditor().getUiComponent()).addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent fe) {
                validator();
            }
            @Override
            public void focusLost(FocusEvent fe) {
                validator();
            }
        });

        ((JTextField)target.getDateEditor().getUiComponent()).getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validator();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validator();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validator();
            }
        });
    }
    
    public void validator(){
        if ( startDateCalendar.getCalendar()==null && endDateCalendar.getCalendar()==null){
                startDateLbl.setText("Shkruaje datën e fillimit të Mbajtjës së Trajnimit.");
                endDateLbl.setText("Shkruaje datën e përfundimit të Mbajtjës së Trajnimit.");
            }
            else if(startDateCalendar.getCalendar()==null)
                startDateLbl.setText("Shkruaje datën e fillimit të Mbajtjës së Trajnimit.");
            else if (endDateCalendar.getCalendar()==null)
                endDateLbl.setText("Shkruaje datën e përfundimit të Mbajtjës së Trajnimit.");
            else {
                String startDateTemp= ((JTextField)startDateCalendar.getDateEditor().getUiComponent()).getText();
                String endDateTemp=((JTextField)endDateCalendar.getDateEditor().getUiComponent()).getText();
                DateFormat df = new SimpleDateFormat("MM-dd-yyyy"); 
                Date startDate;
                Date endDate;
                try {
                startDate = df.parse(startDateTemp);
                endDate=df.parse(endDateTemp);
                if (startDate.after(endDate)){
                    startDateLbl.setText("Data e fillimit nuk mund të jetë më vonë se data e mbarimit.");
                    endDateLbl.setText("");
                }
                else{
                    startDateLbl.setText("");
                    endDateLbl.setText("");
                }
                } catch (ParseException e) {
        }
                }
    }
}
