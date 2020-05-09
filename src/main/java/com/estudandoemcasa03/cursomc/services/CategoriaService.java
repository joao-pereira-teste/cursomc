package com.estudandoemcasa03.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estudandoemcasa03.cursomc.domain.Categoria;
import com.estudandoemcasa03.cursomc.repositories.CategoriaRepository;
import com.estudandoemcasa03.cursomc.services.exceptions.ObjectNotFoundExcepction;
//import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	/*public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		if (obj == null) {

			throw new ObjectNotFoundExcepction("Objeto não encontrado Id:" + id +
					"Tipo" + Categoria.class.getName());
		}
		return obj.orElse(null);
	}*/
	
	
	public Categoria buscar(Integer id) { 
		 Optional<Categoria> obj = repo.findById(id); 
		return obj.orElseThrow(() -> new ObjectNotFoundExcepction( 
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName())); 
		}

}
