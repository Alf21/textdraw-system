package me.alf21.textdrawsystem.dialogs.styles;

import me.alf21.textdrawsystem.dialogs.Dialog;
import me.alf21.textdrawsystem.content.Content;
import me.alf21.textdrawsystem.utils.PlayerTextdraw;
import net.gtaun.shoebill.data.Color;

/**
 * Created by Alf21 on 26.02.2016.
 */
public abstract class DialogStyle {
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

	public void create(Dialog dialog) {
		dialog.setCloseIcon(closeIcon);
		dialog.setContent(new Content(dialog, contentBackground, contentText));
		dialog.setLeftButton(leftButton);
		dialog.setLeftButtonBackground(leftButtonBackground);
		dialog.setPanelBackground(panelBackground);
		dialog.setProcess(process);
		dialog.setRightButton(rightButton);
		dialog.setRightButtonBackground(rightButtonBackground);
		dialog.setTitle(title);
		dialog.setTitleBackground(titleBackground);
		dialog.setHoverColor(new Color(150,0,0,255));
		dialog.setInputColor(new Color(255,255,255,50));
		dialog.setMarkerColor(new Color(150,0,0,50));
	}
}
