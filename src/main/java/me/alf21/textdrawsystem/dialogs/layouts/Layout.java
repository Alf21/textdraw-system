package me.alf21.textdrawsystem.dialogs.layouts;

import me.alf21.textdrawsystem.content.Content;
import net.gtaun.shoebill.data.Vector2D;

/**
 * Created by Alf21 on 27.02.2016.
 */
public interface Layout {

	/**
	 * @param content of panel
	 * @param row row of slot
	 * @param col column of slot
	 * @return position of the slot
	 */
	Vector2D getSlot(Content content, int row, int col);

	/**
	 * @return height of each slot
	 */
	float getHeight(int row, int col);

	/**
	 * @return width of each slot
	 */
	float getWidth(int row, int col);

}
