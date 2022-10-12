package org.birthday.birthday.Commands;

import org.bukkit.Bukkit;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.birthday.birthday.Main;
import org.birthday.birthday.Data.Birthday;

public class AnnounceBirthdays implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player) || sender.isOp()) {
			announceBirthdays();
			return true;
		}
		else {
			sender.sendMessage("[Birthday] You must be OP to use this command.");
			return true;
		}
	}
	
	public static void announceBirthdays() {
		for(Birthday bday : Main.getBirthdayList().getTodaysBirthdays()) {
			Bukkit.broadcastMessage("[Birthday] Happy Birthday " + bday.getName() + "!");
		}
	}
}
