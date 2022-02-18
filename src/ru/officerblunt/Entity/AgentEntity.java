package ru.officerblunt.Entity;

import lombok.Data;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

@Data
public class AgentEntity {
    private int ID;
    private String title;
    private String agentType;
    private String address;
    private String phone;
    private String email;
    private String logo;
    private int priority;
    private ImageIcon image;


    public AgentEntity(String title, String agentType, String address, String phone, String email, String logo, int priority) {
        this.title = title;
        this.agentType = agentType;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.logo = logo;
        this.priority = priority;
    }

    public AgentEntity(int ID, String title, String agentType, String address, String phone, String email, String logo, int priority) {
        this.ID = ID;
        this.title = title;
        this.agentType = agentType;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.logo = logo;
        this.priority = priority;
        try {
            this.image = new ImageIcon(
                    ImageIO.read(AgentEntity.class.getClassLoader().getResource(logo))
                            .getScaledInstance(50, 50, Image.SCALE_DEFAULT)
            );
        } catch (Exception e) {
        }
    }
}
