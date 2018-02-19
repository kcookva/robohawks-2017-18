package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Sam on 11/2/2017. Copied and pasted by Willem. Viewed by Milo.
 */

@TeleOp(name = "5741 TeleOp Zach", group = "Competition")
public class LifterBotZach extends OpMode {

    DcMotor motorRightFront;                    // creates motors in code
    DcMotor motorRightBack;
    DcMotor motorLeftFront;
    DcMotor motorLeftBack;
    DcMotor lift;
    Servo grip1;
    Servo grip2;

    public void init()          // initiates and maps motors/servos/sensors
    {
        motorRightFront = hardwareMap.dcMotor.get("mRF");               // Finds the motor and the library to use it
        motorRightBack = hardwareMap.dcMotor.get("mRB");               // !! Must be prenamed in phone app to green letters (mRF, mRB, etc.) !!
        motorLeftFront = hardwareMap.dcMotor.get("mLF");
        motorLeftBack = hardwareMap.dcMotor.get("mLB");
        lift = hardwareMap.dcMotor.get("lift");
        grip1 = hardwareMap.servo.get("grip1");
        grip2 = hardwareMap.servo.get("grip2");

        motorLeftFront.setDirection(DcMotor.Direction.REVERSE);      //think about logic of motors and how you need to reverse two of them
        motorLeftBack.setDirection(DcMotor.Direction.REVERSE);

        grip1.setPosition(0.9);
        grip2.setPosition(0.1);
    }

    int speed = 4;
    double balancer = 1.3;

    @Override
    public void loop() {                                        // goes into loop after the setup is done  in above void
        // sets up a loop for driving the robot
        double rotate = -gamepad1.right_stick_x;
        double strafe = -gamepad1.left_stick_x;                 // input joystick values into variables that we can use to control the motors
        double drive = gamepad1.left_stick_y;
        double liftSpeed = gamepad1.right_stick_y;


        rotate = Range.clip(rotate, -1, 1);                      // sets a value check to make sure we don't go over the desired speed (related to joysticks)
        strafe = Range.clip(strafe, -1, 1);
        drive = Range.clip(drive, -1, 1);

        if (gamepad1.dpad_up) speed = 4;
        else if (gamepad1.dpad_right) speed = 3;
        else if (gamepad1.dpad_down) speed = 2;
        else if (gamepad1.dpad_left) speed = 1;

        if (Math.abs(gamepad1.left_stick_y) > .1 || Math.abs(gamepad1.right_stick_x) > .1 || Math.abs(gamepad1.left_stick_x) > .1) {  // if joystick value is greater than .1, move.  Will not move if no value (joystick idle)
            motorRightFront.setPower((drive - strafe - rotate) * balancer / 12 * speed);
            motorRightBack.setPower((drive + strafe - rotate)  / balancer / 12 * speed);
            motorLeftFront.setPower((drive + strafe + rotate) * balancer / 12 * speed);
            motorLeftBack.setPower((drive - strafe + rotate) / balancer / 12 * speed);
        } else {                           //will not move if joysticks are not moving
            motorRightFront.setPower(0);
            motorRightBack.setPower(0);
            motorLeftFront.setPower(0);
            motorLeftBack.setPower(0);
        }
        if (liftSpeed > 0.1) {
            lift.setPower(-0.1);
        } else if (liftSpeed < -0.1) {
            lift.setPower(1);
        } else {
            lift.setPower(0.2);
        }

        if (gamepad1.left_trigger > 0.1 && grip1.getPosition() < 0.9) {
            grip1.setPosition(grip1.getPosition() - 0.005);
            grip2.setPosition(grip2.getPosition() + 0.005);
        } else if (gamepad1.right_trigger > 0.1 && grip1.getPosition() > 0) {
            grip1.setPosition(grip1.getPosition() + 0.005);
            grip2.setPosition(grip2.getPosition() - 0.005);
        }
        if(gamepad1.left_bumper) {
            grip1.setPosition(0.25);
            grip2.setPosition(0.75);
        }
        if(gamepad1.right_bumper) {
            grip1.setPosition(0);
            grip2.setPosition(1);
        }

        telemetry.addData("Joy1", "Drive:  " + String.format("%.2s", drive)); // feedback given to the driver phone from the robot phone
        telemetry.addData("Joy1", "Strafe:  " + String.format("%.2s", strafe));
        telemetry.addData("Joy1", "Rotate:  " + String.format("%.2s", rotate));
    }
}