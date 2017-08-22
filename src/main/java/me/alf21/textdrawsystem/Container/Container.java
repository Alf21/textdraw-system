package me.alf21.textdrawsystem.container;

import me.alf21.textdrawsystem.TextdrawSystem;
import me.alf21.textdrawsystem.content.components.Component;
import me.alf21.textdrawsystem.content.components.ComponentData;
import me.alf21.textdrawsystem.content.components.bar.Bar;
import me.alf21.textdrawsystem.content.components.bar.BarInterface;
import me.alf21.textdrawsystem.content.components.button.Button;
<<<<<<< HEAD
import me.alf21.textdrawsystem.content.components.clickableTextdraw.ClickableTextdraw;
=======
>>>>>>> bcb4a165b45f56cd0365363c3e448c63d534149e
import me.alf21.textdrawsystem.content.components.input.Input;
import me.alf21.textdrawsystem.content.components.input.InputType;
import me.alf21.textdrawsystem.content.components.list.List;
import me.alf21.textdrawsystem.dialogs.Dialog;
import me.alf21.textdrawsystem.player.PlayerData;
import me.alf21.textdrawsystem.utils.PlayerTextdraw;
import net.gtaun.shoebill.data.Color;
import net.gtaun.shoebill.data.Vector2D;
import net.gtaun.shoebill.object.Destroyable;
import net.gtaun.shoebill.object.Player;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by Alf21 on 14.07.2016 in the project 'textdraw-system'.
 */
public class Container implements Destroyable {

	private ArrayList<Component> components;
	private String name;
	private Player player;
	private Dialog dialog;

	protected Container(Dialog dialog, String name) {
		this.dialog = dialog;
		player = dialog.getPlayer();
		this.name = name;
		components = new ArrayList<>();
		if (!name.equals("Content")) {
			PlayerData playerLifecycle = TextdrawSystem.getInstance().getPlayerLifecycleHolder().getObject(player, PlayerData.class);
			playerLifecycle.getContainers().add(this);
		}
	}

	public static Container create(Dialog dialog, String name) {
		return new Container(dialog, name);
	}

	public static Container create(Player player, String name) {
		return new Container(TextdrawSystem.getPanel(player), name);
	}

	public static Container get(Player player, String name) {
		PlayerData playerLifecycle = TextdrawSystem.getInstance().getPlayerLifecycleHolder().getObject(player, PlayerData.class);
		for (Container container : playerLifecycle.getContainers()) {
			if (container.getName().equals(name))
				return container;
		}
		return null;
	}

	public void show() {
		components.forEach(Component::show);
	}

	public void hide() {
		components.forEach(Component::hide);
	}

	public void recreate() {
		components.forEach(Component::recreate);
	}

	@Override
	public boolean isDestroyed() {
		for (Component component : components)
			if (!component.isDestroyed())
				return false;
		return true;
	}

	@Override
	public void destroy() {
		hide();
		components.forEach(Component::destroy);
		PlayerData playerLifecycle = TextdrawSystem.getInstance().getPlayerLifecycleHolder().getObject(player, PlayerData.class);
		playerLifecycle.getContainers().remove(this);
	}

	public ComponentData getComponentData() {
		ArrayList<ComponentData> arrayList = new ArrayList<>();
		components.forEach(component -> arrayList.add(component.getComponentData()));
		return new ComponentData<>(arrayList);
	}

	public ArrayList<PlayerTextdraw> getAllPlayerTextdraws() {
		ArrayList<PlayerTextdraw> playerTextdraws = new ArrayList<>();
<<<<<<< HEAD
		components.forEach(comp -> playerTextdraws.addAll(comp.getAllPlayerTextdraws()));
=======
		components.forEach(comp -> comp.getAllPlayerTextdraws().forEach(playerTextdraws::add));
>>>>>>> bcb4a165b45f56cd0365363c3e448c63d534149e
		return playerTextdraws;
	}

	public ArrayList<PlayerTextdraw> getComponentTextdraws() {
		ArrayList<PlayerTextdraw> playerTextdraws = new ArrayList<>();
<<<<<<< HEAD
		components.forEach(component -> playerTextdraws.addAll(component.getComponentTextdraws()));
=======
		components.forEach(component -> component.getComponentTextdraws().forEach(playerTextdraws::add));
>>>>>>> bcb4a165b45f56cd0365363c3e448c63d534149e
		return playerTextdraws;
	}

	public void addList(List list) {
		if (!components.contains(list))
			components.add(list);
	}

	public void addInput(Input input) {
		if (!components.contains(input))
			components.add(input);
	}

	public void addBar(Bar bar) {
		if (!components.contains(bar))
			components.add(bar);
	}

<<<<<<< HEAD
	public void addClickableTextdraw(ClickableTextdraw clickableTextdraw) {
		if (!components.contains(clickableTextdraw))
			components.add(clickableTextdraw);
	}

=======
>>>>>>> bcb4a165b45f56cd0365363c3e448c63d534149e
	public void addButton(Button button) {
		if (!components.contains(button))
			components.add(button);
	}

