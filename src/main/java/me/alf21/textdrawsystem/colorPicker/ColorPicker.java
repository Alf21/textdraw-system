package me.alf21.textdrawsystem.colorPicker;

import me.alf21.textdrawsystem.TextdrawSystem;
import me.alf21.textdrawsystem.calculations.Calculation;
import me.alf21.textdrawsystem.container.Container;
import me.alf21.textdrawsystem.content.components.Component;
import me.alf21.textdrawsystem.content.components.button.Button;
import me.alf21.textdrawsystem.utils.PlayerTextdraw;
import net.gtaun.shoebill.constant.TextDrawAlign;
import net.gtaun.shoebill.constant.TextDrawFont;
import net.gtaun.shoebill.data.Color;
import net.gtaun.shoebill.data.Vector2D;
import net.gtaun.shoebill.object.Player;

import java.util.ArrayList;

/**
 * Created by Alf21 on 17.07.2016 in the project 'textdraw-system'.
 */
public class ColorPicker extends Container {

	private Player player;
	private PickedColorHandler pickedColorHandler;

	private PlayerTextdraw titleTextdraw;
	private PlayerTextdraw leftBackgroundTextdraw, rightBackgroundTextdraw;

	public ColorPicker(Player player) {
		super(TextdrawSystem.getPanel(player), "ColorPicker");
		this.player = player;
		createColorPicker();
	}

	public void createColorPicker() {
		createTitle();
		createBackground();
		createColorBar();
	}

	public static ColorPicker create(Player player) {
		return new ColorPicker(player);
	}

	private void createColorBar() {
	//	float rightHeight = Calculation.getBoxHeight(rightBackgroundTextdraw);
		float startX = 530.0f;
		float startY = 117.0f;
		float paddingY = 13.0f;
		float width = 66.0f;

		Color[] color = {
				Color.DARKBLUE,
				Color.BLUE,
				Color.PURPLE,
				Color.PINK,
				Color.RED,
				Color.ORANGE,
				Color.YELLOW,
				Color.YELLOWGREEN,
				Color.GREEN,
				Color.TEAL,
				Color.BROWN,
				Color.GRAY,
				Color.WHITE
		};
		for (int y = 0; y < color.length; y++) {
			Button button = Button.create(this, startX, startY + paddingY * y, width, "_", "Bar_Button_" + y);
			Color tmp = color[y];
			button.getButtonTextdraw().setBoxColor(tmp);
			button.setClickHandler(handler -> {
				clearColorContent();
				createColorContent(tmp);
				showColorContent();
			});
			button.toggleActivationEffect(false);
			this.addButton(button);
		}

		createColorContent(color[0]);
	}

	private void clearColorContent() {
		ArrayList<Component> tmp = new ArrayList<>();
		getComponents().forEach(component -> {
			if (component.getName().contains("Content_Button_"))
				tmp.add(component);
		});
		tmp.forEach(component -> {
			component.hide();
			component.destroy();
			getComponents().remove(component);
		});
	}

	private void showColorContent() {
		getComponents().forEach(component -> {
			if (component.getName().contains("Content_Button_"))
				component.show();
		});
	}

	private void createColorContent(Color color) {
		float startX = 85.0f, startY = 120.0f;
		float leftWidth = Calculation.getWidth(leftBackgroundTextdraw);
		float leftHeight = Calculation.getBoxHeight(leftBackgroundTextdraw);
		float defaultWidth = 9.0f;
		float defaultHeight = 9.0f;
		float paddingX = 15.0f;
		float paddingY = 15.0f;

		int maxX = (int)( leftWidth / (defaultWidth * 2 + paddingX) ) + 1;
		int maxY = (int)( leftHeight / (defaultHeight + paddingY) ) + 1;

		int maxColors = maxX * maxY;
		int a = 255; //TODO ask for alpha later

		//create horizontal colors
		int bx, by = 0;
	//	for (float y = startY; y <= leftHeight - startY; y += paddingY + Calculation.letterHeightToHeight(defaultHeight)) {
		for (float y = startY; y <= leftHeight + startY; y += paddingY + defaultHeight) {
			bx = 0;
			for (float x = startX; x <= leftWidth + startX; x += paddingX + defaultWidth * 2) {
				Button button = Button.create(this, x, y, defaultWidth, "_", "Content_Button_" + by + "_" + bx);
			/*
				double  xFactor = (double) bx / (double) maxX,
						yFactor = (double) by / (double) maxY;
				int     r = (int)( ((double) color.getR() * xFactor) / 2.0 + ((double) color.getR() * yFactor) / 2.0 ),
						g = (int)( ((double) color.getG() * xFactor) / 2.0 + ((double) color.getG() * yFactor) / 2.0 ),
						b = (int)( ((double) color.getB() * xFactor) / 2.0 + ((double) color.getB() * yFactor) / 2.0 );
			*/
				double  factor = (double) maxColors / 2.0;
				int     currentColorValue = bx + by * maxX + 1;
				int     r, g, b;
				if (currentColorValue < maxColors / 2) {
					r = (int) ( (double) color.getR() / factor * (double) currentColorValue );
					g = (int) ( (double) color.getG() / factor * (double) currentColorValue );
					b = (int) ( (double) color.getB() / factor * (double) currentColorValue );
				}
				else if (currentColorValue == maxColors / 2) {
					r = color.getR();
					g = color.getG();
					b = color.getB();
				}
				else {
					Color tmp2 = new Color(255 - color.getR(), 255 - color.getG(), 255 - color.getB(), a);
					r = color.getR() + (int) ( (double) tmp2.getR() / factor * (double) ( currentColorValue - (int) factor) );
					g = color.getG() + (int) ( (double) tmp2.getG() / factor * (double) ( currentColorValue - (int) factor) );
					b = color.getB() + (int) ( (double) tmp2.getB() / factor * (double) ( currentColorValue - (int) factor) );
				}
				//System.out.println("(" + r + ", " + g + ", " + b + "): bx-by: " + bx + "-" + by + "; CCV: " + currentColorValue + " (MaxColors: " + maxColors + ") -> maxX: " + maxX); //TODO sometimes it wont be reach (255, 255, 255) but (255, 254, 255), ...
				Color tmp = new Color(r, g, b, a);
				button.getButtonTextdraw().setBoxColor(tmp);
				button.setClickHandler(handler -> onPickColor(tmp));
				button.toggleActivationEffect(false);
				this.addButton(button);
				bx++;
			}
			by++;
		}
	}

