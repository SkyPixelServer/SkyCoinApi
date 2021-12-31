package hu.crackpixel.skycoinapi.skycoinapi;

import hu.crackpixel.skycoinapi.skycoinapi.Currencies.IPlayerCurrencyManager;
import hu.crackpixel.skycoinapi.skycoinapi.Currencies.SimplePlayerCurrencyManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.ServicesManager;

@SuppressWarnings("ResultOfMethodCallIgnored")
public final class SkyCoinApi extends JavaPlugin {
    public static IPlayerCurrencyManager currencyMan;
    @Override
    public void onEnable() {
        // Plugin startup logic


        ServicesManager sm = getServer().getServicesManager();

        // create our data folder

        getDataFolder().mkdirs();

        //initialize our config and stuff
        currencyMan = new SimplePlayerCurrencyManager(); // continue it oi i brb bacc
        currencyMan.init(this);
        currencyMan.load();

    }

    @Override
    public void onDisable() {
        currencyMan.save();
    }

    public static IPlayerCurrencyManager getCurrencyMan(){
        return currencyMan;
    }
}
