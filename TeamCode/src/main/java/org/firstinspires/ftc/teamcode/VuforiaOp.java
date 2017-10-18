package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.vuforia.HINT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.Axis;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Created by willem on 10/17/17.
 * This file from this YouTube tutorial: https://www.youtube.com/watch?v=2z-o9Ts8XoE
 * There are already samples of Vuforia from FTC in FtcRobotController/java/org.firstinspires.ftc.robotcontroller/external.samples/
 */

@Disabled
public class VuforiaOp extends LinearOpMode {
    public void runOpMode() throws InterruptedException {
        VuforiaLocalizer.Parameters params = new VuforiaLocalizer.Parameters(R.id.cameraMonitorViewId);
        params.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        params.vuforiaLicenseKey = "AU+Hhq//////AAAAGd9kr9g+lEnym+2hVPAmhaEtSfd00bDGZA42FOFEDHI41cl7ED+Zp6rlyQ5ILeULVIkubwif7sf2hr9H+jYSGMG2+HNflwgNtbq/e6HkJ+gZGhfXmLSpYVarNKW6/H+4S0BkvqlKzsZHimE4JSccsoiSZdiY0pxjSomaqNHNL+imEWwkv0NK47LAEtsuyxxbvtawUCGMobNL4i1zADRdQ3s8zv/dUx+AoyFiDrn6pO9h9GzLsWSWaLRoENlLf7wQeWYp2Wb6xeDaDcr9xsUFzhg4Gruy7KlE+0gWLAZNg66bB0tWp1NBdq1ZlWIx6+oIlB/MPoV3Z0uT1AW/d6hvdKv3e6lfwWwK8oBu/P0RTXVR";
        params.cameraMonitorFeedback = VuforiaLocalizer.Parameters.CameraMonitorFeedback.AXES;

        VuforiaLocalizer vuforia = ClassFactory.createVuforiaLocalizer(params);
        Vuforia.setHint(HINT.HINT_MAX_SIMULTANEOUS_IMAGE_TARGETS, 4);

        VuforiaTrackables beacons = vuforia.loadTrackablesFromAsset("RelicVuMark");
        beacons.get(0).setName("RelicRecovery");

        waitForStart();

        beacons.activate();

        while (opModeIsActive()) {
            for(VuforiaTrackable beac : beacons) {
                OpenGLMatrix pose = ((VuforiaTrackableDefaultListener) beac.getListener()).getPose();

                if(pose != null) {
                    VectorF translation = pose.getTranslation();

                    telemetry.addData(beac.getName() + "-Translation", translation);

                    double degreesToTurn = Math.toDegrees(Math.atan(translation.get(1), translation.get(2)));

                    telemetry.addData(beac.getName() + "-Degrees", degreesToTurn);
                }
            }
            telemetry.update();
        }
    }
}
