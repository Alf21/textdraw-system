package me.alf21.textdrawsystem.panelDialog;

import me.alf21.textdrawsystem.content.components.ComponentDataCollection;

/**
 * Created by Alf21 on 12.06.2016 in the project 'textdraw-system'.
 */
@FunctionalInterface
public interface PanelDialogClickOkHandler {
	void handle(AbstractPanelDialog panelDialog, ComponentDataCollection componentDataCollection);
}
