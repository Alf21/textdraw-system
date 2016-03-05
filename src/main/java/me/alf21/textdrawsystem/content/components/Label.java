package me.alf21.textdrawsystem.content.components;

import me.alf21.textdrawsystem.content.Content;
import me.alf21.textdrawsystem.utils.PlayerTextdraw;
import net.gtaun.shoebill.constant.TextDrawAlign;
import net.gtaun.shoebill.constant.TextDrawFont;
import net.gtaun.shoebill.data.Color;
import net.gtaun.shoebill.data.Vector2D;

import java.util.ArrayList;

/**
 * Created by Alf21 on 27.02.2016.
 */
public class Label extends Component { //TODO Change to Label and base on Component instead of input or easily create BarLabel and ImageLabel and RadioLabel and SelectLabel and SwitcherLabel

	private Component component;
	private PlayerTextdraw playerTextdraw;

	private Label(Content content, String text, Component component, String name) {
		super(content, ComponentAlignment.TOP_LEFT, name);
		this.component = component;
		if(text.isEmpty() || text.replaceAll(" ", "").isEmpty())
			text = "_";

		playerTextdraw = PlayerTextdraw.create(getPlayer(), component.getComponentPosition().getX()-8f, component.getComponentPosition().getY(), text);
		playerTextdraw.setAlignment(TextDrawAlign.RIGHT);
		playerTextdraw.setBackgroundColor(new Color(0,0,0,255));
		playerTextdraw.setFont(TextDrawFont.get(1));
		playerTextdraw.setLetterSize(0.280000f, 1.000000f);
		playerTextdraw.setColor(new Color(255,255,255,255));
		playerTextdraw.setOutlineSize(0);
		playerTextdraw.setProportional(true);
		playerTextdraw.setShadowSize(1);
		playerTextdraw.setSelectable(false);
	}

	public static Label create(Content content, String text, Component component, String name) { return new Label(content, text, component, name); }

	public void attach(Component component) {
		detach();
		this.component = component;
		if(getPlayer() != component.getPlayerTextdraws().get(0).getPlayer())
			playerTextdraw.changePlayer(component.getPlayerTextdraws().get(0).getPlayer());
		playerTextdraw.move(component.getComponentPosition().getX()-8f, component.getComponentPosition().getY());
		System.out.println("Label added to " + component.getName() + " -- " + component);
	}

	public void advancedAttach(Component component) {
		detach();
		this.component = component;
	}

	public void detach() {
		if(component != null) {
			Component component = this.component;
			this.component = null;
			component.detachLabel();
		}
	}

	public PlayerTextdraw getLabelTextdraw() {
		return playerTextdraw;
	}

	public Component getComponent() {
		return component;
	}

	public void setText(String text) {
		if(text.isEmpty() || text.replaceAll(" ", "").isEmpty())
			text = "_";
		getLabelTextdraw().setText(text);
	}

	public String getText() {
		return getLabelTextdraw().getText();
	}

	@Override
	public void destroy() {
		playerTextdraw.destroy();
	}

	@Override
	public void recreate() { playerTextdraw.recreate(); }

	@Override
	public boolean isDestroyed() {
		return playerTextdraw.isDestroyed();
	}

	@Override
	public void show() {
		playerTextdraw.show();
	}

	@Override
	public void hide() {
		playerTextdraw.hide();
	}

	@Override
	public ArrayList<PlayerTextdraw> getPlayerTextdraws() {
		ArrayList<PlayerTextdraw> playerTextdraws = new ArrayList<>();
		playerTextdraws.add(playerTextdraw);
		return playerTextdraws;
	}

	@Override
	public Vector2D getComponentPosition() {
		return playerTextdraw.getPosition();
	}
}
