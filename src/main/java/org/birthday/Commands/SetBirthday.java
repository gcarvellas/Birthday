package org.birthday.Commands;

import java.text.ParseException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.birthday.Main;
import org.birthday.Data.Birthday;

public class SetBirthday implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if ((!(sender instanceof Player) || sender.isOp()) && args.length == 2) {
			if (Main.userExists(args[0])){
				try {
					Main.removeBirthday(new Birthday(args[0], Main.getBirthdayList().getBirthdayByName(args[0])));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			try {
				Main.addBirthday(new Birthday(args[0], args[1]));
				sender.sendMessage("[Birthday] Successfully added " + args[0] + " to the birthday list.");
			} catch (ParseException e) {
				sender.sendMessage("[Birthday] Invalid input! Please enter a username and then the date in the format MM/dd/yyyy");
			}
			return true;
		}
		else if(args.length == 1) {
			if (Main.userExists(sender.getName())){
				try {
					Main.removeBirthday(new Birthday(sender.getName(), Main.getBirthdayList().getBirthdayByName(sender.getName())));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			try {
				if (Main.userExists(sender.getName())){
					Main.removeBirthday(new Birthday(sender.getName(), Main.getBirthdayList().getBirthdayByName(sender.getName())));
				}
				Main.addBirthday(new Birthday(sender.getName(), args[0]));
				sender.sendMessage("[Birthday] Successfully added " + sender.getName() + " to the birthday list.");
			} catch (ParseException e) {
				sender.sendMessage("[Birthday] Invalid input! Please enter the date in the format MM/dd/yyyy");
			}
			return true;
		}
		else {
			sender.sendMessage("[Birthday] You must be OP to use this command.");
			return true;
		}
	}
}
