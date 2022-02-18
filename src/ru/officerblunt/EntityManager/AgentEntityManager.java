package ru.officerblunt.EntityManager;

import ru.officerblunt.Entity.AgentEntity;
import ru.officerblunt.Main;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgentEntityManager {
    public static void insert (AgentEntity a) throws SQLException {
        try (Connection c = Main.getConnection()) {
            String sql = "INSERT INTO agent (title, agentType, address, phone, email, logo, priority) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, a.getTitle());
            ps.setString(2, a.getAgentType());
            ps.setString(3, a.getAddress());
            ps.setString(4, a.getPhone());
            ps.setString(5, a.getEmail());
            ps.setString(6, a.getLogo());
            ps.setInt(7, a.getPriority());
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                a.setID(keys.getInt(1));
                return;
            }
            throw new SQLException("Entity not added!");
        }
    }
    public static void update (AgentEntity a) throws SQLException {
        try (Connection c = Main.getConnection()) {
            String sql = "UPDATE agent SET title = ?, agentType = ?, address = ?, phone = ?, email = ?, logo = ?, priority = ? WHERE ID = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, a.getTitle());
            ps.setString(2, a.getAgentType());
            ps.setString(3, a.getAddress());
            ps.setString(4, a.getPhone());
            ps.setString(5, a.getEmail());
            ps.setString(6, a.getLogo());
            ps.setInt(7, a.getPriority());
            ps.setInt(8, a.getID());
            ps.executeUpdate();
        }
    }

    public static AgentEntity selectByID (int id) throws SQLException {
        try (Connection c = Main.getConnection()) {
            String sql = "SELECT * FROM agent WHERE ID = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return new AgentEntity(
                        resultSet.getInt("ID"),
                        resultSet.getString("Title"),
                        resultSet.getString("agentType"),
                        resultSet.getString("address"),
                        resultSet.getString("phone"),
                        resultSet.getString("email"),
                        resultSet.getString("logo"),
                        resultSet.getInt("Priority")
                );
            }
            return null;
        }
    }
    public static List<AgentEntity> selectAll() throws SQLException {
        try (Connection c = Main.getConnection()) {

            String sql = "SELECT * FROM agent";
            Statement s = c.createStatement();
            ResultSet resultSet = s.executeQuery(sql);

            List<AgentEntity> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add (new AgentEntity(
                        resultSet.getInt("ID"),
                        resultSet.getString("Title"),
                        resultSet.getString("agentType"),
                        resultSet.getString("address"),
                        resultSet.getString("phone"),
                        resultSet.getString("email"),
                        resultSet.getString("logo"),
                        resultSet.getInt("Priority")
                        ));
            }
            return list;
        }
    }
    public static void delete (AgentEntity a) throws SQLException {
        try (Connection c = Main.getConnection()) {
            String sql = "DELETE FROM agent WHERE ID = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, a.getID());
            ps.executeUpdate();
        }
    }
}
