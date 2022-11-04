package ceafadepui;
import robocode.*;
import java.awt.Color;
import static robocode.util.Utils.normalRelativeAngleDegrees;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * CeafaDePui - a robot by (your name here)
 */
public class CeafaDePui extends AdvancedRobot
{
	int counterTurnRight = 1;
	final static double BULLET_POWER = 3;
	final static double BULLET_DAMAGE = BULLET_POWER * 4;
	final static double BULLET_SPEED = 20 - 3 * BULLET_POWER;
	

	 
	//double direction = 1;
	/**
	 * run: CeafaDePui's default behavior
	 */
	public void run() {
		turnRadarRightRadians(Double.POSITIVE_INFINITY);
		setColors(Color.yellow,Color.yellow,Color.yellow); // body,gun,radar

		// Robot main loop
		while(true) {
			setTurnRight(5 * counterTurnRight);
			execute();
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		
		//lock the radar on the enemy
		setTurnRadarLeftRadians(getRadarTurnRemainingRadians());
		if (e.getBearing() >= 0)
		{
			counterTurnRight = 1;
		} else {
			counterTurnRight = -1;
		}
		setTurnRight(e.getBearing());
		setMaxVelocity(400 / getTurnRemaining());		

		setAhead(e.getDistance());
	}

	public void onHitRobot(HitRobotEvent e) {
		
		if (e.getBearing() >= 0)
		{
			counterTurnRight = 1;
		} else {
			counterTurnRight = -1;
		}
		setTurnRight(e.getBearing());


		if (e.getEnergy() > 16)
		{
			setFire(3);
		}
		else if (e.getEnergy() > 10)
		{
			setFire(2);
		}
		else if (e.getEnergy() > 4)
		{
			setFire(1);
			setFire(.1);
			execute();
		}
		else if (e.getEnergy() > 2)
		{
			setFire(.5);
		}
		else if (e.getEnergy() > .4)
		{
			setFire(.1);
		}
		setAhead(-5);
		setAhead(40);
	}
	
	public void onWin(WinEvent e) {
		for (int i = 0; i < 50; i++) {
			turnRight(30);
			turnLeft(30);
		}
	}
}
