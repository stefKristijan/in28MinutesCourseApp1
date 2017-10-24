package com.EM2.in28MinutesCourseApp.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.EM2.in28MinutesCourseApp.model.ToDo;
import com.EM2.in28MinutesCourseApp.service.ToDoService;

//@SessionAttributes("name") ne treba jer se name može dobit iz authentificationa
@Controller
public class ToDoController {
	
	
	@Autowired
	ToDoService toDoService;
	
	
	/*@InitBinder anotacija se koristi za inicjalizaciju bindera koji mapira objekte tj. povezuje
	 * atribute nekog modela ili sami objekt s atributima ili objektima unutar određenog view-a
	 * na temelju imena atributa definiranih u klasi modela
	 * u ovom primjeru se u metodi registerCustomEditor() definira kako će se formatirati objekti
	 * klase Date
	 */
	@InitBinder
	public void InitBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy.");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	
	/*Prilikom pristupanja URI-ju /list-todos metodom GET se u ModelMap objekt dodaju 
	 * svi ToDo objekti koji pripadaju prijavljenom korisniku (prijavljeni korisnik tj.
	 * njegovo ime se dobije iz @SessionAttribute("name") pomoću metode get() ModelMap klase
	 */
	@RequestMapping(value = "/list-todos", method = RequestMethod.GET)
	public String showTodos(ModelMap model) {
		model.put("todos", this.toDoService.retrieveToDos(SecurityContextHolder.getContext().getAuthentication().getName()));
		return "list-todos";
	}

	
	/*Prilikom pristupanja URI-ju /add-todo metodom GET je zbog commandName="todo" u View-u
	 * tj. zbog Spring Form Tagova potrebno predati i @ModelAttribute tj. ToDo objekt jer će 
	 * se u suprotnom javiti exception ako objekt todo nije definiran (može se u samom modelu
	 * definirati defaultni konstruktor ili u samoj metodi se treba inicijalizirati toDo objekt
	 * toDo = new ToDo(params);
	 */
	@RequestMapping(value = "/add-todo", method = RequestMethod.GET)
	public String showAddTodoPage(ModelMap model, @ModelAttribute("todo") ToDo toDo) {
		return "todo";
	}
	
	/*Prilikom POST metode se uz pomoć anotacije @Valid (na temelju definiranih validacijskih anotacija 
	 * u modelu) utvrđuje jesu li vrijednosti atributa objekta todo valjani, ukoliko nisu to registrira
	 * BindingResult objekt i u View (ukoliko postoji element koji prikazuje error-e) ispisuje zbog čega
	 * atribut nije valjan
	 * 
	 * redirect:/list-todos označava da se pozove metoda mapirana na /list-todos - showTodos() tj. da se
	 * učita view s osvježenim podacima
	 */
	@RequestMapping(value = "/add-todo", method = RequestMethod.POST)
	public String addTodo(ModelMap model, @Valid @ModelAttribute("todo") ToDo todo, BindingResult result) {
		if(result.hasErrors()){
			return "todo";
		}
		this.toDoService.addToDo((String) model.get("name"), todo.getDesc(), todo.getTargetDate() ,false);
		return "redirect:/list-todos";
	}

	
	@RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
	public String deleteTodo(@RequestParam int id) {
		toDoService.deleteToDo(id);
		return "redirect:/list-todos";
	}


	/*U ovoj metodi se na temelju kliknutog gumba Edit iz liste ToDo-a 
	 * predaje id ToDo-a i on se sprema u hidden field kako bi se taj
	 * id mogao koristiti i za POST metodu, inače bi prilikom POST-anja
	 * atribut id objekta toDo bio null što bi izazvalo exception
	 */
	@RequestMapping(value = "/update-todo", method = RequestMethod.GET)
	public String showUpdateTodo(@RequestParam int id, ModelMap model) {
		ToDo toDo = this.toDoService.retrieveToDo(id);
		model.put("todo",toDo);
		return "todo";
	}
	
	/*Iz istog razloga zašto je negdje potrebno čuvati id, potrebno je i 
	 * postaviti vrijednost datuma jer bi prilikom POST-anja on bio null
	 * ZAKLJUČAK: Ako se koristi @ModelAttribute nijedan atribut ne smije 
	 * imati vrijednost null!
	 */
	@RequestMapping(value = "/update-todo", method = RequestMethod.POST)
	public String updateTodo(@Valid @ModelAttribute("todo") ToDo toDo, BindingResult result, ModelMap model) {
		if(result.hasErrors()){
			return "todo";
		}
		toDo.setUser((String) model.get("name"));
		this.toDoService.updateToDo(toDo);
		return "redirect:/list-todos";
	}
	
}
