// created 09-11-2009
package com.tool.guicomps;

import javax.swing.*;
import java.awt.*;

public class JPanelDecorated extends JxPanel////JPanelCurvedGradient // JxPanel
{
   private JPanelCurvedGradient panelAllOnIt ;
	public JPanelDecorated()
	{
		super();
		Color blue= new Color(129, 169, 226);
         Color lightBlue= new Color(196,218,246);//220,235,253);//148, 215, 254);
        panelAllOnIt = new JPanelCurvedGradient(blue,lightBlue,0,0);
        this.add(panelAllOnIt);
	}

	public JPanelDecorated(LayoutManager lm)
	{
		super(lm);
		
		Color blue= new Color(129, 169, 226);
         Color lightBlue= new Color(196,218,246);//220,235,253);//148, 215, 254);
        panelAllOnIt = new JPanelCurvedGradient(blue,lightBlue,0,0);
        this.add(panelAllOnIt);
	}	
}
