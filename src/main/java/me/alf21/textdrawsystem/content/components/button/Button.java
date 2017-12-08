package me.alf21.textdrawsystem.content.components.button;

import me.alf21.textdrawsystem.TextdrawSystem;
import me.alf21.textdrawsystem.container.Container;
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
// TODO use ClickableTextdraw as BaseComponent and extend it...
public class Button extends Component {
	private PlayerTextdraw playerTextdraw;
	private ButtonHandler clickHandler;
	private ButtonHandler activatedHandler;
	private ButtonHandler deactivatedHandler;
	private boolean activated;
	private boolean toggleActivationEffect;
	private Color activationColor, activationBgColor;
	private Color deactivationColor, deactivationBgColor;

	private Button(Container container, float x, float y, float width, String text, String name) {
		super(container, ComponentAlignment.TOP_LEFT, name);
		if(text.replaceAll(" ", "").isEmpty())
			text = "_";
		if(Float.isNaN(width))
			width = 125f;

		activationColor = TextdrawSystem.SELECT_COLOR;
		activationBgColor = TextdrawSystem.SELECT_BG_COLOR;
		deactivationColor = TextdrawSystem.UNSELECT_COLOR;
		deactivationBgColor = TextdrawSystem.UNSELECT_BG_COLOR;

		playerTextdraw = PlayerTextdraw.create(super.getPlayer(), x, y, text);
		playerTextdraw.setAlignment(TextDrawAlign.get(2));
		playerTextdraw.setBackgroundColor(new Color(0,0,0,255));
		playerTextdraw.setFont(TextDrawFont.get(1));
		playerTextdraw.setLetterSize(0.280000f, 1.000000f);
		playerTextdraw.setColor(deactivationColor);
		playerTextdraw.setOutlineSize(0);
		playerTextdraw.setProportional(true);
		playerTextdraw.setShadowSize(0);
		playerTextdraw.setUseBox(true);
		playerTextdraw.setBoxColor(deactivationBgColor);
		playerTextdraw.setTextSize(10.0f, width);
		playerTextdraw.setSelectable(true);

		activated = false;
		toggleActivationEffect = true;
	}

	public static Button create(Container container, float x, float y, float width, String text, String name) {
		return new Button(container, x, y, width, text, name);
	}

	public static Button create(Container container, float x, float y, String text, String name) {
		return create(container, x, y, Float.NaN, text, name);
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
	public void onClick(net.gtaun.shoebill.entities.PlayerTextdraw playerTextdraw) {
		toggleActivated();
		if (clickHandler != null)
			clickHandler.handle(this);

		if (activated)
			onActivate();
		else onDeactivate();
	}

	public void onActivate() {
		if (activatedHandler != null)
			activatedHandler.handle(this);
	}

	public void onDeactivate() {
		if (deactivatedHandler != null)
			deactivatedHandler.handle(this);
	}

	public PlayerTextdraw getButtonTextdraw() {
		return playerTextdraw;
	}

	@Override
	public ComponentData getComponentData() {
		return new ComponentData<>(activated);
	}

	@Override
	public Vector2D getComponentPosition() {
		return playerTextdraw.getPosition();
	}

	public boolean isActivated() {
		return activated;
	}

	public void toggleActivated() {
		activated = !activated;

		if (!toggleActivationEffect)
			return;

		boolean visible = playerTextdraw.isShown();
		if (visible) playerTextdraw.hide();
		if (activated) {
			playerTextdraw.setColor(activationColor);
			playerTextdraw.setBoxColor(activationBgColor);
		}
		else {
			playerTextdraw.setColor(deactivationColor);
			playerTextdraw.setBoxColor(deactivationBgColor);
		}
		if (visible) playerTextdraw.show();
	}

	public void setClickHandler(ButtonHandler clickHandler) {
		this.clickHandler = clickHandler;
	}

	public ButtonHandler getClickHandler() {
		return clickHandler;
	}

	public void setActivatedHandler(ButtonHandler activatedHandler) {
		this.activatedHandler = activatedHandler;
	}

	public ButtonHandler getActivatedHandler() {
		return activatedHandler;
	}

	public void setDeactivatedHandler(ButtonHandler deactivatedHandler) {
		this.deactivatedHandler = deactivatedHandler;
	}

	public ButtonHandler getDeactivatedHandler() {
		return deactivatedHandler;
	}

	public void toggleActivationEffect(boolean toggleActivationEffect) {
		this.toggleActivationEffect = toggleActivationEffect;
	}

	public Color getActivationColor() {
		return activationColor;
	}

	public void setActivationColor(Color activationColor) {
		this.activationColor = activationColor;
		if (isActivated()) {
			boolean showed = playerTextdraw.isShown();
			if (showed)
				playerTextdraw.hide();
			playerTextdraw.setColor(activationColor);
			if (showed)
				playerTextdraw.show();
		}
	}

	public Color getDeactivationColor() {
		return deactivationColor;
	}

	public void setDeactivationColor(Color deactivationColor) {
		this.deactivationColor = deactivationColor;
		if (!isActivated()) {
			boolean showed = playerTextdraw.isShown();
			if (showed)
				playerTextdraw.hide();
			playerTextdraw.setColor(deactivationColor);
			if (showed)
				playerTextdraw.show();
		}
	}

	public Color getDeactivationBgColor() {
		return deactivationBgColor;
	}

	public void setDeactivationBgColor(Color deactivationBgColor) {
		this.deactivationBgColor = deactivationBgColor;
		if (!isActivated()) {
			boolean showed = playerTextdraw.isShown();
			if (showed)
				playerTextdraw.hide();
			playerTextdraw.setBoxColor(deactivationBgColor);
			if (showed)
				playerTextdraw.show();
		}
	}

	public Color getActivationBgColor() {
		return activationBgColor;
	}

	public void setActivationBgColor(Color activationBgColor) {
		this.activationBgColor = activationBgColor;
		if (isActivated()) {
			boolean showed = playerTextdraw.isShown();
			if (showed)
				playerTextdraw.hide();
			playerTextdraw.setBoxColor(activationBgColor);
			if (showed)
				playerTextdraw.show();
		}
	}
}
