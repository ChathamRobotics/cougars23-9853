/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */


package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotor.RunMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple.Direction;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
/**
 * This is NOT an opmode.
 *
 * This class is used to define all the hardware for our 2022-2023 robot.
 *
 * */
public class OurBot
{

    /** Encoder counts per motor revolution, usually found in the motor's datasheet */
    public static final double COUNTS_PER_MOTOR_REV = 28;
    /**
     * The gear ratio for the motor, in this case for two of the REV Ultraplanetary gearboxes
     * Notice that they are weird numbers, despite the gearboxes having integer ratios listed on them
     * ALWAYS check the gearbox datasheet for the true gear ratios, just in case
     * */
    public static final double DRIVE_GEAR_REDUCTION = 2.89 * 3.61 * 5.23;
    /** Wheel diameter in inches, try to be as precise as possible */
    public static final double WHEEL_DIAMETER_INCHES = 75 / 25.4;
    /**
     * Encoder counts per inch the robot moves
     * This is what's actually used to calculate how much the motors should turn
     */
    public static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * Math.PI) * -1;

    /*
     * Defining the motors and servos
     * By defining them as variables in this class, we can use them throughout our project,
     * with autocomplete, without having to know the string device names
     */
    public DcMotor leftFront = null;
    public DcMotor leftBack = null;
    public DcMotor rightFront = null;
    public DcMotor rightBack = null;

    public void init(HardwareMap hwMap)
    {
        /*
        leftBack = 0
        leftFront = 1
        rightBack = 2
        rightFront = 3
        */

        //Define motor
        leftFront = hwMap.get(DcMotor.class, "leftFront");
        leftBack = hwMap.get(DcMotor.class, "leftBack");
        rightFront = hwMap.get(DcMotor.class, "rightFront");
        rightBack = hwMap.get(DcMotor.class, "rightBack");

        //Initialize motor direction
        leftFront.setDirection(Direction.FORWARD);
        leftBack.setDirection(Direction.FORWARD);
        rightFront.setDirection(Direction.FORWARD);
        rightBack.setDirection(Direction.FORWARD);


        //Set all motors to 0 power
        leftFront.setPower(0);
        leftBack.setPower(0);
        rightFront.setPower(0);
        rightBack.setPower(0);


        //Reset all encoders
        leftFront.setMode(RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(RunMode.STOP_AND_RESET_ENCODER);

        //Set motors to run with encoder
        leftFront.setMode(RunMode.RUN_USING_ENCODER);
        leftBack.setMode(RunMode.RUN_USING_ENCODER);
        rightFront.setMode(RunMode.RUN_USING_ENCODER);
        rightBack.setMode(RunMode.RUN_USING_ENCODER);


        //stops motors when 0 power, more precision
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
}
