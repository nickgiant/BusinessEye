// created 05-02-2010


//http://forums.sun.com/thread.jspa?threadID=5388152


package com.tool.guicomps;


import javax.swing.*;
import java.awt.*;
public class JSlidingPanel extends JxPanel{
	private static final long serialVersionUID = 1L;
	private JComponent component1, component2;
	private Dimension dim1, dim2;	
	private int delay = 10;
	private int rate  = 5;//20
	private boolean shown;
	private boolean showing;
	private boolean hiding;	
	private GridBagLayout grid;
	private GridBagConstraints c;	
	private boolean newComponent;
 
	public static void main(String[] a){
		final JSlidingPanel s = new JSlidingPanel();
		
		JLabel label = new JLabel("Put Mouse HERE");
			label.addMouseListener(new java.awt.event.MouseAdapter(){
				public void mouseEntered(java.awt.event.MouseEvent e){
					s.showComponent();
				}public void mouseExited(java.awt.event.MouseEvent e){
					s.hideComponent();
				}
			});
		JPanel p1 = new JPanel();
			p1.add(label);
			p1.setOpaque(true);
			p1.setBackground(Color.red);
			
		JPanel p2 = new JPanel();
			p2.setOpaque(true);
			p2.setBackground(Color.green);
			p2.setLayout(new BoxLayout(p2, BoxLayout.Y_AXIS));
				
			p2.add(new JLabel("<html><h1>My HTML Label</h1><p>This component is a JLabel inside a JPanel.</p>"));			

				s.setComponent1(p1);
				s.setComponent2(p2);
			//	s.getComponent2().setSize(new Dimension(100, 300));
				
			JFrame f=new JFrame("");
				f.setContentPane(s);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.setSize(650,500);
		        f.setVisible(true);
	}//*/
	public JSlidingPanel(){
		super();
		
		newComponent=false;
		grid = new GridBagLayout();
		c = new GridBagConstraints();
			c.fill=GridBagConstraints.HORIZONTAL;
			c.anchor=GridBagConstraints.NORTH;
			c.weightx=1;
			c.weighty=0;
			
		setLayout(grid);
	}	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
 
