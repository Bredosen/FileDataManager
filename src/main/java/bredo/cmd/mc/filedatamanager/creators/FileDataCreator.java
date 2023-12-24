package bredo.cmd.mc.filedatamanager.creators;

import bredo.cmd.mc.filedatamanager.registries.FileDataRegistry;
import bredo.cmd.mc.filedatamanager.parsers.FileDataCreatorParser;
import bredo.cmd.mc.filedatamanager.serializers.DefaultJsonSerializer;
import bredo.cmd.mc.filedatamanager.utilities.FileData;

/**
 * This class is responsible for creating instances of {@link FileData}.
 * It encapsulates the logic for initializing and registering FileData objects,
 * making use of various components like {@link FileDataRegistry},
 * {@link FileDataCreatorParser}, and {@link DefaultJsonSerializer}.
 *
 * <p>This class is designed with a final modifier to prevent inheritance,
 * emphasizing its role as a utility class with static methods.
 * It is part of the file data management system, specifically focusing on
 * the creation and initialization phase of FileData objects.</p>
 *
 * @see FileData
 * @see FileDataRegistry
 * @see FileDataCreatorParser
 * @see DefaultJsonSerializer
 */
public final class FileDataCreator {

    /**
     * Creates a {@link FileData} object with the given name. This is a convenience
     * method that delegates to the {@link #create(String, Object...)} method
     * with a default parameter setting.
     *
     * <p>It initializes a new FileData instance and registers it with the {@link FileDataRegistry}.
     * This method is typically used when no additional parameters are needed
     * for the FileData creation.</p>
     *
     * @param name The name of the FileData object to create.
     *
     * @see #create(String, Object...)
     */
    public static void create(final String name) {
        create(name, false);
    }

    /**
     * Creates a {@link FileData} object with the specified name and additional parameters.
     * This method serves as the primary way to initialize and register FileData objects
     * within the system.
     *
     * <p>It first ensures that a default JSON serializer is available by checking and
     * potentially creating a {@link DefaultJsonSerializer}. Then, it creates a new
     * FileData instance, registers it with the {@link FileDataRegistry}, and parses
     * the additional parameters using {@link FileDataCreatorParser}.</p>
     *
     * @param name      The name of the FileData object to create.
     * @param parameter An array of additional parameters used in the creation of the FileData.
     *                  These parameters are parsed and applied to the FileData object.
     *
     * @see FileDataRegistry#register(FileData)
     * @see FileDataCreatorParser#parseFileDataParameters(FileData, Object...)
     * @see DefaultJsonSerializer#createDefaultJsonSerializer()
     */
    public static void create(final String name, final Object... parameter) {
        if (!DefaultJsonSerializer.createdDefaultJsonSerializer()) DefaultJsonSerializer.createDefaultJsonSerializer();

        final FileData fileData = FileData.createFileData(name);
        FileDataRegistry.register(fileData);
        FileDataCreatorParser.parseFileDataParameters(fileData, parameter);
    }
}