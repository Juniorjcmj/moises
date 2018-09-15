package br.com.moises.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.moises.model.Funcionario;
import br.com.moises.service.exception.FuncionarioService;
import javassist.tools.rmi.ObjectNotFoundException;

@Controller
@RequestMapping("/funcionario")
public class FuncionarioController {
	
	@Autowired
	FuncionarioService service;
	
	//Vai para tela principal do CRUD aonde são listados todos os posts
	@GetMapping("/")
	public ModelAndView findAll() {
		
		ModelAndView mv = new ModelAndView("/funcionario");
		mv.addObject("funcionarios", service.findAll());
		return mv;
	}
	
	// daqui para baixo
	//Vai para tela de adição de post
		@GetMapping("/add")
		public ModelAndView add(Funcionario funcionario) {
			
			ModelAndView mv = new ModelAndView("/funcionarioAdd");
			mv.addObject("funcionario", funcionario);
			
			return mv;
		}
		
		//Vai para tela de edição de oms (mesma tela de adição, contudo é enviado para a view um objeto que já existe)
		@GetMapping("/edit/{id}")
		public ModelAndView edit(@PathVariable("id") Integer id) throws ObjectNotFoundException {
			
			return add(service.find(id));
		}
		
		//Exclui um post por seu ID e redireciona para a tela principal
		@GetMapping("/delete/{id}")
		public ModelAndView delete(@PathVariable("id") Integer id) throws ObjectNotFoundException {
			
			service.delete(id);
			
			return findAll();
		}
		
		//Recebe um objeto preenchido do Thymeleaf e valida 
		//Se tudo estiver ok, salva e volta para tela principal
		//Se houver erro, retorna para tela atual exibindo as mensagens de erro
		@PostMapping("/save")
		public ModelAndView save(@Valid Funcionario funcionario, BindingResult result) throws ObjectNotFoundException {
			
			if(result.hasErrors()) {
				return add(funcionario);
			}
				
		service.insert(funcionario);
			
			
			
			return findAll();
		}
		

}
