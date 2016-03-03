package me.alf21.textdrawsystem.dialogs.panel.styles;

import me.alf21.textdrawsystem.dialogs.panel.styles.normal.Normal;

/**
 * Created by Alf21 on 27.02.2016.
 */
public enum PanelStyles {
	NORMAL(Normal.class);

	private Class<?> styleClass;

	PanelStyles(Class<?> panelStyle) {
		this.styleClass = panelStyle;
	}

	public Class<?> getStyleClass() {
		return styleClass;
	}
}
