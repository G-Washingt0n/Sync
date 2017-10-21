package com.gnyblecraft.marcul.ideasproject.userProfile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gnyblecraft.marcul.ideasproject.ExploreActivity;
import com.gnyblecraft.marcul.ideasproject.R;

import java.util.ArrayList;

/**
 * Created by lenovo on 17.10.2017.
 */

public class ProfileFragment extends Fragment {


    private RecyclerView recyclerView;
    private ArrayList<String> userInfo = new ArrayList<>();
    private String hostname = "https://save-ideas.com/api";
    private enum LayoutManagerType {
        LINEAR_LAYOUT_MANAGER
    }
    private static final String TAG = "RecyclerViewFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    protected LayoutManagerType currentLayoutManagerType;

    protected RecyclerView.LayoutManager layoutManager;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userInfo.add(ExploreActivity.preferences.getString(ExploreActivity.USER_NAME,null));
        userInfo.add(ExploreActivity.preferences.getString(ExploreActivity.USER_EMAIL,null));


        //getUser();
        //здесь бы сделать запрос и получить текущего юзера, но долго и recycle проскакивает
        //ProfileModel profileData = ProfileModel.getInstance(0); //любое число не ноль чтобы вернуть пользователя

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile,container,false);
        root.setTag(TAG);

        recyclerView = (RecyclerView) root.findViewById(R.id.profile_recyclerView);
        layoutManager = new LinearLayoutManager(getActivity());
        currentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        if (savedInstanceState != null) {
            // Restore saved layout manager type.
            currentLayoutManagerType = (LayoutManagerType) savedInstanceState
                    .getSerializable(KEY_LAYOUT_MANAGER);
        }
        recyclerView.setLayoutManager(layoutManager);
        ProfileAdapter profileAdapter = new ProfileAdapter(userInfo);
        recyclerView.setAdapter(profileAdapter);


        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            }
        }, 3500);
*/



         /* findViewById(R.id.tabIdeas).setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            TextView text = (TextView) findViewById(R.id.fragmentTextView);
            text.setText("Ideas Fragment");
            showFragment(getSupportFragmentManager(),new IdeasFragment());
        }
    });


        findViewById(R.id.tabCertificates).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                TextView text = (TextView) findViewById(R.id.fragmentTextView);
                text.setText("Certificates Fragment");
                showFragment(getSupportFragmentManager(),new CertificatesFragment());
            }
        });

        findViewById(R.id.tabFavorites).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                TextView text = (TextView) findViewById(R.id.fragmentTextView);
                text.setText("Favorites Fragment");
                showFragment(getSupportFragmentManager(),new FavoritesFragment());
            }
        });
*/
     return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

   /* private void getUser() {

        RestAdapter adapter = new RestAdapter(getApplicationContext(),hostname);
        LogInViewModel.UserRepository userRepository = adapter.createRepository(LogInViewModel.UserRepository.class);

        userRepository.findById(ExploreActivity.preferences.getString(ExploreActivity.USER_ID,null), new ObjectCallback<LogInViewModel.User>() {
            @Override
            public void onSuccess(LogInViewModel.User object) {

                Log.e("Get Profile data:", object.getName());
                if(object.getName()!=null)
                    userInfo.add(object.getName());

                Log.e("Get Profile data:", object.getSurname());
                if(object.getSurname()!=null)
                    userInfo.add(object.getSurname());

                if(object.getCity()!=null && object.getCountry()!=null)
                    userInfo.add(object.getCountry() + ", " + object.getCity());

                Log.e("Get Profile data:", object.getEmail());
                if(object.getEmail()!=null)
                    userInfo.add(object.getEmail());

                if(object.getSecondEmail()!=null)
                    userInfo.add(object.getSecondEmail());
                if(object.getDetails()!=null)
                    userInfo.add(object.getDetails());

                Log.e("In Adapter items count:",String.valueOf(userInfo.size()));

            }

            @Override
            public void onError(Throwable t) {
                Log.e("Get Profile data error:", t.toString());
            }
        });
    }*/

    public static void showFragment(FragmentManager fragmentManager, Fragment fragment){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_profile,fragment, fragment.getClass().getName());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }
}
