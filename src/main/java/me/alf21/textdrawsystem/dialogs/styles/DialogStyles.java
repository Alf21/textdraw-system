package me.alf21.textdrawsystem.dialogs.styles;

<<<<<<< HEAD
import me.alf21.textdrawsystem.dialogs.styles.light.Light;
=======
>>>>>>> bcb4a165b45f56cd0365363c3e448c63d534149e
import me.alf21.textdrawsystem.dialogs.styles.normal.Normal;

/**
 * Created by Alf21 on 27.02.2016 in the project 'textdraw-system'.
 */
public enum DialogStyles {
<<<<<<< HEAD
	NORMAL(Normal.class),
	LIGHT(Light.class);
=======
	NORMAL(Normal.class);
>>>>>>> bcb4a165b45f56cd0365363c3e448c63d534149e

	private Class<?> dialogStyle;

	DialogStyles(Class<?> dialogStyle) {
		this.dialogStyle = dialogStyle;
	}

	public Class<?> getStyleClass() {
		return dialogStyle;
	}
}
