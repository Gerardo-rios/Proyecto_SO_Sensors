package com.example.proyecto_so.ui.sensors;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto_so.R;

import com.example.proyecto_so.enumSensor.SensorsEnum;
import com.example.proyecto_so.threats.Threads;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SensoresFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SensoresFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SensoresFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public final String TAG = SensoresFragment.class.getName();

    Thread tg, tl, tp;
    Threads s1, s2, s3;


    public SensoresFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SensoresFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SensoresFragment newInstance(String param1, String param2) {
        SensoresFragment fragment = new SensoresFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_sensores, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ImageView e1 = getActivity().findViewById(R.id.btn_start1);
        ImageView e2 = getActivity().findViewById(R.id.btn_start2);
        ImageView e3 = getActivity().findViewById(R.id.btn_start3);

        ImageView f1 = getActivity().findViewById(R.id.btn_stop1);
        ImageView f2 = getActivity().findViewById(R.id.btn_stop2);
        ImageView f3 = getActivity().findViewById(R.id.btn_stop3);

        e1.setOnClickListener(this);
        e2.setOnClickListener(this);
        e3.setOnClickListener(this);
        f1.setOnClickListener(this);
        f2.setOnClickListener(this);
        f3.setOnClickListener(this);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btn_start1:

                Log.i(TAG, "GYROSCOPIO START");
                try {
                    s1 = new Threads(getActivity(), SensorsEnum.GYROSCOPE);
                    tg = new Thread(s1);
                    tg.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            case R.id.btn_start2:

                Log.i(TAG, "LIGHT START");
                try {
                    s2 = new Threads(getActivity(), SensorsEnum.LIGHT);
                    tl = new Thread(s2);
                    tl.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            case R.id.btn_start3:

                Log.i(TAG, "PROXIMITY START");
                try {
                    s3 = new Threads(getActivity(), SensorsEnum.PROXIMITY);
                    tp = new Thread(s3);
                    tp.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            case R.id.btn_stop1:

                Log.i(TAG, "GYROSCOPE STOP");
                if (s1 != null){
                    s1.stop();
                }

                break;
            case R.id.btn_stop2:

                Log.i(TAG, "LIGHT STOP");
                if (s2 != null){
                    s2.stop();
                }

                break;
            case R.id.btn_stop3:

                Log.i(TAG, "PROXIMITY STOP");
                if (s3 != null){
                    s3.stop();
                }

                break;

        }


    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
