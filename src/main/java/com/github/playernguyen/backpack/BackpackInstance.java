package com.github.playernguyen.backpack;

import com.github.playernguyen.backpack.language.BackpackLanguage;

public abstract class BackpackInstance {

    public Backpack getBackpack() {
        return Backpack.getBackpack();
    }

    public BackpackLanguage getLanguage() {
        return getBackpack().getLanguage();
    }

}
