// Created by Kaiden Cook on 2/17/18
package org.firstinspires.ftc.teamcode;


import com.qualcomm.hardware.motors.RevRoboticsCoreHexMotor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="LifterBot2", group="Concept")
public class LifterBot2 extends OpMode { // Predefines/creates motors

    DcMotor motorRightFront;
    DcMotor motorRightBack;
    DcMotor motorLeftFront;
    DcMotor motorLeftBack;
    DcMotor lift;
    CRServo gripper;

    public void init() { // Initialize all of the predefined variables
        motorRightFront = hardwareMap.dcMotor.get("mRF");
        motorRightBack = hardwareMap.dcMotor.get("mRB");
        motorLeftFront = hardwareMap.dcMotor.get("mLF");
        motorLeftBack = hardwareMap.dcMotor.get("mLB");
        lift = hardwareMap.dcMotor.get("lift");
        gripper = hardwareMap.crservo.get("gripper");

        motorLeftFront.setDirection(DcMotor.Direction.REVERSE);
        motorLeftBack.setDirection(DcMotor.Direction.REVERSE);
    }

    int speed = 4; // Default speed to divide by 12 to get servo speed between 1 and -1

    @Override
    public void loop() { // Sets loop for driver control
        double rotate =  -gamepad1.right_stick_x;
        double strafe = -gamepad1.left_stick_x;
        double drive = -gamepad1.left_stick_y;

        rotate = Range.clip(rotate, -1, 1);
        strafe = Range.clip(strafe, -1, 1);
        drive = Range.clip(drive, -1, 1);

        if (gamepad1.dpad_up) {
            speed = 4;
        }
        else if (gamepad1.dpad_right) {
            speed = 3;
        }
        else if (gamepad1.dpad_down) {
            speed = 2;
        }
        else if (gamepad1.dpad_left) {
            speed = 1;
        }

        if (Math.abs(gamepad1.left_stick_y) > .1 || Math.abs(gamepad1.right_stick_x) > .1 || Math.abs(gamepad1.left_stick_x) > .1)) { // If the left or right sticks move more than .1 they move, if not then they don't move
            motorRightFront.setPower((drive - strafe - rotate) / 12 * speed);
            motorRightBack.setPower((drive + strafe - rotate) / 12 * speed);
            motorLeftFront.setPower((drive + strafe + rotate) / 12 * speed);
            motorLeftBack.setPower((drive - strafe + rotate) / 12 * speed);
        }
        else {
            motorRightFront.setPower(0);
            motorRightBack.setPower(0);
            motorLeftFront.setPower(0);
            motorLeftBack.setPower(0);
        }


        // Lifter code
        if (gamepad1.right_bumper) {
            lift.setPower(-1);
        }
        else if (gamepad1.right_bumper) {
            lift.setPower(1);
        }
        else {
            lift.setPower(0);
        }

        // Grabber code
        if (gamepad1.left_trigger > .1) {
            gripper.setPower(-1);
        }
        else if (gamepad1.right_trigger > .1) {
            gripper.setPower(1);
        }
        else {
            gripper.setPower(0);
        }



        telemetry.addData("Joy1", "Drive " + String.format("%.2s", drive));
        telemetry.addData("Joy1", "Strafe " + String.format("%.2s", strafe));
        telemetry.addData("Joy1", "Rotate " + String.format("%.2s", rotate));
    }

}