	private void createBackground() {
		leftBackgroundTextdraw = PlayerTextdraw.create(player, 75.000000f, 114.000000f, "_");
		leftBackgroundTextdraw.setBackgroundColor(new Color(0, 0, 0, 255));
		leftBackgroundTextdraw.setFont(TextDrawFont.get(1));
		leftBackgroundTextdraw.setLetterSize(new Vector2D(0.500000f, 32.000000f));
		leftBackgroundTextdraw.setColor(new Color(255, 255, 255, 255));
		leftBackgroundTextdraw.setOutlineSize(0);
		leftBackgroundTextdraw.setProportional(true);
		leftBackgroundTextdraw.setShadowSize(1);
		leftBackgroundTextdraw.setUseBox(true);
		leftBackgroundTextdraw.setBoxColor(new Color(0, 0, 0, 150));
		leftBackgroundTextdraw.setTextSize(new Vector2D(490.000000f, 0.000000f));
		leftBackgroundTextdraw.setSelectable(false);

		rightBackgroundTextdraw = PlayerTextdraw.create(player, 495.000000f, 114.000000f, "_");
		rightBackgroundTextdraw.setBackgroundColor(new Color(0, 0, 0, 255));
		rightBackgroundTextdraw.setFont(TextDrawFont.get(1));
		rightBackgroundTextdraw.setLetterSize(new Vector2D(0.500000f, 32.000000f));
		rightBackgroundTextdraw.setColor(new Color(255, 255, 255, 255));
		rightBackgroundTextdraw.setOutlineSize(0);
		rightBackgroundTextdraw.setProportional(true);
		rightBackgroundTextdraw.setShadowSize(1);
		rightBackgroundTextdraw.setUseBox(true);
		rightBackgroundTextdraw.setBoxColor(new Color(0, 0, 0, 150));
		rightBackgroundTextdraw.setTextSize(new Vector2D(565.000000f, 0.000000f));
		rightBackgroundTextdraw.setSelectable(false);
	}

	private void createTitle() {
		titleTextdraw = PlayerTextdraw.create(player, 320.000000f, 100.000000f, "Color Picker");
		titleTextdraw.setAlignment(TextDrawAlign.get(2));
		titleTextdraw.setBackgroundColor(new Color(0, 0, 0, 255));
		titleTextdraw.setFont(TextDrawFont.get(1));
		titleTextdraw.setLetterSize(new Vector2D(0.500000f, 1.000000f));
		titleTextdraw.setColor(new Color(255, 255, 255, 255));
		titleTextdraw.setOutlineSize(1);
		titleTextdraw.setProportional(true);
		titleTextdraw.setShadowSize(0);
		titleTextdraw.setUseBox(true);
		titleTextdraw.setBoxColor(new Color(0, 0, 0, 150));
		titleTextdraw.setTextSize(new Vector2D(0.000000f, 490.000000f));
		titleTextdraw.setSelectable(false);
	}

	public void onPickColor(Color color) {
		if (pickedColorHandler != null)
			pickedColorHandler.handle(this, color);
		this.destroy();
	}

	public void setPickedColorHandler(PickedColorHandler pickedColorHandler) {
		this.pickedColorHandler = pickedColorHandler;
	}

	public Player getPlayer() {
		return player;
	}

	@Override
	public void show() {
		titleTextdraw.show();
		leftBackgroundTextdraw.show();
		rightBackgroundTextdraw.show();
		super.show();
	}

	@Override
	public void recreate() {
		titleTextdraw.recreate();
		leftBackgroundTextdraw.recreate();
		rightBackgroundTextdraw.recreate();
		super.recreate();
	}

	@Override
	public void hide() {
		super.hide();
		titleTextdraw.hide();
		leftBackgroundTextdraw.hide();
		rightBackgroundTextdraw.hide();
	}

	@Override
	public void destroy() {
		super.destroy();
		titleTextdraw.destroy();
		leftBackgroundTextdraw.destroy();
		rightBackgroundTextdraw.destroy();
	}

	@Override
	public boolean isDestroyed() {
		return super.isDestroyed() && titleTextdraw.isDestroyed() && leftBackgroundTextdraw.isDestroyed() && rightBackgroundTextdraw.isDestroyed();
	}

	public PlayerTextdraw getTitleTextdraw() {
		return titleTextdraw;
	}

	public PlayerTextdraw getLeftBackgroundTextdraw() {
		return leftBackgroundTextdraw;
	}

	public PlayerTextdraw getRightBackgroundTextdraw() {
		return rightBackgroundTextdraw;
	}
}
