package me.alf21.textdrawsystem.dialogs.styles;

import me.alf21.textdrawsystem.dialogs.styles.light.Light;
import me.alf21.textdrawsystem.dialogs.styles.normal.Normal;

/**
 * Created by Alf21 on 27.02.2016 in the project 'textdraw-system'.
 */
public enum DialogStyles {
	NORMAL(Normal.class),
	LIGHT(Light.class);

	private Class<?> dialogStyle;

	DialogStyles(Class<?> dialogStyle) {
		this.dialogStyle = dialogStyle;
	}

	public Class<?> getStyleClass() {
		return dialogStyle;
	}
}
