package me.alf21.textdrawsystem.content;

import me.alf21.textdrawsystem.container.Container;
import me.alf21.textdrawsystem.content.components.list.List;
import me.alf21.textdrawsystem.content.components.list.ListItem;
import me.alf21.textdrawsystem.dialogs.Dialog;
import me.alf21.textdrawsystem.content.components.Component;
import me.alf21.textdrawsystem.content.components.ComponentData;
import me.alf21.textdrawsystem.content.pages.Page;
import me.alf21.textdrawsystem.utils.PlayerTextdraw;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by Alf21 on 26.02.2016 in the project 'textdraw-system'.
 */
public class Content extends Container {

	private Dialog dialog;
	private PlayerTextdraw contentText, contentBackground;
	private ArrayList<Page> pages;
	private Page currentPage;
	private boolean showed;

	public Content(Dialog dialog, PlayerTextdraw contentBackground, PlayerTextdraw contentText) {
		super(dialog, "Content");
		this.dialog = dialog;
		this.contentBackground = contentBackground;
		this.contentText = contentText;
		this.pages = new ArrayList<>();
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
		super.getComponents().forEach(Component::show);
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
		super.getComponents().forEach(Component::hide);
		contentText.hide();
		contentBackground.hide();
	}

	public void hideFromDialog() {
		initHide();
	}

	public void recreate() {
		contentBackground.recreate();
		contentText.recreate();
		pages.forEach(Page::recreate);
		super.getComponents().forEach(Component::recreate);

		if (dialog.getPanelDialog() != null)
			dialog.getPanelDialog().recreate();
	}

	public void recreate(ArrayList<Component> addons) {
		recreate();
		addons.forEach(Component::recreate);
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
		super.getComponents().forEach(Component::destroy);
		contentBackground.destroy();
		contentText.destroy();
	}

	@Override
	public boolean isDestroyed() {
		for (Page page : pages) {
			if (!page.isDestroyed())
				return false;
		}
		for (Component component : super.getComponents()) {
			if (!component.isDestroyed())
				return false;
		}
		return !(!contentBackground.isDestroyed() || !contentText.isDestroyed());
	}

	public Dialog getDialog() {
		return dialog;
	}

	@Override
	public ArrayList<Component> getComponents() {
		ArrayList<Component> components = new ArrayList<>(super.getComponents());
		pages.forEach(page -> page.getComponents().forEach(components::add));
		if (dialog.getPanelDialog() != null)
			dialog.getPanelDialog().getComponents().forEach(components::add);
		return components;
	} //TODO add pages and dialoges

	@Override
	public <E extends Component> ArrayList<Component> getComponents(Class<E> compClass) {
		ArrayList<Component> components = new ArrayList<>();
		super.getComponents().forEach(component -> {
			if (component.getClass() == compClass) {
				components.add(component);
			}
		});
		pages.forEach(page -> page.getComponents().forEach(component -> {
			if (component.getClass() == compClass) {
				components.add(component);
			}
		}));
		if (dialog.getPanelDialog() != null)
			dialog.getPanelDialog().getComponents().forEach(component -> {
				if (component.getClass() == compClass) {
					components.add(component);
				}
			});
		return components;
	} //TODO add pages and dialoges

