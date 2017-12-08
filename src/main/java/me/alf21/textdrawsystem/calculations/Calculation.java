package me.alf21.textdrawsystem.calculations;

import me.alf21.textdrawsystem.utils.PlayerTextdraw;
import me.alf21.textdrawsystem.utils.PlayersTextdraw;
import net.gtaun.shoebill.constant.TextDrawAlign;
import net.gtaun.shoebill.constant.TextDrawFont;
import net.gtaun.shoebill.data.Color;
import net.gtaun.shoebill.data.Vector2D;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Alf21 on 26.02.2016 in the project 'textdraw-system'.
 */
public class Calculation {

	// TODO fix heightToLetterHeight and vv Calculation
	public final static float LETTERHEIGHTVALUE = 8.23f; //8.63 //to have the correct y-coord position, use /10 (but incorrect samp resolution -> =8.23 && y-coord + 10)

	public static float letterHeightToHeight(float letterHeight) {
		return letterHeight * LETTERHEIGHTVALUE;
	}

	public static float heightToLetterHeight(float height) {
		return height / LETTERHEIGHTVALUE;
	}

	private static String[] getLines(PlayerTextdraw playerTextdraw) {
		String text = playerTextdraw.getText();
		Pattern pattern = Pattern.compile("(?)~n~$");
		Matcher matcher = pattern.matcher(text);
		while (matcher.find()) {
			text = text.replaceAll("(?)~n~$", "").trim();
			matcher = pattern.matcher(text);
		}
		return text.split("~n~");
		//TODO not only get lines from ~n~ but also from the maxWidth and height or height / letterSize.Y ?
	}

	public static Color getBoxColor(Color minColor, Color maxColor, float value, float maxValue) {
		float factor = value / maxValue;
		int	    r =	minColor.getR() + Math.round((maxColor.getR() - minColor.getR()) * factor),
				g =	minColor.getG() + Math.round((maxColor.getG() - minColor.getG()) * factor),
				b =	minColor.getB() + Math.round((maxColor.getB() - minColor.getB()) * factor),
				a =	minColor.getA() + Math.round((maxColor.getA() - minColor.getA()) * factor);
		if(r > 255)	r = 255;	if(r < 0) r = 0;
		if(g > 255)	g = 255;	if(g < 0) g = 0;
		if(b > 255)	b = 255;	if(b < 0) b = 0;
		if(a > 255)	a = 255;	if(a < 0) a = 0;
		return new Color(r,g,b,a);
	}

	public static Vector2D getWidth(PlayerTextdraw playerTextdraw, double maxProcess, double process) {
		float widthX, widthY;
		if(playerTextdraw.getAlignment() == TextDrawAlign.CENTER) {
			widthY = playerTextdraw.getTextSize().y;
			widthY /= maxProcess;
			widthY *= process;
			widthX = playerTextdraw.getTextSize().x;
		}
		else {
			widthX = playerTextdraw.getTextSize().x;
			widthX /= maxProcess;
			widthX *= process;
			widthY = playerTextdraw.getTextSize().y;
		}
		return new Vector2D(widthX, widthY);
	}

	public static float getWidth(PlayerTextdraw playerTextdraw) {
		float width;
		switch (playerTextdraw.getAlignment()) {
			case LEFT:
				width = playerTextdraw.getTextSize().x - playerTextdraw.getPosition().x - 4f;
				break;
			case CENTER:
				width = playerTextdraw.getTextSize().y;
				break;
			case RIGHT:
				width = Math.abs(playerTextdraw.getPosition().x - 4f - playerTextdraw.getTextSize().x); //TODO check
				break;
			default:
				width = playerTextdraw.getTextSize().x;
				break;
		}
		return width;
	}

	public static float getBoxWidth(PlayerTextdraw playerTextdraw) {
		float width = 0f, tmp = 0f;
		TextDrawFont textDrawFont = playerTextdraw.getFont();

		String[] parts = getLines(playerTextdraw);
		for (String part : parts) {
			part = part.replaceAll("~.~", "").trim();
			for (int i = 0; i < part.toCharArray().length; i++) {
				if (playerTextdraw.isProportional()) {
					if (i+1 != part.toCharArray().length)
						tmp += getLetterWidth(textDrawFont, part.toCharArray()[i]) * (playerTextdraw.getLetterSize().x + 0.5f);
					else
						tmp += (getLetterWidth(textDrawFont, part.toCharArray()[i]) + 2.0f) * (playerTextdraw.getLetterSize().x + 0.5f);
				}
			}
			if (tmp > width)
				width = tmp;
			tmp = 0f;
		}

		return width;
	}

	public static float getBoxWidth(PlayersTextdraw playersTextdraw) {
		return getBoxWidth(PlayerTextdraw.create(playersTextdraw, playersTextdraw.getPlayers().get(0)));
	}

	public static float getBoxWidth(ArrayList<PlayerTextdraw> playerTextdraws) {
		float width = 0f;
		//TODO sort from curPosition - (beforePosition + height) //TODO auch die zwischenspalten mitberechnen ! falls es auch unter dem anderen txd liegt
		for(PlayerTextdraw playerTextdraw : playerTextdraws) {
			width += getBoxWidth(playerTextdraw);
		}
		return width;
	}

	public static float getBoxWidth(PlayerTextdraw playerTextdraw, float maxHeight) {
		float width = 0f;
		//TODO calc here the max width based on the max height
		return width;
	}

