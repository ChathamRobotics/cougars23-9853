package org.firstinspires.ftc.teamcode.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;


@Autonomous
public class RedRight extends BaseAutonOpenCVWebcam {
    int zone;
    final int ARM_HOVER_INCHES = 6;
    int greenAverage, redAverage, blueAverage;
    int rotation2Target;
    int rotation1Target;
    public DcMotor rotation1;
    public DcMotor rotation2;

    DistanceSensor distanceSensor;


    public void runOpMode()
    {


        //sets up webcam and robot from parent class
        super.runOpMode();
        /*distanceSensor = hardwareMap.get(DistanceSensor.class, "" +
                "" +
                "" +
                "distanceSensor");*/

        //wait for driver to press start
        //sets up rotation motor1
        rotation1 = hardwareMap.get(DcMotor.class, "leftRotation");
        rotation1.setDirection(DcMotorSimple.Direction.REVERSE);
        rotation1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rotation1Target = rotation1.getCurrentPosition();
        rotation1.setTargetPosition(rotation1Target);
        rotation1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rotation1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //rotation1.setPower(0.3);

        //sets up rotation motor2
        rotation2 = hardwareMap.get(DcMotor.class, "rightRotation");
        rotation2.setDirection(DcMotorSimple.Direction.REVERSE);
        rotation2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rotation2Target  = rotation2.getCurrentPosition();
        rotation2.setTargetPosition(rotation2Target);
        rotation2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rotation2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //rotation2.setPower(0.3);

        robot.claw.setPosition(0.7);

        telemetry.addData(">", "press play pls");
        telemetry.update();
        waitForStart();







        while(pipeline.getRedAverage() == 0 )
        {
            telemetry.addData("Status", "not working");
            telemetry.update();
            sleep(5);
        }

        telemetry.addData("Status", "it worked!");
        telemetry.update();



        //if blue is the color
        if ( pipeline.getBlueAverage() > pipeline.getGreenAverage() && pipeline.getBlueAverage() > pipeline.getRedAverage())
        {
            telemetry.addData("Color", "Blue");
            zone = 1;
        }

        //if color is green
        else if (pipeline.getGreenAverage() > pipeline.getBlueAverage() && pipeline.getGreenAverage() > pipeline.getRedAverage())
        {
            telemetry.addData("Color", "Green");
            zone = 2;
        }
        else{
            telemetry.addData("Color", "Red");
            zone = 3;
        }
        telemetry.update();
        robot.leftFront.setTargetPosition((int) -(robot.COUNTS_PER_INCH*47));
        robot.leftBack.setTargetPosition((int) -(robot.COUNTS_PER_INCH*47));
        robot.rightFront.setTargetPosition((int) -(robot.COUNTS_PER_INCH*47));
        robot.rightBack.setTargetPosition((int) -(robot.COUNTS_PER_INCH*47));
        rotation1.setTargetPosition(500);
        rotation2.setTargetPosition(500);

        robot.leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        rotation1.setPower(0.4);
        rotation2.setPower(0.4);
        robot.leftFront.setPower(0.3);
        robot.leftBack.setPower(0.3);
        robot.rightFront.setPower(0.3);
        robot.rightBack.setPower(0.3);

        while(robot.rightBack.isBusy() || robot.leftBack.isBusy() || robot.rightFront.isBusy() || robot.leftFront.isBusy()){
            //

        }







        rotation1.setTargetPosition(980);
        rotation2.setTargetPosition(980);
        while(rotation1.isBusy() && rotation2.isBusy())
        {
            //
        }
        sleep(500);
        //strafe "left"
        robot.leftFront.setPower(0.1);
        robot.leftBack.setPower(0.1);
        robot.rightFront.setPower(0.1);
        robot.rightBack.setPower(0.1);
        robot.leftFront.setTargetPosition(  robot.leftFront.getCurrentPosition() + (int)(robot.COUNTS_PER_INCH*16));
        robot.leftBack.setTargetPosition(robot.leftBack.getCurrentPosition()-(int)(robot.COUNTS_PER_INCH*16));
        robot.rightFront.setTargetPosition(robot.rightFront.getCurrentPosition()-(int)(robot.COUNTS_PER_INCH*16));
        robot.rightBack.setTargetPosition(robot.rightBack.getCurrentPosition()+(int)(robot.COUNTS_PER_INCH*16));




        while(robot.rightBack.isBusy() || robot.leftBack.isBusy() || robot.rightFront.isBusy() || robot.leftFront.isBusy()){
            //
        }



        robot.leftFront.setPower(0);
        robot.leftBack.setPower(0);
        robot.rightFront.setPower(0);
        robot.rightBack.setPower(0);

        //get arm ready to extend
        robot.arm.setTargetPosition(2150);
        robot.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.arm.setPower(0.5);

        while(robot.arm.isBusy()){
            //
        }

        //move robot over to prepare score
        rotation1.setPower(0.5);
        rotation2.setPower(0.5);
        robot.leftFront.setPower(0.2);
        robot.leftBack.setPower(0.2);
        robot.rightFront.setPower(0.2);
        robot.rightBack.setPower(0.2);
        robot.leftFront.setTargetPosition(  robot.leftFront.getCurrentPosition() + (int)(robot.COUNTS_PER_INCH*1.4));
        robot.leftBack.setTargetPosition(robot.leftBack.getCurrentPosition()+(int)(robot.COUNTS_PER_INCH*1.4));
        robot.rightFront.setTargetPosition(robot.rightFront.getCurrentPosition()+(int)(robot.COUNTS_PER_INCH*1.4));
        robot.rightBack.setTargetPosition(robot.rightBack.getCurrentPosition()+(int)(robot.COUNTS_PER_INCH*1.4));


        while(robot.rightBack.isBusy() || robot.leftBack.isBusy() || robot.rightFront.isBusy() || robot.leftFront.isBusy()){
            //
        }

        //put cone on claw
        sleep(2000);
        robot.claw.setPosition(0.55);
        sleep(2000);
        robot.claw.setPosition(0.55);

        //go down from pole, back up a bit
        robot.leftFront.setTargetPosition(  robot.leftFront.getCurrentPosition() + (int)(robot.COUNTS_PER_INCH*2.5));
        robot.leftBack.setTargetPosition(robot.leftBack.getCurrentPosition()+(int)(robot.COUNTS_PER_INCH*2.5));
        robot.rightFront.setTargetPosition(robot.rightFront.getCurrentPosition()+(int)(robot.COUNTS_PER_INCH*2.5));
        robot.rightBack.setTargetPosition(robot.rightBack.getCurrentPosition()+(int)(robot.COUNTS_PER_INCH*2.5));
        robot.arm.setTargetPosition(0);

        while(robot.rightBack.isBusy() || robot.leftBack.isBusy() || robot.rightFront.isBusy() || robot.leftFront.isBusy() || robot.arm.isBusy()){
            telemetry.addData("arm", "bruh we processing");
            telemetry.update();
        }

        /*robot.leftFront.setTargetPosition(  robot.leftFront.getCurrentPosition() - (int)(robot.COUNTS_PER_INCH*.52));
        robot.leftBack.setTargetPosition(robot.leftBack.getCurrentPosition()-(int)(robot.COUNTS_PER_INCH*2.5));
        robot.rightFront.setTargetPosition(robot.rightFront.getCurrentPosition()+(int)(robot.COUNTS_PER_INCH*2.5));
        robot.rightBack.setTargetPosition(robot.rightBack.getCurrentPosition()+(int)(robot.COUNTS_PER_INCH*2.5));*/





        //encoderDrive(0.3, 40, 40, 40, 40, 5);



        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);  // pause to display final telemetry message.






        switch(zone)
        {
            case(1):
                //move back, left, and up
                encoderDrive(0.3, -3, -3, -3, -3, 10, true);
                encoderDrive(0.3, 12, -12, -12, 12, 10, true);
                //encoderDrive(0.3, 26, 26, 26, 26, 10, true);
                break;
            case(2):
                //move back, right, and up
                encoderDrive(0.3, -3, -3, -3, -3, 10, true);
                encoderDrive(0.3, -15, 15, 15, -15, 10, true);
                //encoderDrive(0.3, 26, 26, 26, 26, 10, true);
                break;
            case(3):
                //move back, far right, and up
                encoderDrive(0.3, -3, -3, -3, -3, 10, true);
                encoderDrive(0.3, -40, 40, 40, -40, 10, true);
                //encoderDrive(0.3, 26, 26, 26, 26, 10, true);
                break;
            default:
                break;
        }





        telemetry.update();
        sleep(4000);



    }

}
