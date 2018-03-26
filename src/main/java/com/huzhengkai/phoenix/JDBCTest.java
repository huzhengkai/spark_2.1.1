package com.huzhengkai.phoenix;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by root on 2018/3/16.
 */
public class JDBCTest
{
    public static void main(String[] args) throws Exception
    {
        Class.forName("oracle.jdbc.OracleDriver");
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@221.236.20.211:15213:orcl",
                "pcl", "Phpcl321");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from PCLODS.RPT_T_LOAN_WIDE");
        while(rs.next())
        {
            System.out.println(rs.getString("REG_DT"));
        }
    }
}
