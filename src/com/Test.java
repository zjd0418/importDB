package com;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {
	public static void  ImportCopy() throws SQLException{
		ReadMsSql rm = new ReadMsSql();
		ImportMysql im = new ImportMysql();
		ResultSet rs = rm.getMsSqlData("stu");
		while(rs.next()){
			im.ImportCopyDB(rs.getString("name"),rs.getInt("age"),rs.getTimestamp("releaseDate"));
		}
		rm.Close();
		im.Close();
	}
	public static void main(String[] args) throws SQLException{
		Test.ImportCopy();
	}

}
