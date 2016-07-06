package me.alf21.textdrawsystem.content;

import me.alf21.textdrawsystem.content.components.list.List;
import me.alf21.textdrawsystem.content.components.list.ListItem;
import me.alf21.textdrawsystem.dialogs.Dialog;
import me.alf21.textdrawsystem.content.components.Component;
import me.alf21.textdrawsystem.content.components.ComponentData;
import me.alf21.textdrawsystem.content.pages.Page;
import me.alf21.textdrawsystem.dialogs.styles.Process;
import me.alf21.textdrawsystem.panelDialog.PanelDialog;
import me.alf21.textdrawsystem.utils.PlayerTextdraw;
import net.gtaun.shoebill.data.Color;
import net.gtaun.shoebill.object.Destroyable;
import net.gtaun.shoebill.object.Player;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Alf21 on 26.02.2016 in the project 'textdraw-system'.
 */
public class Content implements Destroyable {

	private Dialog dialog;
	private PlayerTextdraw contentText, contentBackground;
	private ArrayList<Page> pages;
	private ArrayList<Component> components;
	private Page currentPage;
	private boolean showed;

	public Content(Dialog dialog, PlayerTextdraw contentBackground, PlayerTextdraw contentText) {
		this.dialog = dialog;
		this.contentBackground = contentBackground;
		this.contentText = contentText;
		this.pages = new ArrayList<>();
		this.components = new ArrayList<>();
		showed = false;
	}

	public void show() {
		if (dialog.getPanelDialog() != null && !dialog.getPanelDialog().isShowed())
			dialog.getPanelDialog().show();
		else initShow();
	}

	public void show(ArrayList<Component> addons) {
		show();
		addons.forEach(Component::show);
	}

	public void showFromDialog(ArrayList<Component> addons) {
		initShow();
		addons.forEach(Component::show);
	}

	private void initShow() {
		showed = true;

		contentBackground.show();
		contentText.show();
		if (currentPage != null) { //TODO hide the others
			if (currentPage.isDestroyed())
				currentPage.recreate();
			else currentPage.hide();
			currentPage.show();
		}
		components.forEach(Component::show);
	}

	public void hide() {
		if (dialog.getPanelDialog() != null)
			dialog.getPanelDialog().hideFromPanel();
		initHide();
	}

	private void initHide() {
		showed = false;

		if (currentPage != null)
			currentPage.hide();
		components.forEach(Component::hide);
		contentText.hide();
		contentBackground.hide();
	}

	public void hideFromDialog() {
		initHide();
	}

	public void setText(String text) {
		contentText.setText(text == null || text.replaceAll(" ", "").isEmpty() ? "_" : text);
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public void destroy() {
		showed = false;

		pages.forEach(Page::destroy);
		components.forEach(Component::destroy);
		contentBackground.destroy();
		contentText.destroy();
	}

	@Override
	public boolean isDestroyed() {
		for (Page page : pages) {
			if (!page.isDestroyed())
				return false;
		}
		for (Component component : components) {
			if (!component.isDestroyed())
				return false;
		}
		return !(!contentBackground.isDestroyed() || !contentText.isDestroyed());
	}

	public Dialog getDialog() {
		return dialog;
	}

	public ArrayList<Component> getComponents() {
		ArrayList<Component> components = new ArrayList<>(this.components);
		pages.forEach(page -> page.getComponents().forEach(components::add));
		if (dialog.getPanelDialog() != null)
			dialog.getPanelDialog().getComponents().forEach(components::add);
		return components;
	} //TODO add pages and dialoges

	public Component getComponent(net.gtaun.shoebill.object.PlayerTextdraw playerTextdraw) {
		for (Page page : pages) {
			for (Component component : page.getComponents()) {
				for (PlayerTextdraw playerTextdraws : component.getAllPlayerTextdraws()) {
					if (playerTextdraws.isPlayerTextdraw(playerTextdraw))
						return component;
				}
			}
		}
		if (dialog.getPanelDialog() != null) {
			for (Component component : dialog.getPanelDialog().getComponents()) {
				for (PlayerTextdraw playerTextdraws : component.getAllPlayerTextdraws()) {
					if (playerTextdraws.isPlayerTextdraw(playerTextdraw))
						return component;
				}
			}
		}
		for (Component component : components) {
			for (PlayerTextdraw playerTextdraws : component.getAllPlayerTextdraws()) {
				if (playerTextdraws.isPlayerTextdraw(playerTextdraw))
					return component;
			}
		}
		return null;
	}

	public Component getComponent(String name) {
		for (Page page : pages) {
			for (Component component : page.getComponents()) {
				if (component.getName().equals(name))
					return component;
			}
		}
		if (dialog.getPanelDialog() != null) {
			for (Component component : dialog.getPanelDialog().getComponents()) {
				if (component.getName().equals(name))
					return component;
			}
		}
		for (Component component : components) {
			if(component.getName().equals(name))
				return component;
		}
		return null;
	}

	public boolean hasMarkedComponents() {
		for (Page page : pages) {
			for (Component component : page.getComponents()) {
				if (component.isRequired())
					if(!component.isFilled())
						return true;
			}
		}
		if (dialog.getPanelDialog() != null) {
			for (Component component : dialog.getPanelDialog().getComponents()) {
				if (component.isRequired())
					if(!component.isFilled())
						return true;
			}
		}
		for (Component component : components) {
			if (component.isRequired())
				if(!component.isFilled())
					return true;
		}
		return false;
	}

	public ArrayList<Component> getMarkedComponents() {
		ArrayList<Component> markedComponents = new ArrayList<>();
		for (Page page : pages) {
			page.getComponents().stream().filter(component -> component.isRequired() && !component.isFilled()).forEach(markedComponents::add);
		}
		if (dialog.getPanelDialog() != null) {
			dialog.getPanelDialog().getComponents().stream().filter(component -> component.isRequired() && !component.isFilled()).forEach(markedComponents::add);
		}
		markedComponents.addAll(components.stream().filter(component -> component.isRequired() && !component.isFilled()).collect(Collectors.toList()));
		return markedComponents;
	}

	public PlayerTextdraw getContentBackground() {
		return contentBackground;
	}

	public Page getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Page currentPage) {
		if(!pages.contains(currentPage))
			pages.add(currentPage);
		if (isShowed() && this.currentPage != null && !this.currentPage.isDestroyed())
			this.currentPage.hide();
		this.currentPage = currentPage;

		if (isShowed()) {
			pages.forEach(page -> {
				if (page == currentPage && page.isDestroyed())
					page.recreate();
				else if (page != currentPage && !page.isDestroyed()) {
					page.getPageInterface().onLeave(this, page);
					if (!page.isDestroyed()) {
						page.hide();
						page.destroy();
					}
				}
			});
			currentPage.getPageInterface().onReach(this, currentPage);

			currentPage.show();
		}
	}

