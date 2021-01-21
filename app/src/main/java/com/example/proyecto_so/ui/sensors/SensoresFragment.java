package com.example.proyecto_so.ui.sensors;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.proyecto_so.R;

import com.example.proyecto_so.threats.AcelerometerThread;
import com.example.proyecto_so.threats.LightThread;

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
    private AcelerometerThread acelerometerThread;
    private Thread threadA;
    private Thread threadL;
    private LightThread lightThread;

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
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_sensores, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button startA = getActivity().findViewById(R.id.button_startA);
        Button stopA = getActivity().findViewById(R.id.button_stopA);
        Button startL = getActivity().findViewById(R.id.button_startL);
        Button stopL = getActivity().findViewById(R.id.button_stopL);
        startA.setOnClickListener(this);
        stopA.setOnClickListener(this);
        startL.setOnClickListener(this);
        stopL.setOnClickListener(this);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.button_startA:
                Log.i(TAG, "ACELEROMETER START");
                try {
                    acelerometerThread = new AcelerometerThread(getActivity());
                    threadA = new Thread(acelerometerThread);
                    threadA.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.button_stopA:
                Log.i(TAG, "ACELEROMETER STOP");
                if (acelerometerThread != null){
                    acelerometerThread.stop();
                    //threadA.stop();
                }
                break;
            case R.id.button_startL:
                Log.i(TAG, "LIGHT START");
                try {
                    lightThread = new LightThread(getActivity());
                    threadL = new Thread(lightThread);
                    threadL.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.button_stopL:
                if (lightThread != null){
                    lightThread.stop();
                    //threadL.stop();
                }
                Log.i(TAG, "LIGHT STOP");
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
