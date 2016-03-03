package me.alf21.textdrawsystem.content.components;

/**
 * Created by Alf21 on 27.02.2016.
 */
public class Image extends Component {

	Image(String name) {
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
