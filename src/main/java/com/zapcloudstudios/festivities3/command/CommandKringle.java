package com.zapcloudstudios.festivities3.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

import com.zapcloudstudios.festivities3.Festivities;
import com.zapcloudstudios.festivities3.kringle.KringleTeleporter;

public class CommandKringle extends CommandBase
{
	@Override
	public String getCommandUsage(ICommandSender icommandsender)
	{
		return "/" + this.getCommandName();
	}

	@Override
	public String getCommandName()
	{
		return "gotokringle";
	}

	@Override
	public void processCommand(ICommandSender icommandsender, String[] astring)
	{
		if (astring.length > 0)
		{
			try
			{
				String username = astring[0];
				if (!this.runCommand(icommandsender, username))
				{
					throw new Exception("Could not find player with name: " + username);
				}
			}
			catch (final Exception var6)
			{
				throw new CommandException(var6.getMessage(), new Object[0]);
			}
		}
		else
		{
			try
			{
				String username = icommandsender.getCommandSenderName();
				if (!this.runCommand(icommandsender, username))
				{
					throw new Exception("Could not find player with name: " + username);
				}
			}
			catch (final Exception var6)
			{
				throw new CommandException(var6.getMessage(), new Object[0]);
			}
		}
	}

	protected boolean runCommand(ICommandSender icommandsender, String username)
	{
		EntityPlayerMP player = (EntityPlayerMP) icommandsender.getEntityWorld().getPlayerEntityByName(username);
		if (player == null)
		{
			return false;
		}
		MinecraftServer mServer = MinecraftServer.getServer();
		if (player.dimension == Festivities.kringleId)
		{
			player.mcServer.getConfigurationManager().transferPlayerToDimension(player, 0, new KringleTeleporter(mServer.worldServerForDimension(0)));
		}
		else
		{
			player.mcServer.getConfigurationManager().transferPlayerToDimension(player, Festivities.kringleId, new KringleTeleporter(mServer.worldServerForDimension(Festivities.kringleId)));
		}
		return true;
	}
}
