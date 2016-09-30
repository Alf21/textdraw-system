package me.alf21.textdrawsystem.dialogs.layouts;

import me.alf21.textdrawsystem.content.Content;
import net.gtaun.shoebill.data.Vector2D;

/**
 * Created by Alf21 on 27.02.2016 in the project 'textdraw-system'.
 */
public class Table implements Layout {

	@Override
	public Vector2D getSlot(Content content, int row, int col) {
		return null;
	}

	@Override
	public float getHeight(int row, int col) {
		return 0;
	}

	@Override
	public float getWidth(int row, int col) {
		return 0;
	}
}
