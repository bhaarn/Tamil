package com.padhuga.tamil.listeners;


import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.DragEvent;
import android.view.View;
import android.widget.AbsListView;

import com.padhuga.tamil.adapters.ItemBaseAdapter;
import com.padhuga.tamil.models.Item;
import com.padhuga.tamil.models.PassObject;
import com.padhuga.tamil.models.Results;
import com.padhuga.tamil.utils.AddOrRemove;
import com.padhuga.tamil.utils.AlertGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemBaseDragListener implements View.OnDragListener {

    private final Item me;
    private final Context context;
    private ArrayList<Results> results;

    public ItemBaseDragListener(Item i, Context context, ArrayList<Results> results) {
        me = i;
        this.context = context;
        this.results = results;
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {

        //Used to resume original color in drop ended/exited
        int resumeColor = ContextCompat.getColor(context, android.R.color.background_light);
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                v.setBackgroundColor(0x30000000);
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                v.setBackgroundColor(resumeColor);
                break;
            case DragEvent.ACTION_DROP:
                PassObject passObj = (PassObject) event.getLocalState();
                View view = passObj.view;
                Item passedItem = passObj.item;
                List<Item> srcList = passObj.srcList;
                AbsListView oldParent = (AbsListView) view.getParent();
                ItemBaseAdapter srcAdapter = (ItemBaseAdapter) (oldParent.getAdapter());

                AbsListView newParent = (AbsListView) v.getParent();
                ItemBaseAdapter destAdapter = (ItemBaseAdapter) (newParent.getAdapter());
                List<Item> destList = destAdapter.getList();

                int removeLocation = srcList.indexOf(passedItem);
                int insertLocation = destList.indexOf(me);
                /*
                 * If drag and drop on the same list, same position,
				 * ignore
				 */
                //   if (srcList != destList || removeLocation != insertLocation) {
                AddOrRemove addOrRemove = new AddOrRemove();
                if (addOrRemove.removeItemToList(srcList, passedItem)) {
                    destList.add(insertLocation, passedItem);
                }

                passedItem.ItemPlaceValue = insertLocation + 1;
                me.ItemPlaceValue = removeLocation + 1;

                srcAdapter.notifyDataSetChanged();
                destAdapter.notifyDataSetChanged();
                AlertGenerator alertGenerator = new AlertGenerator();

                if (srcAdapter.getList().size() == 0) {                //oldParent.getTag().equals("listView1") &&
                    ArrayList<Integer> droppedItems = new ArrayList<>();
                    for (int i = 0; i < destList.size(); i++) {
                        droppedItems.add(destList.get(i).ItemPlaceValue);
                    }
                    ArrayList<Integer> a;
                    ArrayList<Integer> b;
                    ArrayList<Integer> c;
                    ArrayList<Integer> d;
                    ArrayList<Integer> e;
                    a = b = c = d = e = droppedItems;
                     /*   if (results.get(0).getParent1() != null && results.get(0).getParent2() == null) {
                            int startIdx = Collections.indexOfSubList(results.get(0).getParent1(), a);
                            if (-1 != startIdx)
                            {
                                int endIdx = startIdx + a.size() - 1;
                            }
                        } else {*/
                    if (results.get(0).getParent1() != null && a.size() == results.get(0).getParent1().size() && a.removeAll(results.get(0).getParent1()) && a.size() == 0) {
                        alertGenerator.buildAlert(context, true);
                    } else if (results.get(0).getParent2() != null && b.size() == results.get(0).getParent2().size() && b.removeAll(results.get(0).getParent2()) && b.size() == 0) {
                        alertGenerator.buildAlert(context, true);
                    } else if (results.get(0).getParent3() != null && c.size() == results.get(0).getParent3().size() && c.removeAll(results.get(0).getParent3()) && c.size() == 0) {
                        alertGenerator.buildAlert(context, true);
                    } else if (results.get(0).getParent4() != null && d.size() == results.get(0).getParent4().size() && d.removeAll(results.get(0).getParent4()) && d.size() == 0) {
                        alertGenerator.buildAlert(context, true);
                    } else if (results.get(0).getParent5() != null && e.size() == results.get(0).getParent5().size() && e.removeAll(results.get(0).getParent5()) && e.size() == 0) {
                        alertGenerator.buildAlert(context, true);
                    } else {
                        alertGenerator.buildAlert(context, false);
                    }
                }
                //  }
                //    }
                else {
                    if (results.get(0).getParent1() != null && results.get(0).getParent2() == null) {
                        ArrayList<Integer> a;
                        ArrayList<Integer> droppedItems = new ArrayList<>();
                        for (int i = 0; i < destList.size(); i++) {
                            droppedItems.add(destList.get(i).ItemPlaceValue);
                        }
                        a = droppedItems;
                        int startIdx = Collections.indexOfSubList(results.get(0).getParent1(), a);
                        if (-1 != startIdx) {
                           // int endIdx = startIdx + a.size() - 1;
                            alertGenerator.buildAlert(context, true);
                        } else {
                            alertGenerator.buildAlert(context, false);
                        }
                    }
                }

                v.setBackgroundColor(resumeColor);

                break;
            case DragEvent.ACTION_DRAG_ENDED:
                v.setBackgroundColor(resumeColor);
            default:
                break;
        }

        return true;
    }

}
