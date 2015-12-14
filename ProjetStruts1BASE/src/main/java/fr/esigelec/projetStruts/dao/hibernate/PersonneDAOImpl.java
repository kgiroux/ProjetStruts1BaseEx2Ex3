package fr.esigelec.projetStruts.dao.hibernate;

import java.security.InvalidParameterException;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import fr.esigelec.projetStruts.dao.hibernate.HibernateUtil;
import fr.esigelec.projetStruts.dao.IPersonneDAO;
import fr.esigelec.projetStruts.dto.Personne;

public class PersonneDAOImpl implements IPersonneDAO {
	/**
	 * Ajoute une personne dans la BDD
	 * @param p la personne à ajouter
	 */
	@Override
	public void ajouter(Personne p) {
		if(p != null){
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(p);
			session.getTransaction().commit();
			session.close();
		}else{
			throw new IllegalArgumentException("Null Parameter");
		}
	}

	/**
	 * Récupère la liste des personne de la BDD
	 * @return la liste des personnes
	 */
	@Override
	public List<Personne> getListe() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "from Personne";
		Query query = session.createQuery(hql);
		List<Personne> personne=query.list();
		session.getTransaction().commit();
		session.close();
		return personne;
	}
	
	/**
	 * Récupère la liste des personne de la BDD ayant le même nom
	 * @param personne dont le nom sert de recherche
	 * @return la liste des personnes possédant le même nom
	 */
	@Override
	public List<Personne> getListeNom(String nom) {
		if(!("".equals(nom))){
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			String hql = "from Personne where nom =:name";
			Query query = session.createQuery(hql);
			query.setText("name", nom);
			List<Personne> p = query.list();
			session.getTransaction().commit();
			session.close();
		return p;
		}else{
			throw new InvalidParameterException();
		}
	}

	/**
	 * Supprime une personne de la BDD à partir de son id
	 * @param id id de la personne à supprimer
	 */
	@Override
	public void supprimer(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "from Personne where id =:idt";
		Query query = session.createQuery(hql);
		query.setInteger("idt", id);
		Personne p = (Personne) query.list().get(0);
		session.getTransaction().commit();
		session.beginTransaction();
		try{
			session.delete(p);
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally{
			session.close();
		}
	}
}
