package system.admin;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class TopupTableActionCellEditor extends DefaultCellEditor {

    private TopupTableActionEvent topupEvent;

    public TopupTableActionCellEditor(TopupTableActionEvent topupEvent) {
        super(new JCheckBox());
        this.topupEvent = topupEvent;
    }

    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int row, int column){
        TopupPanelAction action = new TopupPanelAction();
        action.initTopupEvent(topupEvent, row);
        action.setBackground(jtable.getSelectionBackground());
        return action;
    }
}
