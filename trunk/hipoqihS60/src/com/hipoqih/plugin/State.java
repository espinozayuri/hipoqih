package com.hipoqih.plugin;
 
import java.util.Hashtable;

import javax.microedition.rms.RecordStore;
 
//import com.hipoqih.plugin.gps.GPS;

public class State 
{
	public static String user = "dferdom";
	public static String password = "dfd";
	public static boolean connected = false;
	public static String positionSource = "GPS";
	public static String latitude = "";
	public static String longitude = "";
	public static String seconds = "";
	public static String minutes = "";
	public static boolean autoAlert = false;
	public static boolean mapAlert = false;
	public static boolean urlMapAlert = false;
	public static int alertWidth = 0;
	public static int alertHeight = 0;
	public static int alertTop = 0;
	public static int alertLeft = 0;
	public static RecordStore recordStore;
	public static Hashtable recordMaps = new Hashtable();
	public static int zoom = 10000;
	public static String secureId = "";
}