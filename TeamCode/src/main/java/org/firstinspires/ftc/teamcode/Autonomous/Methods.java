package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by skc5741 on 2/15/18.
 */

public class Methods extends Setup {

    public int stage = 0;
    public long encoderSet = 0;

    public void resetEncoders() {
        encoderSet = motorRightBack.getCurrentPosition();
    }

    public void forward(double rotations, double speed) {

        motorRightFront.setPower(speed*balancer);
        motorLeftFront.setPower(speed*balancer);
        motorRightBack.setPower(speed*(1/balancer));
        motorLeftBack.setPower(speed*(1/balancer));
        int distance = ticks(rotations);
        telemetry.addData("Distance","Distance:  "+String.format("%.9s",distance));
        long currentDistance = Math.abs(motorRightBack.getCurrentPosition() - encoderSet);
        telemetry.addData("CurrentDistance","CurrentDistance:  "+String.format("%.9s",currentDistance));

        if (currentDistance >= distance) {
            motorOFF();
            encoderSet = motorRightBack.getCurrentPosition();
            stage ++;
        }
    }

    public void turnRight(double rotations, double speed)
    {

        motorRightFront.setPower(-speed*balancer);
        motorLeftFront.setPower(speed*balancer);
        motorRightBack.setPower(-speed*(1/balancer));
        motorLeftBack.setPower(speed*(1/balancer));
        int distance = ticks(rotations);
        telemetry.addData("Distance","Distance:  "+String.format("%.9s",distance));
        long currentDistance = Math.abs(motorRightBack.getCurrentPosition() - encoderSet);
        telemetry.addData("CurrentDistance","CurrentDistance:  "+String.format("%.9s",currentDistance));

        if (currentDistance >= distance) {
            motorOFF();
            encoderSet = motorRightBack.getCurrentPosition();
            stage ++;
        }
    }

    public void turnLeft(double rotations, double speed)
    {

        motorRightFront.setPower(speed*balancer);
        motorLeftFront.setPower(-speed*balancer);
        motorRightBack.setPower(speed*(1/balancer));
        motorLeftBack.setPower(-speed*(1/balancer));
        int distance = ticks(rotations);
        telemetry.addData("Distance","Distance:  "+String.format("%.9s",distance));
        long currentDistance = Math.abs(motorRightBack.getCurrentPosition() - encoderSet);
        telemetry.addData("CurrentDistance","CurrentDistance:  "+String.format("%.9s",currentDistance));

        if (currentDistance >= distance) {
            motorOFF();
            encoderSet = Math.abs(motorRightBack.getCurrentPosition());
            stage ++;
        }
    }

    public void backward(double rotations, double speed)
    {
        motorRightFront.setPower(-speed*balancer);
        motorLeftFront.setPower(-speed*balancer);
        motorRightBack.setPower(-speed*(1/balancer));
        motorLeftBack.setPower(-speed*(1/balancer));
        int distance = ticks(rotations);
        telemetry.addData("Distance","Distance:  "+String.format("%.9s",distance));
        long currentDistance = Math.abs(motorRightBack.getCurrentPosition() - encoderSet);
        telemetry.addData("CurrentDistance","CurrentDistance:  "+String.format("%.9s",currentDistance));

        if (currentDistance >= distance) {
            motorOFF();
            encoderSet = Math.abs(motorRightBack.getCurrentPosition());
            stage ++;
        }
    }

    public void strafeRight(double rotations, double speed)
    {
        motorRightFront.setPower(-speed*balancer);
        motorLeftFront.setPower(speed*balancer);
        motorRightBack.setPower(speed*(1/balancer));
        motorLeftBack.setPower(-speed*(1/balancer));
        int distance = ticks(rotations);
        telemetry.addData("Distance","Distance:  "+String.format("%.9s",distance));
        long currentDistance = Math.abs(motorRightBack.getCurrentPosition() - encoderSet);
        telemetry.addData("CurrentDistance","CurrentDistance:  "+String.format("%.9s",currentDistance));

        if (currentDistance >= distance) {
            motorOFF();
            encoderSet = Math.abs(motorRightBack.getCurrentPosition());
            stage ++;
        }
    }

    public void strafeLeft(double rotations, double speed)
    {
        motorRightFront.setPower(speed*balancer);
        motorLeftFront.setPower(-speed*balancer);
        motorRightBack.setPower(-speed*(1/balancer));
        motorLeftBack.setPower(speed*(1/balancer));
        int distance = ticks(rotations);
        telemetry.addData("Distance","Distance:  "+String.format("%.9s",distance));
        long currentDistance = Math.abs(motorRightBack.getCurrentPosition() - encoderSet);
        telemetry.addData("CurrentDistance","CurrentDistance:  "+String.format("%.9s",currentDistance));

        if (currentDistance >= distance) {
            motorOFF();
            encoderSet = Math.abs(motorRightBack.getCurrentPosition());
            stage ++;
        }
    }

    public void motorOFF() {
        motorRightFront.setPower(0);
        motorLeftFront.setPower(0);
        motorRightBack.setPower(0);
        motorLeftBack.setPower(0);
    }

    public int ticks(double rotations) {
        return (int)(rotations * 1120);
    }
}
