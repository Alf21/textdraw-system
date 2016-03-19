package me.alf21.textdrawsystem.content.attachments;

import me.alf21.textdrawsystem.calculations.Calculation;
import me.alf21.textdrawsystem.content.Content;
import me.alf21.textdrawsystem.content.components.Component;
import me.alf21.textdrawsystem.utils.PlayerTextdraw;
import net.gtaun.shoebill.constant.TextDrawAlign;
import net.gtaun.shoebill.constant.TextDrawFont;
import net.gtaun.shoebill.data.Color;
import net.gtaun.shoebill.data.Vector2D;
import net.gtaun.shoebill.object.Player;

import java.util.ArrayList;

/**
 * Created by Alf21 on 12.03.2016 in the project 'textdraw-system'.
 */
public class Box extends Attachment {

	private PlayerTextdraw playerTextdraw;

	protected Box(Content content, Color color, String name) {
		super(content, AttachmentAlignment.TOP_LEFT, name);

		playerTextdraw = PlayerTextdraw.create(getPlayer(), 0f, 0f, "_");
		playerTextdraw.setAlignment(TextDrawAlign.LEFT);
		playerTextdraw.setBackgroundColor(new Color(0,0,0,255));
		playerTextdraw.setFont(TextDrawFont.FONT2);
		playerTextdraw.setLetterSize(0.5f, 1.0f);
		playerTextdraw.setColor(new Color(255,255,255,255));
		playerTextdraw.setOutlineSize(0);
		playerTextdraw.setProportional(true);
		playerTextdraw.setShadowSize(1);
		playerTextdraw.setSelectable(false);
		playerTextdraw.setUseBox(true);
		playerTextdraw.setBoxColor(color);
		playerTextdraw.setTextSize(-4.0f, -4.0f);
	}

	public static Box create(Content content, Color color, String name) {
		return new Box(content, color, name);
	}

	@Override
	public void attach(Component component) {
		super.attach(component);
		Player tmp;
		if((tmp = component.getComponentTextdraws().get(0).getPlayer()) != super.getPlayer())
			playerTextdraw.changePlayer(tmp);
		playerTextdraw.move(component.getComponentPosition().getX(), component.getComponentPosition().getY());
		float height = Calculation.getBoxHeight(component.getComponentTextdraws()) + 4f;
		playerTextdraw.setWidth(Calculation.getBoxWidth(component.getComponentTextdraws()) + 4f);
		playerTextdraw.setHeight(height);
		playerTextdraw.setLetterSize(playerTextdraw.getLetterSize().getX(), Calculation.heightToLetterHeight(height));
	}

	public PlayerTextdraw getBoxTextdraw() {
		return playerTextdraw;
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

	public void update() {
		if(super.getComponent() != null && !isDestroyed()) {
			playerTextdraw.move(super.getComponent().getComponentPosition().getX(), super.getComponent().getComponentPosition().getY());
			float height = Calculation.getBoxHeight(super.getComponent().getComponentTextdraws()) + 4f;
			playerTextdraw.setWidth(Calculation.getBoxWidth(super.getComponent().getComponentTextdraws()) + 4f);
			playerTextdraw.setHeight(height);
			playerTextdraw.setLetterSize(playerTextdraw.getLetterSize().getX(), height);
		}
	}
}
