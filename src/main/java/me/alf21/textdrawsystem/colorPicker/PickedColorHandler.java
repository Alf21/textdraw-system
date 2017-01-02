package me.alf21.textdrawsystem.colorPicker;

import net.gtaun.shoebill.data.Color;

/**
 * Created by Alf21 on 17.07.2016 in the project 'textdraw-system'.
 */
@FunctionalInterface
public interface PickedColorHandler {
	void handle(ColorPicker colorPicker, Color color);
}
