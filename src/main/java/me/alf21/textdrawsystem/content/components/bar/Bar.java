package me.alf21.textdrawsystem.content.components.bar;

import me.alf21.textdrawsystem.container.Container;
import me.alf21.textdrawsystem.calculations.Calculation;
import me.alf21.textdrawsystem.content.components.Component;
import me.alf21.textdrawsystem.content.components.ComponentAlignment;
import me.alf21.textdrawsystem.utils.PlayerTextdraw;
import net.gtaun.shoebill.constant.TextDrawFont;
import net.gtaun.shoebill.data.Color;
import net.gtaun.shoebill.data.Vector2D;
import net.gtaun.shoebill.object.Timer;

import java.util.ArrayList;


/**
 * Created by Alf21 on 04.03.2016 in the project 'textdraw-system'.
 */
public class Bar extends Component { //TODO only calcs height to button but not centered -> sub the half from the position.y

	private BarInterface barInterface;
	private Timer timer;
	private float minHeight, maxHeight, minWidth, maxWidth;
	private PlayerTextdraw playerTextdraw, barBackground;
	private double process;
	private Color minColor, maxColor;

	private Bar(Container container, float x, float y, float minHeight, float maxHeight, float minWidth, float maxWidth, Color minColor, Color maxColor, BarInterface barInterface, String name) {
		super(container, ComponentAlignment.TOP_LEFT, name);
		this.barInterface = barInterface;
		this.minHeight = minHeight-4;
		this.maxHeight = maxHeight-4;
		this.minWidth = minWidth;
		this.maxWidth = maxWidth;
		this.minColor = minColor;
		this.maxColor = maxColor;
		createTimer(200);
		createBar(x, y);

		barInterface.onInit(container, this);
	}

	private void createBar(float x, float y) {
		playerTextdraw = PlayerTextdraw.create(super.getPlayer(), x, y, "_");
		playerTextdraw.setBackgroundColor(new Color(0, 0, 0, 255));
		playerTextdraw.setFont(TextDrawFont.get(1));
		playerTextdraw.setLetterSize(new Vector2D(0.500000f, getMaxHeight() / 8.225f));
		playerTextdraw.setColor(new Color(255, 255, 255, 255));
		playerTextdraw.setTextSize(x - 4, getMaxHeight());
		playerTextdraw.setOutlineSize(0);
		playerTextdraw.setProportional(true);
		playerTextdraw.setShadowSize(1);
		playerTextdraw.setUseBox(true);
		playerTextdraw.setBoxColor(getColor());
		playerTextdraw.setSelectable(false);

		barBackground = PlayerTextdraw.create(super.getPlayer(), x, y, "_");
		barBackground.setBackgroundColor(new Color(0, 0, 0, 255));
		barBackground.setFont(TextDrawFont.get(1));
		barBackground.setLetterSize(new Vector2D(0.500000f, getMaxHeight() / 8.225f));
		barBackground.setColor(new Color(255, 255, 255, 255));
		barBackground.setTextSize(x - 4 + getMaxWidth(), getMaxHeight());
		barBackground.setOutlineSize(0);
		barBackground.setProportional(true);
		barBackground.setShadowSize(1);
		barBackground.setUseBox(true);
		barBackground.setBoxColor(new Color(getColor().getR(), getColor().getG(), getColor().getB(), 50));
		barBackground.setSelectable(false);
	}

	public static Bar create(Container container, float x, float y, float minHeight, float maxHeight, float minWidth, float maxWidth, Color minColor, Color maxColor, BarInterface barInterface, String name) {
		return new Bar(container, x, y, minHeight, maxHeight, minWidth, maxWidth, minColor, maxColor, barInterface, name);
	}

	public static Bar create(Container container, float x, float y, float minHeight, float maxHeight, float minWidth, float maxWidth, Color minColor, Color maxColor, String name) {
		return create(container, x, y, minHeight, maxHeight, minWidth, maxWidth, minColor, maxColor, new BarInterface() {}, name);
	}

	public static Bar create(Container container, float x, float y, float minHeight, float maxHeight, float minWidth, float maxWidth, String name) {
		return create(container, x, y, minHeight, maxHeight, minWidth, maxWidth, new Color(0, 150, 0, 255), new Color(0, 150, 0, 255), new BarInterface() {}, name);
	}

