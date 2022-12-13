package nu.educom.MI6;

public class Presenter implements IPresenter{
    private View view;
    private Model model = new Model();
    private String agentNumber;
    private String sentence;

    public Presenter(View view) {
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
        this.view.displayLogin();
    }

    public void triggerBack() {
        this.view.displayLogin();
    }
    
}
