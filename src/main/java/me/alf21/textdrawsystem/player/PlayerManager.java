package me.alf21.textdrawsystem.player;

import java.io.IOException;
import java.util.Random;

import me.alf21.textdrawsystem.TextdrawSystem;
import me.alf21.textdrawsystem.calculations.Calculation;
import me.alf21.textdrawsystem.content.Content;
import me.alf21.textdrawsystem.content.attachments.Label;
import me.alf21.textdrawsystem.content.components.bar.Bar;
import me.alf21.textdrawsystem.content.components.bar.BarInterface;
import me.alf21.textdrawsystem.content.components.list.List;
import me.alf21.textdrawsystem.content.components.list.ListItem;
import me.alf21.textdrawsystem.content.components.list.ListItemInterface;
import me.alf21.textdrawsystem.content.pages.PageInterface;
import me.alf21.textdrawsystem.dialogs.layouts.DialogLayout;
import me.alf21.textdrawsystem.dialogs.types.Panel;
import me.alf21.textdrawsystem.content.components.Component;
import me.alf21.textdrawsystem.content.components.Text;
import me.alf21.textdrawsystem.content.components.input.Input;
import me.alf21.textdrawsystem.content.components.input.InputType;
import me.alf21.textdrawsystem.content.pages.Page;
import me.alf21.textdrawsystem.dialogs.types.DialogType;
import me.alf21.textdrawsystem.utils.PlayerTextdraw;
import net.gtaun.shoebill.Shoebill;
import net.gtaun.shoebill.data.Color;
import net.gtaun.shoebill.data.Vector2D;
import net.gtaun.shoebill.event.player.*;
import net.gtaun.shoebill.object.Player;
import net.gtaun.util.event.EventManager;

