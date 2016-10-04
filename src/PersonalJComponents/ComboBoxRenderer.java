
package PersonalJComponents;

import java.awt.Component;
import java.awt.Font;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

public class ComboBoxRenderer extends DefaultListCellRenderer {

    private static final long serialVersionUID = 1L;
    private Font baseFont = new JLabel("Test").getFont();

    @Override
    public Component getListCellRendererComponent(JList list, Object value,
            int index, boolean isSelected, boolean cellHasFocus) {

        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        //if (value instanceof Font) {
        //    Font font = (Font) value;
        //    setFont(new Font(font.getName(), baseFont.getStyle(), baseFont.getSize()));
              setFont(new Font("Courier", Font.ITALIC, 10));
        //}
        return this;
    }
}
