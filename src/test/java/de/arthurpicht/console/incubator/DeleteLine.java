package de.arthurpicht.console.incubator;

import de.arthurpicht.console.utils.AnsiCode;
import org.junit.jupiter.api.Test;

public class DeleteLine {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("This is some output");
        Thread.sleep(1000);

        System.out.print("Some more output in next line w/o lf and really lone line ..... ah ..........................");
        Thread.sleep(1000);

        System.out.print(AnsiCode.ERASE_LINE_CONTENT());
        System.out.print(AnsiCode.MOVE_LINE_UP());
        System.out.print(AnsiCode.ERASE_LINE_CONTENT());
        System.out.println("This is some other text overwritten.");

    }

}
