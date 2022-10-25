package org.firstinspires.ftc.teamcode.teleop;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotor.RunMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple.Direction;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.OurBot;


/*
    TODO

    fix motor assignments
    set up a better drive



 */

@TeleOp(name="Basic Drive")
public class TankDrive extends LinearOpMode {
    private final OurBot robot = new OurBot();
    private double basePower = 0.8;


    private final double FAST_DRIVE_POWER = 0.8;
    private final double SLOW_DRIVE_POWER = 0.5;
    private final double ARM_POWER = 0.4;
    private final double INTAKE_POWER = 0.8;



    /*
    The main loop for the controller

     */
    public void runOpMode()
    {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Let OurRobot do the heavy lifting of getting and initializing the hardware
        robot.init(hardwareMap);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();



        // Run until the end of the match (driver presses STOP)
        while(opModeIsActive())
        {

            /*
            Gamepad 1 controls

            going to first use both joysticks to move

            left joystick moves left side up, right moves right up


             */

            //drive

            //the way motors are config, this is what gets tank drive to work
            double leftDrive = gamepad1.left_stick_y;
            double rightDrive = gamepad1.right_stick_y;
            double rightTrigger = gamepad1.right_trigger;
            double leftTrigger = gamepad1.left_trigger;

            double leftPower = leftDrive * basePower;
            double rightPower = rightDrive * basePower;

            if (rightTrigger > 0) {
                //negative == forward
                robot.leftFront.setPower(-1 * basePower);
                robot.leftBack.setPower(basePower);
                robot.rightFront.setPower(basePower);
                robot.rightBack.setPower(-1 * basePower);

                //whichever side its going to those wheels go in

            } else if (leftTrigger > 0) {
                robot.leftFront.setPower(basePower);
                robot.leftBack.setPower(-1 * basePower);
                robot.rightFront.setPower(basePower);
                robot.rightBack.setPower(-1 * basePower);

            } else {
                //sets power for all motors
                robot.leftFront.setPower(leftPower);
                robot.leftBack.setPower(leftPower);
                robot.rightFront.setPower(rightPower);
                robot.rightBack.setPower(rightPower);

            }


            //updates control hub
            telemetry.addData("Power", basePower);
            telemetry.update();


        }

    }
}
