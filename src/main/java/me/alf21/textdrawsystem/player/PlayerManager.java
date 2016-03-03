package me.alf21.textdrawsystem.player;

import java.io.IOException;

import me.alf21.textdrawsystem.TextdrawSystem;
import me.alf21.textdrawsystem.dialogs.interfaces.PageInterface;
import me.alf21.textdrawsystem.dialogs.interfaces.PanelInterface;
import me.alf21.textdrawsystem.dialogs.panel.Panel;
import me.alf21.textdrawsystem.content.components.Component;
import me.alf21.textdrawsystem.content.components.Text;
import me.alf21.textdrawsystem.content.components.input.Input;
import me.alf21.textdrawsystem.content.components.input.InputType;
import me.alf21.textdrawsystem.content.pages.Page;
import me.alf21.textdrawsystem.dialogs.panel.layouts.PanelLayout;
import me.alf21.textdrawsystem.dialogs.panel.types.PanelType;
import me.alf21.textdrawsystem.utils.PlayerTextdraw;
import net.gtaun.shoebill.event.player.*;
import net.gtaun.shoebill.object.Player;
import net.gtaun.util.event.EventManager;

public class PlayerManager
{	
	public EventManager eventManager;
	public PlayerData playerLifecycle;

	public PlayerManager() throws IOException
	{	
		eventManager = TextdrawSystem.getInstance().getEventManagerInstance();
		
		eventManager.registerHandler(PlayerSpawnEvent.class, (e) -> {
			Player player = e.getPlayer();
			playerLifecycle = TextdrawSystem.getInstance().getPlayerLifecycleHolder().getObject(player, PlayerData.class);
			Panel panel = playerLifecycle.getPanel();
			if(panel != null && !panel.isDestroyed()) {
				panel.hide();
				panel.destroy();
			}
			panel = new Panel(player);
			if(panel.getPanelType() == PanelType.PAGE) {
				panel.setPanelLayout(PanelLayout.FORM);
				panel.getLeftButton().setText("BACK");
				panel.getRightButton().setText("NEXT");

				Input input = Input.create(panel.getContent(), panel.getLayout().getSlot(panel.getContent(), 2, 2), "email");
				input.attachLabel("Enter an E-Mail");
				input.setInputTypes(InputType.EMAIL);
				input.toggleRequired(true);

				Input input2 = Input.create(panel.getContent(), panel.getLayout().getSlot(panel.getContent(), 1, 2), "name");
				input2.attachLabel("Enter a Name");
				input2.setInputTypes(InputType.TEXT);
				input2.toggleRequired(true);

				Input input3 = Input.create(panel.getContent(), panel.getLayout().getSlot(panel.getContent(), 3, 2), "password");
				input3.attachLabel("Enter a Password");
				input3.setInputTypes(InputType.PASSWORD);
				input3.toggleRequired(true);

				Page page1 = Page.create();
				page1.getComponents().add(input);
				page1.getComponents().add(input2);
				page1.getComponents().add(input3);

				Input input4 = Input.create(panel.getContent(), panel.getLayout().getSlot(panel.getContent(), 1, 2), "description");
				input4.attachLabel("Enter a Description");
				input4.setInputTypes(InputType.TEXT);
				input4.toggleRequired(false);

				Page page2 = Page.create();
				page2.getComponents().add(input4);

				Page page3 = Page.create(new PageInterface() {
					@Override
					public void onReach() {
						Text text = Text.create(getContent(), getContent().getContentBackground().getPosition(), "text");
						String string = "Email: " + getContent().getComponentData("email").getString();
						string += "~n~Name: " + getContent().getComponentData("name").getString();
						string += "~n~Password: " + getContent().getComponentData("password").getString();
						string += "~n~Description: " + getContent().getComponentData("description").getString();
						text.setText(string);
						getPage().getComponents().add(text);
					}

					@Override
					public void onLeave() {
						getPage().clear();
					}
				});

				panel.getContent().addPage(page1);
				panel.getContent().addPage(page2);
				panel.getContent().addPage(page3);

				panel.getContent().setCurrentPage(page1);
			}
			panel.show();
			playerLifecycle.setPanel(panel);
		});

		eventManager.registerHandler(PlayerClickPlayerTextDrawEvent.class, (e) -> {
			Player player = e.getPlayer();
			playerLifecycle = TextdrawSystem.getInstance().getPlayerLifecycleHolder().getObject(player, PlayerData.class);
			if(playerLifecycle == null)
				return;
			Panel panel = playerLifecycle.getPanel();
			if(panel == null)
				return;
			PlayerTextdraw closeIcon = panel.getCloseIcon();
			PlayerTextdraw leftButton = panel.getLeftButton();
			PlayerTextdraw rightButton = panel.getRightButton();
			if (closeIcon.isPlayerTextdraw(e.getPlayerTextdraw())) {
				panel.onClickCloseIcon();
			} else if (leftButton.isPlayerTextdraw(e.getPlayerTextdraw())) {
				panel.onClickLeftButton();
			} else if (rightButton.isPlayerTextdraw(e.getPlayerTextdraw())) {
				panel.onClickRightButton();
			}
			else {
				Component component = panel.getContent().getComponent(e.getPlayerTextdraw());
				if(component != null) {
					component.onClick();
				}
			}
		});

		eventManager.registerHandler(PlayerDisconnectEvent.class, (e) -> {
			Player player = e.getPlayer();
			playerLifecycle = TextdrawSystem.getInstance().getPlayerLifecycleHolder().getObject(player, PlayerData.class);
			if(playerLifecycle.getPanel() != null && !playerLifecycle.getPanel().isDestroyed()) {
				playerLifecycle.getPanel().hide();
				playerLifecycle.getPanel().destroy();
			}
		});
	}


	public void uninitialize() {
		
	}


	public void destroy() {
		
	}
}