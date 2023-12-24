package bredo.cmd.mc.filedatamanager.serializers;

import bredo.cmd.mc.filedatamanager.creators.SerializerCreator;
import bredo.cmd.mc.filedatamanager.interfaces.ISerializer;
import bredo.cmd.mc.filedatamanager.manipulator.SerializerManipulator;
import bredo.cmd.mc.filedatamanager.registries.SerializerMatrix;
import bredo.cmd.mc.filedatamanager.utilities.Data;
import bredo.cmd.mc.filedatamanager.utilities.registries.DataRegistry;
import bredo.cmd.mc.filedatamanager.utilities.managers.FileManager;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class DefaultJsonSerializer implements ISerializer {

    private final static DefaultJsonSerializer defaultJsonSerializer;
    public final static String DEFAULT_JSON_SERIALIZER_NAME;

    static {
        defaultJsonSerializer = new DefaultJsonSerializer();
        DEFAULT_JSON_SERIALIZER_NAME = "defaultJsonSerializer";
    }

    public static void createDefaultJsonSerializer() {
        SerializerCreator.create(DEFAULT_JSON_SERIALIZER_NAME, true);
        SerializerManipulator.setISerializer(getDefaultJsonSerializer());
        SerializerManipulator.deselectSerializer();
    }

    public static boolean createdDefaultJsonSerializer() {
        return SerializerMatrix.contains(DEFAULT_JSON_SERIALIZER_NAME);
    }

    public static DefaultJsonSerializer getDefaultJsonSerializer() {
        return defaultJsonSerializer;
    }

    private static boolean addNewLine;

    @Override
    public void save(final DataRegistry dataRegistry, final FileManager fileManager) {

        final YamlConfiguration yamlConfiguration = new YamlConfiguration();
        boolean first = true;
        for (final Data data : dataRegistry.getRegistry()) {
            yamlConfiguration.set(data.getPath(), data.getData());
            if (!data.containsComments()) continue;
            final List<String> comments = new ArrayList<>();
            for (final String comment : data.getComments()) {
                if (isAddNewLine() && !first) comments.add(null);
                comments.add(comment);
            }
            yamlConfiguration.setComments(data.getPath(), comments);
            first = false;
        }

        try {
            yamlConfiguration.save(fileManager.getFile());
        } catch (final IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void load(final DataRegistry dataRegistry, final FileManager fileManager) {
        final YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(fileManager.getFile());
        for (final Data data : dataRegistry.getRegistry()) {
            final Object dataFromFile = yamlConfiguration.get(data.getPath(), data.getData());
            if (!yamlConfiguration.contains(data.getPath())) continue;
            if (!data.getData().equals(dataFromFile)) data.setData(dataFromFile);
        }
    }

    public static boolean isAddNewLine() {
        return addNewLine;
    }

    public static void setAddNewLine(final boolean addNewLine) {
        DefaultJsonSerializer.addNewLine = addNewLine;
    }
}
