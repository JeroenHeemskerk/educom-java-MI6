package nu.educom.MI6.Backup;

public class Presenter implements IPresenter{
    private View view;
    private Model model;
    private Crud crud;
    private String agentNumber;
    private String sentence;

    public Presenter(View view, Crud crud) {
        this.crud = crud;
        this.model = new Model(crud, this);
        this.view = view;
        this.view.setPresenterInterface(this);
        this.view.setLoginHandler();
        this.view.setBackHandler();
    }

    public void triggerLogin() {
        agentNumber = this.view.getAgentNumber();
        sentence = this.view.getSentence();
        if(this.model.validateLogin(agentNumber, sentence)) {
            view.displayLoginSuccess("ACCESS GRANTED");
        } else {
            view.displayLoginError(model.getErrors().get("Validation"));
        }

    }

    public void displayLogin() {
        view.displayLogin();
    }

    public void triggerBack() {
        view.displayLogin();
    }

//    public void setIntervalValue(int secs) {
//        view.setIntervalValue(secs);
//        view.startTimer();
//    }
    
}
