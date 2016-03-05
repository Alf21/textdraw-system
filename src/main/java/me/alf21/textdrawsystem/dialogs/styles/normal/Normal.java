package me.alf21.textdrawsystem.dialogs.styles.normal;

import me.alf21.textdrawsystem.dialogs.Dialog;
import me.alf21.textdrawsystem.content.Content;
import me.alf21.textdrawsystem.dialogs.styles.DialogStyle;
import me.alf21.textdrawsystem.dialogs.styles.Process;
import me.alf21.textdrawsystem.utils.PlayerTextdraw;
import net.gtaun.shoebill.constant.TextDrawAlign;
import net.gtaun.shoebill.constant.TextDrawFont;
import net.gtaun.shoebill.data.Color;
import net.gtaun.shoebill.data.Vector2D;
import net.gtaun.shoebill.object.Player;

/**
 * Created by Alf21 on 26.02.2016.
 */
public class Normal extends DialogStyle {
	private static PlayerTextdraw panelBackground;
	private static PlayerTextdraw title;
	private static PlayerTextdraw titleBackground;
	private static PlayerTextdraw leftButtonBackground;
	private static PlayerTextdraw rightButtonBackground;
	private static PlayerTextdraw leftButton;
	private static PlayerTextdraw rightButton;
	private static PlayerTextdraw contentBackground;
	private static PlayerTextdraw contentText;
	private static PlayerTextdraw closeIcon;
	private static Process process;

