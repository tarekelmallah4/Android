package ca.windmobile.mytestingprojects.mytestingproject;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FragmentTest extends AppCompatActivity implements OnCourceSelectionChangesListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_test);
    }

    @Override
    public void OnCourceSelectionChanges(int courceIndex) {
        FragmentManager mng = getFragmentManager();
        CourceDescriptionFragment frgCourceDesc = (CourceDescriptionFragment) mng.findFragmentById(R.id.frgCourceDescription);
        frgCourceDesc.setCource(courceIndex);
    }
}
