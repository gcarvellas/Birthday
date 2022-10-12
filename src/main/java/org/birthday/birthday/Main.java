package org.birthday.birthday;

import java.io.IOException;
import java.util.NavigableSet;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import org.birthday.birthday.Commands.*;
import org.birthday.birthday.Data.Birthday;
import org.birthday.birthday.Data.BirthdayList;

public class Main extends JavaPlugin{
	private static BirthdayList birthdayList;
	private BukkitTask announceTask;
	private final int taskDelay = 60;

	@Override
	public void onEnable() {
		// startup, reloads, plugin reloads
		this.getConfig().options().copyDefaults();
		this.saveDefaultConfig();
		loadBirthdayList(Bukkit.getServer().getConsoleSender());
		saveBirthdayList(Bukkit.getServer().getConsoleSender());
		announceTask = new AnnounceTask().runTaskTimerAsynchronously(this, 0, taskDelay * 20);
			
		//Commands
		getCommand("birthdayAdd").setExecutor(new SetBirthday());
		getCommand("birthdayRemove").setExecutor(new RemoveBirthday());
		getCommand("birthdayGetAll").setExecutor(new GetBirthdays());
		getCommand("birthdayAnnounce").setExecutor(new AnnounceBirthdays());
		getCommand("birthdayToday").setExecutor(new GetBirthdaysToday());
	}
	
	@Override
	public void onDisable() {
		//shutdown, reloads, plugin reloads
		saveBirthdayList(Bukkit.getServer().getConsoleSender());
	}
	
	// /birthdaySave and /birthdayLoad
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player) || sender.isOp()) {
			if (cmd.getName().equalsIgnoreCase("birthdaySave"))
				saveBirthdayList(sender);
			else if(cmd.getName().equalsIgnoreCase("birthdayLoad"))
				loadBirthdayList(sender);
			return true;
		}
		else{
			sender.sendMessage("[Birthday] You must be OP to use this command.");
			return true;
		}
	}
	
	//Save and Load
	
	private void loadBirthdayList(CommandSender sender) {
		try {
			birthdayList = ConfigManager.deserialize(this.getConfig().getString("data"));
			if (birthdayList == null) {
				sender.sendMessage("[Birthday] No Birthday List found in config. Generating new Birthday List.");
				birthdayList = new BirthdayList();
			}
			else {
				sender.sendMessage("[Birthday] Birthdays have been loaded.");
			}
		} catch (ClassNotFoundException e) {
			sender.sendMessage("[Birthday] No Birthday List found in config. Generating new Birthday List.");
			birthdayList = new BirthdayList();
		} catch (IOException f) {
			sender.sendMessage("[Birthday] An IOException has occurred while loading the birthday list. Generating new Birthday List.");
			birthdayList = new BirthdayList();
		}
	}	
	
	private void saveBirthdayList(CommandSender sender) {
		try {
			this.getConfig().set("data", ConfigManager.serialize(birthdayList));
			this.saveConfig();
			sender.sendMessage("[Birthday] Birthdays have been saved.");
		}catch(IOException e) {
			sender.sendMessage("[Birthday] AN IOException has occurred while saving the list.");
		}
	}
	
	//Static Helpers
	
	public static BirthdayList getBirthdayList() {
		return birthdayList;
	}
	
	public static void addBirthday(Birthday birthday) {
		birthdayList.add(birthday);
	}
	
	public static void removeBirthday(Birthday birthday) {
		birthdayList.remove(birthday);
	}
	
	public static boolean userExists(String name) {
		return birthdayList.userExistsByName(name);
	}
	
	public static NavigableSet<Birthday> getSet() {
		return birthdayList.getSet();
	}
	
}