	@Override
	public void create(Dialog dialog) {
		Player player = dialog.getPlayer();

		panelBackground = PlayerTextdraw.create(player, 320.000000f, 120.000000f, "_");
		panelBackground.setAlignment(TextDrawAlign.get(2));
		panelBackground.setBackgroundColor(new Color(0, 0, 0, 255));
		panelBackground.setFont(TextDrawFont.get(1));
		panelBackground.setLetterSize(new Vector2D(0.500000f, 22.000000f));
		panelBackground.setColor(new Color(255, 255, 255, 255));
		panelBackground.setOutlineSize(0);
		panelBackground.setProportional(true);
		panelBackground.setShadowSize(1);
		panelBackground.setUseBox(true);
		panelBackground.setBoxColor(new Color(0, 0, 0, 50));
		panelBackground.setTextSize(new Vector2D(0.000000f, 410.000000f));
		panelBackground.setSelectable(false);

		title = PlayerTextdraw.create(player, 320.000000f, 120.000000f, "_");
		title.setAlignment(TextDrawAlign.get(2));
		title.setBackgroundColor(new Color(0, 0, 0, 255));
		title.setFont(TextDrawFont.get(2));
		title.setLetterSize(new Vector2D(0.239999f, 1.000000f));
		title.setColor(new Color(255, 255, 255, 255));
		title.setOutlineSize(0);
		title.setProportional(true);
		title.setShadowSize(1);
		title.setUseBox(true);
		title.setBoxColor(new Color(0, 0, 0, 100));
		title.setTextSize(new Vector2D(0.000000f, 410.000000f));
		title.setSelectable(false);

		titleBackground = PlayerTextdraw.create(player, 320.000000f, 140.000000f, "_");
		titleBackground.setAlignment(TextDrawAlign.get(2));
		titleBackground.setBackgroundColor(new Color(0, 0, 0, 255));
		titleBackground.setFont(TextDrawFont.get(1));
		titleBackground.setLetterSize(new Vector2D(0.500000f, 19.000000f));
		titleBackground.setColor(new Color(255, 255, 255, 255));
		titleBackground.setOutlineSize(0);
		titleBackground.setProportional(true);
		titleBackground.setShadowSize(1);
		titleBackground.setUseBox(true);
		titleBackground.setBoxColor(new Color(0, 0, 0, 100));
		titleBackground.setTextSize(new Vector2D(0.000000f, 400.000000f));
		titleBackground.setSelectable(false);

		leftButtonBackground = PlayerTextdraw.create(player, 170.000000f, 292.000000f, "_");
		leftButtonBackground.setAlignment(TextDrawAlign.get(2));
		leftButtonBackground.setBackgroundColor(new Color(0, 0, 0, 255));
		leftButtonBackground.setFont(TextDrawFont.get(1));
		leftButtonBackground.setLetterSize(new Vector2D(0.500000f, 1.599998f));
		leftButtonBackground.setColor(new Color(255, 255, 255, 255));
		leftButtonBackground.setOutlineSize(0);
		leftButtonBackground.setProportional(true);
		leftButtonBackground.setShadowSize(1);
		leftButtonBackground.setUseBox(true);
		leftButtonBackground.setBoxColor(new Color(255, 255, 255, 50));
		leftButtonBackground.setTextSize(new Vector2D(0.000000f, 90.000000f));
		leftButtonBackground.setSelectable(false);

		rightButtonBackground = PlayerTextdraw.create(player, 470.000000f, 292.000000f, "_");
		rightButtonBackground.setAlignment(TextDrawAlign.get(2));
		rightButtonBackground.setBackgroundColor(new Color(0, 0, 0, 255));
		rightButtonBackground.setFont(TextDrawFont.get(1));
		rightButtonBackground.setLetterSize(new Vector2D(0.500000f, 1.599998f));
		rightButtonBackground.setColor(new Color(255, 255, 255, 255));
		rightButtonBackground.setOutlineSize(0);
		rightButtonBackground.setProportional(true);
		rightButtonBackground.setShadowSize(1);
		rightButtonBackground.setUseBox(true);
		rightButtonBackground.setBoxColor(new Color(255, 255, 255, 50));
		rightButtonBackground.setTextSize(new Vector2D(0.000000f, 90.000000f));
		rightButtonBackground.setSelectable(false);

		leftButton = PlayerTextdraw.create(player, 170.000000f, 295.000000f, "_");
		leftButton.setAlignment(TextDrawAlign.get(2));
		leftButton.setBackgroundColor(new Color(0, 0, 0, 255));
		leftButton.setFont(TextDrawFont.get(1));
		leftButton.setLetterSize(new Vector2D(0.500000f, 1.000000f));
		leftButton.setColor(new Color(255, 255, 255, 255));
		leftButton.setUseBox(true);
		leftButton.setBoxColor(new Color(0, 0, 0, 0));
		leftButton.setTextSize(new Vector2D(10.000000f, 90.000000f));
		leftButton.setOutlineSize(0);
		leftButton.setProportional(true);
		leftButton.setShadowSize(1);
		leftButton.setSelectable(true);

		rightButton = PlayerTextdraw.create(player, 470.000000f, 295.000000f, "_");
		rightButton.setAlignment(TextDrawAlign.get(2));
		rightButton.setBackgroundColor(new Color(0, 0, 0, 255));
		rightButton.setFont(TextDrawFont.get(1));
		rightButton.setLetterSize(new Vector2D(0.500000f, 1.000000f));
		rightButton.setColor(new Color(255, 255, 255, 255));
		rightButton.setUseBox(true);
		rightButton.setBoxColor(new Color(0, 0, 0, 0));
		rightButton.setTextSize(new Vector2D(10.000000f, 90.000000f));
		rightButton.setOutlineSize(0);
		rightButton.setProportional(true);
		rightButton.setShadowSize(1);
		rightButton.setSelectable(true);

		contentBackground = PlayerTextdraw.create(player, 320.000000f, 146.000000f, "_");
		contentBackground.setAlignment(TextDrawAlign.get(2));
		contentBackground.setBackgroundColor(new Color(0, 0, 0, 255));
		contentBackground.setFont(TextDrawFont.get(1));
		contentBackground.setLetterSize(new Vector2D(0.500000f, 15.000000f));
		contentBackground.setColor(new Color(255, 255, 255, 255));
		contentBackground.setOutlineSize(0);
		contentBackground.setProportional(true);
		contentBackground.setShadowSize(1);
		contentBackground.setUseBox(true);
		contentBackground.setBoxColor(new Color(255, 255, 255, 50));
		contentBackground.setTextSize(new Vector2D(0.000000f, 390.000000f));
		contentBackground.setSelectable(false);

		contentText = PlayerTextdraw.create(player, 130.000000f, 150.000000f, "_");
		contentText.setBackgroundColor(new Color(0, 0, 0, 255));
		contentText.setFont(TextDrawFont.get(1));
		contentText.setLetterSize(new Vector2D(0.500000f, 1.000000f));
		contentText.setColor(new Color(255, 255, 255, 255));
		contentText.setOutlineSize(0);
		contentText.setProportional(true);
		contentText.setShadowSize(1);
		contentText.setUseBox(true);
		contentText.setBoxColor(new Color(0, 0, 0, 0));
		contentText.setTextSize(new Vector2D(510.000000f, 0.000000f));
		contentText.setSelectable(false);

		closeIcon = PlayerTextdraw.create(player, 520.000000f, 120.000000f, "x");
		closeIcon.setAlignment(TextDrawAlign.get(2));
		closeIcon.setBackgroundColor(new Color(0, 0, 0, 255));
		closeIcon.setFont(TextDrawFont.get(2));
		closeIcon.setLetterSize(new Vector2D(0.300000f, 1.000000f));
		closeIcon.setColor(new Color(255, 255, 255, 255));
		closeIcon.setOutlineSize(0);
		closeIcon.setProportional(true);
		closeIcon.setShadowSize(0);
		closeIcon.setUseBox(true);
		closeIcon.setBoxColor(new Color(150, 0, 0, 255));
		closeIcon.setTextSize(new Vector2D(10.000000f, 10.000000f));
		closeIcon.setSelectable(true);

		process = NormalProcess.create(dialog);

		dialog.setCloseIcon(closeIcon);
		dialog.setContent(new Content(dialog, contentBackground, contentText));
		dialog.setLeftButton(leftButton);
		dialog.setLeftButtonBackground(leftButtonBackground);
		dialog.setPanelBackground(panelBackground);
		dialog.setProcess(process);
		dialog.setRightButton(rightButton);
		dialog.setRightButtonBackground(rightButtonBackground);
		dialog.setTitle(title);
		dialog.setTitleBackground(titleBackground);
		dialog.setHoverColor(new Color(150,0,0,255));
		dialog.setInputColor(new Color(255,255,255,50));
		dialog.setMarkerColor(new Color(150,0,0,50));
	}
}
