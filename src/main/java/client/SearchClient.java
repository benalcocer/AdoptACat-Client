package client;

import arguments.CommandLineArguments;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * A client that sends a GET request and outputs the data.
 */
public class SearchClient {

    /**
     * The singleton instance of SearchClient.
     */
    private static SearchClient instance;

    /**
     * Search for cats to adopt.
     *
     * @param server The server to connect to.
     * @param filters The filters, if any, to append to the request.
     * @param arguments The command line arguments.
     */
    public void searchForCats(String server, String filters, CommandLineArguments arguments) {
        HttpGet request = new HttpGet(filters.isEmpty() ? server : server + "?" + filters);

        try (CloseableHttpClient httpClient = HttpClients.createDefault(); CloseableHttpResponse response = httpClient.execute(request)) {
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();

                if (entity != null) {
                    try {
                        JSONArray jsonArray = new JSONArray(EntityUtils.toString(entity));
                        if (arguments.csvOut == null) {
                            // Return info as a String
                            int indentFactor = arguments.prettyPrint ? 4 : 0;
                            System.out.println(jsonArray.toString(indentFactor));
                        } else {
                            // Output to the CSV file
                            if (writeCsvFile(jsonArray, arguments.csvOut)) {
                                Logger.getGlobal().info("Successfully written to: " + arguments.csvOut.getPath());
                            } else {
                                Logger.getGlobal().info("CSV write failed for: " + arguments.csvOut.getPath());
                            }
                        }
                    } catch (JSONException e) {
                        Logger.getGlobal().warning(e.getMessage());
                    }
                }
            } else {
                String reason = "";
                if (!response.getStatusLine().getReasonPhrase().isEmpty()) {
                    reason = ", Reason: " + response.getStatusLine().getReasonPhrase();
                }
                Logger.getGlobal().warning("Status code: " + response.getStatusLine().getStatusCode() + reason);
            }
        } catch (IOException e) {
            Logger.getGlobal().severe(e.getCause().getMessage());
        }
    }

    /**
     * Write a JSONArray to a CSV file.
     *
     * @param jsonArray The JSONArray to write to the CSV file.
     * @param csvFile The CSV file to write to.
     * @return true if the file write was a success, otherwise false.
     */
    public boolean writeCsvFile(JSONArray jsonArray, File csvFile) {
        boolean retVal = false;

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(CDL.toString(jsonArray));
            retVal = true;
        } catch (IOException e) {
            Logger.getGlobal().info(e.getMessage());
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    Logger.getGlobal().severe(e.getMessage());
                }
            }
        }

        return retVal;
    }

    /**
     * @return The singleton instance of SearchClient.
     */
    public static SearchClient getInstance() {
        if (instance == null) {
            instance = new SearchClient();
        }
        return instance;
    }
}
