package system.admin;

import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class TableActionCellEditor extends DefaultCellEditor {

    private TableActionEvent event;
    private ActionEvent evt;
    public TableActionCellEditor(TableActionEvent event) {
        super(new JCheckBox());
        this.event = event;
    }

    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int row, int column){
        PanelAction action = new PanelAction();
        action.initEvent(event, row, evt);
        action.setBackground(jtable.getSelectionBackground());
        return action;
    }
}
