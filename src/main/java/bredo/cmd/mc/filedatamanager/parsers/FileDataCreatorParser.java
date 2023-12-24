package bredo.cmd.mc.filedatamanager.parsers;

import bredo.cmd.mc.filedatamanager.interfaces.IDefaultsData;
import bredo.cmd.mc.filedatamanager.manipulator.FileDataManipulator;
import bredo.cmd.mc.filedatamanager.serializers.DefaultJsonSerializer;
import bredo.cmd.mc.filedatamanager.utilities.FileData;
import bredo.cmd.mc.unilink.handlers.ExceptionHandler;

/**
 * This class is responsible for parsing parameters and applying them to a {@link FileData} object.
 * It utilizes arrays of default values for strings, booleans, and {@link IDefaultsData} objects
 * to initialize or reset file data parameters.
 * <p>
 * It also employs the {@link FileDataManipulator} class to set various attributes of {@link FileData}.
 * Exceptions are handled using the {@link ExceptionHandler} class.
 * </p>
 *
 * @see FileDataManipulator
 * @see ExceptionHandler
 */
public final class FileDataCreatorParser {

    /**
     * Default values for file data strings.
     * <ul>
     * <li>Index 0 - File Directory</li>
     * <li>Index 1 - File Name</li>
     * <li>Index 2 - File Extension</li>
     * <li>Index 3 - FileData Serializer Name</li>
     * </ul>
     */
    private final static String[] defaultFileDataStrings = new String[]{"", "FileData", "yml", DefaultJsonSerializer.DEFAULT_JSON_SERIALIZER_NAME};

    /**
     * Default values for file data booleans.
     * <ul>
     * <li>Index 0 - Select FileData</li>
     * <li>Index 1 - Use Plugin Directory Folder</li>
     * <li>Index 2 - Cache Data</li>
     * </ul>
     */
    private final static Boolean[] defaultFileDataBooleans = new Boolean[]{true, true, true};

    /**
     * Default values for {@link IDefaultsData} instances.
     * Currently, it contains only a single null value as default.
     */
    private final static IDefaultsData[] defaultIDefaultsData = new IDefaultsData[]{null};

    /**
     * Parses the provided parameters and applies them to the specified {@link FileData} object.
     * The parameters can include {@link String}s, {@link Boolean}s, and {@link IDefaultsData} instances.
     * The method dynamically handles the type and number of parameters, applying the corresponding
     * values to the {@link FileData} object using {@link FileDataManipulator}.
     *
     * @param fileData   The {@link FileData} object to which the parameters will be applied.
     * @param parameters A variable number of parameters, which can include {@link String},
     *                   {@link Boolean}, and {@link IDefaultsData} instances.
     *
     * @throws ArrayIndexOutOfBoundsException if more parameters are provided than expected.
     * @throws IllegalStateException          if an invalid parameter type is encountered.
     */
    public static void parseFileDataParameters(final FileData fileData, final Object... parameters) {
        // Cloning the default arrays to avoid modifying the original defaults.
        final String[] strings = defaultFileDataStrings.clone();
        final Boolean[] booleans = defaultFileDataBooleans.clone();
        final IDefaultsData[] iDefaultsDatas = defaultIDefaultsData.clone();

        // Indexes to keep track of the current position in each array.
        int stringIndex = 0;
        int booleanIndex = 0;
        int iDefaultsDataIndex = 0;

        // Iterating through each parameter.
        for (final Object parameter : parameters) {
            // Handling String parameters.
            if (parameter instanceof String string) {
                // Ensuring the index is within the bounds of the array.
                if (stringIndex >= strings.length) {
                    ExceptionHandler.throwLogException(new ArrayIndexOutOfBoundsException("There are too many Strings in the parameters!"));
                    continue;
                }
                strings[stringIndex++] = string;
                continue;
            }

            // Handling Boolean parameters.
            if (parameter instanceof Boolean bool) {
                // Ensuring the index is within the bounds of the array.
                if (booleanIndex >= booleans.length) {
                    ExceptionHandler.throwLogException(new ArrayIndexOutOfBoundsException("There are too many Booleans in the parameters!"));
                    continue;
                }
                booleans[booleanIndex++] = bool;
                continue;
            }

            // Handling IDefaultsData parameters.
            if (parameter instanceof IDefaultsData iDefaultsData) {
                // Ensuring the index is within the bounds of the array.
                if (iDefaultsDataIndex >= iDefaultsDatas.length) {
                    ExceptionHandler.throwLogException(new ArrayIndexOutOfBoundsException("There are too many IDefaultsDatas in the parameters!"));
                    continue;
                }
                iDefaultsDatas[iDefaultsDataIndex++] = iDefaultsData;

                continue;
            }

            // Throwing an exception for invalid parameter types.
            ExceptionHandler.throwLogException(new IllegalStateException("The parameter: '" + parameter + "' is invalid!"));
        }

        // Applying the parsed parameters to the FileData object using FileDataManipulator.
        FileDataManipulator.setFileDirectory(fileData.getName(), strings[0]);
        FileDataManipulator.setFileName(fileData.getName(), strings[1]);
        FileDataManipulator.setFileExtension(fileData.getName(), strings[2]);
        FileDataManipulator.setSerializer(fileData.getName(), strings[3]);

        // Additional operations based on boolean values.
        if (booleans[0]) FileDataManipulator.selectFileData(fileData.getName());
        FileDataManipulator.setUsePluginDirectory(fileData.getName(), booleans[1]);
        FileDataManipulator.setCacheData(fileData.getName(), booleans[2]);

        // Setting the IDefaultsData instance, if any.
        FileDataManipulator.setIDefaultsData(fileData.getName(), iDefaultsDatas[0]);
    }
}