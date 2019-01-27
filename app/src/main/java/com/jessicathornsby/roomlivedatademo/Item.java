package com.jessicathornsby.roomlivedatademo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "item_table")
public class Item {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "item")
    private String mItem;

    public Item(@NonNull String item) {
        this.mItem = item;}

    public String getItem(){return this.mItem;}
}
