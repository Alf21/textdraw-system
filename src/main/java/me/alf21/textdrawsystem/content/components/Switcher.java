package me.alf21.textdrawsystem.content.components;

import me.alf21.textdrawsystem.container.Container;

/**
 * Created by Alf21 on 27.02.2016 in the project 'textdraw-system'.
 */
public class Switcher extends Component {

	protected Switcher(Container container, ComponentAlignment componentAlignment, String name) {
		super(container, componentAlignment, name);
	}

	@Override
	public void destroy() {

	}

	@Override
	public boolean isDestroyed() {
		return false;
	}
}
