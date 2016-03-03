package me.alf21.textdrawsystem.calculations;

import me.alf21.textdrawsystem.utils.PlayerTextdraw;
import net.gtaun.shoebill.constant.TextDrawAlign;
import net.gtaun.shoebill.data.Vector2D;

/**
 * Created by Alf21 on 26.02.2016.
 */
public class Calculation {

	public static Vector2D getWidth(PlayerTextdraw playerTextdraw, double maxProcess, double process) {
		float widthX, widthY;
		if(playerTextdraw.getAlignment() == TextDrawAlign.CENTER) {
			widthY = playerTextdraw.getTextSize().getY();
			widthY /= maxProcess;
			widthY *= process;
			widthX = playerTextdraw.getTextSize().getX();
		}
		else {
			widthX = playerTextdraw.getTextSize().getX();
			widthX /= maxProcess;
			widthX *= process;
			widthY = playerTextdraw.getTextSize().getY();
		}
		return new Vector2D(widthX, widthY);
	}

	public static float getWidth(PlayerTextdraw playerTextdraw) {
		float width;
		if(playerTextdraw.getAlignment() == TextDrawAlign.CENTER) {
			width = playerTextdraw.getTextSize().getY();
		}
		else {
			width = Math.abs(playerTextdraw.getPosition().getX() - playerTextdraw.getTextSize().getX());
		}
		return width;
	}

	public static float getHeight(PlayerTextdraw playerTextdraw) {
	//	return (playerTextdraw.getLetterSize().getY()*9.225f); //TODO what if getY() smaller than or equals 1
		return (playerTextdraw.getLetterSize().getY()*8.225f);
		//TODO Count Lines
		//return height;
	}

}
