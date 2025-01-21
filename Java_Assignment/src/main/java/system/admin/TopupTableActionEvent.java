package system.admin;

public interface TopupTableActionEvent {
    public void onApprove(int row);
    public void onDecline(int row);
}
