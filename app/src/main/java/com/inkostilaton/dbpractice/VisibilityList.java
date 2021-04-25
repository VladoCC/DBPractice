package com.inkostilaton.dbpractice;

import java.util.ArrayList;

public class VisibilityList<T extends VisibilityList.Searchable> {

    private ArrayList<Element<T>> list = new ArrayList<>();
    private ArrayList<Integer> shown = new ArrayList<>();

    private boolean updated = true;

    public void add(T data, boolean visible) {
        list.add(new Element<T>(data, visible));
        if (visible) {
            shown.add(list.size() - 1);
        }
    }

    public void add(T data) {
        add(data, true);
    }

    private void update() {
        if (!updated) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).visible) {
                    shown.add(i);
                }
            }
            updated = true;
        }
    }

    private int getIndex(int shownIndex) {
        update();
        return shown.get(shownIndex);
    }

    public T get(int index) {
        return list.get(getIndex(index)).data;
    }

    public boolean isVisible(int index) {
        return list.get(index).visible;
    }

    public void replace(int index, T data, boolean visible) {
        if (index < list.size()) {
            Element elem = list.get(index);
            elem.data = data;
            setVisible(index, visible);
        } else {
            add(data, visible);
        }
    }

    public void setVisible(int index, boolean visible) {
        Element elem = list.get(index);
        if (elem.visible != visible) {
            updated = false;
            shown.clear();
        }
        elem.visible = visible;
    }

    public void setVisibleAll() {
        for (int i = 0; i < list.size(); i++) {
            setVisible(i, true);
        }
    }

    public void setQuery(String query) {
        for (int i = 0; i < list.size(); i++) {
            boolean visible = list.get(i).data.match(query);
            setVisible(i, visible);
        }
    }

    public void removeVisible(int index) {
        int realIndex = getIndex(index);
        if (list.get(realIndex).visible) {
            list.remove(realIndex);
            shown.remove(index);
        }
    }

    public int size() {
        update();
        return shown.size();
    }

    private class Element<D extends Searchable> {
        public D data;
        public boolean visible;

        public Element(D data, boolean visible) {
            this.data = data;
            this.visible = visible;
        }
    }

    public interface Searchable {
        boolean match(String query);
    }
}
