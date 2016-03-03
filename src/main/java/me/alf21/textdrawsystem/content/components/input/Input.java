package me.alf21.textdrawsystem.content.components.input;

import me.alf21.textdrawsystem.TextdrawSystem;
import me.alf21.textdrawsystem.dialogs.panel.Panel;
import me.alf21.textdrawsystem.content.Content;
import me.alf21.textdrawsystem.content.components.Component;
import me.alf21.textdrawsystem.content.components.ComponentData;
import me.alf21.textdrawsystem.utils.PlayerTextdraw;
import net.gtaun.shoebill.common.dialog.InputDialog;
import net.gtaun.shoebill.constant.TextDrawFont;
import net.gtaun.shoebill.data.Color;
import net.gtaun.shoebill.data.Vector2D;
import net.gtaun.shoebill.object.Player;

import java.util.ArrayList;

/**
 * Created by Alf21 on 27.02.2016.
 */
public class Input extends Component {

	private Player player;
	private Content content;
	private String placeholder;
	private InputLabel label;
	private PlayerTextdraw playerTextdraw;
	private InputType inputType;

	private Input(Content content, float x, float y, String placeholder, InputType inputType, String name) {
		super(name);
		player = content.getPanel().getPlayer();
		this.content = content;
		this.placeholder = placeholder;
		this.inputType = inputType;

		playerTextdraw = PlayerTextdraw.create(player, x, y, placeholder);
		playerTextdraw.setBackgroundColor(new Color(0,0,0,255));
		playerTextdraw.setFont(TextDrawFont.get(1));
		playerTextdraw.setLetterSize(0.280000f, 1.000000f);
		playerTextdraw.setColor(new Color(255,255,255,255));
		playerTextdraw.setOutlineSize(0);
		playerTextdraw.setProportional(true);
		playerTextdraw.setShadowSize(0);
		playerTextdraw.setUseBox(true);
		playerTextdraw.setBoxColor(content.getPanel().getInputColor());
		playerTextdraw.setTextSize(x+125f, 10.000000f);
		playerTextdraw.setSelectable(true);
	}

	public static Input create(Content content, float x, float y, String placeholder, InputType inputType, String name) {
		if(placeholder.isEmpty() || placeholder.replaceAll(" ", "").equals(""))
			placeholder = Panel.TEXT_EMPTY;
		return new Input(content, x, y, placeholder, inputType, name);
	}

	public static Input create(Content content, float x, float y, String placeholder, String name) { return create(content, x, y, placeholder, InputType.TEXT, name); }

	public static Input create(Content content, float x, float y, String name) { return create(content, x, y, Panel.TEXT_EMPTY, InputType.TEXT, name); }

	public static Input create(Content content, Vector2D vector2D, String name) { return create(content, vector2D.getX(), vector2D.getY(), Panel.TEXT_EMPTY, InputType.TEXT, name); }

	public static Input create(Content content, Vector2D vector2D, String placeholder, String name) { return create(content, vector2D.getX(), vector2D.getY(), placeholder, InputType.TEXT, name); }

	public static Input create(Content content, Vector2D vector2D, String placeholder, InputType inputType, String name) { return create(content, vector2D.getX(), vector2D.getY(), placeholder, inputType, name); }

	public InputLabel attachLabel(String text) {
		destroyLabel();
		label = InputLabel.create(player, text, this, getName() + "_label");
		return label;
	}

	public void attachLabel(InputLabel label) {
		destroyLabel();
		label.attach(this);
	}

	public InputLabel detachLabel() {
		InputLabel label = getLabel();
		if (label != null) {
			if (label.getInput() != null)
				label.detach();
			this.label = null;
		}
		return label;
	}

	public InputLabel getLabel() {
		return label;
	}

	public void destroyLabel() {
		if(label != null && !label.isDestroyed())
			label.destroy();
	}

	@Override
	public void destroy() {
		if(label != null && !label.isDestroyed())
			label.destroy();
		playerTextdraw.destroy();
	}

	@Override
	public boolean isDestroyed() {
		return !(!playerTextdraw.isDestroyed() || label != null && !label.isDestroyed());
	}

	@Override
	public void recreate() {
		if(label != null)
			label.recreate();
		playerTextdraw.recreate();
	}

	@Override
	public void show() {
		playerTextdraw.show();
		if(label != null && !label.isDestroyed())
			label.show();
	}

	@Override
	public void hide() {
		if(label != null && !label.isDestroyed())
			label.hide();
		playerTextdraw.hide();
	}

	@Override
	public ArrayList<PlayerTextdraw> getPlayerTextdraws() {
		ArrayList<PlayerTextdraw> playerTextdraws = new ArrayList<>();
		if(label != null)
			playerTextdraws = label.getPlayerTextdraws();
		playerTextdraws.add(playerTextdraw);
		return playerTextdraws;
	}

	@Override
	public void onClick() {
		String string = "";
		if(isRequired())
			string = " - {FF0000}REQUIRED";
		InputDialog.create(player, TextdrawSystem.getInstance().getEventManagerInstance())
				.caption("{FFFF00}" + getInputType().getName() + " {FF8A05}format" + string)
				.message("To fill the conditions, you need to enter in a valid format.\nExample: " + InputType.getCondition(getInputType()))
				.buttonCancel("Cancel")
				.buttonOk("Okay")
				.onClickOk((inputDialog, s) -> {
					if(InputType.matchesCondition(getInputType(), s) && !s.isEmpty() && !s.replaceAll(" ", "").isEmpty()) {
						if(isMarked())
							unmark();
						playerTextdraw.setText(s);
					}
					else if (!InputType.matchesCondition(getInputType(), playerTextdraw.getText()) || !isRequired()) { //TODO !isRequired() OR after every not required input an clear text button
						if(isRequired() && !isMarked())
							mark();
						playerTextdraw.setText(getPlaceholder());
					}
				})
				.build()
				.show();
	}

	@Override
	public boolean isRequired() {
		return super.isRequired();
	}

	@Override
	public void toggleRequired(boolean required) {
		super.toggleRequired(required);
		if (isRequired()) {
			if(InputType.matchesCondition(getInputType(), playerTextdraw.getText())
					&& !playerTextdraw.getText().equals(getPlaceholder())) {
				if (isMarked())
					unmark();
			}
			else {
				if (!isMarked())
					mark();
			}
		}
		else {
			if (isMarked())
				unmark();
		}
	}

	@Override
	public void mark() {
		super.mark();
		playerTextdraw.setBoxColor(content.getPanel().getMarkerColor());
		if(playerTextdraw.isShowed()) {
			playerTextdraw.hide();
			playerTextdraw.show();
		}
	}

	@Override
	public boolean isMarked() {
		return super.isMarked();
	}

	@Override
	public void unmark() {
		super.unmark();
		playerTextdraw.setBoxColor(content.getPanel().getInputColor());
		if(playerTextdraw.isShowed()) {
			playerTextdraw.hide();
			playerTextdraw.show();
		}
	}

	public PlayerTextdraw getInputTextdraw() {
		return playerTextdraw;
	}

	public InputType getInputType() {
		return inputType;
	}

	public void setInputTypes(InputType inputType) {
		this.inputType = inputType;
	}

	public String getPlaceholder() {
		return placeholder;
	}

	@Override
	public boolean isFilled() {
		return !playerTextdraw.getText().equals(getPlaceholder());
	}

	@Override
	public ComponentData getComponentData() {
		return new ComponentData(playerTextdraw.getText());
	}
}
