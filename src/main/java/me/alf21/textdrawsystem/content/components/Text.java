package me.alf21.textdrawsystem.content.components;

import me.alf21.textdrawsystem.content.Content;
import me.alf21.textdrawsystem.utils.PlayerTextdraw;
import net.gtaun.shoebill.constant.TextDrawAlign;
import net.gtaun.shoebill.constant.TextDrawFont;
import net.gtaun.shoebill.data.Color;
import net.gtaun.shoebill.data.Vector2D;
import net.gtaun.shoebill.object.Player;

/**
 * Created by Alf21 on 02.03.2016.
 */
public class Text extends Component {

	private Content content;
	private Player player;
	private PlayerTextdraw playerTextdraw;

	protected Text(Content content, float x, float y, float width, String text, String name) {
		super(name);
		this.content = content;
		player = content.getPanel().getPlayer();
		playerTextdraw = PlayerTextdraw.create(player, x, y, text);
		playerTextdraw.setUseBox(true);
		//TODO
	//	if(width != Float.NaN)
	//		width = Calculation.getBoxSize(width);
	//  calc width from pos

		playerTextdraw = PlayerTextdraw.create(player, x, y, text);
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

	public static Text create(Content content, float x, float y, float width, String text, String name) {
		if(name.replaceAll(" ", "").isEmpty())
			name = "_";
		return new Text(content, x, y, width, text, name);
	}

	public static Text create(Content content, float x, float y, String name) { return create(content, x, y, Float.NaN, "_", name); }

	public static Text create(Content content, Vector2D vector2D, float width, String text, String name) { return create(content, vector2D.getX(), vector2D.getY(), width, text, name); }

	public static Text create(Content content, Vector2D vector2D, String text, String name) { return create(content, vector2D.getX(), vector2D.getY(), Float.NaN, text, name); }

	public static Text create(Content content, Vector2D vector2D, String name) { return create(content, vector2D, "_", name); }

	public Content getContent() {
		return content;
	}

	public PlayerTextdraw getPlayerTextdraw() {
		return playerTextdraw;
	}

	@Override
	public void recreate() {
		playerTextdraw.recreate();
	}

	@Override
	public boolean isDestroyed() {
		return playerTextdraw.isDestroyed();
	}

	@Override
	public void destroy() {
		playerTextdraw.destroy();
	}

	@Override
	public void hide() {
		playerTextdraw.hide();
	}

	@Override
	public void show() {
		playerTextdraw.show();
	}

	public void setText(String text) {
		if(text.isEmpty() || text.replaceAll(" ", "").isEmpty())
			text = "_";
		playerTextdraw.setText(text);
	}

	public String getText() {
		return playerTextdraw.getText();
	}
}
