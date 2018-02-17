package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by skc5741 on 2/15/18.
 */

@Autonomous(name="5741 Right Auto", group ="Competition")
public class Auto2 extends Methods {

    public void loop() {

        switch (stage) {
            case 0:
                resetEncoders();
                stage++;
                break;
            case 1:
                forward(1.1, 0.15);
                break;
            case 2:
                strafeRight(0.5, 0.25);
                break;
        }

        telemetry.addData("Stage", "Stage Number:  " + String.format("%.2s", stage));
        telemetry.addData("EncoderSet", "EncoderSet:  " + String.format("%.9s", encoderSet));
    }
}