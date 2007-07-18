/*
 *
 * The contents of this file are subject to the GNU Public License
 * Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License.
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See
 * the License for the specific terms governing rights and limitations
 * under the License.
 *
 * Developed by Michael Juntao Yuan 2002.
 *
 * */
package com.hipoqih.plugin;
 
import java.io.*;
import java.util.*;

import javax.microedition.lcdui.*;

public class SplashScreen extends Canvas 
{
	private MIDletExiter m;
	
	public SplashScreen (MIDletExiter me) 
	{
		m = me;
	}

	public void paint (Graphics g) 
	{
		Image splash;
		int w = getWidth ();
		int h = getHeight ();

		try 
		{
			Class c = this.getClass();
			InputStream is = c.getResourceAsStream("/hipoqihSplash.PNG");
			ByteArrayOutputStream bos = new ByteArrayOutputStream ();
			byte [] buf = new byte [256];
			while (true) 
			{
				int rd = is.read (buf, 0, 256);
				if (rd == -1) break;
				bos.write (buf, 0, rd);
			}
			buf = bos.toByteArray ();
			splash = Image.createImage(buf, 0, buf.length);
			bos.close();
			is.close();

			g.drawImage(splash, w/2, h/2, Graphics.VCENTER | Graphics.HCENTER);
		} 
		catch (Exception e) 
		{
			g.drawString(e.getMessage(), w/2, h/2, Graphics.BASELINE | Graphics.HCENTER);
		}
	}
	
	public void keyPressed(int keyCode)
	{
		m.nextDisplay();
	}
}
