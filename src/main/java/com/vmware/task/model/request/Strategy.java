package com.vmware.task.model.request;

import java.util.Objects;

public class Strategy {

	private Integer goal;
	
	private Integer step;

	public Integer getGoal() {
		return goal;
	}

	public void setGoal(Integer goal) {
		this.goal = goal;
	}

	public Integer getStep() {
		return step;
	}

	public void setStep(Integer step) {
		this.step = step;
	}

	public Strategy(){}

	public Strategy(Integer goal, Integer step) {
		this.goal = goal;
		this.step = step;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Strategy strategy = (Strategy) o;
		return Objects.equals(goal, strategy.goal) &&
				Objects.equals(step, strategy.step);
	}

	@Override
	public int hashCode() {
		return Objects.hash(goal, step);
	}

	@Override
	public String toString() {
		return "Strategy{" +
				"goal=" + goal +
				", step=" + step +
				'}';
	}
}
