// created 03-09-2010

// http://www.java2s.com/Code/Java/Swing-JFC/JTablesorterclickthetableheadertosortacolumnandatable.htm

package com.tool.guicomps;

import java.util.*;


class TableComparator implements Comparator {
  protected boolean isSortAsc;

  public TableComparator( boolean sortAsc) {
    isSortAsc = sortAsc;
  }

  public int compare(Object o1, Object o2) {
    if (!(o1 instanceof Integer) || !(o2 instanceof Integer))
      return 0;
    Integer s1 = (Integer) o1;
    Integer s2 = (Integer) o2;
    int result = 0;
    result = s1.compareTo(s2);
    if (!isSortAsc)
      result = -result;
    return result;
  }

  public boolean equals(Object obj) {
    if (obj instanceof TableComparator) {
      TableComparator compObj = (TableComparator) obj;
      return compObj.isSortAsc == isSortAsc;
    }
    return false;
  }
}