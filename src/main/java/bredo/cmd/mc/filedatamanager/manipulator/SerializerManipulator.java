package bredo.cmd.mc.filedatamanager.manipulator;

import bredo.cmd.mc.filedatamanager.interfaces.ISerializer;
import bredo.cmd.mc.filedatamanager.registries.SerializerMatrix;
import bredo.cmd.mc.filedatamanager.validators.SerializerValidator;
import bredo.cmd.mc.unilink.validators.Validator;

/**
 * retrieve gives warning, but the method, only outputs Serializer or throws exception.
 */
public final class SerializerManipulator {

    //<editor-fold desc="Selected Serializer name">
    private static String selectedSerializerName;

    public static void selectSerializer(final String serializerName) {
        SerializerValidator.validateSerializerName(serializerName);
        selectedSerializerName = serializerName;
    }

    public static String selectedSerializer() {
        return selectedSerializerName;
    }

    public static void deselectSerializer() {
        selectedSerializerName = null;
    }
    //</editor-fold>

    //<editor-fold desc="ISerializer">
    public static void setISerializer(final String serializerName, final ISerializer iSerializer) {
        SerializerValidator.validateSerializerName(serializerName);
        Validator.validateObject(iSerializer, "ISerializer");
        SerializerMatrix.retrieve(serializerName).setiSerializer(iSerializer);
    }

    public static void setISerializer(final ISerializer iSerializer) {
        setISerializer(selectedSerializer(), iSerializer);
    }

    public static ISerializer getISerializer(final String serializerName) {
        SerializerValidator.validateSerializerName(serializerName);
        return SerializerMatrix.retrieve(serializerName).getiSerializer();
    }

    public static ISerializer getISerializer() {
        return getISerializer(selectedSerializer());
    }
    //</editor-fold>

}
