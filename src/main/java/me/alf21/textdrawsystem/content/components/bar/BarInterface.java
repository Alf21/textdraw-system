package me.alf21.textdrawsystem.content.components.bar;

import me.alf21.textdrawsystem.content.Content;

/**
 * Created by Alf21 on 04.03.2016.
 */
public abstract class BarInterface {

	public void onInit(Content content, Bar bar) {
		bar.setProcess(0);
	}

	public void onProcess(Content content, Bar bar) {

	}
}
