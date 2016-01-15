package net.prolinesystems.plafan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public abstract class AbstractDao {

	protected Connection con;
	protected PreparedStatement pstmt;
	protected ResultSet rs;

}
