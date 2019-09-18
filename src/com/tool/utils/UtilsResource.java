// copy from iSQLViewer package org.isqlviewer.util; BasicUtilities

/*
 * Copyright 2002 by Mark A. Kobold
 * 
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at http://www.mozilla.org/MPL/
 * 
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * 
 * The Original Code is iSQL-Viewer, A Mutli-Platform Database Tool.
 * 
 * The Initial Developer of the Original Code is Markus A. Kobold.
 * 
 * Portions created by Mark A. Kobold are Copyright (C) Copyright (C) 2000 Mark A.
 * Kobold. All Rights Reserved. Contributor(s): Mark A. Kobold
 * <mkobold@sprintpcs.com> .
 * 
 * Contributor(s): all the names of the contributors are added in the source
 * code where applicable.
 * 
 * If you didn't download this code from the following link, you should check
 * if you aren't using an obsolete version: http://isql.sourceforge.net/
 */
package com.tool.utils;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import java.util.*;

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.undo.*;



/**
 * An average utilities object like that of SwingUtilities and Utilities.
 * <p>
 * This is abstract since there is no need to have an instance of this class
 * especially since all methods are static anyways.
 * <p>
 * I have put most of the things that tend to be used over and over and this
 * somewhat centralizes some functionality, paticularly when dealing with
 * resource bundles.
 * <p>
 * In the other comments there will be a reference to the default bundle, this
 * actually in reference to the ResourceBundle from org. isqlviewer.
 * resource.ResourceBundle
 * <p>
 * I will admit there are some iSQL-Viewer specific dependancies here however I
 * still feel this is the proper packaging for this class, since the
 * dependancies are minor and can be easily removed.
 * 
 * @author Markus A. Kobold &lt;mkobold at sprintpcs dot com&gt;
 * @version 1.0
 */
public abstract class UtilsResource {
	/*private static Toolkit tk = Toolkit.getDefaultToolkit();
	public static final DataFlavor bookmarkFlavour = new DataFlavor(SQLBookmark.class, "SQL-Bookmark");
	public static final DataFlavor serviceFlavour = new DataFlavor(ServiceDefinition.class, "iSQL-Service");

	public static final int CMD_MASK = tk.getMenuShortcutKeyMask();*/

	private static ResourceBundle res = ResourceBundle.getBundle("resource.ResourceStrings");
	//private static final JTextPopup textPopup = new JTextPopup();

	/*
	 * // see getKeyStroke (String)
	 */
	/*private static ModifierKeyword[] modifierKeywords =
		{
			new ModifierKeyword("shift", InputEvent.SHIFT_MASK),
			new ModifierKeyword("control", InputEvent.CTRL_MASK),
			new ModifierKeyword("ctrl", InputEvent.CTRL_MASK),
			new ModifierKeyword("meta", CMD_MASK),
			new ModifierKeyword("alt", InputEvent.ALT_MASK),
			new ModifierKeyword("button1", InputEvent.BUTTON1_MASK),
			new ModifierKeyword("button2", InputEvent.BUTTON2_MASK),
			new ModifierKeyword("button3", InputEvent.BUTTON3_MASK)};*/

	/**
	 * Sets the look and feel inside the UIManager.
	 * <p>
	 * This class will also reset the DefaultMetalTheme if there is an instance
	 * of a Metal look and feel currently installed. This mainly to restore
	 * from switching to and from the KnustoffLookAndFeel
	 * <p>
	 * There is a small chance that if the given class name cannot be used as a
	 * look and feel and falling back to the system defined look and feel fails
	 * as well this will cause the JVM to exit, however you have to really
	 * screw with things to get that to happen anyways.
	 * 
	 * @param classname of the new LookAndFeel to use.
	 */
	/*public static void setLookAndFeel(String classname) {
		SystemConfig config = SystemConfig.getInstance();
		try {
			Class c = Class.forName(classname, true, config.getRuntimeClassLoader());
			LookAndFeel laf = (LookAndFeel) c.newInstance();
			if (UIManager.getLookAndFeel() instanceof MetalLookAndFeel) {
				MetalLookAndFeel.setCurrentTheme(new DefaultMetalTheme());
			}
			UIManager.setLookAndFeel(laf);
		} catch (Throwable t) {
			HandleException(t, getString("LAF_Error", classname));
			try {
				classname = UIManager.getSystemLookAndFeelClassName();
				UIManager.setLookAndFeel(classname);
			} catch (Throwable t2) {
				HandleException(t2, getString("LAF_Error", classname));
				System.exit(-1);
			}
		}
	}*/

	/**
	 * Returns a localized keystroke from the default resource bundle.
	 * <p>
	 * 
	 * @param str Resource Key to load keystroke from.
	 * @return KeyStroke from the default bundle.
	 * @see #getLocalKeyStroke(ResourceBundle, String)
	 */
	/*public static KeyStroke getLocalKeyStroke(String str) {
		return getLocalKeyStroke(res, str);
	}*/

