package arguments;

import com.beust.jcommander.Parameter;

import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * The JCommander arguments that will be parsed.
 */
public class CommandLineArguments {

    /**
     * Parameter to filter mapping.
     */
    private HashMap<String, String> filterMap;

    @Parameter
    public List<String> mainParams;

    @Parameter(names = "-server", description = "Specify the api server. Default: http:/localhost:3000/cats")
    public String server;

    // Output parameters
    @Parameter(names = "-csv", description = "Output results to a given CSV file. Example: -csv ./csvOut.csv", converter = FileConverter.class)
    public File csvOut;

    @Parameter(names = "-pretty", description = "Pretty print the JSON output")
    public boolean prettyPrint = false;

    // Cat parameters
    @Parameter(names = "-name", description = "Specify cat name.", converter = StringConverter.class)
    public String name;

    @Parameter(names = "-breed", description = "Specify cat breed.", converter = StringConverter.class)
    public String breed;

    @Parameter(names = "-age", description = "Specify cat age.", converter = StringConverter.class)
    public String age;

    @Parameter(names = "-size", description = "Specify cat size.", converter = StringConverter.class)
    public String size;

    @Parameter(names = "-gender", description = "Specify cat gender.", converter = StringConverter.class)
    public String gender;

    @Parameter(names = "-coatLength", description = "Specify cat coat length.", converter = StringConverter.class)
    public String coatLength;

    @Parameter(names = "-color", description = "Specify cat color.", converter = StringConverter.class)
    public String color;

    /**
     * Create a filter in the form of "parameter"="value"
     *
     * @param key The key
     * @param paramValue The value
     * @return The created filter
     */
    private String createFilter(String key, String paramValue) {
        return key.substring(1) + "=" + paramValue;
    }

    /**
     * Get a filter for a given parameter.
     *
     * @param parameter The parameter to get the filter for.
     */
    public String getFilter(String parameter) {
        if (filterMap == null) {
            filterMap = new HashMap<>();
            filterMap.computeIfAbsent("-name", key -> createFilter(key, name));
            filterMap.computeIfAbsent("-breed", key -> createFilter(key, breed));
            filterMap.computeIfAbsent("-age", key -> createFilter(key, age));
            filterMap.computeIfAbsent("-size", key -> createFilter(key, size));
            filterMap.computeIfAbsent("-gender", key -> createFilter(key, gender));
            filterMap.computeIfAbsent("-coatLength", key -> createFilter(key, coatLength));
            filterMap.computeIfAbsent("-color", key -> createFilter(key, color));
        }

        if (filterMap.containsKey(parameter)) {
            return filterMap.get(parameter);
        }
        return "";
    }
}
