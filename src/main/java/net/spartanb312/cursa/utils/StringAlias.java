package net.spartanb312.cursa.utils;

import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;

public class StringAlias {

    public static int ENGLISH = 1;
    public static int FRENCH = 2;
    public static int CHINESE = 3;
    public static int JAPANESE = 4;

    private final Int2ObjectArrayMap<String> aliasMap = new Int2ObjectArrayMap<>();
    private final String name;
    private String current;

    public StringAlias(String name) {
        this.name = name;
    }

    public StringAlias(String name, int defaultSeed) {
        this.name = name;
        addAlias(defaultSeed, name);
        current = name;
    }

    public String getName() {
        return name;
    }

    public String getCurrent() {
        return current;
    }

    public void updateWithSeed(int seed) {
        current = aliasMap.get(seed);
    }

    public void addAlias(int seed, String alias) {
        aliasMap.put(seed, alias);
    }

    public void removeAlias(int seed) {
        aliasMap.remove(seed);
    }

}