	/**
	 * Returns a parameterized resource string from the default resource
	 * bundle.
	 * <p>
	 * The params may be null.
	 * 
	 * @param str resource key to load.
	 * @param params single parameter to format the localized string with.
	 * @return String that is the formatted localized string.
	 * @see #getString(ResourceBundle, String, String[])
	 */
	public static String getString(String str, String params) {
		return getString(res, str, params);
	}

	/**
	 * Returns a parameterized resource string from the default resource
	 * bundle.
	 * <p>
	 * The params may be null.
	 * 
	 * @param str resource key to load.
	 * @param params parameter list to format into the localized string
	 * @return String that is the formatted localized string.
	 * @see #getString(ResourceBundle, String, String[])
	 */
	public static String getString(String str, String[] params) {
		return getString(res, str, params);
	}

	/**
	 * Returns a reesource string from the default resource bundle.
	 * <p>
	 * 
	 * @param str resource key to load.
	 * @return String that is the localized string.
	 * @see #getString(ResourceBundle, String, String[])
	 */
	public static String getString(String str) {
		return getString(res, str);
	}

	/**
	 * Helper method for detecting a 1.4.xxx runtime.
	 * <p>
	 * Basically check is the System java.specification.version starts with 1.4
	 * this should work for all JDK 1.4.x
	 * 
	 * @return true if is 1.4.x runtime, otherwise false for something else
	 */
	/*public static boolean is1dot4Runtime() {
		return System.getProperty("java.specification.version", "").startsWith("1.4");
	}*/

	/**
	 * Creates a local resource URL with the given item.
	 * <p>
	 * All URLs are relative to 'org/isqlviewer/resource/'
	 * 
	 * @param item relative identifier for resource.
	 * @return URL for the relative URL plus the item.
	 */
	/*public final static URL getResourceURL(String item) {
		//return org.isqlviewer.core.Launcher.class.getResource("/org/isqlviewer/resource/".concat(item));
		return utils.Launcher.class.getResource("/org/isqlviewer/resource/".concat(item));
	}*/

	/**
	 * Method that will load an Icon with a size designatedd for Toolbars.
	 * <p>
	 * This method gets an instance of the SystemConfig to get the preferred
	 * size of toolbar icons and then loads the corresponding icon for that
	 * size.
	 * 
	 * @param img simple name of the image to load.
	 * @return the desired icon to be loaded.
	 */
	/*public final static Icon loadToolbarIconResource(final String img) {
		UserPreferences prefs = SystemConfig.getInstance().getPreferences();
		int size = prefs.getBoolean(ConfigConstants.KEY_USE_LARGE_ICONS) ? 24 : 16;
		return loadIconResource(img + size);
	}*/

	/**
	 * Loads an Icon from the default location.
	 * <p>
	 * Attampts to load icon by shortname assumes .png extenstion from the
	 * 'icon' subfolder of the default resources directory.
	 * <p>
	 * If filename does not exists a message will be printed out to the
	 * System.err and a default Icon from the UIManager will be provided.
	 * 
	 * @see #getResourceURL(String)
	 * @param img short name of the image to load.
	 * @return Icon object for the Image file.
	 */
	/*public final static Icon loadIconResource(final String img) {
		try {
			return new ImageIcon(getResourceURL("icons/" + img + ".png"));
		} catch (Throwable t) { // This Should Be here since this is defined in
			// BasicLookAndFeel
			System.err.println(getString("Image_Load_Error", img));
			return UIManager.getIcon("InternalFrame.icon");
		}
	}
      */
	/**
	 * Method optimizeTableView.
	 * 
	 * @param Table
	 */
	/*public static void optimizeTableView(JTable Table) {
		if (Table == null)
			return;
		if (!(Table.getParent() instanceof JViewport))
			return;

		JViewport jvp = (JViewport) Table.getParent();
		TableModel ref = Table.getModel();
		Table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		Table.setModel(ref);

		for (int i = 0; i < Table.getColumnCount(); i++) {
			Table.getColumnModel().getColumn(i).sizeWidthToFit();
		}
		synchronized (Table) {
			if (Table.getPreferredSize().getWidth() <= jvp.getPreferredSize().getWidth())
				Table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		}
	}*/

