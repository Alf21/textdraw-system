package me.alf21.textdrawsystem.dialogs.styles;

import me.alf21.textdrawsystem.dialogs.Dialog;
import net.gtaun.shoebill.object.Destroyable;

/**
 * Created by Alf21 on 26.02.2016.
 */
public abstract class Process implements Destroyable {

	private Dialog dialog;

	public Process(Dialog dialog) {
		this.dialog = dialog;
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

	public Dialog getDialog() {
		return dialog;
	}
}