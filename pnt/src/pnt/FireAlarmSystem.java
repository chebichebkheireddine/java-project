package pnt;

import java.util.Scanner;

public class FireAlarmSystem {

	boolean isSmokeDetected = false;
	boolean isHeatDetected = false;
	boolean isFireAlarmTriggered = false;
	boolean isSprinklerSystemActivated = false;
	boolean isEmergencyLightingOn = false;
	boolean areOccupantsEvacuating = false;
	int eventCount = 0;
	long startTime;

	public void detectSmoke() {
		isSmokeDetected = true;
		eventCount++;
		System.out.println(
				"[" + eventCount + "] Smoke detected at " + (System.currentTimeMillis() - startTime) + " milliseconds");
	}

	public void detectHeat() {
		isHeatDetected = true;
		eventCount++;
		System.out.println(
				"[" + eventCount + "] Heat detected at " + (System.currentTimeMillis() - startTime) + " milliseconds");
	}

	public void triggerFireAlarm() {
		isFireAlarmTriggered = true;
		eventCount++;
		System.out.println("[" + eventCount + "] Fire alarm triggered at " + (System.currentTimeMillis() - startTime)
				+ " milliseconds");
	}

	public void activateSprinklerSystem() {
		isSprinklerSystemActivated = true;
		eventCount++;
		System.out.println("[" + eventCount + "] Sprinkler system activated at "
				+ (System.currentTimeMillis() - startTime) + " milliseconds");
	}

	public void turnOnEmergencyLighting() {
		isEmergencyLightingOn = true;
		eventCount++;
		System.out.println("[" + eventCount + "] Emergency lighting turned on at "
				+ (System.currentTimeMillis() - startTime) + " milliseconds");
	}

	public void evacuateOccupants() {
		areOccupantsEvacuating = true;
		eventCount++;
		System.out.println("[" + eventCount + "] Occupants begin evacuation at "
				+ (System.currentTimeMillis() - startTime) + " milliseconds");
	}

	public void smokeDetectorSendsSignal() {
		if (!isSmokeDetected) {
			detectSmoke();
			triggerFireAlarm();
		}
	}

	public void fireAlarmTriggersAlertSystem() {
		if (isFireAlarmTriggered) {
			activateSprinklerSystem();
		}
	}

	public void alertSystemActivatesSprinklerSystem() {
		if (isSprinklerSystemActivated) {
			eventCount++;
			System.out.println("[" + eventCount + "] Fire extinguished at " + (System.currentTimeMillis() - startTime)
					+ " milliseconds");
		}
	}

	public void powerLost() {
		turnOnEmergencyLighting();
	}

	public void occupantsFollowEvacuationRoutes() {
		if (isFireAlarmTriggered) {
			evacuateOccupants();
		}
	}

	public static void main(String[] args) {
		FireAlarmSystem system = new FireAlarmSystem();
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter the duration of the simulation in seconds: ");
		int duration = scanner.nextInt() * 1000;
		long startTime = System.currentTimeMillis();

		while (System.currentTimeMillis() - startTime < duration) {
			if (System.currentTimeMillis() - startTime < 5000) {
				// Test the smoke detector sending a signal to the fire alarm
				system.smokeDetectorSendsSignal();
			} else if (System.currentTimeMillis() - startTime < 10000) {
				// Test the fire alarm triggering the alert system
				system.fireAlarmTriggersAlertSystem();
			} else if (System.currentTimeMillis() - startTime < 15000) {
				// Test the alert system activating the sprinkler system
				system.alertSystemActivatesSprinklerSystem();
			} else if (System.currentTimeMillis() - startTime < 20000) {
				// Test power loss
				system.powerLost();
			} else if (System.currentTimeMillis() - startTime < 25000) {
				// Test occupants following evacuation routes
				system.occupantsFollowEvacuationRoutes();
			} else {
				break;
			}
		}
		scanner.close();
	}
}