	/**
	 * Removes Selected columns from JTable.
	 * <p>
	 * This method will only work with EnhancedTableModels, and works based on
	 * the current selected columns on the JTable.
	 * 
	 * @see #removeSelectedRows(JTable)
	 * @see EnhancedTableModel#removeColumn(int)
	 * @see JTable#getSelectedColumns()
	 * @param table with selections made.
	 */
	/*public static void removeSelectedColumns(JTable table) {
		if (table == null) {
			return;
		} else {
			EnhancedTableModel model = (EnhancedTableModel) table.getModel();
			int cols[] = table.getSelectedColumns();
			for (int i = cols.length - 1; i >= 0; i--) {
				model.removeColumn(cols[i]);
			}
			cols = null;
			model = null;

			optimizeTableView(table);
		}
	}*/

	/**
	 * Consistent way to chosing a file to save to with JFileChooser.
	 * <p>
	 * 
	 * @param owner to show the component relative to.
	 * @return File based on the user selection can be null.
	 */
	/*public static File saveSystemFile(Component owner) {
		UserPreferences prefs = SystemConfig.getInstance().getPreferences();
		JFileChooser jfc = new JFileChooser(prefs.get(ConfigConstants.KEY_SES_LAST_FILE));
		jfc.setFileHidingEnabled(!prefs.getBoolean(ConfigConstants.KEY_SHOW_HIDDEN_FILES));
		int result = jfc.showSaveDialog(owner);
		prefs.put(ConfigConstants.KEY_SES_LAST_FILE, jfc.getCurrentDirectory().getAbsolutePath());

		if (result == JFileChooser.APPROVE_OPTION) {
			File selection = jfc.getSelectedFile();
			if (selection.exists()) {
				String msg = getString("File_Overwrite_Message", selection.getName());
				String title = getString("Warning");
				int response = JOptionPane.showConfirmDialog(owner, msg, title, JOptionPane.YES_NO_OPTION);
				if (response == JOptionPane.YES_OPTION) {
					return selection;
				} else {
					return null;
				}
			} else {
				try {
					if (selection.createNewFile()) {
						return selection;
					} else {
						return null;
					}
				} catch (IOException ioe) {
					HandleException(ioe, "File::createNewFile()");
					return null;
				}
			}
		} else {
			return null;
		}
	}*/

	/**
	 * Consistent way to chosing a file to open with JFileChooser.
	 * <p>
	 * 
	 * @see JFileChooser#setFileSelectionMode(int)
	 * @see #getSystemFiles(Component, int)
	 * @param owner to show the component relative to.
	 * @param mode selection mode for the JFileChooser.
	 * @return File based on the user selection can be null.
	 */
	/*public static File getSystemFile(Component owner, int mode) {
		UserPreferences prefs = SystemConfig.getInstance().getPreferences();

		JFileChooser jfc = new JFileChooser(prefs.get(ConfigConstants.KEY_SES_LAST_FILE));
		jfc.setFileSelectionMode(mode);
		jfc.setFileHidingEnabled(!prefs.getBoolean(ConfigConstants.KEY_SHOW_HIDDEN_FILES));
		int result = jfc.showOpenDialog(owner);
		prefs.put(ConfigConstants.KEY_SES_LAST_FILE, jfc.getCurrentDirectory().getAbsolutePath());

		if (result == JFileChooser.APPROVE_OPTION)
			return jfc.getSelectedFile();
		else
			return null;
	}*/

	/**
	 * Consistent way to chosing multiple files to save with JFileChooser.
	 * <p>
	 * 
	 * @see #saveSystemFile(Component)
	 * @param owner to show the component relative to.
	 * @return File[] based on the user selection can be null.
	 */
	/*public static File[] saveSystemFiles(Component owner) {
		UserPreferences prefs = SystemConfig.getInstance().getPreferences();

		JFileChooser jfc = new JFileChooser(prefs.get(ConfigConstants.KEY_SES_LAST_FILE));
		jfc.setFileHidingEnabled(!prefs.getBoolean(ConfigConstants.KEY_SHOW_HIDDEN_FILES));
		jfc.setMultiSelectionEnabled(true);
		int result = jfc.showSaveDialog(owner);
		prefs.put(ConfigConstants.KEY_SES_LAST_FILE, jfc.getCurrentDirectory().getAbsolutePath());

		if (result == JFileChooser.APPROVE_OPTION)
			return jfc.getSelectedFiles();
		else
			return new File[0];
	}*/

	/**
	 * Consistent way to chosing multiple files to open with JFileChooser.
	 * <p>
	 * 
	 * @see JFileChooser#setFileSelectionMode(int)
	 * @see #getSystemFiles(Component, int)
	 * @param owner to show the component relative to.
	 * @param mode selection mode for the JFileChooser.
	 * @return File[] based on the user selection can be null.
	 */
	/*public static File[] getSystemFiles(Component owner, int mode) {
		UserPreferences prefs = SystemConfig.getInstance().getPreferences();

		JFileChooser jfc = new JFileChooser(prefs.get(ConfigConstants.KEY_SES_LAST_FILE));
		jfc.setFileSelectionMode(mode);
		jfc.setFileHidingEnabled(!prefs.getBoolean(ConfigConstants.KEY_SHOW_HIDDEN_FILES));
		jfc.setMultiSelectionEnabled(true);
		int result = jfc.showOpenDialog(owner);
		prefs.put(ConfigConstants.KEY_SES_LAST_FILE, jfc.getCurrentDirectory().getAbsolutePath());

		if (result == JFileChooser.APPROVE_OPTION)
			return jfc.getSelectedFiles();
		else
			return new File[0];
	}*/

