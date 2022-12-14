package nu.educom.MI6;

public class Presenter implements IPresenter{
    private View view;
    private Model model;
    private Crud crud;
    private String agentNumber;
    private String sentence;

    public Presenter(View view, Crud crud) {
        this.crud = crud;
        this.model = new Model(crud, view);
        this.view = view;
        this.view.setPresenterInterface(this);
        this.view.setLoginHandler();
        this.view.setBackHandler();
    }

    public void triggerLogin() {
        agentNumber = this.view.getAgentNumber();
        sentence = this.view.getSentence();
        model.validateAgentNumber(agentNumber);
//        } else {
//            view.displayLoginError(model.getErrors().get("Validation"));
//        }
        try {
            var agent_number = model.getAgent().getAgentNumber();
            //System.out.println("testABC");
            if (model.authenticateAgent(agent_number)) {
                System.out.println("test");
                view.displayLoginSuccess(String.format("Login Successful %s", agent_number));
            } else {
                System.out.println("test123");
                view.displayLoginError(model.getErrors().get("Validation"));

            }
            //model.setAgent(null);
        } catch (Exception e) {
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
