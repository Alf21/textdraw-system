package me.alf21.textdrawsystem.content.components;

import me.alf21.textdrawsystem.utils.PlayerTextdraw;
import net.gtaun.shoebill.object.Destroyable;

import java.util.ArrayList;

/**
 * Created by Alf21 on 27.02.2016.
 */
public abstract class Component implements Destroyable {

	private boolean required, marked;
	private ArrayList<PlayerTextdraw> playerTextdraws = new ArrayList<>(); //TODO default initialize
	private ComponentData componentData = new ComponentData(false);
	private String name;

	protected Component(String name) {
		this.name = name;
	}

	public void show() {
		playerTextdraws.forEach(PlayerTextdraw::show);
	}

	public void hide() {
		playerTextdraws.forEach(PlayerTextdraw::hide);
	}

	public boolean isFilled() { return true; }

	public ArrayList<PlayerTextdraw> getPlayerTextdraws() {
		return playerTextdraws;
	}

	public void recreate() { playerTextdraws.forEach(PlayerTextdraw::recreate); }

	public void onClick() { }

	public boolean isRequired() {
		return required;
	}

	public void toggleRequired(boolean required) {
		this.required = required;
	}

	public void mark() {
		marked = true;
	}

	public boolean isMarked() {
		return marked;
	}

	public void unmark() {
		marked = false;
	}

	@Override
	public void destroy() {
		playerTextdraws.forEach(PlayerTextdraw::destroy);
	}

	@Override
	public boolean isDestroyed() {
		for(PlayerTextdraw playerTextdraw : playerTextdraws) {
			if(!playerTextdraw.isDestroyed())
				return false;
		}
		return true;
	}

	public ComponentData getComponentData() {
		return componentData;
	}

	public void setComponentData(ComponentData componentData) {
		this.componentData = componentData;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
