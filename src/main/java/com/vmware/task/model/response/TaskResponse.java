package com.vmware.task.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskResponse {

    private String task;

    private String status;

    private String result;

    private List<String> results;

    private TaskResponse() {
    }

    private TaskResponse(TaskResponseBuilder trb) {
        this.task = trb.task;
        this.status = trb.status;
        this.result = trb.result;
        this.results = trb.results;
    }

    public String getTask() {
        return task;
    }

    public String getStatus() {
        return status;
    }

    public String getResult() {
        return result;
    }

	public List<String> getResults() {
		return results;
	}

    public static class TaskResponseBuilder {

        private String task;

        private String status;

        private String result;

        private List<String> results;

        public TaskResponseBuilder setTask(String task) {
            this.task = task;
            return this;
        }

        public TaskResponseBuilder setStatus(String status) {
            this.status = status;
            return this;
        }

        public TaskResponseBuilder setResult(String result) {
            this.result = result;
            return this;
        }

        public TaskResponseBuilder setResults(List<String> results) {
            this.results = results;
            return this;
        }

        public TaskResponse build() {
            return new TaskResponse(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskResponse response = (TaskResponse) o;
        return Objects.equals(task, response.task) &&
                Objects.equals(status, response.status) &&
                Objects.equals(result, response.result) &&
                Objects.equals(results, response.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(task, status, result, results);
    }

    @Override
    public String toString() {
        return "TaskResponse{" +
                "task='" + task + '\'' +
                ", status='" + status + '\'' +
                ", result='" + result + '\'' +
                ", results=" + results +
                '}';
    }
}
