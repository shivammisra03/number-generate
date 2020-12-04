package com.vmware.task.service;

import com.vmware.task.model.request.Strategy;
import com.vmware.task.model.response.TaskResponse;

import java.util.List;

public interface TaskService {

	/**
	 * post request to the service with goal and steps
	 *
	 * @param strategy
	 * @return taskResponse
	 */
	TaskResponse postService(List<Strategy> strategy);

	/**
	 * get the status for a particular uuid
	 *
	 * @param uuid
	 * @return
	 */
	TaskResponse getStatus(String uuid);

	/**
	 * get the result for the corresponding uuid
	 *
	 * @param uuid
	 * @return
	 */
	TaskResponse getResult(String uuid);

}
