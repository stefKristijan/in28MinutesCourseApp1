package com.EM2.in28MinutesCourseApp.daoImpl;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.EM2.in28MinutesCourseApp.dao.ToDoDao;
import com.EM2.in28MinutesCourseApp.model.ToDo;

@Repository
public class ToDoDaoImpl implements ToDoDao{

	private static List<ToDo> todos = new ArrayList<>();
	private static int count=3;
	
	static {
		todos.add(new ToDo(1, "kico", "Learn Spring MVC", new Date(), false));
		todos.add(new ToDo(2, "kico", "Learn Spring Boot", new Date(), false));
		todos.add(new ToDo(3, "kico", "Learn everything", new Date(), false));
	}
	
	@Override
	public List<ToDo> retrieveToDos(String user) {
		List<ToDo> filteredToDos = new ArrayList<>();
		for(ToDo toDo : todos) {
			if(toDo.getUser().equals(user)) {
				filteredToDos.add(toDo);
			}
		}
		return filteredToDos;
	}

	@Override
	public void addToDo(String name, String desc, Date targetDate, boolean isDone) {
		todos.add(new ToDo(++count, name, desc, targetDate, isDone));
	}

	@Override
	public void deleteToDo(int id) {
		Iterator<ToDo> iterator = todos.iterator();
		while(iterator.hasNext()) {
			ToDo toDo = iterator.next();
			if(toDo.getId()==id) {
				iterator.remove();
			}
		}
	}

	@Override
	public ToDo retrieveToDo(int id) {
		for(ToDo toDo: todos) {
			if(toDo.getId()==id) {
				return toDo;
			}
		}
		return null;
	}

	@Override
	public void updateToDo(ToDo toDo) {
		todos.remove(toDo);
		todos.add(toDo);
	}

}
