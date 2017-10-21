package com.gnyblecraft.marcul.ideasproject.sideNavigationMenu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gnyblecraft.marcul.ideasproject.R;

import java.util.ArrayList;

/**
 * Created by lenovo on 16.10.2017.
 */

public class AboutUsFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<String> urlList = new ArrayList<>();
    private ArrayList<String> nameList = new ArrayList<>();
    private ArrayList<String> positionsList = new ArrayList<>();
    private ArrayList<String> descriptionList = new ArrayList<>();
    private ArrayList<String> headerList = new ArrayList<>();
    private ArrayList<String> largeTextList = new ArrayList<>();
    private ArrayList<String> contactsTypeList = new ArrayList<>();
    private ArrayList<String> contactsInfoList = new ArrayList<>();

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

        urlSet();
        nameSet();
        positionsSet();
        descriptionsSet();
        headersSet();
        textSet();
        contTypeSet();
        contInfoSet();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_about_us,container,false);
        root.setTag(TAG);


        recyclerView = (RecyclerView) root.findViewById(R.id.aboutus_recyclerView);
        layoutManager = new LinearLayoutManager(getActivity());
        currentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        if (savedInstanceState != null) {
            // Restore saved layout manager type.
            currentLayoutManagerType = (LayoutManagerType) savedInstanceState
                    .getSerializable(KEY_LAYOUT_MANAGER);
        }
        recyclerView.setLayoutManager(layoutManager);
        AboutUsAdapter aboutUsAdapter = new AboutUsAdapter(getActivity(),urlList,nameList,positionsList,descriptionList,headerList,largeTextList,contactsTypeList,contactsInfoList);
        recyclerView.setAdapter(aboutUsAdapter);

        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * *Вызывается когда активити в которой леит фрагмент создана.
     * Посл этого метода можно исполтьзовать метод getActivity().
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void contInfoSet() {
        contactsInfoList.add("1800 4 IDEAS (43327)");
        contactsInfoList.add("+612 61816894");
        contactsInfoList.add("mail@save-ideas.com");
        contactsInfoList.add("support@save-ideas.com");
        contactsInfoList.add("https://save-ideas.com");
    }

    private void contTypeSet() {
        contactsTypeList.add("Phone:");
        contactsTypeList.add("Int phone:");
        contactsTypeList.add("Email:");
        contactsTypeList.add(" ");
        contactsTypeList.add("Website:");
    }

    private void textSet() {
        largeTextList.add(getString(R.string.Project_info_text1));
        largeTextList.add(getString(R.string.Project_info_text2));
        largeTextList.add(getString(R.string.Project_info_text3));
        largeTextList.add(getString(R.string.Project_info_text4));
        largeTextList.add(getString(R.string.Missions_text));
    }

    private void headersSet() {
        headerList.add("The project");
        headerList.add("Our mission");
        headerList.add("Our team");
        headerList.add("Our contacts");
    }


    private void urlSet(){
        urlList.add("https://saveideas.s3.amazonaws.com/team/hazbo_skoko@3x.png");
        urlList.add("https://saveideas.s3.amazonaws.com/team/Robert_Hood.png");
        urlList.add("https://saveideas.s3.amazonaws.com/team/Tatiana_Foltea.png");
        urlList.add("https://saveideas.s3.amazonaws.com/team/Talbots.png");
        urlList.add("https://saveideas.s3.amazonaws.com/team/vito_bobek@3x.png");
        urlList.add("https://saveideas.s3.amazonaws.com/team/Abdulellah_Al-Saud.png");
        urlList.add("https://s8.hostingkartinok.com/uploads/images/2017/10/22f7f47d41b440c95c8f20447756d88b.jpg"); //заменить на адрес с сайта
        urlList.add("https://saveideas.s3.amazonaws.com/team/lesley_williams@3x.png");
        urlList.add("https://saveideas.s3.amazonaws.com/team/Abdul_Mannan.png");
        urlList.add("https://saveideas.s3.amazonaws.com/team/Aniesi_Theresa_Unoma.png");
        urlList.add("https://saveideas.s3.amazonaws.com/team/Teshager_Lemma.png");
        urlList.add("https://saveideas.s3.amazonaws.com/team/Nadejda_Foltea.png");
        urlList.add("https://saveideas.s3.amazonaws.com/team/placide_tapsoba@3x.png");
        urlList.add("https://saveideas.s3.amazonaws.com/team/gordana_and_branko.png");
        urlList.add("https://saveideas.s3.amazonaws.com/team/Ferenc_Guba.png");
        urlList.add("https://saveideas.s3.amazonaws.com/team/helle_sandbring@3x.png");
        urlList.add("https://saveideas.s3.amazonaws.com/team/Saleh_Mukhashin.png");

    }

    private void nameSet(){
        nameList.add("Prof Dr Hazbo Skoko");
        nameList.add("Mr Robert Hood");
        nameList.add("Ms Tatiana Foltea");
        nameList.add("Talbots Pty Ltd, Accountants");
        nameList.add("Prof Dr Vito Bobek");
        nameList.add("HRH Mr Abdulellah Al-Saud");
        nameList.add("Ms. Daniela Ramírez Navarro");
        nameList.add("Dr Lesley Williams");
        nameList.add("Mr Md. Abdul Mannan MA (Mgmt)");
        nameList.add("Ms Aniesi Theresa Unoma");
        nameList.add("Teshager A Lemma");
        nameList.add("Ms Nadejda Foltea");
        nameList.add("Placide Tapsoba MD");
        nameList.add("Gordana Matijasevic MD and Prof Dr Branko Cavric");
        nameList.add("Dr. Ferenc Guba");
        nameList.add("Ms Helle Sandbring");
        nameList.add("Mr Saleh Mukashin");

    }

    private void positionsSet(){
        positionsList.add("Founder & CEO");
        positionsList.add("Solicitor");
        positionsList.add("Intellectual Property (IP) Expert Consultant and Head of Italy and Romania Office");
        positionsList.add("Bathurst, NSW Australia");
        positionsList.add("Head of Central European Office (Maribor, Slovenia), and Chief Operation Officer");
        positionsList.add("Head of Middle East Office (Riyadh KSA)");
        positionsList.add("Head of the Central and South American Office");
        positionsList.add("Head of New Zealand and Pacific Office, and Chief Marketing Officer");
        positionsList.add("Head of Bangladesh, Pakistan and India Office and Chief IT Officer");
        positionsList.add("Head of Nigeria Office");
        positionsList.add("Head of Ethiopia Office (Addis Ababa, Ethiopia)");
        positionsList.add("Head of Moldova Office");
        positionsList.add("Head of Central and Western Africa (Accra, Ghana)");
        positionsList.add("Head of Central and South Africa Office (Gaborone, Botswana)");
        positionsList.add("Head of Hungary Office (Budapest, Hungary)");
        positionsList.add("Head of Scandinavia Office (Malmo, Sweden)");
        positionsList.add("Head of Tanzania and Kenya Office (Nairobi, Kenya)");


    }

    private void descriptionsSet(){
        descriptionList.add(getString(R.string.Prof_Hasbo_description));
        descriptionList.add(getString(R.string.Mr_Hood_description));
        descriptionList.add(getString(R.string.Ms_Foltea_description));
        descriptionList.add(getString(R.string.Talbots_description));
        descriptionList.add(getString(R.string.Prof_Bobek_description));
        descriptionList.add(getString(R.string.Mr_Al_Saud_description));
        descriptionList.add(getString(R.string.Ms_Daniela_Ramirez_description));
        descriptionList.add(getString(R.string.Dr_Williams_description));
        descriptionList.add(getString(R.string.Mr_Abdul_description));
        descriptionList.add(getString(R.string.Ms_Unoma_description));
        descriptionList.add(getString(R.string.Mr_Lemma_description));
        descriptionList.add(getString(R.string.Ms_Nadejda_Foltea_description));
        descriptionList.add(getString(R.string.Mr_Tapsoba_description));
        descriptionList.add(getString(R.string.Gordan_and_Branko_description));
        descriptionList.add(getString(R.string.Dr_Ferenc_description));
        descriptionList.add(getString(R.string.Ms_Sandbring_description));
        descriptionList.add(getString(R.string.Mr_Mukashin_description));
    }


}

