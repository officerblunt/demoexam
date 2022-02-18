package ru.officerblunt.ui;

import ru.officerblunt.Entity.AgentEntity;
import ru.officerblunt.EntityManager.AgentEntityManager;
import ru.officerblunt.Util.BaseForm;
import ru.officerblunt.Util.DialogUtil;

import javax.swing.*;
import java.sql.SQLException;

public class AgentEditForm extends BaseForm {
    private JPanel mainPanel;
    private JTextField titleField;
    private JTextField agentTypeField;
    private JTextField addressField;
    private JTextField phoneField;
    private JTextField emailField;
    private JTextField logoField;
    private JTextField priorityField;
    private JButton backButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JTextField idField;

    private AgentEntity a;
    public AgentEditForm(AgentEntity a) {
        super(800, 600);
        this.a = a;
        setContentPane(mainPanel);
        initButtons();
        initFields();
        setVisible(true);
    }

    private void initFields () {
        idField.setText(String.valueOf(a.getID()));
        idField.setEditable(false);
        titleField.setText(a.getTitle());
        agentTypeField.setText(a.getAgentType());
        addressField.setText(a.getAddress());
        phoneField.setText(a.getPhone());
        emailField.setText(a.getEmail());
        logoField.setText(a.getLogo());
        priorityField.setText(String.valueOf(a.getPriority()));
    }
    private void initButtons () {
        updateButton.addActionListener(e -> {
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
            AgentEntity agent = new AgentEntity(a.getID(), title, type, address, phone, email, logo, priority);
            try {
                AgentEntityManager.update(agent);
            } catch (SQLException ex) {
                DialogUtil.showError(this, "Ошибка изменения!");
            }
            DialogUtil.showMessage(this, "Агент успешно изменён!");
            dispose();
            new AgentTableForm();
        });
        backButton.addActionListener(e -> {
            dispose();
            new AgentTableForm();
        });
        deleteButton.addActionListener(e -> {
            try {
                AgentEntityManager.delete(a);
                DialogUtil.showMessage(this, "Клиент успешно удалён!");
            } catch (SQLException ex) {
                DialogUtil.showError(this, "Ошибка удаления!");
            }
        });
    }
}
