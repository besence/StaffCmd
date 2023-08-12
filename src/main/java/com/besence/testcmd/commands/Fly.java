package com.besence.testcmd.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Fly implements CommandExecutor, TabCompleter{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("このコマンドはPlayerしか実行できねーよばーかw");
            return true;
        }
        if (!sender.isOp()) {
            sender.sendMessage("Opねぇのに実行しようとすんなよカスww");
            return true;
        }

        final Player p = (Player) sender;

        if (args.length < 1) {
            p.sendMessage("引数を指定してください。");
            return true;
        }
        String Arg1 = args[0].toLowerCase();
        if (Arg1.equalsIgnoreCase("on")) {
            p.setAllowFlight(true);
            p.sendMessage("飛行が有効になったよ");

        } else if (Arg1.equalsIgnoreCase("off")) {
            p.setAllowFlight(false);
            p.sendMessage("飛行が無効になったよ");

            return true;
        }
        return true;
    }


    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args){
        List<String> comList = new ArrayList<>();
        if (args.length == 1){
            comList.add("on");
            comList.add("off");
        }
        return comList;
    }
}
