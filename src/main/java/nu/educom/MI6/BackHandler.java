package nu.educom.MI6;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackHandler implements ActionListener {

    private IPresenter control;

    public BackHandler(IPresenter control) {
        this.control = control;
    }

    public void actionPerformed(ActionEvent e) {
        control.triggerBack();
    }

}