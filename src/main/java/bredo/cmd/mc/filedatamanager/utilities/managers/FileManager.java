package bredo.cmd.mc.filedatamanager.utilities.managers;

import bredo.cmd.mc.filedatamanager.utilities.FileData;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class FileManager {

    private final FileData fileData;
    private File file;

    public FileManager(final FileData fileData) {
        this.fileData = fileData;
    }

    public void createFile(final JavaPlugin javaPlugin) {
        if (created()) return;
        final String directoryPath = (getFileData().isUsePluginDirectory() ? "/" : "") + getFileData().getFileDirectory();
        final String fullPath = (getFileData().isUsePluginDirectory() ? javaPlugin.getDataFolder().getPath() : "") + directoryPath;
        final File file = new File(fullPath, getFileData().getFileName() + '.' + getFileData().getFileExtension());

        final File directory = file.getParentFile();
        if (!directory.isDirectory()) try {
            final boolean successfully = directory.mkdirs();
            //TODO: Add successfully action for directory creation.
        } catch (final Exception exception) {
            throw new RuntimeException(exception);
        }

        if (!file.isFile()) try {
            final boolean successfully = file.createNewFile();
            //TODO: Add successfully action for file creation.
        } catch (final IOException exception) {
            throw new RuntimeException(exception);
        }

        this.file = file;
    }

    public boolean created() {
        return file != null && file.isFile();
    }

    public File getFile() {
        return file;
    }

    public FileData getFileData() {
        return fileData;
    }
}
