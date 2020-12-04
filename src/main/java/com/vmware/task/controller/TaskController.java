package com.vmware.task.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vmware.task.model.request.Strategy;
import com.vmware.task.model.response.TaskResponse;
import com.vmware.task.service.TaskService;

@RestController
@RequestMapping(value = "/api")
public class TaskController {

	@Autowired
	TaskService taskService;
	
	@PostMapping(path = "/generate")
	public ResponseEntity<TaskResponse> postStrategy(@RequestBody Strategy strategy) {
		TaskResponse response = taskService.postService(Arrays.asList(strategy));
		return ResponseEntity.accepted().body(response);
	}
	
	@GetMapping(path = "/tasks/{uuid}/status")
	public ResponseEntity<TaskResponse> getStatus(@PathVariable String uuid) {
		TaskResponse response = taskService.getStatus(uuid);
		return ResponseEntity.accepted().body(response);
	}
	
	@GetMapping(path = "/tasks/{uuid}")
	public ResponseEntity<TaskResponse> getResultForAction(@PathVariable String uuid, @RequestParam String action) {
		if(action.equals("get_numlist")){
			TaskResponse response = taskService.getResult(uuid);
			return ResponseEntity.accepted().body(response);
		}
		else return ResponseEntity.badRequest().build();
	}
	
	@PostMapping(path = "/bulkGenerate")
	public ResponseEntity<TaskResponse> postBulkStrategy(@RequestBody List<Strategy> strategies) {
		TaskResponse response = taskService.postService(strategies);
		return ResponseEntity.accepted().body(response);
	}
	
}
