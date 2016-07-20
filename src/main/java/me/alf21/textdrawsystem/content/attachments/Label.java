package me.alf21.textdrawsystem.content.attachments;

import me.alf21.textdrawsystem.container.Container;
import me.alf21.textdrawsystem.content.components.Component;
import me.alf21.textdrawsystem.utils.PlayerTextdraw;
import net.gtaun.shoebill.constant.TextDrawAlign;
import net.gtaun.shoebill.constant.TextDrawFont;
import net.gtaun.shoebill.data.Color;
import net.gtaun.shoebill.data.Vector2D;

import java.util.ArrayList;

/**
 * Created by Alf21 on 27.02.2016 in the project 'textdraw-system'.
 */
public class Label extends Attachment { //TODO Change to Label and base on Component instead of input or easily create BarLabel and ImageLabel and RadioLabel and SelectLabel and SwitcherLabel

	private PlayerTextdraw playerTextdraw;

	private Label(Container container, String text, String name) {
		super(container, AttachmentAlignment.TOP_LEFT, name);
		if(text.isEmpty() || text.replaceAll(" ", "").isEmpty())
			text = "_";

		playerTextdraw = PlayerTextdraw.create(getPlayer(), 0f, 0f, text);
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

	public static Label create(Container container, String text, String name) { return new Label(container, text, name); }

	@Override
	public void attach(Component component) {
		super.attach(component);
		if(getPlayer() != component.getComponentTextdraws().get(0).getPlayer())
			playerTextdraw.changePlayer(component.getComponentTextdraws().get(0).getPlayer());
		playerTextdraw.move(component.getComponentPosition().getX()-8f, component.getComponentPosition().getY());
	}

	public PlayerTextdraw getLabelTextdraw() {
		return playerTextdraw;
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
	public Vector2D getAttachmentPosition() {
		return playerTextdraw.getPosition();
	}
}
