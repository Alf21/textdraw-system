package me.alf21.textdrawsystem.content.components.input;

import me.alf21.textdrawsystem.content.components.Component;
import me.alf21.textdrawsystem.utils.PlayerTextdraw;
import net.gtaun.shoebill.constant.TextDrawAlign;
import net.gtaun.shoebill.constant.TextDrawFont;
import net.gtaun.shoebill.data.Color;
import net.gtaun.shoebill.object.Player;

import java.util.ArrayList;

/**
 * Created by Alf21 on 27.02.2016.
 */
public class InputLabel extends Component {

	private Player player;
	private Input input;
	private PlayerTextdraw playerTextdraw;

	private InputLabel(Player player, String text, Input input, String name) {
		super(name);
		this.player = player;
		this.input = input;
		if(text.isEmpty() || text.replaceAll(" ", "").isEmpty())
			text = "_";

		playerTextdraw = PlayerTextdraw.create(player, input.getInputTextdraw().getPosition().getX()-8f, input.getInputTextdraw().getPosition().getY(), text);
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

	public static InputLabel create(Player player, String text, Input input, String name) { return new InputLabel(player, text, input, name); }

	public void attach(Input input) {
		detach();
		this.input = input;
		if(player != input.getInputTextdraw().getPlayer())
			playerTextdraw.changePlayer(input.getInputTextdraw().getPlayer());
		playerTextdraw.move(input.getInputTextdraw().getPosition().getX()-8f, input.getInputTextdraw().getPosition().getY());
	}

	public void advancedAttach(Input input) {
		detach();
		this.input = input;
	}

	public void detach() {
		if(input != null) {
			Input input = this.input;
			this.input = null;
			input.detachLabel();
		}
	}

	public PlayerTextdraw getLabelTextdraw() {
		return playerTextdraw;
	}

	public Input getInput() {
		return input;
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
}
