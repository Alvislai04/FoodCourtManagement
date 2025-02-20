/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system.Vendor;

import java.awt.Component;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Louis
 */
public class customListRender {
    public static class MultilineListCellRenderer extends JTextArea implements ListCellRenderer<String> {

    public MultilineListCellRenderer() {
        setLineWrap(true); // Enable line wrapping
        setWrapStyleWord(true); // Wrap at word boundaries
        setOpaque(true); // Make the background visible
        setBorder(null);
        setFont(new Font("Segoe UI Black", Font.BOLD, 18));
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends String> list, String value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
        setText(value); // Set the text of the item
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        
        // Adjust the preferred size dynamically
            setSize(list.getWidth(), Short.MAX_VALUE); // Set the width to match the list
            int height = getPreferredSize().height;   // Calculate the preferred height
            list.setFixedCellHeight(-1);              // Allow variable cell heights
            list.setFixedCellWidth(list.getWidth());  // Set cell width to the list's width
        
        return this;
    }
}



}
