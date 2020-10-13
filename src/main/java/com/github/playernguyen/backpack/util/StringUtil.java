package com.github.playernguyen.backpack.util;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {

    public static List<String> toList(String s) {
        List<String> stringList = new ArrayList<>();
        String[] split = s.split("\\|");
        for (String s1 : split) {
            stringList.add(ChatColor.translateAlternateColorCodes('&', s1));
        }
        return stringList;
    }

}
