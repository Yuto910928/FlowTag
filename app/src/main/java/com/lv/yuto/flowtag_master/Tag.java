package com.lv.yuto.flowtag_master;

/**
 * Created by lv158 on 2016/7/30.
 */
public class Tag {
    public int id;
    public String name;
    public boolean isSelected;

    public Tag(int id, String name, boolean isSelected) {
        this.id = id;
        this.name = name;
        this.isSelected = isSelected;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
