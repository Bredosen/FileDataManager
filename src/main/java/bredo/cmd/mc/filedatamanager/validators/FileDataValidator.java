package bredo.cmd.mc.filedatamanager.validators;

import bredo.cmd.mc.filedatamanager.utilities.FileData;
import bredo.cmd.mc.unilink.types.ExceptionType;
import bredo.cmd.mc.unilink.validators.Validator;

public final class FileDataValidator {

    public static boolean validateFileData(final FileData fileData) {
        return Validator.validateObject(fileData, "FileData", ExceptionType.CRASH_EXCEPTION);
    }

    public static boolean validateFileDataName(final String fileDataName) {
        return Validator.validateString(fileDataName, "FileData name", ExceptionType.CRASH_EXCEPTION, ExceptionType.LOG_EXCEPTION, true);
    }
}
