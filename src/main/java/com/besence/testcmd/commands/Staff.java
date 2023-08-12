package com.besence.testcmd.commands;

import org.bukkit.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.Locale;
import java.util.ArrayList;
import java.util.List;

public class Staff implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage(ChatColor.DARK_RED +"consoleからは実行できません");
            return true;
        }
        //command実行者をstaffに格納
        final Player staff = (Player) sender;
        //command実行者がbesence.staffという権限を持っていない、もしくはopを持っていない場合に権限不足メッセージが表示でreturnで返される
        if (!staff.isOp()){
            sender.sendMessage("権限がありません");
        }
        //引数に何も指定しなかった場合にエラーメッセージを表示する
        if (args.length < 1) {
            sender.sendMessage("ゲームモードを指定してください");
            return true;
        }
        //mochiko変数に第一引数を追加
        String mochiko = args[0].toLowerCase();
        if (mochiko.equals("spectator")) {
            //command実行者のgamemodeをspectatorに変更する
            staff.setGameMode(GameMode.SPECTATOR);
            staff.sendMessage(ChatColor.AQUA + "ゲームモードがスペクテイターになりました");
            return true;
        } else if (mochiko.equals("survival")) {
            staff.setGameMode(GameMode.SURVIVAL);
            staff.sendMessage(ChatColor.AQUA + "ゲームモードがサバイバルになりました");
            return true;
        } else if (mochiko.equals("adventure")) {
            staff.setGameMode((GameMode.ADVENTURE));
            staff.sendMessage(ChatColor.AQUA + "ゲームモードがアドベンチャーになりました");
            return true;
        }else if (mochiko.equals(("creative"))){
            staff.sendMessage(ChatColor.DARK_RED + "ま～た権限乱用しようとしたなー怒怒");
            return true;
        } else if (mochiko.equals("co i")) {
            staff.performCommand("co i");
            staff.sendMessage(ChatColor.GOLD + "coreProtectが有効になりました");
            return true;
        } else if (mochiko.equals("teleport")) {
            if (args.length < 3) {
                staff.sendMessage("Playerを指定してください🅂");
                return true;
            }

            Player targetPlayer = staff.getServer().getPlayer(args[1]);
            Player destinationPlayer = staff.getServer().getPlayer(args[2]);

            if (targetPlayer == null || destinationPlayer == null) {
                staff.sendMessage("プレイヤーが見つかりません。");
                return true;
            }

            targetPlayer.teleport(destinationPlayer);
            staff.sendMessage(targetPlayer.getName() + " を " + destinationPlayer.getName() + " の場所にテレポートしました。");
        } else if (mochiko.equals("kick")) {
            if (args.length < 2) {
                sender.sendMessage(ChatColor.GOLD + "プレイヤー名を入れてください");
                return true;
            }

            Player pl = (Player) sender;
            Player killed = staff.getServer().getPlayer(args[1]);

            if (killed == null) {
                sender.sendMessage(ChatColor.GOLD + "そのプレイヤーは現在Besenceにいません");
                return true;
            }

            killed.kickPlayer(ChatColor.AQUA + "迷惑行為");
        }

        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();
        if (args.length == 1) {
            completions.add("spectator");
            completions.add("survival");
            completions.add("adventure");
            completions.add("creative");
            completions.add("teleport");
            completions.add("co i");
            completions.add("kick");
            if (args[0].equalsIgnoreCase("teleport") && args.length <= 3) {
                for (Player player : sender.getServer().getOnlinePlayers()) {
                    completions.add(player.getName());
                }
            }
            if (args[0].equalsIgnoreCase("kick") && args.length == 2) {
                Player[] onlinePlayers = Bukkit.getOnlinePlayers().toArray(new Player[0]);
                List<String> playerNames = new ArrayList<>();

                for (Player player : onlinePlayers) {
                    playerNames.add(player.getName());
                }

                // タブ補完に使用するプレイヤー名のリストを設定
                return playerNames;
            }
        }


        return completions;
    }
}
