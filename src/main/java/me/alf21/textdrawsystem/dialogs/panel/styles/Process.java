package me.alf21.textdrawsystem.dialogs.panel.styles;

import me.alf21.textdrawsystem.dialogs.panel.Panel;
import net.gtaun.shoebill.object.Destroyable;

/**
 * Created by Alf21 on 26.02.2016.
 */
public abstract class Process implements Destroyable {

	private Panel panel;

	public Process(Panel panel) {
		this.panel = panel;
	}

	public void start() {}

	public void stop() {}

	public void hide() {}

	public void show() {}

	public void process() {}

	public void setProcess(double process) {}

	public void setMaxProcess(double maxProcess) {}

	public double getProcess() { return 0.0; }

	public double getMaxProcess() { return 0.0; }

	@Override
	public void destroy() {

	}

	@Override
	public boolean isDestroyed() {
		return true;
	}

	public Panel getPanel() {
		return panel;
	}
}
