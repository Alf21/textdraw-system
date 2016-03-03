package me.alf21.textdrawsystem.dialogs.interfaces;

import me.alf21.textdrawsystem.content.pages.Page;
import me.alf21.textdrawsystem.dialogs.panel.Panel;

/**
 * Created by Alf21 on 02.03.2016.
 */
public abstract class PanelInterface {

	private Panel panel;

	public PanelInterface() { }

	public void onFinish() {

	}

	public void onClose() {
		getPanel().hide();
		getPanel().destroy();
	}

	public void setPanel(Panel panel) {
		this.panel = panel;
	}

	public Panel getPanel() {
		return panel;
	}
}
