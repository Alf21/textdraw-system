package me.alf21.textdrawsystem.content.components.input;

import me.alf21.textdrawsystem.TextdrawSystem;
import me.alf21.textdrawsystem.container.Container;
import me.alf21.textdrawsystem.content.components.Component;
import me.alf21.textdrawsystem.content.components.ComponentAlignment;
import me.alf21.textdrawsystem.content.components.ComponentData;
import me.alf21.textdrawsystem.dialogs.Dialog;
//import me.alf21.textdrawsystem.inputDialog.InputDialog;
import me.alf21.textdrawsystem.utils.PlayerTextdraw;
import net.gtaun.shoebill.common.dialog.InputDialog;
import net.gtaun.shoebill.constant.TextDrawFont;
import net.gtaun.shoebill.data.Color;
import net.gtaun.shoebill.data.Vector2D;

import java.util.ArrayList;

/**
 * Created by Alf21 on 27.02.2016 in the project 'textdraw-system'.
 */
public class Input extends Component {

	private String placeholder;
	private PlayerTextdraw playerTextdraw;
	private InputType inputType;

	private Input(Container container, float x, float y, float width, String placeholder, InputType inputType, String name) {
		super(container, ComponentAlignment.TOP_LEFT, name);
		if(placeholder.replaceAll(" ", "").isEmpty())
			placeholder = Dialog.TEXT_EMPTY;
		this.placeholder = placeholder;
		this.inputType = inputType;
		if(Float.isNaN(width))
			width = 125f;

		playerTextdraw = PlayerTextdraw.create(super.getPlayer(), x, y, placeholder);
		playerTextdraw.setBackgroundColor(new Color(0,0,0,255));
		playerTextdraw.setFont(TextDrawFont.get(1));
		playerTextdraw.setLetterSize(0.280000f, 1.000000f);
		playerTextdraw.setColor(new Color(255,255,255,255));
		playerTextdraw.setOutlineSize(0);
		playerTextdraw.setProportional(true);
		playerTextdraw.setShadowSize(0);
		playerTextdraw.setUseBox(true);
		playerTextdraw.setBoxColor(container.getDialog().getInputColor());
		playerTextdraw.setTextSize(x-4+width, 10.000000f);
		playerTextdraw.setSelectable(true);

		playerTextdraw.setAllText(placeholder);
	}

	public static Input create(Container container, float x, float y, float width, String placeholder, InputType inputType, String name) {
		return new Input(container, x, y, width, placeholder, inputType, name);
	}

	public static Input create(Container container, float x, float y, String placeholder, String name) {
		return create(container, x, y, Float.NaN, placeholder, InputType.TEXT, name);
	}

	public static Input create(Container container, float x, float y, String name) {
		return create(container, x, y, Float.NaN, Dialog.TEXT_EMPTY, InputType.TEXT, name);
	}

	public static Input create(Container container, Vector2D vector2D, String name) {
		return create(container, vector2D.x, vector2D.y, Float.NaN, Dialog.TEXT_EMPTY, InputType.TEXT, name);
	}

	public static Input create(Container container, Vector2D vector2D, InputType inputType, String name) {
		return create(container, vector2D.x, vector2D.y, Float.NaN, Dialog.TEXT_EMPTY, inputType, name);
	}

	public static Input create(Container container, Vector2D vector2D, String placeholder, String name) {
		return create(container, vector2D.x, vector2D.y, Float.NaN, placeholder, InputType.TEXT, name);
	}

	public static Input create(Container container, Vector2D vector2D, String placeholder, InputType inputType, String name) {
		return create(container, vector2D.x, vector2D.y, Float.NaN, placeholder, inputType, name);
	}

	public static Input create(Container container, Vector2D vector2D, float width, InputType inputType, String name) {
		return create(container, vector2D.x, vector2D.y, width, Dialog.TEXT_EMPTY, inputType, name);
	}

	public static Input create(Container container, Vector2D vector2D, float width, String placeholder, InputType inputType, String name) {
		return create(container, vector2D.x, vector2D.y, width, placeholder, inputType, name);
	}

	@Override
	public void destroy() {
		super.destroy();
		playerTextdraw.destroy();
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
		super.hide();
		playerTextdraw.hide();
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
	public void onClick(net.gtaun.shoebill.entities.PlayerTextdraw clickedPlayerTextdraw) {
		String string = "";
		if (isRequired()) string = " - {FF0000}REQUIRED";
		InputDialog.create(TextdrawSystem.getInstance().getEventManagerInstance(), inputType == InputType.PASSWORD)
				.caption("{FFFF00}" + getInputType().getName() + " {FF8A05}format" + string)
				.message("To fill the conditions, you need to enter in a valid format.\nExample: " + InputType.getCondition(getInputType()))
				.buttonCancel("Cancel")
				.buttonOk("Okay")
				.onClickOk((inputDialog, p, s) -> {
					if (InputType.matchesCondition(getInputType(), s) && !s.isEmpty() && !s.replaceAll(" ", "").isEmpty()) {
						if (isMarked()) unmark();
						playerTextdraw.setAllText(s);
						if (inputType == InputType.PASSWORD) playerTextdraw.setText("xxxxxxxx");
					} else if (!InputType.matchesCondition(getInputType(), playerTextdraw.getAllText()) || !isRequired()) {
						if (isRequired() && !isMarked()) mark();
						playerTextdraw.setAllText(getPlaceholder());
					}
				})
				.onCancel((inputDialog, p) -> {
					if (!InputType.matchesCondition(getInputType(), playerTextdraw.getAllText()) || !isRequired()) {
						if (isRequired() && !isMarked()) mark();
						playerTextdraw.setAllText(getPlaceholder());
					}
				})
				.build()
				.show(super.getPlayer());
		//TODO if is not needed a InputDialog (bcus of desc): Enter input without InputDialog direct into the input
	}

	@Override
	public boolean isRequired() {
		return super.isRequired();
	}

	@Override
	public void toggleRequired(boolean required) {
		super.toggleRequired(required);
		if (isRequired()) {
			if(InputType.matchesCondition(getInputType(), playerTextdraw.getAllText())
					&& !playerTextdraw.getAllText().equals(getPlaceholder())) {
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
		playerTextdraw.setBoxColor(super.getContainer().getDialog().getMarkerColor());
		if(playerTextdraw.isShown()) {
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
		playerTextdraw.setBoxColor(super.getContainer().getDialog().getInputColor());
		if(playerTextdraw.isShown()) {
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
		return !playerTextdraw.getAllText().equals(getPlaceholder());
	}

	@Override
	public ComponentData getComponentData() {
		return new ComponentData<>(playerTextdraw.getAllText());
	}

	@Override
	public Vector2D getComponentPosition() {
		return playerTextdraw.getPosition();
	}
}
