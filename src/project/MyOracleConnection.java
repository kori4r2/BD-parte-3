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
            try{
                connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@grad.icmc.usp.br:15215:orcl",
                    username,
                    password);
            }catch(Exception e){
                connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@192.168.183.15:1521:orcl",
                    username,
                    password);
            }
        }
        
        public void insertInto(String table, String attributes){
            try{
                System.out.println("insert into " + table + " values " + attributes);
                pstmt = connection.prepareStatement("insert into " + table + " values " + attributes);
                pstmt.executeUpdate();
                pstmt.close();
            }catch(Exception e){
                System.out.println("Error inserting values " + attributes + " to table " + table);
                System.exit(1);
            }
        }
        
        public String selectMax(String atrib, String table){
            try{
                rs = stmt.executeQuery("select max(" + atrib + ") from " + table);
                rs.next();
                String result = rs.getString("max(" + atrib + ")");
                return result;
            }catch(Exception e){
                System.out.println(e.getMessage());
                return new String("");
            }
        }
        
        public String[] selectColumn(String columnName, String tableName){
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
        public String[] selectColumns(String column1, String column2, String tableName){
            ArrayList<String> list = new ArrayList<String>();
            try{
                stmt = connection.createStatement();
                rs = stmt.executeQuery("select * from " + tableName);
                while(rs.next()){
                    list.add(rs.getString(column1) + " - " + rs.getString(column2));
                }
                
                String[] result = list.toArray(new String[list.size()]);
                
                return result;
            }catch(Exception e){
                System.out.println(e.getMessage());
                return new String[0];
            }
        }
        
        public void close(){
            try{
                stmt.close();
                connection.close();
                System.out.println("Fechou as conexões");
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
}
