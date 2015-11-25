package fr.esigelec.projetStruts.dao;


public class HibernateDAOFactory extends DAOFactory{
	@Override
	public IPersonneDAO getPersonneDAO() {
		return new fr.esigelec.projetStruts.dao.hibernate.PersonneDAOImpl();
	}

}
