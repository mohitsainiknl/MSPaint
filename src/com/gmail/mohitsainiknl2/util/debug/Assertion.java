/*
 * Copyright (c) 2021 Mohit Saini, Under MIT License. Use is subject to license terms.
 * 
 */

package com.gmail.mohitsainiknl2.util.debug;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.Throwable;

/**
 * Assertion class is designed to help the developer in debugging process,
 * This class print the message to Output-Stream if the assertion is enabled
 * for the project which uses this.<br>
 * 
 * To enable Assertion :<br>
 * Command-Prompt :<br>
 *      "java -ea -jar MSCalculator.jar" should do it on the command prompt.<br><br>
 * 
 * VS Code :<br>
 *      goto- "Run and Debug(Ctrl + Shift + D)" >> On top drop-down menu select "Add Configuration" <br>
 *            Then, (new launch.json file will open),<br>
 *            Add(with quotes),  "vmArgs": "-enableassertions" , under the Configurations section.<br>
 *            and, Save the file.<br><br>
 * 
 * 					OR<br>
 *      goto the link- "https://stackoverflow.com/questions/54024007/how-to-enable-java-assertions-in-visual-studio-code"<br><br>
 * 
 * Eclipse :<br>
 * 		goto- Run >> Run_Configurations >> Java_Applications(left_sidebar) >> MainLauncher >> Arguments <br>
 * 			  Then, type "-ea" or "-enableassertions" under VM Arguments section <br>
 * 			  Click, Apply and Run.<br><br>
 * 
 * 					OR<br>
 *      goto the link- "https://stackoverflow.com/questions/5509082/eclipse-enable-assertions"<br><br>
 *      
 * IntelliJ :<br>
 *      goto the link- "https://stackoverflow.com/questions/18168257/where-to-add-compiler-options-like-ea-in-intellij-idea"<br>
 *
 * @author Mohit Saini
 */
public class Assertion {
    private static final boolean showMessage = true;
    private static final boolean showErrorMessage = true;

    /**
     * throwMessage method only print the message on the Output-Stream,
     * not the location of thrown (if Assertion is enabled).
     * @param message this is the message to be print.
     */
    public static void throwMessage(String message) {
        if(showMessage) {
            final boolean isEnabled = checkAssertionEnabled();
            if(isEnabled) {
                printMessage(message);
            }
            
        }
    }

    /**
     * throwErrorMessage method print both the message and the location of thrown
     * (if Assertion is enabled). Because, this method will execute on condition,
     *      Like - if(  !(_assertion_condition_)  ) Assertion.throwErrorMessage(new Throwable(_assertion_message_));
     * 
     * so, if developer's assumption is false about the assertion-condition , this will inform the developer
     * about his wrong assumption in the debugging or testing process (where assertion is enabled).
     * @param obj this object help to find the thrown-location of the error-message.
     */
    public static void throwErrorMessage(Throwable obj) {
        if(showErrorMessage) {
            final boolean isEnabled = checkAssertionEnabled();
            if(isEnabled) {
                printErrorMessage(obj);
            }
        }
    }

    /**
     * checkAssertionEnabled method check if the assertion is enabled of not.
     * @return true if assertion is enabled, otherwise false.
     */
    private static boolean checkAssertionEnabled() {
        try {
            assert false : "";
        } catch (AssertionError err) {
            return true;
        }
        return false;
    }

    /**
     * printErrorMessage method help to print both message and location 
     * of error, with the help of obj.
     * @param obj this contains the information about error-message to be thrown.
     */
    private static void printErrorMessage(Throwable obj) {
        String message = obj.getMessage();
        printMessage(message);

        String location = "        at " + obj.getStackTrace()[0].toString();
        printMessage(location);
    }

    /**
     * printMessage method is the fastest way, here, to print the message, if it fails to print
     * the message, it will call the printMessage2ndWay method.
     * @param message this is the message we need to print.
     */
    private static void printMessage(String message) {
        try {
            OutputStream out = new BufferedOutputStream(System.out);
            out.write((message + "\n").getBytes());
            out.flush();
        } catch (Exception e1) {
            printMessage2ndWay(message);
        }
    }

    /**
     * printMessage method is the Second fastest way, here, to print the message, if it fails to print
     * the message, it will call the printMessage3ndWay method.
     * @param message this is the message we need to print.
     */
    private static void printMessage2ndWay(String message) {
        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
            out.write(message + "\n");
            out.flush();
        } catch (Exception e2) {
            printMessage3rdWay(message);
        }
    }

    /**
     * printMessage method is the common way to print the message
     * @param message this is the message we need to print.
     */
    private static void printMessage3rdWay(String message) {
        System.out.println(message);
    }
}
