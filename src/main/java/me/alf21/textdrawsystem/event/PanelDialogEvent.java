/**
 * Copyright (C) 2011 MK124
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.alf21.textdrawsystem.event;

import me.alf21.textdrawsystem.panelDialog.AbstractPanelDialog;
import me.alf21.textdrawsystem.panelDialog.PanelDialog;
import net.gtaun.shoebill.object.Player;
import net.gtaun.util.event.Event;

/**
 * This class is the base class of every DialogEvent.
 * 
 * @author MK124
 * @author Marvin Haschker
 * @author Alf21
 * @see net.gtaun.util.event.Event
 */
public abstract class PanelDialogEvent extends Event
{
	private AbstractPanelDialog panelDialog;
	private Player player;
	
	
	protected PanelDialogEvent(AbstractPanelDialog panelDialog, Player player)
	{
		this.panelDialog = panelDialog;
		this.player = player;
	}
	
	public AbstractPanelDialog getDialog()
	{
		return panelDialog;
	}
	
	public Player getPlayer()
	{
		return player;
	}
}
