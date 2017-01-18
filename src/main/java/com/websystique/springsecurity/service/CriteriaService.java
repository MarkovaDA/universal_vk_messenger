package com.websystique.springsecurity.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CriteriaService {

    public Integer notConsideredCriteriaId(Connection connection) {
        int nextCriteriaId = -1;
        try {
            Statement statement = connection.createStatement();
            String sql = "select min(id) as criteriaId from vk_messenger.criteria as B where B.considered=0;";
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                Object task = rs.getObject("criteriaId");
                nextCriteriaId = rs.getInt("criteriaId");
                break;
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(CriteriaService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nextCriteriaId;
    }

    public void updateNotConsidered(Connection connection, int number) {
        String sql = "update vk_messenger.criteria as A "
                + "set A.considered=1 where A.id = ?;";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, number);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CriteriaService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
