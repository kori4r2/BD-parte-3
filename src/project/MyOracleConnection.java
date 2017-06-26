package project;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Italo
 */
public class MyOracleConnection {
    
        private Statement stmt;
        private ResultSet rs;
        private Connection connection;
        private PreparedStatement pstmt;
        
        public MyOracleConnection(String username, String password) throws Exception{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(
                "jdbc:oracle:thin:@grad.icmc.usp.br:15215:orcl",
                username,
                password);
        }
        
        public String[] Selection(String columnName, String tableName){
            ArrayList<String> list = new ArrayList<String>();
            try{
                stmt = connection.createStatement();
                rs = stmt.executeQuery("select * from " + tableName);
                while(rs.next()){
                    list.add(rs.getString(columnName));
                }
                
                String[] result = list.toArray(new String[list.size()]);
                
                return result;
            }catch(Exception e){
                System.out.println(e.getMessage());
                return new String[0];
            }
        }
}
