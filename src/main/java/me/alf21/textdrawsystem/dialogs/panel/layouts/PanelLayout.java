package me.alf21.textdrawsystem.dialogs.panel.layouts;

/**
 * Created by Alf21 on 26.02.2016.
 */
public enum PanelLayout {
	TABLE(Table.class),
	LIST(List.class),
	FORM(Form.class),
	FREE_STYLE(FreeStyle.class);

	private Class<?> layoutClass;

	PanelLayout(Class<?> layoutClass) {
		this.layoutClass = layoutClass;
	}

	public Class<?> getLayoutClass() {
		return layoutClass;
	}
}