	@Override
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
		for (Component component : super.getComponents()) {
			for (PlayerTextdraw playerTextdraws : component.getAllPlayerTextdraws()) {
				if (playerTextdraws.isPlayerTextdraw(playerTextdraw))
					return component;
			}
		}
		return null;
	}

	@Override
	public <E extends Component> Component getComponent(Class<E> compClass, net.gtaun.shoebill.object.PlayerTextdraw playerTextdraw) {
		for (Page page : pages) {
			for (Component component : page.getComponents()) {
				if (component.getClass() == compClass)
					for (PlayerTextdraw playerTextdraws : component.getAllPlayerTextdraws()) {
						if (playerTextdraws.isPlayerTextdraw(playerTextdraw))
							return component;
					}
			}
		}
		if (dialog.getPanelDialog() != null) {
			for (Component component : dialog.getPanelDialog().getComponents()) {
				if (component.getClass() == compClass)
					for (PlayerTextdraw playerTextdraws : component.getAllPlayerTextdraws()) {
						if (playerTextdraws.isPlayerTextdraw(playerTextdraw))
							return component;
					}
			}
		}
		for (Component component : super.getComponents()) {
			if (component.getClass() == compClass)
				for (PlayerTextdraw playerTextdraws : component.getAllPlayerTextdraws()) {
					if (playerTextdraws.isPlayerTextdraw(playerTextdraw))
						return component;
				}
		}
		return null;
	}

	@Override
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
		for (Component component : super.getComponents()) {
			if(component.getName().equals(name))
				return component;
		}
		return null;
	}

	@Override
	public <E extends Component> Component getComponent(Class<E> compClass, String name) {
		for (Page page : pages) {
			for (Component component : page.getComponents()) {
				if (component.getClass() == compClass)
					if (component.getName().equals(name))
						return component;
			}
		}
		if (dialog.getPanelDialog() != null) {
			for (Component component : dialog.getPanelDialog().getComponents()) {
				if (component.getClass() == compClass)
					if (component.getName().equals(name))
						return component;
			}
		}
		for (Component component : super.getComponents()) {
			if (component.getClass() == compClass)
				if(component.getName().equals(name))
					return component;
		}
		return null;
	}

	@Override
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
		for (Component component : super.getComponents()) {
			if (component.isRequired())
				if(!component.isFilled())
					return true;
		}
		return false;
	}

	@Override
	public <E extends Component> boolean hasMarkedComponents(Class<E> compClass) {
		for (Page page : pages) {
			for (Component component : page.getComponents()) {
				if (component.getClass() == compClass)
					if (component.isRequired())
						if(!component.isFilled())
							return true;
			}
		}
		if (dialog.getPanelDialog() != null) {
			for (Component component : dialog.getPanelDialog().getComponents()) {
				if (component.getClass() == compClass)
					if (component.isRequired())
						if(!component.isFilled())
							return true;
			}
		}
		for (Component component : super.getComponents()) {
			if (component.getClass() == compClass)
				if (component.isRequired())
					if(!component.isFilled())
						return true;
		}
		return false;
	}

	@Override
	public ArrayList<Component> getMarkedComponents() {
		ArrayList<Component> markedComponents = new ArrayList<>();
		for (Page page : pages) {
			page.getComponents().stream().filter(component -> component.isRequired() && !component.isFilled()).forEach(markedComponents::add);
		}
		if (dialog.getPanelDialog() != null) {
			dialog.getPanelDialog().getComponents().stream().filter(component -> component.isRequired() && !component.isFilled()).forEach(markedComponents::add);
		}
		markedComponents.addAll(super.getComponents().stream().filter(component -> component.isRequired() && !component.isFilled()).collect(Collectors.toList()));
		return markedComponents;
	}

	@Override
	public <E extends Component> ArrayList<Component> getMarkedComponents(Class<E> compClass) {
		ArrayList<Component> markedComponents = new ArrayList<>();
		for (Page page : pages) {
			page.getComponents().stream().filter(component -> component.getClass() == compClass && component.isRequired() && !component.isFilled()).forEach(markedComponents::add);
		}
		if (dialog.getPanelDialog() != null) {
			dialog.getPanelDialog().getComponents().stream().filter(component -> component.getClass() == compClass && component.isRequired() && !component.isFilled()).forEach(markedComponents::add);
		}
		markedComponents.addAll(super.getComponents().stream().filter(component -> component.getClass() == compClass && component.isRequired() && !component.isFilled()).collect(Collectors.toList()));
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
	@Override
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
		for(Component component : super.getComponents()) {
			if(component.getName().equals(name))
				return component.getComponentData();
		}
		return null;
	}

	public boolean isShowed() {
		return showed;
	}

	public void clear() {
		super.getComponents().forEach(Component::hide);
		super.getComponents().forEach(Component::destroy);
	}
}
