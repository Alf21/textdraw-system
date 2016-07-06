package me.alf21.textdrawsystem.content.components;

import java.util.ArrayList;

/**
 * Created by Alf21 on 12.06.2016 in the project 'textdraw-system'.
 */
public class ComponentDataCollection {

	private ArrayList<Component> components;

	public ComponentDataCollection(ArrayList<Component> components) {
		this.components = components;
	}

	public ArrayList<Component> getComponents() {
		return components;
	}

	public ComponentData get(String name) {
		for(Component component : this.getComponents()) {
			if(component.getName().equals(name))
				return component.getComponentData();
		}
		return null;
	}
}