	/**
	 * Removes Selected rows from JTable.
	 * <p>
	 * This method will only work with EnhancedTableModels, and works based on
	 * the current selected columns on the JTable.
	 * 
	 * @see #removeSelectedColumns(JTable)
	 * @see EnhancedTableModel#removeRow(int)
	 * @see JTable#getSelectedRows()
	 * @param table with selections made.
	 */
	/*public static void removeSelectedRows(JTable table) {
		if (table == null) {
			return;
		} else {
			EnhancedTableModel model = (EnhancedTableModel) table.getModel();
			int rows[] = table.getSelectedRows();
			for (int i = rows.length - 1; i >= 0; i--)
				model.removeRow(rows[i]);
			rows = null;
			model = null;
		}
	}*/

	/**
	 * Helper method to show an exception in a popup window with stack trace
	 * details.
	 * <p>
	 * 
	 * @param owner to show the component relative to.
	 * @param t exception or error to show.
	 * @param Message extra message for this error.
	 */
	/*public static void showExceptionPopup(Component owner, Throwable t, String Message) {
		Object msg = new ThrowableOptionPane(t, Message);
		JOptionPane.showMessageDialog(owner, msg, getString("Error"), JOptionPane.ERROR_MESSAGE);
	}*/

	/**
	 * Utility method for creating KeyStrokes.
	 * <p>
	 * Synonym for 'KeyStroke.getKeyStroke(vk, mask, false)'
	 * 
	 * @param VK constant for the Key
	 * @param Mask standared input mask
	 * @return KeyStroke based on the given parameters.
	 */
	/*public static KeyStroke createKeyStroke(int VK, int Mask) {
		return KeyStroke.getKeyStroke(VK, Mask, false);
	}*/

	/**
	 * Creates a tab delimited text for selected cells of the given JTable.
	 * <p>
	 * 
	 * @param table to copy selection from.
	 */
	/*public static void copySelectedCellsToClipBoard(JTable table) {
		if (table == null) {
			return;
		} else {
			try {
				table.getCellEditor().cancelCellEditing();
			} catch (Throwable t) {

			}
			StringBuffer buff = new StringBuffer("");
			StringBuffer row = new StringBuffer("");
			for (int r = 0; r < table.getRowCount(); r++) {
				for (int c = 0; c < table.getColumnCount(); c++) {
					if (table.isCellSelected(r, c))
						row.append(table.getValueAt(r, c) + "\t");
				}

				if (row.toString().trim().length() >= 1) {
					buff.append(row);
					buff.append(System.getProperty("line.seperator", "\n"));
				}
				row.setLength(0);
			}

			Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
			StringSelection ss = new StringSelection(buff.toString().trim());
			cb.setContents(ss, ss);
		}
	}*/

	/**
	 * Utilitiy method to restore System.out to the default FileDescriptor.
	 * <p>
	 */
	/*public static void restoreSystemOut() {
		PrintStream out = new PrintStream(new FileOutputStream(FileDescriptor.out));
		System.setOut(out);
	}*/

	/**
	 * Utilitiy method to restore System.err to the default FileDescriptor.
	 * <p>
	 */
	/*public static void restoreSystemErr() {
		PrintStream out = new PrintStream(new FileOutputStream(FileDescriptor.err));
		System.setErr(out);
	}*/

	/**
	 * Base Method for retrieving strings from a given ResourceBundle.
	 * <p>
	 * This method is used by all the getString(XXX) methods in this class.
	 * pretty basic stuff here gets the string from the bundle, however it will
	 * return the given string is the bundle cannot aquire the proper resource
	 * string.
	 * 
	 * @see ResourceBundle#getString(java.lang.String)
	 * @param bundle to load resource string from.
	 * @param str ResourceBundle key for resource string.
	 * @return String from the ResourceBundle.
	 */
	public static String getString(ResourceBundle bundle, String str) {
		try {
			return bundle.getString(str);
		} catch (Throwable t) {
			System.out.println("** Warning ** Resource String [" + str + "] is not available.");
			return str;
		}
	}


