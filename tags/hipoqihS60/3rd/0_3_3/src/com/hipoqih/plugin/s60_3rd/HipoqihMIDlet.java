/*
 * Created by Javier Cancela
 * Copyright (C) 2007 hipoqih.com, All Rights Reserved.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, If not, see <http://www.gnu.org/licenses/>.*/

package com.hipoqih.plugin.s60_3rd;


import com.hipoqih.plugin.*;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

import com.hipoqih.plugin.UI.*;
import com.hipoqih.plugin.s60_3rd.gps.*;

public class HipoqihMIDlet extends MIDlet implements CommandListener, ProviderStatusListener, MIDletExiter
{
	private Command exitCommand;
	private Command goCommand;
	private static Display display;
    private Object mutex = new Object();
    private HipoqihData data = null;

	public HipoqihMIDlet () throws Exception 
	{ 
		display = Display.getDisplay(this);
		exitCommand = new Command("EXIT", Command.SCREEN, 2);
		goCommand = new Command("GO", Command.SCREEN, 1);
	}
	
	public static Display getDisplay()
	{
		return display;
	}
 
	public void startApp() 
	{
		try 
		{
			// Initialize any exisitng security info
			// that might be stored in RMS stores.
			State.getInstance().loadConfiguration();
	    } 
		catch (Exception e) 
	    {
	      e.printStackTrace();
	    }

	    SplashScreen splash = new SplashScreen ();
	    splash.addCommand(exitCommand);
	    splash.addCommand(goCommand);
	    splash.setFullScreenMode(true);
	    splash.setCommandListener( (CommandListener) this);
	    display.setCurrent(splash);
	}
	
	public void exit()
	{
		destroyApp(false);
	    notifyDestroyed();
	}
	
	public void pauseApp() 
	{
	}
	 
	public void destroyApp(boolean unconditional) 
	{
		try
		{
			State.recordStore.closeRecordStore();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void commandAction(Command command, Displayable screen) 
	{
		if (command == exitCommand) 
		{
			destroyApp(false);
			notifyDestroyed();
	    } 
		else if (command == goCommand) 
		{ 
	        if (ConfigurationProvider.isLocationApiSupported())
	        {
	            ConfigurationProvider.getInstance().autoSearch(this);
	        }
	        else
	        {
	            Alert alert = new Alert("Error",
	                    "Location API not supported!", null,
	                    AlertType.ERROR);
	            display.setCurrent(alert);
	        }

			// MainFormUI need to access life cycle and MIDlet methods
			MainFormUI.m = this;
	    }
	}
	
    public void providerSelectedEvent()
    {
        // Attempt to acquire the mutex
        synchronized (mutex)
        {
            // Start scanning location updates. Also set the TouristData
            // reference data.
            Gauge indicator = new Gauge(null, false, 50, 1);
            indicator.setValue(Gauge.CONTINUOUS_RUNNING);

            Alert alert = new Alert("Information",
                    "Please wait, looking for location data....", null,
                    AlertType.INFO);
            alert.setIndicator(indicator);

            display.setCurrent(alert);

            // Inform the user that MIDlet is looking for location data.
            data = new HipoqihData((ProviderStatusListener) this);
        }
    }
    
    public void firstLocationUpdateEvent()
    {
        // Attempt to acquire the mutex
        synchronized (mutex)
        {
        	MainFormUI mf = new MainFormUI();
        	data.setMainForm(mf);
        	display.setCurrent(mf);
        }
    }

}
