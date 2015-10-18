package com.example.myway.plugin;

import java.util.Comparator;

import com.example.myway.model.ListViewSortModel;

public class PinyinComparator implements Comparator<ListViewSortModel> {

	@Override
	public int compare(ListViewSortModel sortList1, ListViewSortModel sortList2) {
		//这里主要是用来对ListView里面的数据根据ABCDEFG...来排序  
        if (sortList2.getNamePinYin().equals("*")) {  
            return -1;  
        } else if (sortList1.getNamePinYin().equals("*")) {  
            return 1;  
        } else {  
            return sortList1.getNamePinYin().compareTo(sortList2.getNamePinYin());  
        }  
	}

}
