package me.alf21.textdrawsystem.content.components.clickableTextdraw;

import me.alf21.textdrawsystem.calculations.Calculation;
import me.alf21.textdrawsystem.container.Container;
import me.alf21.textdrawsystem.TextdrawSystem;
import me.alf21.textdrawsystem.content.components.Component;
import me.alf21.textdrawsystem.content.components.ComponentAlignment;
import me.alf21.textdrawsystem.content.components.ComponentData;
import me.alf21.textdrawsystem.utils.PlayerTextdraw;
import net.gtaun.shoebill.constant.TextDrawAlign;
import net.gtaun.shoebill.constant.TextDrawFont;
import net.gtaun.shoebill.data.Color;
import net.gtaun.shoebill.data.Vector2D;

import java.util.ArrayList;

/**
 * Created by Alf21 on 14.07.2016 in the project 'textdraw-system'.
 */
public class ClickableTextdraw extends Component {
	private PlayerTextdraw playerTextdraw;
	private ClickableTextdrawHandler clickHandler;
	private boolean clicked;

	private ClickableTextdraw(Container container, float x, float y, float width, float height, Color color, String name) {
		super(container, ComponentAlignment.TOP_LEFT, name);

		playerTextdraw = PlayerTextdraw.create(super.getPlayer(), x, y, "_");
		playerTextdraw.setAlignment(TextDrawAlign.get(2));
		playerTextdraw.setBackgroundColor(new Color(0,0,0,255));
		playerTextdraw.setFont(TextDrawFont.get(1));
		playerTextdraw.setLetterSize(0.280000f, Calculation.heightToLetterHeight(height));
		playerTextdraw.setOutlineSize(0);
		playerTextdraw.setProportional(true);
		playerTextdraw.setShadowSize(0);
		playerTextdraw.setUseBox(true);
		playerTextdraw.setBoxColor(color);
	//	playerTextdraw.setTextSize(10.0f, width);
		playerTextdraw.setBox(width, height);
		playerTextdraw.setSelectable(true);

		clicked = false;
	}

	public static ClickableTextdraw create(Container container, float x, float y, float width, float height, Color color, String name) {
		return new ClickableTextdraw(container, x, y, width, height, color, name);
	}

	@Override
	public void destroy() {
		playerTextdraw.destroy();
		super.destroy();
	}

	@Override
	public boolean isDestroyed() {
		return !(!playerTextdraw.isDestroyed() || !super.isDestroyed());
	}

	@Override
	public void recreate() {
		super.recreate();
		playerTextdraw.recreate();
	}

	@Override
	public void show() {
		super.show();
		playerTextdraw.show();
	}

	@Override
	public void hide() {
		playerTextdraw.hide();
		super.hide();
	}

	@Override
	public ArrayList<PlayerTextdraw> getAllPlayerTextdraws() {
		ArrayList<PlayerTextdraw> playerTextdraws = super.getAllPlayerTextdraws();
		playerTextdraws.add(playerTextdraw);
		return playerTextdraws;
	}

	@Override
	public ArrayList<PlayerTextdraw> getComponentTextdraws() {
		ArrayList<PlayerTextdraw> playerTextdraws = super.getComponentTextdraws();
		playerTextdraws.add(playerTextdraw);
		return playerTextdraws;
	}

	@Override
	public void onClick(net.gtaun.shoebill.object.PlayerTextdraw playerTextdraw) {
		if (clickHandler != null)
			clickHandler.handle(this);
		clicked = true;
	}

	public PlayerTextdraw getButtonTextdraw() {
		return playerTextdraw;
	}

	@Override
	public ComponentData getComponentData() {
		return new ComponentData<>(clicked);
	}

	@Override
	public Vector2D getComponentPosition() {
		return playerTextdraw.getPosition();
	}

	public void setClickHandler(ClickableTextdrawHandler clickHandler) {
		this.clickHandler = clickHandler;
	}

	public ClickableTextdrawHandler getClickHandler() {
		return clickHandler;
	}

	public boolean isClicked() {
		return clicked;
	}

	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}
}
