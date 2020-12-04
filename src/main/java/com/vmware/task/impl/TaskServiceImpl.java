package com.vmware.task.impl;

import com.vmware.task.model.request.Strategy;
import com.vmware.task.model.response.TaskResponse;
import com.vmware.task.model.response.ThreadTask;
import com.vmware.task.service.TaskService;
import com.vmware.task.util.StatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class TaskServiceImpl implements TaskService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskServiceImpl.class);

    Map<String, ThreadTask> strategyTable = new HashMap<>();

    @Override
    public TaskResponse postService(List<Strategy> strategy) {
        LOGGER.trace(">> postService()");

        String uuid = UUID.randomUUID().toString();

        LOGGER.info("Posting strategy for id : {}", uuid);

        strategyTable.put(uuid, new ThreadTask(strategy));

        LOGGER.trace("<< postService()");
        return new TaskResponse.TaskResponseBuilder().setTask(uuid)
                .build();
    }

    @Override
    public TaskResponse getStatus(String uuid) {
        LOGGER.trace(">> getStatus()");

        String status = strategyTable.containsKey(uuid) ? strategyTable.get(uuid).getStatus() : StatusEnum.ERROR.name();

        LOGGER.info(MessageFormat.format("Status for uuid : {0}, is : {1}", uuid, status));
        LOGGER.trace("<< getStatus()");

        return new TaskResponse.TaskResponseBuilder().setStatus(status)
                .build();
    }

    @Override
    public TaskResponse getResult(String uuid) {
        LOGGER.trace(">> getResult()");

        if (strategyTable.containsKey(uuid)) {
            boolean isBulk = !CollectionUtils.isEmpty(strategyTable.get(uuid).getResults());
            if (isBulk)
                return new TaskResponse.TaskResponseBuilder().setResults(strategyTable.get(uuid).getResults())
                        .build();
            else
                return new TaskResponse.TaskResponseBuilder().setResult(strategyTable.get(uuid).getResult())
                        .build();
        }

        LOGGER.trace("<< getResult()");
        return new TaskResponse.TaskResponseBuilder().setStatus(StatusEnum.ERROR.name())
                .build();

    }
}

