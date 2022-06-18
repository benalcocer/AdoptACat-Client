package client;

import java.io.File;
import java.util.logging.Logger;

import arguments.CommandLineArguments;
import com.beust.jcommander.*;
import org.apache.commons.io.FilenameUtils;

public class Main {

    /**
     * The default server to connect to.
     */
    private static final String DEFAULT_SERVER = "http://localhost:3000/cats";

    /**
     * Main entry point to the application.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        CommandLineArguments jArgs = new CommandLineArguments();
        JCommander jCommander = JCommander.newBuilder().addObject(jArgs).build();

        // Parse arguments
        boolean parseSuccess = true;
        try {
            jCommander.parse(args);
        } catch (ParameterException parameterException) {
            // On parse error, print info and usage
            Logger.getGlobal().info(parameterException.getMessage());
            jCommander.usage();
            parseSuccess = false;
        }

        if (parseSuccess) {
            // Update CSV file extension if needed
            if (jArgs.csvOut != null) {
                try {
                    String extension = FilenameUtils.getExtension(jArgs.csvOut.getName());
                    if (extension.isEmpty()) {
                        jArgs.csvOut = new File(jArgs.csvOut.getPath() + ".csv");
                    } else if (!extension.equalsIgnoreCase("csv")) {
                        Logger.getGlobal().info("Exiting program. File extension is not .csv and is not empty.");
                        jCommander.usage();
                        return;
                    }
                } catch (IllegalArgumentException illegalArgumentException) {
                    Logger.getGlobal().severe(illegalArgumentException.getMessage());
                }
            }

            // Create filter string and update server string if specified
            String server = DEFAULT_SERVER;
            StringBuilder filters = new StringBuilder("");
            int assignedParamCount = 0;
            for (ParameterDescription paramDescription : jCommander.getParameters()) {
                if (paramDescription.isAssigned()) {
                    if (paramDescription.getNames().equals("-server")) {
                      server = jArgs.server;
                    } else {
                        if (assignedParamCount != 0) {
                            filters.append("&");
                        }
                        filters.append(jArgs.getFilter(paramDescription.getNames()));
                        ++assignedParamCount;
                    }
                }
            }

            // Search for cats
            SearchClient.getInstance().searchForCats(server, filters.toString(), jArgs);
        }
    }
}