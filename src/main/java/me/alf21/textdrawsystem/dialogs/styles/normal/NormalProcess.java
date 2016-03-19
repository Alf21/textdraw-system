package me.alf21.textdrawsystem.dialogs.styles.normal;

import me.alf21.textdrawsystem.calculations.Calculation;
import me.alf21.textdrawsystem.dialogs.Dialog;
import me.alf21.textdrawsystem.dialogs.styles.Process;
import me.alf21.textdrawsystem.utils.PlayerTextdraw;
import net.gtaun.shoebill.constant.TextDrawAlign;
import net.gtaun.shoebill.constant.TextDrawFont;
import net.gtaun.shoebill.data.Color;
import net.gtaun.shoebill.data.Vector2D;
import net.gtaun.shoebill.object.Destroyable;

/**
 * Created by Alf21 on 26.02.2016 in the project 'textdraw-system'.
 */
public class NormalProcess extends Process implements Destroyable {
	private static PlayerTextdraw processBarBackground;
	private static PlayerTextdraw processBar;
	private double process, maxProcess;

	private static float max_width = 410f;

	private NormalProcess(Dialog dialog) {
		super(dialog);
	} //TODO REMOVE THIS CLASS AND USE Bar.class AS CONTENT COMPONENT

	public static Process create(Dialog dialog) {
		processBarBackground = PlayerTextdraw.create(dialog.getPlayer(), 320.000000f, 133.500000f, "_");
		processBarBackground.setAlignment(TextDrawAlign.get(2));
		processBarBackground.setBackgroundColor(new Color(0, 0, 0, 255));
		processBarBackground.setFont(TextDrawFont.get(1));
		processBarBackground.setLetterSize(new Vector2D(0.500000f, -0.300000f));
		processBarBackground.setColor(new Color(255, 255, 255, 255));
		processBarBackground.setOutlineSize(0);
		processBarBackground.setProportional(true);
		processBarBackground.setShadowSize(1);
		processBarBackground.setUseBox(true);
		processBarBackground.setBoxColor(new Color(255, 255, 255, 50));
		processBarBackground.setTextSize(new Vector2D(0.000000f, max_width));
		processBarBackground.setSelectable(false);

		processBar = PlayerTextdraw.create(dialog.getPlayer(), 320.000000f, 133.500000f, "_");
		processBar.setAlignment(TextDrawAlign.get(2));
		processBar.setBackgroundColor(new Color(0, 0, 0, 255));
		processBar.setFont(TextDrawFont.get(1));
		processBar.setLetterSize(new Vector2D(0.500000f, -0.300000f));
		processBar.setColor(new Color(255, 255, 255, 255));
		processBar.setOutlineSize(0);
		processBar.setProportional(true);
		processBar.setShadowSize(1);
		processBar.setUseBox(true);
		processBar.setBoxColor(new Color(0, 255, 0, 255));
		processBar.setTextSize(new Vector2D(0.000000f, max_width));
		processBar.setSelectable(false);

		return new NormalProcess(dialog);
	}

	@Override
	public void show() {
		processBarBackground.show();
		processBar.show();
	}

	@Override
	public void process() {
		hide();
		processBar.setTextSize(new Vector2D(0, max_width));
		Vector2D textSize = Calculation.getWidth(processBar, maxProcess, process);
		processBar.setTextSize(textSize);
		show();
	}

	@Override
	public double getProcess() {
		return process;
	}

	@Override
	public double getMaxProcess() {
		return maxProcess;
	}

	@Override
	public void hide() {
		processBar.hide();
		processBarBackground.hide();
	}

	@Override
	public void setProcess(double process) {
		this.process = process;
	}

	@Override
	public void setMaxProcess(double maxProcess) {
		this.maxProcess = maxProcess;
	}

	@Override
	public void start() { //TODO for timer
		super.start();
	}

	@Override
	public void stop() {
		super.stop();
	}

	@Override
	public void destroy() {
		hide();
		processBarBackground.destroy();
		processBar.destroy();
	}

	@Override
	public boolean isDestroyed() {
		return !(!processBarBackground.isDestroyed() || !processBar.isDestroyed());
	}
}
