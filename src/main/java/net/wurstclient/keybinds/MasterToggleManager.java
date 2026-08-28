/*
 * Copyright (c) 2014-2023 Wurst-Imperium and contributors.
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package net.wurstclient.keybinds;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import net.wurstclient.WurstClient;
import net.wurstclient.hack.Hack;

public class MasterToggleManager
{
	private static final MasterToggleManager INSTANCE = new MasterToggleManager();
	
	private boolean clientDisabled = false;
	private Map<String, Boolean> savedHackStates = new HashMap<>();
	private long lastBackspaceTime = 0;
	private static final long DOUBLE_PRESS_THRESHOLD = 500; // milliseconds
	
	private MasterToggleManager()
	{
	}
	
	public static MasterToggleManager getInstance()
	{
		return INSTANCE;
	}
	
	public boolean isClientDisabled()
	{
		return clientDisabled;
	}
	
	public void handleBackspacePress()
	{
		long currentTime = System.currentTimeMillis();
		
		// Check if this is a double press
		if(currentTime - lastBackspaceTime < DOUBLE_PRESS_THRESHOLD)
		{
			toggleClientState();
		}
		
		lastBackspaceTime = currentTime;
	}
	
	private void toggleClientState()
	{
		if(clientDisabled)
		{
			enableClient();
		}else
		{
			disableClient();
		}
	}
	
	private void disableClient()
	{
		// Save state of all currently enabled hacks
		savedHackStates.clear();
		for(Hack hack : WurstClient.INSTANCE.getHax().getAllHax())
		{
			if(hack.isEnabled())
			{
				savedHackStates.put(hack.getName(), true);
				hack.setEnabled(false);
			}
		}
		
		clientDisabled = true;
	}
	
	private void enableClient()
	{
		// Restore previously enabled hacks
		for(Map.Entry<String, Boolean> entry : savedHackStates.entrySet())
		{
			Hack hack = WurstClient.INSTANCE.getHax().getHackByName(entry.getKey());
			if(hack != null && entry.getValue())
			{
				hack.setEnabled(true);
			}
		}
		
		savedHackStates.clear();
		clientDisabled = false;
	}
}
