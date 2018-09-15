package br.com.moises.service.exception;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.moises.model.Funcionario;
import br.com.moises.repository.FuncionarioRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class FuncionarioService {

	@Autowired
	FuncionarioRepository repository;

	public List<Funcionario> findAll() {
		List<Funcionario> list = repository.findAll();
		return list;
	}

	public Funcionario find(Integer id) throws ObjectNotFoundException {
		Optional<Funcionario> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado!:" + id + ", Tipo : " + Funcionario.class.getName()));
	}

	public void delete(Integer id) throws ObjectNotFoundException {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir porque há entidades relacionadas");
		}

	}

	public Funcionario insert(Funcionario obj) throws ObjectNotFoundException {
			
		return repository.save(obj);
		
	}

	public Funcionario update(Funcionario obj) throws ObjectNotFoundException {

		Funcionario newObj = find(obj.getId());

		return repository.saveAndFlush(newObj);
	}
	

}
