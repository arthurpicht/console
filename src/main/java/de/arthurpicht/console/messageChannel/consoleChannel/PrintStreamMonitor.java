package de.arthurpicht.console.messageChannel.consoleChannel;

import java.io.OutputStream;
import java.io.PrintStream;
import de.arthurpicht.utils.io.stream.LimitedByteArrayOutputStream;
import de.arthurpicht.utils.io.stream.TeeOutputStream;

public class PrintStreamMonitor {

    private final OutputStream consoleBufferStdOut = new LimitedByteArrayOutputStream(5);
    private final PrintStream printStreamStdOut;
    private final PrintStream printStreamStdErr;

    public PrintStreamMonitor(PrintStream stdOut, PrintStream stdErr) {
        printStreamStdOut = new PrintStream(new TeeOutputStream(stdOut, consoleBufferStdOut));
        printStreamStdErr = new PrintStream(new TeeOutputStream(stdErr, consoleBufferStdOut));
    }

    public PrintStream getPrintStreamStdOut() {
        return printStreamStdOut;
    }

    public PrintStream getPrintStreamStdErr() {
        return printStreamStdErr;
    }

    public boolean lastOutputEndsWithNewline() {
        return consoleBufferStdOut.toString().endsWith("\n");
    }

}
