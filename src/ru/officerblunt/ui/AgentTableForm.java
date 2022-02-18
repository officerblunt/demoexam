package ru.officerblunt.ui;

import ru.officerblunt.Entity.AgentEntity;
import ru.officerblunt.EntityManager.AgentEntityManager;
import ru.officerblunt.Util.BaseForm;
import ru.officerblunt.Util.CustomTableModel;
import ru.officerblunt.Util.DialogUtil;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

public class AgentTableForm extends BaseForm {
    private JPanel mainPanel;
    private JTable agentTable;
    private JButton addButton;
    private JComboBox typeFilterBox;
    private JComboBox titleFilterBox;
    private JButton dropButton;
    private JLabel rowCountLabel;
    private JButton prioritySortButton;

    private CustomTableModel<AgentEntity> model;
    private boolean sort = false;

    public AgentTableForm() {
        super(800, 600);
        setContentPane(mainPanel);
        initTable();
        initBoxes();
        initButtons();
        setVisible(true);
    }

    private void initTable() {
        agentTable.setRowHeight(50);
        agentTable.getTableHeader().setReorderingAllowed(false);
        try {
            model = new CustomTableModel<>(
                    AgentEntity.class,
                    new String[]{"ID", "title", "agentType", "address", "phone", "email", "logo", "priority", "image"},
                    AgentEntityManager.selectAll()
            );
            agentTable.setModel(model);
            TableColumn column = agentTable.getColumn("logo" );
            column.setMinWidth(0);
            column.setPreferredWidth(0);
            column.setMaxWidth(0);
            updateRowCountLabel(model.getRows().size(), model.getRows().size());
        } catch (Exception e) {
            DialogUtil.showError(this, "Ошибка загрузки данных!" );
        }
        agentTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = agentTable.rowAtPoint(e.getPoint());
                    if (row != -1) {
                        dispose();
                        new AgentEditForm(model.getRows().get(row));
                    }
                }
            }
        });
    }

    private void initBoxes() {
        typeFilterBox.addItem("Все");
        try {
            List<AgentEntity> list = AgentEntityManager.selectAll();
            Set<String> types = new HashSet<>();
            for (AgentEntity a : list) {
                types.add(a.getAgentType());
            }
            for (String t : types) {
                typeFilterBox.addItem(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        typeFilterBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    applyFilters();
                }
            }
        });

        titleFilterBox.addItem("Все");
        try {
            List<AgentEntity> list = AgentEntityManager.selectAll();
            Set<String> titles = new HashSet<>();
            for (AgentEntity a : list) {
                titles.add(a.getTitle());
            }
            for (String t : titles) {
                titleFilterBox.addItem(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        titleFilterBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    applyFilters();
                }
            }
        });
    }


    private void applyFilters () {
        try {
            List<AgentEntity> list = AgentEntityManager.selectAll();
            int max = list.size();
            if (typeFilterBox.getSelectedIndex() != 0) {
                list.removeIf(c -> !c.getAgentType().equals(typeFilterBox.getSelectedItem()));
            }
            if (titleFilterBox.getSelectedIndex() != 0) {
                list.removeIf(c -> !c.getTitle().equals(titleFilterBox.getSelectedItem()));
            }
            updateRowCountLabel(list.size(), max);
            model.setRows(list);
            model.fireTableDataChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void initButtons() {
        addButton.addActionListener(e -> {
            dispose();
            new AgentCreateForm();
        });
        dropButton.addActionListener(e -> {
            typeFilterBox.setSelectedIndex(0);
            titleFilterBox.setSelectedIndex(0);
        });
        prioritySortButton.addActionListener(e -> {
            Collections.sort(model.getRows(), new Comparator<AgentEntity>() {
                @Override
                public int compare(AgentEntity o1, AgentEntity o2) {
                    if (sort) {
                        return Integer.compare(o1.getPriority(), o2.getPriority());
                    } else {
                        return Integer.compare(o2.getPriority(), o1.getPriority());
                    }
                }
            });
            sort = !sort;
            model.fireTableDataChanged();
        });
    }

    private void updateRowCountLabel (int actual, int max) {
        rowCountLabel.setText("Записей отображено: " + actual + " / " + max);
    }
}
