package fr.esigelec.projetStruts.dao;

public abstract class DAOFactory {
	public static final int HIBERNATE = 1;
	public static final int JDBC = 2;
	public abstract IPersonneDAO getPersonneDAO();
	
	public static DAOFactory getDAOFactory(int model){
		switch(model){
		case HIBERNATE :
			return new HibernateDAOFactory();
		case JDBC : 
			return new PersonneDAOFactory();
		default :
			return new HibernateDAOFactory();
		}
	}
}
