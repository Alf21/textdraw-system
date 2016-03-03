package me.alf21.textdrawsystem.dialogs.panel.layouts;

import me.alf21.textdrawsystem.calculations.Calculation;
import me.alf21.textdrawsystem.content.Content;
import me.alf21.textdrawsystem.utils.PlayerTextdraw;
import net.gtaun.shoebill.constant.TextDrawAlign;
import net.gtaun.shoebill.data.Vector2D;

/**
 * Created by Alf21 on 27.02.2016.
 */
public class Form implements Layout {

	public Vector2D getSlot(Content content, int row, int col, int maxRows, int maxCols) {
		PlayerTextdraw contentBackground = content.getContentBackground();
		float iXMax, iXMin, iYMax, iYMin;
		Vector2D position = contentBackground.getPosition().clone();
		float startPositionX = position.getX(), startPositionY = position.getY();
		float width = Calculation.getWidth(contentBackground), height = Calculation.getHeight(contentBackground);
		if(contentBackground.getAlignment() == TextDrawAlign.CENTER)
			startPositionX -= width / 2.0f;

		iXMax = width / ((float) maxCols / 2.0f);
		iXMin = iXMax / 2.0f;
		iYMax = height / ((float) maxRows / 2.0f);
		iYMin = iYMax / 2.0f;

		return new Vector2D(
				startPositionX + (iXMax - iXMin) / 2.0f + (iXMax - iXMin) * ((float) col - 1.0f),
				startPositionY + (iYMax - iYMin) / 2.0f + (iYMax - iYMin) * ((float) row - 1.0f)
		);
	}

	@Override
	public Vector2D getSlot(Content content, int row, int col) {
		return getSlot(content, row, col, 3, 3);
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
