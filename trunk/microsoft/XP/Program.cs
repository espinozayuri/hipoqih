/*
 * Created by Andr�s Ribera
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

using System;
using System.Collections.Generic;
using System.Windows.Forms;

namespace plugin_hipoqih
{
    static class Program
    {
        public static hipoqih frmMain = null;
        public static splash frmSplash = null;
        /// <summary>
        /// The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main()
        {
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);
            frmSplash = new splash();
            frmSplash.Show();
            frmMain = new hipoqih();
            frmSplash.Refresh();
            Application.Run(frmMain);
        }
    }
}