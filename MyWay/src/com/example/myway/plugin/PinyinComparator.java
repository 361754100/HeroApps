package com.example.myway.plugin;

import java.util.Comparator;

import com.example.myway.model.ListViewSortModel;

public class PinyinComparator implements Comparator<ListViewSortModel> {

	@Override
	public int compare(ListViewSortModel sortList1, ListViewSortModel sortList2) {
		//������Ҫ��������ListView��������ݸ���ABCDEFG...������  
        if (sortList2.getNamePinYin().equals("*")) {  
            return -1;  
        } else if (sortList1.getNamePinYin().equals("*")) {  
            return 1;  
        } else {  
            return sortList1.getNamePinYin().compareTo(sortList2.getNamePinYin());  
        }  
	}

}
