package bredo.cmd.mc.filedatamanager.creators;

import bredo.cmd.mc.filedatamanager.manipulator.SerializerManipulator;
import bredo.cmd.mc.filedatamanager.utilities.Serializer;
import bredo.cmd.mc.filedatamanager.registries.SerializerMatrix;

/**
 * This class is responsible for creating instances of {@link Serializer} and managing their lifecycle.
 * It interacts with the {@link SerializerMatrix} to register new serializers and optionally selects them
 * for manipulation via {@link SerializerManipulator}.
 * <p>
 * This class is a part of the file data management system specifically tailored for handling
 * serialization tasks within a Minecraft server environment.
 * </p>
 *
 * @see Serializer
 * @see SerializerMatrix
 * @see SerializerManipulator
 */
public final class SerializerCreator {

    /**
     * Creates a {@link Serializer} instance with the specified name and registers it.
     * This method provides a simplified interface for {@link Serializer} creation without
     * the need for further manipulation.
     *
     * @param name The name of the serializer to be created. This name is used as an identifier
     *             in the {@link SerializerMatrix}.
     * @see #create(String, boolean)
     */
    public static void create(final String name) {
        create(name, false);
    }

    /**
     * Creates a {@link Serializer} instance with the specified name, registers it, and optionally
     * selects it for further manipulation. This method offers more control over the serializer's lifecycle
     * by allowing it to be immediately selected for manipulation after creation.
     *
     * @param name                  The name of the serializer to be created. This name is used as an identifier
     *                              in the {@link SerializerMatrix}.
     * @param selectForManipulation If {@code true}, the created serializer is selected for manipulation using
     *                              {@link SerializerManipulator}.
     * @see Serializer#createInstance(String)
     * @see SerializerMatrix#register(Serializer)
     * @see SerializerManipulator#selectSerializer(String)
     */
    public static void create(final String name, final boolean selectForManipulation) {
        // Create a new instance of Serializer with the provided name
        final Serializer serializer = Serializer.createInstance(name);
        // Register the new serializer in the SerializerMatrix
        SerializerMatrix.register(serializer);
        // If selected for manipulation, make this serializer the active one in SerializerManipulator
        if (selectForManipulation) SerializerManipulator.selectSerializer(name);
    }
}
