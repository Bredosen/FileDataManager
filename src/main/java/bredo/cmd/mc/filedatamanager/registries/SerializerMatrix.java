package bredo.cmd.mc.filedatamanager.registries;

import bredo.cmd.mc.filedatamanager.utilities.Serializer;
import bredo.cmd.mc.unilink.utilities.Registry;

public final class SerializerMatrix extends Registry<Serializer> {

    //<editor-fold desc="Singleton & Constructor">
    private final static SerializerMatrix SINGLETON;

    static {
        SINGLETON = new SerializerMatrix("Serializer");
    }

    public static SerializerMatrix getInstance() {
        return SINGLETON;
    }

    public SerializerMatrix(final String registryType) {
        super(registryType);
    }
    //</editor-fold>

    public static boolean register(final Serializer serializer) {
        return getInstance().registerElement(serializer);
    }

    public static boolean unRegister(final Serializer serializer) {
        return getInstance().unRegisterElement(serializer);
    }

    public static boolean unRegister(final String serializerName) {
        return getInstance().unRegisterElement(serializerName);
    }

    public static boolean contains(final Serializer serializer) {
        return getInstance().containsElement(serializer);
    }

    public static boolean contains(final String serializerName) {
        return getInstance().containsElement(serializerName);
    }

    public static Serializer retrieve(final String serializerName) {
        return getInstance().retrieveElement(serializerName);
    }

}
