package com.vmware.task;

import com.vmware.task.controller.TaskController;
import com.vmware.task.model.request.Strategy;
import com.vmware.task.model.response.TaskResponse;
import com.vmware.task.util.StatusEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
class TaskApplicationTests {

	@Autowired
	TaskController taskController;

	@Test
	void testPostStrategy() {
		Strategy strategy = new Strategy(10, 2);
		ResponseEntity<TaskResponse> taskResponse = taskController.postStrategy(strategy);
		assertNotNull(taskResponse.getBody().getTask());
	}

	@Test
	void testBulkPostStrategy() {
		Strategy strategy1 = new Strategy(10, 2);
		Strategy strategy2 = new Strategy(100, 3);

		List<Strategy> strategyList = new ArrayList<>();
		strategyList.add(strategy1);
		strategyList.add(strategy2);
		ResponseEntity<TaskResponse> taskResponse = taskController.postBulkStrategy(strategyList);
		assertNotNull(taskResponse.getBody().getTask());
	}

	@Test
	void testPostAndGetStrategy() {
		Strategy strategy = new Strategy(10, 2);
		ResponseEntity<TaskResponse> taskResponse = taskController.postStrategy(strategy);
		String uuid = taskResponse.getBody().getTask();

		ResponseEntity<TaskResponse> getStatusResp = taskController.getStatus(uuid);

		assertNotEquals(StatusEnum.ERROR.name(), getStatusResp.getBody().getStatus());

	}

	@Test
	void testPostAndGetNumList() {
		Strategy strategy = new Strategy(10, 2);
		ResponseEntity<TaskResponse> taskResponse = taskController.postStrategy(strategy);
		String uuid = taskResponse.getBody().getTask();

		ResponseEntity<TaskResponse> getStatusResp = taskController.getStatus(uuid);
		assertNotEquals(StatusEnum.ERROR.name(), getStatusResp.getBody().getStatus());
		if(StatusEnum.SUCCESS.name().equals(getStatusResp.getBody())){
			ResponseEntity<TaskResponse> getNumList = taskController.getResultForAction(uuid, "get_numlist");
			assertEquals("10,8,6,4,2,0", getNumList.getBody().getResult());
		}
	}

	@Test
	void testBulkPostStrategyForPerformance() {
		Strategy strategy = new Strategy(100000, 1);

		List<Strategy> strategyList = new ArrayList<>();
		for(int i = 0; i < 10000; i++){
			strategyList.add(strategy);
		}
		ResponseEntity<TaskResponse> taskResponse = taskController.postBulkStrategy(strategyList);
		String uuid = taskResponse.getBody().getTask();

		ResponseEntity<TaskResponse> statusTaskResponse = taskController.getStatus(uuid);

		assertEquals(StatusEnum.IN_PROGRESS.name(), statusTaskResponse.getBody().getStatus());
	}

}
