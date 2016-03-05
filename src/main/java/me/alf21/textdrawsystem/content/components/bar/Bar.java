package me.alf21.textdrawsystem.content.components.bar;

import me.alf21.textdrawsystem.content.Content;
import me.alf21.textdrawsystem.content.components.Component;
import me.alf21.textdrawsystem.content.components.ComponentAlignment;
import me.alf21.textdrawsystem.utils.PlayerTextdraw;
import net.gtaun.shoebill.constant.TextDrawFont;
import net.gtaun.shoebill.data.Color;
import net.gtaun.shoebill.data.Vector2D;
import net.gtaun.shoebill.object.Timer;

import java.util.ArrayList;


/**
 * Created by Alf21 on 04.03.2016.
 */
public class Bar extends Component { //TODO only calcs height to button but not centered -> sub the half from the position.y

	private BarInterface barInterface;
	private Content content;
	private Timer timer;
	private float minHeight, maxHeight, minWidth, maxWidth;
	private PlayerTextdraw playerTextdraw, barBackground;
	private double process;
	private Color color;

	private Bar(Content content, float x, float y, float minHeight, float maxHeight, float minWidth, float maxWidth, Color color, BarInterface barInterface, String name) {
		super(content, ComponentAlignment.TOP_LEFT, name);
		this.content = content;
		this.barInterface = barInterface;
		this.minHeight = minHeight;
		this.maxHeight = maxHeight;
		this.minWidth = minWidth;
		this.maxWidth = maxWidth;
		this.color = color;
		createTimer(250);
		createBar(x, y);

		barInterface.onInit(content, this);
	}

	private void createBar(float x, float y) {
		playerTextdraw = PlayerTextdraw.create(getPlayer(), x, y, "_");
		playerTextdraw.setBackgroundColor(new Color(0, 0, 0, 255));
		playerTextdraw.setFont(TextDrawFont.get(1));
		playerTextdraw.setLetterSize(new Vector2D(0.500000f, getMaxHeight() / 8.225f));
		playerTextdraw.setColor(new Color(255, 255, 255, 255));
		playerTextdraw.setTextSize(x, getMaxHeight());
		playerTextdraw.setOutlineSize(0);
		playerTextdraw.setProportional(true);
		playerTextdraw.setShadowSize(1);
		playerTextdraw.setUseBox(true);
		playerTextdraw.setBoxColor(getColor());
		playerTextdraw.setSelectable(false);

		barBackground = PlayerTextdraw.create(getPlayer(), x, y, "");
		barBackground.setBackgroundColor(new Color(0, 0, 0, 255));
		barBackground.setFont(TextDrawFont.get(1));
		barBackground.setLetterSize(new Vector2D(0.500000f, getMaxHeight() / 8.225f));
		barBackground.setColor(new Color(255, 255, 255, 255));
		barBackground.setTextSize(x + getMaxWidth(), getMaxHeight());
		barBackground.setOutlineSize(0);
		barBackground.setProportional(true);
		barBackground.setShadowSize(1);
		barBackground.setUseBox(true);
		barBackground.setBoxColor(new Color(getColor().getR(), getColor().getG(), getColor().getB(), 50));
		barBackground.setSelectable(false);
	}

	public static Bar create(Content content, float x, float y, float minHeight, float maxHeight, float minWidth, float maxWidth, Color color, BarInterface barInterface, String name) {
		return new Bar(content, x, y, minHeight, maxHeight, minWidth, maxWidth, color, barInterface, name);
	}

	public static Bar create(Content content, float x, float y, float minHeight, float maxHeight, float minWidth, float maxWidth, Color color, String name) {
		return create(content, x, y, minHeight, maxHeight, minWidth, maxWidth, color, new BarInterface() {}, name);
	}

	public static Bar create(Content content, float x, float y, float minHeight, float maxHeight, float minWidth, float maxWidth, String name) {
		return create(content, x, y, minHeight, maxHeight, minWidth, maxWidth, new Color(0, 150, 0, 255), new BarInterface() {}, name);
	}

	public static Bar create(Content content, Vector2D vector2D, float minHeight, float maxHeight, float minWidth, float maxWidth, Color color, BarInterface barInterface, String name) {
		return create(content, vector2D.getX(), vector2D.getY(), minHeight, maxHeight, minWidth, maxWidth, color, barInterface, name);
	}

	public static Bar create(Content content, Vector2D vector2D, float minHeight, float maxHeight, float minWidth, float maxWidth, Color color, String name) {
		return create(content, vector2D.getX(), vector2D.getY(), minHeight, maxHeight, minWidth, maxWidth, color, name);
	}

	public static Bar create(Content content, Vector2D vector2D, float minHeight, float maxHeight, float minWidth, float maxWidth, String name) {
		return create(content, vector2D.getX(), vector2D.getY(), minHeight, maxHeight, minWidth, maxWidth, name);
	}

	public BarInterface getBarInterface() {
		return barInterface;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setProcess(double process) {
		this.process = process;
		float width = ((maxWidth - minWidth) / 100.0f * (float) process) + minWidth;
		float height = ((maxHeight - minHeight) / 100.0f * (float) process) + minHeight;
		playerTextdraw.setTextSize(playerTextdraw.getPosition().getX() + width, getPlayerTextdraw().getTextSize().getY());
		playerTextdraw.setLetterSize(playerTextdraw.getLetterSize().getX(), height / 8.225f);
		playerTextdraw.hide();
		playerTextdraw.show();
	}

	public double getProcess() {
		return process;
	}

	@Override
	public void show() {
		barBackground.show();
		playerTextdraw.show();
		super.show();
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
		timer = Timer.create(interval, (factualInterval) -> barInterface.onProcess(content, this));
	}

	public Content getContent() {
		return content;
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

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public ArrayList<PlayerTextdraw> getPlayerTextdraws() {
		ArrayList<PlayerTextdraw> playerTextdraws = super.getPlayerTextdraws();
		playerTextdraws.add(barBackground);
		playerTextdraws.add(playerTextdraw);
		return playerTextdraws;
	}

	@Override
	public Vector2D getComponentPosition() {
		return playerTextdraw.getPosition();
	}
}
