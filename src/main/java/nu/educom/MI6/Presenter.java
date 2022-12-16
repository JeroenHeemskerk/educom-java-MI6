package nu.educom.MI6;

import java.sql.SQLException;
import java.time.LocalDate;
import java.text.SimpleDateFormat;

public class Presenter implements IPresenter{
    private View view;
    private Model model;
    private Crud crud;
    private String agentNumber;
    private String sentence;

    private SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    public Presenter(View view, Crud crud) {
        this.crud = crud;
        this.view = view;
        this.model = new Model(crud, view);
        this.view.setPresenterInterface(this);
        this.view.setLoginHandler();
        this.view.setBackHandler();
    }

    public void triggerLogin() {
        agentNumber = this.view.getAgentNumber();
        sentence = this.view.getSentence();
        model.validateAgentNumber(agentNumber);

        try {
            var agent_number = model.getAgent().getAgentNumber();
            if (model.authenticateAgent(agent_number)) {
                var license_to_kill = "No license to kill";
                if (model.getAgent().getLicence_to_kill()!=null && LocalDate.now().isBefore(model.getAgent().getLicence_to_kill().toLocalDate())){
                    license_to_kill = String.format("Your license to kill expires on %s.", df.format(model.getAgent().getLicence_to_kill()));
                }
                view.displayLoginSuccess(String.format("Login Successful. Welcome agent %s.", agent_number), license_to_kill, model.getLoginAttempts());
            } else {
                view.displayLoginError(model.getErrors().get("Validation"));
            }
        } catch (SQLException e) {
            view.displayLoginError(model.getErrors().get("Validation"));
        } catch (NullPointerException e) {
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
