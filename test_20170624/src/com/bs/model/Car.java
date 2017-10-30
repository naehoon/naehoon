package com.bs.model;

import java.io.Serializable;

public class Car implements Serializable{
	String modelName; // 문자열 변수 선언  (정렬)
	int displacement; // 정수형 변수 선언
	String type; // 문자열 변수 선언
	double mileage; // 실수형 변수 선언 (정렬)
	
	public String getModelName() { // getModelName
		return modelName;
	}

	public void setModelName(String modelName) { // setModelName
		this.modelName = modelName;
	}

	public int getDisplacement() { // getDisplacement
		return displacement;
	}

	public void setDisplacement(int displacement) { // setDisplacement
		this.displacement = displacement;
	}

	public String getType() { // getType
		return type;
	}

	public void setType(String type) { // setType
		this.type = type;
	}

	public double getMileage() {
		return mileage;
	}

	public void setMileage(double mileage) {
		this.mileage = mileage;
	}

	@Override
	public String toString() { // Car 객체를 출력하기 위한 toString method 선언
		return "Car [modelName=" + modelName + ", displacement=" + displacement + ", type=" + type + ", mileage="
				+ mileage + "]\n";
	}
}
