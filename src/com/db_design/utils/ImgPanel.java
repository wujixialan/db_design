package com.db_design.utils;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImgPanel extends JPanel {
	private ImageIcon icon = null;
	
	public ImgPanel() {
		
	}
	public ImgPanel(String path) {
		this.icon = new ImageIcon(path);
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(this.icon.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
	}
	
}
