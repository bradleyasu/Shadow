package com.hexotic.lyra;

import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import com.hexotic.lib.util.WinOps;
import com.hexotic.lyra.components.frames.LyraFrame;
import com.hexotic.lyra.constants.Constants;

public class Lyra extends JFrame{

	private JDesktopPane rootPane;
	private JInternalFrame mainFrame;
	
	public Lyra() {
		this.setTitle(Constants.APP_NAME+" "+Constants.VERSION+" - "+Constants.APP_COMPANY);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
		
		rootPane = new JDesktopPane();
		this.setContentPane(rootPane);
		
		/* Build main view */
		mainFrame = new LyraFrame();
		rootPane.add(mainFrame); 
		mainFrame.setVisible(true);
		
		
		this.addComponentListener(new ComponentListener() {
			public void componentResized(ComponentEvent e) {
				
				int targetWidth = e.getComponent().getWidth() - Constants.X_OFFSET;
				int targetHeight = e.getComponent().getHeight() - Constants.Y_OFFSET;
				mainFrame.setSize(targetWidth, targetHeight);
				mainFrame.setLocation(0,0);
				
			}

			@Override
			public void componentHidden(ComponentEvent e) {
			}

			@Override
			public void componentMoved(ComponentEvent e) {
			}

			@Override
			public void componentShown(ComponentEvent e) {
			}
		});
		
		pack();
		this.setVisible(true);
		WinOps.centreWindow(this);
	}
	
	
	
	public static void main(String[] args) {
		new Lyra();
	}
}