	/**
	 * Creates a generally safe file name.
	 * <p>
	 * This method will hopefully prevent silly errors that can occur by
	 * attempting to create a file with illegal characters. This method more or
	 * less takes all the ASCII symbols and converts them to underscores.
	 * <p>
	 * This method more or less is a culmination of what i know to be invalid
	 * and or annoying characters to have in a file name on the systems i know.
	 * <p>
	 * For *NIX users in paticular so extra precautions are made so that the
	 * names of these files will be easier to deal with in shell scripts such
	 * that there aren't many characters that will make them difficult when
	 * dealing within a given shell, and shouldn't have to resorting to alot of
	 * escape sequences.
	 * 
	 * @param fqFilename original filename.
	 * @return String an updated version that should be safe for most systems.
	 */
	/*public static String createSafeFilename(String fqFilename) {
		fqFilename = fqFilename.replace(File.pathSeparatorChar, '_');
		fqFilename = fqFilename.replace(File.separatorChar, '_');
		fqFilename = fqFilename.replace('*', '_');
		fqFilename = fqFilename.replace(' ', '_');
		fqFilename = fqFilename.replace('(', '_');
		fqFilename = fqFilename.replace(')', '_');
		fqFilename = fqFilename.replace('\'', '_');
		fqFilename = fqFilename.replace('\"', '_');
		fqFilename = fqFilename.replace(';', '_');
		fqFilename = fqFilename.replace(':', '_');
		fqFilename = fqFilename.replace('>', '_');
		fqFilename = fqFilename.replace('<', '_');
		fqFilename = fqFilename.replace('[', '_');
		fqFilename = fqFilename.replace(']', '_');
		fqFilename = fqFilename.replace('&', '_');
		fqFilename = fqFilename.replace('#', '_');
		fqFilename = fqFilename.replace('@', '_');
		fqFilename = fqFilename.replace('$', '_');
		fqFilename = fqFilename.replace('%', '_');
		fqFilename = fqFilename.replace('^', '_');
		return fqFilename;
	}*/

	/**
	 * Sets all concievable sizes of JComponent to it's preferred size.
	 * <p>
	 * 
	 * @see JComponent#getPreferredSize()
	 * @param c to lock it's size.
	 */
	/*public static void lockComponentToPerferredSize(JComponent c) {
		Dimension prefSize = c.getPreferredSize();
		c.setPreferredSize(prefSize);
		c.setMaximumSize(prefSize);
		c.setMinimumSize(prefSize);
	}*/

	/**
	 * Creates a KeyStroke from a ResourceBundle string.
	 * <p>
	 * 
	 * @see #getKeyStroke(String)
	 * @param bundle to load the localized text from.
	 * @param str ResourceBundle key.
	 * @return KeyStroke for the resource string can be null if not a valid
	 *         keystroke.
	 */
	/*public static KeyStroke getLocalKeyStroke(ResourceBundle bundle, String str) {
		try {
			return getKeyStroke(bundle.getString(str));
		} catch (Exception e) {
			return null;
		}

	}*/

	/**
	 * Method getString.
	 * 
	 * @param bundle
	 * @param str
	 * @param params
	 * @return String
	 */
	public static String getString(ResourceBundle bundle, String str, String params) {
		return getString(bundle, str, new String[] { params });
	}

	/**
	 * This method is the heart of the getString() methods implemented here.
	 * <p>
	 * This method provided string formating with string parameters. For
	 * instance using the ResourceBundles parameters are identified with the
	 * char '$'. Given that c = '$' and as an example str = "My $0
	 * parameterized $1". If the given String[] params will be super imposed
	 * into the existing String str such that for each index of params 0..n
	 * where n is params length each number correctly identified after each
	 * character c is matched up and the string is replaced. So if the params
	 * parameter contained {"X","Y"} the string returned will be "My X
	 * parameterized Y".
	 * <p>
	 * If the character c is preceded by an escape character \ it will be
	 * considered literal an will not be replaced.
	 * <p>
	 * Also each parameter can be repeted indefinitely as needed, using the
	 * previous parameters and new str of "My $0 parameterized $0" will result
	 * as "My X parameterized X"
	 * 
	 * @param c character to identify parameter locations within the source
	 *            string
	 * @param str source string to parameterize.
	 * @param params parameters to use in string replacement.
	 * @return String the new source string with parameters.
	 */
	public static String formatString(char c, String str, String[] params) {
		StringBuffer buff = new StringBuffer(str);
		String s = buff.toString();
		int end = 0;
		int idx0 = 0;
		int idx1 = 0;
		if ((end = s.lastIndexOf(c)) >= 0 && params != null) {
			while (idx0 <= end && end < s.length() - 1) {
				idx0 = s.indexOf(c, idx1);
				for (idx1 = idx0 + 1; idx1 < s.length(); idx1++)
					if (Character.isDigit(s.charAt(idx1)))
						continue;
					else
						break;
				try {
					int x = -1;
					x = Integer.parseInt(s.substring(idx0 + 1, idx1));
					String parameter = params[x];
					if (parameter == null)
						parameter = "";
					buff.replace(idx0, idx1, parameter);
					s = buff.toString();
					end = s.lastIndexOf(c);
				} catch (NumberFormatException e) {
					continue;
				} catch (ArrayIndexOutOfBoundsException e) {
					try {
						buff.replace(idx0, idx1, "");
						s = buff.toString();
						end = s.lastIndexOf(c);
					} catch (Exception ex) {
						continue;
					}
				}
			}
		}
		return buff.toString();
	}

