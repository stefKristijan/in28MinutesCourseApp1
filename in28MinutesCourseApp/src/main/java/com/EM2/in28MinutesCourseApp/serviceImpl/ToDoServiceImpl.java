package com.EM2.in28MinutesCourseApp.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EM2.in28MinutesCourseApp.dao.ToDoDao;
import com.EM2.in28MinutesCourseApp.model.ToDo;
import com.EM2.in28MinutesCourseApp.service.ToDoService;

@Service
public class ToDoServiceImpl implements ToDoService{

	@Autowired
	ToDoDao todoDao;
	
	@Override
	public List<ToDo> retrieveToDos(String user) {
		return this.todoDao.retrieveToDos(user);
	}

	@Override
	public void addToDo(String name, String desc, Date targetDate, boolean isDone) {
		this.todoDao.addToDo(name, desc, targetDate, isDone);
	}

	@Override
	public void deleteToDo(int id) {
		this.todoDao.deleteToDo(id);
	}

	@Override
	public ToDo retrieveToDo(int id) {
		return this.todoDao.retrieveToDo(id);
	}

	@Override
	public void updateToDo(ToDo toDo) {
		this.todoDao.updateToDo(toDo);
		
	}

}
