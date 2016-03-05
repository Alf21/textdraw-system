package me.alf21.textdrawsystem.dialogs;

/**
 * Created by Alf21 on 02.03.2016.
 */
public abstract class DialogInterface {

	public DialogInterface() { }

	public void onFinish(Dialog dialog) {

	}

	public void onClose(Dialog dialog) {
		dialog.hide();
		dialog.destroy();
	}
}
