package me.alf21.textdrawsystem.dialogs;

/**
 * Created by Alf21 on 02.03.2016 in the project 'textdraw-system'.
 */
public interface DialogInterface {

	default void onFinish(Dialog dialog) {
		dialog.getContent().submit();
	}

	default void onClose(Dialog dialog) {
		dialog.hide();
		dialog.destroy();
	}
}
