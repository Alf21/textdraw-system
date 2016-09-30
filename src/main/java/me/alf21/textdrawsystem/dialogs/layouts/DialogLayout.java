package me.alf21.textdrawsystem.dialogs.layouts;

/**
 * Created by Alf21 on 26.02.2016 in the project 'textdraw-system'.
 */
public enum DialogLayout {
	TABLE(Table.class),
	LIST(List.class),
	FORM(Form.class),
	FREE_STYLE(FreeStyle.class);

	private Class<?> layoutClass;

	DialogLayout(Class<?> layoutClass) {
		this.layoutClass = layoutClass;
	}

	public Class<?> getLayoutClass() {
		return layoutClass;
	}
}
