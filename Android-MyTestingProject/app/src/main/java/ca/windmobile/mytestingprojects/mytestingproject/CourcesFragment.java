package ca.windmobile.mytestingprojects.mytestingproject;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by link.tmohamed on 5/9/2016.
 */
public class CourcesFragment extends ListFragment {
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //after the parent activity completely created
        String[] arrCourceTitles = getResources().getStringArray(R.array.cource_titles);
        ArrayAdapter<String> arrAdap = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,arrCourceTitles);
        setListAdapter(arrAdap);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        OnCourceSelectionChangesListener actvCources = (OnCourceSelectionChangesListener) getActivity();
        actvCources.OnCourceSelectionChanges(position);
    }
}
