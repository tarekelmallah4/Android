package ca.windmobile.mytestingprojects.mytestingproject;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CourceDescriptionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CourceDescriptionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CourceDescriptionFragment extends Fragment {

    //My Code
    String[] arr_cource_titles;
    String[] arr_cource_descriptions;
    TextView txtCourceTitle ;
    TextView txtCourceDescription;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ///My Code
        arr_cource_descriptions = getResources().getStringArray(R.array.cource_descriptions);
        arr_cource_titles = getResources().getStringArray(R.array.cource_titles);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View theView =  inflater.inflate(R.layout.fragment_cource_description, container, false);

        //My Code
        txtCourceTitle = (TextView) theView.findViewById(R.id.txtCourceTitle);
        txtCourceDescription = (TextView) theView.findViewById(R.id.txtCourceDescription);

        return theView;
    }


    public void setCource(int courceIndex){
        //custom method should be called from the other fragment (listing)
        txtCourceTitle.setText(arr_cource_titles[courceIndex]);
        txtCourceDescription.setText(arr_cource_descriptions[courceIndex]);
    }
}
