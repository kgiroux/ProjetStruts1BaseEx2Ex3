package fr.esigelec.projetStruts.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class PersonneForm extends ActionForm {
String nom;
String prenom;
int age;

public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public String getPrenom() {
	return prenom;
}
public void setPrenom(String prenom) {
	this.prenom = prenom;
}
public String toString(){
	return nom+" "+prenom;
}
@Override
public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
	
	ActionErrors errors=new ActionErrors();
	if("".equals(nom))
			errors.add("erreur.nom",new ActionMessage("erreur.nom.vide"));
	else if (nom.length() > 12)
			errors.add("erreur.nom.too_long", new ActionMessage("erreur.nom.too_long"));
	if("".equals(prenom))
			errors.add("erreur.prenom",new ActionMessage("erreur.prenom.vide"));
	else if(prenom.length() > 12)
			errors.add("erreur.prenom.too_long", new ActionMessage("erreur.prenom.too_long"));
	
	if("0".equals(age))
			errors.add("erreur.age",new ActionMessage("erreur.age.vide"));
	else if( age > 90 || age < 16)
			errors.add("erreur.age",new ActionMessage("erreur.age.limit"));
	return errors;
}


}
