package me.alf21.textdrawsystem.dialogs.panel.styles;

import me.alf21.textdrawsystem.dialogs.panel.Panel;
import me.alf21.textdrawsystem.content.Content;
import me.alf21.textdrawsystem.utils.PlayerTextdraw;
import net.gtaun.shoebill.data.Color;

/**
 * Created by Alf21 on 26.02.2016.
 */
public abstract class PanelStyle {
	PlayerTextdraw panelBackground = null;
	PlayerTextdraw title = null;
	PlayerTextdraw titleBackground = null;
	PlayerTextdraw leftButtonBackground = null;
	PlayerTextdraw rightButtonBackground = null;
	PlayerTextdraw leftButton = null;
	PlayerTextdraw rightButton = null;
	PlayerTextdraw contentBackground = null;
	PlayerTextdraw contentText = null;
	PlayerTextdraw closeIcon = null;
	Process process = null;

	public void create(Panel panel) {
		panel.setCloseIcon(closeIcon);
		panel.setContent(new Content(panel, contentBackground, contentText));
		panel.setLeftButton(leftButton);
		panel.setLeftButtonBackground(leftButtonBackground);
		panel.setPanelBackground(panelBackground);
		panel.setProcess(process);
		panel.setRightButton(rightButton);
		panel.setRightButtonBackground(rightButtonBackground);
		panel.setTitle(title);
		panel.setTitleBackground(titleBackground);
		panel.setHoverColor(new Color(150,0,0,255));
		panel.setInputColor(new Color(255,255,255,50));
		panel.setMarkerColor(new Color(150,0,0,50));
	}
}
