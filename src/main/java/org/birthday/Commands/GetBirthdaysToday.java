package org.birthday.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.birthday.Main;
import org.birthday.Data.Birthday;

public class GetBirthdaysToday implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		for(Birthday bday : Main.getBirthdayList().getTodaysBirthdays()) {
			sender.sendMessage("[Birthday] Happy Birthday " + bday.getName() + "!");
		}
		return true;
	}
}
