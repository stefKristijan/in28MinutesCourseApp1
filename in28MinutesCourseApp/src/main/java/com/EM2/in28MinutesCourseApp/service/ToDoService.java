package com.EM2.in28MinutesCourseApp.service;

import java.util.Date;
import java.util.List;

import com.EM2.in28MinutesCourseApp.model.ToDo;

public interface ToDoService {

	List<ToDo> retrieveToDos(String user);
	void addToDo(String name, String desc, Date targetDate, boolean isDone);
	void deleteToDo(int id);
	ToDo retrieveToDo(int id);
	void updateToDo(ToDo toDo);
}
