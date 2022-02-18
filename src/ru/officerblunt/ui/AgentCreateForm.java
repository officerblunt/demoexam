package ru.officerblunt.ui;

import ru.officerblunt.Entity.AgentEntity;
import ru.officerblunt.EntityManager.AgentEntityManager;
import ru.officerblunt.Util.BaseForm;
import ru.officerblunt.Util.DialogUtil;

import javax.swing.*;
import java.sql.SQLException;

public class AgentCreateForm extends BaseForm {
    private JPanel mainPanel;
    private JTextField titleField;
    private JTextField agentTypeField;
    private JTextField addressField;
    private JTextField phoneField;
    private JTextField emailField;
    private JTextField logoField;
    private JTextField priorityField;
    private JButton backButton;
    private JButton addButton;

    public AgentCreateForm () {
        super(800, 600);
        setContentPane(mainPanel);
        initButtons();
        setVisible(true);
    }

    private void initButtons () {
        addButton.addActionListener(e -> {
            String title = titleField.getText();
            if (title.length() >= 40 || title.isEmpty()) {
                DialogUtil.showError(this,"");
                return;
            }
            String type = agentTypeField.getText();
            if (type.length() >= 40 || type.isEmpty()) {
                DialogUtil.showError(this,"");
                return;
            }
            String address = addressField.getText();
            if (address.length() >= 40 || address.isEmpty()) {
                DialogUtil.showError(this,"");
                return;
            }
            String phone = phoneField.getText();
            if (phone.length() >= 40 || phone.isEmpty()) {
                DialogUtil.showError(this,"");
                return;
            }
            String email = emailField.getText();
            if (email.length() >= 40 || email.isEmpty()) {
                DialogUtil.showError(this,"");
                return;
            }
            String logo = logoField.getText();
            if (logo.length() >= 40 || logo.isEmpty()) {
                DialogUtil.showError(this,"");
                return;
            }
            int priority = Integer.parseInt(priorityField.getText());
            AgentEntity a = new AgentEntity(title, type, address, phone, email, logo, priority);
            try {
                AgentEntityManager.insert(a);
                DialogUtil.showMessage(this, "Агент успешно добавлен!");
                dispose();
                new AgentTableForm();
            } catch (SQLException ex) {
                DialogUtil.showError(this, "Ошибка добавления!");
                return;
            }
        });
        backButton.addActionListener(e -> {
            dispose();
            new AgentTableForm();
        });
    }
}
