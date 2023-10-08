package com.kshitizadhet;

import com.kshitizadhet.types.Variables;
import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.LocalTime;

public class KeySimulator {
    private Robot robot;
    private String characters;
    private int delay;

    public static void main(final String[] args) {
        int loop = 1;
        for (final String s : args) {
            try {
                loop = Integer.parseInt(s);
                if (loop < 1) {
                    loop = 1;
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Error: Loop must be a positive integer.");
                System.exit(1);
            }
        }

        final int minWords = 350;
        final int maxWords = 500;

        Lorem lorem = LoremIpsum.getInstance();
        String initString = lorem.getWords(minWords, maxWords);

        final Variables var = new Variables(250, initString);
        final LocalTime startTime = LocalTime.now();
        System.out.println("Simulation started at " + startTime);
        for (int i = 1; i <= loop; ++i) {
            final KeySimulator simulator = new KeySimulator(var.getData(), var.getDelay());
            simulator.delay();
            simulator.simulate();
            System.out.println("");
        }
        final LocalTime endTime = LocalTime.now();
        System.out.println("Simulation ended at " + endTime);
    }

    public KeySimulator(String characters, final int delay) {
        this.robot = null;
        if (characters == null || characters.trim().isEmpty()) {
            characters = "";
        }
        this.characters = characters;
        this.delay = delay;
        try {
            this.robot = new Robot();
        } catch (Exception ex) {}
    }

    protected void delay() {
        if (this.robot != null) {
            this.robot.delay(this.delay);
        }
    }

    private int getKeyCode(final char c) {
        int keyCode = 0;
        try {
            keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
        } catch (Exception ex) {}
        return keyCode;
    }

    public String getCharacters() {
        return this.characters;
    }

    public void simulate() {
        int keyCode ;
        if (this.robot == null) {
            return;
        }
        for (int i = 0; i < this.getCharacters().length(); ++i) {
            if (i != 0 && i % 50 == 0) {
                try {
                    Thread.sleep(5000L);
                }
                catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            keyCode = this.getKeyCode(this.getCharacters().charAt(i));
            this.robot.keyPress(keyCode);
            this.robot.keyRelease(keyCode);
        }
        this.robot.keyPress(10);
        this.robot.keyRelease(10);
    }
}