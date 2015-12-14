package fr.esigelec.projetStruts.controleur;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import fr.esigelec.projetStruts.dao.*;

public class VoirFamilleAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String nom=request.getParameter("nom");
		
		IPersonneDAO personneDAO=DAOFactory.getDAOFactory(DAOFactory.HIBERNATE).getPersonneDAO();
		request.setAttribute("liste",personneDAO.getListeNom(nom));

		return mapping.findForward("succes");
		
	}
}
