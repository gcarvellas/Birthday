package org.birthday.birthday;

import java.util.Calendar;

import org.bukkit.scheduler.BukkitRunnable;
import org.birthday.birthday.Commands.AnnounceBirthdays;

public class AnnounceTask extends BukkitRunnable{
	Calendar cal;
	
	
	@Override
	public void run() {
		cal = Calendar.getInstance();
		if (cal.get(Calendar.MINUTE) == 0 && cal.get(Calendar.HOUR) == 0)
			AnnounceBirthdays.announceBirthdays();
		}
	
}