/**
 * Created by Alf21 on 26.02.2016 in the project 'textdraw-system'.
 */
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
			panel = Panel.create(player);
			if(panel.getDialogType() == DialogType.PAGE) {
				panel.setDialogLayout(DialogLayout.FORM);
				panel.getLeftButton().setText("BACK");
				panel.getRightButton().setText("NEXT");

				Input input = Input.create(panel.getContent(), panel.getLayout().getSlot(panel.getContent(), 2, 2), "email");
				input.attachLabel("Enter an E-Mail");
				input.setInputTypes(InputType.EMAIL);
				input.toggleRequired(true);

				Input input2 = Input.create(panel.getContent(), panel.getLayout().getSlot(panel.getContent(), 1, 2), "name");
				input2.attachLabel("Enter a Name");
				input2.setInputTypes(InputType.NAME);
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

				Bar playerTextdrawBar = Bar.create(
						panel.getContent(),
						panel.getLayout().getSlot(panel.getContent(), 2, 3),
						10, 10,
						0, 100,
						new Color(150, 0, 0, 255),
						new Color(0, 150, 0, 255),
						new BarInterface() {

							@Override
							public void onProcess(Content content, Bar bar) {
								double process = (double) Shoebill.get().getSampObjectManager().getPlayerTextdraws(content.getDialog().getPlayer()).size() / 360.0;
								if (bar.getProcess() != process || bar.getAttachment(bar.getName() + "_label") == null) {
									bar.setProcess(process);
									Label label = bar.attachLabel("PlayerTextdraws~n~(" + Shoebill.get().getSampObjectManager().getPlayerTextdraws(content.getDialog().getPlayer()).size() + " / 360)");
									label.show();
								}
							}
						},
						"playerTextdrawBar"
				);

				Bar fpsBar = Bar.create(
						panel.getContent(),
						panel.getLayout().getSlot(panel.getContent(), 2, 1),
						10, 10,
						0, 100,
						new Color(150, 0, 0, 255),
						new Color(0, 150, 0, 255),
						new BarInterface() {

							@Override
							public void onProcess(Content content, Bar bar) {
								double process = (double) content.getDialog().getPlayer().getPing() / 50.0;
								if (bar.getProcess() != process || bar.getAttachment(bar.getName() + "_label") == null) {
									bar.setProcess(process);
									String text = "Ping~n~(" + content.getDialog().getPlayer().getPing() + " / 50)";
									Label label = bar.attachLabel(text);
									label.show();
								}
							}
						},
						"pingBar"
				);

				Bar packetLossBar = Bar.create(
						panel.getContent(),
						panel.getLayout().getSlot(panel.getContent(), 3, 2),
						10, 10,
						0, 100,
						new Color(150, 0, 0, 255),
						new Color(0, 150, 0, 255),
						new BarInterface() {

							@Override
							public void onProcess(Content content, Bar bar) {
								double process = (double) content.getDialog().getPlayer().getPacketLossPercent();
								if (bar.getProcess() != process || bar.getAttachment(bar.getName() + "_label") == null) {
									bar.setProcess(process);
									String text = "PacketLoss: " + content.getDialog().getPlayer().getPacketLossPercent();
									Label label = bar.attachLabel(text);
									label.show();
								}
							}
						},
						"packetLossBar"
				);

				Page page2 = Page.create();
				page2.getComponents().add(input4);
				page2.getComponents().add(playerTextdrawBar);
				page2.getComponents().add(fpsBar);
				page2.getComponents().add(packetLossBar);

				Page page3 = Page.create(new PageInterface() {

					@Override
					public void onReach(Content content, Page page) {
						Text text = Text.create(content, content.getContentBackground().getPosition(), "text");
						String string = "Email: " + content.getComponentData("email").getString();
						string += "~n~Name: " + content.getComponentData("name").getString();
						string += "~n~Password: " + content.getComponentData("password").getString();
						string += "~n~Description: " + content.getComponentData("description").getString();
						text.setText(string);
						text.attachLabel("Some infos");
						text.attachBox(Color.GREEN);
						page.getComponents().add(text);

						Bar quadBar = Bar.create(
								content,
								content.getDialog().getLayout().getSlot(content, 2, 2),
								10, 30,
								10, 30,
								new Color(150, 0, 0, 255),
								new Color(0, 150, 0, 255),
								new BarInterface() {

									@Override
									public void onProcess(Content content, Bar bar) {
										double process = (double) (new Random().nextInt(101)-1) / 100.0;
										if (bar.getProcess() != process) {
											bar.setProcess(process);
										}
									}
								},
								"quadBar"
						);
						page.getComponents().add(quadBar);
						//TODO only delete items which was created HERE with page.clear()
					}

					@Override
					public void onLeave(Content content, Page page) {
						page.clear();
					}
				});

				Page page4 = Page.create();
				List list = List.create(panel.getContent(), new Vector2D(126.0f, 147.0f), 388, 139, "list");
				list.addListItem("Click me", new ListItemInterface() {

					@Override
					public void onClick(List list, ListItem listItem) {
						list.getPlayer().sendMessage("Clicked");
					}
				});
				list.addListItem("Double Click me", new ListItemInterface() {

					@Override
					public void onDoubleClick(List list, ListItem listItem) {
						list.getPlayer().sendMessage("Double clicked");
					}
				});
				list.addListItem("Test 1", new ListItemInterface() {});
				list.addListItem("Test 2", new ListItemInterface() {});
				list.addListItem("Test 3", new ListItemInterface() {});
				list.addListItem("Test 4", new ListItemInterface() {});
				list.addListItem("Test 5", new ListItemInterface() {});
				list.addListItem("Test 6", new ListItemInterface() {});
				list.addListItem("Test 7", new ListItemInterface() {});
				list.addListItem("Test 8", new ListItemInterface() {});
				list.addListItem("Test 9", new ListItemInterface() {});
				list.addListItem("Test 10", new ListItemInterface() {});
				list.addListItem("Test 11", new ListItemInterface() {});
				list.addListItem("Test 12", new ListItemInterface() {});
				list.addListItem("Test 13", new ListItemInterface() {});
				page4.getComponents().add(list);

				panel.getContent().addPage(page1);
				panel.getContent().addPage(page2);
				panel.getContent().addPage(page3);
				panel.getContent().addPage(page4);

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
					component.onClick(e.getPlayerTextdraw());
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