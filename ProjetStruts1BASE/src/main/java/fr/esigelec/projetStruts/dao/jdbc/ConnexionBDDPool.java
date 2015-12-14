package fr.esigelec.projetStruts.dao.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.SQLNestedException;

public class ConnexionBDDPool {

	private static ConnexionBDDPool instance =null; 
	private DataSource datasource;

	private ConnexionBDDPool() {

		Properties prop = new Properties();

		InputStream in = null;
		try {
			// charge le fichier qui se trouve dans le classpath
			in = this.getClass().getResourceAsStream("/bdd.properties");

			prop.load(in);

			datasource = org.apache.commons.dbcp.BasicDataSourceFactory
					.createDataSource(prop);
			((BasicDataSource) datasource).setMaxActive(10);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static ConnexionBDDPool getInstance() {
		if(instance==null)
			instance=new ConnexionBDDPool();
		return instance;
	}

	public Connection getConnection() throws SQLException {
		//System.out.println(((BasicDataSource)datasource).getNumActive());
		//System.out.println(((BasicDataSource)datasource).getNumIdle());
		Connection retour = null;
		retour = datasource.getConnection();
		return retour;
	}

	public static void close(Statement stmt) {
		Connection c = null;
		try {
			c = stmt.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
