package org.demo.codesmell.util;

import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.*;

@Log4j2
public class CmdUtil {

    private static final ExecutorService executor = Executors.newFixedThreadPool(3);

    public static String exec(String cmd) throws IOException, InterruptedException, ExecutionException {
        String[] cmds = new String[]{"/bin/sh", "-c", cmd};
        Process process = Runtime.getRuntime().exec(cmds);
        StreamCaptureThread errorStream = new StreamCaptureThread(process.getErrorStream());
        StreamCaptureThread outputStream = new StreamCaptureThread(process.getInputStream());
        Future<String> errString = executor.submit(errorStream);
        Future<String> outputString = executor.submit(outputStream);
        boolean end = process.waitFor(1, TimeUnit.MINUTES);
        if (end) {
            return outputString.get() + errString.get();
        } else {
            return "processing";
        }
    }

    private static class StreamCaptureThread implements Callable<String> {
        InputStream stream;
        volatile StringBuffer output;

        public StreamCaptureThread(InputStream stream) {
            this.stream = stream;
            this.output = new StringBuffer();
        }

        @Override
        public String call() throws Exception {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.stream));
            String line = bufferedReader.readLine();
            while (line != null) {
                if (!line.trim().isEmpty()) {
                    output.append(line).append("\n");
                }
                line = bufferedReader.readLine();
            }
            return output.toString();
        }
    }
}