	public static Bar create(Container container, Vector2D vector2D, float minHeight, float maxHeight, float minWidth, float maxWidth, Color minColor, Color maxColor, BarInterface barInterface, String name) {
		return create(container, vector2D.getX(), vector2D.getY(), minHeight, maxHeight, minWidth, maxWidth, minColor, maxColor, barInterface, name);
	}

	public static Bar create(Container container, Vector2D vector2D, float minHeight, float maxHeight, float minWidth, float maxWidth, Color minColor, Color maxColor, String name) {
		return create(container, vector2D.getX(), vector2D.getY(), minHeight, maxHeight, minWidth, maxWidth, minColor, maxColor, name);
	}

	public static Bar create(Container container, Vector2D vector2D, float minHeight, float maxHeight, float minWidth, float maxWidth, String name) {
		return create(container, vector2D.getX(), vector2D.getY(), minHeight, maxHeight, minWidth, maxWidth, name);
	}

	public BarInterface getBarInterface() {
		return barInterface;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setProcess(double process) {
		this.process = process;
		float width = ((maxWidth - minWidth) * (float) process) + minWidth;
		float height = ((maxHeight - minHeight) * (float) process) + minHeight;
		playerTextdraw.setTextSize(playerTextdraw.getPosition().getX() - 4 + width, getPlayerTextdraw().getTextSize().getY());
		playerTextdraw.setLetterSize(playerTextdraw.getLetterSize().getX(), height / 8.225f);
		playerTextdraw.setBoxColor(getColor());
		barBackground.setBoxColor(new Color(getColor().getR(), getColor().getG(), getColor().getB(), 50));
		playerTextdraw.hide();
		barBackground.hide();
		barBackground.show();
		playerTextdraw.show();
	}

	public double getProcess() {
		return process;
	}

	@Override
	public void show() {
		super.show();
		barBackground.show();
		playerTextdraw.show();
		timer.start();
	}

	@Override
	public void hide() {
		timer.stop();
		super.hide();
		playerTextdraw.hide();
		barBackground.hide();
	}

	@Override
	public void destroy() {
		timer.destroy();
		super.destroy();
		playerTextdraw.destroy();
		barBackground.destroy();
	}

	@Override
	public boolean isDestroyed() {
		return !(!timer.isDestroyed() || !playerTextdraw.isDestroyed() || !barBackground.isDestroyed() || !super.isDestroyed());
	}

	@Override
	public void recreate() {
		createTimer(250);
		super.recreate();
		barBackground.recreate();
		playerTextdraw.recreate();
	}

	public void createTimer(int interval) {
		timer = Timer.create(interval, (factualInterval) -> barInterface.onProcess(getContainer(), this));
	}

	public float getMaxWidth() {
		return maxWidth;
	}

	public void setMaxWidth(float maxWidth) {
		this.maxWidth = maxWidth;
	}

	public float getMinWidth() {
		return minWidth;
	}

	public void setMinWidth(float minWidth) {
		this.minWidth = minWidth;
	}

	public float getMaxHeight() {
		return maxHeight;
	}

	public void setMaxHeight(float maxHeight) {
		this.maxHeight = maxHeight;
	}

	public float getMinHeight() {
		return minHeight;
	}

	public void setMinHeight(float minHeight) {
		this.minHeight = minHeight;
	}

	public PlayerTextdraw getPlayerTextdraw() {
		return playerTextdraw;
	}

	public PlayerTextdraw getBarBackground() {
		return barBackground;
	}

	@Override
	public ArrayList<PlayerTextdraw> getAllPlayerTextdraws() {
		ArrayList<PlayerTextdraw> playerTextdraws = super.getAllPlayerTextdraws();
		playerTextdraws.add(barBackground);
		playerTextdraws.add(playerTextdraw);
		return playerTextdraws;
	}

	@Override
	public ArrayList<PlayerTextdraw> getComponentTextdraws() {
		ArrayList<PlayerTextdraw> playerTextdraws = super.getComponentTextdraws();
		playerTextdraws.add(barBackground);
		playerTextdraws.add(playerTextdraw);
		return playerTextdraws;
	}

	@Override
	public Vector2D getComponentPosition() {
		return playerTextdraw.getPosition();
	}

	public Color getMinColor() {
		return minColor;
	}

	public Color getMaxColor() {
		return maxColor;
	}

	public Color getColor() {
		return Calculation.getBoxColor(getMinColor(), getMaxColor(), (float) getProcess(), 1.0f);
	}
}