	/**
	 * Method getString.
	 * 
	 * @param bundle
	 * @param str
	 * @param params
	 * @return String
	 */
	public static String getString(ResourceBundle bundle, String str, String[] params) {
		return formatString('$', getString(bundle, str), params);
	}

	/**
	 * Method HandleException.
	 * 
	 * @param t
	 */
	/*public static void HandleException(Throwable t) {
		HandleException(t, null, Boolean.getBoolean("isql.debug"));
	}*/

	/**
	 * Method HandleException.
	 * 
	 * @param t
	 * @param msg
	 */
	/*public static void HandleException(Throwable t, String msg) {
		HandleException(t, msg, Boolean.getBoolean("isql.debug"));
	}*/

	/**
	 * Method HandleException.
	 * 
	 * @param t
	 * @param msg
	 * @param debug
	 */
	/*public static void HandleException(Throwable t, String msg, boolean debug) {
		HandleException(t, msg, debug, System.err);
	}

	public static void HandleException(Throwable t, String msg, boolean debug, PrintStream err) {
		HandleException(t, msg, debug, new PrintWriter(err, true));
	}*/

	/**
	 * Method HandleException.
	 * 
	 * @param t
	 * @param message
	 * @param debug
	 * @param err
	 */
	/*public static void HandleException(Throwable t, String msg, boolean debug, PrintWriter err) {
		if (err == null)
			err = new PrintWriter(System.err, true);

		if (msg != null)
			err.println(msg);

		if (t != null) {
			String n = t.getClass().getName();
			n = n.substring(n.lastIndexOf('.') + 1);
			String s = "Error";

			if (t instanceof Error)
				s = "Error";
			else if (t instanceof SQLException) {
				SystemConfig sysConfig = SystemConfig.getInstance();
				UserPreferences prefs = sysConfig.getPreferences();
				SQLException se = (SQLException) t;
				String[] p =
					new String[] {
						(se.getMessage() != null ? se.getMessage().trim() : "null"),
						Integer.toString(se.getErrorCode()),
						se.getSQLState() == null ? "null" : se.getSQLState(),
						};

				err.println(getString("SQL_Exception", p));
				int max = prefs.getInt(ConfigConstants.KEY_WARNING_CHAIN_LENGTH);
				se = se.getNextException();
				for (int i = 0; i < max && se != null; i++) {
					p =
						new String[] {
							Integer.toString(i),
							(se.getMessage() != null ? se.getMessage().trim() : "null"),
							Integer.toString(se.getErrorCode()),
							se.getSQLState() == null ? "null" : se.getSQLState(),
							};

					err.println(getString("Chained_SQL_Exception", p));
					se = se.getNextException();
				}

				if (debug)
					t.printStackTrace(err);
				p = null;
				return;

			} else if (t instanceof RuntimeException) {
				s = "Runtime Exception";
			} else if (t instanceof Exception) {
				s = "Exception";
			}

			if (t.getLocalizedMessage() != null)
				err.println(s + " :: " + n + "(" + t.getLocalizedMessage().trim() + ")");
			else
				err.println(s + " :: " + n + "(" + t.getMessage() + ")");

			if (debug)
				t.printStackTrace(err);
		}
	}*/

	/**
	 * Method getCaretCol.
	 * 
	 * @param idx
	 * @param comp
	 * @return int
	 * @throws Exception
	 */
	/*public static int getCaretCol(int idx, JTextComponent comp) throws Exception {
		return Math.abs(Utilities.getRowStart(comp, idx) - idx);
	}*/