	public void setCurrentPage(int index) {
		Page currentPage = getPages().get(index);
		setCurrentPage(currentPage);
	}

	public void nextPage() {
		if (currentPage != null) {
			if (!currentPage.getPageInterface().onLeave(this, currentPage))
				return;
			submit();
			if (!currentPage.isDestroyed()) {
				currentPage.hide();
				currentPage.destroy();
			}
			int i = 1;
			for (Page page : getPages()) {
				if (page == getCurrentPage())
					break;
				i++;
			}
			setCurrentPage(getPages().get(i));
		}
	}

	public void submit() {
		if (currentPage != null && currentPage.getComponents() != null && currentPage.getPageInterface() != null) {
			currentPage.getPageInterface().onSubmit(this, currentPage);
			try {
				currentPage.getComponents().stream().filter(component -> component instanceof List).forEach(component -> ((List) component).getSelectedListItems().forEach(ListItem::onSelect));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void previousPage() {
		if (currentPage != null && currentPage.getComponents() != null) {
			if (!currentPage.getPageInterface().onLeave(this, currentPage))
				return;
			currentPage.getComponents().stream().filter(component -> component instanceof List).forEach(component -> ((List) component).setSelectedListItems(new ArrayList<>()));
			if (!currentPage.isDestroyed()) {
				currentPage.hide();
				currentPage.destroy();
			}
			int i = -1;
			for (Page page : getPages()) {
				if (page == getCurrentPage())
					break;
				i++;
			}
			setCurrentPage(getPages().get(i));
		}
	}

	public int getPageValue(Page page) {
		int i = 0;
		for (Page pages : getPages()) {
			if (page == pages)
				return i;
			i++;
		}
		return 0;
	}

	private ArrayList<Page> getPages() {
		return pages;
	}

	public int getPagesAmount() {
		return getPages().size();
	}

	public void addPage(Page page) {
		page.setContent(this);
		getPages().add(page);
	}

	public void removePage(Page page) {
		page.setContent(null);
		getPages().remove(page);
	}

	/**
	 * get the data of the first component with given name
	 * @param name the name of the component
	 * @return the data of the component
	 */
	public ComponentData getComponentData(String name) {
		for(Page page : pages) {
			for(Component component : page.getComponents()) {
				if(component.getName().equals(name))
					return component.getComponentData();
			}
		}
		if (dialog.getPanelDialog() != null) {
			for(Component component : dialog.getPanelDialog().getComponents()) {
				if(component.getName().equals(name))
					return component.getComponentData();
			}
		}
		for(Component component : components) {
			if(component.getName().equals(name))
				return component.getComponentData();
		}
		return null;
	}

	public boolean isShowed() {
		return showed;
	}

	public void clear() {
		getComponents().forEach(Component::hide);
		getComponents().forEach(Component::destroy);
	}
}
