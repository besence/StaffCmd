package com.besence.testcmd.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class deathmochiko implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage(ChatColor.DARK_RED + "このコマンドはサーバーから実行できません");
            return true;
        }

        if (!(sender.isOp())) {
            sender.sendMessage("あなたは管理者権限を持っていません");
            return true;
        }
        if (args.length < 1) {
            sender.sendMessage("誤ったコマンドを使用しています");
        }
        String moti = args[0].toLowerCase();

        if (moti.equalsIgnoreCase("gg")){
            sender.sendMessage("good game");
            return true;

        }

        return true;
    }

}