	/**
	 * Method getCaretRow.
	 * 
	 * @param idx
	 * @param comp
	 * @return int
	 * @throws Exception
	 */
	/*public static int getCaretRow(int idx, JTextComponent comp) throws Exception {
		return getLineOfOffset(idx, comp.getDocument());
	}

	public static String getCurrentWord(Document doc, int offset) throws BadLocationException {
		String txt = doc.getText(0, doc.getLength());
		// in general in SQL this seperates words and or identifiers ?
		String delims = "\r\t\n ,=<>?{}[]|`~;&+-/*@%";
		int delta = Math.max(0, offset - 1);
		int count = txt.length();

		int min = 0;
		int lastIdx = -1;
		int fromIndex = delta;
		char v[] = txt.toCharArray();
		// this bit is from the lastIndexOf from String.class but we are looking for something more
		for (int i = min + ((fromIndex >= count) ? count - 1 : fromIndex); i >= min; i--) {
			if (delims.indexOf(v[i]) >= 0) {
				lastIdx = i + 1;
				break;
			}
		}

		int idx = Math.max(0, lastIdx);
		return txt.substring(idx, Math.min(offset, doc.getLength())).trim();
	}

	public static void setPreferredGeometry(JFrame frame, String id) {
		Dimension screenSize = tk.getScreenSize();
		Dimension frameSize = frame.getSize();

		if (frameSize.height > screenSize.height)
			frameSize.height = screenSize.height;
		if (frameSize.width > screenSize.width)
			frameSize.width = screenSize.width;

		Dimension d = new Dimension(frameSize);
		Point p = new Point((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
		Rectangle rect = new Rectangle(p, d);

		UserPreferences prefs = SystemConfig.getInstance().getPreferences();
		if (prefs.getBoolean(ConfigConstants.KEY_ENABLE_METRICS)) {

			Rectangle old = prefs.getRect(ConfigConstants.KEY_SESSION_PREFIX.concat(id));
			if ((old.width * old.height) >= 1) {
				rect.setBounds(old);
			}
		}
		frame.setBounds(rect);
	}*/

	/**
	 * Method getClassForPrimative.
	 * 
	 * @param c
	 * @return Class
	 */
	/*public static Class getClassForPrimative(Class c) {
		if (c.isPrimitive()) {
			if (c == boolean.class)
				return Boolean.class;

			if (c == float.class)
				return Float.class;

			if (c == int.class)
				return Integer.class;

			if (c == short.class)
				return Short.class;

			if (c == long.class)
				return Long.class;

			if (c == double.class)
				return Double.class;

			if (c == char.class)
				return Character.class;

			if (c == byte.class)
				return Byte.class;

			if (c == void.class)
				return Void.class;
			else
				return null;
		} else {
			if (c == String.class)
				return String.class;
			else
				return null;
		}

	}

	protected static int getLineOfOffset(int offset, Document doc) throws BadLocationException {

		if (offset < 0 || doc == null) {
			throw new BadLocationException("", -1);
		} else if (offset > doc.getLength()) {
			throw new BadLocationException("", doc.getLength() + 1);
		} else {
			Element map = doc.getDefaultRootElement();
			return map.getElementIndex(offset);
		}
	}*/

	/**
	 * Keystroke parsing code.
	 * <p>
	 * This code is identical to the Java Keystroke parsing code with one small
	 * exception that it will interpet meta as the the menu command mask e.g.
	 * command for Mac OS X, and CTRL for linux and windows.
	 * 
	 * @see KeyStroke#getKeyStroke(java.lang.String)
	 */
	/*protected static KeyStroke getKeyStroke(String s) {
		if (s == null || s.length() == 0) {
			return null;
		}
		StringTokenizer st = new StringTokenizer(s);
		String token;
		int mask = 0;
		boolean released = false;
		boolean typed = false;

		while ((token = st.nextToken()) != null) {
			int tokenMask = 0;

			/* if token matches a modifier keyword update mask and continue */

		/*	for (int i = 0;(tokenMask == 0) && (i < modifierKeywords.length); i++) {
				tokenMask = modifierKeywords[i].getModifierMask(token);
			}

			if (tokenMask != 0) {
				mask |= tokenMask;
				continue;
			}

			if (token.equals("released")) {
				released = true;
				continue;
			}
			if (token.equals("pressed")) {
				continue;
			}
			if (token.equals("typed")) {
				typed = true;
				continue;
			}

			if (typed) {
				if (token.length() != 1) {
					// bogus format, should really throw.
					return null;
				}
				return KeyStroke.getKeyStroke(token.charAt(0));
			}

			/* otherwise token is the keycode name less the "VK_" prefix */

