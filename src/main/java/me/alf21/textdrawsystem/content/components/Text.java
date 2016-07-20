package me.alf21.textdrawsystem.content.components;

import me.alf21.textdrawsystem.container.Container;
import me.alf21.textdrawsystem.content.attachments.Box;
import me.alf21.textdrawsystem.utils.PlayerTextdraw;
import net.gtaun.shoebill.constant.TextDrawAlign;
import net.gtaun.shoebill.constant.TextDrawFont;
import net.gtaun.shoebill.data.Color;
import net.gtaun.shoebill.data.Vector2D;

import java.util.ArrayList;

/**
 * Created by Alf21 on 02.03.2016 in the project 'textdraw-system'.
 */
public class Text extends Component {

	private PlayerTextdraw playerTextdraw;

	protected Text(Container container, float x, float y, float width, String text, String name) {
		super(container, ComponentAlignment.TOP_LEFT, name);
		playerTextdraw = PlayerTextdraw.create(super.getPlayer(), x, y, text);
	//	playerTextdraw.setUseBox(true);
		//TODO
	//	if(width != Float.NaN)
	//		width = Calculation.getBoxSize(width);
	//  calc width from pos

		playerTextdraw.setAlignment(TextDrawAlign.LEFT);
		playerTextdraw.setBackgroundColor(new Color(0,0,0,255));
		playerTextdraw.setFont(TextDrawFont.get(1));
		playerTextdraw.setLetterSize(0.280000f, 1.000000f);
		playerTextdraw.setColor(new Color(255,255,255,255));
		playerTextdraw.setOutlineSize(0);
		playerTextdraw.setProportional(true);
		playerTextdraw.setShadowSize(1);
		playerTextdraw.setSelectable(false);
	}

	public static Text create(Container container, float x, float y, float width, String text, String name) {
		if(name.replaceAll(" ", "").isEmpty())
			name = "_";
		return new Text(container, x, y, width, text, name);
	}

	public static Text create(Container container, float x, float y, String name) {
		return create(container, x, y, Float.NaN, "_", name);
	}

	public static Text create(Container container, Vector2D vector2D, float width, String text, String name) {
		return create(container, vector2D.getX(), vector2D.getY(), width, text, name);
	}

	public static Text create(Container container, Vector2D vector2D, String text, String name) {
		return create(container, vector2D.getX(), vector2D.getY(), Float.NaN, text, name);
	}

	public static Text create(Container container, Vector2D vector2D, String name) {
		return create(container, vector2D, "_", name);
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
	public boolean isDestroyed() {
		return !(!playerTextdraw.isDestroyed() || !super.isDestroyed());
	}

	@Override
	public void destroy() {
		super.destroy();
		playerTextdraw.destroy();
	}

	@Override
	public void recreate() {
		super.recreate();
		playerTextdraw.recreate();
	}

	public void setText(String text) {
		if(text.replaceAll(" ", "").isEmpty())
			text = "_";
		playerTextdraw.setText(text);
		super.getAttachments().stream().filter(attachment -> attachment instanceof Box).map(attachment -> (Box) attachment).forEach(Box::update);
	}

	public String getText() {
		return playerTextdraw.getText();
	}

	@Override
	public Vector2D getComponentPosition() {
		return playerTextdraw.getPosition();
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

	public PlayerTextdraw getPlayerTextdraw() {
		return playerTextdraw;
	}
}
