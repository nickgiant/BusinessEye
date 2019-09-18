// created 16-10-2009
// from http://www.kidslovepc.com/javatable/java-jcombobox-width-part2.shtml


package com.tool.guicomps;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.plaf.metal.*;
import javax.swing.plaf.basic.*;

class JxComboBoxUI extends MetalComboBoxUI
{
protected ComboPopup createPopup()
{
BasicComboPopup popup = new BasicComboPopup( comboBox )
{

public void show()
{
Dimension popupSize = ((JxComboBox)comboBox).getPopupSize();
popupSize.setSize( popupSize.width,getPopupHeightForRowCount( comboBox.getMaximumRowCount() ) );
Rectangle popupBounds = computePopupBounds( 0,comboBox.getBounds().height, popupSize.width, popupSize.height);
scroller.setMaximumSize( popupBounds.getSize() );
scroller.setPreferredSize( popupBounds.getSize() );
scroller.setMinimumSize( popupBounds.getSize() );
list.invalidate();
int selectedIndex = comboBox.getSelectedIndex();
  if ( selectedIndex == -1 )
  {
    list.clearSelection();
  }
  else
  {
      list.setSelectedIndex( selectedIndex );
  }
  
list.ensureIndexIsVisible( list.getSelectedIndex() );
setLightWeightPopupEnabled( comboBox.isLightWeightPopupEnabled() );

show( comboBox, popupBounds.x, popupBounds.y );
}
};
popup.getAccessibleContext().setAccessibleParent(comboBox);
return popup;
}
}


public class JxComboBox extends JComboBox {
protected int popupWidth;

public JxComboBox(ComboBoxModel aModel) {
super(aModel);
setUI(new JxComboBoxUI());
popupWidth = 0;
}

public JxComboBox(final Object[] items) {
super(items);
setUI(new JxComboBoxUI());
popupWidth = 0;
}

public JxComboBox(Vector items) {
super(items);
setUI(new JxComboBoxUI());
popupWidth = 0;
}

public JxComboBox()
{
super();
setUI(new JxComboBoxUI());
popupWidth = 0;
}

public void setPopupWidth(int width) {
popupWidth = width;
}

public void setSelectedIndex(int sel)
{
	super.setSelectedIndex(sel);
    this.setToolTipText(this.getSelectedItem().toString());	
}

public void setSelectedItem(Object sel)
{
	super.setSelectedItem(sel);
    this.setToolTipText(this.getSelectedItem().toString());	
}

public int getSelectedIndex()
{
	return super.getSelectedIndex();
}

public Dimension getPopupSize() {
Dimension size = getSize();
if (popupWidth < 1) popupWidth = size.width;
return new Dimension(popupWidth, size.height);
}
}