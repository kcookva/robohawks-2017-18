package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by skc5741 on 12/7/17.
 */

@TeleOp(name="REV Test", group ="Concept")
public class thing extends OpMode {

    DcMotor motor;
    CRServo servo;

    public void init() {
        motor = hardwareMap.dcMotor.get("motor");
        servo = hardwareMap.crservo.get("servo");
    }

    public void loop() {
        if(Math.abs(gamepad1.left_stick_y) > 0.1) {
            motor.setPower(gamepad1.left_stick_y);
        }
        else {
            motor.setPower(0);
        }

        if(gamepad1.left_bumper) {
            servo.setPower(-1);
        }
        else if(gamepad1.right_bumper) {
            servo.setPower(1);
        }
        else {
            servo.setPower(0);
        }
    }
}
