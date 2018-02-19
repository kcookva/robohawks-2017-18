package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by skc5741 on 2/15/18.
 */

public class Setup extends OpMode{

    DcMotor motorRightFront;                    // creates motors in code
    DcMotor motorRightBack;
    DcMotor motorLeftFront;
    DcMotor motorLeftBack;
    DcMotor lift;
    Servo grip1;
    Servo grip2;
    double balancer = 1.3;

    public void init()          // initiates and maps motors/servos/sensors
    {
        motorRightFront = hardwareMap.dcMotor.get("mRF");               // Finds the motor and the library to use it
        motorRightBack = hardwareMap.dcMotor.get("mRB");               // !! Must be prenamed in phone app to green letters (mRF, mRB, etc.) !!
        motorLeftFront = hardwareMap.dcMotor.get("mLF");
        motorLeftBack = hardwareMap.dcMotor.get("mLB");
        lift = hardwareMap.dcMotor.get("lift");
        grip1 = hardwareMap.servo.get("grip1");
        grip2 = hardwareMap.servo.get("grip2");

        motorRightFront.setDirection(DcMotor.Direction.REVERSE);      //think about logic of motors and how you need to reverse two of them
        motorRightBack.setDirection(DcMotor.Direction.REVERSE);
    }

    public void loop() {}
}