		if(newComponent && component2 != null){
			try{
				dim1 = new Dimension(component1.getSize().width, component1.getSize().height);
				dim2 = new Dimension(component2.getSize().width, component2.getSize().height);
				
				realizeDimensions();
			}catch(Exception ex){JOptionPane.showMessageDialog(null, "Component1 not set");}
			newComponent=false;
		}
	}	
	public int getDelay() {
		return delay;
	}
	public void setDelay(int delay) {
		this.delay = delay;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public synchronized boolean isComponentShown(){
		return shown;
	}
	public synchronized boolean isComponentHidden(){
		return !shown;
	}
	public synchronized boolean isComponentShowing() {
		return showing;
	}
	public synchronized boolean isComponentHiding() {
		return hiding;
	}
	public synchronized JComponent getComponent1() {
		return component1;
	}
	public synchronized void setComponent1(JComponent panel) {
		if(panel==null){
			panel=new JPanel();
			panel.setOpaque(false);
		}
		component1 = panel;
 
		c.gridx=0;
		c.gridy=0;
		c.gridwidth=1;
		c.gridheight=1;
		c.fill=GridBagConstraints.HORIZONTAL;
		
		grid.setConstraints(component1, c);
		add(component1, 0);
	}
	public synchronized JComponent getComponent2() {
		return component2;
	}
	public synchronized void setComponent2(JComponent panel) {
		if(panel==null){
			panel=new JPanel();
			panel.setName("null");
			panel.setOpaque(false);
		}
		if(component1 == null){
			setComponent1(null);
		}if(component2 != null){
			this.remove(component2);
		}
		
		newComponent=true;
		component2 = panel;
		
			c.gridx=0;
			c.gridy=1;
			c.gridwidth=1;
			c.gridheight=1;
			c.fill=GridBagConstraints.HORIZONTAL;
				
			grid.setConstraints(component2, c);
			add(component2, 1);
			
			updateUI();
	}
	public synchronized void hideComponent(){
		if(isComponentShowing() || isComponentHiding() || getComponent2()==null)return;
		
		Thread t1 = new Thread(){
			public void run(){
				hiding=true;
				int counter = dim2.height-rate;
				while(component2.getSize().height > 0){
					if( counter < 0 ){
						int extra = counter;
						int exact_amount =  (counter - extra) ;
						counter = exact_amount;
					}
					
					component2.setPreferredSize(new Dimension(dim2.width, counter));
					component2.setMinimumSize(new Dimension(dim2.width, counter));
					component2.setMaximumSize(new Dimension(dim2.width, counter));
					component2.setSize(new Dimension(dim2.width, counter));
					
					setPreferredSize(new Dimension(dim1.width, dim1.height+component2.getSize().height));
					setMinimumSize(new Dimension(dim1.width, dim1.height+component2.getSize().height));
					setMaximumSize(new Dimension(dim1.width, dim1.height+component2.getSize().height));
					setSize(new Dimension(dim1.width, dim1.height+component2.getSize().height));
					
					component2.updateUI();
					
					try{
						Thread.sleep(delay);
					}catch(Exception ex){}
					
					counter -= rate;
				}
				hiding=false;
				shown=false;
			}
		};t1.start();
	}
	
	// requires
	public synchronized void showComponent()
	{

	    delay=10;
    	rate  = 5;
		
		if(isComponentShowing() || isComponentHiding() || getComponent2()==null || dim2==null)return;
		
		Thread t1 = new Thread(){
			public void run(){
				showing=true;
				int counter = rate;
				while(component2.getSize().height < dim2.height){
					if( counter > dim2.height ){
						int extra =  (counter-dim2.height);
						int exact_amount =  counter-extra;
						counter = exact_amount;
					}
					
					component2.setPreferredSize(new Dimension(dim2.width, counter));
					component2.setMinimumSize(new Dimension(dim2.width, counter));
					component2.setMaximumSize(new Dimension(dim2.width, counter));
					component2.setSize(new Dimension(dim2.width, counter));
					
					setPreferredSize(new Dimension(dim1.width, dim1.height+counter));
					setMinimumSize(new Dimension(dim1.width, dim1.height+counter));
					setMaximumSize(new Dimension(dim1.width, dim1.height+counter));
					setSize(new Dimension(dim1.width, dim1.height+counter));
					
					component2.updateUI();
					
					try{
						Thread.sleep(delay);
					}catch(Exception ex){}
					
					counter += rate;
				}
				showing=false;
				shown = true;
			}
		};t1.start();
	}

    // same as showComponent
	public synchronized void showComponentWhenGuiLoaded()
    {
	    delay=3;
    	rate  = 5;
    	
		if(isComponentShowing() || isComponentHiding() || getComponent2()==null || dim2==null)return;
		
		Thread t1 = new Thread(){
			public void run(){
				showing=true;
				int counter = rate;
				while(component2.getSize().height < dim2.height){
					if( counter > dim2.height ){
						int extra =  (counter-dim2.height);
						int exact_amount =  counter-extra;
						counter = exact_amount;
					}
					
					component2.setPreferredSize(new Dimension(dim2.width, counter));
					component2.setMinimumSize(new Dimension(dim2.width, counter));
					component2.setMaximumSize(new Dimension(dim2.width, counter));
					component2.setSize(new Dimension(dim2.width, counter));
					
					setPreferredSize(new Dimension(dim1.width, dim1.height+counter));
					setMinimumSize(new Dimension(dim1.width, dim1.height+counter));
					setMaximumSize(new Dimension(dim1.width, dim1.height+counter));
					setSize(new Dimension(dim1.width, dim1.height+counter));
					
					component2.updateUI();
					
					try{
						Thread.sleep(delay);
					}catch(Exception ex){}
					
					counter += rate;
				}
				showing=false;
				shown = true;
			}
		};t1.start();
	}
	
	private void realizeDimensions(){
		component2.setPreferredSize(new Dimension(dim2.width,0));
		component2.setMinimumSize(new Dimension(dim2.width,0));
		component2.setMaximumSize(new Dimension(dim2.width,0));
		component2.setSize(new Dimension(dim2.width,0));
		
		setPreferredSize(dim1);
		setMinimumSize(dim1);
		setMaximumSize(dim1);
		setSize(dim1);
 
		updateUI();
	}
	
}