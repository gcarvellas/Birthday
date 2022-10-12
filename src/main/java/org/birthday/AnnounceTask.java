package org.birthday;

import java.util.Calendar;

import org.birthday.Commands.AnnounceBirthdays;
import org.bukkit.scheduler.BukkitRunnable;

public class AnnounceTask extends BukkitRunnable{
	Calendar cal;
	
	
	@Override
	public void run() {
		cal = Calendar.getInstance();
		if (cal.get(Calendar.MINUTE) == 0 && cal.get(Calendar.HOUR) == 0)
			AnnounceBirthdays.announceBirthdays();
		}
	
}
