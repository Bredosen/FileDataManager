package bredo.cmd.mc.filedatamanager.validators;

import bredo.cmd.mc.filedatamanager.utilities.FileData;
import bredo.cmd.mc.unilink.types.ExceptionType;
import bredo.cmd.mc.unilink.validators.Validator;

public final class SerializerValidator {

    public static boolean validateSerializer(final FileData serializer) {
        return Validator.validateObject(serializer, "Serializer", ExceptionType.CRASH_EXCEPTION);
    }

    public static boolean validateSerializerName(final String serializerName) {
        return Validator.validateString(serializerName, "Serializer name", ExceptionType.CRASH_EXCEPTION, ExceptionType.LOG_EXCEPTION, true);
    }
}
