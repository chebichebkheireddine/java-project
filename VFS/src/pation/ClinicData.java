package pation;

import java.util.Queue;

public class ClinicData {
	private int salesCount;
	private int doctorCount;
	private Queue<Sall> salleQueue;
	private Queue<Doctor> doctorList;

	public ClinicData() {
	}

	public int getSalesCount() {
		return salesCount;
	}

	public void setSalesCount(int salesCount) {
		this.salesCount = salesCount;
	}

	public int getDoctorCount() {
		return doctorCount;
	}

	public void setDoctorCount(int doctorCount) {
		this.doctorCount = doctorCount;
	}

	public Queue<Sall> getSalleQueue() {
		return salleQueue;
	}

	public void setSalleQueue(Queue<Sall> salleQueue) {
		this.salleQueue = salleQueue;
	}

	public Queue<Doctor> getDoctorList() {
		return doctorList;
	}

	public void setDoctorList(Queue<Doctor> doctorList) {
		this.doctorList = doctorList;
	}
}