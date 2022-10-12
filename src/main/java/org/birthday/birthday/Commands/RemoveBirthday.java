package org.birthday.birthday.Commands;

import java.text.ParseException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.birthday.birthday.Main;
import org.birthday.birthday.Data.Birthday;

public class RemoveBirthday implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if ((!(sender instanceof Player) || sender.isOp()) && args.length == 1) {
			try {
			if (!Main.userExists(args[0])) {
				sender.sendMessage("[Birthday] Birthday does not exist in the list.");
				return true;
			}
			Main.removeBirthday(new Birthday(args[0], Main.getBirthdayList().getBirthdayByName(args[0])));
			sender.sendMessage("[Birthday] Successfully removed " + args[0] + " to the birthday list.");
			}catch(ParseException e) {
				e.printStackTrace();
			}
		}
		else if(args.length == 0) {
			if (!Main.userExists(sender.getName())) {
				sender.sendMessage("[Birthday] Birthday does not exist in the list.");
				return true;
			}
			try {
				Main.removeBirthday(new Birthday(sender.getName(), Main.getBirthdayList().getBirthdayByName(sender.getName())));
				sender.sendMessage("[Birthday] Successfully removed " + sender.getName() + " to the birthday list.");
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		else {
			sender.sendMessage("[Birthday] You must be OP to use this command.");
		}
		return true;
	}
}
