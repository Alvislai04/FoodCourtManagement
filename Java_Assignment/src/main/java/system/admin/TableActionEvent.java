package system.admin;

public interface TableActionEvent {
    public void onEdit(int row);
    public void onDelete(int row);
    public void onView(int row);
    public void onApprove(int row);
    public void onDecline(int row);
}