	public List createList(Vector2D position, float maxWidth, float maxHeight, String name) {
		List list = List.create(this, position, maxWidth, maxHeight, name);
		getComponents().add(list);
		return list;
	}

	public Input createInput(Vector2D position, float width, String placeholder, InputType inputType, String name) {
		Input input = Input.create(this, position, width, placeholder, inputType, name);
		getComponents().add(input);
		return input;
	}

	public Input createInput(Vector2D position, float width, InputType inputType, String name) {
		Input input = Input.create(this, position, width, inputType, name);
		getComponents().add(input);
		return input;
	}

	public Input createInput(Vector2D position, String placeholder, InputType inputType, String name) {
		Input input = Input.create(this, position, placeholder, inputType, name);
		getComponents().add(input);
		return input;
	}

	public Input createInput(Vector2D position, InputType inputType, String name) {
		Input input = Input.create(this, position, inputType, name);
		getComponents().add(input);
		return input;
	}

	public Input createInput(Vector2D position, String name) {
		Input input = Input.create(this, position, name);
		getComponents().add(input);
		return input;
	}

	public Bar createBar(Vector2D position, float minHeight, float maxHeight, float minWidth, float maxWidth, Color minColor, Color maxColor, BarInterface barInterface, String name) {
		Bar bar = Bar.create(this, position, minHeight, maxHeight, minWidth, maxWidth, minColor, maxColor, barInterface, name);
		getComponents().add(bar);
		return bar;
	}

<<<<<<< HEAD
	public ClickableTextdraw createClickableTextdraw(float x, float y, float width, float height, Color color, String name) {
		ClickableTextdraw clickableTextdraw = ClickableTextdraw.create(this, x, y, width, height, color, name);
		getComponents().add(clickableTextdraw);
		return clickableTextdraw;
	}

=======
>>>>>>> bcb4a165b45f56cd0365363c3e448c63d534149e
	public Button createButton(float x, float y, String text, String name) {
		Button button = Button.create(this, x, y, text, name);
		getComponents().add(button);
		return button;
	}

	public Button createButton(float x, float y, float width, String text, String name) {
		Button button = Button.create(this, x, y, width, text, name);
		getComponents().add(button);
		return button;
	}

	public String getName() {
		return name;
	}

	public Player getPlayer() {
		return player;
	}

	public Dialog getDialog() {
		return dialog;
	}





	public ArrayList<Component> getComponents() {
		return components;
	}

	public <E extends Component> ArrayList<Component> getComponents(Class<E> compClass) {
		ArrayList<Component> components = new ArrayList<>();
		this.components.forEach(component -> {
			if (component.getClass() == compClass) {
				components.add(component);
			}
		});
		return components;
	}

	public Component getComponent(net.gtaun.shoebill.object.PlayerTextdraw playerTextdraw) {
		for (Component component : components) {
			for (PlayerTextdraw playerTextdraws : component.getAllPlayerTextdraws()) {
				if (playerTextdraws.isPlayerTextdraw(playerTextdraw))
					return component;
			}
		}
		return null;
	}

	public <E extends Component> Component getComponent(Class<E> compClass, net.gtaun.shoebill.object.PlayerTextdraw playerTextdraw) {
		for (Component component : components) {
			if (component.getClass() == compClass)
				for (PlayerTextdraw playerTextdraws : component.getAllPlayerTextdraws()) {
					if (playerTextdraws.isPlayerTextdraw(playerTextdraw))
						return component;
				}
		}
		return null;
	}

	public Component getComponent(String name) {
		for (Component component : components) {
			if(component.getName().equals(name))
				return component;
		}
		return null;
	}

	public <E extends Component> Component getComponent(Class<E> compClass, String name) {
		for (Component component : components) {
			if (component.getClass() == compClass)
				if(component.getName().equals(name))
					return component;
		}
		return null;
	}

	public boolean hasMarkedComponents() {
		for (Component component : components) {
			if (component.isRequired())
				if(!component.isFilled())
					return true;
		}
		return false;
	}

	public <E extends Component> boolean hasMarkedComponents(Class<E> compClass) {
		for (Component component : components) {
			if (component.getClass() == compClass)
				if (component.isRequired())
					if(!component.isFilled())
						return true;
		}
		return false;
	}

	public ArrayList<Component> getMarkedComponents() {
		ArrayList<Component> markedComponents = new ArrayList<>();
		markedComponents.addAll(components.stream().filter(component -> component.isRequired() && !component.isFilled()).collect(Collectors.toList()));
		return markedComponents;
	}

	public <E extends Component> ArrayList<Component> getMarkedComponents(Class<E> compClass) {
		ArrayList<Component> markedComponents = new ArrayList<>();
		markedComponents.addAll(components.stream().filter(component -> component.getClass() == compClass && component.isRequired() && !component.isFilled()).collect(Collectors.toList()));
		return markedComponents;
	}

	public ComponentData getComponentData(String name) {
		for(Component component : getComponents()) {
			if(component.getName().equals(name))
				return component.getComponentData();
		}
		return null;
	}
}
