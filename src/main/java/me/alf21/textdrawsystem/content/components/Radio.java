package me.alf21.textdrawsystem.content.components;

import me.alf21.textdrawsystem.content.Content;

/**
 * Created by Alf21 on 27.02.2016.
 */
public class Radio extends Component {

	protected Radio(Content content, ComponentAlignment componentAlignment, String name) {
		super(content, componentAlignment, name);
	}

	@Override
	public void destroy() {

	}

	@Override
	public boolean isDestroyed() {
		return false;
	}
}
