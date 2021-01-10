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

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.proyecto_so.R;

import com.example.proyecto_so.activities.Acelerometer;
import com.example.proyecto_so.activities.Light;
import com.example.proyecto_so.activities.Proximity;
import com.example.proyecto_so.threats.AcelerometerThread;

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
    private com.example.proyecto_so.threats.AcelerometerThread acelerometerThread;
    Thread thread;

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
        Button luz = getActivity().findViewById(R.id.button_luz);
        Button proxi = getActivity().findViewById(R.id.button_proximidad);
        Button acele = getActivity().findViewById(R.id.button_acelerometro);
        luz.setOnClickListener(this);
        proxi.setOnClickListener(this);
        acele.setOnClickListener(this);
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

        Intent intencion;

        switch (v.getId()){

            case R.id.button_luz:
                /*intencion = new Intent(getContext(), Light.class);
                startActivity(intencion);*/
                Log.i(TAG, "ACELEROMETER START");
                try {
                    acelerometerThread = new AcelerometerThread(getActivity());
                    thread = new Thread(acelerometerThread);
                    thread.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.button_proximidad:
                /*intencion = new Intent(getContext(), Proximity.class);
                startActivity(intencion);*/
                Log.i(TAG, "ACELEROMETER STOP");
                if (acelerometerThread != null){
                    acelerometerThread.stop();
                }
                break;
            case R.id.button_acelerometro:
                intencion = new Intent(getContext(), Acelerometer.class);
                startActivity(intencion);
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