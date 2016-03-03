package me.alf21.textdrawsystem.content.components;

/**
 * Created by Alf21 on 27.02.2016.
 */
public class Switcher extends Component {

	Switcher(String name) {
		super(name);
	}

	@Override
	public void destroy() {

	}

	@Override
	public boolean isDestroyed() {
		return false;
	}
}
