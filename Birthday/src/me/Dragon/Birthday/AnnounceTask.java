package me.Dragon.Birthday;

import java.util.Calendar;

import org.bukkit.scheduler.BukkitRunnable;
import me.Dragon.Birthday.Commands.AnnounceBirthdays;

public class AnnounceTask extends BukkitRunnable{
	Calendar cal;
	
	
	@Override
	public void run() {
		cal = Calendar.getInstance();
		if (cal.get(Calendar.MINUTE) == 0 && cal.get(Calendar.HOUR) == 9)
			AnnounceBirthdays.announceBirthdays();
		}
	
}
