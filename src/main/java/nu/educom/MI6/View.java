package nu.educom.MI6;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class View {
    private IPresenter presenter;
    private LoginHandler loginHandler;
    private BackHandler backHandler;
    private JFrame frame;
    private JLabel agentNumberLabel;
    private JLabel sentenceLabel;
    private JLabel errorLabel;
    private JPanel errorPanel;
    private JPanel panel1;
    private JPanel panel2;
    private JFormattedTextField agentNumberTextField;
    private JFormattedTextField sentenceTextField;
    private JButton loginButton;
    private JButton backButton;


    public View() {
        // make frame with the settings
        frame = new JFrame("Login");

    }

    public void setPresenterInterface(IPresenter presenter) {
        this.presenter = presenter;
    }

    public void setLoginHandler() {
        loginHandler = new LoginHandler(presenter);
    }

    public void setBackHandler() {
        backHandler = new BackHandler(presenter);
    }

    public void displayLogin(){
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.revalidate();

        // making labels and panels
        agentNumberLabel = new JLabel("Agent number:", JLabel.CENTER);
        sentenceLabel = new JLabel("Secret sentence:", JLabel.CENTER);
        panel1 = new JPanel();
        panel2 = new JPanel();

        // setting up panel1
        BoxLayout boxLayout1 = new BoxLayout(panel1, BoxLayout.Y_AXIS);
        panel1.setLayout(boxLayout1);
        panel1.setBorder(new EmptyBorder(new Insets(45, 75, 45, 75)));

        // setting up panel2
        BoxLayout boxLayout2 = new BoxLayout(panel2, BoxLayout.Y_AXIS);
        panel2.setLayout(boxLayout2);
        panel2.setBorder(new EmptyBorder(new Insets(45, 75, 45, 75)));

        // add button and textfield
        agentNumberTextField = new JFormattedTextField();
        sentenceTextField = new JFormattedTextField();
        loginButton = new JButton("Login");
        loginButton.addActionListener(loginHandler);

        // add to panel
        panel1.add(agentNumberLabel);
        panel1.add(agentNumberTextField);
        panel1.add(sentenceLabel);
        panel1.add(sentenceTextField);
        panel2.add(loginButton);

        //add to frame
        frame.setLayout(new GridLayout(2, 1));
        frame.add(panel1);
        frame.add(panel2);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void displayMsg(String errorMsg){
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.revalidate();

        errorLabel = new JLabel(errorMsg, JLabel.CENTER);
        errorPanel = new JPanel();
        panel2 = new JPanel();

        backButton = new JButton("Back");
        backButton.addActionListener(backHandler);

        // configure panel2
        BoxLayout boxlayout2 = new BoxLayout(panel2, BoxLayout.Y_AXIS);
        panel2.setLayout(boxlayout2);
        panel2.setBorder(new EmptyBorder(new Insets(45, 70, 45, 70)));


        // configure errPanel
        BoxLayout boxlayout = new BoxLayout(errorPanel, BoxLayout.Y_AXIS);
        errorPanel.setLayout(boxlayout);
        errorPanel.setBorder(new EmptyBorder(new Insets(45, 75, 45, 75)));

        errorPanel.add(errorLabel);
        panel2.add(backButton);

        frame.setLayout(new GridLayout(2, 1));
        frame.add(errorPanel);
        frame.add(panel2);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    public void displayLoginError(String errorMsg) {
        displayMsg(errorMsg);
    }

    public void displayLoginSuccess(String msg) {
        displayMsg(msg);
    }

    public String getAgentnumber(){
        return agentNumberTextField.getText();
    }

    public String getSentence(){
        return sentenceTextField.getText();
    }
}
