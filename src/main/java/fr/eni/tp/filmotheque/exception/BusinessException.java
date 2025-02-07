package fr.eni.tp.filmotheque.exception;

import java.util.ArrayList;
import java.util.List;

public class BusinessException extends Exception {
	private List<String> clesErreurs;

	public List<String> getClesErreurs() {
		return clesErreurs;
	}
	
	public void addCleErreur(String cleErreur) {
		if(clesErreurs == null) {
			clesErreurs = new ArrayList<String>();
		}
		
		clesErreurs.add(cleErreur);
	}
}
