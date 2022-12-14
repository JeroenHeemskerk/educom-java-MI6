package nu.educom.MI6.Backup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginHandler implements ActionListener {

    private IPresenter control;

    public LoginHandler(IPresenter control) {
        this.control = control;
    }

    public void actionPerformed(ActionEvent e) {
        control.triggerLogin();
    }
}