	public static float getBoxHeight(PlayerTextdraw playerTextdraw) {
		return letterHeightToHeight(playerTextdraw.getLetterSize().y) * (float) getLines(playerTextdraw).length;
	}

	public static float getBoxHeight(PlayersTextdraw playersTextdraw) {
		return getBoxHeight(PlayerTextdraw.create(playersTextdraw, playersTextdraw.getPlayers().get(0)));
	}

	public static float getBoxHeight(ArrayList<PlayerTextdraw> playerTextdraws) {
		float minHeight = playerTextdraws.get(0).getPosition().y, maxHeight = getBoxHeight(playerTextdraws.get(0));
		for (PlayerTextdraw playerTextdraw : playerTextdraws) {
			float   height = getBoxHeight(playerTextdraw),
					y = playerTextdraw.getPosition().y;
			if (minHeight > y)
				minHeight = y;
			if (maxHeight < height)
				maxHeight = height;
		}
		return maxHeight - minHeight;
	}

	public static float getBoxHeight(PlayerTextdraw playerTextdraw, float maxWidth) {
		float height = 0f;
		//TODO calc here the max height based on the max width
		return height;
	}

	//TODO
	//getWidth from Letters
	//getLinesAmount from getWidth
	//getHeight from getLinesAmount

	public static float getLetterWidth(TextDrawFont font, char letter) {
		switch (font) { //TODO other fonts !
			case FONT2:
				switch (letter) {
					case ' ':
						return 8.775f;

					case '!':
						return 1.775f;

					case '"':
						return 5.775f;

					case '#':
						return 10.775f;

					case '$':
						return 6.775f;

					case '%':
						return 13.775f;

					case '&':
						return 7.775f;

					case '\'':
						return 2.775f;

					case '(':
						return 2.775f;

					case ')':
						return 2.775f;

					case '+':
						return 5.775f;

					case ',':
						return 2.775f;

					case '-':
						return 4.775f;

					case '.':
						return 4.775f;

					case '/':
						return 4.775f;

					case '0':
						return 8.775f;

					case '1':
						return 7.775f;

					case '2':
						return 7.775f;

					case '3':
						return 7.775f;

					case '4':
						return 7.775f;

					case '5':
						return 7.775f;

					case '6':
						return 7.775f;

					case '7':
						return 7.775f;

					case '8':
						return 7.775f;

					case '9':
						return 7.775f;

					case ':':
						return 2.775f;

					case ';':
						return 2.775f;

					case '<':
						return 8.775f;

					case '=':
						return 8.775f;

					case '>':
						return 8.775f;

					case '?':
						return 6.775f;

					case '@':
						return 1.775f;

					case 'A':
						return 7.875f;

					case 'B':
						return 6.875f;

					case 'C':
						return 6.875f;

					case 'D':
						return 7.875f;

					case 'E':
						return 4.875f;

					case 'F':
						return 6.875f;

					case 'G':
						return 8.875f;

					case 'H':
						return 7.875f;

					case 'I':
						return 2.875f;

					case 'J':
						return 4.875f;

					case 'K':
						return 7.875f;

					case 'L':
						return 4.875f;

					case 'M':
						return 10.875f;

					case 'N':
						return 8.875f;

					case 'O':
						return 10.875f;

					case 'P':
						return 6.875f;

					case 'Q':
						return 9.875f;

					case 'R':
						return 6.875f;

					case 'S':
						return 6.875f;

					case 'T':
						return 5.875f;

					case 'U':
						return 8.875f;

					case 'V':
						return 8.875f;

					case 'W':
						return 12.875f;

					case 'X':
						return 8.875f;

					case 'Y':
						return 6.875f;

					case 'Z':
						return 7.875f;

					case '[':
						return 7.775f;

					case '\\':
						return 3.775f;

					case ']':
						return 14.775f;

					case '^':
						return 2.775f;

					case '_':
						return 3.775f;

					case '`':
						return 2.775f;

					case 'a':
						return 6.775f;

					case 'b':
						return 7.275f;

					case 'c':
						return 4.65f;

					case 'd':
						return 7.275f;

					case 'e':
						return 6.775f;

					case 'f':
						return 4.4f;

					case 'g':
						return 7.275f;

					case 'h':
						return 6.875f;

					case 'i':
						return 3.5f;

					case 'j':
						return 3.5f;

					case 'k':
						return 6.775f;

					case 'l':
						return 3.5f;

					case 'm':
						return 9.775f;

					case 'n':
						return 6.875f;

					case 'o':
						return 7.775f;

					case 'p':
						return 6.875f;

					case 'q':
						return 6.875f;

					case 'r':
						return 5.4f;

					case 's':
						return 5.4f;

					case 't':
						return 4.275f;

					case 'u':
						return 6.275f;

					case 'v':
						return 6.775f;

					case 'w':
						return 9.775f;

					case 'x':
						return 7.175f;

					case 'y':
						return 7.175f;

					case 'z':
						return 6.775f;

					case '{':
						return 7.775f;

					case '|':
						return 5.775f;

					case '}':
						return 7.775f;

					case '~':
						return 0f;

					case '\u20ac': //€
						return 7.775f;

					case '\u00A7': //§
						return 7.775f;

					case '\u00B0': //°
					case '\u00B2': //²
					case '\u00B3': //³
						return 6.775f;

					case '\u00B4':
						return 7.775f;

					default:
						return 0f;
				}

			default:
				return 0f;
		}
	}
}
