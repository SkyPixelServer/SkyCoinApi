package hu.crackpixel.skycoinapi.skycoinapi.Currencies;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;

public interface IPlayerCurrencyManager {
    public HashMap<String, List<Currency>> getPlayers();
    public List<Currency> getPlayerCurrencies(Player player);
    public Currency getPlayerCurrency(Player player, String currencyName);
    public void addPlayerCurrency(Player player, Currency currency, float amount);
    public void subtractPlayerCurrency(Player player, Currency currency, float amount);
    public void save();
    public void load();
    public void init(JavaPlugin plugin);


}
