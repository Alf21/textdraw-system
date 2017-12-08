package me.alf21.textdrawsystem.dialogs.styles;

import me.alf21.textdrawsystem.dialogs.Dialog;
import net.gtaun.shoebill.entities.Destroyable;

/**
 * Created by Alf21 on 26.02.2016 in the project 'textdraw-system'.
 */
public abstract class Process implements Destroyable {

	private Dialog dialog;
	private boolean disabled;

	public Process(Dialog dialog) {
		this.dialog = dialog;
	}

	public void start() {}

	public void stop() {}

	public void hide() {}

	public void show() {}

	public void recreate() {}

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

	public boolean isDisabled() {
		return disabled;
	}

	public void disabled() {
		disabled = true;
	}

	public void enable() {
		disabled = false;
	}
}
