package org.birthday.birthday.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.birthday.birthday.Main;
import org.birthday.birthday.Data.Birthday;

public class GetBirthdays implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if ((!(sender instanceof Player) || sender.isOp())) {
			for(Birthday user : Main.getSet()) {
				sender.sendMessage(user.getName() + "     " + user.getBirthday());
			}
		}
		else {
			sender.sendMessage("[Birthday] You must be OP to use this command.");
		}
		return true;
	}
}
