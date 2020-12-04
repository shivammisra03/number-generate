package com.vmware.task.model.response;

import com.vmware.task.model.request.Strategy;
import com.vmware.task.util.StatusEnum;

import java.util.ArrayList;
import java.util.List;

public class ThreadTask extends Thread {

    private List<Strategy> strategies;

    private String status;

    private String result;

    private List<String> results;

    public String getStatus() {
        return status;
    }

    public String getResult() {
        return result;
    }

    public List<String> getResults() {
        return results;
    }

    public ThreadTask(List<Strategy> strategies){
        this.strategies = strategies;
        this.result = "";
        status = StatusEnum.IN_PROGRESS.name();
        start();
    }

    @Override
    public void run() {
        List<String> resultList = new ArrayList<>();
        for(Strategy strategy : strategies){
            for (int i = strategy.getGoal(); i >= 0; i -= strategy.getStep()) {
                    result += "," + i;
            }
            resultList.add(result.substring(1));
            result = "";
        }
        status = StatusEnum.SUCCESS.name();

        if(strategies.size() == 1)
            result = resultList.get(0);
        else results = resultList;

    }
}
