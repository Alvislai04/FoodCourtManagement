package system.admin;

public interface TableActionEvent {
    public void onEdit(int row);
    public void onDelete(java.awt.event.ActionEvent evt);
    public void onView(int row);
}