		/*	String keycodeName = "VK_" + token;

			int keycode;
			try {
				keycode = KeyEvent.class.getField(keycodeName).getInt(KeyEvent.class);
			} catch (Exception e) {
				BasicUtilities.HandleException(e, keycodeName);
				return null;
			}

			return KeyStroke.getKeyStroke(keycode, mask, released);
		}
		return null;
	}

	private static class ThrowableOptionPane extends JPanel implements ActionListener {
		private JToggleButton tog = new JToggleButton(loadIconResource("StepForward24"));
		private JConsole txt = new JConsole("");
		private JScrollPane jsp = new JScrollPane(txt);

		public ThrowableOptionPane(Throwable t, String message) {
			super(new BorderLayout());
			localizeTextComponent(txt, null);
			jsp.setVisible(false);
			tog.setText(getString("Error_Details"));
			tog.setSelectedIcon(loadIconResource("Down24"));
			tog.setHorizontalTextPosition(SwingConstants.LEFT);
			tog.addActionListener(this);
			txt.configure(SystemConfig.getInstance().getPreferences());
			PrintStream ps = new PrintStream(txt.toOutputStream(), true);
			jsp.setPreferredSize(new Dimension(320, 240));
			HandleException(t, message, true, ps);
			add(tog, BorderLayout.NORTH);
			add(new JLabel(message), BorderLayout.SOUTH);
		}

		public void actionPerformed(ActionEvent e) {
			jsp.setVisible(!jsp.isVisible());
			Window win = SwingUtilities.getWindowAncestor(this);

			if (!jsp.isVisible()) {
				remove(jsp);
			} else {
				add(jsp, BorderLayout.CENTER);
			}

			validateTree();
			win.pack();
		}

	}

	private static class ModifierKeyword {

		final String keyword;
		final int mask;
		ModifierKeyword(String keyword, int mask) {
			this.keyword = keyword;
			this.mask = mask;
		}

		int getModifierMask(String s) {
			return (s.equals(keyword)) ? mask : 0;
		}
	}

	public static void unlocalizeTextComponent(JTextComponent txt, UndoManager mgr) {
		textPopup.removeJTextComponent(txt);

		if (mgr != null) {
			Keymap map = txt.getKeymap();
			map.removeKeyStrokeBinding(BasicUtilities.createKeyStroke(KeyEvent.VK_Z, BasicUtilities.CMD_MASK));
			map.removeKeyStrokeBinding(BasicUtilities.createKeyStroke(KeyEvent.VK_Y, BasicUtilities.CMD_MASK));
		}

	}*/

	/**
	 * Ensures the editor kit for the given text component is using standard
	 * clipboard accelerators.
	 * <p>
	 * This will also install the {ctrl|command}-[Y-Z] if the given undo
	 * manager is null.
	 * 
	 * @param txt to localize.
	 * @param mgr if undo support is enabled for this txt component.
	 */
	/*public static void localizeTextComponent(JTextComponent txt, UndoManager mgr) {
		ActionMap am = txt.getActionMap();
		Keymap map = txt.getKeymap();
		Action a = null;

		a = am.get(DefaultEditorKit.cutAction);
		map.addActionForKeyStroke(createKeyStroke(KeyEvent.VK_X, CMD_MASK), a);
		a = am.get(DefaultEditorKit.copyAction);
		map.addActionForKeyStroke(createKeyStroke(KeyEvent.VK_C, CMD_MASK), a);
		a = am.get(DefaultEditorKit.pasteAction);
		map.addActionForKeyStroke(createKeyStroke(KeyEvent.VK_V, CMD_MASK), a);
		a = am.get(DefaultEditorKit.selectAllAction);
		map.addActionForKeyStroke(createKeyStroke(KeyEvent.VK_A, CMD_MASK), a);

		textPopup.addJTextComponent(txt, true);

		if (mgr != null) {
			a = new UndoTextAction(mgr);
			map.addActionForKeyStroke(BasicUtilities.createKeyStroke(KeyEvent.VK_Z, BasicUtilities.CMD_MASK), a);
			a = new RedoTextAction(mgr);
			map.addActionForKeyStroke(BasicUtilities.createKeyStroke(KeyEvent.VK_Y, BasicUtilities.CMD_MASK), a);
		}
	}

	public static class UndoTextAction extends AbstractAction {
		private UndoManager um = null;

		public UndoTextAction(UndoManager mgr) {
			um = mgr;
		}*/
		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		/*public void actionPerformed(ActionEvent e) {
			try {
				um.undo();
			} catch (Throwable t) {
				beep();
			}

		}

	}

	public static class RedoTextAction extends AbstractAction {
		private UndoManager um = null;

		public RedoTextAction(UndoManager mgr) {
			um = mgr;
		}*/

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
	/*	public void actionPerformed(ActionEvent e) {
			try {
				um.redo();
			} catch (Throwable t) {
				beep();
			}

		}

	}*/

	/**
	 * Utility method from moving an inputstream to an outputstream.
	 * <p>
	 * Pretty basic stuff here every byte read from in is immediately written
	 * to out.
	 * 
	 * @param in InputStream to read from.
	 * @param out OutputStream to write to.
	 * @throws IOException if error occurs while write
	 */
	/*public static void copyStream(InputStream in, OutputStream out) throws IOException {
		synchronized (in) {
			synchronized (out) {
				byte[] buffer = new byte[256];
				while (true) {
					int bytesRead = in.read(buffer);
					if (bytesRead == -1)
						break;
					out.write(buffer, 0, bytesRead);
				}
			}
		}
	}*/
}