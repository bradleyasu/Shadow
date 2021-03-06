package com.hexotic.shadow.components.panels.footer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JPanel;

import com.hexotic.lib.exceptions.ResourceException;
import com.hexotic.lib.resource.Resources;
import com.hexotic.shadow.components.frames.FlagConfigurationFrame;
import com.hexotic.shadow.configurations.Flags;
import com.hexotic.shadow.constants.Constants;
import com.hexotic.shadow.constants.Theme;

public class FooterBar extends JPanel{

	// Static counter names
	
	private FooterMenuItem primaryMenuItem;
	private FilterBox filter;
	
	// Line counters - Key being the counter name and value being the counter control
	private Map<String, FooterCounter> counters;
	
	
	private FlagConfigurationFrame flagConfig;
	
	private String selectedLogId = "";
	
	public FooterBar() {
		this.setPreferredSize(new Dimension(Constants.FOOTER_SIZE, Constants.FOOTER_SIZE));
		this.setBackground(Theme.FOOTER_BACKGROUND);
		counters = new TreeMap<String, FooterCounter>();
		
		this.setLayout(new BorderLayout());
		try {
			primaryMenuItem = new FooterMenuItem(Resources.getInstance().getImage("menu.png"));
			this.add(primaryMenuItem, BorderLayout.WEST);
		} catch (ResourceException e1) {
			e1.printStackTrace();
		}
		
		setupCounters();
		
		filter = new FilterBox();
		this.add(filter, BorderLayout.EAST);

	}
	
	
	public FilterBox getFilterBox() {
		return filter;
	}
	
	private void setupCounters() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0,0));
		panel.setBackground(Theme.FOOTER_BACKGROUND);
		counters.put(Flags.COUNTER_SUCCESS, new FooterCounter(Theme.SUCCESS_COLOR, 0, Flags.COUNTER_SUCCESS));
		counters.put(Flags.COUNTER_INFO, new FooterCounter(Theme.INFO_COLOR, 0, Flags.COUNTER_INFO));
		counters.put(Flags.COUNTER_WARNING, new FooterCounter(Theme.WARNING_COLOR, 0, Flags.COUNTER_WARNING));
		counters.put(Flags.COUNTER_ERROR, new FooterCounter(Theme.ERROR_COLOR, 0, Flags.COUNTER_ERROR));
		counters.put(Flags.COUNTER_BOOKMARK, new FooterCounter(Theme.LINE_BOOKMARK_COLOR, 0, Flags.COUNTER_BOOKMARK));
		
		for(FooterCounter counter : counters.values()){
			panel.add(counter);
			counter.addFooterCounterListener(new FooterCounterListener(){
				@Override
				public void counterActivated(int click, FooterCounter source) {
					// Don't trigger the properties for bookmark as these can't be configured
					if(!source.getFlagType().equals(Flags.COUNTER_BOOKMARK)){
						
						if(click == FooterCounter.RIGHT_CLICK){
							flagConfig.setFlagToConfigure(selectedLogId, source.getFlagType());
							flagConfig.setVisible(true);
						}
						
					}
				}
			});
		}
		
		this.add(panel, BorderLayout.CENTER);
	}
	
	public void reset() {
		for(FooterCounter counter : counters.values()){
			counter.setCount(0);
		}
	}
	
	public Map<String, FooterCounter> getCounters() {
		return counters;
	}
	
	public FooterMenuItem getPrimaryMenuItem() {
		return primaryMenuItem;
	}
	
	public void setFlagConfigFrame(FlagConfigurationFrame configFrame){
		flagConfig = configFrame;
	}
	
	public void setSelectedLog(String logId){
		this.selectedLogId = logId;
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}
}
