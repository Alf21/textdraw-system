package me.alf21.textdrawsystem.content.components;

/**
 * Created by Alf21 on 01.03.2016 in the project 'textdraw-system'.
 */
public class ComponentData<E> {

	private E data;

	public ComponentData(E data) {
		this.data = data;
	}

	private E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}

	public E get() {
		return data;
	}
}
