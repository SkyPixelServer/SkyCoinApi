package hu.crackpixel.skycoinapi.skycoinapi.Currencies;

import hu.crackpixel.skycoinapi.skycoinapi.SkyCoinApi;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;



public class SimplePlayerCurrencyManager implements IPlayerCurrencyManager {
    private HashMap<String, List<Currency>> Players = new HashMap<>();
    private SkyCoinApi plugin;

    public HashMap<String, List<Currency>> getPlayers() {
        return Players;
    }

    public List<Currency> getPlayerCurrencies(Player player) {
        return Players.get(player);
    }

    public Currency getPlayerCurrency(Player player, String currencyName) {
        List<Currency> currencies = Players.get(player.getUniqueId().toString());
        if (currencies == null) {
            currencies = new java.util.ArrayList<>();
            Players.put(player.getUniqueId().toString(), currencies);
        }
        if (currencies.stream().filter(c -> c.getName().equals(currencyName)).findFirst().orElse(null) == null) {
            currencies.add(new Currency(currencyName, 0));
        }
        // find the currency to modify by its name

        Currency currency = currencies.stream().filter(c -> c.getName().equals(currencyName)).findFirst().orElse(null);
        return currency;
    }

    public void setPlayerCurrency(Player player, Currency currency, float amount) {
        List<Currency> currencies = Players.get(player.getUniqueId().toString());
        if (currencies == null) {
            currencies = new java.util.ArrayList<>();
            Players.put(player.getUniqueId().toString(), currencies);
        }
        if (!currencies.contains(currency)) {
            currencies.add(currency);
        }
        // find the currency to modify by its name

        Currency currencyToModify = currencies.stream().filter(c -> c.getName().equals(currency.getName())).findFirst().orElse(null);
        assert currencyToModify != null;
        currencyToModify.setAmount(amount);
    }

    public void setPlayerCurrency(Player player, Currency currency, int amount) {
        List<Currency> currencies = Players.get(player.getUniqueId().toString());
        if (currencies == null) {
            currencies = new java.util.ArrayList<>();
            Players.put(player.getUniqueId().toString(), currencies);
        }
        if (!currencies.contains(currency)) {
            currencies.add(currency);
        }
        // find the currency to modify by its name

        Currency currencyToModify = currencies.stream().filter(c -> c.getName().equals(currency.getName())).findFirst().orElse(null);
        assert currencyToModify != null;
        currencyToModify.setAmount((float)amount);
    }

    public void addPlayerCurrency(Player player, Currency currency, float amount) {
        List<Currency> currencies = Players.get(player.getUniqueId().toString());
        if (currencies == null) {
            currencies = new java.util.ArrayList<>();
            Players.put(player.getUniqueId().toString(), currencies);
        }
        if (!currencies.contains(currency)) {
            currencies.add(currency);
        }
        // find the currency to modify by its name

        Currency currencyToModify = currencies.stream().filter(c -> c.getName().equals(currency.getName())).findFirst().orElse(null);
        assert currencyToModify != null;
        currencyToModify.addAmount(amount);


    }

    public void addPlayerCurrency(Player player, Currency currency, int amount) {
        List<Currency> currencies = Players.get(player.getUniqueId().toString());
        if (currencies == null) {
            currencies = new java.util.ArrayList<>();
            Players.put(player.getUniqueId().toString(), currencies);
        }
        if (!currencies.contains(currency)) {
            currencies.add(currency);
        }
        // find the currency to modify by its name

        Currency currencyToModify = currencies.stream().filter(c -> c.getName().equals(currency.getName())).findFirst().orElse(null);
        assert currencyToModify != null;
        currencyToModify.addAmount((float)amount);


    }

    public void subtractPlayerCurrency(Player player, Currency currency, float amount) {
        List<Currency> currencies = Players.get(player.getUniqueId().toString());
        if (currencies == null) {
            currencies = new java.util.ArrayList<>();
            Players.put(player.getUniqueId().toString(), currencies);
        }
        if (!currencies.contains(currency)) {
            currencies.add(currency);
        }
        // find the currency to modify by its name

        Currency currencyToModify = currencies.stream().filter(c -> c.getName().equals(currency.getName())).findFirst().orElse(null);
        assert currencyToModify != null;
        currencyToModify.subtractAmount(amount);

    }

    public void subtractPlayerCurrency(Player player, Currency currency, int amount) {
        List<Currency> currencies = Players.get(player.getUniqueId().toString());
        if (currencies == null) {
            currencies = new java.util.ArrayList<>();
            Players.put(player.getUniqueId().toString(), currencies);
        }
        if (!currencies.contains(currency)) {
            currencies.add(currency);
        }
        // find the currency to modify by its name

        Currency currencyToModify = currencies.stream().filter(c -> c.getName().equals(currency.getName())).findFirst().orElse(null);
        assert currencyToModify != null;
        currencyToModify.subtractAmount((float)amount);

    }

    // save the players and their currencies and amounts in a file
    public void save() {
        // open yml file
        // save the players and their currencies and amounts in a file
        System.out.println(plugin.getDataFolder());
        File rawFile = new File(plugin.getDataFolder(), "player_currencies.yml");
        if (!rawFile.exists()) {
            try {
                rawFile.createNewFile(); // idk maybe maven wants a trycatch on shit idk
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        YamlConfiguration file = YamlConfiguration.loadConfiguration(rawFile);



        for (String player : Players.keySet()) {
            List<Currency> currencies = Players.get(player);
            for (Currency currency : currencies) {
                System.out.println("AMOUNT OF PLAYER UUID "+player+": "+currency.getAmount());
                file.set(player + "." + currency.getName(), currency.getAmount());
            }
        }
        try {

            File tosave = new File(plugin.getDataFolder(), "player_currencies.yml");
            if (!tosave.exists()) {
                tosave.createNewFile();
            }
            file.save(tosave);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // load the players and their currencies and amounts from a file
    public void load() {
        // open yml file
        // load the players and their currencies and amounts from a file
        try {
            File rawFile = new File(plugin.getDataFolder(), "player_currencies.yml");
            if (!rawFile.exists()) {
                rawFile.createNewFile();
            }
            FileConfiguration file = YamlConfiguration.loadConfiguration(rawFile);
            for (String key : file.getKeys(false)) {
                List<Currency> currencies = new java.util.ArrayList<>();
                for (String currencyName : file.getConfigurationSection(key).getKeys(false)) {
                    Currency currency = new Currency(currencyName, (float) file.getDouble(key + "." + currencyName));
                    currency.setAmount(file.getDouble(key + "." + currencyName));
                    currencies.add(currency);
                }
                // we őput putp ut the player in the hashmap
                Players.put(key, currencies);
            }
        } catch(Exception e) {

        }
    }


    public void init(JavaPlugin plugin) {
        this.plugin = (SkyCoinApi) plugin;

    }



}
