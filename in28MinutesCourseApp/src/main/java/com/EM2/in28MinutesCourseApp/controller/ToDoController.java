package com.EM2.in28MinutesCourseApp.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.EM2.in28MinutesCourseApp.model.ToDo;
import com.EM2.in28MinutesCourseApp.service.ToDoService;


@Controller
@SessionAttributes("name")
public class ToDoController {
	
	@Autowired
	ToDoService toDoService;
	
	@RequestMapping(value = "/list-todos", method = RequestMethod.GET)
	public String showTodos(ModelMap model) {
		model.put("todos", this.toDoService.retrieveToDos((String) model.get("name")));
		return "list-todos";
	}

	@RequestMapping(value = "/add-todo", method = RequestMethod.GET)
	public String showAddTodoPage(ModelMap model, @ModelAttribute("todo") ToDo toDo) {
		return "todo";
	}

	@RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
	public String deleteTodo(@RequestParam int id) {
		toDoService.deleteToDo(id);
		return "redirect:/list-todos";
	}

	@RequestMapping(value = "/add-todo", method = RequestMethod.POST)
	public String addTodo(ModelMap model, @Valid @ModelAttribute("todo") ToDo todo, BindingResult result) {
		if(result.hasErrors()){
			return "todo";
		}
		this.toDoService.addToDo((String) model.get("name"), todo.getDesc(), new Date(),false);
		return "redirect:/list-todos";
	}
	
	@RequestMapping(value = "/update-todo", method = RequestMethod.GET)
	public String showUpdateTodo(@RequestParam int id, ModelMap model) {
		ToDo toDo = this.toDoService.retrieveToDo(id);
		model.put("todo",toDo);
		return "todo";
	}
	
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
