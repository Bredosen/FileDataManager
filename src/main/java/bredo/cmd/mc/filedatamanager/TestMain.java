package bredo.cmd.mc.filedatamanager;

import bredo.cmd.mc.filedatamanager.creators.FileDataCreator;
import bredo.cmd.mc.filedatamanager.interfaces.IDefaultsData;
import bredo.cmd.mc.filedatamanager.manipulator.DataManipulator;
import bredo.cmd.mc.filedatamanager.states.FileDataStates;
import org.bukkit.plugin.java.JavaPlugin;

public final class TestMain extends JavaPlugin {

    public final IDefaultsData defaultsData = dataSetter -> {
        dataSetter.create("motd", true);
        dataSetter.setValue("Welcome to the server!");
        dataSetter.addComment("This is the message of the day");
    };

    @Override
    public void onLoad() {
        FileDataCreator.create("configFile", false, "", "Config", "yml", true, defaultsData, true);
    }

    @Override
    public void onEnable() {
        FileDataStates.select(this, "configFile");

        FileDataStates.create();
        FileDataStates.loadDefaults();
        FileDataStates.load();
        FileDataStates.save();

        FileDataStates.deselect();

        getLogger().info("Message of the day: '" + DataManipulator.get("configFile", "motd") + '\'');
    }

    @Override
    public void onDisable() {
         FileDataStates.save("configFile");
    }
}
