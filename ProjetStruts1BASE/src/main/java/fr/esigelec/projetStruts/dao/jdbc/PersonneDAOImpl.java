package fr.esigelec.projetStruts.dao.jdbc;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import fr.esigelec.projetStruts.dao.IPersonneDAO;
import fr.esigelec.projetStruts.dto.Personne;

public class PersonneDAOImpl implements IPersonneDAO {

	
	/**
	 * Ajoute une personne dans la BDD
	 * @param p la personne à ajouter
	 * @throws SQLException 
	 */
	@Override
	public void ajouter(Personne p) throws SQLException {
		PreparedStatement stmt=null;
		Connection con=ConnexionBDDPool.getInstance().getConnection();
		stmt=con.prepareStatement("INSERT INTO personne (nom,prenom) VALUES (?,?)");
		stmt.setString(1,p.getNom());
		stmt.setString(2,p.getPrenom());
		stmt.executeUpdate();
		ConnexionBDDPool.close(stmt);
	}

	/**
	 * Récupère la liste des personne de la BDD
	 * @return la liste des personnes
	 * @throws SQLException 
	 */
	@Override
	public List<Personne> getListe() throws SQLException {
		List<Personne> retour=new ArrayList<Personne>();
		PreparedStatement stmt=null;
		Connection con=ConnexionBDDPool.getInstance().getConnection();
		stmt=con.prepareStatement("SELECT * FROM  personne");
		ResultSet rs=stmt.executeQuery();
		while(rs.next()){
			retour.add(new Personne(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom")));		
		}
		ConnexionBDDPool.close(stmt);
		
		return retour;
	}
	
	
	/**
	 * Récupère la liste des personne de la BDD ayant le même nom
	 * @param personne dont le nom sert de recherche
	 * @return la liste des personnes possédant le même nom
	 * @throws SQLException 
	 */
	@Override
	public List<Personne> getListeNom(String nom) throws SQLException {
		List<Personne> retour=new ArrayList<Personne>();
		PreparedStatement stmt=null;
		Connection con=ConnexionBDDPool.getInstance().getConnection();
		stmt=con.prepareStatement("SELECT * FROM  personne WHERE nom = ?");
		stmt.setString(1, nom);
		ResultSet rs=stmt.executeQuery();
		while(rs.next()){
			retour.add(new Personne(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom")));
		}
		ConnexionBDDPool.close(stmt);
		return retour;
	}


	/**
	 * Supprime une personne de la BDD à partir de son id
	 * @param id id de la personne à supprimer
	 * @throws SQLException 
	 */
	@Override
	public void supprimer(int id) throws SQLException {
		PreparedStatement stmt=null;
		Connection con=ConnexionBDDPool.getInstance().getConnection();
		stmt=con.prepareStatement("DELETE FROM personne WHERE id=?");
		stmt.setInt(1,id);
		stmt.executeUpdate();
		ConnexionBDDPool.close(stmt);
	}
}
