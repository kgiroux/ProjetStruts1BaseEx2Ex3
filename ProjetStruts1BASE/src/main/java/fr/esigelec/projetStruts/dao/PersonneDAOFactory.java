package fr.esigelec.projetStruts.dao;

import org.hibernate.engine.HibernateIterator;

public class PersonneDAOFactory extends DAOFactory{
	@Override
	public  IPersonneDAO getPersonneDAO(){
		return new fr.esigelec.projetStruts.dao.jdbc.PersonneDAOImpl(); 
	}
	
}
